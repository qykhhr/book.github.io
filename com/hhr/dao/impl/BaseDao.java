package com.hhr.dao.impl;

import com.hhr.utils.JDBCUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author qyk
 * @date 2020:09:19
 */
public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();


    /**
     * update方法用于（insert/delete/update）
     * @param sql
     * @param args
     * @return 如果返回-1说明执行失败，返回其他则是影响数据库的行数
     */
    public int update(String sql,Object ... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.close(conn);
        }
        return -1;
    }


    /**
     * 查询返回一条记录
     * @param Type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T  queryForOne(Class<T> Type,String sql,Object ...args){
        Connection conn = JDBCUtils.getConnection();

        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(Type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.close(conn);
        }
        return null;
    }


    /**
     * 查询返回多条记录（集合）
     * @param Type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> queryForList(Class<T>Type, String sql, Object ...args){
        Connection conn = JDBCUtils.getConnection();

        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(Type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.close(conn);
        }
        return null;
    }

    /**
     * 查询返回一行一列的结果
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql,Object ...args){
        Connection conn = JDBCUtils.getConnection();

        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.close(conn);
        }
        return null;
    }
}
