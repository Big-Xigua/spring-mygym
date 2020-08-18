package com.aaa.mygym.service.impl;

import com.aaa.mygym.dao.ConsumDao;
import com.aaa.mygym.dao.impl.ConsumDaoImpl;
import com.aaa.mygym.entity.ConsumOrder;
import com.aaa.mygym.entity.Goods;
import com.aaa.mygym.service.ConsumeService;
import com.aaa.mygym.util.BaseDao;
import com.aaa.mygym.util.BusinessException;
import com.aaa.mygym.util.IntegerUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @data
 * @describe
 */
public class ConsumeServiceImpl implements ConsumeService {
    private ConsumDao consumDao=new ConsumDaoImpl();
    private BaseDao baseDao=new BaseDao();
    /**
     * 新增商品消费
     *
     * @return
     */


    @Override
    public Map<String, Object> insertOrder( String ruleOrderId, String ruleCardId,
                                           String rulePrice, String rulePay, String ruleMOmo,
                                           String goodsId) {
        ConsumOrder consumOrder=new ConsumOrder();


        consumOrder.setCredit(IntegerUtils.ToInteger(rulePay));
        consumOrder.setCardId(IntegerUtils.ToInteger(ruleCardId));
        consumOrder.setOrderId(IntegerUtils.ToInteger(ruleOrderId));
        consumOrder.setPrice((double) IntegerUtils.ToInteger(rulePrice));
        consumOrder.setPay((double) IntegerUtils.ToInteger(rulePay));
        consumOrder.setCredit(IntegerUtils.ToInteger(rulePay));
        if (StringUtils.isNotBlank(ruleCardId)){

        String sql="select levelId from card where cardId=?";
        Object []params={consumOrder.getCardId()};
        List<Map<String,Object>> list = baseDao.executeQuery(sql, params);
        Object levelId = list.get(0).get("levelId");
        consumOrder.setCardType(IntegerUtils.ToInteger(levelId+""));
        }
        consumOrder.setStatus(1);
        consumOrder.setMomo(ruleMOmo);
        if (StringUtils.isBlank(ruleCardId)){
            consumOrder.setCardId(IntegerUtils.ToInteger("999"));
            consumOrder.setCredit(0);
            consumOrder.setCardType(0);
        }
        String format = new SimpleDateFormat("yyy-MM-dd hh:mm:ss").format(new Date());
        consumOrder.setCreatedtime(format);
        Map map=new HashMap();

        String[]id=goodsId.split(",");
        for (int j=0;j<id.length;j++){
            /**
             * 减少商品数量
             */
            int res=consumDao.delGoodsNumber(Integer.parseInt(id[j]),1);
            map.put("j"+j,res);
        }
        /**
         * 增加消费订单
         */
        int i = consumDao.insertOrder(consumOrder);
        /**
         * 减少卡钱
         */

        int i2 = consumDao.consumerCard(IntegerUtils.ToInteger(ruleCardId), IntegerUtils.ToInteger(rulePay));



        if (StringUtils.isNotBlank(ruleCardId)){

        map.put("he",i2);
        }
        map.put("ha",i);
        return map;
    }

    /**
     * 商品列表
     *
     * @param pageNumber
     * @param pageSize
     * @param searchId
     * @param searchName
     */
    @Override
    public Map<String, Object> consumeGoodList(Integer pageNumber, Integer pageSize, String searchId, String searchName) throws Exception {
        if (pageNumber == null || pageNumber == 0) {
            throw new BusinessException("当前页数不能为空");
        }
        if (pageSize == null || pageSize == 0) {
            throw new BusinessException("每页条数不能为空");
        }
        pageNumber = (pageNumber - 1) * pageSize;
        List<Goods> list = consumDao.consumeGoodsList(pageNumber, pageSize, searchId, searchName);
        int count = consumDao.getAllGoodsInfoCount(searchId, searchName);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("count", count);
        return map;
    }


}
