<template>
  <v-app class="color5">
    <v-main>
      <div
        v-for="(fs, index) in pickup"
        :key="index"
      >
        <v-container>
          <v-row>
            <v-col
              cols="8"
            >
              주문번호 : {{ fs.id }}
            </v-col>
          </v-row>
        </v-container>
        <v-form>
          <v-container>
            <v-row>
              <v-col
                cols="3"
              >
                <h3> {{ fs.market.name }} </h3>
              </v-col>
              <v-col
                cols="3"
              >
                {{ fs.orderItemList[0].item.name }}
              </v-col>

              <v-col
                cols="3"
              >
                {{ fs.orderItemList[0].amount }}
              </v-col>
              <v-col
                cols="3"
              >
                <v-container>
                  {{ fs.registerTime.substring(0, 10) }}<br>
                  {{ fs.orderStatus.name }}<br>
                  <v-btn
                    @click="statusChange"
                  >
                    {{ fs.orderItemList[0].pickStatus }}
                  </v-btn>
                </v-container>
              </v-col>
              <v-container />
            </v-row>
          </v-container>
        </v-form>
      </div>
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
      statusChange: function() {
      // storeSearch: function() {
        axios({
          method:'patch',
          url:'http://localhost:8082/api/orderinfo/check',
          headers:{
            Authorization: `Bearer `+ this.$store.getters.getToken
          },
          // 이 부분을 해결해야 될 듯?
          data:{
            orderStatusId: this.orderStatusId,
            orderInfoId: this.orderInfoId,
          }
        })
        .then((res) => {
          console.log(res)
          alert("회원정보가 변경되었습니다.");
        }
        // .catch((err) => {
        //   // console.log(err)
        //   alert("검색한 가게는 단골가게가 아닙니다. 확인해주세요");
        // });
        )},
    }
}

</script>

<style>

</style>
