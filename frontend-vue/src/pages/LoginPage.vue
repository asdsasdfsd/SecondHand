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
        </form>
      </div>

      <div class = "gradient-box">
        <h3>Welcome to Second Hand!</h3>
        <p>Log in to continue shopping.</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { loginUser } from "@/api/user";
import { ref } from "vue";
import { useRouter } from "vue-router";

export default {
  name: "LoginPage",
  setup() {
    const username = ref("");
    const password = ref("");
    const router = useRouter();

    //因为暂时没和后端连上先注释了API接口使用默认密码登陆
    /*const login = async() => {
      try {
        const response = await loginUser(username.value, password.value);

        if(response.success){
          localStorage.setItem("token", response.token || "");
          router.push("/home");
        } else {
          alert(response.message || "Invalid username or password");
        }
      } catch(error){
        console.error("Login failed:", error);
        alert("An error occurred while logging in.");
      }
    }；*/
    
    //后端联调时注释这段，取消注释上一段
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

    return {
      username,
      password,
      login,
    };
  },
};
</script>

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
  width: 50%; /* Take up the other half of the space */
  background: linear-gradient(to left, #2fb2ae, #5ecbf6); /* Gradient colors */
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
