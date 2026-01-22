package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы элементов
    private By orderButtonTop = By.cssSelector(".Button_Button__ra12g"); // заказать вверху страницы
    private By orderButtonBottom = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM"); // заказать внизу страницы
    private By cookieConsent = By.className("App_CookieConsent__1yUIN"); // всплывающее окно с согласием на использование файлов cookie

    // Локаторы элементов аккордеона
    private By accordionHeading1 = By.id("accordion__heading-0"); // первый вопрос
    private By accordionHeading2 = By.id("accordion__heading-1"); // второй вопрос
    private By accordionHeading3 = By.id("accordion__heading-2"); // третий вопрос
    private By accordionHeading4 = By.id("accordion__heading-3"); // четвертый вопрос
    private By accordionHeading5 = By.id("accordion__heading-4"); // пятый вопрос
    private By accordionHeading6 = By.id("accordion__heading-5"); // шестой вопрос
    private By accordionHeading7 = By.id("accordion__heading-6"); // седьмой вопрос
    private By accordionHeading8 = By.id("accordion__heading-7"); // восьмой вопрос

    // Конструктор
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Методы взаимодействия с элементами
    public void clickAccordion(int questionNumber) {
        By headingLocator = getAccordionHeadingByNumber(questionNumber);
        scrollToElement(driver.findElement(headingLocator));
        driver.findElement(headingLocator).click();
    }

    // Метод для получения элемента текста аккордеона с ожиданием его появления
    public WebElement getAccordionText(int questionNumber) {
        By textLocator = By.id("accordion__panel-" + (questionNumber - 1));
        return wait.until(ExpectedConditions.presenceOfElementLocated(textLocator));
    }

    private By getAccordionHeadingByNumber(int questionNumber) {
        switch (questionNumber) {
            case 1: return accordionHeading1;
            case 2: return accordionHeading2;
            case 3: return accordionHeading3;
            case 4: return accordionHeading4;
            case 5: return accordionHeading5;
            case 6: return accordionHeading6;
            case 7: return accordionHeading7;
            case 8: return accordionHeading8;
            default:
                throw new IllegalArgumentException("Invalid question number: " + questionNumber);
        }
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void closeCookieConsent() {
        WebElement consent = driver.findElement(cookieConsent);
        if (consent.isDisplayed()) {
            consent.findElement(By.cssSelector("button")).click(); // Нажимаем на кнопку закрытия всплывающего окна
        }
    }
}