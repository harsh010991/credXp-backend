package com.credXp.bundle;

import com.credXp.bean.*;
import com.credXp.config.CredXpConfiguration;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

public class HbnBundle extends HibernateBundle<CredXpConfiguration> {

    final String packagePath = LoginInfo.class.getPackage().getName();
    public HbnBundle(){
        super(LoginInfo.class,Card.class, UserCardDetails.class, CardReferral.class, CCLinkedAccount.class);
    }

    @Override
    public PooledDataSourceFactory getDataSourceFactory(CredXpConfiguration credXpConfiguration) {
        return credXpConfiguration.getDataSourceFactory();
    }
}
