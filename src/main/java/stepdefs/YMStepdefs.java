package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.YaMarketMainPage;
import pages.YaMarketSubtitlePage;

import static com.codeborne.selenide.Selenide.open;
import static helpers.Properties.testsProperties;

public class YMStepdefs extends BaseSteps {

    YaMarketMainPage yaMarketMainPage = new YaMarketMainPage();

    YaMarketSubtitlePage yaMarketSubtitlePage = new YaMarketSubtitlePage();


    @Given("пользователь переходит на страницу поиска сайта YandexMarket")
    public void пользовательПереходитНаСтраницуПоискаСайтаYandexMarket() {
        open(testsProperties.yandexMarketUrl());
    }

    @When("пользователь наводит курсор на категорию {string} в каталоге товаров")
    public void пользовательНаводитКурсорНаКатегориюВКаталогеТоваров(String titleCatalogItem) {
        yaMarketMainPage.hoverToItem(titleCatalogItem);
    }

    @And("пользователь кликает по подкатегории товаров {string} в подкаталоге товаров")
    public void пользовательКликаетПоПодкатегорииТоваровВПодкаталогеТоваров(String titleCatalogSubitem) {
        yaMarketMainPage.searchCatalogSubitem(titleCatalogSubitem, YaMarketSubtitlePage.class);
    }

    @Then("проверяем наличие тайтла {string} после перехода в подраздел")
    public void проверяемНаличиеТайтлаПослеПереходаВПодраздел(String titleCatalogSubitem) {
        yaMarketSubtitlePage.getResultTitle(titleCatalogSubitem);
    }
}

