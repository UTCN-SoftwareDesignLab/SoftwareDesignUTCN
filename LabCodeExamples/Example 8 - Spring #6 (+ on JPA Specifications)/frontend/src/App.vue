<template>
  <v-app>
    <v-main>
      <v-container fluid>
        <TopBar v-if="isLoggedIn"></TopBar>
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import TopBar from "./components/TopBar";
export default {
  name: "App",
  components: { TopBar },
  data: () => ({
    //
  }),
  computed: {
    isLoggedIn: function () {
      return this.$store.state.auth.status.loggedIn;
    },
  },
};
</script>
