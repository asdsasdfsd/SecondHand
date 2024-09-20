//axios
import axios from "axios";
import { ElMessage } from "element-plus";

let request = axios.create({
  baseURL: "/",
  timeout: 5000,
});

request.interceptors.request.use((config) => {
  return config;
});

request.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
    let message = "";
    let status = error.response.status;

    switch (status) {
      case 401:
        message = "Token expired";
        break;
      default:
        message = "Other error";
        break;
    }

    ElMessage({
      type: "error",
      message,
    });

    return Promise.reject(error);
  }
);

export default request;
