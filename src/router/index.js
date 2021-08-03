import Vue from 'vue'
import VueRouter from 'vue-router'
import Mainpage from '@/views/Mainpage'
import StoreDetail from '@/views/StoreDetail'
import OrderList from '@/components/mypage/OrderList'
import UserInformationModify from '@/components/mypage/UserInformationModify'
import FrequentStore from '@/components/mypage/FrequentStore'
import StoreInformationModify from '@/components/mypage/StoreInformationModify'
// import Home from '@/views/Home'


Vue.use(VueRouter)

const routes = [
  {
    path: '/mainpage',
    name: 'Mainpage',
    component: Mainpage
  },
  {
    path: '/storedetail',
    name: 'StoreDetail',
    component: StoreDetail
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
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
