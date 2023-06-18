package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Card;
import ru.netology.page.BeginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.*;

public class CreditPageTest {
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
    void shouldNotBuyInCreditGateWithDeclinedCardNumber() {
        Card card = new Card(getDeclinedNumber(), getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkDeclineNotification();
    }

    @Test
    void shouldNotBuyInCreditGateWithInvalidCardNumber() {
        Card card = new Card(getInvalidCardNumber(), getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkDeclineNotification();

    }

    @Test
    void shouldNotBuyInCreditGateWithShortCardNumber() {
        Card card = new Card(getShortCardNumber(), getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkInvalidFormat();
    }

    @Test
    void shouldNotBuyInCreditGateWithEmptyCardNumber() {
        Card card = new Card(null, getCurrentMonth(), getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkRequiredField();
    }

    @Test
    void shouldNotBuyInCreditGateWithInvalidMonth() {
        Card card = new Card(getApprovedNumber(), "00", getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkInvalidDate();
    }


    @Test
    void shouldNotBuyInCreditGateWithNonExistingMonth() {
        Card card = new Card(getApprovedNumber(), "13", getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkInvalidDate();

    }

    @Test
    void shouldNotBuyInCreditGateWithExpiredMonth() {
        Card card = new Card(getApprovedNumber(), getLastMonth(), getCurrentYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkExpiredDate();
    }

    @Test
    void shouldNotBuyInCreditGateWithEmptyMonth() {
        Card card = new Card(getApprovedNumber(), null, getNextYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkRequiredField();
    }

    @Test
    void shouldNotBuyInCreditGateWithExpiredYear() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getLastYear(), getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkExpiredDate();
    }

    @Test
    void shouldNotBuyInCreditGateWithEmptyYear() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), null, getValidName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkRequiredField();
    }

    @Test
    void shouldNotBuyInCreditGateWithOnlyName() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getOnlyName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkInvalidName();
    }

    @Test
    void shouldNotBuyInCreditGateWithOnlyNameInLatinLetters() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getOnlyNameInLatinLetters(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkInvalidName();
    }

    @Test
    void shouldNotBuyInCreditGateWithOnlySurname() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getOnlySurname(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkInvalidName();
    }

    @Test
    void shouldNotBuyInCreditGateWithOnlySurnameInLatinLetters() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getOnlySurnameInLatinLetters(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkInvalidName();
    }

    @Test
    void shouldNotBuyInCreditGateWithNameAndSurnameWithDash() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), "Иван-Иванов", getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkInvalidFormat();
    }

    @Test
    void shouldNotBuyInCreditGateWithTooLongName() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getTooLongName(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkLongName();
    }

    @Test
    void shouldNotBuyInCreditGateWithDigitsInName() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getNameWithNumbers(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkInvalidDataName();
    }

    @Test
    void shouldNotBuyInCreditGateWithTooShortName() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getNameWithOneLetter(), getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkShortName();
    }

    @Test
    void shouldNotBuyInCreditGateWithEmptyName() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), null, getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkRequiredField();
    }

    @Test
    void shouldNotBuyInCreditGateWithSpaceInsteadOfName() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), " ", getValidCvc());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkInvalidDataName();
    }

    @Test
    void shouldNotBuyInCreditGateWithOneDigitInCvc() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidName(), getCvcWithOneDigit());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkInvalidCvc();
    }

    @Test
    void shouldNotBuyInCreditGateWithTwoDigitsInCvc() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidName(), getCvcWithTwoDigits());
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkInvalidCvc();
    }

    @Test
    void shouldNotBuyInCreditGateWithEmptyCvc() {
        Card card = new Card(getApprovedNumber(), getCurrentMonth(), getNextYear(), getValidName(), null);
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkRequiredField();
    }

    @Test
    void shouldNotBuyInCreditGateWithAllEmptyFields() {
        Card card = new Card(null, null, null, null, null);
        var beginPage = new BeginPage();
        beginPage.buyInCredit();
        var creditPage = beginPage.buyInCredit();
        creditPage.fulfillData(card);
        creditPage.checkAllFieldsAreRequired();

    }
}