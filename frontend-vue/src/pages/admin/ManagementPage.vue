<template>
  <div class="common-layout">
    <!-- Header -->
    <header class="header">Header</header>

    <!-- Sidebar, Main Content, and Footer in Grid Layout -->
    <aside class="sidebar">
      <ul class="nav-bar">
        <li>
          <router-link to="/admin/users">User Management</router-link>
        </li>
      </ul>
    </aside>

    <!-- Main Content -->
    <main class="main-content">
      <router-view></router-view>
    </main>

    <!-- Footer -->
    <footer class="footer">
      &copy; 2024 xxx Company. All rights reserved.
    </footer>
  </div>
</template>

<script lang="ts">
export default {
  name: "ManagementPage",
};
</script>

<script lang="ts" setup></script>

<style scoped>
.common-layout {
  display: grid;
  grid-template-columns: 200px 1fr;
  grid-template-rows: auto 1fr auto;
  height: 100vh;
}

.header {
  grid-column: 1 / 3;
  grid-row: 1 / 2;
  background-color: #b3c0d1;
  text-align: center;
  padding: 20px;
}

.sidebar {
  grid-column: 1 / 2;
  grid-row: 2 / 4;
  background-color: #d3dce6;
}

.nav-bar {
  list-style-type: none;
  padding-left: 10px;
}

.main-content {
  grid-column: 2 / 3;
  grid-row: 2 / 3;
  padding: 20px;
}

.footer {
  grid-column: 2 / 3;
  grid-row: 3 / 4;
  background-color: #b3c0d1;
  text-align: center;
  padding: 20px;
}
</style>
