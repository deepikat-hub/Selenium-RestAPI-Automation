package Bewakoof.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Logs {

	public static Logger logger = LogManager.getLogger(Logs.class);
	//	private static Logger logger = null;

	//	public Logs(String name) {
	//		super(name);
	//		logger = getLogger();
	//	}
	public static void main(String args[])
	{
		logger.error("error ===");
	}	
}
