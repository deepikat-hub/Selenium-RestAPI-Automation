import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
public static Logger logger = LogManager.getLogger(Logs.class);
	
	public static void main(String args[])
	{
		logger.error("error ===");
	}	
}
