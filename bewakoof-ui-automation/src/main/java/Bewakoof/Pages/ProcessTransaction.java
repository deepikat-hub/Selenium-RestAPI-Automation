package Bewakoof.Pages;

import Bewakoof.Locators;
import Bewakoof.Common.Functions;
import io.qameta.allure.Step;

public class ProcessTransaction {
	@Step
	public static void checkThirdPartyPage(){
		Functions.wait(40, Locators.paymentGatewayPageelement);
		Functions.checkUrl("secure");
	}
}
