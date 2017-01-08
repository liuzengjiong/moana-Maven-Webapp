package org.moana.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @TODO：
 * @fileName : org.moana.util.DateUtil.java
 * date | author | version |   
 * 2017年1月8日 | Jiong | 1.0 |
 */
public class MDateUtil {
      /** 
       * 时间戳转换成日期格式字符串 
       * @param seconds 精确到毫秒的字符串 
       * @param formatStr 
       * @return 
      */  
     public static String timeStamp2Date(String seconds,String format) {  
         if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
             return "";  
         }  
         if(format == null || format.isEmpty()){
             format = "yyyy-MM-dd HH:mm:ss.S";
         }   
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
         return sdf.format(new Date(Long.valueOf(seconds)/1000));  
     } 
}

