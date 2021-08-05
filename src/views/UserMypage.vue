<template>
  <v-container>
    <v-row>
      <v-col
        sm="3"
      >
        <MypageSide />
      </v-col>
      <v-col
        sm="9"
      >
        <OrderList />

        <!-- if문 다시 도전 -->
        <!-- <div v-if="UserInformationModify">
          <UserInformationModify />
        </div>
        <div v-if="OrderList">
          <OrderList />
        </div>
        <div v-if="FrequentStore">
          <FrequentStore />
        </div> -->
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import OrderList from '@/components/mypage/OrderList.vue';
// import FrequentStore from '@/components/mypage/FrequentStore.vue';
// import UserInformationModify from '@/components/mypage/UserInformationModify.vue';
import MypageSide from '@/components/mypage/MypageSide.vue';

export default {
  name: 'UserMypage',

  components: {
    OrderList,
    MypageSide,
    // FrequentStore,
    // UserInformationModify,
  },
}
</script>

<style>

</style>
