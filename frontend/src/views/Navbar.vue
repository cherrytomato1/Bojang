<template>
  <div
    class="fixed-bar"
    pa-0
  >
    <!-- v-if="customer" -->
    <!-- v-if="userType==='1'" / == 안나옴, = 1개는 나옴 -->
    <!-- <div v-if="$store.getters.userData.userType=='1'"> -->
    <div>
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
        <v-toolbar-title>
          <h2>보 장</h2>
        </v-toolbar-title>
        <v-spacer />
        <router-link
          :to="{ name: 'OrderCheck'}"
          class="pa-5"
        >
          {{ $store.getters.userData }}
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
    <!-- v-if="seller" -->
    <div>
      <!-- <div v-if="userType==='2'"> -->
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
        <v-toolbar-title>
          <router-link
            :to="{ name: 'StoreManager'}"
            class="pa-5"
          >
            스토어 관리
          </router-link>
        </v-toolbar-title>
        <v-spacer />
        <!-- 판매자 마이페이지로? -->
        <router-link
          :to="{ name: 'StoreMypage'}"
          class="pa-5"
        >
          마이페이지
        </router-link>

        <router-link
          :to="{ name: 'InitPage'}"
          class="pa-5"
        >
          로그아웃
        </router-link>
      </v-app-bar>
    </div>
    <!-- pickup -->
    <div>
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
        <v-toolbar-title>
          <router-link
            :to="{ name: 'Pickup'}"
            class="pa-5"
          >
            픽업 관리
          </router-link>
        </v-toolbar-title>
        <v-spacer />
        <router-link
          :to="{ name: 'InitPage'}"
          class="pa-5"
        >
          로그아웃
        </router-link>
      </v-app-bar>
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: 'Navbar',
  data() {
    return{
      userType: '',
      tab: null,
    }
  },
  //   methods: {
  //     axios({
  //       method: 'get',
  //       url: `http://localhost:8085/api/user`,
  //       headers: headers,
  //       data : {userType : userTypeLongValue, name : this.name, phoneNumber: this.phone}
  //       })
  //   },
  // };
  computed:{
    ...mapGetters(["markets", "userData"])
  },
  watch:{
    tab: function (val){ // 선택한 탭 변경될 경우
      this.$store.commit("setMarket",this.$store.getters.markets[val]);
      // console.log(this.$store.getters.market);
    }
  },
  created() {
    this.$store.dispatch("getMarkets");
    // console.log(this.userData)
  }
}
</script>

<style>
.fixed-bar {
  position: sticky;
  top: 0;
  z-index: 2;
}
</style>
