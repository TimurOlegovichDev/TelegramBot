package io.bot.lightWeightBot.training;

public class calculatorMax {
    private int maxWeight;

    calculatorMax(int weight, int n) {
        this.maxWeight = (int) ((100 * weight) / (101.3 - 2.67133 * n) - 2);
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
}
