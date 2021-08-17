<template>
  <v-app class="color5">
    <Navbar />
    <v-container>
      <v-row>
        <v-col
          sm="3"
        >
          <StoreMypageSide />
        </v-col>
        <v-col
          sm="9"
        >
          <StoreInformationModify />
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import StoreInformationModify from '@/components/mypage/StoreInformationModify.vue';
import StoreMypageSide from '@/components/mypage/StoreMypageSide.vue';
import Navbar from '@/views/Navbar.vue';

export default {
  name: 'StoreMypage',

  components: {
    StoreInformationModify,
    StoreMypageSide,
    Navbar,
  },
}
</script>

<style>

</style>
