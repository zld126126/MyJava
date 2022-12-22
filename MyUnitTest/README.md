## idea安装plugin
>Squaretest

## 引用pom
>spring-boot-starter-test <br/>
junit-jupiter

## 使用
在需要单元测试的java文件中:<br/>
>右键->Generate->Generate Test Confirm Mocks(需要指定Java和JUnit5)<br/>
在生成的XXTest.java如UnitTestControllerTest.java中testUnitTest()开始测试即可...

## 出现问题/解决
测试成功是不会报错的,如果报错了就是测试失败...