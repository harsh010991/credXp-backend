package com.credXp.module;

import com.credXp.bundle.HbnBundle;
import com.credXp.dao.ILoginInfoDao;
import com.credXp.dao.impl.LoginInfoDao;
import com.credXp.service.IGuavaCacheService;
import com.credXp.service.IUserService;
import com.credXp.service.impl.GuavaCacheService;
import com.credXp.service.impl.UserService;
import com.google.inject.*;
import com.credXp.config.CredXpConfiguration;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

import javax.inject.Named;
import javax.ws.rs.ext.Provider;

public class CredXpModule extends AbstractModule {

    private final HbnBundle hbnBundle;

    public CredXpModule(HbnBundle hbnBundle){
        this.hbnBundle = hbnBundle;
    }

    @Override
    public void configure() {
        bind(SessionFactory.class).toInstance(hbnBundle.getSessionFactory());
    }


}
