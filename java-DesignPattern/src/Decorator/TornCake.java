package Decorator;

public class TornCake extends Pancake {

    public TornCake() {
        desc = "手抓饼";
    }

    @Override
    public double price() {
        return 4;
    }

}
