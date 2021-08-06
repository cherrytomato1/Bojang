<template>
  <v-app class="color5">
    <Navbar />
    <v-container>
      <v-row>
        <v-col
          sm="2"
        >
          <MypageSide />
        </v-col>
        <v-col
          sm="10"
        >
          <UserInformationModify />
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
// import OrderList from '@/components/mypage/OrderList.vue';
// import FrequentStore from '@/components/mypage/FrequentStore.vue';
import UserInformationModify from '@/components/mypage/UserInformationModify.vue';
import MypageSide from '@/components/mypage/MypageSide.vue';
import Navbar from '@/views/Navbar.vue';

export default {
  name: 'UserMypage',

  components: {
    // OrderList,
    MypageSide,
    Navbar,
    // FrequentStore,
    UserInformationModify,
  },
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
