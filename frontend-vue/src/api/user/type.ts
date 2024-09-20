interface user {
  id: number;
  username: string;
  password: string;
  name: string;
  phone: string;
  email: string;
  avatar: string;
  role: string;
}

export interface userResponseData {
  code: number;
  data: user;
}
