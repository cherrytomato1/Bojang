<template>
  <div>
    <Navbar />
    <v-container>
      <ShoppingBasket />
      <!-- <OrderPayment />
      <OrderDetail /> -->
    </v-container>
  </div>
</template>

<script>
import Navbar from '@/views/Navbar';
import ShoppingBasket from '@/components/payment/ShoppingBasket.vue';
// import OrderPayment from '@/components/payment/OrderPayment.vue';
// import OrderDetail from '@/components/payment/OrderDetail.vue';

export default {
  name: 'Basket',

  components: {
    ShoppingBasket,
    // OrderPayment,
    // OrderDetail,
    Navbar,
  },
}
</script>

<style>

</style>
