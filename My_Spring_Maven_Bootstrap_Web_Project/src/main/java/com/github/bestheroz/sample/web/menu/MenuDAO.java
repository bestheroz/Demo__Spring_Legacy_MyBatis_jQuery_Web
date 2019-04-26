package com.github.bestheroz.sample.web.menu;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.google.gson.JsonObject;

@Mapper
public interface MenuDAO {
    List<MenuVO> getMenuVOList(final JsonObject param) throws CommonException;
}
