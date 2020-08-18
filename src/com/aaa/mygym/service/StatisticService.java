package com.aaa.mygym.service;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    /**
     * 这个是会员等级统计
     * @return
     */
    Map<String,Object> getNearOneYearUser();

    /**
     * 这个近一年是各个会员增长的趋势
     */
    Map<String,Object> getNearOneTypeUser();

    /**
     * 近一年消费
     * @return
     */
    Map<String,Object> getreChargerecord();
    /**
     * 近一年的订单详情
     */
    Map<String,Object> getOrder();
}
