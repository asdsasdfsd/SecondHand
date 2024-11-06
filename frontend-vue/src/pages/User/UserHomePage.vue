<template>
  <el-row class="tac">
    <el-col :span="4">
      <h5 class="mb-2">Sub menu</h5>
      <el-menu
        default-active="1"
        class="el-menu-vertical-demo"
        @select="handleSelect"
      >
        <el-menu-item index="1">
          <el-icon><icon-menu /></el-icon>
          <span>Profile editer</span>
        </el-menu-item>
        <el-menu-item index="2">
          <el-icon><icon-menu /></el-icon>
          <span>Forget password</span>
        </el-menu-item>
        <el-menu-item index="3">
          <el-icon><icon-menu /></el-icon>
          <span>My Commodity</span>
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col :span="20">
      <div>
        <!-- menu item 1 -->
        <el-form :model="form" label-width="auto" style="max-width: 1000px" v-if="idx == '1'">
          <el-form-item label="Nickname" style="max-width: 500px">
            <el-input v-model="form.name" />  
          </el-form-item>
          <el-form-item label="Avatar" style="max-width: 500px">
            <el-image style="width: 100px; height: 100px" :src="url" fit="contain" />
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
          <el-form-item>
            <el-button type="primary" @click="onSubmit">Update</el-button>
            <!-- <el-button>Cancel</el-button> -->
          </el-form-item>
        </el-form>

        <!-- menu item 2 -->
        <el-form :model="form" label-width="auto" style="max-width: 350px" v-if="idx == '2'">
          <el-form-item label="Input new password:">
            <el-input type="password" placeholder="Please input password" v-model="form.firstInput" />
          </el-form-item>
          <el-form-item label="Input password again:">
            <el-input type="password" placeholder="Please input password" v-model="form.secondInput" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onUpdatePassword">Update</el-button>
            <!-- <el-button>Cancel</el-button> -->
          </el-form-item>
        </el-form>
        <el-table :data="userCommodityTableData" style="width: 100%" v-if="idx == '3'">
          <el-table-column label="id" width="50">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <!-- <el-icon><timer /></el-icon> -->
                <span style="margin-left: 10px">{{ scope.row.id }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="name" width="180">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <!-- <el-icon><timer /></el-icon> -->
                <span style="margin-left: 10px">{{ scope.row.name }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="description" width="180">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <!-- <el-icon><timer /></el-icon> -->
                <span style="margin-left: 10px">{{ scope.row.description }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="amount" width="180">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <!-- <el-icon><timer /></el-icon> -->
                <span style="margin-left: 10px">{{ scope.row.amount }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="price" width="180">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <!-- <el-icon><timer /></el-icon> -->
                <span style="margin-left: 10px">{{ scope.row.price }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="releaseDate" width="180">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <!-- <el-icon><timer /></el-icon> -->
                <span style="margin-left: 10px">{{ scope.row.releaseDate }}</span>
              </div>
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
        <el-dialog v-model="updateProductDialogVisible" title="update your commodity" width="500">
          <el-form :model="form">
            <el-form-item label="name" style="max-width: 500px">
              <el-input v-model="form.name" />
            </el-form-item>
            <el-form-item label="description" style="max-width: 500px">
              <el-input v-model="form.description" />
            </el-form-item>
            <el-form-item label="amount" style="max-width: 500px">
              <el-input v-model="form.amount" />
            </el-form-item>
            <el-form-item label="price" style="max-width: 500px">
              <el-input v-model="form.price" />
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
      </div>

    </el-col>
    
  </el-row>
</template>

<script lang="ts">
export default {
  name: "UserHomePage",
};
</script>

<script lang="ts" setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
  User,
} from '@element-plus/icons-vue'
import { defineComponent, ref, onBeforeMount, onMounted, reactive } from 'vue'
import { useRouter } from "vue-router";
import axios from 'axios';
import type { ImageProps } from 'element-plus'
import { id } from 'element-plus/es/locales.mjs';
import { symbolName } from 'typescript';
// import { UserList } from '@/api/user';
  const idx = ref("1")

  const handleSelect = (index:string) =>{
    console.log(`clicked menu item : ${index}`);
    idx.value = index
  }

  // store return value
  const form = reactive({
  id:'',
  name: '',
  description: '',
  amount: '',
  price:'',
})

const usernameValue = ref(null);

const fetchData = async() =>{
    // const data = ref<string | null>(null);
    const error = ref<string | null>(null);
    try {
      console.log("end mounted!");
      const response = await axios.get('myapi/user/selectById/1');
      console.log(response);
      form.name = response.data.data.name;
      form.email = response.data.data.email;
      form.phone = response.data.data.phone;
      form.avatar = response.data.data.avatar;
      form.id = response.data.data.id;
      form.password = response.data.data.password;
      console.log(form);
      

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
        }
      );
      console.log('post response:', response.data);
    } catch (error) {
      console.log(error);
    }
  }

  const changePassword = async() =>{
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
          password: form.firstInput,
        }
      );
      console.log('post response:', response.data);
    } catch (error) {
      console.log(error);
    }
  }
const fits = [
  'fill',
  'contain',
  'cover',
  'none',
  'scale-down',
] as ImageProps['fit'][]
const url =
  'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'

onMounted(() =>{
// using API
  fetchData();
  getUserProduct()
  usernameValue.value = localStorage.getItem('username');
  console.log(usernameValue.value);
});

const onSubmit = () => {
  postData();
  console.log('submit!')
}

const onUpdatePassword = () =>{
  console.log(form.firstInput + " " + form.secondInput);
  changePassword();
  console.log("update password!");  
}


interface UserProduct {
  id: number,
  name: string,
  description: string,
  amount: number,
  price: number,
  releaseDate: string
}
const userCommodityTableData = ref<UserProduct>()
const getUserProduct = async() => {
  console.log("getting user product")
  const response = await axios.get('myapi/product/queryByUsername/' + localStorage.getItem('username'));
  userCommodityTableData.value = response.data.data;
  console.log(response.data.data);
  console.log("table:"+userCommodityTableData.value)
}

const handleDelete = (index: number, row: UserProduct) => {
  // console.log(row.id)
  const response = axios.post('myapi/product/delete/' + row.id)
  console.log(response)
  getUserProduct()
}

const updateProductDialogVisible = ref(false);

const handleEdit = (index: number, row: UserProduct) => {
  console.log(index, row)
  updateProductDialogVisible.value = true
  form.id = row.id
  form.name = row.name;
  form.description = row.description;
  form.amount = row.amount;
  form.price = row.price;
  console.log(form)
}

const handleConform = () => {
  updateProductDialogVisible.value = false;
  const response = axios.put('myapi/product/update', {
    id: form.id,
    name: form.name,
    description: form.description,
    amount: form.amount,
    price: form.price,
    owner: localStorage.getItem('username')
  })
  console.log(response);
  window.location.reload();
}

</script>

<style scoped></style>
