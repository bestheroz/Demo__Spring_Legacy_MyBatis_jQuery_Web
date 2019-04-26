package com.github.bestheroz.sample.web.tablevo.samplemenumst;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.tablevo.SqlForTableDAO;
import com.github.bestheroz.standard.common.tablevo.SqlForTableVO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface TableSampleMenuMstDAO extends SqlForTableDAO {

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
    List<TableSampleMenuMstVO> getList(final TableSampleMenuMstVO vo, final List<String> whereKey, final String orderByColumns) throws CommonException;

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
    TableSampleMenuMstVO getVO(final TableSampleMenuMstVO vo, final List<String> whereKey) throws CommonException;

    @Override
    @InsertProvider(type = SqlForTableVO.class, method = SqlForTableVO.INSERT)
    @SelectKey(statement = "SELECT SEQ_SAMPLE_MENU_MST.NEXTVAL FROM DUAL", keyProperty = "menuId", before = true, resultType = Integer.class)
    <T> void insert(final T vo) throws CommonException;

}
