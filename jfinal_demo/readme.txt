项目启动步骤

1： 使用 blog.sql 中的 sql 语句创建数据库与数据库表

2: 修改 res/a_little_config.txt 文件，填入正确的数据库连接用户名、密码

3: 将项目导入 eclipse。推荐使用 Eclipse IDE for Java EE Developers

4: 打开 com.demo.common包下的 DemoConfig 文件，右键单击该文件并选择 Debug As ---> Java Application。
        其它启动项目的方式见 《JFinal手册》。除此之外，项目还可以与其它普通java web 项目一样使用 tomcat
   jetty 等 web server 来启动，启动方式与非jfinal项目完全一样。

5: 打开浏览器输入  localhost 即可查看运行效果

注意： 请确保您安装了 JavaSE 1.6 或更高版本，tomcat下运行项目需要先删除 jetty-server-xxx.jar，否则会有冲突

6:如果用idea则需要看我的blog文章,idea部署tomcat上启动
https://blog.csdn.net/aaaadong/article/details/82493583

7.自动生成
/jfinal_demo/src/com/demo/common/model/_JFinalDemoGenerator.java
运行就可以

8.启动问题
/jfinal_demo/src/com/demo/common/DemoConfig.java
运行或者直接部署在tomcat上即可

8.启动

