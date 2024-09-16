package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.YaMarketMainPage;
import pages.YaMarketSubtitlePage;

import static com.codeborne.selenide.Selenide.open;
import static helpers.Properties.testsProperties;

/**
 * @author Осюшкин Денис
 * Класс с реализацией тестовых шагов
 */
public class YaMarketStepdefs {

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

    @When("пользователь выставляет фильтр {string} по критерию {string}")
    public void пользовательВыставляетФильтрПоКритерию(String titleFilters, String titleSubfilters) {
        yaMarketSubtitlePage.searchByFilter(titleFilters, titleSubfilters);
    }

    @And("пользователь открывает весь список товаров, листая страницу до конца вниз")
    public void пользовательОткрываетВесьСписокТоваровЛистаяСтраницуДоКонцаВниз() {
        yaMarketSubtitlePage.scrollToEndPage();
    }

    @Then("проверяем, что все предложения соответствуют фильтру по производителю {string}")
    public void проверяемЧтоВсеПредложенияСоответствуютФильтруПоПроизводителю(String titleSubfilters) {
        yaMarketSubtitlePage.validateTitleFilter(titleSubfilters);
    }
}

