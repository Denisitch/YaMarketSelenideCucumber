package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * @author Осюшкин Денис
 * Класс задает методы, которые могут выполняться в различных точках цикла выполнения Cucumber.
 * Обычно они используются для настройки и демонтажа среды до и после каждого сценария
 */
public class Hooks {
    /**
     * Получение метаданных сценария по завершении теста
     * отсутствие тега после @Before говорит о том, что метод применяется ко всем тестам
     * @param scenario - объект метаданных сценария
     * @author Осюшкин Денис
     */
    @Before
    public void getScenarioInfo(Scenario scenario) {
        System.out.println("____________________________");
        System.out.println(scenario.getId());
        System.out.println(scenario.getName());
        System.out.println(scenario.getStatus());
        System.out.println(scenario.isFailed());
        System.out.println(scenario.getSourceTagNames());
        System.out.println(scenario.getUri());
        System.out.println("____________________________");
    }
}
