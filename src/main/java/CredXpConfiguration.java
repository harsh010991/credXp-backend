import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredXpConfiguration extends Configuration {

    @JsonProperty("database")
    private DataSourceFactory dataSourceFactory;
}
