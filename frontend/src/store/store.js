import Vue from 'vue';
import Vuex from 'vuex';
import http from "@/util/http-common";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    markets: [], // 전체 마켓들
    market: [], // 선택된 마켓(id,name)
    stores: [], // (마켓, 카테고리 내)전체 가게들
    store: [], // 마켓에서 선택된 가게

  },
  getters: {
    markets(state){
      return state.markets;
    },
    market(state){
      return state.market;
    },
    stores(state){
      return state.stores;
    },
    store(state){
      return state.store;
    }
  },
  mutations: { // state 값 변경하는 함수.
    setMarkets(state, payload) {
      state.markets = payload;
    },
    setMarket(state, payload){ // 선택된 market명으로 변경
      state.market = payload;
    },
    setStores(state, payload) {
      state.stores = payload;
    },
    setStore(state, payload){
      state.store = payload;
    }
  },
  actions: { // 비동기 처리
    getMarkets(context) {
      http
        .get("/api/market")
        .then(( data ) => {
          context.commit("setMarkets", data.data.marketList);
        })
        .catch(() => {
          alert("getMarkets 오류 발생");
        });
    },
    getStores(context,payload) { // ​/api​/store​/category 해당 마켓의 카테고리별 가게 리스트
      http
        .get(payload)
        .then(( data ) => {
          context.commit("setStores", data.data.storeList);
        })
        .catch(() => {
          alert("getStores 오류 발생");
        });
    },
    getStore(context,payload) { // ​​/api​/store​/{storeId} 해당 ID의 가게 정보
      http
        .get(payload)
        .then(( data ) => {
          context.commit("setStore", data.data.store);
        })
        .catch(() => {
          alert("getStore 오류 발생");
        });
    },
  },
});
