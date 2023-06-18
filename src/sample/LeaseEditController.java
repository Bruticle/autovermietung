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

public class LeaseEditController implements Initializable {

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

    private AutoRentalSystem ars;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setLease(Lease lease) {
        this.lease = lease;

    }


    public void ok(ActionEvent event)  {
        DatabaseConnection connectNow = new DatabaseConnection();

        if (isInputValid()) {
            int customer_id = Integer.parseInt(customerField.getText());
            int car_id = Integer.parseInt(carField.getText());
            int employee_id = Integer.parseInt(employeeField.getText());

            /*
            connectNow.searchCar(car_id);
            connectNow.searchCustomer(customer_id);
            connectNow.searchEmployee(employee_id);

            int i = ars.searchCar(car_id);
            int j = ars.searchCustomer(customer_id);
            int k = ars.searchEmployee(employee_id);

             */

            lease.setCustomer(connectNow.searchCustomer(customer_id));
            lease.setRentedCar(connectNow.searchCar(car_id));
            lease.setSupervisingEmployee(connectNow.searchEmployee(employee_id));

            /*
            lease.getCustomer().setId(Integer.parseInt(customerField.getText()));
            lease.getRentedCar().setId(Integer.parseInt(carField.getText()));
            lease.getSupervisingEmployee().setId(Integer.parseInt(employeeField.getText()));

             */

            lease.setStartDate(startDateField.getText());
            lease.setEndDate(endDateField.getText());
            lease.setPrice(Double.parseDouble(priceField.getText()));
            lease.setInsuranceCosts(Double.parseDouble(insuranceField.getText()));
            lease.setStartKm(Double.parseDouble(startKmField.getText()));
            lease.setEndKm(Double.parseDouble(endKmField.getText()));
            lease.setDuration(Integer.parseInt(durationField.getText()));
            lease.setPriceProDay(Double.parseDouble(priceProKmField.getText()));

            lease.setClosed(closedCheckBox.isSelected());

            connectNow.editLease(lease);


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


        customerField.setText(""+lease.getCustomer().getId());
        carField.setText(""+lease.getRentedCar().getId());
        employeeField.setText(""+lease.getSupervisingEmployee().getId());

        closedCheckBox.setSelected(lease.isClosed());
        startDateField.setText(lease.getStartDate());
        startDateField.setPromptText("yyyy-mm-dd");
        endDateField.setText(lease.getEndDate());
        endDateField.setPromptText("yyyy-mm-dd");
        priceField.setText(""+lease.getPrice());
        insuranceField.setText(""+lease.getInsuranceCosts());
        startKmField.setText(""+lease.getStartKm());
        endKmField.setText(""+lease.getEndKm());
        durationField.setText(""+lease.getDuration());
        priceProKmField.setText(""+lease.getPriceProDay());


    }
}
