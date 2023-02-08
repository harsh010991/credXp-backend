package com.credXp.filter;

import com.credXp.constants.CredXpConstants;
import com.credXp.service.IUserService;
import com.credXp.service.impl.GuavaCacheService;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.credXp.constants.CredXpConstants.ACCOUNT_ID;
import static com.credXp.constants.CredXpConstants.AUTH_TOKEN;
import static com.credXp.constants.ErrorConstants.INVALID_AUTH_TOKEN;
import static com.credXp.constants.LoggerConstant.INVALID_AUTH_TOKEN_ERROR;

@Slf4j
public class IdentifierFilter implements ContainerRequestFilter {

    @Inject
    private IUserService userService;

    @Inject
    private GuavaCacheService guavaCacheService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        List<String> relaxedUrls = Arrays.asList(CredXpConstants.RELAXED_URL);
        String[] urlParts = containerRequestContext.getUriInfo().getPath().split("/");
        if(!relaxedUrls.contains(urlParts[urlParts.length-1])) {
            String authToken = containerRequestContext.getHeaderString(AUTH_TOKEN);
            if (StringUtils.isBlank(authToken) || !userService.validateSessionToken(authToken)) {
                log.error(INVALID_AUTH_TOKEN_ERROR, authToken);
                throw new WebApplicationException(INVALID_AUTH_TOKEN, Response.Status.UNAUTHORIZED);
            }
            List<String> headerValue = new LinkedList<>();
            headerValue.add(String.valueOf(guavaCacheService.getLoginCachePojo(authToken).getAccountId()));
            containerRequestContext.getHeaders().put(ACCOUNT_ID, headerValue);
        }
    }
}
