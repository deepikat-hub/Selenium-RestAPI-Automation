//package com.result.logger;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.net.UnknownHostException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Timestamp;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.List;
//import java.util.Map;
//import base.ui.ProductVM;
//import properties.ConfProperties;
//import ui.nms.Globals;
//import ui.nms.Time;
//import java.time.format.DateTimeFormatter;
//import java.time.LocalDateTime;
//import org.apache.commons.lang3.text.WordUtils;
//import org.testng.IReporter;
//import org.testng.IResultMap;
//import org.testng.ISuite;
//import org.testng.ISuiteResult;
//import org.testng.ITestContext;
//import org.testng.ITestNGMethod;
//import org.testng.ITestResult;
//import org.testng.xml.XmlSuite;
//
//public class TestResultsLogger extends GraphiteLogger implements IReporter {
//
//	private static int build_no = Integer.parseInt(System.getProperty("buildno"));
//	// private static int build_no = 200;
//	private static String dbName = "AutomationDB";
//	private static String dbUser = "root";
//	private static String dbPass = "root123";
//	private static String dbUrl = "jdbc:mysql://10.129.14.161:3306";
//	private static Connection conn;
//	private static String sqlFormat = "yyyy-MM-dd HH:mm:ss";
//
//	@Override
//	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//
//		Long timestamp = System.currentTimeMillis() / 1000;
//
//		logPassedCount(suites, timestamp);
//		logFailedCount(suites, timestamp);
//		logSkippedCount(suites, timestamp);
//
//		conn = DBComms.openConnection(dbUrl, dbUser, dbPass);
//
//		System.out.println("**************Results**************");
//		try {
//			publishTestResults(suites, conn);
//		} catch (SQLException | UnknownHostException e) {
//			e.printStackTrace();
//		}
//		System.out.println("***********************************");
//
//	}
//	public static String getCurrentLocalDateTimeStamp() {
//		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
//	}
//
//	/**
//	 * Prints and inserts TestNg result into the database
//	 * 
//	 * @param suites
//	 *            List of suites executed during execution
//	 * @param conn
//	 *            Connection object
//	 * @throws SQLException
//	 * @throws UnknownHostException
//	 */
//	private static void publishTestResults(List<ISuite> suites, Connection conn)
//			throws UnknownHostException, SQLException {
//		// log details about the VM (NMS)
//		try {
//
//			conn.setAutoCommit(false);
//			// String nms_type = Globals.vmType;
//			String nms_type = "NMS";
//			String nms_name = ProductVM.vmHostname();
//			String nms_ip = ProductVM.vmIP();
//			String nms_version = "NMS_" + ProductVM.nmsVersion().trim();
//			String nms_githash = ProductVM.gitHash();
//			String nms_code_date = Globals.codeDate;
//			if (nms_code_date == "" || nms_code_date == null ) {
//				nms_code_date = TestResultsLogger.getCurrentLocalDateTimeStamp(); //Bad hack for now since it is a required filed for the automation database.
//			}
//			String nms_build_number = ProductVM.nmsBuildNumber().trim();
//			//String nms_code_date = ProductVM.codeDate().trim();
//			String nms_user = ConfProperties.soapadminusername;
//
//			System.out.println("---------NMS DETAILS-------");
//			System.out.println("NMS Box Name= " + nms_name);
//			System.out.println("NMS IP= " + nms_ip);
//			System.out.println("NMS Type= " + nms_type);
//			System.out.println("NMS Version= " + nms_version);
//			System.out.println("NMS GIT HASH= " + nms_githash);
//			System.out.println("NMS BUILD NUMBER= " + nms_build_number);
//			System.out.println("NMS CODE DATE= " + nms_code_date + "\n");
//
//			int nms_id = logNMSDetails(conn, nms_name, nms_ip, "master_IP", nms_version, nms_type, nms_githash,
//					nms_build_number, nms_code_date, nms_user, "This is a nms_box");
//
//			// log details about the slave vm
//			String slave_name = ConfProperties.getHostName();
//			String slave_ip = ConfProperties.getHostIP();
//			String slave_os = Globals.platform;
//			String user = System.getProperty("user.name");
//
//			System.out.println("---------SLAVE DETAILS-------");
//			System.out.println("Slave= " + slave_name);
//			System.out.println("Slave IP= " + slave_ip);
//			System.out.println("Slave OS= " + slave_os);
//			System.out.println("User= " + user + "\n");
//			int slave_id = logSlaveDetails(conn, slave_name, slave_ip, slave_os, user, "This is a Slave.");
//
//			// browser details
//			System.out.println("---------BROWSER DETAILS-------");
//			String browser_name = Globals.browserName;
//			String browser_version = Globals.browserVersion;
//			System.out.println("Browser= " + browser_name);
//			System.out.println("Browser Version= " + browser_version + "\n");
//
//			int suiteCounter = 0;
//
//			int overalltests = 0;
//			int overallpassed = 0;
//			int overallfailed = 0;
//			int overallskipped = 0;
//			int overallconfigCount = 0;
//
//			for (ISuite suite : suites) {
//				String suiteName = WordUtils.capitalizeFully(suite.getName());
//				// log suite name
//				int suiteID = logSuiteName(conn, suiteName); // db
//
//				int testCount = 0;
//				int passCount = 0;
//				int failCount = 0;
//				int skipCount = 0;
//				int configCount = 0;
//
//				Map<String, ISuiteResult> suiteResults = suite.getResults();
//				for (Object o : suiteResults.values()) {
//					ISuiteResult sr = (ISuiteResult) o;
//					ITestContext tc = sr.getTestContext();
//
//					// results for a class
//					testCount = tc.getAllTestMethods().length;
//					passCount = tc.getPassedTests().size();
//					failCount = tc.getFailedTests().size();
//					skipCount = tc.getSkippedTests().size();
//					configCount = tc.getFailedConfigurations().size();
//
//					// results for entire execution
//					overalltests += testCount;
//					overallpassed += passCount;
//					overallfailed += failCount;
//					overallskipped += skipCount;
//					overallconfigCount += configCount;
//
//					// log method specific results to db
//
//					System.out.println("<-------------------------PASSED TEST--------------------------->");
//					IResultMap success = tc.getPassedTests();
//
//					for (ITestResult result : success.getAllResults()) {
//						ITestNGMethod method = result.getMethod();
//						String classname = result.getTestClass().getRealClass().getSimpleName();
//						System.out.println("Classname for the executed tests:" + classname);
//						// insert class name to the class table
//
//						String className = method.getRealClass().getSimpleName();
//						int classID = logTestClassName(conn, suiteID, className);
//
//						// insert method name to the method table
//						int methodID = logTestMethodName(conn, suiteID, classID, method.getMethodName());
//						String startTime = Time.getDateFromMillis(String.valueOf(result.getStartMillis()), sqlFormat);
//						String endTime = Time.getDateFromMillis(String.valueOf(result.getEndMillis()), sqlFormat);
//						double duration = (result.getEndMillis() - result.getStartMillis()) / 1000.0;
//						String test_result = "SUCCESS";
//						System.out.println("==>");
//						System.out.println("Test name: " + tc.getName());
//						System.out.println("Method name: " + method.getMethodName());
//						System.out.println("Start time: " + startTime);
//						System.out.println("End time: " + endTime);
//						System.out.println("Duration: " + duration);
//						System.out.println("");
//						logTestExecutionResults(build_no, startTime, endTime, duration, test_result, "", "",
//								browser_name, browser_version, methodID, classID, suiteID, nms_id, slave_id);
//					}
//
//					System.out.println("<-------------------------FAILED TEST--------------------------->");
//					IResultMap failures = tc.getFailedTests();
//					for (ITestResult result : failures.getAllResults()) {
//						ITestNGMethod method = result.getMethod();
//						// insert class name to the class table
//						String className = method.getRealClass().getSimpleName();
//						int classID = logTestClassName(conn, suiteID, className);
//
//						// insert method name to the method table
//
//						int methodID = logTestMethodName(conn, suiteID, classID, method.getMethodName());
//						String startTime = Time.getDateFromMillis(String.valueOf(result.getStartMillis()), sqlFormat);
//						String endTime = Time.getDateFromMillis(String.valueOf(result.getEndMillis()), sqlFormat);
//						double duration = (result.getEndMillis() - result.getStartMillis()) / 1000.0;
//						String test_result = "FAILURE";
//						String error = result.getThrowable().toString();
//						String stackTrace = returnStackTrace(result.getThrowable());
//
//						System.out.println("==>");
//						System.out.println("Test name: " + className);
//						System.out.println("Method name: " + method.getMethodName());
//						System.out.println("Start time: " + startTime);
//						System.out.println("End time: " + endTime);
//						System.out.println("Duration: " + duration);
//						System.out.println("Error: " + error);
//						System.out.println("Stack trace: " + stackTrace);
//						System.out.println("");
//						// log results for the method
//						logTestExecutionResults(build_no, startTime, endTime, duration, test_result, formatError(error),
//								formatError(stackTrace), browser_name, browser_version, methodID, classID, suiteID,
//								nms_id, slave_id);
//
//					}
//
//					System.out.println("<-------------------------SKIPPED TEST--------------------------->");
//					IResultMap skipped = tc.getSkippedTests();
//
//					for (ITestResult result : skipped.getAllResults()) {
//
//						ITestNGMethod method = result.getMethod();
//						// insert class name to the class table
//						String className = method.getRealClass().getSimpleName();
//						int classID = logTestClassName(conn, suiteID, className);
//
//						// insert method name to the method table
//						int methodID = logTestMethodName(conn, suiteID, classID, method.getMethodName());
//						String startTime = Time.getDateFromMillis(String.valueOf(result.getStartMillis()), sqlFormat);
//						String endTime = Time.getDateFromMillis(String.valueOf(result.getEndMillis()), sqlFormat);
//						double duration = (result.getEndMillis() - result.getStartMillis()) / 1000.0;
//						String test_result = "SKIPPED";
//
//						System.out.println("==>");
//						System.out.println("Test name: " + className);
//						System.out.println("Method name: " + method.getMethodName());
//						System.out.println("");
//
//						logTestExecutionResults(build_no, startTime, endTime, duration, test_result, "", "",
//								browser_name, browser_version, methodID, classID, suiteID, nms_id, slave_id);
//
//					}
//
//					System.out.println("<-------------------------FAILED CONFIG--------------------------->");
//					IResultMap failedConf = tc.getFailedConfigurations();
//					for (ITestResult result : failedConf.getAllResults()) {
//						ITestNGMethod method = result.getMethod();
//						if (!method.getMethodName().equals("tearDown")
//								&& !method.getMethodName().equals("tearDownAfterClass")) {
//							// insert class name into the class table
//
//							String className = method.getRealClass().getSimpleName();
//							int classID = logTestClassName(conn, suiteID, className);
//
//							// insert method name to the method table
//							int methodID = logConfigMethodName(conn, suiteID, classID, method.getMethodName());
//
//							String startTime = Time.getDateFromMillis(String.valueOf(result.getStartMillis()),
//									sqlFormat);
//							String endTime = Time.getDateFromMillis(String.valueOf(result.getEndMillis()), sqlFormat);
//							double duration = (result.getEndMillis() - result.getStartMillis()) / 1000.0;
//							String test_result = "CONFIGURATION FAILURE";
//							String error = result.getThrowable().toString();
//							String stackTrace = returnStackTrace(result.getThrowable());
//
//							System.out.println("==>");
//							System.out.println("Test name: " + className);
//							System.out.println("Method name: " + method.getMethodName());
//							System.out.println("Start time: " + startTime);
//							System.out.println("End time: " + endTime);
//							System.out.println("Duration: " + duration);
//							System.out.println("Error: " + error);
//							System.out.println("Stack trace: " + stackTrace);
//							System.out.println("");
//
//							// log results for the method
//							logTestExecutionResults(build_no, startTime, endTime, duration, test_result,
//									formatError(error), formatError(stackTrace), browser_name, browser_version,
//									methodID, classID, suiteID, nms_id, slave_id);
//						}
//					}
//
//					// Result of the suite
//					System.out.println("Suite: " + suiteName + " | " + tc.getName() + "\n Result is Passed:" + passCount
//							+ "/" + testCount + "\n Result is Failed:" + failCount + "/" + testCount
//							+ "\n Result is Skipped:" + skipCount + "/" + testCount + "\n Config errors:"
//							+ configCount);
//
//				}
//
//				suiteCounter++;
//
//			}
//
//			System.out.println("-------------------- EXECUTION SUMMARY ------------------------");
//			System.out.println("Total Suites run: " + suiteCounter);
//			System.out.println("Total Tests passed: " + overallpassed + "/" + overalltests);
//			System.out.println("Total Tests failed: " + overallfailed + "/" + overalltests);
//			System.out.println("Total Tests skipped: " + overallskipped + "/" + overalltests);
//			System.out.println("Total Configuration failures: " + overallconfigCount);
//			System.out.println("");
//			System.out.println("Overall Pass percent: " + calculatePercentage(overallpassed, overalltests));
//			System.out.println("Overall Fail percent: " + calculatePercentage(overallfailed, overalltests));
//			System.out.println("Overall Skip percent: " + calculatePercentage(overallskipped, overalltests));
//			System.out.println("----------------------------------------------------------------");
//
//			conn.commit();
//		} catch (Exception e) {
//			System.out.println("Rolling back changes");
//			conn.rollback();
//			e.printStackTrace();
//
//		} finally {
//			DBComms.closeConnection(conn);
//		}
//
//	}
//
//	/**
//	 * Returns the stacktrace in String format
//	 * 
//	 * @param t
//	 *            Throwable object
//	 * @return
//	 */
//	private static String returnStackTrace(Throwable t) {
//		StringWriter trace = new StringWriter();
//		t.printStackTrace(new PrintWriter(trace));
//		return trace.toString();
//	}
//
//	/**
//	 * Logs suite details into the database
//	 * 
//	 * @param conn
//	 *            Connection object
//	 * @param suiteName
//	 *            Suite name
//	 * @return Integer index of the record inserted
//	 * @throws SQLException
//	 */
//
//	private static Integer logSuiteName(Connection conn, String suiteName) throws SQLException {
//		String search = "SELECT * FROM TEST_SUITE WHERE testsuite_name='" + suiteName + "';";
//		Statement stmt;
//		ResultSet rs = null;
//		try {
//			stmt = conn.createStatement();
//			stmt.executeUpdate("use " + dbName);
//			rs = stmt.executeQuery(search);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		if (!rs.next()) {
//			String insert = "INSERT INTO TEST_SUITE VALUES( 0,'" + suiteName + "');";
//			return DBComms.executeSql(conn, dbName, insert, true);
//		}
//
//		return rs.getInt(1);
//
//	}
//
//	/**
//	 * Logs the Class name into the Database
//	 * 
//	 * @param conn
//	 *            Connection object
//	 * @param suiteId
//	 *            Id of the suite from the suite table
//	 * @param className
//	 *            Name of the class
//	 * @return Integer index of the record inserted into the database
//	 * @throws SQLException
//	 */
//	private static Integer logTestClassName(Connection conn, Integer suiteId, String className) throws SQLException {
//		String search = "SELECT * FROM CLASS WHERE class_name='" + className + "' AND testsuite_id=" + suiteId + ";";
//
//		Statement stmt;
//		ResultSet rs = null;
//
//		try {
//			stmt = conn.createStatement();
//			stmt.executeUpdate("use " + dbName);
//			rs = stmt.executeQuery(search);
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//		}
//
//		if (!rs.next()) {
//			String insert = "INSERT INTO CLASS VALUES(0,'" + className + "'," + suiteId + ");";
//			return DBComms.executeSql(conn, dbName, insert, true);
//		}
//		return rs.getInt(1);
//	}
//
//	/**
//	 * Logs the method name into the Database
//	 * 
//	 * @param conn
//	 *            Connection object
//	 * @param suiteId
//	 *            Id of the suite
//	 * @param classId
//	 *            Id of the class
//	 * @param methodName
//	 *            name of the method
//	 * @return Integer index inserted record
//	 * @throws SQLException
//	 */
//	private static Integer logTestMethodName(Connection conn, Integer suiteId, Integer classId, String methodName)
//			throws SQLException {
//		String search = "SELECT * FROM METHOD WHERE method_name='" + methodName + "' AND class_id=" + classId
//				+ " AND testsuite_id=" + suiteId + ";";
//
//		Statement stmt;
//		ResultSet rs = null;
//
//		try {
//			stmt = conn.createStatement();
//			stmt.executeUpdate("use " + dbName);
//			rs = stmt.executeQuery(search);
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//		}
//
//		if (!rs.next()) {
//			String insert = "INSERT INTO METHOD VALUES(0,'" + methodName + "'," + classId + "," + suiteId + ");";
//
//			return DBComms.executeSql(conn, dbName, insert, true);
//		}
//		return rs.getInt(1);
//
//	}
//
//	/**
//	 * Logs the details of the config methods
//	 * 
//	 * @param conn
//	 * @param suiteId
//	 * @param classId
//	 * @param methodName
//	 * @return
//	 * @throws SQLException
//	 */
//	private static Integer logConfigMethodName(Connection conn, Integer suiteId, Integer classId, String methodName)
//			throws SQLException {
//		String search = "SELECT * FROM METHOD WHERE method_name='" + methodName + "' AND class_id=" + classId
//				+ " AND testsuite_id=" + suiteId + ";";
//
//		Statement stmt;
//		ResultSet rs = null;
//
//		try {
//			stmt = conn.createStatement();
//			stmt.executeUpdate("use " + dbName);
//			rs = stmt.executeQuery(search);
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//		}
//
//		if (!rs.next()) {
//			String insert = "INSERT INTO METHOD VALUES(0,'" + methodName + "'," + classId + "," + suiteId + ");";
//			return DBComms.executeSql(conn, dbName, insert, true);
//		}
//		return rs.getInt(1);
//
//	}
//
//	private static Integer logNMSDetails(Connection conn, String nms_name, String nms_ip, String master_ip,
//			String nms_version, String nms_type, String nms_githash, String nms_build_number, String nms_code_date,
//			String nms_user, String nms_info) throws SQLException {
//
//		String search = "SELECT * FROM PRODUCT_INVENTORY WHERE machine_ip_address='" + nms_ip + "' AND machine_name='"
//				+ nms_name + "' AND product_version='" + nms_version + "' AND build_number='" + nms_build_number
//				+ "' AND vm_git_hash='" + nms_githash + "';";
//
//		Statement stmt;
//		ResultSet rs = null;
//		try {
//			stmt = conn.createStatement();
//			stmt.executeUpdate("use " + dbName);
//			rs = stmt.executeQuery(search);
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//
//		}
//		if (!rs.next()) {
//			java.sql.Timestamp code_date = getSQLDateTime(nms_code_date, sqlFormat);
//			String insert = "INSERT INTO PRODUCT_INVENTORY VALUES (" + "0" + "," + "'" + nms_name + "'," + "'" + nms_ip
//					+ "'," + "'" + master_ip + "'," + "'" + nms_version + "'," + "'" + nms_type + "'," + "'"
//					+ nms_githash + "'," + "'" + nms_build_number + "'," + "'" + code_date + "'," + "'" + nms_user
//					+ "'," + "'" + nms_info + "');";
//
//			return DBComms.executeSql(conn, dbName, insert, true);
//
//		}
//
//		return rs.getInt(1);
//	}
//
//	/**
//	 * Logs the slave related details into the database
//	 * 
//	 * @param conn
//	 * @param slave_name
//	 * @param slave_ip
//	 * @param slave_os
//	 * @param slave_user
//	 * @param slave_info
//	 * @return
//	 * @throws SQLException
//	 */
//	private static Integer logSlaveDetails(Connection conn, String slave_name, String slave_ip, String slave_os,
//			String slave_user, String slave_info) throws SQLException {
//
//		String search = "SELECT * FROM SLAVE_INVENTORY WHERE slave_box_name='" + slave_name + "' AND slave_box_ip='"
//				+ slave_ip + "' AND slave_os='" + slave_os + "' AND slave_box_user='" + slave_user + "';";
//
//		Statement stmt;
//		ResultSet rs = null;
//		try {
//			stmt = conn.createStatement();
//			stmt.executeUpdate("use " + dbName);
//			rs = stmt.executeQuery(search);
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//
//		}
//		if (!rs.next()) {
//
//			String insert = "INSERT INTO SLAVE_INVENTORY VALUES (" + "0" + "," + "'" + slave_name + "'," + "'"
//					+ slave_ip + "'," + "'" + slave_os + "'," + "'" + slave_user + "'," + "'" + slave_info + "');";
//
//			return DBComms.executeSql(conn, dbName, insert, true);
//
//		}
//
//		return rs.getInt(1);
//	}
//
//	/**
//	 * Logs test execution result for each method into the database
//	 * 
//	 * @param job_id
//	 * @param start_time
//	 * @param end_time
//	 * @param test_duration
//	 * @param test_result
//	 * @param error
//	 * @param stacktrace
//	 * @param browser_name
//	 * @param browser_version
//	 * @param method_id
//	 * @param class_id
//	 * @param testsuite_id
//	 * @param nms_id
//	 * @param slave_id
//	 * @return
//	 */
//	private static Integer logTestExecutionResults(int job_id, String start_time, String end_time, double test_duration,
//			String test_result, String error, String stacktrace, String browser_name, String browser_version,
//			int method_id, int class_id, int testsuite_id, int nms_id, int slave_id) {
//
//		java.sql.Timestamp stime = getSQLDateTime(start_time, sqlFormat);
//		java.sql.Timestamp etime = getSQLDateTime(end_time, sqlFormat);
//
//		String insert = "INSERT INTO TEST_EXECUTION_HISTORY VALUES(" + "0" + "," + job_id + "," + "'" + stime + "',"
//				+ "'" + etime + "'," + "" + test_duration + "," + "'" + test_result + "'," + "'" + error + "'," + "'"
//				+ stacktrace + "',"
//
//				+ "'" + browser_name + "'," + "'" + browser_version + "',"
//
//				+ "" + method_id + "," + "" + class_id + "," + "" + testsuite_id + "," + "" + nms_id + "," + ""
//				+ slave_id + ");";
//
//		return DBComms.executeSql(conn, dbName, insert, true);
//	}
//
//	/**
//	 * Returns date and time in SQL format for db entry
//	 * 
//	 * @param dateTime
//	 * @param currentFormat
//	 * @return sql.Timestamp
//	 */
//	private static java.sql.Timestamp getSQLDateTime(String dateTime, String currentFormat) {
//		SimpleDateFormat sdf = new SimpleDateFormat(currentFormat);
//		java.util.Date date = null;
//		try {
//			date = sdf.parse(dateTime);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		java.sql.Timestamp sqlTs = new Timestamp(date.getTime());
//		return sqlTs;
//	}
//
//	/**
//	 * Escapes ' and , for database entry
//	 * 
//	 * @param errormsg
//	 * @return String
//	 */
//	private static String formatError(String errormsg) {
//
//		return errormsg.replace("'", "\\'").replace(",", "\\,").replace(";", "\\;");
//
//	}
//
//	/**
//	 * Returns a percentage value
//	 * 
//	 * @param numerator
//	 * @param denominator
//	 * @return String
//	 */
//	private static String calculatePercentage(int numerator, int denominator) {
//
//		String output;
//
//		double num = numerator;
//		double den = denominator;
//
//		output = ((num / den) * 100) + " %";
//
//		return output;
//	}
//}