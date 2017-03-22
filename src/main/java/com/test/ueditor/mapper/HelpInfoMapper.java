package com.test.ueditor.mapper;

import com.test.ueditor.model.HelpInfo;

public interface HelpInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HelpInfo record);

    int insertSelective(HelpInfo record);

    HelpInfo selectByPrimaryKey(Long id);

    HelpInfo selectByMenuId(Integer id);

    int updateByPrimaryKeySelective(HelpInfo record);

    int updateByPrimaryKeyWithBLOBs(HelpInfo record);

    int updateByPrimaryKey(HelpInfo record);
}