package com.ymk.tso.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ymk.tso.util.ConnectionUtil;

public class StartupServlet implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Properties props = new Properties();
		try (InputStream stream = getClass().getClassLoader().getResourceAsStream("database.properties")) {
			props.load(stream);
			Map<String, String> connMap = new HashMap<String, String>();
			
			for(Object obj : props.keySet()){
				String key = (String) obj;
				connMap.put(key, props.getProperty(key));
			}
			
			ConnectionUtil.setConnectionProperties(connMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
