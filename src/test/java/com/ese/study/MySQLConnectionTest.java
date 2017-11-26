package com.ese.study;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;


/**
 * 
 * JUnit �� �̿��� JDBC ���� �׽�Ʈ �ڵ�
 * @author Administrator
 *
 */


public class MySQLConnectionTest {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/book_ex?useSSL=false&serverTimezone=UTC";
	private static final String USER = "zerock";
	private static final String PW = "zerock";

	@Test
	public void testConnection() throws Exception {
		
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)) {
			
			System.out.println("JDBC ���� ����");
			System.out.println(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}