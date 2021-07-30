import Vue from 'vue'
import VueRouter from 'vue-router'
import RecommendSection from '@/components/mainpage/RecommendSection'
import StoreMap from '@/components/mainpage/StoreMap'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'RecommendSection',
    component: RecommendSection
  },
  {
    path: '/store-map',
    name: 'StoreMap',
    component: StoreMap
  },
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
