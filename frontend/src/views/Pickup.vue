<template>
  <div class="color5">
    <Navbar />
    <v-container>
      <PickupList />
    </v-container>
  </div>
</template>

<script>
import Navbar from '@/views/Navbar';
import PickupList from '@/components/pickup/PickupList.vue';

export default {
  name: 'Pickup',

  components: {
    PickupList,
    Navbar,
  },
}
</script>

<style>

</style>
