<template>
  <v-app>
    <v-container pa-0>
      <v-app-bar
        color="vll accent-4"
        height="65px"
      >
        <!-- 로고 버튼으로 만듬(누르면 홈화면으로 이동하기 위해서) -->
        <button class="pa-6">
          <img
            src="./assets/logo.png"
            style="width: 60px"
          >
        </button>

        <v-toolbar-title>
          <h2>보 장</h2>
        </v-toolbar-title>
        <v-spacer />

        <!-- <v-btn
        text
        @click="$router.push({name:'OrderList'})" -->
        <!-- 라우팅 방법들 -->
        <router-link
          :to="{ name: 'OrderList'}"
          class="pa-5"
        >
          고객님
        </router-link>
        <router-link
          :to="{ name: 'UserInformationModify'}"
          class="pa-5"
        >
          회원정보 테스트
        </router-link>
        <!-- <router :to="{ name: 'OrderList'}" exact> -->

        <!-- </v-btn> -->

        <button>
          <router-link
            :to="{ name: 'Login'}"
          >
            <img src="./assets/kakao_login_medium.png">
          </router-link>
        </button>
      </v-app-bar>
      <v-card>
        <v-tabs
          background-color="indigo"
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
      <!-- <v-app>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        />
      </div>

      <v-spacer />

      <v-btn
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
        text
      >
        <span class="mr-2">Latest Release</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar> -->

      <v-main>
        <StoreMap />
        <RecommendSection />
      </v-main>
      <router-view />
    </v-container>
  </v-app>
</template>

<script>
import StoreMap from './components/mainpage/StoreMap';
import RecommendSection from './components/mainpage/RecommendSection';
// import OrderList from '@/components/mypage/OrderList';

export default {
  name: 'App',

  components: {
    StoreMap,
    RecommendSection,
    // OrderList,
    // Mypage,
  },

  data: () => ({
    //
  }),
};
</script>

<style scoped>
.vll {
  background-color: #FF6F61 !important;
}
.color1{
  background-color: #cff0da   !important;
}
.color2{
  background-color: #88dba3   !important;
}
.color3{
  background-color: #dadbdb   !important;
}
.color4{
  background-color: #3ac569 !important;
}
#app{
  font-family: 'Jua', sans-serif;
}

</style>
