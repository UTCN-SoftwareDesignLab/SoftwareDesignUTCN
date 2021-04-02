<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create item" : "Edit item" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="item.name" label="Name" />
          <v-text-field v-model="item.description" label="Description" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ItemDialog",
  props: {
    item: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.items
          .create({
            name: this.item.name,
            description: this.item.description,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.items
          .edit({
            id: this.item.id,
            name: this.item.name,
            description: this.item.description,
          })
          .then(() => this.$emit("refresh"));
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.item.id;
    },
  },
};
</script>

<style scoped></style>
