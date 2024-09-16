package ru.yandex.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author Осюшкин Денис
 * Класс запуска тестов Cucumber
 */
@CucumberOptions(
        monochrome = true,
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "json:target/cucumber-report/report.json"},
        features = "src/test/java/features",
        glue = {"stepdefs", "hooks"},
        tags = "not @excluded"
)
@RunWith(Cucumber.class)
public class CucumberRunnerTest {
}
