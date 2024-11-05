<template>
  <div class="search-container" style="text-align: right;">
    <input style="width: 300px; height: 40px;" class="stxt" type="text" name="keyword" v-model="key" placeholder="Please enter keywords">
    <input style="width: 50px; height: 40px;" type="button" class="sbtn" value="search" @click="searchProduct(key)">
    <input style="width: 50px; height: 40px;" type="button" class="sbtn" value="reset" @click="fetchAndSortProducts()">
  </div>

    <div>
        <el-row :gutter="20">
          <el-col :span="6" v-for="product in products" :key="product.id">
            <el-card class="product-card">
              <img :src="product.imageUrl" class="product-image" />
              <div class="product-info">
                <h3 @click="openProductDetails(product)">{{ product.name }}</h3>
                <p>Amount: {{ product.amount }}</p>
                <p><strong>${{ product.price }}</strong></p>
                <el-button type="primary" @click="addToCart(product)">Add to Favorites</el-button>
              </div>
            </el-card>
          </el-col>
        </el-row>

        <el-dialog
        v-model="isDialogVisible"
        width="400px"
        :before-close="handleClose"
        >
          <template #title>
            <h3>{{ selectedProduct?.name }}</h3>
          </template>
          <div>
            <img :src="selectedProduct?.imageUrl" class="dialog-product-image" />
            <p><strong>Price: ${{ selectedProduct?.price }}</strong></p>
            <p><strong>Owner Rating: {{ selectedProduct?.ownerRating }}</strong></p>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="isDialogVisible = false">Close</el-button>
          </span>
        </el-dialog>
        
    </div>
</template>

<script lang="ts">
export default {
  name: "CommodityPage",
};
</script>

<script lang="ts" setup>
import { ref, onMounted, inject } from 'vue';
import { ElMessage } from 'element-plus';
import {fetchProducts, fetchProductsWithKeywords, Product} from "@/api/user";

const products = ref<Product[]>([]);
const selectedProduct = ref<Product | null>(null);
const isDialogVisible = ref(false);
const cart = inject('cart') as any;

const fetchAndSortProducts = async () => {
  const fetchedProducts = await fetchProducts();
  products.value = fetchedProducts.sort((a, b) => {
    return new Date(b.releaseDate).getTime() - new Date(a.releaseDate).getTime();
  });
};

onMounted(() => {
  fetchAndSortProducts();
});

const addToCart = (product: Product) => {
  cart.addToCart(product);
};

//点击商品名打开悬浮窗显示商品细节，之后若要加评分功能也加在悬浮窗里
const openProductDetails = (product: Product) => {
  selectedProduct.value = product;
  isDialogVisible.value = true;
};

const handleClose = () => {
  isDialogVisible.value = false;
};

const searchProduct = async (key) => {
  if (key === "" || key === undefined) {
    alert("keyword can not be empty");
    return;
  }

  const ProductsWithKeywords = await fetchProductsWithKeywords(key);
  products.value = ProductsWithKeywords.sort((a, b) => {
    return new Date(b.releaseDate).getTime() - new Date(a.releaseDate).getTime();
  });
}


</script>

<style scoped>
.dialog-product-image {
  width: 100%;
  height: auto;
  margin-bottom: 20px;
}

.dialog-footer {
  text-align: right;
}

.search-container {
  display: flex;
  justify-content: flex-end; 
  margin: 20px; 
}

.search-input {
  width: 300px; 
}
</style>