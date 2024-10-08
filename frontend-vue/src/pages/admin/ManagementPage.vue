<template>
  <div class="common-layout">
    <el-container>
      <el-header height="80px">
        <!-- <el-row :gutter="20">
          <el-col :span="2"><div class="grid-content ep-bg-purple" />
            <img src="../../images/logo.jpg" class="header-logo">
          </el-col>
          <el-col :span="18"><div class="grid-content ep-bg-purple" /><h2>main header</h2></el-col>
          <el-col :span="4"><div class="grid-content ep-bg-purple" />
          <span class="logout">logout</span></el-col>
        </el-row> -->
        <el-row>
          <el-col :span="12"><div class="left-header">
            <el-menu
              :default-active="activeIndex"
              class="el-menu-demo"
              mode="horizontal"
              :ellipsis="false"
            >
              <img src="../../images/logo.jpg" class="header-logo">
              <el-menu-item index="0">Home</el-menu-item>
              <el-menu-item index="1">Cart</el-menu-item>
              <el-menu-item index="2">About Us</el-menu-item>
              <!-- <el-sub-menu index="3">
                <template #title>Workspace</template>
                <el-menu-item index="2-1">item one</el-menu-item>
                <el-menu-item index="2-2">item two</el-menu-item>
                <el-menu-item index="2-3">item three</el-menu-item>
                <el-sub-menu index="2-4">
                  <template #title>item four</template>
                  <el-menu-item index="2-4-1">item one</el-menu-item>
                  <el-menu-item index="2-4-2">item two</el-menu-item>
                  <el-menu-item index="2-4-3">item three</el-menu-item>
                </el-sub-menu>
              </el-sub-menu> -->
            </el-menu>
          </div>
          </el-col>
          <el-col :span="12"><div class="right-header">
            <el-dropdown trigger="click">
              <span class="el-dropdown-link">
                User_xxx
                <el-icon class="el-icon--right">
                  <arrow-down />
                </el-icon>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item>edit</el-dropdown-item>
                  <el-dropdown-item>logout</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-main>Main</el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </div>
  <!-- 
   1.home 2.cart 3.about us            avatar 1.edit 2.logout
    -->
</template>

<script lang="ts">
export default {
  name: "ManagementPage",
};
</script>

<script lang="ts" setup>
  import { ref } from 'vue'
  import { useRouter } from "vue-router";
  const activeIndex = ref('1')
  const router = useRouter();
</script>

<style scoped>

.el-header{
  height: 80px;
  .header-logo{
  height:80px;
  }
  .left-header{
    text-align: left;
    height: 80px;
    line-height: 80px;
  }
  .right-header{
    text-align: right;
    height: 80px;
    line-height: 80px;
    .el-dropdown{
    height: 40px;
    line-height: 80px;
    margin-right: 10px;
    }
  }
}

</style>
