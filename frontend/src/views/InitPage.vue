<template>
  <v-app class="color5">
    <v-container>
      <v-layout
        justify-center
        align-center
        ma-10
      >
        <img
          class="justify-center"
          src="@/assets/logo.png"
          style="width: 400px"
        >
      </v-layout>
      <v-layout
        justify-center
      >
        <p
          class="text-h1"
        >
          보 장
        </p>
      </v-layout>


      <v-layout
        justify-center
      >
        <!-- {{ $store.state.token }} -->
        <div
          v-if="$store.state.token==''"
          class="justify-center align-center"
        >
          <button
            class="justify-center align-center"
            @click="login"
          >
            <a
              :href="kakaoLoginLink"
              alt="kakao login"
            >
              <img
                class="justify-center"
                alt="kakao logo"
                src="@/assets/kakao_login_large.png"
              >
            </a>
          </button>
        </div>

        <div v-else>
          <v-container>
            <v-form

              ref="form"
              v-model="valid"
              lazy-validation
            >
              <v-text-field
                v-model="name"
                :counter="10"
                :rules="nameRules"
                label="이름"
                required
              />

              <v-text-field
                v-model="phone"
                :rules="phoneRules"
                label="연락처"
                required
              />

              <v-select
                v-model="select"
                :items="types"
                :rules="[v => !!v || '고객 유형을 선택해 주세요.']"
                label="고객 유형"
                required
              />

              <v-btn
                v-if="validate"
                :disabled="!valid"
                color="success"
                class="mr-4"
                @click="validate"
              >
                시장 입장하기
              </v-btn>

              <v-btn
                color="error"
                class="mr-4"
                @click="reset"
              >
                입력 초기화
              </v-btn>
            </v-form>
          </v-container>
        </div>
      </v-layout>
    </v-container>
  </v-app>
</template>

<style scoped>
.text-h1{
  color: #3ac569 !important;
  font-family: 'Jua', sans-serif !important;
}
</style>

<script>
import axios from 'axios';
import store from '@/store/store';
// import Vuex from 'vuex';

export default {
  name: "App",
  data: () => ({
    client_id: "d8d2a25fb9a3d72d3564ed9c5d33c6b3",
    redirect_uri: "http://localhost:80/oauth2/redirect",
    log: false,
    name: "OOO",
    valid: true,
    name: '',
    nameRules: [
      v => !!v || '이름을 입력해 주세요.',
      v => (v && v.length <= 10) || 'Name must be less than 10 characters',
    ],
    phone: '',
    phoneRules: [
      v => !!v || '연락처를 입력해 주세요.',
    ],
    select: null,
    types: [
      '손님',
      '시장 상인',
      '픽업 매니저',
    ],
    checkbox: false,
  }),
  computed: {
    kakaoLoginLink() {
      // return `https://kauth.kakao.com/oauth/authorize?client_id=${this.client_id}&redirect_uri=${this.redirect_uri}&response_type=code`;
      // return `http://localhost:80/oauth2/authorize/kakao?redirect_uri=http://localhost:80/oauth2/redirect`;
      return `http://localhost:8080/oauth2/authorize/kakao?redirect_uri=http://localhost:80/oauth2/redirect`;
    },
  },
  methods: {
    login () {
      this.log = false
    },
    validate () {
      this.$refs.form.validate()
      if (this.$refs.form.validate()){
      //   axios({
      //   method: 'patch',
      //   url: `http://localhost:8080/api/user`,
      // })
      // // userType
      //   .then(function (userType) {
      //     console.log(userType)
      //   })
        // console.log(store.state.token)
        // const Token = store.state.getters("getToken", token);
        const Token = store.state.token;
        console.log("token", Token);
        const headers = {
            "Content-Type": "application/json",
            Authorization: "Bearer " + Token,
        };
        // console.log(headers);

        // axios
        //   .get(`http://localhost:8080/api/user`, {
        //     // name: this.name,
        //     // phoneNumber: this.phoneNumber,
        //     // userType: this.userType,
        //     headers: headers,
        //   })
        //   .then(({ data }) => {
        //     if (data == "success") {
        //       console.log("update................", data);
        //       alert("수정 완료!!!");
        //     } else {
        //       alert("수정 중 오류 발생");
        //     }
        //   })
        //   .catch(() => {
        //     alert("오류 발생");
        //     // this.$router.push('/usermypage')

        //   });


      // console.log(this.name)
      // console.log(this.phone)
      // console.log(this.select)
        if (this.select="손님") {
          this.$router.push('/')
        }
        if (this.select="시장상인") {
          this.$router.push('/basket')
        }
        if (this.select="픽업 매니저") {
          this.$router.push('/usermypage')
        }
      }
    },
    // 기존 code
    // validate () {
    //   this.$refs.form.validate()
    //   if (this.$refs.form.validate()){
    //     // mainpage로 이동
    //     if (this.types="손님") {
    //       // console.log(this.types)
    //       this.$router.push('/')
    //     }
    //     // if문으로 하면 이동은 되는데 마지막 if문으로 이동이 대부분
    //     // 분기를 다른 방식으로 할 순 없을까?
    //     else if (this.types="시장상인") {
    //       // console.log(this.types)
    //       this.$router.push('/basket')
    //     }
    //     else if (this.types="픽업 매니저") {
    //       // console.log(this.types)
    //       this.$router.push('/usermypage')
    //     }
    //     // else {
    //     //   this.$router.push('/usermypage')
    //     // }
    //   }
    // },
    reset () {
      this.$refs.form.reset()
    },

  },
}
</script>
