
<template>
  <v-container>
    <v-row>
      <h1>나의 상점</h1>
      <v-spacer />
      <v-btn
        large
        @click="moveHandler($store.getters.myStore.id)"
      >
        <h3>상점 개설</h3>
      </v-btn>
    </v-row>
    <v-row>
      <v-tabs
        fixed-tabs
        class="color5"
      >
        <v-tab @click="change(1)">
          가게 관리
        </v-tab>
        <v-tab @click="change(2)">
          주문 관리
        </v-tab>
        <v-tab @click="change(3)">
          판매 금액
        </v-tab>
      </v-tabs>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'StoreBar',
  data: () => ({
    tab: 1,
  }),
  methods:{
    change(no){
      this.$emit('change',no);
    },
    moveHandler: function(storeId) {
      this.$router.push(`/livemanager?storeId=${storeId}`);
    }
  }
}
</script>
