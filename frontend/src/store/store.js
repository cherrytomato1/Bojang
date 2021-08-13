import Vue from 'vue';
import Vuex from 'vuex';
import http from "@/util/http-common";
import { TOKEN } from './mutation-types';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    markets: [], // 전체 마켓들
    market: [], // 선택된 마켓(id,name)
    token: '',
    isLogin: false,
    frequentStore: [], // 전체 마켓들
  },
  getters: {
    markets(state){
      return state.markets;
    },
    market(state){
      return state.market;
    },
    frequentStore(state){
      return state.frequentStore;
    },
    getToken: (state) => {
      return state.token;
    },
    getIsLogin: (state) => {
        return state.isLogin;
    }
  },
  mutations: { // state 값 변경하는 함수.
    setMarkets(state, payload) {
      state.markets = payload;
    },
    setMarket(state, payload){ // 선택된 market명으로 변경
      state.market = payload;
    },
    setFrequentStore(state, payload){ // 선택된 market명으로 변경
      state.frequentStore = payload;
    },
    setToken(state, payload){
      state.token = payload;
    },
    setIsLogin(state){
      if (localStorage.getItem("token")) {
        state.isLogin = true;
    } else {
        state.isLogin = false;
    }
    },
  },
  actions: { // 비동기 처리
    getMarkets(context) {
      http
        .get("/api/market")
        .then(( data ) => {
          context.commit("setMarkets", data.data.marketList);
        })
        .catch(() => {
          alert("수행 중 오류가 발생했습니다.");
        });
    },
    getFrequentStore(context) {
      http
        .get("/api/favorite", {
          // header token 팁
          headers: {
            // Authorization: `Bearer `+ this.state.token
            Authorization: `Bearer `+ this.getters.getToken
          }})
        .then(( data ) => {
          context.commit("setFrequentStore", data.data.favoriteStoreList);
        })
        .catch(() => {
          alert("수행 중 오류가 발생했습니다.");
        });
    },
    getToken({ commit }, token) {
      commit("setToken", token);
      // token 출력 됨
      // console.log(token)
    },
    getIsLogin({ commit }, isLogin) {
      commit("setIsLogin", isLogin);
      // console.log(isLogin)
    }
  },
});
