<template>
  <v-app class="color5">
    <v-container>
      <v-row>
        <v-col
          cols="8"
        >
          <h2>
            <!-- {{ $store.getters.frequentStore }} -->
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
            <!-- <img
              :src="favoriteStoreList.store.image"
              alt="가게 사진"
              style="width:60px"
            > -->
          </v-col>

          <v-col
            cols="2"
          >
            <p>
              <!-- {{ $store.getters.frequentStore }} -->
              싸피시장
            </p><br>
            <h3>민기네 수산</h3><br>
            <p>
              수산
            </p>
            <!-- <v-text>
              싸피시장<br>
              <h3>민기네 수산</h3><br>
              수산
            </v-text> -->
          </v-col>
          <v-col
            cols="4"
          >
            <p>
              <br>
              싱싱한 생선 팔아요
            </p>
            <!-- <v-text>
              <br>
              싱싱한 생선 팔아요
            </v-text> -->
          </v-col>
          <v-col
            cols="2"
          >
            <!-- x 표시 아이콘 찾으면 적용해주세요 -->
            <v-icon>x</v-icon>
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
      tab: null,
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
    // this.$store.dispatch("");
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
