package com.nian.homework.week.five.jdbc;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedHashSet;
import java.util.List;

@Component
public class JDBCUtil {

    @Resource(name = "dataSource")
    private  DataSource dataSource;

    public  Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        throw new IllegalArgumentException();
    }


    /**
     * 增删改【Add、Del、Update】
     *
     * @param sql
     * @return int
     */
    public  int executeNonQuery(Connection conn, String sql) {
        int result = 0;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException err) {
            err.printStackTrace();
            free(null, stmt, conn);
        } finally {
            free(null, stmt, conn);
        }
        return result;
    }


    /**
     * 增删改【Add、Del、Update】
     *
     * @param sql
     * @param params
     * @return int[]
     */
    public int[] executeBatch(Connection conn, String sql, List<LinkedHashSet<String>> params) throws SQLException {
        int[] result;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            for(LinkedHashSet<String> eachSet : params) {
                int index = 0;
                for (String eachParam : eachSet) {
                    pstmt.setString(index + 1, eachParam);
                    index ++;
                }
                pstmt.addBatch();
            }
            result = pstmt.executeBatch();
            pstmt.clearBatch();
        } catch (SQLException err) {
            err.printStackTrace();
            conn.rollback();
            free(null, pstmt, conn);
            throw err;
        } finally {
            free(null, pstmt, conn);
        }
        return result;
    }


    /**
     * 增删改【Add、Delete、Update】
     *
     * @param sql
     * @param obj
     * @return int
     */

    public  int executeNonQuery(Connection conn, String sql, Object... obj) {
        int result = 0;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            result = pstmt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
            free(null, pstmt, conn);
        } finally {
            free(null, pstmt, conn);
        }
        return result;
    }


    /**
     * 查【Query】
     *
     * @param sql
     * @return ResultSet
     */

    public  ResultSet executeQuery(Connection conn, String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException err) {
            err.printStackTrace();
            free(rs, stmt, conn);
        }
        return rs;
    }

    /**
     * 查【Query】
     *
     * @param sql
     * @param obj
     * @return ResultSet
     */

    public  ResultSet executeQuery(Connection conn, String sql, Object... obj) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            rs = pstmt.executeQuery();
        } catch (SQLException err) {
            err.printStackTrace();
            free(rs, pstmt, conn);
        }
        return rs;
    }

    public  void free(ResultSet rs, Statement st, Connection conn) {
        free(rs);
        free(st);
        free(conn);
    }


    /**
     * 释放【ResultSet】资源
     *
     * @param rs
     */

    public  void free(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }


    /**
     * 释放【Statement】资源
     *
     * @param st
     */

    public  void free(Statement st) {
        try {
            if (st != null) {
                st.close();
            }

        } catch (SQLException err) {
            err.printStackTrace();
        }
    }


    /**
     * 释放【Connection】资源
     *
     * @param conn
     */

    public  void free(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
}
