package org.moana.api;
/**
 * @TODO：订单
 * 
 */


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.moana.bean.User;
import org.moana.bean.UserOrder;
import org.moana.services.OrderService;
import org.moana.services.UserService;
import org.moana.util.Config;
import org.moana.util.IDFactory;
import org.moana.util.MD5Util;
import org.moana.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@RequestMapping("/order")
@Controller
public class OrderApi {
    private static final Logger LOG = LoggerFactory.getLogger(OrderApi.class);
    public User user;
    private HttpServletRequest request;

    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;

    /**
     * 用于验证用户是否已经登录 使用cookie进行验证
     *
     * @param usercode
     *         用户码
     * @param account
     *         账号
     */
    @ModelAttribute
    public void getUser(@CookieValue("userCode") String usercode, @CookieValue("account") String account, HttpServletRequest request) {
        LOG.info("getUser-data:{userCode:" + usercode + ",account:" + account + "}");
        this.request = request;
        user = userService.getUser(account);
        if (null != user) {
            // 获取 MD5
            String md5 = MD5Util.getMD5(user);
            if (!usercode.equals(md5)) {
                user = null;
            }
        }
    }

    /**
     * 添加订单
     *
     * @return 
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String getList(UserOrder order) {
    	LOG.info("order:"+JSONObject.toJSONString(order));
        Result result = new Result();
        if (null == user) {
        	result.setCode(Config.USER_UNLOGIN);
        	result.setMsg(Config.USER_UNLOGIN_MSG);
        	return JSONObject.toJSONString(result);
        }
        order.setUserId(user.getId());
        order.setId(IDFactory.newID());
        order.setPurchaseTime(new Date());
        result = orderService.addOrder(order);
        String str = JSONObject.toJSONString(result);
        LOG.info(str);
        
        return str;
    }
    
    /**
     * 获取列表
     *
     * @return 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public String getList(int page,int rows) {
    	JSONObject jsonObject = new JSONObject();
    	Result result = new Result();
    	result.setCode(Config.OPERATE_FAILURE);
    	
        if (null == user) {
        	result.setMsg("您还未登录");
        	return JSONObject.toJSONString(result);
        }
        jsonObject.put("username", user.getNickname());
        
        List<Map<Object,Object>> orders = orderService.getListByPage(page, rows);
        int total = orderService.countTotal(user.getId());
        jsonObject.put("list", orders);
        jsonObject.put("total", total);
        result.setCode(Config.OPERATE_SUCCESS);
        result.setMsg("获取成功");
        result.setData(jsonObject);
        
        LOG.info(JSONObject.toJSONString(result));
        
        return JSONObject.toJSONString(result);
    }
}

