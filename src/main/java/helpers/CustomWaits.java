package helpers;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * @author Осюшкин Денис
 * Класс пользовательских ожиданий
 */
public class CustomWaits {

    /**
     * @author Осюшкин Денис
     * Метод ожидания для веб-элемента, который появляется при обновлении данных на странице
     * и исчезает через неопределенное время
     * @param elementXpath селектор элемента
     * @param timeWaitLocated время ожидания видимого элемента
     * @param timeWaitInvisible время ожидания невидимого элемента
     */
    public static void waitInvisibleIfLocated(String elementXpath, int timeWaitLocated, int timeWaitInvisible) {
        for (int i = 0; i < timeWaitLocated; ++i) {
            if (!$$x(elementXpath).isEmpty()) {
                for (int j = 0; ; ++j) {
                    if ($$x(elementXpath).isEmpty())
                        break;
                    if (j + 1 == timeWaitInvisible)
                        Assertions.fail("Элемент " + elementXpath + " не исчез за " + timeWaitInvisible + "секунд");
                    sleep(1000);
                }
            }
            sleep(1000);
        }
    }
}
