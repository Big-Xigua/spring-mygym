package com.aaa.mygym.dao.impl;



import com.aaa.mygym.dao.MenuDao;
import com.aaa.mygym.entity.Menu;
import com.aaa.mygym.util.BaseDao;

import java.util.List;

public class MenuDaoImpl implements MenuDao {
    private BaseDao baseDao = new BaseDao();
    @Override
    public List<Menu> getMenuListByRoleId(Integer roleId) {
        String sql = "select r.resource_name resourceName,r.* from resource r,(select * from resource_role where role_id = ?) a " +
                "where r.id = a.resource_id and r.pid = 0 and r.status = 1 order by r.sort asc";
        Object[] params = {roleId};
        List<Menu> menuList =  baseDao.queryList(sql,params,Menu.class);
        if(menuList != null && menuList.size() > 0){
            for (int i = 0; i < menuList.size(); i++) {
                Menu menu = menuList.get(i);
                String subSql = "select r.resource_name resourceName,r.* from resource r " +
                        "where r.pid = ? and r.status = 1 order by r.sort asc";
                Object[] params1 = {menu.getId()};
                List<Menu> subMenuList =  baseDao.queryList(subSql,params1,Menu.class);
                menu.setMenuList(subMenuList);
            }
        }
        return menuList;
    }

}
