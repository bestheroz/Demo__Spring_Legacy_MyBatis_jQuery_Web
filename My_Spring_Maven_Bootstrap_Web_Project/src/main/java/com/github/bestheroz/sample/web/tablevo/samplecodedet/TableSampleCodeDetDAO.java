package com.github.bestheroz.sample.web.tablevo.samplecodedet;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.tablevo.SqlForTableDAO;
import com.github.bestheroz.standard.common.tablevo.SqlForTableVO;

@Mapper
public interface TableSampleCodeDetDAO extends SqlForTableDAO {
	@SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
	List<TableSampleCodeDetVO> getList(final TableSampleCodeDetVO vo, final List<String> whereKey, final String orderByColumns) throws CommonException;

	@SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
	TableSampleCodeDetVO getVO(final TableSampleCodeDetVO vo, final List<String> whereKey) throws CommonException;
}
