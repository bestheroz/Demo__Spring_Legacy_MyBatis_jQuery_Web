package com.github.bestheroz.standard.common.tablevo;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.github.bestheroz.standard.common.exception.CommonException;

public abstract interface SqlForTableDAO {
	// @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
	// List<Object> getList(final Object vo, final List<String> whereKey, final String orderByColumns) throws CommonException;
	//
	// @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
	// Object getOne(final Object vo, final List<String> whereKey) throws CommonException;

	@SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.COUNT)
	int count(final Object vo, final List<String> whereKey) throws CommonException;

	@InsertProvider(type = SqlForTableVO.class, method = SqlForTableVO.INSERT)
	// @SelectKey(statement = "SELECT SEQSEQSEQSEQ.NEXTVAL FROM DUAL", keyProperty = "seq", before = true, resultType = Long.class)
	<T extends Object> void insert(final T vo) throws CommonException;

	@UpdateProvider(type = SqlForTableVO.class, method = SqlForTableVO.UPDATE)
	<T extends Object> void update(final T vo, final List<String> whereKey, final List<String> forcedUpdateKey) throws CommonException;

	@DeleteProvider(type = SqlForTableVO.class, method = SqlForTableVO.DELETE)
	<T extends Object> void delete(final T vo, final List<String> whereKey) throws CommonException;
}
