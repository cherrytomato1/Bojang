import Vue from 'vue';
import Vuex from 'vuex';
import http from "@/util/http-common";
import http1 from "@/util/http1-item";
import { TOKEN } from './mutation-types';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    markets: [], // 전체 마켓들
    market: [], // 선택된 마켓(id,name)
    stores: [], // (마켓, 카테고리 내)전체 가게들
    store: [], // 마켓에서 선택된 가게
    myStore: [], // 나의 가게정보 (상점 정보수정 시 필요)

    // 정준님 토큰
    // token: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODM2OTU3MjgxIiwianRpIjoiUzVjMzY3NTA2MmE0NCIsImlhdCI6MTYyOTA5MjUyOSwiZXhwIjoxNjI5MTUzMDA5fQ.rVoMikSnzm7gLq-0QTZqBcFH_2C1ZdSkmvCEsENkcWWMIIttvKFYuFDvaTpiDlNPhhuCUqB7kuxDhBHp3Tt54A`

    // 태욱 토큰
    token: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODQyNzQzNzQxIiwianRpIjoiUzBmZWIwMTdkMWQxNCIsImlhdCI6MTYyOTEyNzk4MiwiZXhwIjoxNjI5MTg4NDYyfQ.oziEyg3RfIfXSNWNxpz_Q6O82Tfa-X4cfxTxeFz3eiMjvsRFRooeT6y0Yz03qOGeEfy6c-7dj24StTASQk8VDw',
    isLogin: false,
    frequentStore: [], // 단골가게
    orderList: [], // 주문내역
    basketList: [],
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
    },
    myStore(state){
      return state.myStore;
    },


    frequentStore(state){
      return state.frequentStore;
    },
    getToken: (state) => {
      return state.token;
    },
    getIsLogin: (state) => {
        return state.isLogin;
    },
    orderList(state){
      return state.orderList;
    },
    basketList(state){
      return state.basketList;
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
      // section 번호순으로 정렬
      payload.sort((a,b) =>{
        let compare = 0;
        if (a['section'] > b['section']) {
          compare = 1;
        } else if (b['section'] > a['section']) {
          compare = -1;
        }
        return compare;
      });
      state.stores = payload;
    },
    setStore(state, payload){
      state.store = payload;
    },
    setMyStore(state, payload){
      state.myStore = payload;
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

    setOrderList(state, payload){
      state.orderList = payload;
    },
    setBasketList(state, payload){
      state.basketList = payload;
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
    getMyStore(context){
      http
        .get("/api/store", {
          headers: {
            // Authorization: `Bearer `+ this.state.token
            Authorization: `Bearer `+ this.getters.getToken
          }
        })
        .then(( data ) => {
          context.commit("setMyStore", data.data.store);
        })
        .catch(() => {
          alert("getMyStore 오류 발생");
        });
    },






    getFrequentStore(context) {
      http
        .get("/api/favorite", {
          // header token 팁
          headers: {
            Authorization: `Bearer `+ this.state.token
            // Authorization: `Bearer `+ this.getters.getToken
            // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODQyNzQzNzQxIiwianRpIjoiUzBmZWIwMTdkMWQxNCIsImlhdCI6MTYyOTAzNzIyMSwiZXhwIjoxNjI5MDk3NzAxfQ.VPpKmqk4v1yYDKj_sBZrjMpcDTIS6kvEn_dbsuGL5noGRE4oyLb952ysPVgum9g9C5beP8tM5VzWyWiwcXfzHg
            // Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODQyNzQzNzQxIiwianRpIjoiUzBmZWIwMTdkMWQxNCIsImlhdCI6MTYyOTAzNzIyMSwiZXhwIjoxNjI5MDk3NzAxfQ.VPpKmqk4v1yYDKj_sBZrjMpcDTIS6kvEn_dbsuGL5noGRE4oyLb952ysPVgum9g9C5beP8tM5VzWyWiwcXfzHg`
            }})
        .then(( data ) => {
          // console.log(data)
          context.commit("setFrequentStore", data.data.favoriteStoreList);
        })
        .catch(() => {
          alert("수행 중 오류가 발생했습니다.");
        });
    },
    deleteFrequentStore(context,payload) {
      http
        .delete(payload)
        // 아래에 작성하기
        // .then(( data ) => {
        //   context.commit("setMarkets", data.data.marketList);
        // })
    },
    getOrderList(context) { // ​​/api​/store​/{storeId} 해당 ID의 가게 정보
      http1
        .get(`/api/orderinfo/${this.orderInfoId}/`, {
          // header token 팁
          headers: {
            // Authorization: `Bearer `+ this.state.token
            Authorization: `Bearer `+ this.getters.getToken
          }})
        .then(( data ) => {
          context.commit("setOrderList", data.data.orderItemList);
          // context.commit("setOrderList", data.data.orderInfo);
          // console.log(context.commit("setOrderList", data.data.orderInfo))
          console.log(context.commit("setOrderList", data.data.orderItemList
          ))
        })
        .catch(() => {
          alert("getOrderList 오류 발생");
        });
    },
    getBasketList(context) { // ​​/api​/store​/{storeId} 해당 ID의 가게 정보
      http1
      .get("/api/basket")
      //  {
      // // // header token 팁
      //     headers: {
      // //       // Authorization: `Bearer `+ this.state.token
      //       Authorization: `Bearer `+ this.getters.getToken
      //     }})
        .then(( data ) => {
          context.commit("setBasketList", data.data.item);
          console.log(context.commit("setBasketList", data.data.item
          ))
        })
        .catch(() => {
          alert("getBasketList 오류 발생");
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
