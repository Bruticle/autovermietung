package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CarEditController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField makeField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField licenseNumberField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField districtField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField parkingSpotNumberField;
    @FXML
    private TextField maxPersonField;
    @FXML
    private TextField pricePerKmField;
    @FXML
    private TextField insuranceCompNameField;
    @FXML
    private TextField insuranceCompNumberField;
    @FXML
    private TextField currentKmStatusField;
    @FXML
    private TextField nextMaintenanceDateField;
    @FXML
    private TextField fuelIndicatorField;

    @FXML
    private CheckBox availableCheckBox;


    private static Car car;
    private Stage dialogStage;



    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCar(Car car) {
        this.car = car;

    }


    public void ok(ActionEvent event)  {
        DatabaseConnection connectNow = new DatabaseConnection();
        if (isInputValid()) {


            car.setName(nameField.getText());
            car.setMake(makeField.getText());
            car.setModel(modelField.getText());
            car.setYear(yearField.getText());
            car.setLicenseNumber(licenseNumberField.getText());
            car.setAvailable(availableCheckBox.isSelected());

            car.getCarAddress().setCountry(countryField.getText());
            car.getCarAddress().setDistrict(districtField.getText());
            car.getCarAddress().setStreet(streetField.getText());
            car.getCarAddress().setZipCode(zipCodeField.getText());
            car.getCarAddress().setNumberParkingSpot(Integer.parseInt(parkingSpotNumberField.getText()));

            car.setMaxPerson(Integer.parseInt(maxPersonField.getText()));
            car.setPriceProDay(Double.parseDouble(pricePerKmField.getText()));


            car.setInsuranceCompName(insuranceCompNameField.getText());
            car.setInsuranceCompPhoneNumber(insuranceCompNumberField.getText());

            car.setCurrentKmstatus(Double.parseDouble(currentKmStatusField.getText()));
            car.setNextMaintenance(nextMaintenanceDateField.getText());
            car.setFuelIndicator(Double.parseDouble(fuelIndicatorField.getText()));


            connectNow.editCar(car);


            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();

        }

    }

    private boolean isInputValid() {

        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (makeField.getText() == null || makeField.getText().length() == 0) {
            errorMessage += "No valid last make!\n";
        }
        if (modelField.getText() == null || modelField.getText().length() == 0) {
            errorMessage += "No valid model!\n";
        }
        if (yearField.getText() == null || yearField.getText().length() == 0) {
            errorMessage += "No valid year!\n";
        }
        if (licenseNumberField.getText() == null || licenseNumberField.getText().length() == 0) {
            errorMessage += "No valid license number!\n";
        }
        if (countryField.getText() == null || countryField.getText().length() == 0) {
            errorMessage += "No valid country!\n";
        }
        if (districtField.getText() == null || districtField.getText().length() == 0) {
            errorMessage += "No valid district!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }
        if (zipCodeField.getText() == null || zipCodeField.getText().length() == 0 || zipCodeField.getText().length() != 5) {
            errorMessage += "No valid zipcode!\n";
        }
        if (parkingSpotNumberField.getText() == null || parkingSpotNumberField.getText().length() == 0 ) {
            errorMessage += "No valid parking spot number!\n";
        }
        if (maxPersonField.getText() == null || maxPersonField.getText().length() == 0) {
            errorMessage += "No valid max person!\n";
        }
        if (pricePerKmField.getText() == null || pricePerKmField.getText().length() == 0 ) {
            errorMessage += "No valid price per km!\n";
        }
        if (insuranceCompNameField.getText() == null || insuranceCompNameField.getText().length() == 0 ) {
            errorMessage += "No valid insurance company name!\n";
        }
        if (insuranceCompNumberField.getText() == null || insuranceCompNumberField.getText().length() == 0) {
            errorMessage += "No valid insurance company number!\n";
        }
        if (currentKmStatusField.getText() == null || currentKmStatusField.getText().length() == 0) {

            errorMessage += "No valid current Km status!\n";
        }
        if (nextMaintenanceDateField.getText() == null || nextMaintenanceDateField.getText().length() == 0) {
            errorMessage += "No valid next maintenance date!\n";
        }
        if (fuelIndicatorField.getText() == null || fuelIndicatorField.getText().length() == 0) {
            errorMessage += "No valid fuel indicator!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        nameField.setText(car.getName());
        makeField.setText(car.getMake());
        modelField.setText(car.getModel());
        yearField.setText(car.getYear());
        licenseNumberField.setText(car.getLicenseNumber());
        availableCheckBox.setSelected(car.isAvailable());

        countryField.setText(car.getCarAddress().getCountry());
        districtField.setText(car.getCarAddress().getDistrict());
        streetField.setText(car.getCarAddress().getStreet());
        zipCodeField.setText(car.getCarAddress().getZipCode());
        parkingSpotNumberField.setText(""+car.getCarAddress().getNumberParkingSpot());

        maxPersonField.setText(""+car.getMaxPerson());
        pricePerKmField.setText(""+car.getPriceProDay());

        insuranceCompNameField.setText(car.getInsuranceCompName());
        insuranceCompNumberField.setText(car.getInsuranceCompPhoneNumber());

        currentKmStatusField.setText(""+car.getCurrentKmstatus());
        nextMaintenanceDateField.setText(car.getNextMaintenance());
        nextMaintenanceDateField.setPromptText("yyyy-mm-dd");
        fuelIndicatorField.setText(""+car.getFuelIndicator());




    }
}
