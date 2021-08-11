import Vue from 'vue';
import Vuex from 'vuex';
import http from "@/util/http-common";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    markets: [], // 전체 마켓
    market: '' // 선택된 마켓
  },
  getters: {
    markets(state){
      return state.markets;
    },
    market(state){
      return state.market;
    }
  },
  mutations: { // state 값 변경하는 함수.
    setMarkets(state, payload) {
      state.markets = payload;
    },
  },
  actions: { // 비동기 처리
    getMarkets(context) {
      http
        .get("/api/market")
        .then(( data ) => {
          console.log(data.data.marketList)
          context.commit("setMarkets", data.data.marketList);
        })
        .catch(() => {
          alert("수행 중 오류가 발생했습니다.");
        });
    },
  },
});
