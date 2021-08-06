<template>
  <v-app class="color5">
    <Navbar />
    <v-container>
      <ShoppingBasket />
      <!-- <OrderPayment />
      <OrderDetail /> -->
    </v-container>
  </v-app>
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
