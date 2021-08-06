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
      <!-- v-if로 로그인 상태 확인 -->
      <v-layout
        justify-center
      >
        <div
          v-if="log==false"
          class="justify-center align-center"
        >
          <button
            class="justify-center align-center"
            @click="login"
          >
            <!-- <router-link
        :to="{ name: 'Login'}"
      > -->
            <img src="@/assets/kakao_login_large.png">
            <!-- </router-link> -->
          </button>
        </div>
        <!-- v-else 로그인 되어있을 경우 -->
        <div v-if="log">
          <h1>
            <!-- {{ name }} 고객님 -->
            로그인 되었습니다.
          </h1>
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
export default {
  data: () => ({
    log: false,
    name: "OOO",
    valid: true,
    name: '',
    nameRules: [
      v => !!v || '이름을 입력해 주세요.',
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
  methods: {
    login () {
      this.log = true
    },
    validate () {
      this.$refs.form.validate()
      if (this.$refs.form.validate()){
        this.$router.push('/mainpage')
      }
    },
    reset () {
      this.$refs.form.reset()
    },

  },
}
</script>
