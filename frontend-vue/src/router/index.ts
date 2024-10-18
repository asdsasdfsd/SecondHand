import { createRouter, createWebHistory } from "vue-router";
import LoginPage from "@/pages/LoginPage.vue";
import HomePage from "@/pages/HomePage.vue";
import UserHomePage from "@/pages/User/UserHomePage.vue";
import UserInformationPage from "@/pages/User/UserInformationPage.vue";
import AdminHomePage from "@/pages/admin/AdminHomePage.vue";
import RegisterPage from "@/pages/RegisterPage.vue";
import CreateCommodityPage from "@/pages/commodity/CreatePage.vue";
import ManagementPage from "@/pages/admin/ManagementPage.vue";


const routes = [
  {
    name: "home",
    path: "/home",
    component: HomePage,
    children: [
      {
        name: "admin",
        path: "/admin",
        component: () => import('../pages/admin/AdminHomePage.vue')
      },
      {
        name: "user",
        path: "/user",
        component: () => import('../pages/User/UserHomePage.vue'),
      },
      {
        name: "goods",
        path: "/commodity",
        meta:{
          isShow:true,
          title:"Home"
        },
        component: () => import('../pages/commodity/CommodityPage.vue')
      },
      {
        name: "cart",
        path: "/cart",
        meta:{
          isShow:true,
          title:"Cart"
        },
        component: () => import('../pages/commodity/CartPage.vue')
      },
      {
        name: "create",
        path: "/create",
        meta:{
          isShow:true,
          title:"Create"
        },
        component: () => import('../pages/commodity/CreatePage.vue')
      },
      {
        name: "contact",
        path: "/contact",
        meta:{
          isShow:true,
          title:"About Us"
        },
        component: () => import('../pages/contact/ContactPage.vue')
      },
      {
        name:"default",
        path:"",
        component: () => import('../pages/commodity/CommodityPage.vue'),
      },
     
    ],
  },
  {
    name: "login",
    path: "/login",
    component: LoginPage,
  },
  {
    path: "/",
    redirect: "/login",
  },
  
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

localStorage.setItem("auth", "true");

export default router;
