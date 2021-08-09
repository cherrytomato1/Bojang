<template>
  <v-container>
    <StoreBar />
    <StoreManagerView />
  </v-container>
</template>

<style scoped>

</style>

<script>
import StoreBar from '@/components/storeManager/StoreBar';
import StoreManagerView from '@/components/storeManager/StoreManagerView';

export default {
  components: {
    StoreBar,
    StoreManagerView,
  },
}
</script>

