package github;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class Steps {

    @Step("Открываем главную страницу github")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {repository}")
    public void searchRepository(String repository) {
        $("[name = q").sendKeys(repository, Keys.ENTER);
    }

    @Step("Открываем репозиторий {repository}")
    public void openRepository(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Проверяем наличие вкладки Issues")
    public void checkIssuesTab() {
        $("[data-tab-item=i1issues-tab]").shouldBe(Condition.visible);
    }
}
