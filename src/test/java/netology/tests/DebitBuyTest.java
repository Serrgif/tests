package netology.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import netology.data.BdSqlHelper;
import netology.data.DataHelper;
import netology.pages.DebitBuy; // Убедитесь, что импортируете правильный класс
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitBuyTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @BeforeEach
    public void openPage() {
        open("http://localhost:8080");
    }

    @AfterEach
    public void cleanBase() {
        BdSqlHelper.clearDB();
    }
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @Test
    void buyPositiveAllFieldValidApproved() {
        val startPage = new DebitBuy();
        startPage.inputData(DataHelper.getApprovedCard());
        startPage.waitNotificationApproved();
        assertEquals("APPROVED", BdSqlHelper.getPaymentStatus());
    }
}
