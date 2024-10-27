import { fileURLToPath, URL } from "node:url";

import path from "path";
import vue from "@vitejs/plugin-vue";
import AutoImport from "unplugin-auto-import/vite";
import Components from "unplugin-vue-components/vite";
import { ElementPlusResolver } from "unplugin-vue-components/resolvers";
import { defineConfig } from 'vitest/config';

export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
      dts: path.resolve(__dirname, "types/auto-imports.d.ts"),
    }),
    Components({
      resolvers: [ElementPlusResolver()],
      dts: path.resolve(__dirname, "types/components.d.ts"),
    }),
  ],
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url)),
    },
  },
  server: {
    proxy: {
      '/myapi': {
        target: 'http://localhost:7079/api',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/myapi/, '')
      }
    },
  },
  test: {
    environment: 'jsdom',
    css: true,

  },
  build: {
    rollupOptions: {
      external: ['axios'],  // 添加 axios 到外部依赖
    },
  },
});
