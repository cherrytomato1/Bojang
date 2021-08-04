<template>
  <v-main>
    <v-container>
      <h2>
        (구매자)회원 정보 수정
      </h2>
      <v-form>
        <!-- 카카오계정으로 들어온걸 아니까 없애는 건 어떨까?
         이름이 중복된 경우가 있어서? 구현이 조금 다르게 생각해야됨 -->
        <v-text-field
          v-model="name"
          :error-messages="nameErrors"
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
          label="연락처"
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
        <v-btn
          color="error"
          @click="clear"
        >
          회원 탈퇴
        </v-btn>
      </v-form>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: 'UserInformationModify',
}
</script>

<style>

</style>
