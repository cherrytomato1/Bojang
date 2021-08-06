<template>
  <v-app class="color5">
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
  </v-app>
</template>

<script>
export default {
  name: 'UserInformationModify',
}
</script>

<style>
.color1{
  background-color: #cff0da !important;
}
.color2{
  background-color: #88dba3 !important;
}
.color3{
  background-color: #dadbdb !important;
}
.color4{
  background-color: #3ac569 !important;
}
.color5{
  background-color: #fFFFF3 !important;
}
</style>
