package com.github.bestheroz.standard.common.valuelabel;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class ValueLabelService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ValueLabelDAO valueLabelDAO;

    public List<ValueLabelVO> getValueLabeVOList(final String grcode) throws CommonException {
        return this.valueLabelDAO.getValueLabeVOList(grcode);
    }

    public JsonObject getValueLabelVoListToJsonObject(final String grcode) throws CommonException {
        final JsonObject jsonObject = new JsonObject();
        for (final ValueLabelVO vo : this.getValueLabeVOList(grcode)) {
            jsonObject.addProperty(vo.getValue(), vo.getLabel());
        }
        return jsonObject;
    }
}
