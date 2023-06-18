package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class customersController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Customer> tableView = new TableView<>();

    @FXML
    private TableColumn<Customer,String> idCol;
    @FXML
    private TableColumn<Customer,String>  firstNameCol;
    @FXML
    private TableColumn<Customer,String>  lastNameCol;


    @FXML
    private Label idLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label licenseNumberLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneNumber1Label;
    @FXML
    private Label phoneNumber2Label;
    @FXML
    private Label paymentInfoLabel;


    @FXML
    private TextField filterTextField;

    public void switchToHomepages(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToCars(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("cars.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToLeases(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("leases.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToEmployees(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("employees.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToSettings(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToLogout(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("logout.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buildData();
        showPersonDetails(null);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> showPersonDetails(newValue) );

    }

    // tableview'deki column'ları
    //SQL ile veri çekerek doldurma fonskiyonu
    public void buildData() {

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory("name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory("surname"));

        DatabaseConnection connectNow = new DatabaseConnection();
        tableView.setItems(connectNow.createAllCustomers());


    }

    public void refreshTable() {

        DatabaseConnection connectNow = new DatabaseConnection();
        tableView.getItems().clear();
        tableView.setItems(connectNow.createAllCustomers());
    }

    public void showPersonDetails(Customer customer) {

        if(customer != null ) {
            idLabel.setText(""+customer.getId());
            firstNameLabel.setText(customer.getName());
            lastNameLabel.setText(customer.getSurname());
            licenseNumberLabel.setText(customer.getDrivingLicenseNumber());
            emailLabel.setText(customer.getEmail());
            phoneNumber1Label.setText(customer.getPhoneNumber1());
            phoneNumber2Label.setText(customer.getPhoneNumber2());
            addressLabel.setText(customer.getAddress().toString());
            paymentInfoLabel.setText(customer.getPaymentInformation().toString());

        }else {
            idLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            licenseNumberLabel.setText("");
            addressLabel.setText("");
            emailLabel.setText("");
            phoneNumber1Label.setText("");
            phoneNumber2Label.setText("");
            paymentInfoLabel.setText("");


        }

    }


    public void switchtoEditDialog(ActionEvent event) throws IOException {

        DatabaseConnection connectNow = new DatabaseConnection();


        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Customer selectedItem = tableView.getSelectionModel().getSelectedItem();



        if (selectedIndex >= 0) {
            CustomerEditController customerEditController = new CustomerEditController();
            //System.out.println(selectedItem);
            customerEditController.setCustomer(selectedItem);

            try {
                Parent parent = FXMLLoader.load(getClass().getResource("customerEditDialog.fxml"));
                Scene scene  = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();

            }catch (IOException ioException) {
                ioException.printStackTrace();
                ioException.getCause();
            }



        }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer in the table.");
            alert.showAndWait();
        }


    }

    public void switchtoAddDialog(ActionEvent event) throws IOException {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("customerAddDialog.fxml"));
            Scene scene  = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        }catch(IOException ioException) {
            ioException.printStackTrace();
            ioException.getCause();
        }


    }



    public void deleteCustomer() {

        
        DatabaseConnection connectNow = new DatabaseConnection();


        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Customer selectedItem = tableView.getSelectionModel().getSelectedItem();

        if (selectedIndex >= 0) {
            tableView.getItems().remove(selectedIndex);
            connectNow.deleteCustomer(selectedItem);

        }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer in the table.");
            alert.showAndWait();
        }
        refreshTable();

    }

    public void filterTable() {
        String text = filterTextField.getText();
        // If filter text is empty, display all persons.
        DatabaseConnection connectNow = new DatabaseConnection();
        ObservableList<Customer> customerList = connectNow.createAllCustomers();
        ObservableList<Customer> filteredCustomerList ;
        filteredCustomerList= FXCollections.observableArrayList();
        if(text == null) {
            //do nothing
        }else {
            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = text.toLowerCase();
            for(int i = 0;i<customerList.size();i++) {
                Customer customer = customerList.get(i);
                if (customer.getName().toLowerCase().contains(lowerCaseFilter)) {
                    // Filter matches first name.
                    filteredCustomerList.add(customer);
                } else if (customer.getSurname().toLowerCase().contains(lowerCaseFilter)) {
                    // Filter matches last name.
                    filteredCustomerList.add(customer);
                }
            }

            tableView.setItems(filteredCustomerList);
        }

        for(int i = 0;i<filteredCustomerList.size();i++) {
            System.out.println(filteredCustomerList.get(i));
        }

    }




}