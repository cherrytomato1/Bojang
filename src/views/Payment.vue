<template>
  <div class="color5">
    <Navbar />
    <v-container>
      <OrderPayment />
    </v-container>
  </div>
</template>

<script>
import Navbar from '@/views/Navbar';
import OrderPayment from '@/components/payment/OrderPayment.vue';

export default {
  name: 'Payment',

  components: {
    OrderPayment,
    Navbar,
  },
}
</script>

<style>

</style>
