package jyl.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jLoader {
	static Logger logger = Logger.getLogger(Log4jLoader.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log4jLoader.loaddefault();
		logger.info("ok");
	}

	public static void loaddefault() {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("conf/log4j.properties"); 
	}

}
