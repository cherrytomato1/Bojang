<template>
  <!-- 3. 판매 금액 -->
  <v-container>
    <v-row>
      <v-col
        cols="12"
        lg="6"
      >
        <v-menu
          ref="menu1"
          v-model="menu1"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          max-width="290px"
          min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="date1"
              label="조회할 처음 날짜를 선택해주세요"
              persistent-hint
              prepend-icon="mdi-calendar"
              v-bind="attrs"

              v-on="on"
            />
          </template>
          <v-date-picker
            v-model="date1"
            no-title
            @input="menu1 = false"
          />
        </v-menu>
      </v-col>

      <v-col
        cols="12"
        lg="6"
      >
        <v-menu
          v-model="menu2"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          max-width="290px"
          min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="date2"
              label="조회할 마지막 날짜를 선택해주세요."
              persistent-hint
              prepend-icon="mdi-calendar"
              v-bind="attrs"

              v-on="on"
            />
          </template>
          <v-date-picker
            v-model="date2"
            no-title
            @input="menu2 = false"
          />
        </v-menu>
      </v-col>
      <p>조회 기간 : <strong>{{ date1 }}</strong> ~ <strong>{{ date2 }}</strong> </p>
      <v-btn
        small
        @click="resultHandler(date1,date2)"
      >
        조회
      </v-btn>
    </v-row>

    <!-- 조회 내용 -->
    <v-divider />
    <div v-if="sales.length>0">
      <h2>
        조회기간 내 매출은 총 {{ sum }} 입니다.
      </h2>
      <v-divider />
      <h3> 매출 상세 내역 : </h3><br>

      <v-card
        class="mx-auto"
        max-width="400"
      >
        <v-list flat>
          <v-list-item-group
            v-model="model"
            color="indigo"
          >
            <v-list-item
              v-for="(item,index) in sales"
              :key="index"
            >
              <v-list-item-icon>
                {{ item.registerTime }} :
              </v-list-item-icon>

              <v-list-item-content>
                {{ item.sum }} 원
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-card>
    </div>
  </v-container>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";

export default {
name: 'StoreManagerView3',

// data: vm => ({
//   date1: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
//   date2: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),

//   start: vm.formatDate((new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)),
//   fin: vm.formatDate((new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)),

//   sales:[],
//   sum:0
// }),
data: () => ({
  date1: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
  date2: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
  menu1: false,
  menu2: false,
  sales: [],
  sum:0
}),

methods: {
  formatDate (date) {
    if (!date) return null

    const [year, month, day] = date.split('-')
    return `${year}/${month}/${day}`
  },

  parseDate (date) {
    if (!date) return null

    const [month, day, year] = date.split('/')
    return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
  },

  resultHandler(date1, date2){
    axios({
        method:'get',
        url:'http://i5a508.p.ssafy.io:8081/api/sales/date',
        headers:{
          Authorization: `Bearer `+ this.$store.getters.getToken
        },
        params:{
          end: this.date2,
          start: this.date1,
        }
      })
      .then((response) =>
        this.sales = response.data.salesList,
        console.log(this.sales)
      )
      this.sum=0;
      for(let i=0;this.sales.length;i++){
        this.sum+=this.sales[i].sum
      }
  }
}
}
</script>
