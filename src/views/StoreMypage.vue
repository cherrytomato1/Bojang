<template>
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
</template>

<script>
import StoreInformationModify from '@/components/mypage/StoreInformationModify.vue';
import StoreMypageSide from '@/components/mypage/StoreMypageSide.vue';


export default {
  name: 'UserMypage',

  components: {
    StoreInformationModify,
    StoreMypageSide,
  },
}
</script>

<style>

</style>
