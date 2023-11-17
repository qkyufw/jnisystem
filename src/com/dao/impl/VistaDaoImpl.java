package com.dao.impl;

import com.util.DBUtil;
import org.junit.Test;
import pojo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VistaDaoImpl implements com.dao.VistaDao {
    @Override
    public Vista login(int uid, String pwd) {  //输入账号密码，确认status不为0可登录，放回vista类
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        Vista vista = null;

        try {
            pstmt = conn.prepareStatement("select * from jnisystem.vista where number = ? and pwd = ? and status > 0");
            pstmt.setInt(1, uid);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vista = new Vista();
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return vista;
    }

    @Override
    public Vista selectOne(int number) {
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        Vista vista = null;
        try {
            pstmt = conn.prepareStatement("select * from jnisystem.vista where number = ?");
            pstmt.setInt(1, number);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vista = new Vista();
                vista.setNumber(number);
                vista.setName(rs.getString("name"));
                vista.setType(rs.getString("type"));
                vista.setDescribe(rs.getString("describe"));
                vista.setSumOP(rs.getInt("sumOP"));
                vista.setName_leader(rs.getString("name_leader"));
                vista.setPhone_leader(rs.getString("phone_leader"));
                vista.setID_leader(rs.getInt("ID_leader"));
                vista.setCollege(rs.getString("college"));
                vista.setPwd(rs.getString("pwd"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return vista;
    }

    @Override
    public int sign(Vista vista) {  //注册
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = conn.prepareStatement("insert into jnisystem.vista(name, type, `describe`, sumOP, name_leader, phone_leader, ID_leader, college, pwd) VALUES (?,?,?,?,?,?,?,?,?)");
            pstmt.setObject(1, vista.getName());
            pstmt.setObject(2, vista.getType());
            pstmt.setObject(3, vista.getDescribe());
            pstmt.setObject(4, vista.getSumOP());
            pstmt.setObject(5, vista.getName_leader());
            pstmt.setObject(6, vista.getPhone_leader());
            pstmt.setObject(7, vista.getID_leader());
            pstmt.setObject(8, vista.getCollege());
            pstmt.setObject(9, vista.getPwd());

            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("select number from jnisystem.vista where name = ?");
            pstmt.setObject(1, vista.getName());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return count;
    }

    @Override
    public int update(Vista vista) {  //通过编号修改八项基本数据，sumOP与status不能改变
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            pstmt = conn.prepareStatement("update jnisystem.vista set name = ?, type = ?, `describe` = ?, name_leader = ?, phone_leader = ?, ID_leader = ?, college = ?, pwd = ? where number = ?");
            pstmt.setObject(1, vista.getName());
            pstmt.setObject(2, vista.getType());
            pstmt.setObject(3, vista.getDescribe());
            pstmt.setObject(4, vista.getName_leader());
            pstmt.setObject(5, vista.getPhone_leader());
            pstmt.setObject(6, vista.getID_leader());
            pstmt.setObject(7, vista.getCollege());
            pstmt.setObject(8, vista.getPwd());
            pstmt.setObject(9, vista.getNumber());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return count;
    }

    @Override
    public int insertVoing(Volunteering volunteering) {
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            pstmt = conn.prepareStatement("insert into jnisystem.volunteering(name, time, place, `describe`, tap, vista_id, sumOP, contact) VALUES (?,?,?,?,?,?,?,?) ");
            pstmt.setObject(1, volunteering.getName());
            pstmt.setObject(2, volunteering.getDate());
            pstmt.setObject(3, volunteering.getPlace());
            pstmt.setObject(4, volunteering.getDescribe());
            pstmt.setObject(5, volunteering.getTap());
            pstmt.setObject(6, volunteering.getVista_id());
            pstmt.setObject(7, volunteering.getSumOP());
            pstmt.setObject(8, volunteering.getContact());
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("select * from jnisystem.volunteering where name = ?");
            pstmt.setObject(1, volunteering.getName());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return count;
    }

    @Override
    public Volunteering selectOneVoing(int number) {
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        Volunteering voing = null;
        try {
            pstmt = conn.prepareStatement("select * from jnisystem.volunteering where number = ?");
            pstmt.setInt(1, number);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                voing = new Volunteering();
                voing.setNumber(number);
                voing.setName(rs.getString("name"));
                voing.setPlace(rs.getString("place"));
                voing.setDescribe(rs.getString("describe"));
                voing.setTap(rs.getString("tap"));
                voing.setVista_id(rs.getInt("vista_id"));
                voing.setSumOP(rs.getInt("sumOP"));
                voing.setContact(rs.getString("contact"));
                voing.setData(rs.getDate("time"));
                }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return voing;
    }

    @Override
    public int updateVoing(Volunteering voing) {  //修改活动表
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            pstmt = conn.prepareStatement("update jnisystem.volunteering set name = ?, time = ?, place = ?, `describe` = ?, tap = ?, sumOP = ?, contact = ? where number = ?");
            pstmt.setObject(1, voing.getName());
            pstmt.setObject(2, voing.getDate());
            pstmt.setObject(3, voing.getPlace());
            pstmt.setObject(4, voing.getDescribe());
            pstmt.setObject(5, voing.getTap());
            pstmt.setObject(6, voing.getSumOP());
            pstmt.setObject(7, voing.getContact());
            pstmt.setObject(8, voing.getNumber());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }


        return count;
    }

    @Override
    public int deleteVoing(int number) {
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        int count = 0;
        try{
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("delete from jnisystem.v_application where voing_id = ?");
            pstmt.setInt(1, number);
            count = pstmt.executeUpdate();
            pstmt = conn.prepareStatement("delete from jnisystem.volunteering where number = ?");
            pstmt.setInt(1, number);
            count = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return count;
    }

    @Override
    public List<Volunteering> readVoing(int vista_id) {
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        List<Volunteering> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement("select * from jnisystem.volunteering where vista_id = ?");
            pstmt.setInt(1, vista_id);
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
                voing.setVista_id(vista_id);
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
    public List<VistaVoingVo> readVoingVo(int voing_id) {
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        List<VistaVoingVo> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement("select * from jnisystem.服务队的活动 where voing_id = ?");
            pstmt.setInt(1,voing_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VistaVoingVo voing = new VistaVoingVo();
                voing.setVoing_id(rs.getInt("voing_id"));
                voing.setVoing_name(rs.getString("voing_name"));
                voing.setVo_id(rs.getInt("vo_id"));
                voing.setVo_name(rs.getString("vo_name"));
                voing.setPhone(rs.getString("phone"));
                voing.setIphone(rs.getString("iphone"));
                voing.setSex(rs.getInt("sex"));
                voing.setAge(rs.getInt("age"));
                voing.setKind(rs.getString("kind"));

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
    public List<VistaVo> readVo(int vista_id) {
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        List<VistaVo> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement("select * from jnisystem.服务队队员信息表 where vi_id = ?");
            pstmt.setInt(1, vista_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VistaVo voing = new VistaVo();
                voing.setVi_id(rs.getInt("vi_id"));
                voing.setVo_id(rs.getInt("vo_id"));
                voing.setVo_name(rs.getString("vo_name"));
                voing.setKind(rs.getString("kind"));
                voing.setSex(rs.getInt("sex"));
                voing.setAge(rs.getInt("age"));
                voing.setIphone(rs.getString("iphone"));
                voing.setPhone(rs.getString("phone"));
                voing.setCollege(rs.getString("college"));
                voing.setStatus(rs.getInt("status"));

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
    public int updateTeam(int vi_id, int vo_id) {
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("update jnisystem.team_list set status = 1 where vi_id = ? and vo_id = ?");
            pstmt.setInt(1, vi_id);
            pstmt.setInt(2, vo_id);
            count = pstmt.executeUpdate();
            pstmt = conn.prepareStatement("update jnisystem.vista set sumOP = sumOP + 1 where vista.number = ?");
            pstmt.setInt(1, vi_id);
            count = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return count;
    }

    @Override
    public int deleteTeam(int vi_id, int vo_id) {
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("select * from jnisystem.team_list where vi_id = ? and vo_id = ?");
            pstmt.setInt(1, vi_id);
            pstmt.setInt(2, vo_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if(rs.getInt("status") != 0) {
                    System.out.println(rs.getInt("status"));
                    pstmt = conn.prepareStatement("update jnisystem.vista set sumOP = sumOP - 1 where number = ?");
                    pstmt.setInt(1, vi_id);
                    pstmt.executeUpdate();
                }
            }
            pstmt = conn.prepareStatement("delete from jnisystem.team_list where vi_id = ? and vo_id = ?");
            pstmt.setInt(1, vi_id);
            pstmt.setInt(2, vo_id);
            count = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return count;
    }

    @Test
    public void test() {
//        Vista vista = new Vista(3, "医青服务队", "医疗", "基础医疗知识教育", 20, "张宇", "120", 9, "医学院", "123456", 0);
//        Volunteering voing = new Volunteering(2,"ADE培训", Date.valueOf("2022-11-13"),"暨南大学", "医疗", 3, 9, "120", "学习ADE");
        System.out.println(deleteVoing(5));
    }
}
