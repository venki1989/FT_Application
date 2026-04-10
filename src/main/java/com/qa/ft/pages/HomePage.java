package com.qa.ft.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.ft.utils.ElementUtil;

public class HomePage {

    private WebDriver driver;
    private ElementUtil eleutil;

    private By globalicon      = By.xpath("//a[@data-title='Global Menu']");
    private By globalmenuheading = By.xpath("//div[@class='slick-list draggable']//li/a[@role='heading']");
    private By globalnext      = By.xpath("//button[@class='slick-next']");

    // ✅ Add locator for "Policies and Documents" menu item
    private By pdmsMenuItem    = By.xpath("//div[@class='slick-list draggable']//li/a[normalize-space()='Policies and Documents']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        eleutil = new ElementUtil(driver);
    }

    // ✅ Step 1: Click Global Icon to open the menu
    public void clickGlobalMenu() {
        eleutil.doClick(globalicon);
        // Wait for menu to appear
        eleutil.waitForElementsToBeVisible(globalmenuheading, 5);
        System.out.println("✅ Global Menu opened");
    }

    public List<String> getGlobalMenuText() {
        List<WebElement> globalHeading = eleutil.getElements(globalmenuheading);
        System.out.println("Total elements found: " + globalHeading.size()); // ✅ Fix Bug 2

        List<String> ghlist = new ArrayList<String>();

        for (WebElement e : globalHeading) {
            String text = e.getText().trim();
            if (!text.isEmpty()) {
                ghlist.add(text);
            }
        }
        System.out.println("Global Menu Texts: " + ghlist.toString());
        return ghlist;
    }

    public PDMSPage clickPDMS() {
        // ✅ Step 1: Open Global Menu first
        clickGlobalMenu();

        int maxClicks = 5; // max panels to traverse

        for (int i = 0; i < maxClicks; i++) {
            List<String> d = getGlobalMenuText();
            System.out.println("Panel " + (i + 1) + ": " + d);

            // ✅ Fix Bug 3: use stream contains, not List.contains (exact match issue)
            boolean found = d.stream()
                    .anyMatch(text -> text.contains("Third Parties"));

            if (found) {
                // ✅ Click the actual menu item, not just globalnext
                eleutil.doClick(pdmsMenuItem);
                eleutil.waitForUrl(4, "Third Parties");
                return new PDMSPage(driver);
            }

            // Not found — go to next panel
            System.out.println("Not found on panel " + (i + 1) + " — clicking next arrow...");
            eleutil.doClick(globalnext);

            // Wait for next panel to load
            try { Thread.sleep(800); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }

        System.out.println("❌ 'Policies and Documents' not found in any panel!");
        return null;
    }

    public boolean isLogoDisplayed() {
        return eleutil.doIsDisplayed(globalicon);
    }
}