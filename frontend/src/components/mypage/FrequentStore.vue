<template>
  <v-app class="color5">
    <v-container>
      <v-row>
        <v-col
          cols="8"
        >
          <h2>
            단골 가게 관리
          </h2>
        </v-col>
        <!-- <v-col
          cols="4"
        > -->
        <!-- 검색하면 데이터 찾아야됨 -->
        <!-- <v-text-field
            flat
            hide-details
            label="Search"
            prepend-inner-icon="mdi-magnify"
            solo-inverted
            @keyup.enter="storeSearch"
          />
        </v-col> -->
      </v-row>
    </v-container>
    <br>
    <div
      v-for="(fs, index) in frequentStore"
      :key="index"
    >
      <v-form>
        <v-container>
          <v-row>
            <v-col
              cols="1"
            >
              <v-checkbox value />
            </v-col>
            <v-col
              cols="2"
            >
              <!-- <img
                src="@/assets/fish_store.png"
                alt="가게 사진"
                style="width:60px"
              > -->
              {{ fs.store.image }}
              <!-- 사진 안나옴 405 에러 발생 -->
              <!--
              <v-img
                :src="'http://localhost:8081/api/favorite/' + fs.store.image"
              /> -->
            </v-col>
            <v-col
              cols="2"
            >
              <v-row>
                {{ fs.store.market.name }}<br>
                {{ fs.store.name }} <br>
                {{ fs.store.storeType.name }}
              </v-row>
            </v-col>


            <v-col
              cols="4"
            >
              {{ fs.store.comment }}
            </v-col>
            <v-col
              cols="2"
            >
              <v-icon @click="deleteFrequent(fs.store.id)">
                <!-- <v-icon @click="deleteFrequent(index)"> -->
                x
              </v-icon>
            <!-- test -->
            </v-col>
          </v-row>
        </v-container>
      </v-form>
    </div>
  </v-app>
</template>




<script>
import {mapGetters} from "vuex";
import axios from 'axios'

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
      comment: '',
    }
  },
    computed:{
      ...mapGetters(["frequentStore", "getToken"])
    },
    created() {
      this.$store.dispatch("getFrequentStore");
    },
    methods: {
      // submit: function(name,phoneNumber) {
        // 400 error 발생 해결방법
      // storeSearch: function() {
      //   axios({
      //     method:'get',
      //     url:'http://localhost:8081/api/favorite/search',
      //     headers:{
      //       Authorization: `Bearer `+ this.$store.getters.getToken
      //     },
      //     data:{
      //       name: this.name,
      //       comment: this.comment,
      //     }
      //   })
      //   .then((res) => {
      //     // console.log(res)
      //     // alert("회원정보가 변경되었습니다.");[]
      //   })
      //   .catch((err) => {
      //     // console.log(err)
      //     alert("검색한 가게는 단골가게가 아닙니다. 확인해주세요");
      //   });
      // },
      // 수정해야됨
      deleteFrequent: function(id) {
        axios({
          method:'delete',
          url:`http://localhost:8081/api/favorite/${this.id}`,
          headers:{
            Authorization: `Bearer `+ localStorage.getItem("token")
          },
          data:{
            // favoriteStore: [storeId],
            storeId: [id],
            // basketIdList: [storeId],
          }
        })
        .then(() => {
          this.$store.dispatch("getBasketList")
          alert("단골가게가 삭제 되었습니다.");
          // 로그는 제대로 오는 듯 but 장바구니에서 상품을 제거해야됨
          // reload만 되고 상품제거는 안됨
          // location.reload()
        })
        .catch(() => {
          alert("확인해주세요");
        });
        // console.log(basketId)
      },
    },
      // deleteFrequent(index){
      //   this.$store.dispatch("deleteFrequentStore",`/api/favorite?storeId=${this.$store.getters.frequentStore[index].store.id}`)
      // }
    // watch:{
    //   tab: function (val){ // 선택한 탭 변경될 경우
    //     this.$store.commit("setFrequentStore",this.$store.getters.frequentStore[val]);
    //     // console.log(this.$store.getters.market);
    //   }
    // },
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
