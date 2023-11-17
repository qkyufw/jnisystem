package com.dao.impl;

import com.dao.VolunteerDao;
import com.util.DBUtil;
import org.junit.Test;
import pojo.Vista;
import pojo.Volunteer;
import pojo.VolunteeringForMine;
import pojo.VolunteeringForVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VolunteerDaoImpl implements VolunteerDao {
    @Override
    public Volunteer login(int uid, String pwd) {  //输入账号密码，返回数据
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        Volunteer vo = null;
        try {
            pstmt = conn.prepareStatement("select * from jnisystem.volunteer where ID = ? and  pwd = ?");
            pstmt.setInt(1, uid);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vo = new Volunteer();
                vo.setUid(rs.getInt("ID"));
                vo.setName(rs.getString("name"));
                vo.setPhone(rs.getString("phone"));
                vo.setIphone(rs.getString("iphone"));
                vo.setCollege(rs.getString("college"));
                vo.setKind(rs.getString("kind"));
                vo.setPwd(rs.getString("pwd"));
                vo.setSex(rs.getInt("sex"));
                vo.setAge(rs.getInt("age"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return vo;
    }

    @Override
    public Volunteer selectOne(int uid) {  //查询单个志愿者
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        Volunteer vo = null;
        try {
            pstmt = conn.prepareStatement("select * from jnisystem.volunteer where ID = ?");
            pstmt.setInt(1, uid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                //将查出的数据保存到对象中
                vo = new Volunteer();
                vo.setUid(rs.getInt("ID"));
                vo.setName(rs.getString("name"));
                vo.setPhone(rs.getString("phone"));
                vo.setIphone(rs.getString("iphone"));
                vo.setCollege(rs.getString("college"));
                vo.setKind(rs.getString("kind"));
                vo.setPwd(rs.getString("pwd"));
                vo.setSex(rs.getInt("sex"));
                vo.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }

        return vo;
    }

    @Override
    public int insert(Volunteer volunteer) {
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            pstmt = conn.prepareStatement("insert into jnisystem.volunteer VALUES (?,?,?,?,?,?,?,?,?)");
            pstmt.setObject(1, volunteer.getUid());
            pstmt.setObject(2, volunteer.getName());
            pstmt.setObject(3, volunteer.getPhone());
            pstmt.setObject(4, volunteer.getIphone());
            pstmt.setObject(5, volunteer.getCollege());
            pstmt.setObject(6, volunteer.getKind());
            pstmt.setObject(7, volunteer.getPwd());
            pstmt.setObject(8, volunteer.getSex());
            pstmt.setObject(9, volunteer.getAge());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return count;
    }

    @Override
    public int update(Volunteer vo) {
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            //不允许更改ID与name，其他七项均可以更改
            pstmt = conn.prepareStatement("update jnisystem.volunteer set phone = ?, iphone = ?, college = ?, kind = ?, pwd = ?, sex = ?, age = ? where ID = ?");
            pstmt.setObject(1, vo.getPhone());
            pstmt.setObject(2, vo.getIphone());
            pstmt.setObject(3, vo.getCollege());
            pstmt.setObject(4, vo.getKind());
            pstmt.setObject(5, vo.getPwd());
            pstmt.setObject(6, vo.getSex());
            pstmt.setObject(7, vo.getAge());
            pstmt.setObject(8, vo.getUid());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return count;
    }

    @Override
    public int delete(int uid) {  //根据uid删除对应数据
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("delete from jnisystem.team_list where vo_id = ?");
            pstmt.setInt(1, uid);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("delete from jnisystem.v_application where vo_id = ?");
            pstmt.setInt(1, uid);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("delete from jnisystem.volunteer where ID = ?");
            pstmt.setObject(1, uid);
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
    public List<Vista> selectAllVista() {  //查看所有的服务队列表
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        List<Vista> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement("select * from jnisystem.vista where status > 0");
            ResultSet rs = pstmt.executeQuery();

            //从rs中取数据
            while (rs.next()) {  //密码状态数据不显示
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
                //将当前对象保存到集合中
                list.add(vista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return list;
    }

    @Override
    public List<Vista> selectMy(int id) {
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        List<Vista> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement("select * from jnisystem.我的服务队 where jnisystem.我的服务队.ID = ? ");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            //从rs中取数据
            while (rs.next()) {  //密码状态数据不显示
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
                //将当前对象保存到集合中
                list.add(vista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return list;
    }

    @Override
    public int addTeam(int vi_id, int vo_id) {  //输入队id，志愿者id申请加入服务队,状态默认为0
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            pstmt = conn.prepareStatement("insert into jnisystem.team_list(vi_id, vo_id) VALUES (?,?)");
            pstmt.setInt(1, vi_id);
            pstmt.setInt(2, vo_id);

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }
        return count;
    }

    @Override
    public List<VolunteeringForVo> read() {  //志愿者通过这个方法查看还可以报名的所有活动
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        List<VolunteeringForVo> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement("select  * from jnisystem.活动中心");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                VolunteeringForVo voing = new VolunteeringForVo();

                voing.setNumber(rs.getInt("number"));
                voing.setName(rs.getString("name"));
                voing.setDate(rs.getDate("time"));
                voing.setPlace(rs.getString("place"));
                voing.setSumOP(rs.getInt("sumOP"));
                voing.setDescribe(rs.getString("describe"));
                voing.setTap(rs.getString("tap"));
                voing.setVista_id(rs.getInt("vista_id"));
                voing.setVi_name(rs.getString("vi_name"));
                voing.setContact(rs.getString("contact"));

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
    public int signVoing(int vo_id, int voing_id) {  //需要服务队id 活动id 志愿者id来报名添加进v_application表,并在活动的sumOP中-1
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("insert into jnisystem.v_application(vo_id, voing_id) VALUES (?,?)");
            pstmt.setInt(1, vo_id);
            pstmt.setInt(2, voing_id);
            count = pstmt.executeUpdate();
            pstmt = conn.prepareStatement("update jnisystem.volunteering set sumOP = sumOP - 1 where number = ?");
            pstmt.setInt(1, voing_id);
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
    public List<VolunteeringForMine> readMine(int myid) {
        Connection conn = DBUtil.getConn();  //已经封装完成
        PreparedStatement pstmt = null;
        List<VolunteeringForMine> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement("select * from jnisystem.我的活动 where myid = ? order by time");
            pstmt.setInt(1, myid);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                VolunteeringForMine voing = new VolunteeringForMine();
                voing.setMyid(rs.getInt("myid"));
                voing.setVoing_id(rs.getInt("voing_id"));
                voing.setVoing_name(rs.getString("voing_name"));
                voing.setTap(rs.getString("tap"));
                voing.setDescribe(rs.getString("describe"));
                voing.setDate(rs.getDate("time"));
                voing.setPlace(rs.getString("place"));
                voing.setContact(rs.getString("contact"));
                voing.setVi_name(rs.getString("vi_name"));

                list.add(voing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(conn, pstmt);
        }

        return list;
    }

    @Test
    public void test() {  //单元测试
//        Volunteer vo = selectOne(1);
//        vo.setKind("外");
        System.out.println(selectMy(1));
//        System.out.println(readMine(1));
    }
}
