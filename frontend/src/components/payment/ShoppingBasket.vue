<template>
  <v-app class="color5">
    <v-container>
      <v-row>
        <v-col
          cols="8"
        >
          <h2>
            장바구니
          </h2>
        </v-col>
        <v-col
          offset="9"
        >
          <!-- <v-subheader>장바구니 > 주문결제 > 완료</v-subheader> -->
          <p>
            장바구니 > 주문결제 > 완료
          </p>
        </v-col>
      </v-row>
    </v-container>
    <div class="table-box">
      <table
        class="table table--min table--horizontal"
        cellspacing="0"
        cellpadding="0"
      >
        <colgroup>
          <col style="width:10%">
          <col style="width:40%">
          <col style="width:30%">
          <col style="width:20%">
        </colgroup>
        <!-- <div
      v-for="(fs, index) in basketList"
      :key="index"
    > -->
        <!-- <v-data-table
        :headers="headers"
        :items="items"
        item-key="name"
        class="elevation-1"
      > -->



        <tbody>
          <tr
            v-for="(fs, index) in basketList"
            :key="index"
          >
            <td>
              <h4>{{ fs.basket.item.name }}</h4>
            </td>
            <td>{{ fs.basket.amount }}</td>
            <td>
              <h4>{{ fs.basket.item.price }}원</h4>
            </td>
            <td>
              <v-icon @click="deleteBasket(fs.basket.id)">
                x
              </v-icon>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <v-container>
      <v-row>
        <v-col
          offset="9"
        >
          <h3>전체 주문 금액 : {{ total }} 원</h3>
        </v-col>
      </v-row>
    </v-container>
    <br>
    <v-container>
      <v-row>
        <v-col
          offset="6"
        >
          <br>
          <v-btn
            to="/payment"
          >
            <span>주문하기</span>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>
<script>
import {mapGetters} from "vuex";
import axios from 'axios'

export default {
  name: 'ShoppingBasket',
  data() {
    return{
      name: '',
      amount: [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],
      total: 0,
      items: []
    }
  },
  computed:{
    ...mapGetters(["basketList", "getToken"]),
    // headers () {
    //     return [
    //       {
    //         text: '선택',
    //         align: 'start',
    //         value: 'name',
    //       },
    //       {
    //         text: '상품명',
    //       },
    //       { text: '수량', value: 'fat' },
    //       { text: '개당 가격', value: 'carbs' },
    //       { text: '상품제거', value: 'protein' },
    //     ]
    //   },
  },
  watch:{
    basketList() {
      this.totalHandler()
    }
  },
  created() {
    this.$store.dispatch("getBasketList");
  },
  methods: {
    totalHandler:function() {
      // console.log("###" + this.basketList)
      // console.log(this.basketList)

      this.basketList.forEach(basketItem => {
        this.total += basketItem.basket.amount * basketItem.basket.item.price;
      });
      // console.log(this.total)
    },



    clickHandler: function(id,num) {
      axios({
        method:'put',
        url:'http://i5a508.p.ssafy.io:8082/api/basket',
        headers:{
          Authorization: `Bearer `+ localStorage.getItem("token")
        },
        data:{
          amount: num,
          itemId: id,
        }
      })
      .then(() => {
        alert("장바구니에 상품을 넣었습니다.");
      })
      .catch(() => {
        alert("장바구니에 해당 상품이 이미 있습니다.");
      });
    },
    // 이 부분 다시 하기
    // deleteFrequent(index){
    //   this.$store.dispatch("deleteFrequentStore",`/api/favorite?storeId=${this.$store.getters.frequentStore[index].store.id}`)
    // }
    deleteBasket: function(basketId) {
      axios({
        method:'delete',
        url:'http://i5a508.p.ssafy.io:8082/api/basket',
        headers:{
          Authorization: `Bearer `+ localStorage.getItem("token")
        },
        data:{
          basketIdList: [basketId],
        }
      })
      .then(() => {
        this.$store.dispatch("getBasketList")
        alert("장바구니에 상품이 삭제 되었습니다.");
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
};
</script>

<style>
.vll {
  background-color: #FF6F61 !important;
}
.color1{
  background-color: #cff0da !important;
}
.color2{
  background-color: #88dba3 !important;
}
.color3{
  background-color: #dadbdb !important;
}
.color4{
  background-color: #3ac569 !important;
}
</style>
