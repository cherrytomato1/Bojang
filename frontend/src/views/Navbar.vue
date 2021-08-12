<template>
  <div
    class="fixed-bar"
    pa-0
  >
    <v-app-bar
      color="color2 accent-4"
      height="65px"
    >
      <button
        class="pa-6"
      >
        <router-link
          :to="{ name: 'Mainpage'}"
        >
          <img
            src="@/assets/logo.png"
            style="width: 60px"
          >
        </router-link>
      </button>
      <div v-if="customer">
        <!-- 구매자 일때 -->
        <v-toolbar-title>
          <h2>보 장</h2>
        </v-toolbar-title>
      </div>
      <div v-else-if="seller">
        <!-- 판매자 일때 -->
        <v-toolbar-title>
          <router-link
            :to="{ name: 'StoreMypage'}"
            class="pa-5"
          >
            스토어 관리
          </router-link>
        </v-toolbar-title>
      </div>
      <!-- 픽업매니저 일때 -->
      <div v-else-if="pickup">
        <v-toolbar-title>
          <router-link
            :to="{ name: 'Pickup'}"
            class="pa-5"
          >
            픽업 관리
          </router-link>
        </v-toolbar-title>
      </div>
      <v-spacer />
      <router-link
        :to="{ name: 'OrderCheck'}"
        class="pa-5"
      >
        <!-- 백엔드에서 username 받아오기 -->
        마이페이지
      </router-link>
      <router-link
        :to="{ name: 'FrequentStoreManage'}"
        class="pa-5"
      >
        단골가게
      </router-link>
      <router-link
        :to="{ name: 'Basket'}"
        class="pa-5"
      >
        장바구니
      </router-link>
      <router-link
        :to="{ name: 'InitPage'}"
        class="pa-5"
      >
        로그아웃
      </router-link>
    </v-app-bar>
    <v-card>
      <v-tabs
        v-model="tab"
        background-color="color1"
        center-active
      >
        <v-tab
          v-for="(market, idx) in markets"
          :key="idx"
        >
          {{ market.name }}
        </v-tab>
      </v-tabs>
    </v-card>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: 'Navbar',
  data() {
    return{
      tab: null,
    }
  },

  computed:{
    ...mapGetters(["markets"])
  },
  watch:{
    tab: function (val){ // 선택한 탭 변경될 경우
      this.$store.commit("setMarket",this.$store.getters.markets[val]);
      // console.log(this.$store.getters.market);
    }
  },
  created() {
     this.$store.dispatch("getMarkets");
  }

};
</script>

<style>
.fixed-bar {
  position: sticky;
  top: 0;
  z-index: 2;
}
</style>

<script>
export default {
  name: 'Navbar',
  data: () => ({
    customer: false,
    seller: false,
    pickup: false,
  })
}
</script>
