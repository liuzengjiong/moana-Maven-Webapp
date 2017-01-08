package org.moana.api;
/**
 * @TODO：电影票
 * 
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;

import org.moana.bean.Ticket;
import org.moana.bean.User;
import org.moana.dao.TicketMapper;
import org.moana.redis.RedissonFactory;
import org.moana.services.TicketService;
import org.moana.services.UserService;
import org.moana.util.MD5Util;
import org.redisson.Redisson;
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

@RequestMapping("/ticket")
@Controller
public class TicketApi {
    private static final Logger LOG = LoggerFactory.getLogger(TicketApi.class);
    public User user;
    private HttpServletRequest request;

    
    @Autowired
    private TicketService ticketService;
    
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
     * 获取列表
     *
     * @return 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public String getList(int page,int rows) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        if (null != user) {
        	jsonObject.put("username", user.getNickname());
        }
        List<Ticket> tickets = ticketService.getList(page, rows);
        int total = ticketService.countTotal();
        jsonObject.put("code", "1");
        jsonObject.put("size", tickets.size());
        jsonObject.put("list", tickets);
        jsonObject.put("total", total);
        LOG.info(jsonObject.toString());
        
        return jsonObject.toJSONString();
    }
    
    /**
     * 刷新剩余票数
     *
     * @return 列表
     */
    @RequestMapping(value = "/remainNum", method = RequestMethod.GET)
    @ResponseBody
    public String getRemainNum(String[] ids) {
    	Redisson redis;
    	JSONObject jsonObject = new JSONObject();
    	try{
    		redis = RedissonFactory.getRedisson();
    	}catch(Exception e){
    		 jsonObject.put("code", "0");
    		 jsonObject.put("msg", "redis服务器连接失败，检查服务是否启动");
    		 return jsonObject.toJSONString();
    	}
    	List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
    	for(int i=0;i<ids.length; i++){
    		ConcurrentMap<String, Object> map = redis.getMap(ids[i]);
    		//未存入到redis,或标志位已被置为dirty，需重新写入redis
    		if(map.get("id") == null || "".equals(map.get("id"))
    				||map.get("flag").equals("dirty")){
    			LOG.info("----------------------重新写入redis");
    			Ticket ticket = ticketService.getTicketById(ids[i]);
    			map.put("id",ids[i]);
    			map.put("remainNum",ticket.getRemainNum());
    			map.put("flag", "ok");
    			result.add(map);
    		}else{
    			LOG.info("----------------------从redis中取数据");
    			result.add(map);
    		}
    	}
    	
    	
        
        jsonObject.put("code", "1");
        jsonObject.put("size", result.size());
        jsonObject.put("list", result);
        LOG.info(jsonObject.toString());
        
        return jsonObject.toJSONString();
    }
   

}

