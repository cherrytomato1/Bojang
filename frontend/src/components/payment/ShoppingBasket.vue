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
          offset="10"
        >
          <p>
            장바구니 > 주문결제 > 완료
          </p>
        </v-col>
      </v-row>
    </v-container>
    <div
      v-for="(fs, index) in basketList"
      :key="index"
    >
      <v-container>
        <v-row>
          <v-col
            cols="2"
            offset="1"
          >
            <!-- 시장 부분 데이터에서 없는듯 -->
            {{ fs.storeName }}
          <!-- <p>SSAFY 시장</p> -->
          </v-col>
        </v-row>
      </v-container>
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
              <!-- img는 다시 물어보기 -->
              <img
                src="@/assets/fish1.png"
                alt="가게 사진"
                style="width:60px"
              >
            </v-col>
            <v-col
              cols="2"
            >
              <br>
              {{ fs.basket.item.name }}
            <!-- 이 부분이 왜 안 나타나는지 및 400 error 해결! => token -->
            </v-col>
            <v-col
              cols="3"
            >
              <!-- 수량 조절 부분 -->
              <v-list-item-action>
                <v-btn
                  icon
                  @click="clickHandler(item.id,amount[idx])"
                >
                  <!-- 수량을 가져오면 변경 적용이 어렵나?  -->
                  <h3>{{ fs.basket.amount }}</h3>

                  <!-- test 해봄 -->
                  <!-- <v-text-field
                    v-model="fs.basket.amount[idx]"
                    type="number"
                    min="1"
                    max="100"
                    step="1"
                  /> -->
                  <!-- <v-text-field
                    v-model="amount[idx]"
                    type="number"
                    min="1"
                    max="9"
                    step="1"
                  /> -->
                </v-btn>
              </v-list-item-action>
            </v-col>
            <v-col
              cols="2"
            >
              <br>
              {{ fs.basket.item.price }}원
            </v-col>
            <v-col
              cols="2"
            >
              <br>
              <v-icon @click="deleteBasket(fs.basket.id)">
                x
              </v-icon>
            </v-col>
          </v-row>
        </v-container>
        <v-container>
          <v-row>
            <v-col
              offset="9"
            >
              <p>물품 금액 : {{ fs.basket.amount * fs.basket.item.price }}원</p>
            </v-col>
          </v-row>
        </v-container>
      </v-form>
    </div>
    <!-- 필요시 하기 -->
    <!-- <v-container>
      <v-row>
        <v-col
          cols="1"
        >
          <v-checkbox value />
        </v-col>
        <br>
        <v-col
          cols="2"
        >
          <br>
          <v-btn>
            <p>선택 삭제</p>
          </v-btn>
        </v-col>
      </v-row>
    </v-container> -->
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
    <br>
    <!-- 여기까지만 for 문 돌려야 할 듯 -->

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
    }
  },
  computed:{
    ...mapGetters(["basketList", "getToken"])
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
      console.log(this.basketList)

      this.basketList.forEach(basketItem => {
        this.total += basketItem.basket.amount * basketItem.basket.item.price;
      });
      console.log(this.total)
    },



    clickHandler: function(id,num) {
      axios({
        method:'put',
        url:'http://localhost:8082/api/basket',
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
        url:'http://localhost:8082/api/basket',
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
