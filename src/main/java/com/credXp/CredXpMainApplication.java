package com.credXp;

import com.credXp.bundle.HbnBundle;
import com.credXp.config.CredXpConfiguration;
import com.credXp.filter.IdentifierFilter;
import com.credXp.filter.UserIdentifierFeature;
import com.google.inject.Inject;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.credXp.module.CredXpModule;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class CredXpMainApplication extends Application<CredXpConfiguration> {

//    @Inject
//    private IdentifierFilter identifierFilter;
    public static void main(String[] args) throws Exception {
        new CredXpMainApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<CredXpConfiguration> bootstrap){
        final HbnBundle hibernate = new HbnBundle();
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .modules(new CredXpModule(hibernate))
                .build());
    }

    @Override
    public void run(CredXpConfiguration config, Environment env) throws Exception {
        env.healthChecks().register("credXpHealthCheck", new CredXpHealthCheck());
//        env.servlets().addFilter("identifierFilter", identifierFilter).addMappingForUrlPatterns(null, true, "/*");
        env.jersey().register(UserIdentifierFeature.class);
//        env.jersey().register(userResource);

        //login_info
        // id, login_type, personal_details, status, created_at, updated_at;

        //No guest account
        // only indian mobile number supported.


        // card_list
        // id, name, status, type, offers

        // todo : finding offers json to store and processed.

    }

}
