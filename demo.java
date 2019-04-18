package com.gzcss.common.db;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class ITSMDBHelper {
    private static Properties prop = new Properties();
    private static String user = null;
    private static String pwd = null;
    private static String url = null;
    private static String driver = null;

    static {
        InputStream in = ITSMDBHelper.class.getClassLoader().getResourceAsStream("dbconfig.properties");
        try {
            prop.load(in);
            user = prop.getProperty("jdbc.user.itsm");
            pwd = prop.getProperty("jdbc.pwd.itsm");
            url = prop.getProperty("jdbc.url.itsm");
            driver = prop.getProperty("jdbc.driver.itsm");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取数据库链接
    public static Connection getConnection(){
        Connection conn= null;
        try {
            conn = DriverManager.getConnection(url,user,pwd);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    //执行查询,返回List
    public  static List executeQuery(String sql,Connection conn){
        conn = getConnection();
        PreparedStatement ps= null;
        ResultSet rs= null;
        List list = new ArrayList();
        try{
             ps = conn.prepareStatement(sql);
             rs = ps.executeQuery();
             ResultSetMetaData resultSetMetaData=rs.getMetaData(); //获取键名
             int rows = resultSetMetaData.getColumnCount();//获取行数
             while (rs.next()){
                 Map  map= new HashMap();
                 for (int i=1; i<= rows; i++){
                     map.put(resultSetMetaData.getColumnName(i),rs.getObject(i));
                 }
                 list.add(map);
             }
             String insertSql="insert into kpi_monitor_1 value (?,?,?,?,?,?,?,?)";
             Connection connPortal=OracleDbHelper.getUniformConnection();
             Statement statement=connPortal.createStatement();
             statement.executeUpdate(insertSql);
             connPortal.commit();

            statement.close();
            connPortal.close();

         }catch (Exception e){
             e.printStackTrace();
         }finally{
             try{
                 rs.close();
                 ps.close();
                 conn.close();
             }catch (Exception e){
                 e.printStackTrace();
             }
         }
        return  list;
    }

    //返回总行数
    public  static int getRows(String sql){
        int rows=0;
        Connection conn=getConnection();
        PreparedStatement ps= null;
        ResultSet rs= null;

        try {
            ps= conn.prepareStatement(sql);
            rs= ps.executeQuery(sql);

            while (rs.next()){
                rows= rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }
}
