<template>
  <v-app class="color5">
    <Navbar :type="userData.userType" />
    <StoreMap />
    <!-- <RecommendStore /> -->

    <!-- 가게가 선택되었을 때 표시하기 위함 -->
    <SelectStore v-if="$store.getters.store.id!=undefined" />
  </v-app>
</template>

<style scoped>

</style>

<script>
import Navbar from '@/views/Navbar';
import StoreMap from '@/components/mainpage/StoreMap';
import RecommendStore from '@/components/mainpage/RecommendStore';
import SelectStore from '@/components/mainpage/SelectStore';
import {mapGetters} from "vuex";

export default {
  components: {
    Navbar,
    StoreMap,
    // RecommendStore,
    SelectStore,
  },
//  created() {
//     this.$store.dispatch("getUserData");
//     // console.log(this.userData)
//   },
  computed:{
  ...mapGetters(["userData"])
  },
}

</script>

