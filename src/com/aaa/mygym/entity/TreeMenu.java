package com.aaa.mygym.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装 bootstrap treeview
 */
public class TreeMenu {
    private String text;
    private String icon;
    private int nodeid;
    private int pid;
    private int sort;
    //设置是否被选中
    private Map<String, Object> state;

    private List<TreeMenu> nodes;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getNodeid() {
        return nodeid;
    }

    public void setNodeid(int nodeid) {
        this.nodeid = nodeid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<TreeMenu> getTreeMenuList() {
        return nodes;
    }

    public void setTreeMenuList(List<TreeMenu> treeMenuList) {
        this.nodes = treeMenuList;
    }

    /**
     * 用于角色管理--编辑--回显
     * （根据逻辑判定是否需要默认选中，如需要则调用此方法赋值）
     */
    public void setState(String res) {
        Map<String, Object> stateMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(res)) {
            stateMap.put("checked", true);
            stateMap.put("expanded", true);
        } else {
            stateMap.put("checked", false);
            stateMap.put("expanded", false);
        }
        this.state = stateMap;
    }
}
