package com.github.bestheroz.standard.common.taglibrary;

import com.github.bestheroz.sample.web.login.LoginVO;
import com.github.bestheroz.sample.web.menu.MenuService;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyAccessBeanUtils;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class MenuTag extends TagSupport {
    private static final long serialVersionUID = -8260051129668642050L;
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuTag.class);
    public static final String MENU_TAG = "MENU_TAG";

    @Override
    public int doStartTag() throws JspException {
        final HttpSession session = ((HttpServletRequest) this.pageContext.getRequest()).getSession();

        try {
//            if (MySessionUtils.getAttribute(session, MENU_TAG) == null) {

//            final Document doc = Jsoup.parseBodyFragment(bodyHtml);
//                doc.select("a.navbar-brand").attr("href", CommonCode.CONTEXT_PATH);
            final StringBuilder bodyHtml = new StringBuilder(1024 * 100);
            final LoginVO loginVO = MySessionUtils.getLoginVO(session);
            if (loginVO != null) {
//                    doc.select("#menu_memberNm").html(loginVO.getMemberNm().concat(" 님(").concat(loginVO.getMemberId()).concat(")"));
//                    doc.select("#a_logout").attr("onclick", "MyCommon.goLink('".concat(CommonCode.CONTEXT_PATH).concat("/sample/login/logout.view');"));
                final JsonObject param = new JsonObject();
                final Integer memberTyp = Integer.parseInt(loginVO.getMemberTyp());
                if (memberTyp != null && memberTyp.intValue() >= 800) {
                    param.addProperty("power", memberTyp);
//                        this.drawMenuData(doc, param);

                    bodyHtml.append("<script> const menuData = " + MyMapperUtils.writeObjectAsString(MyAccessBeanUtils.getBean(MenuService.class).getMenuVOObject(param, session)) + "</script>");
                }
                bodyHtml.append(IOUtils.toString(new File(session.getServletContext().getRealPath("/WEB-INF/views/common/menu.html")).toURI(), StandardCharsets.UTF_8));
//                }
                MySessionUtils.setAttribute(session, MENU_TAG, bodyHtml.toString());
            }
            this.pageContext.getOut().print(session.getAttribute(MENU_TAG));
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
        return SKIP_PAGE;
    }

//    private void drawMenuData(final Document doc, final JsonObject param) throws JspException {
//        final List<MenuVO> menuList = MyAccessBeanUtils.getBean(MenuService.class).getMenuVOObject(param);
//        for (final MenuVO menuVO : menuList) {
//            String url = StringUtils.defaultString(menuVO.getUrl());
//            url = StringUtils.startsWith(url, "http") ? url : CommonCode.CONTEXT_PATH.concat(url);
//            final String menuId = String.valueOf(menuVO.getMenuId());
//            final String parMenuId = String.valueOf(menuVO.getParMenuId());
//            final String newParMenuId = parMenuId.concat(menuId);
//            switch (menuVO.getLvl().intValue()) {
//                case 1:
//                    doc.select(".navbar-brand").attr("href", "javascript:void(0);").attr("onclick", "MyCommon.goLink('".concat(url).concat("');"));
//                    break;
//                case 2:
//                    doc.select("#nav_top").append("<li id=\"".concat(newParMenuId).concat("\"></li>"));
//                    doc.select("#".concat(newParMenuId))
//                            .append("<a href=\"javascript:void(0);\" aria-haspopup=\"true\"><i class=\"fa\" aria-hidden=\"true\"></i>&nbsp;".concat(menuVO.getMenuNm()).concat("</a>"));
//                    if (StringUtils.equals(menuVO.getMenuTyp(), "G")) {
//                        doc.select("#".concat(newParMenuId)).addClass("dropdown");
//                        doc.select("#".concat(newParMenuId).concat(">a")).addClass("dropdown-toggle").attr("data-toggle", "dropdown").append("&nbsp;<span class=\"caret\"></span>");
//                        doc.select("#".concat(newParMenuId)).append("<ul id=\"".concat(menuId).concat("\" class=\"dropdown-menu\" role=\"menu\"></ul>"));
//                    } else {
//                        doc.select("#".concat(newParMenuId).concat(">a")).attr("onclick", "MyCommon.goLink('".concat(url).concat("');"));
//                    }
//                    break;
//                default:
//                    doc.select("#".concat(parMenuId)).append("<li id=\"".concat(newParMenuId).concat("\"></li>"));
//                    doc.select("#".concat(newParMenuId))
//                            .append("<a href=\"javascript:void(0);\" aria-haspopup=\"true\"><i class=\"fa\" aria-hidden=\"true\"></i>&nbsp;".concat(menuVO.getMenuNm()).concat("</a>"));
//                    if (StringUtils.equals(menuVO.getMenuTyp(), "G")) {
//                        doc.select("#".concat(newParMenuId)).addClass("dropdown-submenu");
//                        doc.select("#".concat(newParMenuId).concat(">a")).addClass("group-menu");
//                        doc.select("#".concat(newParMenuId)).append("<ul id=\"".concat(menuId).concat("\" class=\"dropdown-menu\" role=\"menu\"></ul>"));
//                    } else {
//                        doc.select("#".concat(newParMenuId).concat(">a")).attr("onclick", "MyCommon.goLink('".concat(url).concat("');"));
//                        doc.select("#".concat(newParMenuId).concat(">a")).addClass("dropdown-toggle").attr("role", "button");
//                    }
//                    break;
//            }
//        }
//    }
}
