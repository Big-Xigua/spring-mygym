package com.aaa.mygym.service.impl;


import com.aaa.mygym.dao.MenuDao;
import com.aaa.mygym.dao.impl.MenuDaoImpl;
import com.aaa.mygym.entity.Menu;
import com.aaa.mygym.service.MenuService;
import com.aaa.mygym.util.BusinessException;

import java.util.List;

public class MenuServiceImpl implements MenuService {
    private MenuDao menuDao = new MenuDaoImpl();
    @Override
    public List<Menu> getMenuListByRoleId(Integer roleId) throws BusinessException {
        if (roleId == null || roleId == 0){
            throw new BusinessException("角色ID不能为空");
        }
        return menuDao.getMenuListByRoleId(roleId);
    }
}
