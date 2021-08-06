<template>
  <div class="color1">
    <Navbar />
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
  </div>
</template>

<style scoped>

</style>

<script>
import Navbar from '@/views/Navbar';
import StoreSide from '@/components/storeDetail/StoreSide';
import StoreLive from '@/components/storeDetail/StoreLive';
import StoreProducts from '@/components/storeDetail/StoreProducts';

export default {
  components: {
    Navbar,
    StoreSide,
    StoreLive,
    StoreProducts,
  },
}
</script>


