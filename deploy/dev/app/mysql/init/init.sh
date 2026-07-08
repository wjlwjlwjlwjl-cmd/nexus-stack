# 拉取 mysql 镜像并创建容器
sudo docker run -d --name=mysql-service -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:latest
echo "【创建用户root，密码：123456】"
# 导入 inituser.sql 和 nacos.sql
sudo docker exec -i mysql-service mysql -uroot -p123456 < ../sql/inituser.sql
echo "【创建用户dev，密码：123，初始化 MySQL 表结构】"

sudo docker exec -i mysql-service mysql -uroot -p123456 < ../sql/nacos.sql
echo "【初始化nacos表，添加nacos用户数据】"