# 1、初始化数据库：创建nacos外接数据库frameworkjava_nacos_dev和脚手架业务数据库frameworkjava_dev
# 2、创建用户，用户名：dev 密码：@123
# 3、授予dev用户特定权限

CREATE database if NOT EXISTS `frameworkjava_nacos_prd` default character set utf8mb4 collate utf8mb4_general_ci;
CREATE database if NOT EXISTS `frameworkjava_prd` default character set utf8mb4 collate utf8mb4_general_ci;

CREATE USER 'dev'@'%' IDENTIFIED BY '@123';
grant replication slave, replication client on *.* to 'dev'@'%';

GRANT ALL PRIVILEGES ON frameworkjava_nacos_prd.* TO  'dev'@'%';
GRANT ALL PRIVILEGES ON frameworkjava_prd.* TO  'dev'@'%';

FLUSH PRIVILEGES;
