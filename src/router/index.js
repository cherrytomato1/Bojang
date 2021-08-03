import Vue from 'vue'
import VueRouter from 'vue-router'
import RecommendSection from '@/components/mainpage/RecommendSection'
import StoreMap from '@/components/mainpage/StoreMap'
import OrderList from '@/components/mypage/OrderList'
import UserInformationModify from '@/components/mypage/UserInformationModify'
import FrequentStore from '@/components/mypage/FrequentStore'
import StoreInformationModify from '@/components/mypage/StoreInformationModify'
// import Home from '@/views/Home'


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
  {
    path: '/orderlist',
    name: 'OrderList',
    component: OrderList
  },
  {
    path: '/mypage/userinformationmodify',
    name: 'UserInformationModify',
    component: UserInformationModify
  },
  {
    path: '/mypage/frequentstore',
    name: 'FrequentStore',
    component: FrequentStore
  },
  {
    path: '/mypage/storeinformationmodify',
    name: 'StoreInformationModify',
    component: StoreInformationModify
  },
  // {
  //   path: '/home',
  //   name: 'Home',
  //   component: Home
  // },
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
