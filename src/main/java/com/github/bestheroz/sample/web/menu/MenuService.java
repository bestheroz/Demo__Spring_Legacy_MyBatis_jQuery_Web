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
import java.util.Map;

@Service
public class MenuService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MenuDAO menuDAO;

    public JsonArray getMenuVOObject(final JsonObject param, boolean isNotLogined) throws CommonException {
        if (isNotLogined) {
            this.logger.warn(CommonException.EXCEPTION_ERROR_NOT_ALLOWED_MEMBER.getJsonObject().toString());
            throw CommonException.EXCEPTION_ERROR_NOT_ALLOWED_MEMBER;
        }

        JsonObject temp = new JsonObject();
        for (MenuVO menuVO : this.menuDAO.getMenuVOList(param)) {
            if (menuVO.getLvl() == 2) {
                temp.add(menuVO.getMenuId().toString(), MyMapperUtils.writeObjectAsJsonElement(menuVO));
            } else if (menuVO.getLvl() == 3) {
                JsonObject tempJsonObject = temp.get(menuVO.getParMenuId().toString()).getAsJsonObject();
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
        JsonArray result = new JsonArray();
        for(Map.Entry<String, JsonElement> entry : temp.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }
}
