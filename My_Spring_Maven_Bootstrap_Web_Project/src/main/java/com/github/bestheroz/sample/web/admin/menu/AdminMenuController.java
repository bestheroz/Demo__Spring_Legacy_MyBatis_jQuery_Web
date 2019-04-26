package com.github.bestheroz.sample.web.admin.menu;

import com.github.bestheroz.sample.web.tablevo.samplemenumst.TableSampleMenuMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.file.excel.ExcelService;
import com.github.bestheroz.standard.common.file.excel.ExcelVO;
import com.github.bestheroz.standard.common.file.excel.HugeExcelService;
import com.github.bestheroz.standard.common.file.pdf.PdfService;
import com.github.bestheroz.standard.common.file.pdf.PdfVO;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.github.bestheroz.standard.common.valuelabel.ValueLabelService;
import com.github.bestheroz.standard.common.valuelabel.ValueLabelVO;
import com.github.bestheroz.standard.context.abstractview.AbstractExcelXView;
import com.github.bestheroz.standard.context.abstractview.AbstractPdfboxView;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminMenuController {
    @Autowired
    private AdminMenuService adminMenuService;
    @Autowired
    private ValueLabelService valueLabelService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/sample/admin/menu/adminMenu.view", method = RequestMethod.GET)
    public String home() throws Exception {
        return "/sample/admin/menu/AdminMenu";
    }

    @RequestMapping(value = "/sample/admin/menu/getSampleMenuMstVOList.json", method = RequestMethod.POST)
    @ResponseBody
    public List<AdminMenuVO> getSampleMenuMstVOList(final AdminMenuVO vo) throws CommonException {
        return this.adminMenuService.getSampleMenuMstVOList(vo);
    }

    @RequestMapping(value = "/sample/admin/menu/insertSampleMenuMst.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject insertSampleMenuMst(final TableSampleMenuMstVO vo) throws CommonException {
        this.adminMenuService.insertSampleMenuMst(vo, MySessionUtils.getLoginVO(this.session));
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/admin/menu/updateSampleMenuMst.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject updateSampleMenuMst(final TableSampleMenuMstVO vo) throws CommonException {
        this.adminMenuService.updateSampleMenuMst(vo, MySessionUtils.getLoginVO(this.session));
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/admin/menu/deleteSampleMenuMst.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject deleteSampleMenuMst(final TableSampleMenuMstVO vo) throws CommonException {
        this.adminMenuService.deleteSampleMenuMst(vo);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/sample/admin/menu/getPMenuValueLableVOList.json", method = RequestMethod.POST)
    @ResponseBody
    public List<ValueLabelVO> getPMenuValueLableVOList(final AdminMenuVO vo) throws CommonException {
        return this.adminMenuService.getPMenuValueLableVOList(null);
    }

    @RequestMapping(value = "/sample/admin/menu/adminMenu.xlsx", method = RequestMethod.POST)
    public String getExcel(final Model model, final AdminMenuVO vo) throws CommonException {
        model.addAttribute(AbstractExcelXView.FILE_NAME, "메뉴리스트");

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, "메뉴ID", "menuId", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, "메뉴명", "menuNm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "메뉴구분", "menuTyp", ExcelService.CellType.STRING_CENTER, this.valueLabelService.getValueLabelVoListToJsonObject("MENU_TYP", this.session));
        AbstractExcelXView.addHeader(excelVOList, "부모메뉴ID", "parMenuId", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, "사용여부", "useYn", ExcelService.CellType.STRING_CENTER, this.valueLabelService.getValueLabelVoListToJsonObject("USE_YN", this.session));
        AbstractExcelXView.addHeader(excelVOList, "권한", "power", ExcelService.CellType.STRING_RIGHT, this.valueLabelService.getValueLabelVoListToJsonObject("MEMBER_TYP", this.session));
        AbstractExcelXView.addHeader(excelVOList, "(같은그룹내)출력순서", "dispSeq", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, "URL", "url", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "비고", "remark1", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "작업일시", "updDt", ExcelService.CellType.DATE);
        AbstractExcelXView.addHeader(excelVOList, "작업자", "updMemberId", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        model.addAttribute(AbstractExcelXView.LIST_DATA, this.adminMenuService.getSampleMenuMstVOList(vo));

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

    @RequestMapping(value = "/sample/admin/menu/adminMenuHugeExcel.xlsx", method = RequestMethod.POST)
    public String getHugeExcel(final Model model, final AdminMenuVO vo) throws Exception {
        model.addAttribute(AbstractExcelXView.FILE_NAME, "메뉴리스트");

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, "메뉴ID", "menuId", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, "메뉴명", "menuNm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "메뉴구분", "menuTyp", ExcelService.CellType.STRING_CENTER, this.valueLabelService.getValueLabelVoListToJsonObject("MENU_TYP", this.session));
        AbstractExcelXView.addHeader(excelVOList, "부모메뉴ID", "parMenuId", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, "사용여부", "useYn", ExcelService.CellType.STRING_CENTER, this.valueLabelService.getValueLabelVoListToJsonObject("USE_YN", this.session));
        AbstractExcelXView.addHeader(excelVOList, "권한", "power", ExcelService.CellType.STRING_RIGHT, this.valueLabelService.getValueLabelVoListToJsonObject("MEMBER_TYP", this.session));
        AbstractExcelXView.addHeader(excelVOList, "(같은그룹내)출력순서", "dispSeq", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, "URL", "url", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "비고", "remark1", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "작업일시", "updDt", ExcelService.CellType.DATE);
        AbstractExcelXView.addHeader(excelVOList, "작업자", "updMemberId", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        final StringBuilder sql = new StringBuilder("SELECT SMM.MENU_ID , SMM.MENU_NM , SMM.MENU_TYP , SMM.PAR_MENU_ID , SMM.USE_YN , SMM.POWER ,");
        sql.append(" SMM.DISP_SEQ , SMM.URL , SMM.REMARK1 , SMM.UPD_DT , SMM.UPD_MEMBER_ID FROM SAMPLE_MENU_MST SMM WHERE 1=1 ORDER BY SMM.UPD_DT DESC");

        model.addAttribute(AbstractExcelXView.SQL, sql.toString());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return HugeExcelService.VIEW_NAME;
    }

    @RequestMapping(value = "/sample/admin/menu/adminMenu.pdf", method = RequestMethod.POST)
    public String getPdf(final Model model, final AdminMenuVO vo) throws CommonException {
        model.addAttribute(AbstractPdfboxView.FILE_NAME, "메뉴리스트");

        final List<PdfVO> pdfVOList = new ArrayList<>();
        AbstractPdfboxView.addPdfDataType(pdfVOList, "메뉴ID", "menuId", AbstractPdfboxView.CellType.STRING_CENTER, 10f);
        AbstractPdfboxView.addPdfDataType(pdfVOList, "메뉴명", "menuNm", AbstractPdfboxView.CellType.STRING, 20f);
        AbstractPdfboxView.addPdfDataType(pdfVOList, "메뉴구분", "menuTyp", AbstractPdfboxView.CellType.STRING_CENTER, 10f,
                this.valueLabelService.getValueLabelVoListToJsonObject("MENU_TYP", this.session));
        AbstractPdfboxView.addPdfDataType(pdfVOList, "URL", "url", AbstractPdfboxView.CellType.STRING, 40f);
        model.addAttribute(AbstractPdfboxView.PDF_VOS, pdfVOList);

        model.addAttribute(AbstractPdfboxView.LIST_DATA, this.adminMenuService.getSampleMenuMstVOList(vo));

        return PdfService.VIEW_NAME;
    }
}
