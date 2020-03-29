package Bewakoof.Pages;

import Bewakoof.Locators;
import Bewakoof.Common.Functions;

public class ProcessTransaction {
	public static void checkThirdPartyPage(){
		Functions.wait(40, Locators.paymentGatewayPageelement);
		Functions.checkUrl("secure");
	}
}
