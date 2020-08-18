package com.aaa.mygym.dao;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @data
 * @describe
 */
public interface StatisticsDao {
    /**
     * 对于进一年产品消费进行的统计
     * @return
     */
    List<Map<String,Object>> getNearOneYearUser();

    /**
     * 近一年的普通会员
     */
    List<Map<String,Object>> getCommonUser();

    /**
     * 近一年的青铜会员
     * @return
     */
    List<Map<String,Object>> getQinTong();

    /**
     * 近一年白银会员
     * @return
     */
    List<Map<String,Object>> getBaiYin();
    /**
     * 近一年的黄金会员·
     * @return
     */
    List<Map<String,Object>> getHuangJinUser();

    /**
     * 近一年的铂金会员
     * @return
     */
    List<Map<String,Object>> getBoJin();

    /**'
     * 近一年总消费情况
     * @return
     */
    List<Map<String,Object>> getCountreChargerecord();


    /**
     * 近一年的订单详情
     */
    List<Map<String,Object>> getOrder();
}
