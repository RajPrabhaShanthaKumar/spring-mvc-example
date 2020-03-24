package com.wipro.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import javax.naming.Context;

public class securityApplication {

	

	private Hashtable getDBConnDetails() {
		InitialContext ctx = null;
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.naming.java.javaURLContextFactory");
		env.put(Context.PROVIDER_URL, "http://localhost:7002");
		env.put(Context.SECURITY_PRINCIPAL, "wipro");
		env.put(Context.SECURITY_CREDENTIALS, "admin");
		ctx = new InitialContext(env);

		return env;
	}

	private void sendTheTxnData() {
		Hashtable credentials = getDBConnDetails();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		InitialContext ctx = new InitialContext(credentials);

		try {
			javax.sql.DataSource ds = (javax.sql.DataSource) ctx
					.lookup("myDataSource");

			conn = ds.getConnection();

			// You can now use the conn object to create
			// Statements and retrieve result sets:
			stmt = conn.createStatement();
			stmt.execute("select * from someTable");
			rs = stmt.getResultSet();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//logger.error(e);
		}

	}
}
