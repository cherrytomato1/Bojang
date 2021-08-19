<template>
  <v-app class="pa-2 color5">
    <v-container>
      <v-row>
        <v-col cols="8">
          <h2>
            주문 내역
          </h2>
        </v-col>
        <!-- <v-col
          cols="4"
        >
          <v-text-field
            v-col
            class="col-4"
            flat
            hide-details
            label="Search"
            prepend-inner-icon="mdi-magnify"
            solo-inverted
          />
        </v-col> -->
      </v-row>
      <v-col cols="12" class="py-2">
        <v-btn-toggle v-model="tab" tile color="deep-purple accent-3" group>
          <v-btn v-for="tabItem in tabs" :key="tabItem">
            {{ tabItem }}
          </v-btn>
        </v-btn-toggle>
      </v-col>
    </v-container>
    <!-- outlined 적용이 잘 안되는데 다시 적용해보기 -->
    <v-container v-for="order in $store.getters.orderList" :key="order.id">
      <v-row>
        <v-col cols="4">
          <!-- 주문 날짜 불러오기 -->
          <p>
            {{ order.registerTime.split(" ")[0] }}
          </p>
        </v-col>
        <v-col cols="2">
          <v-row row="2">
            <p>{{ order.market.name }}</p>
          </v-row>
        </v-col>

        <v-col cols="3">
          <p>{{ order.price }} 원</p>
        </v-col>
        <v-col cols="3">
          <p>
            {{ order.orderStatus.name }}
          </p>
        </v-col>
        <!-- <v-col
          cols="2"
        >
          <v-btn
            color="warning"
          >
            결제 취소
          </v-btn>
        </v-col> -->
      </v-row>
      <div v-for="orderItem in order.orderItemList" :key="orderItem.id">
        <v-form>
          <v-container>
            <v-row>
              <v-col cols="2">
                <img
                  v-bind:src="orderItem.item.image"
                  alt="상품 사진"
                  style="width:60px"
                />
              </v-col>

              <v-col cols="4">
                <!-- <p>
                민기네 수산<br>
                고등어<br>
                수량: 13 <br>
              </p> -->
                <!-- {{ fs.item.name }}<br /> -->
              </v-col>
              <v-col cols="2">
                <p>
                  {{ orderItem.item.price * orderItem.amount }}<br />
                  {{ order.registerTime.split(" ")[0] }}<br />
                </p>
              </v-col>
              <v-col cols="4">
                {{ orderItem.comment }}
                <!-- <p>요청사항 : 고등어 손질 부탁드려요!<br /></p> -->
              </v-col>
            </v-row>
          </v-container>
        </v-form>
      </div>
      <v-row>
        <v-col cols="12" offset="9">
          <br />
          <br />
          <v-btn color="success" v-bind:to="orderDetailUrl + order.id">
            주문 상세보기
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import { mapGetters } from "vuex";

// script vuetify에서 가져와야됨
export default {
  name: "OrderList",
  data() {
    return {
      // 변경하기
      tab: null,
      tabs: ["최근 6개월", "2021", "2020", "2019", "2018"],
      orderDetailUrl: "/find-order-detail/"
    };
  },

  computed: {
    ...mapGetters(["orderList", "getToken"])
  },
  watch: {
    tab: function(val) {
      // 선택한 탭 변경될 경우
      console.log("tab >>>>> " + val);
      // this.$store.commit("setOrderList", this.$store.getters.orderList[val]);
      this.$store.dispatch("getOrderList", this.tabs[val]);
      // console.log(this.$store.getters.OrderList);
    }
  },
  created() {
    this.$store.dispatch("getOrderList", this.tabs[0]);
  }
};
</script>

<style>
.color1 {
  background-color: #cff0da !important;
}
.color2 {
  background-color: #88dba3 !important;
}
.color3 {
  background-color: #dadbdb !important;
}
.color4 {
  background-color: #3ac569 !important;
}
#app {
  font-family: "Stylish", sans-serif;
  font-family: "Jua", sans-serif;
  font-family: "Yeon Sung", cursive;
}
</style>
