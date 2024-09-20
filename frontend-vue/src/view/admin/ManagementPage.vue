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
          <li><RouterLink to="/admin">Home</RouterLink></li>
          <li><RouterLink to="/admin/users">User Management</RouterLink></li>
          <li>
            <RouterLink to="/admin/products">Product Management</RouterLink>
          </li>
        </ul>
      </nav>

      <!-- Main Content -->
      <main class="main-content">
        <RouterView />
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

<script lang="ts" setup>
import { RouterView, RouterLink } from "vue-router";
</script>

<style scoped>
/* 全局样式 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html,
body {
  height: 100%;
  font-family: Arial, sans-serif;
}

/* Grid 布局 */
.container {
  display: grid;
  grid-template-rows: auto 1fr auto;
  grid-template-columns: 200px 1fr;
  grid-template-areas:
    "header header"
    "nav main"
    "footer footer";
  height: 100vh; /* 视窗高度 */
}

/* Header */
.header {
  grid-area: header;
  background-color: #4caf50;
  color: white;
  padding: 20px;
  text-align: center;
}

/* Navigation (Sidebar) */
.sidebar {
  grid-area: nav;
  background-color: #333;
  padding: 20px;
}

.sidebar ul {
  list-style-type: none;
}

.sidebar li {
  margin-bottom: 10px;
}

.sidebar a {
  color: white;
  text-decoration: none;
  padding: 10px;
  display: block;
}

.sidebar a:hover {
  background-color: #575757;
  border-radius: 4px;
}

/* Main Content */
.main-content {
  grid-area: main;
  background-color: #f4f4f4;
  padding: 20px;
}

/* Footer */
.footer {
  grid-area: footer;
  background-color: #333;
  color: white;
  text-align: center;
  padding: 10px;
}
</style>
