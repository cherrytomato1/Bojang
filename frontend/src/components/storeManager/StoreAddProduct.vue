<template>
  <v-container>
    <v-simple-table>
      <template v-slot:default>
        <tbody>
          <tr>
            <td>상품 이름</td>
            <td>
              <v-form
                ref="form"
              >
                <v-text-field v-model="name" />
              </v-form>
            </td>
          </tr>
          <tr>
            <td>상품 설명</td>
            <td>
              <v-form
                ref="form"
              >
                <v-text-field v-model="content" />
              </v-form>
            </td>
          </tr>
          <tr>
            <td>가격</td>
            <td>
              <v-form
                ref="form"
              >
                <v-text-field v-model="price" />
              </v-form>
            </td>
          </tr>

          <tr>
            <td />
            <v-btn
              depressed
              color="primary "
              medium
              @click="addHandler"
            >
              추가하기
            </v-btn>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  data () {
    return {
      name:'',
      price:100,
      content:'',
      onSale:true,
      itemType:1,

    }
  },
  methods:{
    addHandler(){
      axios({
        method:'put',
        url:`http://i5a508.p.ssafy.io:8082/api/item/${this.$store.getters.myStore.id}`,
        headers:{
          Authorization: `Bearer `+ this.$store.getters.getToken
        },
        data:{
          content: this.content,
          itemType:this.itemType,
          name:this.name,
          onSale:this.onSale,
          price:this.price
        }
      })
      .then(() => {
        // console.log(this.comment)
        alert("상품이 성공적으로 추가되었습니다.");
        this.$router.go()
      })
    },
  }


}

</script>
