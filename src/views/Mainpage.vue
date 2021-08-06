<template>
  <div class="color1">
    <Navbar />
    <StoreMap />
    <!-- <RecommendStore /> -->
    <SelectStore />
  </div>
</template>

<style scoped>

</style>

<script>
import Navbar from '@/views/Navbar';
import StoreMap from '@/components/mainpage/StoreMap';
import RecommendStore from '@/components/mainpage/RecommendStore';
import SelectStore from '@/components/mainpage/SelectStore';

export default {
  components: {
    Navbar,
    StoreMap,
    // RecommendStore,
    SelectStore,
  },
}
</script>

