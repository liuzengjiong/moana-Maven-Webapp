package org.moana.services.impl;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.moana.bean.Ticket;
import org.moana.bean.UserOrder;
import org.moana.dao.TicketMapper;
import org.moana.dao.UserOrderMapper;
import org.moana.redis.RedissonFactory;
import org.moana.services.OrderService;
import org.moana.util.Config;
import org.moana.util.Result;
import org.redisson.Redisson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private UserOrderMapper orderMapper;
    
    @Autowired
    private TicketMapper ticketMapper;
    
	@Override
	public Result addOrder(UserOrder order) {
		 Result result = new Result();
		 result.setCode(0);
		 
		 Ticket ticket = ticketMapper.selectByPrimaryKey(order.getTicketId());
		 if(null == ticket){
			 result.setMsg("获取不到库存信息,请刷新页面重试");
			 return result;
		 }
		 if(ticket.getRemainNum()<order.getPurchaseNum()){
			 result.setMsg("您的购买数量超过货存数量，当前剩余："+ticket.getRemainNum());
			 return result;
		 }
		 
		 Map<Object,Object> historyOrder =
				 orderMapper.countPurchaseNumByUserIdandTicketId(order.getUserId(),order.getTicketId());
		 
		 long historyNum = 0;
		 if(null!=historyOrder){
			 historyNum  =  Long.valueOf(historyOrder.get("sumPurchaseNum").toString());
		 }
		 
		 //超过限购数
		 if(order.getPurchaseNum() + historyNum > Config.LIMIT_PURCHASE_NUM){
			 result.setMsg("您的订单已超过限购数，您还能购买的数量为："+(Config.LIMIT_PURCHASE_NUM-historyNum));
			 return result;
		 }
		 
		 int updateNum = ticket.getRemainNum() - order.getPurchaseNum();
		 ticket.setRemainNum(updateNum);
		 ticketMapper.updateByPrimaryKey(ticket);
		 //更新ticket后redis数据为脏
		 Redisson redis = RedissonFactory.getRedisson();
		 ConcurrentMap<String, Object> map = redis.getMap(ticket.getId());
		 map.put("flag", "dirty");
		 
		 if(orderMapper.insert(order)==1){
			 result.setCode(1);
			 result.setMsg("订单添加成功");
		 }else{
			 result.setMsg("订单添加失败");
		 }
		 return result;
	}

	@Override
	public List<Map<Object,Object>> getListByPage(int page, int rows) {
		int index = (page-1)*rows;
		List<Map<Object,Object>> list = orderMapper.selectListByPages(index, rows);
		for(int i=0;i<list.size();i++){
			Map<Object,Object> map = list.get(i);
			String displayTime = map.get("displayTime")==null?"":map.get("displayTime").toString();
			map.put("displayTime",displayTime);
			String purchaseTime =  map.get("purchaseTime")==null?"": map.get("purchaseTime").toString();
			map.put("purchaseTime",purchaseTime);
		}
		return  list;
	}

	@Override
	public int countTotal(String userId) {
		
		return orderMapper.countTotal(userId);
	}




    
}
