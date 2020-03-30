package Bewakoof;

public class Locators {

	public static String urlHomePage = "https://www.bewakoof.com/";
	public static String search = "//input[contains(@placeholder,'Search by product, category or collection')]";
	public static String goToLoginButton = "loginLink";
	public static String email = "mob_email_id";
	public static String continueButton = "//button[contains(text(),'Continue')]";
	public static String password = "//input[contains(@id,'mob_password')]";
	public static String login = "//button[contains(text(),\"LOG IN\")]";
	public static String signup = "//span[contains(text(),'SIGN UP')]";

	public static String product="//div[contains(@id,'testProductcard_1')]//a";
	public static String sizeMedium="//div[contains(@id,'testSizes_M')]";
	public static String addToBag="//span[contains(text(),'ADD TO BAG')]";
	public static String goToBag="//span[contains(text(),'GO TO BAG')]";
	public static String selectDeliveryAddress="//button[contains(text(),'Select Delivery Address')]";
	public static String deliverHereButton="//button[contains(text(),'DELIVER HERE')]";

	public static String wallet="//div[contains(text(),'Wallet')]";
	public static String paytm="#PAYTM";
	public static String paytmLabel="//div[contains(@class,'paybox')]//h6";
	public static String payNow="//button[contains(text(),'PAY NOW')]";
	public static String paymentGatewayPageelement="//span[contains(text(),'Transaction ID:')]";
	public static String preLogoutButton = "//a[contains(@id,'testHeaderAcc')]";
	public static String logout = "//li[contains(text(),'Logout')]";
	

}
