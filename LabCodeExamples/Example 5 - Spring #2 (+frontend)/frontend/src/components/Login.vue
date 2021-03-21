<template>
  <v-container>
    <v-layout row justify-center pt-3>
      <v-flex xs4 class="grey lighten-4">
        <v-container class="text-xs-center">
          <v-card flat>
            <v-card-title primary-title>
              <h4 v-if="inLoginMode">Login</h4>
              <h4 v-else>Register</h4>
            </v-card-title>
            <v-form>
              <v-text-field
                v-if="mode === 'register'"
                prepend-icon="email"
                name="Email"
                label="Email"
                v-model="login.email"
                validate-on-blur
              >
              </v-text-field>
              <v-text-field
                prepend-icon="person"
                name="Username"
                label="Username"
                v-model="login.username"
                validate-on-blur
              ></v-text-field>
              <v-text-field
                prepend-icon="lock"
                name="Password"
                label="Password"
                type="password"
                v-model="login.password"
                validate-on-blur
              ></v-text-field>
              <v-card-actions>
                <v-container>
                  <v-layout v-if="inLoginMode" row justify-center>
                    <v-btn primary large block @click="attemptLogin"
                      >Login</v-btn
                    >
                    <v-btn plain @click="toggleMode">
                      No account? Click to register!
                    </v-btn>
                  </v-layout>

                  <v-layout v-else row justify-center>
                    <v-btn primary large block @click="attemptRegister"
                      >Register</v-btn
                    >
                    <v-btn plain @click="toggleMode">
                      Already registered? Click to login!
                    </v-btn>
                  </v-layout>

                  <v-layout v-if="isLoggedIn" row justify-center>
                    <v-btn @click="logout"> Logout </v-btn>
                  </v-layout>
                </v-container>
              </v-card-actions>
            </v-form>
          </v-card>
        </v-container>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: "HelloWorld",

  data: () => ({
    mode: "login",
    login: {
      email: "",
      username: "username1",
      password: "password",
    },
  }),
  methods: {
    async attemptLogin() {
      await this.$store.dispatch("auth/login", this.login);
      console.log(this.$store.state.auth.user);
    },
    async attemptRegister() {
      let res = await this.$store.dispatch("auth/register", this.login);
      console.log(res);
    },
    toggleMode() {
      this.mode = this.mode === "login" ? "register" : "login";
    },
    logout() {
      this.$store.dispatch("auth/logout");
    },
  },
  computed: {
    isLoggedIn: function () {
      return this.$store.state.auth.status.loggedIn;
    },
    inLoginMode: function () {
      return this.mode === "login";
    },
  },
};
</script>
