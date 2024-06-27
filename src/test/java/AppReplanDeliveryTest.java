import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppReplanDeliveryTest {

    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll(){ SelenideLogger.removeListener("allure");}

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void ShouldSuccessfulPlanMeeting() {
        DataGenerator.User validUser = DataGenerator.Registration.generateUser("ru");
        int addDaysForFirstMeeting = 4;
        String firstMeetingDate = DataGenerator.generateData(addDaysForFirstMeeting);
        int addDaysForSecondMeeting = 6;
        String secondMeetingDate = DataGenerator.generateData(addDaysForSecondMeeting);
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhoneNumber());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
       $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
       $("[data-test-id='date'] input").setValue(secondMeetingDate); /// Тут проверка надругую дату
       $(byText("Запланировать")).click();
       $("[data-test-id='replan-notification'] .notification__content")
               .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"))
               .shouldBe(visible);
       $("[data-test-id='replan-notification'] button").click();
       $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + secondMeetingDate))
                .shouldBe(visible);
    }
}




