import { fileURLToPath, URL } from "node:url";

import { defineConfig } from "vite";
import path from "path";
import vue from "@vitejs/plugin-vue";
import AutoImport from "unplugin-auto-import/vite";
import Components from "unplugin-vue-components/vite";
import { ElementPlusResolver } from "unplugin-vue-components/resolvers";

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
});
