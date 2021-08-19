<template>
  <v-app class="color5">
    <div id="session-header">
      <h1 id="session-title">
        {{ mySessionId }}
        !!f
      </h1>
      <input
        id="buttonLeaveSession"
        class="btn btn-large btn-danger"
        type="button"
        value="Leave session"
        @click="leaveSession"
      >
    </div>
    <div
      id="main-video"
      class="col-md-6"
    >
      <user-video :stream-manager="mainStreamManager" />
    </div>
    <div class="chat-box">
      <div
        ref="chatDisplay"
        class="chat-display"
      >
        <div
          v-for="(chat, index) in chats"
          :key="index"
          class="chat-line"
        >
          <div
            v-if="chat.userId === myUserName"
            class="my-comment"
          >
            <div>
              <span class="participant-name">{{ chat.nickname }} </span><span class="chat-msg">{{ chat.msg }}</span>
            </div>
          </div>
          <div
            v-else
            class="other-comment"
          >
            <div>
              <span class="participant-name other">{{ chat.nickname }} </span><span class="chat-msg">{{ chat.msg }}</span>
            </div>
          </div>
        </div>
      </div>
      <div class="msg-wrapper">
        <input
          v-model="sendMsg"
          type="text"
          class="msg-input"
          placeholder="메세지를 입력해주세요"
          @keydown.enter="submitMsg"
        >
      </div>
    </div>
  </v-app>
</template>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/openvidu/UserVideo';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = 'https://i5a508.p.ssafy.io:8443';
const OPENVIDU_SERVER_SECRET = 'ssafy508';

export default {
  name: 'LiveManager',

  components: {
    UserVideo,
  },

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      chats: [],
      isFrontCamera: false,
      sendMsg: '',

      mySessionId: this.$store.getters.myStore.id,
      myUserName: this.$store.getters.myStore.name,
    };
  },

  // created(){
  //   this.$store.dispatch("getMyStore");

  // },
  mounted(){
    console.log("mySessionId :"+mySessionId);
    console.log("myUserName : "+myUserName);

    this.joinSession()
  },

  methods: {

    chat_on_scroll() {
      this.$refs.chatDisplay.scrollTop = this.$refs.chatDisplay.scrollHeight;
    },

    submitMsg() {
      if (this.sendMsg.trim() === '') return;
      const sendData = {
        userId: this.myUserName,
        nickname: this.myUserName,
        msg: this.sendMsg,
      };
      this.sendMsg = '';
      this.session
        .signal({
          data: JSON.stringify(sendData), // Any string (optional)
          to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
          type: 'my-chat', // The type of message (optional)
        })
        .then(() => {
          console.log('Message successfully sent');
        })
        .catch((error) => {
          console.error(error);
        });
    },

    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---
      this.session.on('signal:my-chat', (event) => {
        this.chats.push(JSON.parse(event.data));
        setTimeout(this.chat_on_scroll, 10);
      });

      // On every asynchronous exception...
      this.session.on('exception', ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: '640x480', // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;

            // --- Publish your stream ---

            this.session.publish(this.mainStreamManager);
          })
          .catch((error) => {
            console.log('There was an error connecting to the session:', error.code, error.message);
          });
      });

      window.addEventListener('beforeunload', this.leaveSession);
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener('beforeunload', this.leaveSession);
    },

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) => this.createToken(sessionId));
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },
  },
};
</script>

<style>
/* Helper classes */
.basil {
  background-color: #FFFBE6 !important;
}
.vll {
  background-color: #FF6F61 !important;
}
.color1{
  background-color: #cff0da   !important;
}
.color2{
  background-color: #88dba3   !important;
}
.color3{
  background-color: #dadbdb    !important;
}
.color4{
  background-color: #3ac569 !important;
}

.basil--text {
  color: #3ac569 !important;
}
</style>


