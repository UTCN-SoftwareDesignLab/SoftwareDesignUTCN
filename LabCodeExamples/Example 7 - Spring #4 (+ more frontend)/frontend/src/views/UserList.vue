<template>
  <v-container> USER LIST </v-container>
</template>

<script>
import api from "../api";

export default {
  name: "UserList",
  methods: {

  },
  async created() {
    console.log(
        await api.users.allUsers()
    )
  }
};
</script>

<style scoped></style>
