import axios from "axios";

export const BASE_URL = "http://localhost:8088/api";
export const HTTP = axios.create({
  baseURL: BASE_URL,
});

export default function authHeader() {
  let user = JSON.parse(localStorage.getItem("user"));
  if (user && user.token) {
    return { Authorization: "Bearer " + user.token };
  } else {
    return {};
  }
}
