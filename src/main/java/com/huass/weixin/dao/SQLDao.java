package com.huass.weixin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.jdbc.ReturningWork;
import org.springframework.stereotype.Repository;

import com.huass.common.persistence.BaseDao;

@SuppressWarnings("rawtypes")
@Repository
public class SQLDao extends BaseDao 
{
	
	public Object[] select(final String sql)
	{
		return getSession().doReturningWork(new ReturningWork<Object[]>() {
			@Override
			public Object[] execute(Connection conn) throws SQLException
			{
				Object[] objArray = new Object[2];
				PreparedStatement ps = null;
				ResultSet rs = null;
				try
				{
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					ResultSetMetaData metas = rs.getMetaData();
					int columnSize = metas.getColumnCount();
					List<String> columns = new ArrayList<String>();
					for(int i=1; i<=columnSize; i++)
					{
						columns.add(metas.getColumnLabel(i));
					}
					List<List<String>> datas = new ArrayList<List<String>>();
					while(rs.next())
					{
						List<String> data = new ArrayList<String>();
						for(String column :columns)
						{
							Object obj = rs.getObject(column);
							String str = obj == null ? "" : obj.toString();
							data.add(str);
						}
						datas.add(data);
					}
					objArray[0] = columns;
					objArray[1] = datas;
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				finally
				{
					if(rs != null && !rs.isClosed())
					{
						rs.close();
					}
					if(ps != null && !ps.isClosed())
					{
						ps.close();
					}
				}
				return objArray;
			}
		});
	}
	
	
	public Object[] exesql(final String sql)
	{
		return getSession().doReturningWork(new ReturningWork<Object[]>() {

			@Override
			public Object[] execute(Connection conn) throws SQLException
			{
				Object[] objArray = new Object[2];
				objArray[0] = "影响行数";
				PreparedStatement ps = null;
				try
				{
					ps = conn.prepareStatement(sql);
					objArray[1] = ps.executeUpdate();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return objArray;
			}
		});
	}
	
	public static void main(String[] args)
	{
		String sql="update ss";
		System.out.println(sql.split(";").length);
	}
}
