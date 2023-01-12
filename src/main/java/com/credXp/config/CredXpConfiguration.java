package com.credXp.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;
import ru.vyarus.dropwizard.guice.module.support.ConfigurationAwareModule;
import ru.vyarus.dropwizard.guice.module.yaml.bind.Config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CredXpConfiguration extends Configuration{

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory(){
        return database;
    }

    @JsonProperty
    @Config
    private GuavaCacheConfig guavaCacheConfig;

}
