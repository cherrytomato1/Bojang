<template>
  <v-card
    max-width="600"
    class="mx-auto"
  >
    <v-toolbar
      class="color4"
      dark
    >
      <v-toolbar-title>판매중인 상품</v-toolbar-title>
    </v-toolbar>

    <v-list
      subheader
      two-line
    >
      <v-list-item
        v-for="(item,idx) in $store.getters.store.itemList"
        :key="idx"
      >
        <v-list-item-avatar>
          <v-icon
            class="grey lighten-1"
            dark
          >
            mdi-fish
          </v-icon>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title v-text="item.name" />

          <v-list-item-subtitle v-text="item.price" />
        </v-list-item-content>

        <v-list-item-action>
          <v-btn
            icon
            @click="clickHandler(item.id,amount[idx])"
          >
            <v-icon>
              mdi-basket
            </v-icon>
          </v-btn>

          <v-text-field
            v-model="amount[idx]"
            type="number"
            min="1"
            max="9"
            step="1"
          />
        </v-list-item-action>
      </v-list-item>

      <v-divider inset />
    </v-list>
  </v-card>
</template>

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

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: 'StoreProducts',
  data: () => ({
    amount: [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],
  }),
  computed: {
    ...mapGetters(["store"]),

  },
  methods: {
    clickHandler: function(id,num) {
      axios({
        method:'put',
        url:'http://localhost:8082/api/basket',
        headers:{
          Authorization: `Bearer `+ this.$store.getters.getToken
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
    }
  },

}
</script>
