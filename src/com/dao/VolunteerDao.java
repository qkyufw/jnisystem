package com.dao;

import pojo.*;

import java.util.List;

public interface VolunteerDao {

    //OK登录
    Volunteer login(int uid, String pwd);

    //不需要 查询单个用户实现  返回值(对象） 参数（ID） select * from volunteer where ID =
    Volunteer selectOne(int uid);

    //添加修改删除返回值int
    //OK 添加/注册 insert into volunteering values(?,?,?...,?)
    int insert(Volunteer volunteer);

    //修改 update volunteer set xx=?, xx=? where=?
    //根据id修改单个志愿者的七项信息
    int update(Volunteer volunteer);
    //注销操作，根据UID删除单个志愿者所有信息
    int delete(int uid);

    //查看服务队表
    List<Vista> selectAllVista();

    List<Vista> selectMy(int id);

    //加入服务队,插入服务队队员表，成功返回1，失败返回0
    int addTeam(int vi_id, int vo_id);

    //查看活动中心，通过活动中心的视图，只查看不更改
    List<VolunteeringForVo> read();

//    List<VolunteeringForVo> search(int)

    //报名活动，插入活动
    int signVoing(int vo_id, int voing_id);

    //查看历史活动，通过我的id查看我的活动的视图，只查看，不更改
    List<VolunteeringForMine> readMine(int myid);
    //我的组织（暂时不实现）
}
