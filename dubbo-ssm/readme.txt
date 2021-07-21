第一步:
Zookeeper为dubbo的注册中心，dubbo服务的生产者和消费者都需要在Zookeeper进行注册；
下载zookeeper压缩包并解压；
进入conf目录将 zoo_sample.cfg 改名为 zoo.cfg；
进入bin目录双击zkServer.cmd，若启动成功，则windows单机版zookeeper搭建成功！

第二步:
按照先后顺序，首先启动Zookeeper：zkServer.cmd
然后启动服务生产者测试类，
Dubbo provider start...
最后启动服务消费者的测试类，
hello,哈哈哈
当控制台输出以下结果，则代表成功。