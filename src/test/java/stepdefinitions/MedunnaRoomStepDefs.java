package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Select;
import pages.MedunnaHomePage;
import pages.MedunnaRoomPage;
import utilities.Driver;

public class MedunnaRoomStepDefs {

    MedunnaHomePage medunnaHomePage = new MedunnaHomePage();
    MedunnaRoomPage medunnaRoomPage = new MedunnaRoomPage();

    @When("Click on ItemsAndTitles")
    public void click_on_ıtems_and_titles() {
        medunnaHomePage.itemsdAndTitles.click();
    }

    @When("click on Room option")
    public void click_on_room_option() {
        medunnaHomePage.roomOption.click();
    }

    @When("click on Create a new room button")
    public void click_on_create_a_new_room_button() {
        medunnaRoomPage.createANewRoomButton.click();
    }

    @When("enter {string} room number input")
    public void enter_room_number_input(String roomNumber) {
        int roomNumberFaker = Faker.instance().number().numberBetween(100000,1000000);

        medunnaRoomPage.roomNumberInput.sendKeys(roomNumberFaker+"");
    }

    @When("select Suite option from Room Type dropdown")
    public void select_suite_option_from_room_type_dropdown() {
        new Select(medunnaRoomPage.roomTypeDropDown).selectByIndex(3);
    }

    @When("click on Status checkbox")
    public void click_on_status_checkbox() {
        medunnaRoomPage.statusCheckbox.click();
    }

    @When("enter {string} in Price input")
    public void enter_in_price_input(String price) {
        medunnaRoomPage.priceInput.sendKeys(price);
    }

    @When("enter {string} in Description input")
    public void enter_in_description_input(String description) {
        medunnaRoomPage.descriptionInput.sendKeys(description);
    }

    @When("click on Save button")
    public void click_on_save_button() {
        medunnaRoomPage.saveSubmitButton.click();
    }

    @When("close the application")
    public void close_the_application() {
        Driver.closeDriver();
    }
}
