<template>
  <v-app class="color5">
    <Navbar />
    <StoreMap />
    <!-- <RecommendStore /> -->

    <!-- 가게가 선택되었을 때 표시하기 위함 -->
    <SelectStore v-if="!$store.getters.store" />
  </v-app>
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

