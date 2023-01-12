package com.credXp;

import com.codahale.metrics.health.HealthCheck;

public class CredXpHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
