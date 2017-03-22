package com.test.ueditor.controller;

import com.test.ueditor.mapper.HelpInfoMapper;
import com.test.ueditor.model.HelpInfo;
import com.test.ueditor.service.HelpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2017/3/22 0022.
 *
 * @author zlf
 * @since 1.0
 */
@RestController
public class HelpInfoController {

    @Autowired
    HelpInfoMapper helpInfoMapper;

    @Autowired
    HelpInfoService helpInfoService;

    @RequestMapping(value = "test")
    public HelpInfo test(){
        return helpInfoMapper.selectByPrimaryKey(3l);
    }

    @RequestMapping(value = "test1")
    public HelpInfo test1(){
        return helpInfoService.findHelpInfoByMenuId(1);
    }

}
