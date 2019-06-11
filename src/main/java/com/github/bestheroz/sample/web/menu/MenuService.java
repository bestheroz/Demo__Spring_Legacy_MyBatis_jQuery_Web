package com.github.bestheroz.sample.web.menu;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class MenuService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MenuDAO menuDAO;
    @Autowired
    private HttpSession session;

    public JsonObject getMenuVOObject(final JsonObject param) throws CommonException {
        if (this.session.getAttribute(MySessionUtils.SESSION_VALUE_OF_LOGIN_VO) == null) {
            this.logger.warn(CommonException.EXCEPTION_ERROR_NOT_ALLOWED_MEMBER.getJsonObject().toString());
            throw CommonException.EXCEPTION_ERROR_NOT_ALLOWED_MEMBER;
        }

        JsonObject result = new JsonObject();
        for (MenuVO menuVO : this.menuDAO.getMenuVOList(param)) {
            if (menuVO.getLvl().intValue() == 2) {
                result.add(menuVO.getMenuId().toString(), MyMapperUtils.writeObjectAsJsonElement(menuVO));
            } else if (menuVO.getLvl().intValue() == 3) {
                JsonObject tempJsonObject = result.get(menuVO.getParMenuId().toString()).getAsJsonObject();
                JsonArray children;
                if (tempJsonObject.has("children")) {
                    children = tempJsonObject.get("children").getAsJsonArray();
                } else {
                    children = new JsonArray();
                }
                children.add(MyMapperUtils.writeObjectAsJsonElement(menuVO));
                tempJsonObject.add("children", children);
            }
        }
        return result;
    }
}
