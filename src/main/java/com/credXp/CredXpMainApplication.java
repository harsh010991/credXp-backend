package com.credXp;

import com.credXp.bundle.HbnBundle;
import com.credXp.config.CredXpConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.credXp.module.CredXpModule;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class CredXpMainApplication extends Application<CredXpConfiguration> {
    public static void main(String[] args) throws Exception {
        new CredXpMainApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<CredXpConfiguration> bootstrap){
        final HbnBundle hibernate = new HbnBundle();
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .printDiagnosticInfo()
                .modules(new CredXpModule(hibernate))
                .build());


    }

    @Override
    public void run(CredXpConfiguration config, Environment env) throws Exception {
        env.healthChecks().register("credXpHealthCheck", new CredXpHealthCheck());
//        env.jersey().register(userResource);

        //login_info
        // id, login_type, personal_details, status, created_at, updated_at;

        //No guest account
        // only indian mobile number supported.

        //user_card_details
        // account_id, card_id, cash_saved, status, created_at, updated_at

        // card_list
        // id, name, status, type, offers
    }

}
