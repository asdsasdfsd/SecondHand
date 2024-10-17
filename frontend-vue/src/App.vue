<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script lang="ts">
import { reactive, provide } from 'vue';
import type { Product } from "@/api/user";

interface CartItem extends Product {
  quantity: number;
}

export default {
  name: "App",

  setup() {
    const cart = reactive({
      items: [] as CartItem[],
      totalPrice: 0,

      addToCart(product: Product) {
        const existingItem = this.items.find(item => item.id === product.id);
        if (existingItem) {
          if (existingItem.quantity < product.amount) {
            existingItem.quantity += 1;
          }
        } else {
          this.items.push({ ...product, quantity: 1 });
        }
        this.calculateTotalPrice();
      },

      increaseQuantity(product: CartItem) {
        if (product.quantity < product.amount) {
          product.quantity += 1;
        }
        this.calculateTotalPrice();
      },

      decreaseQuantity(product: CartItem) {
        if (product.quantity > 1) {
          product.quantity -= 1;
        }
        this.calculateTotalPrice();
      },

      clearCart() {
        this.items = [];
        this.totalPrice = 0;
      },

      calculateTotalPrice() {
        this.totalPrice = this.items.reduce((sum, item) => sum + (item.price * item.quantity), 0);
      },

      getTotalPrice() {
        return this.totalPrice;
      }
    });

    provide('cart', cart);
  }
};
</script>

<style scoped></style>
