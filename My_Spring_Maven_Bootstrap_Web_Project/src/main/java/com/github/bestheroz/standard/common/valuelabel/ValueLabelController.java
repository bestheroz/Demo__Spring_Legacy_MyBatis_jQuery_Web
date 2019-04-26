package com.github.bestheroz.standard.common.valuelabel;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.bestheroz.standard.common.exception.CommonException;

@Controller
public class ValueLabelController {
    @Autowired
    private ValueLabelService valueLabelService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/common/valuelabel/getValueLabeVOList.json", method = RequestMethod.POST)
    @ResponseBody
    public List<ValueLabelVO> getValueLabeVOList(@RequestParam("grcode") final String grcode) throws CommonException {
        return this.valueLabelService.getValueLabeVOList(grcode, this.session);
    }
}
