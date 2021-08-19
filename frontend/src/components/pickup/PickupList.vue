<template>
  <v-app class="color5">
    <v-main>
      <v-container>
        <v-row>
          <v-col
            cols="8"
          >
            <p>
              주문번호 : 123456
            </p>
          </v-col>
        </v-row>
      </v-container>
      <v-form>
        <v-container>
          <v-row>
            <v-col
              cols="3"
            >
              <h3> 민기네 수산시장 </h3>
            </v-col>
            <v-col
              cols="3"
            >
              <span>고등어</span><br>
              <span>갈치</span>
            </v-col>

            <v-col
              cols="3"
            >
              <span>1</span><br>
              <span>2</span>
            </v-col>
            <v-col
              cols="3"
            >
              <v-container>
                <span>21.07.21.12:00</span><br>
                <span>주문완료</span><br>
                <v-btn>픽업완료</v-btn>
              </v-container>
            </v-col>
            <v-container />
            <v-container>
              <v-row>
                <v-col
                  cols="8"
                >
                  <p>
                    주문번호 : 654321
                  </p>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row>
                <v-col
                  cols="3"
                >
                  <h3> 민기네 정육 </h3>
                </v-col>
                <v-col
                  cols="3"
                >
                  <span>삼겹살</span><br>
                  <span>목살</span>
                </v-col>

                <v-col
                  cols="3"
                >
                  <span>1</span><br>
                  <span>2</span>
                </v-col>
                <v-col
                  cols="3"
                >
                  <!-- 그리드 통합으로 조절하는 법 알면 수정해서 다듬기 -->
                  <v-container>
                    <span>21.07.21.12:00</span><br>
                    <span>주문완료</span><br>
                    <v-btn>픽업완료</v-btn>
                  </v-container>
                  <v-container />
                </v-col>
                <v-container />
                <v-container>
                  <v-row>
                    <v-col
                      cols="3"
                    >
                      <h3> 민기네 청과 </h3>
                    </v-col>
                    <v-col
                      cols="3"
                    >
                      <span>사과</span><br>
                      <span>포도</span>
                    </v-col>

                    <v-col
                      cols="3"
                    >
                      <span>1</span><br>
                      <span>2</span>
                    </v-col>
                  </v-row>
                </v-container>
              </v-row>
            </v-container>
          </v-row>
        </v-container>
      </v-form>
    </v-main>
  </v-app>
</template>

<script>
import {mapGetters} from "vuex";
import axios from 'axios'

export default {
  name: 'PickupList',
  data() {
    return{
      name: '',
      comment: '',
    }
  },
    computed:{
      ...mapGetters(["pickup", "getToken"])
    },
    created() {
      this.$store.dispatch("getPickup");
    },
    methods: {
      // submit: function(name,phoneNumber) {
        // 400 error 발생 해결방법
      // storeSearch: function() {
      //   axios({
      //     method:'get',
      //     url:'http://localhost:8081/api/favorite/search',
      //     headers:{
      //       Authorization: `Bearer `+ this.$store.getters.getToken
      //     },
      //     data:{
      //       name: this.name,
      //       comment: this.comment,
      //     }
      //   })
      //   .then((res) => {
      //     // console.log(res)
      //     // alert("회원정보가 변경되었습니다.");[]
      //   })
      //   .catch((err) => {
      //     // console.log(err)
      //     alert("검색한 가게는 단골가게가 아닙니다. 확인해주세요");
      //   });
      // },
      deleteFrequent(index){
        this.$store.dispatch("deleteFrequentStore",`/api/favorite?storeId=${this.$store.getters.frequentStore[index].store.id}`)
      }
    },
}

</script>

<style>

</style>
