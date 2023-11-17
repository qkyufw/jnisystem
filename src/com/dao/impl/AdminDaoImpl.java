package com.dao.impl;

import com.dao.AdminDao;
import com.util.DBUtil;
import pojo.Vista;
import pojo.Volunteer;
import pojo.Volunteering;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public List<Volunteer> selectAllVo() {  //查询所有表
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        List<Volunteer> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select * from jnisystem.volunteer");
            ResultSet rs = pstmt.executeQuery();

            //从rs中取出数据
            while (rs.next()) {
                //将查出的数据保存到对象中
                Volunteer vo = new Volunteer();
                vo.setUid(rs.getInt("ID"));
                vo.setName(rs.getString("name"));
                vo.setPhone(rs.getString("phone"));
                vo.setIphone(rs.getString("iphone"));
                vo.setCollege(rs.getString("college"));
                vo.setKind(rs.getString("kind"));
                vo.setPwd(rs.getString("pwd"));
                vo.setSex(rs.getInt("sex"));
                vo.setAge(rs.getInt("age"));
                //把当前对象保存到集合里
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {  //关闭
            DBUtil.closeAll(conn, pstmt);
        }
        return list;
    }

    @Override
    public List<Vista> selectAllVista() {
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        List<Vista> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement("select * from jnisystem.vista");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Vista vista = new Vista();
                vista.setNumber(rs.getInt("number"));
                vista.setName(rs.getString("name"));
                vista.setType(rs.getString("type"));
                vista.setDescribe(rs.getString("describe"));
                vista.setSumOP(rs.getInt("sumOP"));
                vista.setName_leader(rs.getString("name_leader"));
                vista.setPhone_leader(rs.getString("phone_leader"));
                vista.setID_leader(rs.getInt("ID_leader"));
                vista.setCollege(rs.getString("college"));
                vista.setPwd(rs.getString("pwd"));
                vista.setStatus(rs.getInt("status"));
                list.add(vista);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return list;
    }

    @Override
    public List<Volunteering> selectAllVoing() {  //找出所有的活动
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        List<Volunteering> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select * from jnisystem.volunteering");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Volunteering voing = new Volunteering();
                voing.setNumber(rs.getInt("number"));
                voing.setName(rs.getString("name"));
                voing.setData(rs.getDate("time"));
                voing.setPlace(rs.getString("place"));
                voing.setDescribe(rs.getString("describe"));
                voing.setTap(rs.getString("tap"));
                voing.setSumOP(rs.getInt("sumOP"));
                voing.setContact(rs.getString("contact"));
                voing.setVista_id(rs.getInt("vista_id"));
                list.add(voing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return list;
    }

    @Override
    public int addVista(int vi_id) {
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            pstmt = conn.prepareStatement("update jnisystem.vista set status = 1 where number = ?");
            pstmt.setInt(1, vi_id);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return count;
    }

    @Override
    public int deleteVista(int vi_id) {
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = conn.prepareStatement("delete from jnisystem.vista where number = ?");
            pstmt.setInt(1, vi_id);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return count;
    }
}
