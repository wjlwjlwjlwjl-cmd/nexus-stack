### 1. nexus-mstemplate

* 微服务模板，默认在 18085
* 添加时需要配置 bash 环境变量，指定服务地址、工作空间（bootstrap.yaml）
* 测试各个接口使用，和先集成到 Nacos 服务发现

### 2. nexus-gateway

* 网关服务，默认在 18080 端口
* 需在 Nacos 配置，默认访问模板微服务测试接口加前缀 mstemplate

### 3. nexus-common

封装 Web 开发常用工具类等，目前已做的内容包括：

* nexus-common-core，提供 Bean 复制（部分）、Json 序列化与反序列化、字符串校验、时间戳操作

* nexus-common-domain，提供统一状态码、统一返回结果载体类