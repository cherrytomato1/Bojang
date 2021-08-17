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
              v-model="start"
              label="조회할 처음 날짜를 선택해주세요"
              persistent-hint
              prepend-icon="mdi-calendar"
              v-bind="attrs"
              @blur="date1 = parseDate(start)"
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
              v-model="fin"
              label="조회할 마지막 날짜를 선택해주세요."
              persistent-hint
              prepend-icon="mdi-calendar"
              v-bind="attrs"
              @blur="date2 = parseDate(fin)"
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
    </v-row>
  </v-container>
</template>

<script>
import {mapGetters} from "vuex";
import StoreAddProduct from './StoreAddProduct';

  export default {
    name: 'StoreManagerView',

    data: vm => ({
      date1: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      date2: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),

      start: vm.formatDate((new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)),
      fin: vm.formatDate((new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)),

      menu1: false,
      menu2: false,
    }),

    computed: {
      computedDateFormatted () {
        return this.formatDate(this.date1)
      },
    },

    watch: {
      date1 (val) {
        this.start = this.formatDate(this.date1)
      },
      date2 (val) {
        this.fin = this.formatDate(this.date2)
      },
    },

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
    },
  }
</script>
