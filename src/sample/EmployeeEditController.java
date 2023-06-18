package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeEditController implements Initializable {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;

    @FXML
    private TextField countryField;
    @FXML
    private TextField districtField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField homeNumberField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumber1Field;




    private static Employee employee;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;

    }


    public void ok(ActionEvent event)  {
        DatabaseConnection connectNow = new DatabaseConnection();
        if (isInputValid()) {
            employee.setName(firstNameField.getText());
            employee.setSurname(lastNameField.getText());


            employee.getAddress().setCountry(countryField.getText());
            employee.getAddress().setDistrict(districtField.getText());
            employee.getAddress().setStreet(streetField.getText());
            employee.getAddress().setZipCode(zipCodeField.getText());
            employee.getAddress().setHomeNumber(homeNumberField.getText());

            employee.setEmail(emailField.getText());
            employee.setPhoneNumber1(phoneNumber1Field.getText());


            connectNow.editEmployee(employee);


            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();

        }
        employeesController employeesController = new employeesController();
        employeesController.refreshTable();

    }

    private boolean isInputValid() {

        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
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
            //uzunluğu 5 mi
            errorMessage += "No valid zipcode!\n";
        }
        if (homeNumberField.getText() == null || homeNumberField.getText().length() == 0 ) {

            errorMessage += "No valid homenumber!\n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No valid email!\n";
        }
        if (phoneNumber1Field.getText() == null || phoneNumber1Field.getText().length() == 0 || phoneNumber1Field.getText().length() != 11) {
            //uzunluğu 11
            //0532-123-45-67
            errorMessage += "No valid phonenumber1!\n";
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


        firstNameField.setText(employee.getName());
        lastNameField.setText(employee.getSurname());

        countryField.setText(employee.getAddress().getCountry());
        districtField.setText(employee.getAddress().getDistrict());
        streetField.setText(employee.getAddress().getStreet());
        zipCodeField.setText(employee.getAddress().getZipCode());
        homeNumberField.setText(employee.getAddress().getHomeNumber());
        emailField.setText(employee.getEmail());
        phoneNumber1Field.setText(employee.getPhoneNumber1());


    }

}
