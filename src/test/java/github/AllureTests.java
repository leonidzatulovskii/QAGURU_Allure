package github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@Owner("leonidzatulovskii")
@Severity(SeverityLevel.CRITICAL)
@Feature("Вкладка Issues")
@Story("Таб бар")
public class AllureTests {

    Steps steps = new Steps();
    public static final String REPOSITORY = "leonidzatulovskii/QAGURU_JUnitAnnotations";

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @DisplayName("Проверка наличия вкладки Issues, логгирование с помощью Selenide Listener")
    public void issueTabSelenideLogger() {
        Allure.parameter("Вид логгирования", "Selenide Listener");

        open("https://github.com");

        $("[name = q").sendKeys(REPOSITORY, Keys.ENTER);

        $(linkText(REPOSITORY)).click();
        $("[data-tab-item=i1issues-tab]").shouldBe(Condition.visible);

    }

    @Test
    @DisplayName("Проверка наличия вкладки Issues, Lambda шаги")
    public void issueTabLambdaSteps() {
        Allure.parameter("Вид логгирования", "Lambda шаги");

        step("Открываем главную страницу github", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $("[name = q").sendKeys(REPOSITORY, Keys.ENTER);
        });

        step("Открываем репозиторий " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Проверяем наличие вкладки Issues", () -> {
            $("[data-tab-item=i1issues-tab]").shouldBe(Condition.visible);
        });
    }

    @Test
    @DisplayName("Проверка наличия вкладки Issues, аннотации Steps")
    public void issueTabAnnotationsSteps() {
        Allure.parameter("Вид логгирования", "аннотации Steps");

        steps.openMainPage();
        steps.searchRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.checkIssuesTab();
    }
}
