<template>
  <v-app>
    <v-main>
      <router-view />
      <!-- <InitPage />
      <Mainpage />
      <StoreDetail />
      <StoreManager /> -->
    </v-main>
  </v-app>
</template>

<script>
import Mainpage from './views/Mainpage';
import StoreDetail from './views/StoreDetail';
import InitPage from './views/InitPage';
import StoreManager from './views/StoreManager';

// import StoreInformationModify from '@/components/mypage/StoreInformationModify';
// import UserInformationModify from '@/components/mypage/UserInformationModify';
// import FrequentStore from '@/components/mypage/FrequentStore';
// import OrderList from '@/components/mypage/OrderList';


export default {
  name: 'App',

  components: {
    // Mainpage,
    // StoreDetail,
    // InitPage,
    // StoreManager,

    // StoreInformationModify,
    // UserInformationModify,
    // FrequentStore,
    // OrderList,
    // Mypage,

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
#app{
  font-family: 'Stylish', sans-serif;
  font-family: 'Jua', sans-serif;
  font-family: 'Yeon Sung', cursive;
}
.fixed-bar {
  position: sticky;
  top: 0;
  z-index: 2;
}
</style>
