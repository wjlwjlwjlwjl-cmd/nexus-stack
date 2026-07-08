# 拉取 mysql 镜像
sudo docker run -it -d --name=mysql-service -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:latest
# 导入 inituser.sql 和 nacos.sql
sudo docker exec -i mysql-service mysql -uroot -p123456 < ../sql/inituser.sql
echo "【创建用户dev，密码：123，初始化 MySQL 表结构】"

sudo docker exec -i mysql-service mysql -uroot -p123456 < ../sql/nacos.sql
echo "【初始化nacos表，添加nacos用户数据】"