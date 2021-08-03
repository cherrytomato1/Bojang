<template>
  <!-- 아래 적용하면 빨간 줄이 생김 왜???? -->
  <!-- <div>
    <h2> 회원 정보 수정</h2>
  </div> -->
  <v-form>
    <v-text-field
      v-model="name"
      :error-messages="nameErrors"
      :counter="10"
      label="Kakao 계정"
      required
      @input="$v.name.$touch()"
      @blur="$v.name.$touch()"
    />
    <v-text-field
      v-model="email"
      :error-messages="emailErrors"
      label="Name"
      required
      @input="$v.email.$touch()"
      @blur="$v.email.$touch()"
    />
    <v-text-field
      v-model="email"
      :error-messages="emailErrors"
      label="contact"
      required
      @input="$v.email.$touch()"
      @blur="$v.email.$touch()"
    />
    <v-btn
      class="mr-4"
      @click="submit"
    >
      저장
    </v-btn>
    <v-btn @click="clear">
      회원 탈퇴
    </v-btn>
  </v-form>
</template>

<script>
export default {
  name: 'UserInformationModify',
}
</script>

<style>

</style>
