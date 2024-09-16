#language:en
  Feature: Проверка результатов поиска в YandexMarket

    @ui #mvn test -D"cucumber.filter.tags=@ui"
    Scenario Outline: Проверка результатов поиска в YandexMarket с использованием Selenide
      Given пользователь переходит на страницу поиска сайта YandexMarket
      When пользователь наводит курсор на категорию "<Название категории>" в каталоге товаров
      And пользователь кликает по подкатегории товаров "<Название подкатегории категории>" в подкаталоге товаров
      Then проверяем наличие тайтла "<Название подкатегории категории>" после перехода в подраздел



      Examples:
        |Название категории  |Название подкатегории категории |
        |Электроника         |Смартфоны                       |