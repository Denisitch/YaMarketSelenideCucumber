package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.title;
import static helpers.Assertions.assertTrue;
import static helpers.CustomWaits.waitInvisibleIfLocated;
import static helpers.Properties.testsProperties;

/**
 * @author Осюшкин Денис
 * Класс, характеризующий страницу подраздела сайта YandexMarket
 */
public class YaMarketSubtitlePage extends BasePage {

    /**
     * Универсальный селектор названия фильтра
     */
    private static final String TITLE_FILTER =
            "//div[@data-filter-type='%s']//h4[text()='%s']";

    /**
     * Селектор спиннера загрузки страницы при обновлении фильтров
     */
    private static final String SPINNER_PRELOAD =
            "//div[contains(@class, 'position_center')]//span[@data-auto='spinner']";

    /**
     * Селектор спиннера загрузки страницы при пролистывании страницы вниз
     */
    private static final String SPINNER_MORE =
            "//button[@data-auto='pager-more']//span[@data-auto='spinner']";

    /**
     * Селектор кнопки "Показать еще"
     */
    private static final String BUTTON_MORE =
            "//button[@data-auto='pager-more']";

    /**
     * Селектор веб-элемента блока продукта
     */
    private static final String PRODUCTS =
            "//div[@data-zone-name='productSnippet']";

    /**
     * @param titleCatalogSubitem название искомой подкатегории в каталоге
     * @return YaMarketSubtitlePage
     * @author Осюшкин Денис
     * Проверка тайтла
     */
    @Step("Проверяем наличие тайтла: {titleCatalogSubitem} после перехода в подраздел")
    public YaMarketSubtitlePage getResultTitle(String titleCatalogSubitem) {
        assertTrue(title().contains(titleCatalogSubitem),
                "Тайтл %s на сайте не содержит %s".formatted(title(), titleCatalogSubitem));
        return this;
    }

    /**
     * @param titleFilters    название фильтра
     * @param titleSubfilters критерий фильтра
     * @return YaMarketSubtitlePage
     * @author Осюшкин Денис
     * По названию фильтра производим фильтрацию с помощью чекбокса
     */
    @Step("Выставляем фильтр {titleFilters} по критерию: {titleSubfilters}")
    public YaMarketSubtitlePage searchByFilter(String titleFilters, String titleSubfilters) {
        $x("//input[@aria-label='в виде сетки']").click();
        searchFiltersCheckbox(titleFilters, titleSubfilters);
        return this;
    }

    /**
     * @return YaMarketSubtitlePage
     * @author Осюшкин Денис
     * Метод, пролистывающий страницу с товарами вниз
     */
    @Step("Открываем весь список товаров, листая страницу до конца вниз")
    public YaMarketSubtitlePage scrollToEndPage() {
        while (!$$x(BUTTON_MORE).isEmpty()) {
            $x(BUTTON_MORE).scrollTo();
            waitInvisibleIfLocated(SPINNER_MORE,
                    testsProperties.timeWaitLocated(), testsProperties.timeWaitInvisible());
        }
        return this;
    }

    /**
     * @param titleSubfilters критерий фильтра чекбокс
     * @return YaMarketSubtitlePage
     * @author Осюшкин Денис
     * Метод, в котором происходит проверка фильтра с чекбоксами
     */
    @Step("Проверяем, что все предложения соответствуют фильтру по производителю {titleSubfilters}")
    public YaMarketSubtitlePage validateTitleFilter(String titleSubfilters) {
        assertTrue(getTitleProducts().stream()
                        .allMatch(title -> title.contains(titleSubfilters)),
                "Не все предложения соответствуют фильтру по производителю %s");
        return this;
    }

    /**
     * @return List<String>
     * @author Осюшкин Денис
     * Метод, возвращающий список с названиями продуктов
     */
    @Step("Получаем список с названиями всех продуктов")
    private List<String> getTitleProducts() {
        return $$x(PRODUCTS + "//span[@data-auto='snippet-title']").stream()
                .map(SelenideElement::getText)
                .toList();
    }

    /**
     * @param titleFilters    название фильтра
     * @param titleSubfilters критерий фильтра
     * @author Осюшкин Денис
     * Метод, фильтрующий чекбокс по названию фильтра
     */
    @Step("Выставляем фильтр {titleFilters} по критерию: {titleSubfilters}")
    private void searchFiltersCheckbox(String titleFilters, String titleSubfilters) {
        SelenideElement itemBlockFilter = $x(TITLE_FILTER.formatted("enum", titleFilters));
        itemBlockFilter.$x("./ancestor::fieldset//button").shouldBe(visible).click();
        SelenideElement fieldInput = itemBlockFilter.$x("./ancestor::fieldset//input");
        fieldInput.click();
        fieldInput.sendKeys(titleSubfilters);
        itemBlockFilter.$x("./ancestor::fieldset//span[text()='%s']/preceding-sibling::*"
                .formatted(titleSubfilters)).click();
        waitInvisibleIfLocated(SPINNER_PRELOAD,
                testsProperties.timeWaitLocated(), testsProperties.timeWaitInvisible());
    }


}
