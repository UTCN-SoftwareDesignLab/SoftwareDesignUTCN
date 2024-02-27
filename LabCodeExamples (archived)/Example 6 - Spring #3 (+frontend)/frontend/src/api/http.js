import axios from "axios";

export const BASE_URL = "http://localhost:8088/api";
export const HTTP = axios.create({
  baseURL: BASE_URL,
});

export function encodeParams(p) {
  const queryParamsObject = {};
  for (const key of Object.keys(p)) {
    const filterItem = p[key];
    queryParamsObject[key] = filterItem.toString();
  }
  return queryParamsObject;
}

HTTP.interceptors.response.use(
  function (response) {
    if (response && response.config && response.config.data) {
      if (typeof response.config.data === "string") {
        try {
          response.config.data = JSON.parse(response.config.data);
        } catch (e) {
          /* intentionally left blank */
        }
      }
    }
    return response;
  },
  function (error) {
    return Promise.reject(error);
  }
);
