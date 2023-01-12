package com.credXp.module;

import com.credXp.bundle.HbnBundle;
import com.credXp.service.impl.GuavaCacheService;
import com.google.inject.*;
import com.credXp.config.CredXpConfiguration;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

import javax.inject.Named;

public class CredXpModule extends AbstractModule {

    private final HbnBundle hbnBundle;

    public CredXpModule(HbnBundle hbnBundle){
        this.hbnBundle = hbnBundle;
    }

    @Override
    public void configure() {
//        binder.bind(IUserService.class).to(UserService.class);
//        binder.bind(ILoginInfoDao.class).to(LoginInfoDao.class);
//        binder.bind(UserResource.class);
        bind(SessionFactory.class).toInstance(hbnBundle.getSessionFactory());
    }

//    @Provides
//    @Singleton
//    @Named("sessionFactory")
//    public SessionFactory provideSessionFactory(){
//        return hibernate.getSessionFactory();
//    }
}
