package com.github.bestheroz.standard.common.util;

import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.github.bestheroz.sample.web.login.LoginVO;
import com.github.bestheroz.standard.common.constant.CommonCode;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MyTestUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyTestUtils.class);

	protected MyTestUtils() {
		throw new UnsupportedOperationException();
	}

	private static LoginVO loginedVO = null;
	private static MockMvc mockMvc = null;

	public static void setMockMvc(final MockMvc mockMvc) {
		MyTestUtils.mockMvc = mockMvc;
	}

	public static ResultActions performGet(final String url) {
		return performGet(url, null, false);
	}

	public static ResultActions performGetLogined(final String url) {
		return performGet(url, null, true);
	}

	public static ResultActions performGet(final String url, final Object params) {
		return performGet(url, params, false);
	}

	public static ResultActions performGetLogined(final String url, final Object params) {
		return performGet(url, params, true);
	}

	private static ResultActions performGet(final String url, final Object params, final boolean isLogined) {
		try {
			final MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(StringUtils.startsWith(url, CommonCode.CONTEXT_PATH) ? url : CommonCode.CONTEXT_PATH + url);
			if (isLogined) {
				setLoginedSession(mockHttpServletRequestBuilder);
			}
			for (final Entry<String, JsonElement> entry : MyMapperUtils.writeObjectAsJsonObject(params).entrySet()) {
				mockHttpServletRequestBuilder.param(entry.getKey(), String.valueOf(entry.getValue()));
			}
			final ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
			MyTestUtils.logReponse(resultActions.andReturn());
			return resultActions;
		} catch (final Throwable e) {
			LOGGER.warn(ExceptionUtils.getStackTrace(e));
			throw new CommonException(e);
		}
	}

	public static ResultActions performPost(final String url) {
		return performPost(url, null, false);
	}

	public static ResultActions performPostLogined(final String url) {
		return performPost(url, null, true);
	}

	public static ResultActions performPost(final String url, final Object params) {
		return performPost(url, params, false);
	}

	public static ResultActions performPostLogined(final String url, final Object params) {
		return performPost(url, params, true);
	}

	private static ResultActions performPost(final String url, final Object params, final boolean isLogined) {
		try {
			final MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post(StringUtils.startsWith(url, CommonCode.CONTEXT_PATH) ? url : CommonCode.CONTEXT_PATH + url);
			if (isLogined) {
				setLoginedSession(mockHttpServletRequestBuilder);
			}
			for (final Entry<String, JsonElement> entry : MyMapperUtils.writeObjectAsJsonObject(params).entrySet()) {
				mockHttpServletRequestBuilder.param(entry.getKey(), entry.getValue().getAsString());
			}
			final ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
			MyTestUtils.logReponse(resultActions.andReturn());
			return resultActions;
		} catch (final Throwable e) {
			LOGGER.warn(ExceptionUtils.getStackTrace(e));
			throw new CommonException(e);
		}
	}

	private static void setLoginedSession(final MockHttpServletRequestBuilder mockHttpServletRequestBuilder) {
		if (loginedVO != null) {
			mockHttpServletRequestBuilder.sessionAttr(MySessionUtils.SESSION_VALUE_OF_LOGIN_VO, loginedVO);
			mockHttpServletRequestBuilder.sessionAttr("memberId", loginedVO.getMemberId());
			mockHttpServletRequestBuilder.sessionAttr("memberNm", loginedVO.getMemberNm());
			mockHttpServletRequestBuilder.sessionAttr("memberTyp", loginedVO.getMemberTyp());
		}
	}

	public static void doLogined(final LoginVO loginVO) {
		loginedVO = loginVO;
	}

	public static void logReponse(final MvcResult mvcResult) {
		try {
			final String requestURI = mvcResult.getRequest().getRequestURI();
			final JsonObject response = MyMapperUtils.writeObjectAsJsonObject(mvcResult.getResponse());
			response.remove("content");
			response.remove("outputStream");
			LOGGER.debug("result.header : {}\n{}", requestURI, response.toString());
			LOGGER.debug("result.content : {}\n{}", requestURI, mvcResult.getResponse().getContentAsString());
		} catch (final Throwable e) {
			LOGGER.warn(ExceptionUtils.getStackTrace(e));
			throw new CommonException(e);
		}
	}
}
