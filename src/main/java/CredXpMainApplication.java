import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.dual.HibernateBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class CredXpMainApplication extends Application<CredXpConfiguration> {
    public static void main(String[] args) throws Exception {
        new CredXpMainApplication().run(args);
    }

    private final HibernateBundle<CredXpConfiguration> hibernate = new HibernateBundle<CredXpConfiguration>(Person.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(CredXpConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

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
