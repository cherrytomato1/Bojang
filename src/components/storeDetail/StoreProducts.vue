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
        v-for="product in products"
        :key="product.name"
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
          <v-list-item-title v-text="product.name" />

          <v-list-item-subtitle v-text="product.price" />
        </v-list-item-content>

        <v-list-item-action>
          <v-btn icon>
            <v-icon>
              mdi-basket
            </v-icon>
          </v-btn>

          <v-text-field
            v-model="product.count"
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
  export default {
    data: () => ({
      products: [
        {
          name: '갈치',
          price: '1마리 10,000원 / 5마리 40,000원',
          count: 0,
        },
        {
          name: '꽁치',
          price: '1마리 10,000원 / 5마리 40,000원',
          count: 0,
        },
        {
          name: '한치',
          price: '1마리 10,000원 / 5마리 40,000원',
          count: 0,
        },
        {
          name: '쥐치',
          price: '1마리 10,000원 / 5마리 40,000원',
          count: 0,
        },
        {
          name: '자갈치',
          price: '1마리 10,000원 / 5마리 40,000원',
          count: 0,
        },
      ],
    }),
  }
</script>
