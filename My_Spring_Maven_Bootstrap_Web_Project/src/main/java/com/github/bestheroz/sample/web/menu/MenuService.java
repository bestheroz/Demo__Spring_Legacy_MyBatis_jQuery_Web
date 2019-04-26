package com.github.bestheroz.sample.web.menu;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.google.gson.JsonObject;

@Service
public class MenuService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MenuDAO menuDAO;
    @Autowired
    private HttpSession session;

    public List<MenuVO> getMenuVOList(final JsonObject param) throws CommonException {
        if (this.session.getAttribute(MySessionUtils.SESSION_VALUE_OF_LOGIN_VO) == null) {
            this.logger.warn(CommonException.EXCEPTION_ERROR_NOT_ALLOWED_MEMBER.getJsonObject().toString());
            throw CommonException.EXCEPTION_ERROR_NOT_ALLOWED_MEMBER;
        }
        return this.menuDAO.getMenuVOList(param);
    }
}
