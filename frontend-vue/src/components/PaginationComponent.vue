<template>
  <div class="flex items-center mb-4">
    <el-radio-group v-model="size" class="mr-4">
      <el-radio-button value="default">default</el-radio-button>
      <el-radio-button value="large">large</el-radio-button>

      <el-radio-button value="small">small</el-radio-button>
    </el-radio-group>
    <div>
      background:
      <el-switch v-model="background" class="ml-2" />
    </div>
    <div class="ml-4">
      disabled: <el-switch v-model="disabled" class="ml-2" />
    </div>
  </div>

  <hr class="my-4" />

  <div class="demo-pagination-block">
    <div class="demonstration">All combined</div>
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[100, 200, 300, 400]"
      :size="size"
      :disabled="disabled"
      :background="background"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalCount"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script lang="ts">
export default {
  name: "PaginationComponent",
};
</script>

<script lang="ts" setup>
import { ref, watch } from "vue";
import type { ComponentSize } from "element-plus";
import axios from "axios";
import type { User } from "../api/user/types/user";

const currentPage = ref(5);
const pageSize = ref(100);
const size = ref<ComponentSize>("default");
const background = ref(false);
const disabled = ref(false);
const totalCount = ref(0);
const users = ref<User[]>([]);

const fetchUsers = async () => {
  try {
    const response = await axios.get("/users", {
      params: {
        page: currentPage.value,
        size: pageSize.value,
      },
    });
    users.value = response.data.content;
    totalCount.value = response.data.totalElements;
  } catch (error) {
    console.error("Failed to fetch users:", error);
  }
};

const handleSizeChange = (val: number) => {
  pageSize.value = val;
  fetchUsers();
  console.log(`Page size changed to ${val} items per page`);
};

const handleCurrentChange = (val: number) => {
  currentPage.value = val;
  fetchUsers();
  console.log(`Current page changed to ${val}`);
};

watch([currentPage, pageSize], fetchUsers, { immediate: true });
</script>

<style scoped>
.demo-pagination-block + .demo-pagination-block {
  margin-top: 10px;
}
.demo-pagination-block .demonstration {
  margin-bottom: 16px;
}
</style>
