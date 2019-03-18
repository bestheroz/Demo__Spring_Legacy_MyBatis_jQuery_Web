package com.github.bestheroz.sample.web.tablevo.samplecodemst;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.tablevo.SqlForTableDAO;
import com.github.bestheroz.standard.common.tablevo.SqlForTableVO;

@Mapper
public interface TableSampleCodeMstDAO extends SqlForTableDAO {
	@SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
	List<TableSampleCodeMstVO> getList(final TableSampleCodeMstVO vo, final List<String> whereKey, final String orderByColumns) throws CommonException;

	@SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
	TableSampleCodeMstVO getVO(final TableSampleCodeMstVO vo, final List<String> whereKey) throws CommonException;
}
