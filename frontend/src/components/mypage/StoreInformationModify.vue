<template>
  <v-main>
    <v-container>
      <h2>
        판매자 정보 수정
      </h2>
      <v-form>
        <v-text-field
          v-model="name"
          :counter="10"
          :rules="nameRules"
          label="Name"
          required
        />
        <v-text-field
          v-model="phoneNumber"
          :rules="phoneNumberRules"
          label="phoneNumber"
          required
        />
        <v-text-field
          v-model="address"
          :rules="addressRules"
          label="가게주소"
          required
        />
        <v-row>
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
            md="8"
          >
            <v-text-field
              v-model="title"
              :disabled="isUpdating"
              label="계좌번호"
            />
          </v-col>
          <v-col
            offset="5"
            cols="4"
          >
            <v-btn
              class="mr-4"
              @click="submit"
            >
              저장
            </v-btn>
          </v-col>
          <!-- <v-col
            offset="6"
          >
            <v-btn
              color="error"
              @click="deleteId"
            >
              회원 탈퇴
            </v-btn>
          </v-col> -->
        </v-row>
      </v-form>
    </v-container>
  </v-main>
</template>

<script>
import axios from 'axios'

export default {
  name: 'StoreInformationModify',


 data: () => ({
      valid: true,
      name: '',
      nameRules: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 10) || 'Name must be less than 10 characters',
      ],
      phoneNumber: '',
      phoneNumberRules: [
        v => !!v || 'phoneNumber is required',
        v => /.+@.+\..+/.test(v) || '예시, 010-1234-5678 처럼 입력해주세요',
      ],
      address: '',
      addressRules: [
        v => !!v || 'address is required',
        // 아래 부분 길이 변경하도록 바꾸기 - 찾아보기
        v => (v && v.length <= 10) || '가게 주소를 입력해주세요',
      ],
      select: null,
      // data 가져와서 넣는 것 참고하기
      items: [
        'bank',
      ],
      title: '',
      titleRules: [
        v => !!v || 'title is required',
        v => /.+@.+\..+/.test(v) || '예시, 1002-34-5678 처럼 입력해주세요',
      ],
    }),
    methods: {
      // validate를 바꿔도 됨
      // submit: function(name,phoneNumber) {
        // 400 error 발생 해결방법
      submit: function() {
        // this.$refs.observer.validate()
        axios({
          method:'patch',
          url:'http://localhost:8085/api/user',
          headers:{
            Authorization: `Bearer `+ localStorage.getItem("token")
          },
          data:{
            name: this.name,
            phoneNumber: this.phoneNumber,
          }
        })
        .then((res) => {
          // console.log(res)
          alert("회원정보가 변경되었습니다.");
        })
        .catch((err) => {
          // console.log(err)
          alert("형식을 확인해주세요");
        });
      },
      // 로그아웃이 제대로 안되는 것 같음 - 확인해보기
      // deleteId () {
      //   this.token = ''
      //   this.$router.push({ name: 'InitPage'})
      //   // this.$refs.observer.reset()
      // },
    }
}
</script>

<style>

</style>
