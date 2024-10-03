<template>
    <div class="common-layout">
      <el-container>
        <el-header height="80px">
          <el-row>
            <el-col :span="12"><div class="left-header">
              <el-menu
                class="el-menu-demo"
                mode="horizontal"
                :ellipsis="false"
                router
              >
              <!-- 'router' open router mode -->
                <img src="../images/logo.jpg" class="header-logo">
                <el-menu-item :index="item.path" v-for="item in list" :key="item.path">
                  <span>{{ item.meta.title }}</span>
                </el-menu-item>     
              </el-menu>
            </div>
            </el-col>
            <el-col :span="12"><div class="right-header">
              <el-dropdown trigger="click" @command="handleCommand">
                <span class="el-dropdown-link">
                  User_xxx
                  <el-icon class="el-icon--right">
                    <arrow-down />
                  </el-icon>
                </span>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item command="a">edit</el-dropdown-item>
                    <el-dropdown-item command="b">logout</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
              </div>
            </el-col>
          </el-row>
        </el-header>



        <el-main>
          <router-view></router-view>
        </el-main>




        <el-footer>Footer</el-footer>
      </el-container>
    </div>
  </template>
  
<script lang="ts">
  import { ref } from 'vue'
  import { defineComponent } from 'vue'
  import { useRouter } from "vue-router";


  export default defineComponent({
    name: "HomePage",
    components:{

    },
  });
</script>

<script lang="ts" setup>  
  import { ElMessage } from 'element-plus'
  const router = useRouter()
  console.log(router.getRoutes());
  const list = router.getRoutes().filter(v=>v.meta.isShow)
  console.log(list);

  const handleCommand = (command: string | number | object) => {
    if(command == "a")
    {
      router.push("/user");
    } 
    else if(command == "b"){
      router.push("/login");
    }
    
    
}
  
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
  