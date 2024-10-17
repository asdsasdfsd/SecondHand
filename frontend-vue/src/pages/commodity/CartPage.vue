<template>
  <div class="cart-container">
    <h2 class="cart-title">Cart List</h2>

    <ul v-if="cart.items.length > 0" class="cart-list">
            <img :src="item.imageUrl" class="cart-product-image" />
            <div class="item-details">
              <h3>{{ item.name }}</h3>
              <p>Price: ${{ item.price }}</p>
              <p>Quantity: {{ item.quantity }}</p>
            </div>
            <!--加减商品数量的按钮-->
            <div class="quantity-controls">
              <el-button @click="decreaseQuantity(item)">-</el-button>
              <span>{{ item.quantity }}</span>
              <el-button @click="increaseQuantity(item)">+</el-button>
            </div>

            <div class="total-price">
              <p>Total for this product: ${{ item.price * item.quantity }}</p>
            </div>
      <!-- </li> -->

      <div class="total-price">
        <h3>Total Price: ${{ cart.getTotalPrice() }}</h3>
      </div>

      <el-button type="danger" @click="clearCart">Clear Cart</el-button>
    </ul>

    <div v-else>
      <p>Your cart is empty.</p>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "CartPage",
};
</script>

<script lang="ts" setup>
import { inject } from 'vue';

const cart = inject('cart') as any;

const increaseQuantity = (item: any) => {
  cart.increaseQuantity(item);
};

const decreaseQuantity = (item: any) => {
  cart.decreaseQuantity(item);
};

const clearCart = () => {
  cart.clearCart();
};
</script>

<style scoped>
.cart-container {
  padding: 20px;
  background-color: #f0f4ff;
}

.cart-title {
  font-size: 24px;
  margin-bottom: 20px;
  color: #1e3a8a; 
}

.cart-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.cart-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  margin-bottom: 15px;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  background-color: white;
}

.product-image {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 8px;
}

.item-details {
  flex-grow: 1;
  margin-left: 15px;
}

.item-details h3 {
  font-size: 18px;
  margin-bottom: 5px;
  color: #1e3a8a; 
}

.item-details p {
  font-size: 16px;
  color: #4a5568; 
}

.quantity-controls {
  display: flex;
  align-items: center;
}

.quantity-controls span {
  margin: 0 10px;
  font-size: 18px;
}

.el-button {
  background-color: #3b82f6; 
  color: white;
  border-radius: 5px;
}

.el-button:hover {
  background-color: #2563eb;
}

.total-price {
  text-align: right;
  margin-top: 20px;
}

.total-price h3 {
  font-size: 22px;
  color: #1e3a8a; 
}
</style>
