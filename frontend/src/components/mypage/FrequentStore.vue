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
                :src="'http://i5a508.p.ssafy.io:8081/api/favorite/' + fs.store.image"
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
      <!-- </v-data-table> -->
    </div>
  </v-app>
</template>




<script>
import {mapGetters} from "vuex";
import axios from 'axios'

export default {
  name: 'FrequentStore',
  data() {
    return{
      name: '',
      comment: '',
        // calories: '',
        // items: [
        //   {
        //     name: 'Frozen Yogurt',
        //     calories: 159,
        //     fat: 6.0,
        //     carbs: 24,
        //     protein: 4.0,
        //     iron: '1%',
        //   },
        //   {
        //     name: 'Ice cream sandwich',
        //     calories: 237,
        //     fat: 9.0,
        //     carbs: 37,
        //     protein: 4.3,
        //     iron: '1%',
        //   },
        // ]
    }
  },
    computed:{
      ...mapGetters(["frequentStore", "getToken"]),
      // headers () {
      //   return [
      //     {
      //       text: 'Dessert (100g serving)',
      //       align: 'start',
      //       sortable: false,
      //       value: 'name',
      //     },
      //     {
      //       text: 'Calories',
      //       value: 'calories',
      //       filter: value => {
      //         if (!this.calories) return true

      //         return value < parseInt(this.calories)
      //       },
      //     },
      //     { text: 'Fat (g)', value: 'fat' },
      //     { text: 'Carbs (g)', value: 'carbs' },
      //     { text: 'Protein (g)', value: 'protein' },
      //     { text: 'Iron (%)', value: 'iron' },
      //   ]
      // },
    },
    created() {
      this.$store.dispatch("getFrequentStore");
    },
    methods: {
      deleteFrequent: function(id) {
        axios({
          method:'delete',
          url:`https://i5a508.p.ssafy.io:8081/api/favorite/${this.id}`,
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
        })
        .catch(() => {
          alert("확인해주세요");
        });
      },
    },
};

</script>
