package com.credXp.filter;

import com.credXp.annotations.RequiredAuthIdentifier;
import com.google.inject.Inject;
import com.google.inject.Provides;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class UserIdentifierFeature implements DynamicFeature {
    @Inject
    private IdentifierFilter identifierFilter;
    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        if (resourceInfo.getResourceMethod().getAnnotation(RequiredAuthIdentifier.class) != null) {
            context.register(identifierFilter);
        }
    }
}
