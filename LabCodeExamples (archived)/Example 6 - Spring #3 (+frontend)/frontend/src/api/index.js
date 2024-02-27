import camelCase from "lodash-es";
const requireModule = require.context("./services", false, /\.js$/);
const api = {};

requireModule.keys().forEach((fileName) => {
  if (fileName === "./index.js") return;
  const moduleName = camelCase(fileName.replace(/(\.\/|\.js)/g, ""));
  api[moduleName] = {
    ...requireModule(fileName).default,
  };
});

export default api;
