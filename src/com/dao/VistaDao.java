package com.dao;

import pojo.Vista;
import pojo.VistaVo;
import pojo.VistaVoingVo;
import pojo.Volunteering;

import java.util.List;

public interface VistaDao {
    //登录,根据账号密码来查找，且状态不能为0（即审核通过）
    Vista login(int uid, String pwd);

    Vista selectOne(int uid);

    //注册，返回服务队编号
    int sign(Vista vista);

    //修改基本信息
    int update(Vista vista);

    //发布活动，插入活动表
    int insertVoing(Volunteering volunteering);

    //根据活动编号查找活动
    Volunteering selectOneVoing(int number);

    //修改活动
    int updateVoing(Volunteering volunteering);

    //删除活动
    int deleteVoing(int number);

    //通过服务队id查看该服务队的活动表
    List<Volunteering> readVoing(int vista_id);

    //通过服务队id和活动id查看报名表
    List<VistaVoingVo> readVoingVo(int voing_id);

    //通过服务队id查看队员表
    List<VistaVo> readVo(int vista_id);

    //修改队员表,通过服务队id与志愿者id审核通过队员
    int updateTeam(int vi_id, int vo_id);

    //删除队员
    int deleteTeam(int vi_id, int vo_id);
}
