第一种实现
1.启动write
访问http://localhost:8081/setName
访问http://localhost:8081/getName
在8081中也可以访问到getName
2.启动read
访问http://localhost:8082/getName

第二种实现
读写分离,用jedis实现
启动multisource
访问http://localhost:8081/setName -->用主redis写
访问http://localhost:8081/getName -->用从redis读


注:
启动redisdemo下的6381-6383即可启动主从服务,26381-26383即可启动哨兵服务