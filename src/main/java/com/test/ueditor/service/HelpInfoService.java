package com.test.ueditor.service;

import com.test.ueditor.model.HelpInfo;

/**
 * Created on 2017/3/22 0022.
 *
 * @author zlf
 * @since 1.0
 */
public interface HelpInfoService {
    HelpInfo findHelpInfoByMenuId(Integer menuId);
}
