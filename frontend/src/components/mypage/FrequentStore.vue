<template>
  <v-app class="color5">
    <v-container>
      <v-row>
        <v-col
          cols="8"
        >
          <h2>
            <!-- {{ $store.getters.frequentStore.store.market.name }} -->
            <!-- {{ $store.getters.frequentStore }} -->
            <!-- state는 mutation에서 받기만 하니까 비어있음 -->
            <!-- {{ $store.state.frequentStore }} -->
            <!-- {{ $store.getters.frequentStore.market.name }} -->
            단골 가게 관리
          </h2>
        </v-col>
        <v-col
          cols="4"
        >
          <!-- 검색하면 데이터 찾아야됨 -->
          <v-text-field
            flat
            hide-details
            label="Search"
            prepend-inner-icon="mdi-magnify"
            solo-inverted
          />
        </v-col>
      </v-row>
    </v-container>
    <v-form>
      <v-container>
        <v-row>
          <v-col
            cols="2"
          >
            <v-checkbox value />
          </v-col>
          <v-col
            cols="2"
          >
            <!-- size mx-auto가 안되네 추후 설정 -->
            <img
              src="@/assets/fish_store.png"
              alt="가게 사진"
              style="width:60px"
            >
            <!-- img 하는건 찾아보기 -->
            <!-- {{ $store.getters.frequentStore[0].store.image }} -->
            <!-- <img
              :src="favoriteStoreList.store.image"
              alt="가게 사진"
              style="width:60px"
            > -->
          </v-col>
          <v-col
            cols="2"
          >
            <!-- 찾아보기 - 안되는거 물어보기 -->
            <!-- <p
              v-for="(store, index) in stores"
              :key="index"
              :store="store"
            > -->
            <p>
              <!-- v-for로 돌리기 -->
              {{ $store.getters.frequentStore[index].store.market.name }}<br>
              {{ $store.getters.frequentStore[index].store.name }}<br>
              {{ $store.getters.frequentStore[index].store.storeType.name }}
            </p>
          </v-col>
          <v-col
            cols="4"
          >
            <p>
              {{ $store.getters.frequentStore[0].store.comment }}
            </p>
          </v-col>
          <v-col
            cols="2"
          >
            <v-icon @click="deleteFrequent(index)">
              x
            </v-icon>
            <!-- test -->
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </v-app>
</template>




<script>
import {mapGetters} from "vuex";

// const Token = store.state.getters.token;
// const headers = {
//     "Content-Type": "application/json",
//     Authorization: "Bearer " + Token,
// };

export default {
  name: 'FrequentStore',
  data() {
    return{
      name: '',
    }
  },
  // 이 부분 다시 하기
  methods: {
    deleteFrequent(index){
      this.$store.dispatch("deleteFrequentStore",`/api/favorite?storeId=${this.$store.getters.frequentStore[index].store.id}`)

    }
  },
  computed:{
    ...mapGetters(["frequentStore", "getToken"])
  },
  // watch:{
  //   tab: function (val){ // 선택한 탭 변경될 경우
  //     this.$store.commit("setFrequentStore",this.$store.getters.frequentStore[val]);
  //     // console.log(this.$store.getters.market);
  //   }
  // },
  created() {
    this.$store.dispatch("getFrequentStore");
  }

};










// import axios from 'axios'
//   new Vue({
//     el: '#app',


// created() {
//   axios
//     .get('http://localhost:8081/api/favorite')
//     .then((res) => {
//       // handle success
//       console.log(res);
//     })
//     .catch((err) => {
//       // handle error
//       console.log(err);
//     })
//     .then(() => {
//       // always executed
//     })
//   }
// });

// export default {
//   name: 'FrequentStore',
//   data: function () {
//     return {
//         id: null,
//         store: null,
//     }
//   },


  //  getData () {
  //     axios {
  //       method: 'get',
  //       url: 'http://localhost:8081/api/favorite',
  //     }
  //       .then(res => {
  //         console.log(res)
  //       })
  //       .catch(err => {
  //         console.log(err)
  //       })
  //   },
    // }

</script>
