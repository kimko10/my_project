package com.carrot.common.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ObjectMapper {

    public List<Map<Object, Object>> selectAll(@Param("sql") String sql);
    public Map<Object, Object> selectOne(@Param("sql") String sql);
    public void update(@Param("table") String table, @Param("data") HashMap<Object, Object> data, @Param("where") String where);
    public void insert(@Param("table") String table, @Param("data") HashMap<Object, Object> data);
    public void delete(@Param("table") String table, @Param("data") HashMap<Object, Object> data);
    public Map<Object, Object> getData(@Param("table") String table, @Param("data") HashMap<Object, Object> data);
    public List<Map<Object, Object>> getList(@Param("table") String table, @Param("data") HashMap<Object, Object> data);

}

