# 🏥 医小助 - 智能医疗助手

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-green?style=for-the-badge&logo=spring)](https://spring.io/projects/spring-boot)
[![Vue 3](https://img.shields.io/badge/Vue-3.5.13-4FC08D?style=for-the-badge&logo=vue.js)](https://vuejs.org)
[![LangChain4j](https://img.shields.io/badge/LangChain4j-1.0.0--beta6-orange?style=for-the-badge)](https://langchain4j.dev)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)

> 基于 RAG（检索增强生成）技术的智能医疗助手系统，使用大语言模型为用户提供医疗咨询服务。

---

## ✨ 特性

| 功能 | 描述 |
|------|------|
| 🧠 **智能问答** | 基于 RAG 技术，结合医疗知识库提供准确的医疗咨询服务 |
| ⚡ **流式响应** | 支持 SSE 流式输出，实时返回回答内容 |
| 💬 **多轮对话** | 基于 MongoDB 实现会话历史管理 |
| 📅 **工具调用** | 集成预约挂号功能，支持智能预约 |
| 🔍 **向量检索** | 使用 Redis 进行高效的语义检索 |

---

## 🛠 技术栈

### 后端

<div align="center">

| 技术 | 版本 | 用途 |
|------|------|------|
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="20" height="20"/> Java | 17 | 编程语言 |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="20" height="20"/> Spring Boot | 3.5.0 | Web 框架 |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redis/redis-original.svg" width="20" height="20"/> Redis | - | 向量存储 |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mongodb/mongodb-original.svg" width="20" height="20"/> MongoDB | - | 聊天历史 |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" width="20" height="20"/> MySQL | - | 预约数据 |

</div>

- **LangChain4j** - LLM 应用开发框架
- **阿里云 DashScope** - 通义千问 Qwen3.5-plus
- **MyBatis-Plus** - ORM 框架

### 前端

<div align="center">

| 技术 | 版本 | 用途 |
|------|------|------|
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/vuejs/vuejs-original.svg" width="20" height="20"/> Vue | 3.5.13 | 前端框架 |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/vitejs/vitejs-original.svg" width="20" height="20"/> Vite | 5.4.8 | 构建工具 |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/typescript/typescript-original.svg" width="20" height="20"/> Element Plus | 2.8.4 | UI 组件 |

</div>

---

## 📁 项目结构

```
MedicalAgent/
│
├── 📂 MedicalAgent_backend/              # 后端服务
│   ├── 📂 src/main/java/com/ai/medicalAgent/
│   │   ├── 🤖 assistant/                # AI 智能体定义
│   │   ├── ⚙️ config/                   # 配置类
│   │   ├── 🎮 controller/               # 控制器
│   │   ├── 📦 entity/                   # 实体类
│   │   ├── 🗄️ mapper/                   # 数据访问层
│   │   ├── 🧩 service/                  # 业务逻辑
│   │   ├── 💾 store/                    # 存储实现
│   │   └── 🔧 tools/                    # 工具类
│   └── 📂 src/main/resources/
│       └── 📂 content/                   # 知识库文档
│           ├── 📄 md/                    # Markdown 文档
│           └── 📑 pdf/                   # PDF 文档
│
└── 📂 MedicalAgent_frontend/             # 前端应用
    └── 📂 src/
        └── 📂 components/                # Vue 组件
```

---

## 🚀 快速开始

### 1️⃣ 环境要求

- Java 17+
- Node.js 16+
- Maven 3.8+
- MySQL 8.0+
- MongoDB 6.0+
- Redis 7.0+

### 2️⃣ 克隆项目

```bash
git clone https://github.com/your-repo/MedicalAgent.git
cd MedicalAgent
```

### 3️⃣ 后端配置

在 `MedicalAgent_backend/src/main/resources/application.properties` 中配置：

```properties
# 阿里云 DashScope API
langchain4j.open-ai.chat-model.api-key=your_api_key
langchain4j.open-ai.chat-model.model-name=qwen3.5-plus

# MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/medicalAgent_chat_memory_db

# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/medicalagent
spring.datasource.username=root
spring.datasource.password=root

# Redis
langchain4j.community.redis.host=your_redis_host
langchain4j.community.redis.port=6379
```

### 4️⃣ 启动服务

```bash
# 🚦 后端启动
cd MedicalAgent_backend
mvn spring-boot:run

# 🌐 前端启动
cd MedicalAgent_frontend
npm install
npm run dev
```

---

## 📡 API 接口

### 对话接口

| 项目 | 说明 |
|------|------|
| 🔗 URL | `/medicalAgent/chat` |
| 📝 方法 | `POST` |
| 📤 类型 | `text/stream;charset=utf-8` |

#### 请求参数

```json
{
  "memoryId": 1,
  "message": "请问口腔科在几楼？"
}
```

| 参数 | 类型 | 必填 | 描述 |
|------|------|------|------|
| `memoryId` | Long | ✅ | 会话 ID |
| `message` | String | ✅ | 用户消息 |

---

## 📚 知识库

系统内置以下医疗知识文档：

| 文档 | 描述 |
|------|------|
| 🏥 医院信息 | 医院基本信息、地址、联系方式 |
| 🏠 科室信息 | 各科室介绍（口腔科、神经内科等） |
| 🤖 人工智能 | AI 在医疗领域的应用 |

> 📂 知识库位于 `MedicalAgent_backend/src/main/resources/content/` 目录，支持 Markdown 和 PDF 格式。

---

## 📷 预览

> 暂无截图

---

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

---

## 📄 License

MIT License - see [LICENSE](LICENSE) for details.

---

<div align="center">

⭐️ Stars | 🔀 Forks | 📌 Issues

</div>
