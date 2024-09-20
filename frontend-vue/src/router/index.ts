import { createRouter, createWebHistory } from "vue-router";
import PaginationComponent from "@/components/PaginationComponent.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/admin/users",
      component: PaginationComponent,
    },
  ],
});

export default router;
