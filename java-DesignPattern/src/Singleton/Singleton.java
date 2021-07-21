package Singleton;

/**
 * 单例模式
 */
public class Singleton {
    //饿汉式 推荐使用
    public static void main(String[] args) {
        Singleton.getInstance();
    }
    private static Singleton instance=null;
    private Singleton(){

    }
    public static Singleton getInstance(){
        System.out.println("调用了单例模式");
        if(instance==null){
            instance = new Singleton();
        }
        return instance;
    }
}
