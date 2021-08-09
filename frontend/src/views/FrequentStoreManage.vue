<template>
  <v-app class="color5">
    <Navbar />
    <v-container>
      <v-row>
        <v-col
          sm="2"
        >
          <MypageSide />
        </v-col>
        <v-col
          sm="10"
        >
          <FrequentStore />
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<style scoped>

</style>

<script>
import MypageSide from '@/components/mypage/MypageSide';
import FrequentStore from '@/components/mypage/FrequentStore';
import Navbar from '@/views/Navbar';

export default {
  name: 'FrequentStoreManage',
  components: {
    MypageSide,
    FrequentStore,
    Navbar,
  },
}
</script>
