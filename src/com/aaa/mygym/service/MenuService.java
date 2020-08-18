package com.aaa.mygym.service;


import com.aaa.mygym.entity.Menu;

import java.util.List;

public interface MenuService {
    /**
     * 通过roleId 获取 菜单
     * @param roleId
     * @return
     */
    List<Menu> getMenuListByRoleId(Integer roleId) throws Exception;
}
