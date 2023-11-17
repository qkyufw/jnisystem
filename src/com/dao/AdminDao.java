package com.dao;

import pojo.Vista;
import pojo.Volunteer;
import pojo.Volunteering;

import java.util.List;

public interface AdminDao {
    //查询所有用户操作  返回值（对象（1条数据）or集合（多条数据））  参数 select * from volunteer
    List<Volunteer> selectAllVo();

    //查询所有服务队
    List<Vista> selectAllVista();

    //查询所有活动
    List<Volunteering> selectAllVoing();

    //通过服务队注册
    int addVista(int vi_id);

    //删除服务队
    int deleteVista(int vi_id);
}
