package Bewakoof;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Bewakoof.Common.GlobalCommon;
import Bewakoof.Pages.Cart;
import Bewakoof.Pages.Checkout;
import Bewakoof.Pages.Home;
import Bewakoof.Pages.LoginPage;
import Bewakoof.Pages.Payment;
import Bewakoof.Pages.ProcessTransaction;
import Bewakoof.Pages.ProductDetail;

public class Login extends GlobalCommon {

	private String searchText = "white tee";

	@BeforeClass
	public void start() throws Exception {

		Home.goToURL(Locators.urlHomePage);
	}

	@AfterClass
	public void end() throws Exception {
		Home.goToURL(Locators.urlHomePage);
		// Home.logout();
	}

	@Test
	public void test() throws Exception {

		LoginPage.validateTitleLogin();
		LoginPage.Login();
		Home.search(searchText);
		Home.selectProduct();
		ProductDetail.selectSize();
		ProductDetail.addToBag();
		ProductDetail.goToBag();
		Cart.selectDeliveryAddress();
		Checkout.deliverHere();
		Payment.selectPaymentMode(Locators.wallet);
		Payment.selectPaymentOption(By.cssSelector(Locators.paytm));
		Payment.payNow();
		ProcessTransaction.checkThirdPartyPage();

	}

}
