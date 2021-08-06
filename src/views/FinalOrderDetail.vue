<template>
  <div>
    <Navbar />
    <v-container>
      <OrderDetail />
    </v-container>
  </div>
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
