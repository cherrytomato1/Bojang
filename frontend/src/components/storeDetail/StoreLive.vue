<template>
  <v-app class="color5">
    <!-- 잘됨 -->
    <div v-if="isOnAir==false">
      <v-img src="@/assets/live_ready.png" />
    </div>
    <div v-if="isOnAir==true">
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
    </div>

    <!-- <v-card
      class="mx-auto"
      max-width="375"
    >
      img height를 812 - 184 = 로 우선설정
      <v-img
        class="white--text align-end"
        height="628px"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
      </v-img>

      <v-card-subtitle class="pb-0">
        채팅창
      </v-card-subtitle>

      <v-card-text
        class="text--primary"
      >
        <div>채팅내역1</div>
        <div>채팅내역2</div>
      </v-card-text>

      <v-card-actions>
        <v-text-field />

        <v-btn
          icon
          color="blue"
        >
          <v-icon>
            mdi-send
          </v-icon>
        </v-btn>
      </v-card-actions>
    </v-card> -->
  </v-app>
</template>

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

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/openvidu/UserVideo';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = 'https://i5a508.p.ssafy.io:8443';
const OPENVIDU_SERVER_SECRET = 'ssafy508';

export default {
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
      sendMsg: '',
      isOnAir:false,
      // mySessionId: this.$store.getters.store.id,
      myUserName: '시장 손님 ' + Math.floor(Math.random() * 100),
    };
  },

  created(){
    axios({
        method:'get',
        url:'http://localhost:8085/api/user',
        headers:{
          Authorization: `Bearer `+ this.$store.getters.getToken
        }
      })
      .then((data) => {
        this.myUserName = data.data.name
      })

    // this.joinSession();
    axios
      .get(
        `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${this.$route.query.storeId}`,

        {
          headers:{
            Authorization:"Basic T1BFTlZJRFVBUFA6c3NhZnk1MDg="
          }
        }
      )

      .then( (data) =>{
        this.isOnAir = true,
        console.log(data)
        this.joinSession()
      })
      .catch(()=>{
        this.isOnAir = false
      })

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

      // On every new Stream received...
      this.session.on('streamCreated', ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
        this.mainStreamManager = this.subscribers[this.subscribers.length - 1];
      });

      this.session.on('signal:my-chat', (event) => {
        this.chats.push(JSON.parse(event.data));
        setTimeout(this.chat_on_scroll, 10);
      });

      // On every Stream destroyed...
      this.session.on('streamDestroyed', ({ stream }) => {
        console.log('방송 종료!');
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        this.isOnAir=false;
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on('exception', ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.$route.query.storeId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {})
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

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
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
