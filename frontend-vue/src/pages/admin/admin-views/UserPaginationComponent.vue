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

  <el-table :data="users" style="width: 100%">
    <el-table-column prop="name" label="Name" />
    <el-table-column prop="username" label="Username" />
    <el-table-column prop="role" label="Role" />
    <el-table-column prop="phone" label="Phone" />
    <el-table-column prop="email" label="Email" />
  </el-table>

  <div class="demo-pagination-block">
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[5, 10, 20, 40]"
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
  name: "UserPaginationComponent",
};

import { defineComponent, ref, onMounted } from "vue";
import type { ComponentSize } from "element-plus";
import { fetchUsersPage } from "@/api/user/index";
import type { User } from "@/api/user/type";
</script>

<script lang="ts" setup>
const currentPage = ref(1);
const pageSize = ref(5);
const size = ref<ComponentSize>("default");
const background = ref(false);
const disabled = ref(false);
const totalCount = ref(0);
const users = ref<User[]>([]);

const fetchUsers = async () => {
  try {
    const response = await fetchUsersPage(
      currentPage.value - 1,
      pageSize.value
    );
    users.value = response.users;
    totalCount.value = response.totalElements;
  } catch (error) {
    console.error("Error fetching users:", error);
  }
};

onMounted(fetchUsers);

const handleSizeChange = (val: number) => {
  pageSize.value = val;
  fetchUsers();
};

const handleCurrentChange = (val: number) => {
  currentPage.value = val;
  fetchUsers();
};
</script>

<style scoped>
.demo-pagination-block + .demo-pagination-block {
  margin-top: 10px;
}
.demo-pagination-block .demonstration {
  margin-bottom: 16px;
}
</style>
