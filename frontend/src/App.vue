<template>
  <div id="app">
    <v-app-bar
      color="vll accent-4"
      height="65"
    >
      <!-- 로고 버튼으로 만듬(누르면 홈화면으로 이동하기 위해서) -->
      <button class="pa-6">
        <img src="./assets/logo.png"
             style="width: 60px">
      </button>

      <v-toolbar-title>
        <h2>보 장</h2>
      </v-toolbar-title>
      <v-spacer />
      <button>
        <img src="./assets/kakao_login_medium.png">
      </button>
    </v-app-bar>
    <!-- 구매자 일때 -->
    <span v-if="customer">
      <ul class="navbar-nav">
        <li class="nav-item">
          <router-link
            :to="{ name: '#' }"
            class="text-light nav-link"
          >{{ customer_name }}님</router-link>
        </li>
        <li class="nav-item">
          <router-link
            :to="{ name: '#' }"
            class="text-light nav-link"
          >단골가게</router-link>
        </li>
        <li class="nav-item">
          <router-link
            :to="{ name: '#' }"
            class="text-light nav-link"
          >장바구니</router-link>
        </li>
        <li class="nav-item">
          <router-link
            to="#"
            class="text-light nav-link"
            text-decoration:
            none
            @click.native="logout"
          >로그아웃</router-link>
        </li>
      </ul>
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
    </span>
    <!-- 판매자 일 때  -->
    <span v-if="store">
      <ul class="navbar-nav gap-3">
        <li class="nav-item">
          <a
            class="text-light nav-link"
            @click="handleClickButton"
          >{{ store_name }}님</a>
          <app-my-modal
            :visible.sync="visible"
          />
        </li>
        <li class="nav-item">
          <router-link
            to="#"
            class="text-light nav-link"
            text-decoration:
            none
            @click.native="logout"
          >로그아웃</router-link>
        </li>
      </ul>
    </span>

    <!-- 픽업매니저 일 때  -->
    <span v-if="pickup">
      <ul class="navbar-nav gap-3">
        <li class="nav-item">
          <a
            class="text-light nav-link"
            @click="handleClickButton"
          >{{ pickup_name }}님</a>
          <app-my-modal
            :visible.sync="visible"
          />
        </li>
        <li class="nav-item">
          <router-link
            to="#"
            class="text-light nav-link"
            text-decoration:
            none
            @click.native="logout"
          >로그아웃</router-link>
        </li>
      </ul>
    </span>
      <!-- 발표를 위해 보여주는 코드 발표 후 삭제 (로그인 구현이 안되서)-->
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
    <!-- 메인페이지 container 잡고 가운데 정렬 -->
    <v-container>
      <v-layout>
        <v-flex justify-center>
          <StoreMap />
          <RecommendSection />
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

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

<script>
import StoreMap from './components/mainpage/StoreMap';
import RecommendSection from './components/mainpage/RecommendSection';



export default {
  name: 'App',

  components: {
    StoreMap,
    RecommendSection,
  },

  data: () => ({
    //
  }),
};
</script>
