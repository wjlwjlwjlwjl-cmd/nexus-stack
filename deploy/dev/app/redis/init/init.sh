echo "【默认redis.conf路径：/home/diinki/nexus-stack/deploy/dev/app/redis/conf/redis.conf】"

sudo docker run -d \ 
    --name=redis-service \
    -p 6379:6379 \
    -v /home/diinki/nexus-stack/deploy/dev/app/redis/conf/redis.conf:/etc/redis/redis.conf \
    redis:latest redis-server /etc/redis/redis.conf

echo "【拉取并创建 Redis 容器完成，密码：@123】"