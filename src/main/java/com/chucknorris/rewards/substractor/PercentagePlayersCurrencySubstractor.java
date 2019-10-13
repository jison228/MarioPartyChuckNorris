package com.chucknorris.rewards.substractor;

public abstract class PercentagePlayersCurrencySubstractor implements Substractor {
    private double percentage;

    public PercentagePlayersCurrencySubstractor(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public double substract(double amountToSubstract) {
        return amountToSubstract * (percentage / 100);
    }

}
