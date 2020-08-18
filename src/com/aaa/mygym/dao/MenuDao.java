package com.aaa.mygym.dao;


import com.aaa.mygym.entity.Menu;

import java.util.List;

/**
 * @author
 * @date
 * 菜单接口
**/
public interface MenuDao {
    /**
     * 通过roleId 获取 菜单
     * @param roleId
     * @return
     */
    List<Menu> getMenuListByRoleId(Integer roleId);
}
