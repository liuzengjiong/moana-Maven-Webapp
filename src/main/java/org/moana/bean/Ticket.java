package org.moana.bean;

import java.util.Date;

public class Ticket {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.id
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.movie_name
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    private String movieName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.price
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    private Long price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.remain_num
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    private Integer remainNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.total_num
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    private Integer totalNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.display_time
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    private String displayTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.cinema_name
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    private String cinemaName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.update_time
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.id
     *
     * @return the value of ticket.id
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.id
     *
     * @param id the value for ticket.id
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.movie_name
     *
     * @return the value of ticket.movie_name
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.movie_name
     *
     * @param movieName the value for ticket.movie_name
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName == null ? null : movieName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.price
     *
     * @return the value of ticket.price
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public Long getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.price
     *
     * @param price the value for ticket.price
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.remain_num
     *
     * @return the value of ticket.remain_num
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public Integer getRemainNum() {
        return remainNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.remain_num
     *
     * @param remainNum the value for ticket.remain_num
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.total_num
     *
     * @return the value of ticket.total_num
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.total_num
     *
     * @param totalNum the value for ticket.total_num
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.display_time
     *
     * @return the value of ticket.display_time
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public String getDisplayTime() {
        return displayTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.display_time
     *
     * @param displayTime the value for ticket.display_time
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public void setDisplayTime(String displayTime) {
        this.displayTime = displayTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.cinema_name
     *
     * @return the value of ticket.cinema_name
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public String getCinemaName() {
        return cinemaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.cinema_name
     *
     * @param cinemaName the value for ticket.cinema_name
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName == null ? null : cinemaName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.update_time
     *
     * @return the value of ticket.update_time
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.update_time
     *
     * @param updateTime the value for ticket.update_time
     *
     * @mbggenerated Sat Jan 07 13:50:31 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}