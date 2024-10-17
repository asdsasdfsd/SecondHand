<template>
    <div>
      <el-row :gutter="20">
          <el-col :span="6">
            <el-card class="product-card">
              <img src="https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2" class="product-image" />
              <div class="product-info">
                <h3>Sample Product 1</h3>
                <p><strong>$999</strong></p>
                <el-button type="primary">Add to Cart</el-button>
              </div>
            </el-card>
          </el-col>
        </el-row>
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
import { fetchProducts, Product } from "@/api/user";

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

</script>

<style scoped></style>