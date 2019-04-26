package com.github.bestheroz.sample.web.admin.menu;

import com.github.bestheroz.sample.web.tablevo.samplemenumst.TableSampleMenuMstDAO;
import com.github.bestheroz.sample.web.tablevo.samplemenumst.TableSampleMenuMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyAccessBeanUtils;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.github.bestheroz.standard.common.util.MyTestUtils;
import com.github.bestheroz.standard.common.valuelabel.ValueLabelVO;
import com.github.bestheroz.standard.test.DefaultTestClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

public class TestAdminMenuController extends DefaultTestClass {
    @Test
    public void 메뉴화면호출() throws Exception {
        MyTestUtils.performGetLogined("/sample/admin/menu/adminMenu.view").andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/sample/admin/menu/AdminMenu")).andReturn();
    }

    @Test
    public void 메뉴데이터_취득() throws Exception {
        final MvcResult mvcResult = MyTestUtils.performPostLogined("/sample/admin/menu/getSampleMenuMstVOList.json").andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        Assertions.assertTrue(MyMapperUtils.writeObjectAsArrayList(mvcResult.getResponse().getContentAsString(), AdminMenuVO.class).get(0).getMenuId() != null);
    }

    @Test
    public void 메뉴데이터_추가() throws Exception {
        final TableSampleMenuMstVO vo = new TableSampleMenuMstVO();
        vo.setMenuNm("메뉴데이터_추가");
        vo.setDispSeq(999);
        vo.setMenuTyp("P");
        vo.setPower(999);
        vo.setParMenuId(1000000000);
        vo.setUseYn("Y");
        final MvcResult mvcResult = MyTestUtils.performPostLogined("/sample/admin/menu/insertSampleMenuMst.json", vo).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        Assertions.assertEquals(MyMapperUtils.writeObjectAsJsonObject(mvcResult.getResponse().getContentAsString()), CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject());
        Assertions.assertEquals(MyAccessBeanUtils.getBean(TableSampleMenuMstDAO.class).getVO(vo, Arrays.asList("menuNm")).getMenuNm(), "메뉴데이터_추가");
    }

    @Test
    public void 메뉴데이터_수정() throws Exception {
        final TableSampleMenuMstVO vo = new TableSampleMenuMstVO();
        vo.setMenuId(1000000000);
        vo.setRemark1("메뉴데이터_수정");
        final MvcResult mvcResult = MyTestUtils.performPostLogined("/sample/admin/menu/updateSampleMenuMst.json", vo).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        Assertions.assertEquals(MyMapperUtils.writeObjectAsJsonObject(mvcResult.getResponse().getContentAsString()), CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject());
        Assertions.assertEquals(MyAccessBeanUtils.getBean(TableSampleMenuMstDAO.class).getVO(vo, Arrays.asList("menuId")).getRemark1(), "메뉴데이터_수정");
    }

    @Test
    public void 메뉴데이터_삭제() throws Exception {
        final TableSampleMenuMstVO vo = new TableSampleMenuMstVO();
        vo.setMenuId(1000000000);
        final MvcResult mvcResult = MyTestUtils.performPostLogined("/sample/admin/menu/deleteSampleMenuMst.json", vo).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        Assertions.assertEquals(MyMapperUtils.writeObjectAsJsonObject(mvcResult.getResponse().getContentAsString()), CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject());
        Assertions.assertEquals(MyAccessBeanUtils.getBean(TableSampleMenuMstDAO.class).getVO(vo, Arrays.asList("menuId")), null);
    }

    @Test
    public void 카테고리_select리스트_가져오기() throws Exception {
        final MvcResult mvcResult = MyTestUtils.performPostLogined("/sample/admin/menu/getPMenuValueLableVOList.json").andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        Assertions.assertTrue(MyMapperUtils.writeObjectAsArrayList(mvcResult.getResponse().getContentAsString(), ValueLabelVO.class).size() > 0);
    }

    @Test
    public void 엑셀다운로드() throws Exception {
        MyTestUtils.performPostLogined("/sample/admin/menu/adminMenu.xlsx").andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")).andReturn();
        // Assertions.assertTrue(MyMapperUtils.writeObjectAsArrayList(mvcResult.getResponse().getContentAsString(), ValueLabelVO.class).size() > 0);
    }

    @Test
    public void 엑셀대용량다운로드() throws Exception {
        MyTestUtils.performPostLogined("/sample/admin/menu/adminMenuHugeExcel.xlsx").andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")).andReturn();
        // Assertions.assertTrue(MyMapperUtils.writeObjectAsArrayList(mvcResult.getResponse().getContentAsString(), ValueLabelVO.class).size() > 0);
    }

    @Test
    public void PDF다운로드() throws Exception {
        MyTestUtils.performPostLogined("/sample/admin/menu/adminMenu.pdf").andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_PDF)).andReturn();
        // Assertions.assertTrue(MyMapperUtils.writeObjectAsArrayList(mvcResult.getResponse().getContentAsString(), ValueLabelVO.class).size() > 0);
    }

}
