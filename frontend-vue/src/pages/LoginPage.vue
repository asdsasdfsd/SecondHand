<template>
  <div class="login-page">
    <div class = "login-content">
      <div class="login-container">
        <h2>Sign in</h2>
        <form @submit.prevent="login">
          <div class = "input-group">
            <input
            type = "text"
            v-model = "username"
            placeholder="Your Username"
            required
            />
          </div>
          <div class="input-group">
            <input
            type="password"
            v-model="password"
            placeholder="Your Password"
            required
            />
          </div>
          <button type="submit" class="login-button">Login</button>
          <el-divider />
          <h3>Join now</h3>
          <button @click="handleRegister" class="login-button">Register</button>
        </form>
      </div>

      <div class = "gradient-box">
        <h3>Welcome to Second Hand!</h3>
        <p>Log in to continue shopping.</p>
      </div>
    </div>
    <div class="dialog">
      <el-dialog
      v-model="dialogVisible"
      title="Register"
      width="500"
      :before-close="handleClose"
      >
      <!-- <span>This is a message</span> -->
      <el-form :model="form" label-width="auto" style="max-width: 600px">
        <el-form-item label="User name">
          <el-input v-model="form.username" />
        </el-form-item>
        <el-form-item label="Password">
          <el-input
          v-model="form.firstInput"
          style="width: 240px"
          type="password"
          placeholder="Please input password"
          />
        </el-form-item>
        <el-form-item label="Confirm password">
          <el-input
          v-model="form.secondInput"
          style="width: 240px"
          type="password"
          placeholder="Please input password again"
          />
        </el-form-item>
        <el-form-item label="Email">
          <el-input v-model="form.email" />
        </el-form-item> 
        <el-form-item label="Phone number">
          <el-input v-model="form.phone" />
        </el-form-item>

      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="handleConfirm">
            Confirm
          </el-button>
        </div>
      </template>
    </el-dialog>

    </div>
  </div>
</template>

<script lang="ts">
import { loginUser } from "@/api/user";
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import { ElMessage } from 'element-plus'
import type { PhoneFilled } from "@element-plus/icons-vue";

export default {
  name: "LoginPage",
  setup() {
    const username = ref("");
    const password = ref("");
    const router = useRouter();
    
    const login = async() => {
      try {
        const response = await loginUser(username.value, password.value);

        if(response.success){
          localStorage.setItem("token", response.data.token || "");
          localStorage.setItem("username", username.value);

          const role = response.data.user.role;
          if(role == 1){
            router.push({
              path:"/home",
              state:{isAdmin:true}
            });
          } else {
            router.push({
              path:"/home",
              state:{isAdmin:false}
            });
          }
        } else {
          alert(response.message || "Invalid username or password");
        }
      } catch(error){
        console.error("Login failed:", error);
        alert("An error occurred while logging in.");
      }
    };
    
    /*
    const login = () => {
      if (username.value === "admin" && password.value === "admin") {
        router.push({
          path:"/home",
          state:{isAdmin:true}
        });
      }
      else if(username.value === "user" && password.value === "user")
      {
        router.push({
          path:"/home",
          state:{isAdmin:false}
        });
      } else {
        alert("Invalid username or password");
      }
    };
    */

    //新增注册功能
    const dialogVisible = ref(false);
    const handleRegister = () => {
      dialogVisible.value = true;
      // console.log(dialogVisible);
    }
    const handleClose = () => {
      dialogVisible.value = false;
    }

    const form = reactive({
      username: '',
      firstInput: '',
      secondInput: '',
      password: '',
      email: '',
      phone: '',
    })

    const postData = async() =>{
    try {
      console.log("register user: ");
      if(form.firstInput != form.secondInput){
        ElMessage({
          showClose: true,
          message: 'Sorry, the password are not inconsistent.',
          type: 'error',
        })
        return ;
      }
      else{
        form.password = form.firstInput;
      }
      const response = await axios.post('myapi/user/register', 
        {
          username: form.username,
          email: form.email,
          phone: form.phone,
          role: 0,
          password: form.password,
          // name: form.username,
        }
      );
      console.log('post response:', response.data);
    } catch (error) {
      console.log(error);
    }
    };

    const handleConfirm = () => {
      postData();
      dialogVisible.value = false;
    };
    return {
      username,
      password,
      login,
      dialogVisible,
      handleRegister,
      handleClose,
      form,
      postData,
      handleConfirm,
    };
  },
};
</script>

<!-- <script lang="ts" setup>
  
</script> -->

<style scoped>
.login-page{
  background-color: #f5f5f5bf; 
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-content{
  display: flex;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  width: 100%;
  max-width: 700px;
}

.login-container {
  padding: 40px;
  width: 50%;
  box-sizing: border-box;
  text-align: center;
}

h2 {
  font-size: 36px;
  margin-bottom: 20px;
}

.input-group{
  margin-bottom: 20px;
}

input{
  width: 100%;
  padding: 15px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

input:focus{
  outline: none;
  border-color: #007bff;
}

.login-button{
  width: 100%;
  padding: 15px;
  font-size: 16px;
  color: white;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.login-button:hover {
  background-color: #0056b3;
}

.gradient-box {
  width: 50%; 
  background: linear-gradient(to left, #2fb2ae, #5ecbf6); 
  color: white;
  padding: 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.gradient-box h3 {
  font-size: 1.8em;
  margin-bottom: 10px;
}

.gradient-box p {
  font-size: 1.2em;
}

</style>
