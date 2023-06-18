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

public class LeaseAddController implements Initializable {

    @FXML
    private TextField customerField;
    @FXML
    private TextField carField;
    @FXML
    private TextField employeeField;
    @FXML
    private TextField startDateField;
    @FXML
    private TextField endDateField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField insuranceField;
    @FXML
    private TextField startKmField;
    @FXML
    private TextField endKmField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField priceProKmField;



    @FXML
    private CheckBox closedCheckBox;





    private Stage dialogStage;
    private static Lease lease;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void ok(ActionEvent event)  {
        DatabaseConnection connectNow = new DatabaseConnection();
        if (isInputValid())  {

            int customer_id = Integer.parseInt(customerField.getText());
            int car_id = Integer.parseInt(carField.getText());
            int employee_id = Integer.parseInt(employeeField.getText());;

            String startDate = startDateField.getText();
            String endDate = endDateField.getText();

            double price = Double.parseDouble(priceField.getText());
            double insuranceCosts = Double.parseDouble(insuranceField.getText());

            boolean closed = closedCheckBox.isSelected();

            int duration = Integer.parseInt(durationField.getText());
            double startKm = Double.parseDouble(startKmField.getText());
            double endKm = Double.parseDouble(endKmField.getText());
            double priceProKm = Double.parseDouble(priceProKmField.getText());





            connectNow.addLease(customer_id,car_id,employee_id,startDate,endDate,price,insuranceCosts,closed,duration,startKm,endKm,priceProKm);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }


    }

    private boolean isInputValid() {

        String errorMessage = "";

        if (customerField.getText() == null || customerField.getText().length() == 0) {
            errorMessage += "No valid customer id!\n";
        }
        if (carField.getText() == null || carField.getText().length() == 0) {
            errorMessage += "No valid car id!\n";
        }
        if (employeeField.getText() == null || employeeField.getText().length() == 0) {
            errorMessage += "No valid employee id!\n";
        }
        if (startDateField.getText() == null || startDateField.getText().length() == 0) {
            errorMessage += "No valid start date!\n";
        }
        if (endDateField.getText() == null || endDateField.getText().length() == 0) {
            errorMessage += "No valid end date!\n";
        }
        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        }
        if (insuranceField.getText() == null || insuranceField.getText().length() == 0 ) {
            //uzunluğu 5 mi
            errorMessage += "No valid insurance costs!\n";
        }if (startKmField.getText() == null || startKmField.getText().length() == 0) {
            errorMessage += "No valid start km!\n";
        }
        if (endKmField.getText() == null || endKmField.getText().length() == 0) {
            errorMessage += "No valid end km!\n";
        }
        if (priceProKmField.getText() == null || priceProKmField.getText().length() == 0) {
            errorMessage += "No valid price pro km!\n";
        }
        if (durationField.getText() == null || durationField.getText().length() == 0 ) {
            //uzunluğu 5 mi
            errorMessage += "No valid duration!\n";
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
        leasesController leasesController = new leasesController();
        leasesController.refreshTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startDateField.setPromptText("yyyy-mm-dd");
        endDateField.setPromptText("yyyy-mm-dd");
    }


}
