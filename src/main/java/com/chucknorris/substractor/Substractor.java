package com.chucknorris.substractor;

import com.chucknorris.rewards.RewardApplicable;

public interface Substractor extends RewardApplicable {
    double substract(double amountToBeSubstract);
}
