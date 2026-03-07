package pages;

import base.BasePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Manufacturer;

public class ProductAddPage extends BasePage {
    private final By productName = (By.id("inputName"));
    private final By productPrice = (By.id("inputPrice"));
    private final By productAmount = (By.id("inputAmount"));
    private final By saveProduct = (By.id("save-new-product"));
    private final By productCompany = (By.xpath("//select[@id='inputManufacturer']"));


    public ProductAddPage(WebDriver driver) {
        super(driver);
    }

    public void createProduct(String name, String price, String amount, Manufacturer manufacturer){
        typeIntoElement(productName,name);
        typeIntoElement(productPrice,price);
        typeIntoElement(productAmount,amount);
        selectOptionByText(productCompany, manufacturer.getVisibleText());
        clickElement(saveProduct);
    }
}
