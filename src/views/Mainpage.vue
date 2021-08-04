<template>
  <v-container>
    <StoreMap />
    <RecommendStore />
    <SelectStore />
  </v-container>
</template>

<style scoped>

</style>

<script>
import StoreMap from '@/components/mainpage/StoreMap';
import RecommendStore from '@/components/mainpage/RecommendStore';
import SelectStore from '@/components/mainpage/SelectStore';

export default {
  components: {
    StoreMap,
    RecommendStore,
    SelectStore,
  },
}
</script>

