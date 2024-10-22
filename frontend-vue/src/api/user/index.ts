import request from "@/utils/request";
import type { ApiResponse, User } from "./type";
import axios from "axios";
enum API {
  UserManagement = "myapi/user/selectPage",
  Login = "myapi/user/login",
  ProductManagement = "myapi/product",
  QueryProducts = "myapi/product/query",
  AddProduct = "myapi/product/add",
}

interface LoginResponse {
  success: boolean;
  token?: string;
  message?: string;
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
  return response;
}

export const fetchProducts = async (page = 1, pageSize = 10): Promise<Product[]> => {
  const response = await request.get<{ data: Product[] }>(API.QueryProducts, {
    params: { page, pageSize },
  });
  return response.data;
};

export const addProduct = async (product: Omit<Product, 'id'>): Promise<Product> => {
  const response = await request.post<Product>(API.AddProduct, product);

  if (response.success) {
    console.log("Product added successfully", response.data);
    return response.data; 
  } else {
    console.error("Failed to add product");
    throw new Error("Failed to add product");
  }
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

  return {
    users: (response.data as any).content,
    totalPages: (response.data as any).totalPages,
    totalElements: (response.data as any).totalElements,
  };
};

