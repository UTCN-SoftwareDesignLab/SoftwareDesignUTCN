import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allItems() {
    return HTTP.get(BASE_URL + "/fo", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
};
