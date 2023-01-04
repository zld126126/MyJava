package com.dongtech.myexcel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

@SpringBootApplication
public class MyExcelApplication {
    private static final Logger logger = LoggerFactory.getLogger(MyExcelApplication.class);

    public static void main(String[] args) {
        //记录启动程序耗时
        StopWatch sw = new StopWatch("DongTech-MyExcel");
        sw.start("启动耗时：");

        SpringApplication.run(MyExcelApplication.class, args);
        runOk();

        sw.stop();
        logger.info(sw.getId() + " " + sw.getLastTaskName() + sw.getLastTaskTimeMillis() + "ms");
    }

    public static void runOk(){
        logger.info("\n" +
                "////////////////////////////////////////////////////////////////////  \n" +
                "//                          _ooOoo_                               //  \n" +
                "//                         o8888888o                              //      \n" +
                "//                         88\" . \"88                              //      \n" +
                "//                         (| ^_^ |)                              //      \n" +
                "//                         O\\  =  /O                              //  \n" +
                "//                      ____/`---'\\____                           //                          \n" +
                "//                    .'  \\\\|     |//  `.                         //  \n" +
                "//                   /  \\\\|||  :  |||//  \\                        //      \n" +
                "//                  /  _||||| -:- |||||-  \\                       //  \n" +
                "//                  |   | \\\\\\  -  /// |   |                       //  \n" +
                "//                  | \\_|  ''\\---/''  |   |                       //          \n" +
                "//                  \\  .-\\__  `-`  ___/-. /                       //          \n" +
                "//                ___`. .'  /--.--\\  `. . ___                     //      \n" +
                "//              .\"\" '<  `.___\\_<|>_/___.'  >'\"\".                  //  \n" +
                "//            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |                 //      \n" +
                "//            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /                 //  \n" +
                "//      ========`-.____`-.___\\_____/___.-`____.-'========         //      \n" +
                "//                           `=---='                              //  \n" +
                "//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //  \n" +
                "//         佛祖保佑       永无BUG     永不修改     DongTech          //  \n" +
                "////////////////////////////////////////////////////////////////////\n");
    }
}
