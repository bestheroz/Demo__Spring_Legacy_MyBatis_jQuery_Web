package com.github.bestheroz.standard.test;

import com.github.bestheroz.sample.web.login.LoginVO;
import com.github.bestheroz.sample.web.tablevo.samplemembermst.TableSampleMemberMstDAO;
import com.github.bestheroz.sample.web.tablevo.samplemembermst.TableSampleMemberMstVO;
import com.github.bestheroz.standard.common.util.MyAccessBeanUtils;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.github.bestheroz.standard.common.util.MyTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

@SpringJUnitConfig(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@Transactional
public class DefaultTestClass {
    @Autowired
    protected WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MyTestUtils.setMockMvc(MockMvcBuilders.webAppContextSetup(this.wac).build());
    }

    @Test
    public void login() {
        final TableSampleMemberMstVO tableSampleMemberMstVO = new TableSampleMemberMstVO();
        tableSampleMemberMstVO.setMemberId("1");
        MyTestUtils
                .doLogined(MyMapperUtils
                        .writeObjectAsObject(MyAccessBeanUtils.getBean(TableSampleMemberMstDAO.class).getVO(tableSampleMemberMstVO, Collections.singleton("memberId")), LoginVO.class));
    }

    @SuppressWarnings("NonAsciiCharacters")
    @Test
    public void 초기구동확인() throws Exception {
        MyTestUtils.performGet("/").andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        this.login();
    }

}
