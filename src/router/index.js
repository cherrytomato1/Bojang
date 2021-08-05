import Vue from 'vue'
import VueRouter from 'vue-router'
import Mainpage from '@/views/Mainpage'
import StoreDetail from '@/views/StoreDetail'
import OrderList from '@/components/mypage/OrderList'
import UserInformationModify from '@/components/mypage/UserInformationModify'
import FrequentStore from '@/components/mypage/FrequentStore'
import StoreInformationModify from '@/components/mypage/StoreInformationModify'

// import Home from '@/views/home/components/Home'
// import Login from '@/views/home/components/Login'
// import OauthHandler from '@/views/home/components/OauthHandler'
// import NotFound from '@/views/error/NotFound'
// import {createWebHistory, createRouter} from 'vue-router';

``
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
//   {
//     path: '/',
//     redirect: '/home'
// },
// {
//     path: '/home',
//     name: 'Home',
//     component: Home
//     // component: () => import('@/views/home/components/Home'),
// },
// {
//     path: '/login',
//     name: 'Login',
//     component: Login
//     // component: () => import('@/views/home/components/Login')
// },
// {
//     path: '/oauth2/redirect',
//     name: 'OauthHandler',
//     component: OauthHandler
//     // component: () => import('@/views/home/components/OauthHandler'),
// },
// {
//     path: "/:catchAll(.*)",
//     name: 'NotFound',
//     component: NotFound
//     // component: () => import('@/views/error/NotFound')
// }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router

// export const router = createRouter({
//   history: createWebHistory(),
//   routes
// });
