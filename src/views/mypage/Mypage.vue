<template>
  <v-container>
    <OrderList />
  </v-container>
</template>

<script>
import OrderList from '@/components/mypage/OrderList.vue';

export default {
  name: 'Mypage',

  components: {
    OrderList,
  },
}
</script>

<style>

</style>
