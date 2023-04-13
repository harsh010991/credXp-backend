package com.credXp.module;

import com.credXp.bundle.HbnBundle;
import com.credXp.service.IUserCardDetailsService;
import com.credXp.service.IUserService;
import com.credXp.service.impl.UserCardDetailsService;
import com.credXp.service.impl.UserService;
import com.google.inject.*;
import org.hibernate.SessionFactory;


public class CredXpModule extends AbstractModule {

    private final HbnBundle hbnBundle;

    public CredXpModule(HbnBundle hbnBundle){
        this.hbnBundle = hbnBundle;
    }

    @Override
    public void configure() {
        bind(SessionFactory.class).toInstance(hbnBundle.getSessionFactory());
        bind(IUserCardDetailsService.class).to(UserCardDetailsService.class);
//        bind(UserCardDetailsService.class).asEagerSingleton();
    }


}
