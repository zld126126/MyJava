1.先得有ruby和java环境

2.请先启动redis集群

rediscluster文件夹,依次启动6个cmd

然后运行命令
ruby redis-trib.rb create --replicas 1 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 127.0.0.1:6385

3.集群启动好后,启动本项目application

4.访问http://localhost:8081/setTest
效果页面有true

5.访问http://localhost:8081/getTest
效果页面有true

6.也可以用redis数据库工具看
安装文件都在rediscluster-setup中
