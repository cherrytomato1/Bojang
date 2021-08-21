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
      <v-layout justify-center>
        <p class="text-h1">
          보 장
        </p>
      </v-layout>

      <v-layout justify-center>
        <div
          v-if="$store.state.token == ''"
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

        <div v-if="isLoaded !== false">
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

              <!-- <v-select
                v-model="userType"
                :items="types"
                :rules="[v => !!v || '고객 유형을 선택해 주세요.']"
                label="고객 유형"
                required
              /> -->

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
.text-h1 {
  color: #3ac569 !important;
  font-family: "Jua", sans-serif !important;
}
</style>

<script>
import axios from "axios";

import { mapGetters } from "vuex";
// import Vuex from 'vuex';
// Vue.use(Vuex)

export default {
  name: "App",

  data: () => ({
    log: false,
    name: "OOO",
    valid: true,
    name: "",
    nameRules: [
      v => !!v || "이름을 입력해 주세요.",
      v => (v && v.length <= 10) || "이름은 10 글자 이하로 작성해주세요"
    ],
    phone: "",
    phoneRules: [v => !!v || "연락처를 입력해 주세요."],
    userType: null,
    types: ["손님", "시장 상인", "픽업 매니저"],
    checkbox: false,
    isLoaded: false
  }),

  mounted() {
    // while ((!this.$store.state.token == "") & (this.$userData != undefined)) {}
    console.log("userData >> " + this.$userData);
    console.log("userData >> " + this.$store.state.userData);

    if (
      (this.userData.length != 0) & (this.userData.phoneNumber != null) &&
      this.userData.phoneNumber != ""
    ) {
      this.$router.push("/mainpage");
    }
  },
  computed: {
    ...mapGetters(["userData"]),
    kakaoLoginLink() {
      // return `http://localhost:8085/oauth2/authorize/kakao?redirect_uri=https://localhost:1024/oauth2/redirect`;
      return `https://i5a508.p.ssafy.io:8085/oauth2/authorize/kakao?redirect_uri=http://i5a508.p.ssafy.io/oauth2/redirect`;
    }
  },

  watch: {
    userData() {
      if (
        this.userData.phoneNumber != undefined &&
        this.userData.phoneNumber.length > 0
      ) {
        this.$router.push("/mainpage");
      }
      this.isLoaded = true;
    }
  },

  methods: {
    login() {
      this.log = false;
    },
    validate() {
      // console.log(this.$store)
      this.$refs.form.validate();
      if (this.$refs.form.validate()) {
        const headers = {
          "Content-Type": "application/json",
          Authorization: "Bearer " + this.$store.getters.getToken
        };
        // const userTypeLongValue = this.types.indexOf(this.userType) + 1;

        axios({
          method: "patch",
          url: `https://i5a508.p.ssafy.io:8085/api/user`,
          headers: headers,
          data: {
            // userType: userTypeLongValue,
            userType: 1,
            name: this.name,
            phoneNumber: this.phone
          }
        })
          // userType
          .then(function(data) {
            console.log(data);
            if (data.status != 200) {
              this.$router.push("/");
            }
          });
        this.$router.push("/mainpage");
      }
    },

    reset() {
      this.$refs.form.reset();
    }
  }
};
</script>
