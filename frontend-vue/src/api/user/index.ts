import request from "@/utils/request";
import type { userResponseData } from "./type";

enum API {
  UserManagement = "/api/user/selectPage",
}

export const manageUser = () =>
  request.get<any, userResponseData>(API.UserManagement);
