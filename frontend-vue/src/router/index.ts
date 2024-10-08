import { createRouter, createWebHistory } from "vue-router";
import LoginPage from "@/pages/LoginPage.vue";
import HomePage from "@/pages/HomePage.vue";
import RegisterPage from "@/pages/RegisterPage.vue";
import UserHomePage from "@/pages/User/UserHomePage.vue";
import ManagementPage from "@/pages/admin/ManagementPage.vue";

import UserPaginationComponent from "@/pages/admin/admin-views/UserPaginationComponent.vue";

const routes = [
  {
    name: "home",
    path: "/home",
    component: HomePage,
    children: [
      {
        name: "admin-userpage",
        path: "/admin",
        component: () => import('../pages/admin/ManagementPage.vue')
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
        name: "contact",
        path: "/contact",
        meta:{
          isShow:true,
          title:"About Us"
        },
        component: () => import('../pages/contact/ContactPage.vue')
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
  {
    name: "register",
    path: "/register",
    component: RegisterPage,
  },
  {
    name: "user",
    path: "/user",
    component: UserHomePage,
  },
  {
    name: "admin",
    path: "/admin",
    component: ManagementPage,
    children: [
      {
        name: "admin-userpage",
        path: "users",
        component: UserPaginationComponent,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

localStorage.setItem("auth", "true");

export default router;
