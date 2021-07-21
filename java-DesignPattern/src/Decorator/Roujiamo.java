package Decorator;

public class Roujiamo extends Pancake {

    public Roujiamo() {
        desc = "肉夹馍";
    }

    @Override
    public double price() {
        return 6;
    }

}