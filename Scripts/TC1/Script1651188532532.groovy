import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

File html = new File("./Households - Index.mhtml")

WebUI.openBrowser(html.toURI().toURL().toExternalForm());
WebUI.delay(3)
WebUI.closeBrowser()