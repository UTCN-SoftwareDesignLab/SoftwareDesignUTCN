import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allItems() {
    return HTTP.get(BASE_URL + "/fo", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(item) {
    return HTTP.post(BASE_URL + "/fo", item, { headers: authHeader() });
  },
  edit(item) {
    return HTTP.put(BASE_URL + "/fo", item, { headers: authHeader() });
  },
};
