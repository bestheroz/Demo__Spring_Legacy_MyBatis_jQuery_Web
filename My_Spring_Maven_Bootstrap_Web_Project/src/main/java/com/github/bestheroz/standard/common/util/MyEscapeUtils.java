package com.github.bestheroz.standard.common.util;

import org.apache.commons.lang3.StringUtils;

import com.nhncorp.lucy.security.xss.XssPreventer;
import com.nhncorp.lucy.security.xss.XssSaxFilter;

public class MyEscapeUtils {
    protected MyEscapeUtils() {
        throw new UnsupportedOperationException();
    }

    // private static final Logger LOGGER = LoggerFactory.getLogger(MyFilterUtil.class);
    private static final XssSaxFilter XSS_SAX_FILTER = XssSaxFilter.getInstance("my-lucy-xss-sax.xml", true);

    public static String escapeAll(final String dirty) {
        return StringUtils.isNotEmpty(dirty) ? escapeXss(unescapeAll(dirty)) : dirty;
    }

    private static String escapeXss(final String dirty) {
        return XssPreventer.escape(XssPreventer.unescape(XSS_SAX_FILTER.doFilter(dirty)));
    }

    public static String unescapeAll(final String clean) {
        return StringUtils.isNotEmpty(clean) ? unescapeXss(unescapeForSecurity(clean)) : clean;
    }

    private static String unescapeXss(final String clean) {
        return XssPreventer.unescape(clean);
    }

    private static String unescapeForSecurity(final String clean) {
        return clean.replaceAll("&#35;", "#").replaceAll("&#40;", "\\(").replaceAll("&#41;", "\\)").replaceAll("&#", "&&#35;");
    }
}
