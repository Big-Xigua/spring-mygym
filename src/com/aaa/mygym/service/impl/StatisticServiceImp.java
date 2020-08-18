package com.aaa.mygym.service.impl;



import com.aaa.mygym.dao.StatisticsDao;
import com.aaa.mygym.dao.impl.StatisticsDaoImp;
import com.aaa.mygym.service.StatisticService;
import com.aaa.mygym.util.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @data
 * @describe
 */
public class StatisticServiceImp implements StatisticService {
    private StatisticsDao statisticsDao=new StatisticsDaoImp();
    @Override
    public Map<String,Object> getNearOneYearUser() {
        int[] ints = DateUtils.getMonthByNearYear();
        int[] datas = new int[12];
        String[] strings = new String[12];
        List<Map<String, Object>> mapList = statisticsDao.getNearOneYearUser();
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < mapList.size(); j++) {
                if (ints[i] == Integer.parseInt(mapList.get(j).get("mouth") + "")) {
                    datas[i] = Integer.parseInt(mapList.get(j).get("len")+"");
                }
            }
        }

        Integer nearYear = DateUtils.getNearYear();

        for (int i = 0; i < ints.length; i++) {
            if(ints[ints.length-1]<ints[i]){
               strings[i]=nearYear-1+"."+ints[i];
            }else{
                strings[i]=nearYear+"."+ints[i];
            }

        }

        Map<String,Object> map = new HashMap<>();
        map.put("m",strings);
        map.put("d",datas);
        return  map;
    }


    @Override
    public Map<String, Object> getNearOneTypeUser() {
        int[] ints = DateUtils.getMonthByNearYear();
        /**
         * 这个是获取近一年的年月
         */
        String []mouth=new String[13];
        mouth[0]="product";
        Integer nearYear = DateUtils.getNearYear();
        for (int i = 1; i < mouth.length; i++) {
            if(ints[ints.length-1]<ints[i-1]){
                mouth[i]=nearYear-1+"."+ints[i-1];
            }else{
                mouth[i]=nearYear+"."+ints[i-1];
            }
        }

        /**
         *普通
         */
        String []commonList=new String[13];
        commonList[0]="普通";
        List<Map<String, Object>> commonUser = statisticsDao.getCommonUser();
        if(commonUser==null||commonUser.size()==0){
            for(int i=1;i<commonList.length;i++){
                commonList[i]=0+"";
            }
        }else{
            for (int i = 0; i < ints.length; i++) {
                for (int j = 0; j < commonUser.size(); j++) {
                    if (ints[i] == Integer.parseInt(commonUser.get(j).get("month") + "")) {
                        commonList[i+1] = commonUser.get(j).get("len")+"";
                        break;
                    }else{
                        commonList[i+1]="0";
                    }
                }
            }
        }

        /**
         * 青铜
         */
        List<Map<String, Object>> qinTong = statisticsDao.getQinTong();
        String []qinTongList=new String[13];
        qinTongList[0]="青铜";
        if(qinTong==null||qinTong.size()==0){
            for(int i=1;i<commonList.length;i++){
                qinTongList[i]="0";
            }
        }else{
            for (int i = 0; i < ints.length; i++) {
                for (int j = 0; j < qinTong.size(); j++) {
                    if (ints[i] == Integer.parseInt(qinTong.get(j).get("month") + "")) {
                        qinTongList[i+1] = qinTong.get(j).get("len")+"";
                        break;
                    }else{
                        qinTongList[i+1]="0";
                    }
                }
            }
        }

        /**
         * 白银
         */
        List<Map<String, Object>> baiYin = statisticsDao.getBaiYin();
        String []baiYinList=new String[13];
        baiYinList[0]="白银";

        if(baiYin==null||baiYin.size()==0){
            for(int i=1;i<baiYinList.length;i++){
                baiYinList[i]="0";
            }
        }else{
            for (int i = 0; i < ints.length; i++) {
                for (int j = 0; j < baiYin.size(); j++) {
                    if (ints[i] == Integer.parseInt(baiYin.get(j).get("month") + "")) {
                        baiYinList[i+1] = baiYin.get(j).get("len")+"";
                        break;
                    }else{
                        baiYinList[i+1]="0";
                    }
                }
            }
        }
        /**
         * 黄金
         */
        List<Map<String, Object>> huangJinUser = statisticsDao.getHuangJinUser();
        String []huangJinUserList=new String[13];
        huangJinUserList[0]="黄金";
        if(huangJinUser==null||huangJinUser.size()==0){
            for(int i=1;i<huangJinUserList.length;i++){
                huangJinUserList[i]="0";
            }
        }else{
            for (int i = 0; i < ints.length; i++) {
                for (int j = 0; j < huangJinUser.size(); j++) {
                    if (ints[i] == Integer.parseInt(huangJinUser.get(j).get("month") + "")) {
                        huangJinUserList[i+1] =huangJinUser.get(j).get("len")+"";
                        break;
                    }else{
                        huangJinUserList[i+1]="0";
                    }
                }
            }
        }
        /**
         * 铂金
         */
        List<Map<String, Object>> boJin = statisticsDao.getBoJin();
        String []boJinList=new String[13];
        boJinList[0]="铂金";
        if(boJin==null||boJin.size()==0){
            for(int i=1;i<boJinList.length;i++){
                boJinList[i]="0";
            }
        }else{
            for (int i = 0; i < ints.length; i++) {
                for (int j = 0; j < boJin.size(); j++) {
                    if (ints[i] == Integer.parseInt(boJin.get(j).get("month") + "")) {
                        boJinList[i+1] =boJin.get(j).get("len")+"";
                        break;
                    }else{
                        boJinList[i+1]="0";
                    }
                }
            }
        }
        Map<String,Object>map=new HashMap<>();
        map.put("len",mouth);
        map.put("pu",commonList);
        map.put("qin",qinTongList);
        map.put("bai",baiYinList);
        map.put("hung",huangJinUserList);
        map.put("bo",boJinList);
        return map;
    }

    @Override
    public Map<String, Object> getreChargerecord() {
        int[] ints = DateUtils.getMonthByNearYear();
        String[] datas = new String[12];
        String[] strings = new String[12];
        List<Map<String, Object>> mapList = statisticsDao.getCountreChargerecord();
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < mapList.size(); j++) {
                if (ints[i] == Integer.parseInt(mapList.get(j).get("mon") + "")) {
                    datas[i] = (mapList.get(j).get("count")+"");
                    break;
                }else{
                    datas[i]="0";
                }
            }
        }

        /**
         * 这个在月份前添加月份所属年份
         */
        Integer nearYear = DateUtils.getNearYear();

        for (int i = 0; i < ints.length; i++) {
            if(ints[ints.length-1]<ints[i]){
                strings[i]=nearYear-1+"."+ints[i];
            }else{
                strings[i]=nearYear+"."+ints[i];
            }

        }

        Map<String,Object> map = new HashMap<>();
        map.put("m",strings);
        map.put("d",datas);
        return  map;
    }


    @Override
    public Map<String, Object> getOrder() {
        List<Map<String, Object>> order = statisticsDao.getOrder();
        int[] ints = DateUtils.getMonthByNearYear();
        /**
         * 这个是获取近一年的年月
         */
        String []mouth=new String[13];
        mouth[0]="product";
        Integer nearYear = DateUtils.getNearYear();
        for (int i = 1; i < mouth.length; i++) {
            if(ints[ints.length-1]<ints[i-1]){
                mouth[i]=nearYear-1+"."+ints[i-1];
            }else{
                mouth[i]=nearYear+"."+ints[i-1];
            }
        }

        /**
         * 这个是向前端添加的数据
         */

        String [] data=new String[13];
        data[0]="销售总单数";

        String [] data1=new String [13];
        data1[0]="销售总金额";

        /**
         * 这个是进行比较穿过来年份和现在近一年年份比较看穿过来的有哪些年份
         */
        for(int i=0;i<ints.length;i++){
            for(int j=0;j<order.size();j++){
                if(ints[i] == Integer.parseInt(order.get(j).get("mon")+"")){
                    data[i+1]=order.get(j).get("count")+"";
                    data1[i+1]=order.get(j).get("sum")+"";
                    break;
                }else{
                    data[i+1]="0";
                    data1[i+1]="0";
                }
            }
        }

        Map<String,Object>map=new HashMap<>();
        map.put("map",mouth);
        map.put("count",data);
        map.put("sum",data1);
        return map;
    }


}
