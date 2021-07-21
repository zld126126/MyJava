package Decorator;

public class MyTest {
    public static void main(String[] args) {
        Pancake tornCake = new TornCake();
        //手抓饼基础价
        System.out.println(String.format("%s ￥%s", tornCake.getDesc(), tornCake.price()));

        Pancake roujiamo = new Roujiamo();
        roujiamo = new FiredEgg(roujiamo);
        roujiamo = new FiredEgg(roujiamo);
        roujiamo = new Ham(roujiamo);
        //我好饿
        System.out.println(String.format("%s ￥%s", roujiamo.getDesc(), roujiamo.price()));
    }

}
