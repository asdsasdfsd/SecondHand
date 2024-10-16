import request from "@/utils/request";
import type { ApiResponse, User } from "./type";
import axios from "axios";
enum API {
  UserManagement = "/api/user/selectPage",
}
// const service = axios.create({
//   baseURL:"http://localhost:7079/api",
//   timeout:5000,
//   headers:{
//     "Content-Type":"application/json;charset=utf-8",
//   }
// })
// export interface UserListForm{
//   userId:number
//   // username:string
//   // password:string
// }
// export class UserList{
//   ruleForm:UserListForm={
//     userId:1,
//     // username:"",
//     // password:"",
//   }
// }


// interface UsersPageResponse {
//   users: User[];
//   totalPages: number;
//   totalElements: number;
// }

// export const fetchUsersPage = async (
//   pageNum = 0,
//   pageSize = 10
// ): Promise<UsersPageResponse> => {
//   const response = await request.post<ApiResponse<User>>(
//     API.UserManagement,
//     null,
//     {
//       params: {
//         pageNum,
//         pageSize,
//       },
//     }
//   );

//   console.log(response);

//   return {
//     users: (response.data as any).content,
//     totalPages: (response.data as any).totalPages,
//     totalElements: (response.data as any).totalElements,
//   };
// };

// service.interceptors.request.use((config)=>{
//   if(localStorage.getItem('token')){
//     config.headers.token = 
//   }
// })
