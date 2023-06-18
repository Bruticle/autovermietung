package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.naming.spi.InitialContextFactory;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



public class CarAddController implements Initializable {

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

    @FXML
    private ImageView imageView;

    @FXML
    private static Image image;

    public static String path1;

    private Stage dialogStage;
    private static Car car;

    public Image getImage() {
        return image;
    }

    public Car getCar() {
        return  car;
    }

    public String getPath1() {
        return path1;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void ok(ActionEvent event)  {
        DatabaseConnection connectNow = new DatabaseConnection();
        if (isInputValid())  {

            String name = nameField.getText();
            String make = makeField.getText();
            String model = modelField.getText();
            String year =  yearField.getText();
            String license_number = licenseNumberField.getText();
            boolean available = availableCheckBox.isSelected();

            String country = countryField.getText();
            String district = districtField.getText();
            String street = streetField.getText();
            String zipCode = zipCodeField.getText();
            int parkingSpotNumber = Integer.parseInt(parkingSpotNumberField.getText());

            int maxPerson = Integer.parseInt(maxPersonField.getText());
            double priceProKm = Double.parseDouble(pricePerKmField.getText());

            String insuranceCompName = insuranceCompNameField.getText();
            String insuranceCompNumber = insuranceCompNumberField.getText();

            double currentKmStatus = Double.parseDouble(currentKmStatusField.getText());
            String nextMaintenanceDate = nextMaintenanceDateField.getText();
            double fuelIndicator = Double.parseDouble(fuelIndicatorField.getText());



            connectNow.addCar(name,make,model,year,license_number,available,country,district,street,zipCode,parkingSpotNumber,maxPerson,priceProKm,insuranceCompName,insuranceCompNumber,currentKmStatus,nextMaintenanceDate,fuelIndicator);

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
        nextMaintenanceDateField.setPromptText("yyyy-mm-dd");
    }

    /*
    public void addPicture() throws URISyntaxException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(dialogStage);
        if (file != null) {
            String path = file.getAbsolutePath();
            path1 = path;
            //System.out.println(path);
            image = new Image(new File(path).toURI().toString());
            imageView.setImage(image);

        }

    }

     */

}
