package com.github.bestheroz.standard.context.logging;

import com.github.bestheroz.standard.common.util.MyMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class RequestLoggingFilter extends CommonsRequestLoggingFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private static final String REQUEST_PARAMETERS = "<{}>{}, parameters={}";

    @Override
    protected void beforeRequest(final HttpServletRequest request, final String message) {
        if (StringUtils.contains(request.getHeader("accept"), "html") || request.getMethod().equalsIgnoreCase("POST")) {
            LOGGER.info(REQUEST_PARAMETERS, request.getMethod(), new UrlPathHelper().getPathWithinApplication(request), MyMapperUtils.writeObjectAsJsonObject(request.getParameterMap()));
        }
    }

    @Override
    protected void afterRequest(final HttpServletRequest request, final String message) {
        // super.afterRequest(request, message);
    }
}
