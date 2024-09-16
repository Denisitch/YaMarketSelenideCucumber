package helpers;

import org.aeonbits.owner.Config;

/**
 * @author Осюшкин Денис
 * Интерфейс, для получения доступа к проперти файлу
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/main/resources/tests.properties"
})
public interface TestsProperties extends Config {

    /**
     * @author Осюшкин Денис
     * URL ссылка на страницу YandexMarket
     * @return String
     */
    @Key("yandex.market.url")
    String yandexMarketUrl();

    /**
     * @author Осюшкин Денис
     * Дефолтный таймаут
     * @return int
     */
    @Key("default.timeout")
    int defaultTimeout();

    /**
     * @author Осюшкин Денис
     * Время ожидания видимого элемента в сек
     * @return int
     */
    @Key("time.wait.located")
    int timeWaitLocated();

    /**
     * @author Осюшкин Денис
     * Время ожидания невидимого элемента в сек
     * @return int
     */
    @Key("time.wait.invisible")
    int timeWaitInvisible();

    /**
     * @author Осюшкин Денис
     * Время ожидания потока в сек
     * @return int
     */
    @Key("time.wait.for.sleep")
    int timeSleep();

    /**
     * @author Осюшкин Денис
     * Время ожидаемой работы цикла в миллисек
     * @return long
     */
    @Key("time.elapsed")
    long timeElapsed();
}
