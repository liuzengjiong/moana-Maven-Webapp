package org.moana.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.moana.bean.Ticket;

public interface TicketMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    @Delete({
        "delete from ticket",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    @Insert({
        "insert into ticket (id, movie_name, ",
        "price, remain_num, ",
        "total_num, display_time, ",
        "cinema_name, update_time)",
        "values (#{id,jdbcType=VARCHAR}, #{movieName,jdbcType=VARCHAR}, ",
        "#{price,jdbcType=DECIMAL}, #{remainNum,jdbcType=INTEGER}, ",
        "#{totalNum,jdbcType=INTEGER}, #{displayTime,jdbcType=TIMESTAMP}, ",
        "#{cinemaName,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(Ticket record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    @Select({
        "select",
        "id, movie_name, price, remain_num, total_num, display_time, cinema_name, update_time",
        "from ticket",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="movie_name", property="movieName", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="remain_num", property="remainNum", jdbcType=JdbcType.INTEGER),
        @Result(column="total_num", property="totalNum", jdbcType=JdbcType.INTEGER),
        @Result(column="display_time", property="displayTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="cinema_name", property="cinemaName", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Ticket selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    @Select({
        "select",
        "id, movie_name, price, remain_num, total_num, display_time, cinema_name, update_time",
        "from ticket"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="movie_name", property="movieName", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="remain_num", property="remainNum", jdbcType=JdbcType.INTEGER),
        @Result(column="total_num", property="totalNum", jdbcType=JdbcType.INTEGER),
        @Result(column="display_time", property="displayTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="cinema_name", property="cinemaName", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Ticket> selectAll();
    
    //TODO  分页查询
    @Select({
        "select",
        "id, movie_name, price, remain_num, total_num, display_time, cinema_name, update_time",
        "from ticket",
       // "where <![CDATA[ display_time>now() ]]>",
        "order by display_time desc",
        "limit #{0},#{1}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="movie_name", property="movieName", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="remain_num", property="remainNum", jdbcType=JdbcType.INTEGER),
        @Result(column="total_num", property="totalNum", jdbcType=JdbcType.INTEGER),
        @Result(column="display_time", property="displayTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="cinema_name", property="cinemaName", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Ticket> selectListByPages(int index,int num);
    
    //TODO  查询总数
    @Select({
        "select",
        "count(id)",
        "from ticket",
    })
    int countTotal();
    
    //TODO  剩余票数获取
    @Select({
        "select",
        "id, remain_num",
        "from ticket",
        " id in <foreach collection='ids' item='item' index='index'", 
         "open='(' separator=',' close=')'>#{list}</foreach>"
    })
    //注意 你可以传递一个 List 实例或者数组作为参数对象传给 MyBatis。
    //当你这么做的时 候,MyBatis 会自动将它包装在一个 Map 中,用名称在作为键。
    //List 实例将会以“list” 作为键,而数组实例将会以“array”作为键。
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="remain_num", property="remainNum", jdbcType=JdbcType.INTEGER),
    })
    List<Map<String,Integer>> getRemainNum(List<String> ids);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ticket
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    @Update({
        "update ticket",
        "set movie_name = #{movieName,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=DECIMAL},",
          "remain_num = #{remainNum,jdbcType=INTEGER},",
          "total_num = #{totalNum,jdbcType=INTEGER},",
          "display_time = #{displayTime,jdbcType=TIMESTAMP},",
          "cinema_name = #{cinemaName,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Ticket record);
    
    //基于配置文件的方法
    List<Map<String,String>> selectByIds(@Param("ids") List<String> ids);
}