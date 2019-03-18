package com.github.bestheroz.sample.web.tablevo.samplemembermst;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.tablevo.SqlForTableDAO;
import com.github.bestheroz.standard.common.tablevo.SqlForTableVO;

@Mapper
public interface TableSampleMemberMstDAO extends SqlForTableDAO {

	@SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
	List<TableSampleMemberMstVO> getList(final TableSampleMemberMstVO vo, final List<String> whereKey, final String orderByColumns) throws CommonException;

	@SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
	TableSampleMemberMstVO getVO(final TableSampleMemberMstVO vo, final List<String> whereKey) throws CommonException;
}
