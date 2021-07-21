package SingleFactory;

/**
 * 简单水果工厂类
 */
public class SimpleFruitFactory {
    public static Fruit createFruit(String type){
        Fruit fruit = null;
        if(type.equals("apple")){
            fruit = new Apple();
        }else if(type.equals("orange")){
            fruit = new Orange();
        }
        System.out.println("调用了简易工厂模式");
        return fruit;
    }
}
