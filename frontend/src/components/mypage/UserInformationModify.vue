<template>
  <v-app class="color5">
    <v-container>
      <h1>
        회원 정보 수정
      </h1>
      <br>
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
        <v-row>
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
            offset="5"
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
  </v-app>
</template>

<script>
// import {mapGetters} from "vuex";
import axios from 'axios'


export default {
  name: 'UserInformationModify',
  data: () => ({
      valid: true,
      name: '',
      nameRules: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 10) || '이름은 10자 이내로 작성해주세요',
      ],
      phoneNumber: '',
      phoneNumberRules: [
        v => !!v || 'phoneNumber is required',
        v => /.+@.+\..+/.test(v) || '예시, 010-1234-5678 처럼 입력해주세요',
      ]
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
            Authorization: `Bearer `+ this.$store.getters.getToken
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

    //   clickHandler: function(id,num) {
    //   axios({
    //     method:'put',
    //     url:'http://localhost:8082/api/basket',
    //     headers:{
    //       Authorization: `Bearer `+ this.$store.getters.getToken
    //     },
    //     data:{
    //       amount: num,
    //       itemId: id,
    //     }
    //   })
    //   .then(() => {
    //     alert("장바구니에 상품을 넣었습니다.");
    //   })
    //   .catch(() => {
    //     alert("장바구니에 해당 상품이 이미 있습니다.");
    //   });
    // },
    // 이 부분은 이제 적용되는듯 - token이 사라지게 안되네..


      // deleteId () {
      //   this.token = ''
      //   this.$router.push({ name: 'InitPage'})
      //   // this.$refs.observer.reset()
      // },
    //   deleteId: function () {
    //   axios({
    //     method: 'delete',
    //     url: `http://localhost:8085/api/user`,
    //     // url: `http://localhost:8085/api/user/${this.user.id}/`,
    //     // headers: this.setToken()
    //   })
    //     .then((res) => {
    //       // console.log(res)
    //       this.$router.push({ name: 'InitPage'})
    //     })
    //     // .catch((err) => {
    //     //   console.log(err)
    //     // })
    // },
    }
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
