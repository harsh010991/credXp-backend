import resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import java.util.ArrayList;
import java.util.List;

public class CredXpMainApplication extends Application<CredXpConfiguration> {
    public static void main(String[] args) throws Exception {
        new CredXpMainApplication().run(args);
    }

    @Override
    public void run(CredXpConfiguration config, Environment env) throws Exception {
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
