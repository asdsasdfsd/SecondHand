# 安装需求

- **JDK 17**
- **Maven**
- **Node.js**
- **npm**

# 安装依赖

## 连接数据库

### application.properties

![application.properties](Readme-Image/application.properties.png)

### HeidiSQL

![HeidiSQL](Readme-Image/HeidiSQL.png)

请确保红色方框中的信息一一对应：

- **IP**
- **端口**
- **用户名**（一般为 `root`）
- **密码**

绿色方框中的名称可以修改，但必须在 MySQL 中创建一个相同名字的数据库。运行 Java Spring Boot 应用时，会自动创建空的表（JPA 的 ORM 功能）。

# 后端运行依赖

## Maven

![Maven](Readme-Image/Maven.png)

按上面的配置，Maven 应该可以自动将依赖下载到本地仓库，无需单独下载 Maven。

# 前端运行

### Windows

下载 Node.js：[https://nodejs.org/zh-cn](https://nodejs.org/zh-cn)（记得选 "Add to PATH" 选项）

### macOS

可以使用 Homebrew 安装：

```bash
brew install node
```

命令行直接下载
下载 node.js,会带着 npm
检测下载

```bash
node -v
npm -v
```

安装依赖

```bash
#进入frondend-vue文件夹
npm install
```

运行

```bash
#进入frondend-vue文件夹
npm run dev
#不想用dev可以改下面红框中对应的命令
```

## CLI

![CLI](Readme-Image/CLI.png)

暂时想到这么多，有问题我们再讨论

# 后端结构介绍

## pom.xml - Maven 管理的项目依赖 (目前包括)

- `springboot`: Spring 启动相关
- `springboot-test`: 启动测试
- `JDBC`: 数据库连接
- `JPA`: ORM 支持和持久化支持

## application.properties

- 数据库连接配置
- 端口配置

## java 目录结构

- **common**: 用泛型统一处理 response 数据，简化操作
- **config**: 配置类，用于注入 Bean 工厂 (IOC 容器)，以及解决跨域问题
- **controller**: 表示层 (对应 use case)，处理前端请求
- **service**: 服务层 (商业逻辑)
  - `Impl`: 具体实现
  - `service`: 定义接口
- **repository**: 数据持久化层
- **model**: DAO (JPA 中实体可以直接对应数据库表格)

# 前端结构介绍

- **node_modules**: 项目依赖
- **public & src**: 项目内容

## src 中

- **App.vue**: 主组件
- **main.ts**: 主入口文件
- **文件夹**
  - `api`: API 接口 (文件夹是分类，type.ts 是数据结构，index.ts 是请求方法)
  - `assets`: 静态资源
  - `components`: 组件 (小的，可以在不同界面使用的组件)
  - `pages`: 视图组件 (基本界面和可以路由的界面)
  - `router`: 路由配置 (界面跳转逻辑)
  - `store`: 状态管理
  - `styles`: 样式文件
  - `utils`: 工具函数 (现在有 axios 封装)

## 配置文件

- **env.d.ts** 用于定义 TypeScript 中的环境变量类型
- **index.html**: 项目入口文件
- **package.json & package-lock.json**: npm 管理项目依赖
- **tsconfig.app.json** 针对应用程序部分的 TypeScript 编译器配置文件
- **tsconfig.json** 定义 TypeScript 编译器的全局配置
- **tsconfig.node.json** 针对 Node.js 环境的 TypeScript 编译配置
- **vite.config.ts**: Vite 配置文件(可以换成 webpack)

# 数据库介绍

- **MySQL**
- **HeidiSQL** 数据库客户端工具

```

```
