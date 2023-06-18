package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Card;
import ru.netology.data.DbUtils;
import ru.netology.page.BeginPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataGenerator.*;

public class PaymentPageTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        String url = System.getProperty("sut.url");
        open(url);
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldBuyInPaymentGate() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkSuccessNotification();
        assertEquals("APPROVED", DbUtils.getPaymentStatus());
    }

    @Test
    void shouldBuyInPaymentGateWithNameInLatinLetters() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidNameInLatinLetters(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkSuccessNotification();
        assertEquals("APPROVED", DbUtils.getPaymentStatus());
    }

    @Test
    void shouldNotBuyInPaymentGateWithDeclinedCardNumber() {
        Card card = new Card(getDeclinedNumber(), getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkDeclineNotification();
        assertEquals("DECLINED", DbUtils.getPaymentStatus());
    }

    @Test
    void shouldNotBuyInPaymentGateWithInvalidCardNumber() {
        Card card = new Card(getInvalidCardNumber(), getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkDeclineNotification();

    }

    @Test
    void shouldNotBuyInPaymentGateWithShortCardNumber() {
        Card card = new Card(getShortCardNumber(), getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkInvalidFormat();
    }

    @Test
    void shouldNotBuyInPaymentGateWithEmptyCardNumber() {
        Card card = new Card(null, getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkRequiredField();
    }

    @Test
    void shouldNotBuyInPaymentGateWithInvalidMonth() {
        Card card = new Card(getApprovedNumber(), "00", getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkInvalidDate();
    }

    @Test
    void shouldNotBuyInPaymentGateWithNonExistingMonth() {
        Card card = new Card(getApprovedNumber(), "13", getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkInvalidDate();

    }

    @Test
    void shouldNotBuyInPaymentGateWithExpiredMonth() {
        Card card = new Card(getApprovedNumber(), getLastMonth(), getCurrentYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkExpiredDate();
    }

    @Test
    void shouldNotBuyInPaymentGateWithEmptyMonth() {
        Card card = new Card(getApprovedNumber(), null, getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkRequiredField();
    }


    @Test
    void shouldNotBuyInPaymentGateWithExpiredYear() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getLastYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkExpiredDate();
    }

    @Test
    void shouldNotBuyInPaymentGateWithEmptyYear() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), null, getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkRequiredField();
    }


    @Test
    void shouldNotBuyInPaymentGateWithOnlyName() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getOnlyName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkInvalidName();
    }

    @Test
    void shouldNotBuyInPaymentGateWithOnlyNameInLatinLetters() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getOnlyNameInLatinLetters(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkInvalidName();
    }

    @Test
    void shouldNotBuyInPaymentGateWithOnlySurname() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getOnlySurname(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkInvalidName();
    }

    @Test
    void shouldNotBuyInPaymentGateWithOnlySurnameInLatinLetters() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getOnlySurnameInLatinLetters(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkInvalidName();
    }

    @Test
    void shouldNotBuyInPaymentGateWithNameAndSurnameWithDash() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), "Иван-Иванов", getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkInvalidFormat();
    }

    @Test
    void shouldNotBuyInPaymentGateWithTooLongName() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getTooLongName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkLongName();
    }

    @Test
    void shouldNotBuyInPaymentGateWithDigitsInName() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getNameWithNumbers(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkInvalidDataName();
    }

    @Test
    void shouldNotBuyInPaymentGateWithTooShortName() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getNameWithOneLetter(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkShortName();
    }

    @Test
    void shouldNotBuyInPaymentGateWithEmptyName() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), null, getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkRequiredField();
    }

    @Test
    void shouldNotBuyInPaymentGateWithSpaceInsteadOfName() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), " ", getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkInvalidDataName();
    }

    @Test
    void shouldNotBuyInPaymentGateWithOneDigitInCvc() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidName(), getCvcWithOneDigit());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkInvalidCvc();
    }

    @Test
    void shouldNotBuyInPaymentGateWithTwoDigitsInCvc() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidName(), getCvcWithTwoDigits());
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkInvalidCvc();
    }

    @Test
    void shouldNotBuyInPaymentGateWithEmptyCvc() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidName(), null);
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkRequiredField();
    }


    @Test
    void shouldNotBuyInPaymentGateWithAllEmptyFields() {
        Card card = new Card(null, null, null, null, null);
        var beginPage = new BeginPage();
        beginPage.buy();
        var paymentPage = new PaymentPage();
        paymentPage.fulfillData(card);
        paymentPage.checkAllFieldsAreRequired();
    }

}