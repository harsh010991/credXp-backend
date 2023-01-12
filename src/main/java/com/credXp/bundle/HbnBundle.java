package com.credXp.bundle;

import com.credXp.bean.LoginInfo;
import com.credXp.config.CredXpConfiguration;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

public class HbnBundle extends HibernateBundle<CredXpConfiguration> {

    public HbnBundle(){
        super(LoginInfo.class);
    }

    @Override
    public PooledDataSourceFactory getDataSourceFactory(CredXpConfiguration credXpConfiguration) {
        return credXpConfiguration.getDataSourceFactory();
    }
}
