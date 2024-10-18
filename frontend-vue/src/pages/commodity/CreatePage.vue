<template>
  <div class="create-page">
    <el-card class="box-card create-form">
      <h2>Create New Product</h2>
      
      <el-form :model="productForm" ref="productFormRef" label-width="120px" class="form-box">
        <el-form-item label="Product Name" prop="name" :rules="[{ required: true, message: 'Please input the product name', trigger: 'blur' }]">
          <el-input v-model="productForm.name" placeholder="Enter product name"></el-input>
        </el-form-item>

        <el-form-item label="Amount" prop="amount" :rules="[{ required: true, message: 'Please input the product amount', trigger: 'blur' }]">
          <el-input-number v-model="productForm.amount" :min="1"></el-input-number>
        </el-form-item>

        <el-form-item label="Description" prop="description">
          <el-input v-model="productForm.description" type="textarea" placeholder="Enter product description"></el-input>
        </el-form-item>

        <el-form-item label="Price" prop="price" :rules="[{ required: true, message: 'Please input the product price', trigger: 'blur' }]">
          <el-input-number v-model="productForm.price" :min="0" :step="0.01"></el-input-number>
        </el-form-item>

        <el-form-item label="Product Image" prop="image">
          <el-upload
            class="upload-demo"
            action="/api/product/add"
            list-type="picture-card"
            :on-success="handleImageSuccess"
            :on-remove="handleImageRemove"
            :file-list="fileList"
            :limit="1"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
          <img v-if="productForm.imageUrl" :src="productForm.imageUrl" class="preview-image" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm" class="create-button">Submit</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import { addProduct } from "@/api/user"; 

interface UploadResponse {
  url: string; 
}

interface FileItem {
  name: string;
  url: string; 
}

const storedUsername = localStorage.getItem("username");

const productForm = ref({
  name: '',
  amount: 1,
  description: '',
  price: 0,
  imageUrl: '',
  owner: storedUsername || 'user123', 
});

const productFormRef = ref(null);
const fileList = ref<FileItem[]>([]);

const handleImageSuccess = (response: any, file: any) => {
  productForm.value.imageUrl = response.url; // Assume backend returns image URL
  fileList.value = [file];
};

const handleImageRemove = () => {
  productForm.value.imageUrl = '';
  fileList.value = [];
};

const submitForm = async () => {
  if (!productForm.value.imageUrl) {
    ElMessage.error('Please upload an image');
    return;
  }

  const formRef = productFormRef.value as any;
  formRef.validate(async (valid: boolean) => {
    if (valid) {
      try {
        const newProduct = await addProduct({
          name: productForm.value.name,
          amount: productForm.value.amount,
          description: productForm.value.description,
          price: productForm.value.price,
          imageUrl: productForm.value.imageUrl,
          owner: localStorage.getItem("username") as string,
          ownerRating: 0, // 默认评分
          releaseDate: new Date().toISOString(),
        });

        ElMessage.success('Product created successfully');
        formRef.resetFields();
        fileList.value = [];
        productForm.value.imageUrl = ''; // Clear image URL after successful creation
      } catch (error) {
        ElMessage.error('Failed to create product');
        console.error(error);
      }
    } else {
      ElMessage.error('Please complete the form');
    }
  });
};
</script>

<style scoped>
.create-page {
  padding: 20px;
  display: flex;
  justify-content: center;
}

.create-form {
  width: 500px;
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}

.form-box {
  margin-bottom: 20px;
}

.preview-image {
  width: 100%;
  height: auto;
  margin-top: 10px;
}

.box-card {
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.create-button {
  width: 100%;
  padding: 15px;
  font-size: 16px;
  color: white;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>
