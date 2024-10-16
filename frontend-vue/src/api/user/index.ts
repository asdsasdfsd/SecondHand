import request from "@/utils/request";
import type { ApiResponse, User } from "./type";

enum API {
  UserManagement = "/api/user/selectPage",
  Login = "/api/user/login",
  ProductManagement = "/api/products/selectPage",
}

interface LoginResponse {
  success: boolean;
  token?: string;
  message?: string;
}

interface UsersPageResponse {
  users: User[];
  totalPages: number;
  totalElements: number;
}

export interface Product {
  id: number;
  amount: number;
  owner: string;
  ownerRating: number;
  name: string;
  description: string;
  price: number;
  imageUrl: string;
  releaseDate: string;
}

export const loginUser = async (username: string, password: string): Promise<LoginResponse> => {
  const response = await request.post<LoginResponse>(API.Login, {
    username,
    password,
  });

  //example: {success: true/false, token: "auth-token", message: "error or success message"}
  return response.data;
}

export const fetchProducts = async (): Promise<Product[]> => {
  const response = await request.get<{ data: Product[] }>(API.ProductManagement);
  return response.data;
};


export const fetchUsersPage = async (
  pageNum = 0,
  pageSize = 10
): Promise<UsersPageResponse> => {
  const response = await request.post<ApiResponse<User>>(
    API.UserManagement,
    null,
    {
      params: {
        pageNum,
        pageSize,
      },
    }
  );

  console.log(response);

  return {
    users: (response.data as any).content,
    totalPages: (response.data as any).totalPages,
    totalElements: (response.data as any).totalElements,
  };
};

