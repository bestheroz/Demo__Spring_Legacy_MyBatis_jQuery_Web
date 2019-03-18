package com.github.bestheroz.standard.common.valuelabel;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.github.bestheroz.standard.common.exception.CommonException;

@Mapper
public interface ValueLabelDAO {
	@Select(value = "SELECT SCD.CODE, SCD.CODE_NM FROM SAMPLE_CODE_DET SCD WHERE SCD.GRCODE = #{grcode, jdbcType=VARCHAR} ORDER BY SCD.DISP_SEQ ASC")
	@Results(value = { @Result(column = "CODE", property = "value"), @Result(column = "CODE_NM", property = "label") })
	List<ValueLabelVO> getValueLabeVOList(final String grcode) throws CommonException;

	@Select(value = "SELECT SCD.CODE_NM FROM SAMPLE_CODE_DET SCD WHERE SCD.GRCODE = #{grcode, jdbcType=VARCHAR} AND SCD.CODE = #{code, jdbcType=VARCHAR}  ORDER BY SCD.DISP_SEQ ASC")
	@Results(value = { @Result(column = "CODE_NM", property = "label") })
	String getLabelByValue(final Map<String, Object> params) throws CommonException;
}
