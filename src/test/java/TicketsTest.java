import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketsTest {
    private final By FROM = By.id("afrom");
    private final By TO = By.id("bfrom");
    private final By GO_BTN = By.xpath(".//span[@class = 'gogogo']");
    // private final By SELECTED_AIRPORT = By.xpath(".//span[@class='bTxt']");

    private final By GET_PRICE = By.xpath(".//span[@onclick = 'setLang();']");

    private final By FLIGHT_INFO = By.xpath(".//span[@class='bTxt']");
    private final By NAME_INPUT = By.id("name");
    private final By SURNAME_INPUT = By.id("surname");
    private final By DISCOUNT_CODE_INPUT = By.id("discount");
    private final By ADULTS_COUNT = By.id("adults");
    private final By CHILDREN_COUNT = By.id("children");
    private final By LUGGAGE_COUNT = By.id("bugs");
    private final By NEW_FLIGHT_DATE = By.id("flight");

    private final By BOOKBTN = By.id("book2");

    private final By SEATBTN = By.xpath(".//div[@onclick='seat(6)']");

    private final By BOOK3BTN = By.id("book3");

    private final By FINAL_MESSAGE = By.xpath(".//div[@class='finalTxt']");






    private final String URL = "http://www.qaguru.lv:8089/tickets/";
    private final String DEPARTURE_AIRPORT = "RIX";
    private final String ARRIVAL_AIRPORT = "MEL";
    private final String NAME = "Darja";
    private final String SURNAME = "Repina";
    private final String DISCOUNT = "090876";
    private final String ADULTS = "2";
    private final String CHILDREN = "1";
    private final String BUGS = "3";
    private final String FLIGHT = "11";

    private final String SUCCESFULLMESSAGE = "Thank You for flying with us!";



    @Test
    public void successTicketsBookCheck() {
        // open browser
        WebDriver browser = new ChromeDriver();

        // rashlopnuts okno na vsjo okno
        browser.manage().window().maximize();

        // open home page
        browser.get(URL);

        // select departure airport
        WebElement fromDropdown = browser.findElement(FROM);
        Select fromSelect = new Select(fromDropdown);
        fromSelect.selectByValue(DEPARTURE_AIRPORT);

        // select arrival airport
        WebElement toDropdown = browser.findElement(TO);
        Select toSelect = new Select(toDropdown);
        toSelect.selectByValue(ARRIVAL_AIRPORT);

        // press go go go btn
        browser.findElement(GO_BTN).click();

        // Ckeck if selected airport appears
        //  List<WebElement> selectedAirports = browser.findElements(SELECTED_AIRPORT);
        //  System.out.println("Fists airport" + selectedAirports.get(0).getText());
        //  System.out.println("Second airport" + selectedAirports.get(1).getText());


        // fill in passenger personal info
        WebElement nameInputField = browser.findElement(NAME_INPUT);
        nameInputField.clear();
        nameInputField.sendKeys(NAME);

        WebElement surnameInputField = browser.findElement(SURNAME_INPUT);
        surnameInputField.clear();
        surnameInputField.sendKeys(SURNAME);

        WebElement discountInputField = browser.findElement(DISCOUNT_CODE_INPUT);
        discountInputField.clear();
        discountInputField.sendKeys(DISCOUNT);

        WebElement adultsCountField = browser.findElement(ADULTS_COUNT);
        adultsCountField.clear();
        adultsCountField.sendKeys(ADULTS);

        WebElement childrenCountField = browser.findElement(CHILDREN_COUNT);
        childrenCountField.clear();
        childrenCountField.sendKeys(CHILDREN);

        WebElement luggageCountField = browser.findElement(LUGGAGE_COUNT);
        luggageCountField.clear();
        luggageCountField.sendKeys(BUGS);


        WebElement flightDatesDropdown = browser.findElement(NEW_FLIGHT_DATE);
        Select newFlightDatesSelect = new Select(flightDatesDropdown);
        newFlightDatesSelect.selectByValue(FLIGHT);


        // press get price link

        browser.findElement(GET_PRICE).click();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(FLIGHT_INFO, 5));

        List<WebElement> flightInfo = browser.findElements(FLIGHT_INFO);
        Assertions.assertEquals(DEPARTURE_AIRPORT, flightInfo.get(0).getText(), "Wrong departure Airport!");
        Assertions.assertEquals(ARRIVAL_AIRPORT, flightInfo.get(1).getText(), "Wrong Arrival Airport!");

        String name = flightInfo.get(2).getText();
        Assertions.assertEquals(NAME, name.substring(0, name.length() - 1), "Wrong name!");


        Assertions.assertEquals(DEPARTURE_AIRPORT, flightInfo.get(3).getText(), "Wrong departure Airport!");
        Assertions.assertEquals(ARRIVAL_AIRPORT, flightInfo.get(4).getText(), "Wrong Arrival Airport!");

        //press book btn
        browser.findElement(BOOKBTN).click();


        // select seat number
        wait.until(ExpectedConditions.presenceOfElementLocated(SEATBTN));
        browser.findElement(SEATBTN).click();


        //press book btn

        browser.findElement(BOOK3BTN).click();

        // check if successful msg appears
        wait.until(ExpectedConditions.presenceOfElementLocated(FINAL_MESSAGE));
        Assertions.assertEquals(browser.findElement(FINAL_MESSAGE).getText(), SUCCESFULLMESSAGE, "Wrong message");

    }
}
