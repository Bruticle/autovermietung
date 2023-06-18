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

public class EmployeeAddController implements Initializable {
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
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;





    private Stage dialogStage;
    private static Employee employee;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void ok(ActionEvent event)  {
        DatabaseConnection connectNow = new DatabaseConnection();
        if (isInputValid())  {

            String first_name = firstNameField.getText();
            String last_name = lastNameField.getText();


            String country = countryField.getText();
            String district = districtField.getText();
            String street = streetField.getText();
            String zipCode = zipCodeField.getText();
            String homeNumber = homeNumberField.getText();

            String email = emailField.getText();
            String phoneNumber1 = phoneNumber1Field.getText();


            String username = usernameField.getText();
            String password = passwordField.getText();





            connectNow.addEmployee(first_name,last_name,country,district,street,zipCode,homeNumber,email,phoneNumber1,username,password);
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
        if (usernameField.getText() == null || usernameField.getText().length() == 0  ) {
            //önceden alınmış bir username
            errorMessage += "No valid username!\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "No valid password!\n";

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

    }

}
