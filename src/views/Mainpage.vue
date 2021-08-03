<template>
  <v-container>
    <StoreMap />
    <RecommendStore />
  </v-container>
</template>

<style scoped>

</style>

<script>
import StoreMap from '@/components/mainpage/StoreMap';
import RecommendStore from '@/components/mainpage/RecommendStore';

export default {
  name: 'App',

  components: {
    StoreMap,
    RecommendStore,
  },
}
</script>

