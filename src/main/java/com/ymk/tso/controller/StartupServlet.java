package com.ymk.tso.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ymk.tso.util.ConnectionUtil;

/**
 * StartupServlet calss
 * @author yemyokyaw
 *
 */
public class StartupServlet implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	/**
	 * contextInitialized method get database properties
	 * @param arg0
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Properties props = new Properties();
		
		// take database properties from the file
		try (InputStream stream = getClass().getClassLoader().getResourceAsStream("database.properties")) {
			props.load(stream);
			Map<String, String> connMap = new HashMap<String, String>();
			
			for(Object obj : props.keySet()){
				String key = (String) obj;
				connMap.put(key, props.getProperty(key));
			}
			
			// set the database propertiesd to connection.
			ConnectionUtil.setConnectionProperties(connMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
