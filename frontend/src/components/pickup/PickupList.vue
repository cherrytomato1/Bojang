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
                <!-- <h3> {{ fs.market.id }} </h3> -->
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


                  <!-- {{ fs.orderStatus.id }} -->
                  <div v-if="fs.orderStatus.id=='1'">
                    <v-btn
                      @click="statusChange(fs.id, fs.orderStatus.id)"
                    >
                      픽업완료
                    </v-btn>
                  </div>

                  <div v-else-if="fs.orderStatus.id=='2'">
                    <v-btn
                      @click="statusChange"
                    >
                      인수완료
                    </v-btn>
                  </div>
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
      // console.log(this.pickup)
    },
    methods: {
      statusChange: function(id,status) {
        // console.log(id);
        // console.log(status)

      // storeSearch: function() {
        axios({
          method:'patch',
          url:'http://localhost:8082/api/orderinfo/check',
          headers:{
            Authorization: `Bearer `+ localStorage.getItem("token")
          },

          data:{
            orderStatusId: status+1,
            orderInfoId: id,
          }
        })
        .then((res) => {
          // console.log(res)
          alert("픽업 정보가 변경되었습니다.");
          // location.reload()
          // token이 날라가는듯(새로고침)
          // this.$router.go()

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
