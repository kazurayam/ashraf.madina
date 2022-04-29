import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.By

File html = new File("./Households - Index.mhtml")

WebUI.openBrowser(html.toURI().toURL().toExternalForm());
WebUI.verifyElementPresent(makeTO("//div[contains(@role,'table')]"),5)
WebUI.verifyElementPresent(makeTO("//div[contains(@role,'table')]//div[@role='row']"),5)

List<WebElement> rows = WebUI.findWebElements(makeTO("//div[contains(@role,'table')]//div[@role='row']"),5);
assert rows.size() > 1
int numberOfRows = rows.size()
println "numberOfRows=${numberOfRows}"

//
for (WebElement row in rows) {
	WebElement owner = row.findElement(By.xpath("div[@role='gridcell'][4]"))
	assert owner != null
	println "owner=${owner.getText()}"
	WebElement status = row.findElement(By.xpath("div[@role='gridcell'][5]"))
	
	if (owner.getText().trim() == "Ashraf Tester" && 
		status.getText().trim() == "Client") {
		checkRow(row)
	}
}

WebUI.closeBrowser()

/**
 *  
 */
def checkRow(WebElement row) {
	WebElement name = row.findElement(By.xpath("div[@role='gridcell'][3]"))
	WebUI.comment("Name=${name.getText().trim()}")
	// and more ...
}

/**
 * @param xpath
 * @return a TestObject
 */
TestObject makeTO(String xpath) {
	TestObject to = new TestObject(xpath)
	to.addProperty("xpath", ConditionType.EQUALS, xpath)
	return to
}