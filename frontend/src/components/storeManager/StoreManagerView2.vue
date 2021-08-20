<template>
  <!-- 2. 주문 관리 -->
  <div v-if="orderList.length==0">
    <h2>등록된 주문이 없습니다.</h2>
  </div>
  <div v-else>
    <v-card
      class="mx-auto"
      max-width="500"
    >
      <v-list>
        <v-list-group
          v-for="(order,idx) in orderList"
          :key="idx"
          v-model="mod1"
          :prepend-icon="act1"
          no-action
        >
          <template v-slot:activator>
            <v-list-item-content>
              <v-list-item-title>
                <v-row>
                  주문번호 : {{ order.orderInfoId }}
                </v-row>
                <v-row>
                  주문 내역 : {{ order.item.name }}
                </v-row>
                <v-row>
                  수량 : {{ order.amount }}
                </v-row>
                <v-row>
                  요구사항 : {{ order.comment }}
                </v-row>
              </v-list-item-title>
            </v-list-item-content>
          </template>

          <v-list-item
            v-for="child in item.items[1]"
            :key="child.title"
          >
            <v-list-item-content>
              <v-list-item-title v-text="child.title" />
            </v-list-item-content>
          </v-list-item>
        </v-list-group>
      </v-list>
    </v-card>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import StoreAddProduct from './StoreAddProduct';

export default {
  name: 'StoreManagerView2',
  components: {
  },
  data () {
    return {
      orderList:[],
      mod1:true,
      mod2:true,
      act1:'mdi-alarm-check',
      act2:'mdi-alarm-off',
      items: [
        {
          title: '미완료 주문',
          active: true,
          action: 'mdi-alarm-check',
          items: [{ title: '미완료 1' }],
        },
        {
          title: '완료 주문',
          active: true,
          action: 'mdi-alarm-off',
          items: [
            { title: '완료1' },
            { title: '완료2' },
            { title: '완료3' },
          ],
        },
      ]
    }
  },
  created(){
    axios({
      method:'get',
      url:`http://i5a508.p.ssafy.io:8082/api/orderitem/${$store.getters.myStore.id}`,
      headers:{
        Authorization: `Bearer `+ this.$store.getters.getToken
      },
    })
    .then((response) =>
      this.orderList = response.data.list,
    )
  },

  }
</script>
