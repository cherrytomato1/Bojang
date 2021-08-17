<template>
  <v-container>
    <v-simple-table>
      <template v-slot:default>
        <tbody>
          <tr>
            <td>이미지</td>
            <td>
              <v-file-input
                accept="image/*"
                label="이미지 등록 및 수정"
                filled
                prepend-icon="mdi-camera"
              />
            </td>
            {{ $props.item-image }}
            {{ $props.item.name }}

            <td>
              <v-btn
                depressed
                color="primary"
                x-small
              >
                등록
              </v-btn>
            </td>
          </tr>
          <tr>
            <td>이름</td>
            <td>
              <v-form
                ref="form"
              >
                <v-text-field
                  v-model="itemName"
                />
              </v-form>
            </td>
          </tr>
          <tr>
            <td>설명</td>
            <td>
              <v-form
                ref="form"
              >
                <v-text-field />
              </v-form>
            </td>
          </tr>
          <tr>
            <td>가격</td>
            <td>
              <v-form
                ref="form"
              >
                <v-text-field />
              </v-form>
            </td>
          </tr>
          <tr>
            <td>판매중</td>
            <td>
              <v-checkbox
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
export default {
  props: ['storeId','item','item-id','item-image','item-Name','item-content','item-price','item-on-sale'],
  data () {
    return {
      itemName: $props.item.name,
      product: {
        image: '',
        name: '갈치',
        ment: '몸에 좋고 맛도 좋은 갈치가 1마리에 5천원',
        price: '5,000',
        selling: true,
      }
    }
  }
}
</script>
