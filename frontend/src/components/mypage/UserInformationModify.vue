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
// import {mapGetters} from "vuex";

export default {
  name: 'UserInformationModify',
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
        v => /.+@.+\..+/.test(v) || 'phoneNumber must be valid',
      ]
    }),
  // computed:{
  // //   ...mapGetters([""])
  // // },
  // // // watch:{
  // // //   tab: function (val){ // 선택한 탭 변경될 경우
  // // //     this.$store.commit("setFrequentStore",this.$store.getters.frequentStore[val]);
  // // //     // console.log(this.$store.getters.market);
  // // //   }
  // // // },
  // // created() {
  // //   this.$store.dispatch("");
  // //   // this.$store.dispatch("");
  // // }
  // },

    methods: {
      // validate를 바꿔도 됨
      submit () {
        this.$refs.observer.validate()
      },
      clear () {
        this.name = ''
        this.phoneNumber = ''
        // this.$refs.observer.reset()
      },
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
