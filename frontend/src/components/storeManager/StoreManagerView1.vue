<template>
  <v-container>
    <!-- 1. 가게 관리 -->
    <h2>1. 가게 관리 페이지</h2>
    <div class="table-box">
      <table
        class="table table--min table--horizontal"
        cellspacing="0"
        cellpadding="0"
      >
        <colgroup>
          <col style="width:10%" />
          <col style="width:40%" />
          <col style="width:30%" />
          <col style="width:20%" />
        </colgroup>

        <tbody>
          <tr>
            <th>
              가게이름
            </th>
            <td colspan="3">
              <h1>{{ $store.getters.myStore.name }}</h1>
            </td>
          </tr>
          <tr>
            <th>
              이미지
            </th>
            <td>
              <v-img
                :src="
                  'http://localhost:8081/api/store/downloadFile/' +
                    $store.getters.myStore.image
                "
              />
            </td>
            <td>
              <v-file-input
                accept="image/*"
                label="이미지 등록 및 수정"
                prepend-icon="mdi-camera"
                @change="selectImage"
              />
            </td>
            <td>
              <v-btn @click="submit">
                이미지 변경
              </v-btn>
            </td>
          </tr>
          <tr>
            <th>가게설명</th>
            <td>
              현재 등록된 가게설명 : <br /><strong>{{
                $store.getters.myStore.comment
              }}</strong>
            </td>
            <td>
              <v-form ref="form">
                <v-text-field v-model="comment" placeholder="수정할 가게설명" />
              </v-form>
            </td>
            <td>
              <v-btn @click="updateComment">
                수정
              </v-btn>
            </td>
          </tr>
          <tr>
            <th>판매품목</th>
            <td colspan="3">
              <!-- 판매 품목 테이블 -->
              <v-simple-table>
                <colgroup>
                  <col style="width:30%" />
                  <col style="width:20%" />
                  <col style="width:25%" />
                  <col style="width:15%" />
                  <col style="width:5%" />
                  <col style="width:5%" />
                </colgroup>

                <thead>
                  <tr>
                    <th class="text-center">
                      이미지
                    </th>
                    <th class="text-center">
                      이름
                    </th>
                    <th class="text-center">
                      설명
                    </th>
                    <th class="text-center">
                      가격
                    </th>
                    <th class="text-center">
                      판매중
                    </th>
                    <th />
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="(item, index) in $store.getters.myStore.itemList"
                    :key="item.id"
                  >
                    <td>
                      <v-img
                        :src="
                          'http://localhost:8082/api/item/downloadFile/' +
                            item.image
                        "
                      />
                    </td>
                    <td>
                      <h4>{{ item.name }}</h4>
                    </td>
                    <td>{{ item.content }}</td>
                    <td>
                      <h4>{{ item.price }}</h4>
                    </td>
                    <td>
                      <v-checkbox v-model="item.onSale" required />
                    </td>
                    <td>
                      <!-- 상품 수정 모달창 -->
                      <v-dialog v-model="dialogUpdate[index]" width="500">
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn
                            color="orange"
                            dark
                            v-bind="attrs"
                            x-small
                            v-on="on"
                          >
                            수정
                          </v-btn>
                        </template>

                        <v-card>
                          <StoreUpdateProduct :idx="index" />
                          <v-btn
                            color="primary"
                            text
                            @click="dialogUpdate[index] = false"
                          >
                            X
                          </v-btn>
                        </v-card>
                      </v-dialog>
                      <v-spacer />
                      <v-btn depressed color="error" x-small>
                        삭제
                      </v-btn>
                    </td>
                  </tr>
                  <tr>
                    <td />
                    <!-- 상품 추가 모달창 -->
                    <v-dialog v-model="dialogAdd" width="500">
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn color="primary" dark v-bind="attrs" v-on="on">
                          상품 추가
                        </v-btn>
                      </template>

                      <v-card>
                        <StoreAddProduct />

                        <v-btn color="primary" text @click="dialogAdd = false">
                          X
                        </v-btn>
                      </v-card>
                    </v-dialog>
                  </tr>
                </tbody>
              </v-simple-table>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </v-container>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";
import StoreAddProduct from "./StoreAddProduct";
import StoreUpdateProduct from "./StoreUpdateProduct";

export default {
  name: "StoreManagerView",
  components: {
    StoreAddProduct,
    StoreUpdateProduct
  },
  data() {
    return {
      dialogUpdate: [],
      // dialogUpdate: false,
      dialogAdd: false,

      image: "",
      comment: this.$store.getters.myStore.comment
    };
  },
  created() {
    for (var i = 0; i < this.$store.getters.myStore.itemList.length; i++) {
      this.dialogUpdate[i] = false;
    }
  },

  methods: {
    updateComment() {
      axios({
        method: "post",
        url: "http://localhost:8081/api/store/comment",
        headers: {
          Authorization: `Bearer ` + this.$store.getters.getToken
        },
        data: {
          comment: this.comment
        }
      }).then(() => {
        // console.log(this.comment)
        alert("가게설명이 성공적으로 수정되었습니다.");
        this.$router.go();
      });
    },

    selectImage(file) {
      this.image = file;
    },
    async submit() {
      const formData = new FormData();
      formData.append("file", this.image);

      try {
        const { data } = await axios.post(
          "http://localhost:8081/api/store/image",
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
              Authorization: `Bearer ` + this.$store.getters.getToken
            }
          }
        );
        console.log(data);
        this.$router.go();
      } catch (err) {
        console.log(err);
      }
    }
  }
};
</script>

<style>
.table {
  table-layout: fixed;
  width: 100%;
  text-align: center;
}
.table th {
  background: #ccc;
}
.table td,
.table th {
  padding: 10px 20px;
  border-top: 1px solid #ccc;
  word-break: break-all;
}
.table--min {
  min-width: 700px;
}
@media screen and (max-width: 768px) {
  /*normal*/
  .table-box {
    overflow-x: auto;
  }
  /*horizontal*/
  .table--horizontal tr {
    position: relative;
  }
  .table--horizontal th {
    position: fixed;
    width: 32px;
  }
}
</style>
