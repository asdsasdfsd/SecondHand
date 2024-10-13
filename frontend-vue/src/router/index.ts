import { createRouter, createWebHistory } from "vue-router";
import LoginPage from "@/pages/LoginPage.vue";
import RegisterPage from "@/pages/RegisterPage.vue";
import UserHomePage from "@/pages/User/UserHomePage.vue";
import ManagementPage from "@/pages/admin/ManagementPage.vue";

import UserPaginationComponent from "@/pages/admin/admin-views/UserPaginationComponent.vue";

const routes = [
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
export { routes };