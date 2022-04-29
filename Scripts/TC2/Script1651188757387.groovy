import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

File html = new File("./Households - Index.mhtml")

WebUI.openBrowser(html.toURI().toURL().toExternalForm());
WebUI.verifyElementPresent(makeTO("//div[contains(@role,'table')]"),5)
WebUI.verifyElementPresent(makeTO("//div[contains(@role,'table')]//div[@role='row']"),5)

List<WebElement> rows = WebUI.findWebElements(makeTO("//div[contains(@role,'table')]//div[@role='row']"),5);
assert rows.size() > 1
int numberOfRows = rows.size()
println "numberOfRows=${numberOfRows}"

for (WebElement row in rows) {
	WebElement owner = row.findElement(By.xpath("div[@role='gridcell'][4]"))
	assert owner != null
	//println "owner=${owner.getText()}"
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
	println "Name=${name.getText().trim()}"
	// add more code ...
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