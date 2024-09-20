<template>
  <div class="container">
    <!-- Header -->
    <header class="header">
      <h1>Header</h1>
    </header>

    <!-- Navigation and Main Content -->
    <div class="main-content-wrapper">
      <!-- Navigation -->
      <nav class="sidebar">
        <ul>
          <li><router-link to="/admin/users">User Management</router-link></li>
        </ul>
      </nav>

      <!-- Main Content -->
      <main class="main-content">
        <router-view></router-view>
      </main>
    </div>

    <!-- Footer -->
    <footer class="footer">
      <p>&copy; 2024 Your Company. All rights reserved.</p>
    </footer>
  </div>
</template>

<script lang="ts">
export default {
  name: "ManagementPage",
};
</script>

<script lang="ts" setup></script>

<style scoped></style>
