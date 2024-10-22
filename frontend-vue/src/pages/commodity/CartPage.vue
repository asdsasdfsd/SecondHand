<template>
  <div class="cart-container">
    <h2 class="cart-title">Favorites List</h2>

    <div v-if="cart.items.length > 0">
      <el-row :gutter="20" v-for="item in cart.items" :key="item.id">
        <el-col :span="24">
          <el-card>
            <img :src="item.imageUrl" class="cart-product-image" />
            <h3 class="product-name">{{ item.name }}</h3>
            <p class="product-price">Price: ${{ item.price }}</p>
            <p>Owner: {{ item.owner }}</p>

            <div class="quantity-control">
              <el-button @click="decreaseQuantity(item)" class="quantity-button">-</el-button>
              <span>{{ item.quantity }}</span>
              <el-button @click="increaseQuantity(item)" class="quantity-button">+</el-button>
            </div>

            <p class="total-item-price">Total for this product: ${{ item.price * item.quantity }}</p>
          </el-card>
        </el-col>
      </el-row>

      <div class="total-price">
        <h3>Total Price: ${{ cart.getTotalPrice() }}</h3>
      </div>

      <el-button type="danger" @click="clearCart" class="clear-cart-button">Clear Favorites</el-button>
    </div>

    <div v-else>
      <p>Your Favorites is empty.</p>
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
  background-color: #f9f9f9;
}

.cart-title {
  color: #3b5998;
  font-size: 1.5rem;
  margin-bottom: 20px;
}

.product-name {
  color: #2a5298;
  font-weight: bold;
}

.product-price, .total-item-price {
  color: #4b8fe2;
}

.quantity-control {
  display: flex;
  align-items: center;
}

.quantity-button {
  background-color: #4a90e2;
  border-radius: 5px;
  color: white;
}

.quantity-button:hover {
  background-color: #357abd;
}

.total-price h3 {
  color: #1c468e;
  font-size: 1.8rem;
  text-align: right;
}

.clear-cart-button {
  background-color: #d9534f;
  color: white;
}

.clear-cart-button:hover {
  background-color: #c9302c;
}

.cart-product-image {
  width: 100px;
  height: auto;
  margin-right: 20px;
}
</style>
