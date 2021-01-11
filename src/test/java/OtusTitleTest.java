import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class OtusTitleTest {

    private final Logger logger = LogManager.getLogger(OtusTitleTest.class);
    private static WebDriver driver;
    protected static String url;

    @Before
    public void setUp() {
        logger.info("Подготавливаю браузер Chrome");
        WebDriverManager.chromedriver().setup();

        logger.info("Получаю url из конфига");
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        url = cfg.url();
    }

    @Test
    public void isTitleCorrect() {
        driver = new ChromeDriver();
        logger.info("Открываю страницу " + url);
        driver.get(url);
        logger.info("Проверяю содержание поля title");
        assertThat("Значение поля title не совпадает с ожидаемым",
                driver.getTitle(),
                equalTo("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям"));
    }

    @After
    public void tearDown() {
        logger.info("Закрываю браузер Chrome");
        driver.quit();
    }

}
