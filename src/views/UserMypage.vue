<template>
  <div>
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
  </div>
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

</style>
