<template>
  <v-container>
    <v-row>
      <v-col
        sm="2"
      >
        <StoreSide />
      </v-col>
      <v-col
        sm="5"
      >
        <StoreLive />
      </v-col>
      <v-col
        sm="5"
      >
        <StoreProducts />
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>

<script>
import StoreSide from '@/components/storeDetail/StoreSide';
import StoreLive from '@/components/storeDetail/StoreLive';
import StoreProducts from '@/components/storeDetail/StoreProducts';

export default {
  components: {
    StoreSide,
    StoreLive,
    StoreProducts,
  },
}
</script>


