package com.github.bestheroz.sample.web.tablevo.samplemembermst;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.tablevo.SqlForTableDAO;
import com.github.bestheroz.standard.common.tablevo.SqlForTableVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface TableSampleMemberMstDAO extends SqlForTableDAO {

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
    List<TableSampleMemberMstVO> getList(final TableSampleMemberMstVO vo, final List<String> whereKey, final String orderByColumns) throws CommonException;

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
    TableSampleMemberMstVO getVO(final TableSampleMemberMstVO vo, final List<String> whereKey) throws CommonException;
}
