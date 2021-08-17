<template>
  <!-- 2. 주문 관리 -->
  <v-card
    class="mx-auto"
    max-width="500"
  >
    <v-list>
      <v-list-group
        v-for="item in items"
        :key="item.title"
        v-model="item.active"
        :prepend-icon="item.action"
        no-action
      >
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title v-text="item.title" />
          </v-list-item-content>
        </template>

        <v-list-item
          v-for="child in item.items"
          :key="child.title"
        >
          <v-list-item-content>
            <v-list-item-title v-text="child.title" />
          </v-list-item-content>
        </v-list-item>
      </v-list-group>
    </v-list>
  </v-card>
</template>

<script>
import {mapGetters} from "vuex";
import StoreAddProduct from './StoreAddProduct';

export default {
  name: 'StoreManagerView',
  components: {
  },
  data () {
    return {
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

  }
</script>
