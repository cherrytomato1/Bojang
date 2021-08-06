<template>
  <div class="color1">
    <Navbar />
    <v-container>
      <v-row>
        <v-col
          cols="2"
        >
          <MypageSide />
        </v-col>
        <v-col
          cols="10"
          pa="5"
        >
          <OrderList />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style scoped>

</style>

<script>
import MypageSide from '@/components/mypage/MypageSide';
import OrderList from '@/components/mypage/OrderList';
import Navbar from '@/views/Navbar';

export default {
  name: 'OrderCheck',
  components: {
    MypageSide,
    OrderList,
    Navbar,
  },
}
</script>
