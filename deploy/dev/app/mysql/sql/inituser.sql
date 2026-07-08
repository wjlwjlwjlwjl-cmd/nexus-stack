-- 1. 创建两个业务库，带反引号包裹含横杠库名
CREATE DATABASE IF NOT EXISTS `nexus-stack_nacos_dev` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `nexus-stack_dev` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 2. 创建远程访问用户 dev，密码 @123
CREATE USER IF NOT EXISTS 'dev'@'%' IDENTIFIED BY '@123';

-- 3. 授予主从复制权限（如需binlog同步才保留，单机可删除这行）
GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'dev'@'%';

-- 4. 给dev分配两个库全部权限，库名必须加反引号 `` ` ``
GRANT ALL PRIVILEGES ON `nexus-stack_nacos_dev`.* TO 'dev'@'%';
GRANT ALL PRIVILEGES ON `nexus-stack_dev`.* TO 'dev'@'%';

-- 刷新权限生效
FLUSH PRIVILEGES;