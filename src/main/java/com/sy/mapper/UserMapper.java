package com.sy.mapper;

import com.sy.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where CONCAT(name,sex,username,password,tel,vip,role,address,status) like CONCAT('%',#{name},'%')")
    List<User> getUsersList(String name);

    @Insert("insert into user (id,name,sex,username,password,tel,vip,role,address,status) " +
            "values (#{id},#{name},#{sex},#{username},#{password},#{tel},#{vip},#{role},#{address},#{status})")
    public int add(User user);

    @Delete(" delete from user where id= #{id} ")
    public void del(int id);

    @Select("select * from user where id= #{id} ")
    public User getUser(int id);

    @Update("update users set " +
            "name = #{name}," +
            "sex = #{sex}," +
            "username = #{username}," +
            "password = #{password}," +
            "tel = #{tel}" +
            "vip = #{vip}" +
            "role = #{role}" +
            "address = #{address}" +
            "status = #{status}"
           )
    public int update(User user);
}
