package com.github.bestheroz.standard.common.exception;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonExceptionController {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value = {"/common/exception/error.view"})
    public String errorView(final HttpSession session) {
        session.removeAttribute("returnUrl");
        return "/common/exception/error";
    }

    @RequestMapping(value = "/common/exception/error404.view")
    public String error404View(final HttpSession session) {
        session.removeAttribute("returnUrl");
        return "/common/exception/error404";
    }

}
