<template>
  <v-layout
    justify-center
    align-center
  >
    <v-card
      color="color1"
      max-width="1000"
      class="align-center"
    >
      <v-card-title class="text-center justify-center py-6">
        <h1 class="font-weight-bold basil--text">
          {{ $store.getters.market.name }}
        </h1>
      </v-card-title>

      <v-tabs
        v-model="tab"
        background-color="transparent"
        color="basil"
        grow
      >
        <v-tab
          v-for="category in categories"
          :key="category.tabName"
        >
          {{ category.tabName }}
        </v-tab>
      </v-tabs>

      <v-tabs-items v-model="tab">
        <v-tab-item
          v-for="cg in categories"
          :key="cg.tabName"
        >
          <v-card
            color="color3"
            flat
          >
            <v-tabs-items v-model="tab">
              <v-tab-item
                v-for="c in categories"
                :key="c.tabName"
              >
                <v-card
                  color="color4"
                  flat
                >
                  <v-card-text>
                    <v-img
                      :src="c.image"
                    />
                  </v-card-text>
                  <!-- 맵 이미지 아래 가게 리스트(표) 생성 -->

                  <v-container fluid>
                    <v-data-iterator
                      :items="$store.getters.stores"
                      :items-per-page.sync="itemsPerPage"
                      :page.sync="page"
                      hide-default-footer
                    >
                      <template v-slot:default="props">
                        <v-row>
                          <v-col
                            v-for="store in props.items"
                            :key="store.name"
                            cols="12"
                            sm="6"
                            md="4"
                            lg="3"
                          >
                            <v-card @click="clickHandler(store.id)">
                              <v-card-title class="subheading font-weight-bold">
                                {{ store.name }}
                                {{ store.section }}
                              </v-card-title>
                            </v-card>
                          </v-col>
                        </v-row>
                      </template>

                      <template v-slot:footer>
                        <v-row
                          class="mt-2"
                          align="center"
                          justify="center"
                        >
                          <span class="grey--text">한 화면에 표시할 가게 수</span>
                          <v-menu offset-y>
                            <template v-slot:activator="{ on, attrs }">
                              <v-btn
                                dark
                                text
                                color="primary"
                                class="ml-2"
                                v-bind="attrs"
                                v-on="on"
                              >
                                {{ itemsPerPage }}
                                <v-icon>mdi-chevron-down</v-icon>
                              </v-btn>
                            </template>
                            <v-list>
                              <v-list-item
                                v-for="(number, index) in itemsPerPageArray"
                                :key="index"
                                @click="updateItemsPerPage(number)"
                              >
                                <v-list-item-title>{{ number }}</v-list-item-title>
                              </v-list-item>
                            </v-list>
                          </v-menu>

                          <v-spacer />

                          <span
                            class="mr-4
                            grey--text"
                          >
                            현재 {{ page }} 페이지 / 총 {{ numberOfPages }}
                          </span>
                          <v-btn
                            fab
                            dark
                            color="blue darken-3"
                            class="mr-1"
                            @click="formerPage"
                          >
                            <v-icon>mdi-chevron-left</v-icon>
                          </v-btn>
                          <v-btn
                            fab
                            dark
                            color="blue darken-3"
                            class="ml-1"
                            @click="nextPage"
                          >
                            <v-icon>mdi-chevron-right</v-icon>
                          </v-btn>
                        </v-row>
                      </template>
                    </v-data-iterator>
                  </v-container>
                </v-card>
              </v-tab-item>
            </v-tabs-items>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
    </v-card>
  </v-layout>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: 'StoreMap',
  data () {
    return {
      tab: null,
      market: 1,
      storeTypeId: 1,
      categories: [
        {cgId:1,tabName:'농산물',image:require("../../assets/map/MarketMap_1.jpg")},
        {cgId:2,tabName:'축산물',image:require("../../assets/map/MarketMap_2.jpg")},
        {cgId:3,tabName:'수산물',image:require("../../assets/map/MarketMap_3.jpg")},
        {cgId:4,tabName:'가공식품',image:require("../../assets/map/MarketMap_4.jpg")},
        {cgId:5,tabName:'의류신발',image:require("../../assets/map/MarketMap_5.jpg")},
        {cgId:6,tabName:'가정용품',image:require("../../assets/map/MarketMap_6.jpg")},
        {cgId:7,tabName:'기타',image:require("../../assets/map/MarketMap_7.jpg")},
      ],

      // vuetify
      keys:['section'],
      itemsPerPageArray: [4, 8, 12],
      search: '',
      filter: {},
      page: 1,
      itemsPerPage: 4,
    }
  },

  computed: {
    ...mapGetters(["stores"]),
    ...mapGetters(["markets"]),
    numberOfPages () {
      return Math.ceil(this.$store.getters.stores.length / this.itemsPerPage)
    },
  },
  watch:{
    tab: function (val){ // 선택한 탭 변경될 경우
    val=val+1
      this.$store.dispatch("getStores","/api/store/category?marketId="+this.market+"&storeTypeId="+val);

    },
    market: function (val){ // 선택한 시장 변경될 경우
      this.$store.dispatch("getStores","/api/store/category?marketId="+val+"&storeTypeId="+this.tab+1);

    }
  },
  created() {
    this.$store.dispatch("getStores","/api/store/category?marketId="+this.market+"&storeTypeId="+this.storeTypeId);
    // 메인페이지로 왔을 때 처음에 가게 선택 섹션이 보이지 않아야 함.
    this.$store.commit("setStore",[]);
  },
  methods: {
    nextPage () {
      if (this.page + 1 <= this.numberOfPages) this.page += 1
    },
    formerPage () {
      if (this.page - 1 >= 1) this.page -= 1
    },
    updateItemsPerPage (number) {
      this.itemsPerPage = number
    },
    clickHandler: function(storeId) {
      this.$store.dispatch("getStore",`/api/store/${storeId}`);
    }
  },

}
</script>

<style>
/* Helper classes */
.basil {
  background-color: #FFFBE6 !important;
}
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
  background-color: #dadbdb    !important;
}
.color4{
  background-color: #3ac569 !important;
}
.basil--text {
  color: #3ac569 !important;
}
</style>


