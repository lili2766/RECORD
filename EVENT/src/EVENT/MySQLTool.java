package EVENT;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class MySQLTool {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String URL = "jdbc:mysql://localhost:3306/event";
    private Connection conn = null;//数据库对象
    private PreparedStatement stmt = null;//预编译SQL语句

    public MySQLTool() {
        /*
         *连接数据库
         */
        try {
            Class.forName(driver);//通过class.forname加载驱动程序
            conn = DriverManager.getConnection(URL, "root", "123456");
            conn.setAutoCommit(false);//关闭自动提交,选择此选项美国语句执行完后conn.commit;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void close() throws SQLException {
        conn.commit();
        stmt.close();


    }

    /*
    插入数据
     */
    public boolean insert(EVENT event) {

        boolean flag = false;
        String sql = "insert into event.eventa values (?,?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, event.getId());
            stmt.setString(2, event.getEventName());
            stmt.setString(3, event.getEventType());
            stmt.setLong(4, event.getStartTime());
            stmt.setLong(5, event.getEndTime());
            stmt.setDouble(6, event.getUseTime());
            stmt.setString(7, event.getRemark());
            if (stmt.executeUpdate() != 0) {//提交语句
                flag = true;

            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /*
    以id删除数据
     */
    public boolean deleteByID(int id) {
        boolean flag = false;
        String sql = "delete from event.event where id =?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);//执行sql语句
            if (stmt.executeUpdate() != 0) {
                flag = true;

            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /*
    改数据
     */
    public boolean updateData(int id, int toid) {
        boolean flag = false;
        String sql = "update event.eventa set id=? where id=?";
        try {
            stmt = conn.prepareStatement(sql);
            //      stmt.setString(1, "usetime");
            stmt.setInt(1, toid);
            stmt.setInt(2, id);
            if (stmt.executeUpdate() != 0) {
                flag = true;
            }
            conn.commit();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }

    public boolean updateDataEventName(int id, String eventname) {
        boolean flag = false;
        String sql = "update event.eventa set eventname=? where id=?";
        try {
            stmt = conn.prepareStatement(sql);
            //      stmt.setString(1, "usetime");
            stmt.setString(1, eventname);
            stmt.setInt(2, id);
            if (stmt.executeUpdate() != 0) {
                flag = true;
            }
            conn.commit();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }

    public boolean updateDataEventType(int id, String eventtype) {
        boolean flag = false;
        String sql = "update event.eventa set eventtype=? where id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, eventtype);
            stmt.setInt(2, id);
            if (stmt.executeUpdate() != 0) {
                flag = true;
            }
            conn.commit();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }
    public boolean updateDataStartTime(int id, long startTime) {
        boolean flag = false;
        String sql = "update event.eventa set starttime=? where id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, startTime);
            stmt.setInt(2, id);
            if (stmt.executeUpdate() != 0) {
                flag = true;
            }
            conn.commit();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }
    public boolean updateDataEndTime(int id, long endTime) {
        boolean flag = false;
        String sql = "update event.eventa set endtime=? where id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, endTime);
            stmt.setInt(2, id);
            if (stmt.executeUpdate() != 0) {
                flag = true;
            }
            conn.commit();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }
    public boolean updateDataUseTime(int id, double useTime) {
        boolean flag = false;
        String sql = "update event.eventa set usetime=? where id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, useTime);
            stmt.setInt(2, id);
            if (stmt.executeUpdate() != 0) {
                flag = true;
            }
            conn.commit();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }
    public boolean updateDataRemark(int id, String remark) {
        boolean flag = false;
        String sql = "update event.eventa set remark=? where id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, remark);
            stmt.setInt(2, id);
            if (stmt.executeUpdate() != 0) {
                flag = true;
            }
            conn.commit();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }
    public ArrayList<EVENT> queryAll() {
        ArrayList<EVENT> eventArrayList = null;
        String sql = "select *from event.eventa";
        ResultSet resultSet = null;

        try {
            stmt = conn.prepareStatement(sql);
            //调用PreparedStatement实例的executeQuery方法，将结果存入ResultSet实例中
            resultSet = stmt.executeQuery();
            eventArrayList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String eventName = resultSet.getString(2);
                String eventType = resultSet.getString(3);
                long startTime = resultSet.getLong(4);
                long endTime = resultSet.getLong(5);
                double useTime = resultSet.getDouble(6);
                String remark = resultSet.getString(7);
                eventArrayList.add(new EVENT(id, eventName, eventType, startTime, endTime, useTime, remark));
            }
            resultSet.close();
            close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventArrayList;

    }
    /*
    以事件名称模糊查询
     */
    public ArrayList<EVENT> queryEventName(String eventname) {
        ArrayList<EVENT> eventArrayList = null;
        String sql = "select *from event.eventa where eventname like ?";
        ResultSet resultSet = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"%"+eventname+"%");
            //调用PreparedStatement实例的executeQuery方法，将结果存入ResultSet实例中
            resultSet = stmt.executeQuery();
            eventArrayList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String eventName = resultSet.getString(2);
                String eventType = resultSet.getString(3);
                long startTime = resultSet.getLong(4);
                long endTime = resultSet.getLong(5);
                double useTime = resultSet.getDouble(6);
                String remark = resultSet.getString(7);
                eventArrayList.add(new EVENT(id, eventName, eventType, startTime, endTime, useTime, remark));
            }
            resultSet.close();
            close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventArrayList;

    }

    public ArrayList<EVENT> selectAll() {
        ArrayList<EVENT> stuList = null;
        //结果集，用来存储与操作查询到的多个结果
        ResultSet set = null;
        //SQL语句，学号与Java成绩为变量
        String sql = "SELECT * FROM event.eventa;";
        try {
            stmt = conn.prepareStatement(sql);
            //调用PreparedStatement实例的executeQuery方法，将结果存入ResultSet实例中
            set = stmt.executeQuery();
            stuList = new ArrayList<>();

            while (set.next()) {
                //通过getInt方法查询结果集中的某条记录的值
                int id = set.getInt(1);
                String name = set.getString(2);
                String typea = set.getString(3);
                long stime = set.getLong(4);
                long etime = set.getLong(5);
                double utime = set.getDouble(6);
                String remark = set.getString(7);
                stuList.add(new EVENT(id, name, typea, stime, etime, utime, remark));
            }

            set.close();
            stmt.close();
            conn.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Error");
        }
        return stuList;
    }

    public static void main(String[] args) {
        MySQLTool mySQLTool = new MySQLTool();
        //  mySQLTool.deleteByID(25);
        mySQLTool.queryEventName("吃");
        // EVENT event = new EVENT(1, "吃饭", "说活", 12, 23, 2.1, "ky");
        System.out.println(mySQLTool.selectAll());
        System.out.println(mySQLTool.queryAll());
      //  mySQLTool.updateDataStartTime(9, 88);
        Date date = new Date();
        System.out.println();
        System.out.println(date.getTime());
        System.out.println(new Timestamp(0));
        System.out.println(new Timestamp(date.getTime()));
        //    Statement statement=null;
        //  Connection connection=null;
//        String sql = "insert into event.eventa values (3,null,null,null,null,null,null)";
//        try {
//            connection = DriverManager.getConnection(mySQLTool.URL, "root", "123456");
//            statement = connection.createStatement();
//            statement.executeUpdate(sql);
//           mySQLTool.conn.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }
}
