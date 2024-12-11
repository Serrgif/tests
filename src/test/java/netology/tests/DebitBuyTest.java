package netology.tests;

import com.codeborne.selenide.Selenide;
import lombok.val;
import netology.data.BdSqlHelper;
import netology.data.DataHelper;
import netology.pages.DebitBuy; // Убедитесь, что импортируете правильный класс
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitBuyTest {
    @BeforeEach
    public void openPage() {
        open("http://localhost:8080");
    }

    @AfterEach
    public void cleanBase() {
        BdSqlHelper.clearDB();
    }

    @Test
    void buyPositiveAllFieldValidApproved() {
        val startPage = new DebitBuy();
        startPage.inputData(DataHelper.getApprovedCard());
        assertEquals("APPROVED", BdSqlHelper.getPaymentStatus());
    }
}
