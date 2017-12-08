package com.chenxin.authority.dao;

import com.chenxin.authority.pojo.BaseRoles;
import com.chenxin.authority.pojo.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public abstract interface BaseRolesMapper
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract int deleteByExample(Criteria paramCriteria);

  public abstract int deleteByPrimaryKey(String paramString);

  public abstract int insert(BaseRoles paramBaseRoles);

  public abstract int insertSelective(BaseRoles paramBaseRoles);

  public abstract List<BaseRoles> selectByExample(Criteria paramCriteria);

  public abstract BaseRoles selectByPrimaryKey(String paramString);

  public abstract int updateByExampleSelective(@Param("record") BaseRoles paramBaseRoles, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByExample(@Param("record") BaseRoles paramBaseRoles, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByPrimaryKeySelective(BaseRoles paramBaseRoles);

  public abstract int updateByPrimaryKey(BaseRoles paramBaseRoles);
}
