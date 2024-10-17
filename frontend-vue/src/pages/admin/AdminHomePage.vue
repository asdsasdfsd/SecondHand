<template>
  <div>
    <el-row :gutter="20">
    <el-col :span="6"><div class="grid-content ep-bg-purple">
      <h5 class="mb-2">Sub menu</h5>
      <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
      @select="handleSelect"
      >
        <el-menu-item index="1">
          <el-icon><icon-menu /></el-icon>
          <span>User Management</span>
        </el-menu-item>
        <el-menu-item index="2">
          <el-icon><icon-menu /></el-icon>
          <span>Commodity Management</span>
        </el-menu-item>
      </el-menu>
    </div></el-col>
    <el-col :span="18"><div class="grid-content ep-bg-purple">
      <span>Search id: </span>
      <el-input v-model="inputId" style="width: 240px" placeholder="Please input id" />
      <el-button type="primary" @click="searchId">Search(id)  </el-button>
      <el-input v-model="inputName" style="width: 240px" placeholder="Please input name" />
      <el-button type="primary" @click="searchName">Search(name)  </el-button>
      <el-button type="primary" @click="resetAll">Reset  </el-button>
      <el-table :data="tableData" style="width: 100%" v-if="idx == '1'">
    <el-table-column label="Id" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <!-- <el-icon><timer /></el-icon> -->
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="Name" width="180">
      <template #default="scope">
        <el-popover effect="light" trigger="hover" placement="top" width="auto">
          <template #default>
            <div>name: {{ scope.row.name }}</div>
            <div>address: {{ scope.row.address }}</div>
            <div>username: {{ scope.row.username }}</div>
            <div>email: {{ scope.row.email }}</div>
            <div>phone: {{ scope.row.phone }}</div>
            <div>role: {{ scope.row.role }}</div>
            <div>avatar: {{ scope.row.avatar }}</div>
          </template>
          <template #reference>
            <el-tag>{{ scope.row.name }}</el-tag>
          </template>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column label="Operations">
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
          Edit
        </el-button>
        <el-button
          size="small"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)"
        >
          Delete
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog v-model="updateDialogVisible" title="Shipping address" width="500">
    <el-form :model="form">
      <el-form-item label="Nickname" style="max-width: 500px">
            <el-input v-model="form.name" />  
          </el-form-item>
          <el-form-item label="Avatar" style="max-width: 500px">
            <el-image style="width: 100px; height: 100px" :src="form.avatar" fit="contain" />
          </el-form-item>
          <el-form-item label="Email" style="max-width: 500px">
            <el-input v-model="form.email" />  
          </el-form-item>
          <el-form-item label="Phone" style="max-width: 500px">
            <el-input v-model="form.phone" />  
          </el-form-item>
          <el-form-item label="Role">
            <el-radio-group v-model="form.role">
              <el-radio value="0">User</el-radio>
              <el-radio value="1">Admin</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="password" style="max-width: 500px">
            <el-button type="primary" @click="resetPassword">Reset Password</el-button>
          </el-form-item>
      <!-- <el-form-item label="Zones" :label-width="formLabelWidth">
        <el-select v-model="form.region" placeholder="Please select a zone">
          <el-option label="Zone No.1" value="shanghai" />
          <el-option label="Zone No.2" value="beijing" />
        </el-select>
      </el-form-item> -->
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="updateDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="handleConform">
          Confirm
        </el-button>
      </div>
    </template>
  </el-dialog>
    </div></el-col>
    </el-row>
  </div>
    

</template>
  
  <script lang="ts">
  export default {
    name: "AdminHomePage",
  };
  
  </script>
  
<script lang="ts" setup>
  import {reactive, ref, onMounted, computed} from 'vue';
  import axios from 'axios';
import { User } from '@element-plus/icons-vue';
import { ta } from 'element-plus/es/locales.mjs';
import { get } from 'node_modules/axios/index.cjs';
  interface User {
  // date: string
  name: string
  // address: string
  id: string
  username: string
  email: string
  phone: string
  role: string
  avatar: string
  }
  const tableData = ref<User>();



  const idx = ref("1");
  const handleSelect = (key: string, keyPath: string[]) => {
    idx.value = key;
    console.log(key, keyPath)
  }

  const updateDialogVisible = ref(false);
  const form = reactive({
    name: '',
    email: '',
    phone: '',
    role: '',
    username: '',
    avatar: '',
    id: 0,
    password: '',
  })

  const getAllUser = async() =>{
    // const data = ref<string | null>(null);
    const error = ref<string | null>(null);
    try {
      console.log("end mounted!");
      const response = await axios.get('myapi/user/selectAll');
      tableData.value = response.data.data;
      console.log(response.data.data);
    } catch (error) {
      console.log(error);
    }
  }
  const getUserById = async(id: string) =>{
    // const data = ref<string | null>(null);
    const error = ref<string | null>(null);
    try {
      console.log("end mounted!");
      const response = await axios.get('myapi/user/selectById/' + id);
      form.id = response.data.data.id;
      form.username = response.data.data.username;
      form.name = response.data.data.name;
      form.phone = response.data.data.phone;
      form.email = response.data.data.email;
      form.role = response.data.data.role;
      form.avatar = response.data.data.avatar;
      form.password = response.data.data.password;
      // form.avatar
      console.log(response.data.data);

    } catch (error) {
      console.log(error);
    }
  }

  const postData = async() =>{
    try {
      console.log("postData!");
      const response = await axios.put('myapi/user/update', 
        {
          id: form.id,
          name: form.name,
          avatar: form.avatar,
          email: form.email,
          phone: form.phone,
          role: form.role,
          password: form.password,
          username: form.username,
        }
      );
      console.log('post response:', response.data);
    } catch (error) {
      console.log(error);
    }
  }

  const resetPassword = async() =>{
    try {
      console.log("postData!");
      const response = await axios.put('myapi/user/update', 
        {
          id: form.id,
          name: form.name,
          avatar: form.avatar,
          email: form.email,
          phone: form.phone,
          role: form.role,
          password: "123456",
          username: form.username,
        }
      );
      console.log('post response:', response.data);
    } catch (error) {
      console.log(error);
    }
  }


  const formLabelWidth = '140px';
  const handleEdit = (index: number, row: User) => {
    console.log(index, row)
    updateDialogVisible.value = true
    getUserById(row.id);
  }

  const handleDelete = (index: number, row: User) => {
    console.log(index, row)
  }

  const handleConform = () => {
    updateDialogVisible.value = false;
    postData();
  }

  //input search
  const inputId = ref('');
  const inputName = ref('');
  
  const searchId = () => {
    // console.log(inputId.value);
    let temp = tableData.value.find(user => user.id == inputId.value);
    tableData.value = [];
    tableData.value.push(temp);

  }
  const searchName = () => {
    console.log(inputName.value);
    let temp = tableData.value.find(user => user.name == inputName.value);
    tableData.value = [];
    tableData.value.push(temp);

  }

  const resetAll = () => {
    getAllUser();
    inputId.value = '';
    inputName.value = '';
  }

  
  onMounted(() => {
    getAllUser();
  })

</script>
  
  <style scoped></style>
  