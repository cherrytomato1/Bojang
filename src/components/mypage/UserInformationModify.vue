<template>
  <v-main>
    <v-container>
      <h1>
        회원 정보 수정
      </h1>
      <br>
      <v-form>
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
        <v-row>
          <v-col
            cols="4"
          >
            <v-btn
              class="mr-4"
              @click="submit"
            >
              저장
            </v-btn>
          </v-col>
          <v-col
            offset="5"
          >
            <v-btn
              color="error"
              @click="clear"
            >
              회원 탈퇴
            </v-btn>
          </v-col>
        </v-row>
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
