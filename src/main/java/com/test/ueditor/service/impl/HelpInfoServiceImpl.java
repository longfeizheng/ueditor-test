package com.test.ueditor.service.impl;

import com.test.ueditor.mapper.HelpInfoMapper;
import com.test.ueditor.model.HelpInfo;
import com.test.ueditor.service.HelpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/3/22 0022.
 *
 * @author zlf
 * @since 1.0
 */
@Service
public class HelpInfoServiceImpl implements HelpInfoService {

    @Autowired
    private HelpInfoMapper helpInfoMapper;

    @Override
    public HelpInfo findHelpInfoByMenuId(Integer menuId) {
        return helpInfoMapper.selectByMenuId(menuId);
    }
}
