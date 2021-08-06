<template>
  <v-container>
    <!-- 1. 가게 관리 -->
    <h2>1. 가게 관리 페이지</h2>
    <v-simple-table>
      <template v-slot:default>
        <tbody>
          <tr>
            <td>가게이름</td>
            <td>
              <v-form ref="form">
                <v-text-field
                  v-model="store.name"
                />
              </v-form>
            </td>
          </tr>
          <tr>
            <td>이미지</td>
            <td>
              <v-file-input
                label="이미지 경로"
                filled
                prepend-icon="mdi-camera"
              />
            </td>
          </tr>
          <tr>
            <td>가게설명</td>
            <td>
              <v-form
                ref="form"
              >
                <v-text-field
                  v-model="store.ment"
                />
              </v-form>
            </td>
            <td>
              <v-btn>
                수정
              </v-btn>
            </td>
          </tr>
          <tr>
            <td>판매품목</td>
            <td>
              <v-simple-table>
                <thead>
                  <tr>
                    <th class="text-left">
                      이미지
                    </th>
                    <th class="text-left">
                      이름
                    </th>
                    <th class="text-left">
                      설명
                    </th>
                    <th class="text-left">
                      가격
                    </th>
                    <th class="text-left">
                      판매중
                    </th>
                    <th />
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="product in store.products"
                    :key="product.name"
                  >
                    <td>
                      <v-icon
                        class="grey lighten-1"
                        dark
                      >
                        mdi-fish
                      </v-icon>
                    </td>
                    <td><h4>{{ product.name }}</h4></td>
                    <td>{{ product.ment }}</td>
                    <td><h4>{{ product.price }}</h4></td>
                    <td>
                      <v-checkbox
                        v-model="product.selling"
                        value="1"
                        required
                      />
                    </td>
                    <td>
                      <v-btn
                        depressed
                        color="orange"
                        x-small
                      >
                        수정
                      </v-btn>
                      <v-spacer />
                      <v-btn
                        depressed
                        color="error"
                        x-small
                      >
                        삭제
                      </v-btn>
                    </td>
                  </tr>
                  <tr>
                    <td />
                    <td />
                    <v-btn
                      depressed
                      color="primary "
                      medium
                    >
                      추가하기
                    </v-btn>
                  </tr>
                </tbody>
              </v-simple-table>
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    1-1. 상품 수정 페이지
    <StoreAddProduct />
    <!-- 2. 주문 관리 -->
    <h2>2. 주문 관리 페이지</h2>
    <!-- 3. 판매 금액 -->
    <h2>3. 판매 금액 페이지</h2>
  </v-container>
</template>

<script>
import StoreAddProduct from './StoreAddProduct';

export default {
  components: {
    StoreAddProduct
  },
    data () {
      return {
        store:
          {
            name: '싸피수산',
            image: '',
            ment: '싸피수산은 싸피시장에서 30년 이상 장사한 가게입니다. 믿음과 신뢰를 바탕으로 판매하고 있습니다.',
            products: [{
              image: '',
              name: '갈치',
              ment: '몸에 좋고 맛도 좋은 갈치가 1마리에 5천원',
              price: '5,000',
              selling: true,
            },{
              image: '',
              name: '꽁치',
              ment: '몸에 좋고 맛도 좋은 꽁치가 1마리에 6천원',
              price: '6,000',
              selling: true,
            },{
              image: '',
              name: '쥐치',
              ment: '몸에 좋고 맛도 좋은 쥐치가 1마리에 7천원',
              price: '7,000',
              selling: false,
            },{
              image: '',
              name: '자갈치',
              ment: '몸에 좋고 맛도 좋은 자갈치가 1마리에 8천원',
              price: '8,000',
              selling: true,
            }],
          },
      }
    },
  }
</script>
