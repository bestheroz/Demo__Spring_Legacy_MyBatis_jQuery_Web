package com.github.bestheroz.standard.context.filter.https;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

public class HttpsRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletResponse response = null;

    public HttpsRequestWrapper(final HttpServletRequest request) {
        super(request);
        // TODO Auto-generated constructor stub
    }

    public void setResponse(final HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public HttpSession getSession() {
        final HttpSession session = super.getSession();
        this.processSessionCookie(session);
        return session;
    }

    @Override
    public HttpSession getSession(final boolean create) {
        final HttpSession session = super.getSession(create);
        this.processSessionCookie(session);
        return session;
    }

    private void processSessionCookie(final HttpSession session) {
        if (null == this.response || null == session) {
            return;
        }

        final Object cookieOverWritten = this.getAttribute("COOKIE_OVERWRITTEN_FLAG");
        if (null == cookieOverWritten && this.isSecure() && this.isRequestedSessionIdFromCookie()) {
            final Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(-1);
            final String contextPath = this.getContextPath();
            if (StringUtils.isNotEmpty(contextPath)) {
                cookie.setPath(contextPath);
            } else {
                cookie.setPath("/");
            }
            this.response.addCookie(cookie);
            this.setAttribute("COOKIE_OVERWRITTEN_FLAG", "true");
        }
    }
}