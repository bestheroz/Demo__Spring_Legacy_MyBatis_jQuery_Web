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

    @SuppressWarnings("unchecked")
    public List<ValueLabelVO> getValueLabeVOList(final String grcode, final HttpSession session) throws CommonException {
        if (session.getAttribute("ValueLabel." + grcode) == null) {
            session.setAttribute("ValueLabel." + grcode, this.valueLabelDAO.getValueLabeVOList(grcode));
            this.logger.debug("save ==> ValueLabel.{}.", grcode);
        }
        this.logger.debug("cache ==> ValueLabel.{}.", grcode);
        return (List<ValueLabelVO>) session.getAttribute("ValueLabel." + grcode);
    }

    public String getLabelByValue(final Map<String, Object> params) throws CommonException {
        return this.valueLabelDAO.getLabelByValue(params);
    }

    public JsonObject getValueLabelVoListToJsonObject(final String grcode, final HttpSession session) throws CommonException {
        final JsonObject jsonObject = new JsonObject();
        for (final ValueLabelVO vo : this.getValueLabeVOList(grcode, session)) {
            jsonObject.addProperty(vo.getValue(), vo.getLabel());
        }
        return jsonObject;
    }
}
