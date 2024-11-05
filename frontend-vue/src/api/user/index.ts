import request from "@/utils/request";
import type { ApiResponse, User } from "./type";
import axios from "axios";
enum API {
  UserManagement = "myapi/user/selectPage",
  Login = "myapi/user/login",
  ProductManagement = "myapi/product",
  QueryProducts = "myapi/product/query",
  AddProduct = "myapi/product/add",
  QueryProductsWithKeyWord = "myapi/product/queryWithKeyword",
}

interface LoginResponse {
  data: any;
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
  const response = await axios.post<LoginResponse>(API.Login, {
    username,
    password,
  });

  //example: {success: true/false, token: "auth-token", message: "error or success message"}
  return response.data;
}

export const fetchProducts = async (page = 1, pageSize = 10): Promise<Product[]> => {
  const response = await axios.get<{ data: Product[] }>(API.QueryProducts, {
    params: { page, pageSize },
  });
  return response.data.data;
};

export const fetchProductsWithKeywords = async (key : string): Promise<Product[]> => {
  const response = await axios.get<{ data: Product[] }>(API.QueryProductsWithKeyWord, {
    params: { key },
  });
  return response.data.data;
};

export const addProduct = async (product: Omit<Product, 'id'>): Promise<Product> => {
  const response = await axios.post<Product>(API.AddProduct, product);
  if (response.data.success) {
    console.log("Product added successfully", response.data.data);
    return response.data.data;
  } else {
    console.error("Failed to add product");
    throw new Error("Failed to add product");
  }
};

export const fetchUsersPage = async (
  pageNum = 0,
  pageSize = 10
): Promise<UsersPageResponse> => {
  const response = await axios.post<ApiResponse<User>>(
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

