<template>
  <div
    class="fixed-bar"
    pa-0
  >
    <v-app-bar
      color="color2 accent-4"
      height="65px"
    >
      <button
        class="pa-6"
      >
        <router-link
          :to="{ name: 'Mainpage'}"
        >
          <img
            src="@/assets/logo.png"
            style="width: 60px"
          >
        </router-link>
      </button>
      <v-toolbar-title>
        <h2>보 장</h2>
      </v-toolbar-title>
      <v-spacer />
      <router-link
        :to="{ name: 'OrderCheck'}"
        class="pa-5"
      >
        마이페이지
      </router-link>
      <router-link
        :to="{ name: 'FrequentStoreManage'}"
        class="pa-5"
      >
        단골가게
      </router-link>
      <router-link
        :to="{ name: 'Basket'}"
        class="pa-5"
      >
        장바구니
      </router-link>
      <router-link
        :to="{ name: 'InitPage'}"
        class="pa-5"
      >
        로그아웃
      </router-link>
    </v-app-bar>
    <v-card>
      <v-tabs
        background-color="color1"
        center-active
      >
        <v-tab>방이시장</v-tab>
        <v-tab>방학동도깨비시장</v-tab>
        <v-tab>별빛남문시장</v-tab>
        <v-tab>봉천제일시장</v-tab>
        <v-tab>민락골목시장</v-tab>
        <v-tab>부천강남시장</v-tab>
        <v-tab>부천신흥시장</v-tab>
        <v-tab>새마을시장</v-tab>
        <v-tab>상도전통시장</v-tab>
        <v-tab>상대원시장</v-tab>
        <v-tab>석촌시장</v-tab>
        <v-tab>성호시장</v-tab>
        <v-tab>속초관광시장</v-tab>
        <v-tab>수유전통시장</v-tab>
        <v-tab>송강시장</v-tab>
        <v-tab>신영시장</v-tab>
        <v-tab>안양중앙시장</v-tab>
        <v-tab>안중시장</v-tab>
        <v-tab>열우물전통시장</v-tab>
        <v-tab>용운시장</v-tab>
      </v-tabs>
    </v-card>
  </div>
</template>

<style>
.fixed-bar {
  position: sticky;
  top: 0;
  z-index: 2;
}
</style>
