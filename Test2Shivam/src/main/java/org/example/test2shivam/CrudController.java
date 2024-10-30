package org.example.test2shivam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CrudController implements Initializable {
    @FXML
    private TextField enterid;
    @FXML
    private TextField entername;
    @FXML
    private TextField entersubject;
    @FXML
    private TextField enterroom;
    @FXML
    private Label errorLabel;

    @FXML
    private TableView<professor> tableView;
    @FXML
    private TableColumn<professor, Integer> id;
    @FXML
    private TableColumn<professor, String> pname;
    @FXML
    private TableColumn<professor, String> subject;
    @FXML
    private TableColumn<professor, Integer> classroom;

    ObservableList<professor> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        pname.setCellValueFactory(new PropertyValueFactory<>("pname"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        classroom.setCellValueFactory(new PropertyValueFactory<>("classroom"));
        tableView.setItems(list);
    }

    public void clearFields() {
        enterid.clear();
        entername.clear();
        entersubject.clear();
        enterroom.clear();
        errorLabel.setText("");  // Clear the error label
    }

    public boolean validateFields(String pname, String subject, String classroom, String id) {
        if (pname.isEmpty() || subject.isEmpty() || classroom.isEmpty()) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("All fields are required.");
            return false;
        }
        if (!id.isEmpty() && !id.matches("\\d+")) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("ID must be a number.");
            return false;
        }
        if (!classroom.matches("\\d+")) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Classroom must be a number.");
            return false;
        }
        return true;
    }

    public void createData(ActionEvent actionEvent) {
        String pname = entername.getText();
        String subject = entersubject.getText();
        String classroom = enterroom.getText();

        if (pname.isEmpty() && subject.isEmpty() && classroom.isEmpty()) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Name, Subject, and Classroom are required.");
            return;
        }

        String jdbcUrl = "jdbc:mysql://localhost:3306/Lab-1_shivam";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "INSERT INTO professor(pname, subject, classroom) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, pname);
            statement.setString(2, subject);
            statement.setInt(3, Integer.parseInt(classroom));
            statement.executeUpdate();


            clearFields();
            populateTable();
        } catch (SQLException e) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Database error occurred.");
            e.printStackTrace();
        }
    }

    public void updateData(ActionEvent actionEvent) {
        String id = enterid.getText();
        String pname = entername.getText();
        String subject = entersubject.getText();
        String classroom = enterroom.getText();

        if (!validateFields(pname, subject, classroom, id)) {
            return;
        }

        String jdbcUrl = "jdbc:mysql://localhost:3306/Lab-1_shivam";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "UPDATE professor SET pname = ?, subject = ?, classroom = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, pname);
            statement.setString(2, subject);
            statement.setInt(3, Integer.parseInt(classroom));
            statement.setInt(4, Integer.parseInt(id));
            statement.executeUpdate();


            clearFields();
            populateTable();
        } catch (SQLException e) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Database error occurred.");
            e.printStackTrace();
        }
    }

    public void deleteData(ActionEvent actionEvent) {
        String id = enterid.getText();

        if (id.isEmpty()) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("ID is required for deletion.");
            return;
        }

        String jdbcUrl = "jdbc:mysql://localhost:3306/Lab-1_shivam";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "DELETE FROM professor WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));
            statement.executeUpdate();


            clearFields();
            populateTable();
        } catch (SQLException e) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Database error occurred.");
            e.printStackTrace();
        }
    }

    public void loadData(ActionEvent actionEvent) {
        String id = enterid.getText();

        if (id.isEmpty()) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("ID is required to load data.");
            return;
        }

        String jdbcUrl = "jdbc:mysql://localhost:3306/Lab-1_shivam";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM professor WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                entername.setText(resultSet.getString("pname"));
                entersubject.setText(resultSet.getString("subject"));
                enterroom.setText(resultSet.getString("classroom"));
            } else {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("No record found with ID: " + id);
            }
        } catch (SQLException e) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Database error occurred.");
            e.printStackTrace();
        }
    }

    public void populateTable() {
        list.clear();
        String jdbcUrl = "jdbc:mysql://localhost:3306/Lab-1_shivam";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM professor";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String pname = resultSet.getString("pname");
                String subject = resultSet.getString("subject");
                int classroom = resultSet.getInt("classroom");

                list.add(new professor(id, pname, subject, classroom));
            }
            tableView.setItems(list);
        } catch (SQLException e) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Error fetching data.");
            e.printStackTrace();
        }
    }
}
