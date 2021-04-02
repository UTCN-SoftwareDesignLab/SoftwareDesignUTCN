<template>
  <v-card color="grey lighten-4" flat tile>
    <v-toolbar>
      <v-app-bar-nav-icon></v-app-bar-nav-icon>

      <v-toolbar-title>Lab example</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon @click="logout">
        <v-icon>logout</v-icon>
      </v-btn>
    </v-toolbar>
  </v-card>
</template>

<script>
import router from "../router";

export default {
  name: "TopBar",
  methods: {
    logout() {
      this.$store.dispatch("auth/logout");
      router.push("/");
    },
  },
};
</script>

<style scoped></style>
