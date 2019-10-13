package com.chucknorris.rewards.substractor;

import com.chucknorris.rewards.RewardApplicable;

public interface Substractor extends RewardApplicable {
    double substract(double amountToSubstract);
}
