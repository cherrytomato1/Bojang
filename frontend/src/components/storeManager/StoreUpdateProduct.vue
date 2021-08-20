<template>
  <v-container>
    <v-simple-table>
      <template v-slot:default>
        <colgroup>
          <col style="width:40%">
          <col style="width:50%">
          <col style="width:10%">
        </colgroup>

        <tbody>
          <tr>
            <td>
              <v-img
                max-height="256"
                max-width="194"
                :src="'https://i5a508.p.ssafy.io:8082/api/item/downloadFile/' + item.image"
              />
            </td>
            <td>
              <v-file-input
                accept="image/*"
                label="이미지 등록 및 수정"
                filled
                prepend-icon="mdi-camera"
                @change="selectImage"
              />
            </td>


            <td>
              <v-btn
                depressed
                color="primary"
                @click="submit"
              >
                등록
              </v-btn>
            </td>
          </tr>
          <tr>
            <td>이름</td>
            <td colspan="2">
              <v-form
                ref="form"
              >
                <v-text-field
                  v-model="item.name"
                />
              </v-form>
            </td>
          </tr>
          <tr>
            <td>설명</td>
            <td colspan="2">
              <v-form
                ref="form"
              >
                <v-text-field
                  v-model="item.content"
                />
              </v-form>
            </td>
          </tr>
          <tr>
            <td>가격</td>
            <td colspan="2">
              <v-form
                ref="form"
              >
                <v-text-field
                  v-model="item.price"
                />
              </v-form>
            </td>
          </tr>
          <tr>
            <td>판매중</td>
            <td>
              <v-checkbox
                v-model="item.onSale"
                value="1"
                required
              />
            </td>
          </tr>
          <tr>
            <td />
            <v-btn
              depressed
              color="primary "
              medium
              @click="updateHandler"
            >
              상품 수정완료
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
  // props: ['storeid','itemid','itemimage','itemname','itemcontent','itemprice','itemonsale'],
  props: ['idx'],
  data () {
    return {
      img:'',
      item:[]
    }
  },
  created(){
    this.item = this.$store.getters.myStore.itemList[this.idx]
  },
  methods:{
    selectImage(file) {
      this.img = file;
    },
    async submit() {
      const formData = new FormData();
      formData.append("file", this.img);
      formData.append("itemId", this.item.id)
      formData.append("storeId", this.$store.getters.myStore.id)


      try {
        const { data } = await axios.patch(
          "https://i5a508.p.ssafy.io:8082/api/item/image",
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
              Authorization: `Bearer `+ this.$store.getters.getToken
            },
            data:{

            }
          }
        );
        console.log(data);
        this.$router.go()
      } catch (err) {
        console.log(err);
      }
    },

    updateHandler(){
      axios({
        method:'patch',
        url:`https://i5a508.p.ssafy.io:8082/api/item/${this.$store.getters.myStore.id}`,
        headers:{
          Authorization: `Bearer `+ this.$store.getters.getToken
        },
        data:{
          itemId: this.item.id,
          content: this.item.content,
          itemType:this.item.itemType.id,
          name:this.item.name,
          onSale:this.item.onSale,
          price:this.item.price,
          storeId:this.$store.getters.myStore.id
        }
      })
      .then(() => {
        alert("상품이 성공적으로 수정되었습니다.");
        this.$router.go()
      })
    },
  }
}
</script>
