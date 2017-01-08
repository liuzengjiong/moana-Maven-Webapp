package org.moana.services;

import java.util.List;
import java.util.Map;

import org.moana.bean.UserOrder;
import org.moana.util.Result;


public interface OrderService {
    
	Result addOrder(UserOrder order);
	List<Map<Object,Object>> getListByPage(int page,int rows);
    int countTotal(String userId);
}
