package SingleFactory;

public class FruitShop {
    public static void main(String[] args) {
        SimpleFruitFactory sff = new SimpleFruitFactory();
        FruitShop fs = new FruitShop(sff);
        fs.orderFruit("apple");
    }
    private  SimpleFruitFactory simpleFruitFactory;
    public FruitShop(SimpleFruitFactory simpleFruitFactory){
        this.simpleFruitFactory= simpleFruitFactory;
    }
    public Fruit orderFruit(String type) {
        Fruit fruit = SimpleFruitFactory.createFruit(type);
        fruit.show();
        return fruit;
    }
}
