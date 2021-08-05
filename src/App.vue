<template>
  <v-app>
    <v-container
      class="fixed-bar"
      pa-0
    >
      <v-app-bar
        color="vll accent-4"
        height="65px"
      >
        <!-- <div> -->
        <!-- 로고 버튼으로 만듬(누르면 홈화면으로 이동하기 위해서) -->
        <button class="pa-6">
          <img
            src="./assets/logo.png"
            style="width: 60px"
          >
        </button>

        <v-toolbar-title>
          <h2>보 장</h2>
        </v-toolbar-title>
        <v-spacer />

        <router-link
          :to="{ name: 'OrderList'}"
          class="pa-5"
        >
          고객님
        </router-link>
        <router-link
          :to="{ name: 'UserInformationModify'}"
          class="pa-5"
        >
          회원정보 테스트
        </router-link>

        <button>
          <router-link
            :to="{ name: 'Login'}"
          >
            <img src="./assets/kakao_login_medium.png">
          </router-link>
        </button>
      </v-app-bar>
      <v-card>
        <v-tabs
          background-color="indigo"
          center-active
        >
          <v-tab>방이시장</v-tab>
          <v-tab>방학동도깨비시장</v-tab>
          <v-tab>별빛남문시장</v-tab>
          <v-tab>봉천제일시장</v-tab>
          <v-tab>민락골목시장</v-tab>
          <v-tab>부천강남시장</v-tab>
          <v-tab>부천신흥시장</v-tab>
          <v-tab>새마을시장</v-tab>
          <v-tab>상도전통시장</v-tab>
          <v-tab>상대원시장</v-tab>
          <v-tab>석촌시장</v-tab>
          <v-tab>성호시장</v-tab>
          <v-tab>속초관광시장</v-tab>
          <v-tab>수유전통시장</v-tab>
          <v-tab>송강시장</v-tab>
          <v-tab>신영시장</v-tab>
          <v-tab>안양중앙시장</v-tab>
          <v-tab>안중시장</v-tab>
          <v-tab>열우물전통시장</v-tab>
          <v-tab>용운시장</v-tab>
        </v-tabs>
      </v-card>
    </v-container>

    <v-main>
      <Mainpage />
      <!-- <SelectStore />
      <StoreLive /> -->

      <!-- <StoreInformationModify />
      <UserInformationModify />
      <FrequentStore />
      <OrderList /> -->

      <StoreDetail />

      <!-- <Pickup /> -->
      <!-- <UserMypage /> -->
      <!-- <ShoppingBasket /> -->
      <!-- <OrderPayment /> -->
      <!-- <OrderDetail /> -->
      <!-- <Payment /> --> -->
      <!-- <router-view /> -->
    </v-main>
    <!-- <StoreMypage /> -->
  </v-app>
</template>

<script>
import Mainpage from './views/Mainpage';

// import StoreInformationModify from '@/components/mypage/StoreInformationModify';
// import UserInformationModify from '@/components/mypage/UserInformationModify';
// import FrequentStore from '@/components/mypage/FrequentStore';
// import OrderList from '@/components/mypage/OrderList';
// import Pickup from '@/components/pickup/Pickup';
// import ShoppingBasket from '@/components/payment/ShoppingBasket';
// import OrderPayment from '@/components/payment/OrderPayment';
// import OrderDetail from '@/components/payment/OrderDetail';
import StoreDetail from './views/StoreDetail';
// import UserMypage from './views/UserMypage';
// import StoreMypage from './views/StoreMypage';
// import Payment from '@/views/Payment.vue';

export default {
  name: 'App',

  components: {
    Mainpage,
    // StoreInformationModify,
    // UserInformationModify,
    // FrequentStore,
    // OrderList,
    // UserMypage,

    StoreDetail,
    // StoreMypage,
    // Payment,
    // ShoppingBasket,
    // OrderPayment,
    // OrderDetail,
    // Pickup,
  },

  data: () => ({
    //
  }),
};
</script>

<style scoped>
.vll {
  background-color: #FF6F61 !important;
}
.color1{
  background-color: #cff0da   !important;
}
.color2{
  background-color: #88dba3   !important;
}
.color3{
  background-color: #dadbdb   !important;
}
.color4{
  background-color: #3ac569 !important;
}
#app{
  font-family: 'Jua', sans-serif;
}
.fixed-bar {
  position: sticky;
  top: 0;
  z-index: 2;
}
</style>
