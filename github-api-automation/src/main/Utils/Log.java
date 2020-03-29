import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logs {
public static Logger logger = LogManager.getLogger(Logs.class);
	
	public static void main(String args[])
	{
		logger.error("error ===");
	}	
}
