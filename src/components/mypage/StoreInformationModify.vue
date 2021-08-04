<template>
  <v-main>
    <v-container>
      <h2>
        (판매자)회원 정보 수정
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

        <v-row>
          <v-col
            cols="12"
            md="10"
          >
            <v-text-field
              v-model="email"
              :error-messages="emailErrors"
              label="가게주소"
              required
              @input="$v.email.$touch()"
              @blur="$v.email.$touch()"
            />
          </v-col>
          <v-col
            cols="12"
            md="2"
          >
            <v-btn
              class="mr-4"
              color="primary"
            >
              우편번호 검색
            </v-btn>
          </v-col>


          <v-col
            cols="12"
            md="4"
          >
            <v-select
              v-model="select"
              :items="items"
              :rules="[v => !!v || 'Item is required']"
              label="은행"
              required
            />
          </v-col>
          <v-col
            cols="12"
            md="6"
          >
            <v-text-field
              v-model="title"
              :disabled="isUpdating"
              label="계좌번호"
            />
          </v-col>
          <v-col
            cols="12"
            md="2"
          >
            <v-btn
              class="mr-4"
              color="primary"
            >
              계좌확인
            </v-btn>
          </v-col>
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
        </v-row>
      </v-form>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: 'StoreInformationModify',
}
</script>

<style>

</style>
