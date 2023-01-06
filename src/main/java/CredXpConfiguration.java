import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredXpConfiguration extends Configuration {
    private int defaultSize;
}
