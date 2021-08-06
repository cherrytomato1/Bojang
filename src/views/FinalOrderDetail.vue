<template>
  <v-app class="color5">
    <Navbar />
    <v-container>
      <OrderDetail />
    </v-container>
  </v-app>
</template>

<script>
import Navbar from '@/views/Navbar';

import OrderDetail from '@/components/payment/OrderDetail.vue';

export default {
  name: 'FinalOrderDetail',

  components: {
    OrderDetail,
    Navbar,
  },
}
</script>

<style>

</style>
