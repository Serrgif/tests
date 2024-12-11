package netology.pages;

import com.codeborne.selenide.SelenideElement;
import netology.data.Card;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitBuy {
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement monthField = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement yearField = $(byText("Год")).parent().$(".input__control");
    private SelenideElement cardHolderField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvvField = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement buyButton = $("button").shouldHave(exactText("Купить"));
    private SelenideElement continueButton = $$(".button").findBy(exactText("Продолжить"));
    private SelenideElement approvedOperation = $(byText("Операция одобрена Банком.")).parent().$(".notification__content");
    private SelenideElement cancelField = $$(".notification__closer").first(); // Используем $$ для получения списка и first()

     public DebitBuy(){
        buyButton.click();
    }


    public void inputData(Card card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolderField.setValue(card.getCardHolder());
        cvvField.setValue(card.getCvv());
        continueButton.click();
    }

    public void waitNotificationApproved() {
        approvedOperation.shouldBe(visible, Duration.ofSeconds(15));
        cancelField.click();
    }
}
