<template>
  <div class="common-layout">
    <el-container>
      <el-header height="80px">
        <el-row>
          <el-col :span="12"><div class="left-header">
            <el-menu
              class="el-menu-demo"
              mode="horizontal"
              default-active="/commodity"
              :ellipsis="false"
              router
            >
            <!-- 'router' open router mode -->
              <img src="../images/logo.jpg" class="header-logo">
              <el-menu-item :index="item.path" v-for="item in list" :key="item.path">
                <span>{{ item.meta.title }}</span>
              </el-menu-item>     
            </el-menu>
          </div>
          </el-col>
          <el-col :span="12"><div class="right-header">
            <el-dropdown trigger="click" @command="handleCommand">
              <span class="el-dropdown-link">
                User_xxx
                <el-icon class="el-icon--right">
                  <arrow-down />
                </el-icon>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item command="a">edit</el-dropdown-item>
                  <el-dropdown-item command="b">logout</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
            </div>
          </el-col>
        </el-row>
      </el-header>

      <el-main>
        <!-- <el-row :gutter="20">
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
        </el-row> -->
        
        <!--调试时取消注释这段，注释上面的那段-->
        <!---
        <el-row :gutter="20">
          <el-col :span="6" v-for="product in products" :key="product.id">
            <el-card class="product-card">
              <img :src="product.imageUrl" class="product-image" />
              <div class="product-info">
                <h3 @click="openProductDetails(product)">{{ product.name }}</h3>
                <p>Amount: {{ product.amount }}</p>
                <p><strong>${{ product.price }}</strong></p>
                <el-button type="primary" @click="addToCart(product)">Add to Cart</el-button>
              </div>
            </el-card>
          </el-col>
        </el-row>
      -->
        <router-view></router-view>
      </el-main>

      <!--悬浮窗，调试时取消注释这段-->
      <!--
      <el-dialog
        :visible.sync="isDialogVisible"
        width="400px"
        :before-close="handleClose"
      >
        <template #title>
          <h3>Sample Product 1</h3>
        </template>
        <div>
          <img :src="selectedProduct?.imageUrl" class="dialog-product-image" />
          <p>Description: {{ selectedProduct?.Description }}</p>
          <p>Amount: {{ product.amount }}</p>
          <p><strong>Price: ${{ selectedProduct?.price }}</strong></p>
          <p><strong>Owner: {{ selectedProduct?.owner }}</strong></p>
          <p>Owner Rating: {{ selectedProduct?.ownerRating }}</p>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="isDialogVisible = false">Close</el-button>
        </span>
      </el-dialog>
    -->

      <el-footer><div class="footer_class">
        <span class="footer_font">Copyright SecondHand is Powered by</span>
        <span class="footer_team4"> team4</span>
      </div></el-footer>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, inject } from 'vue';
import { useRouter } from "vue-router";
// import { ElMessage } from 'element-plus';
// import { fetchProducts, Product } from "@/api/user";

// const products = ref<Product[]>([]);
// const selectedProduct = ref<Product | null>(null);
// const isDialogVisible = ref(false);
// const cart = inject('cart') as any;

// const fetchAndSortProducts = async () => {
//   const fetchedProducts = await fetchProducts();
//   products.value = fetchedProducts.sort((a, b) => {
//     return new Date(b.releaseDate).getTime() - new Date(a.releaseDate).getTime();
//   });
// };

// onMounted(() => {
//   fetchAndSortProducts();
// });

// const addToCart = (product: Product) => {
//   cart.addToCart(product);
// };

const router = useRouter();
console.log(router.getRoutes());

const list = router.getRoutes().filter(v => v.meta.isShow);
console.log(list);

const isAdmin = ref(history.state.isAdmin);
console.log(isAdmin.value);

const handleCommand = (command: string | number | object) => {
  if (command === "a") {
    if (isAdmin.value) {
      router.push("/admin"); 
    } else {
      router.push("/user");  
    }
  } else if (command === "b") {
    router.push("/login"); 
  }
};
// //点击商品名打开悬浮窗显示商品细节，之后若要加评分功能也加在悬浮窗里
// const openProductDetails = (product: Product) => {
//   selectedProduct.value = product;
//   isDialogVisible.value = true;
// };

// const handleClose = () => {
//   isDialogVisible.value = false;
// };

</script>

<style scoped>
.el-header {
  height: 80px;
  .header-logo {
    height: 80px;
  }
  .left-header {
    text-align: left;
    height: 80px;
    line-height: 80px;
  }
  .right-header {
    text-align: right;
    height: 80px;
    line-height: 80px;
    .el-dropdown {
      height: 40px;
      line-height: 80px;
      margin-right: 10px;
    }
  }
}
.footer_class {
  text-align: center;
  background-color: rgb(90, 156, 248);
  .footer_font {
    color: white;
  }
  .footer_team4 {
    color: rgb(149, 115, 86);
  }
}

.dialog-product-image {
  width: 100%;
  height: auto;
  margin-bottom: 20px;
}

.dialog-footer {
  text-align: right;
}
</style>
