<template>
  <v-container>
    <ShoppingBasket />
    <OrderPayment />
    <OrderDetail />
  </v-container>
</template>

<script>
import ShoppingBasket from '@/components/payment/ShoppingBasket.vue';
import OrderPayment from '@/components/payment/OrderPayment.vue';
import OrderDetail from '@/components/payment/OrderDetail.vue';

export default {
  name: 'Payment',

  components: {
    ShoppingBasket,
    OrderPayment,
    OrderDetail,
  },
}
</script>

<style>

</style>
