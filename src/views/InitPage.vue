<template>
  <v-container justify="center">
    <img

      src="@/assets/logo.png"
      style="width: 500px"
    >

    <h1>
      보 장
    </h1>
    <!-- v-if로 로그인 상태 확인 -->
    <div v-if="log==false">
      <button @click="login">
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
            to="/mainpage"
          >
            시장 입장하기1
          </v-btn>

          <v-btn
            v-if="validate"
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="validate"
          >
            시장 입장하기2
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
  </v-container>
</template>

<style scoped>

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
