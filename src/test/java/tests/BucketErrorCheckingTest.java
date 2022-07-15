package tests;

import org.junit.Before;
import org.junit.Test;
import pages.BucketPage;
import pages.HomePage;
import pages.ProductPage;
import pages.WearPage;

public class BucketErrorCheckingTest {

    @Before
    public void setup() {
        System.setProperty("webdriver.driver.chrome", "chromedriver");
        HomePage homePage = new HomePage();
        homePage.openPage().clickGoToWear();

        WearPage wearPage = new WearPage();
        wearPage.clickStoreProduct("Футболка поло черная (м)");

        ProductPage productPage = new ProductPage();
        productPage.clickAddToBucketButton();
    }

    @Test
    public void checkingIncorrectNumberAlert() throws InterruptedException {
        BucketPage bucketPage = new BucketPage();
        bucketPage.sendName("Lilith")
                .sendPhone("(000)000-00-00") // если тут "+7(000)000-00-00", то тест падает, так как условия проходят и идет перенаправление на оплату
                .sendRegion("Planet Earth")
                .sendAddress("Москва, пер. Зеленый, д. 3")
                .sendReceiverName("Selena")
                .sendStreet("Зеленый")
                .sendHouse("3")
                .sendOffice("777")
                .clickOrderButton()
                .checkPhoneErrorText("Укажите, пожалуйста, корректный номер телефона")
                .checkMainErrorText("Укажите, пожалуйста, корректный номер телефона");
    }
}
