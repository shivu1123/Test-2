package org.example.test2shivam;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label emailError;
    @FXML
    private Label passwordError;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        // Clear previous error messages
        emailError.setText("");
        passwordError.setText("");

        String email = username.getText().trim();
        String pass = password.getText().trim();
        boolean isValid = true;

        // Check if both fields are empty
        if (email.isEmpty() && pass.isEmpty()) {
            emailError.setText("Both email and password are required.");
            emailError.setTextFill(Color.RED);
            isValid = false;
        } else {
            // Validate email field
            if (email.isEmpty()) {
                emailError.setText("Email is required.");
                emailError.setTextFill(Color.RED);
                isValid = false;
            } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                emailError.setText("Invalid email format.");
                emailError.setTextFill(Color.RED);
                isValid = false;
            }

            // Validate password field
            if (pass.isEmpty()) {
                passwordError.setText("Password is required.");
                passwordError.setTextFill(Color.RED);
                isValid = false;
            } else if (pass.length() < 8) {
                passwordError.setText("Password must be at least 8 characters.");
                passwordError.setTextFill(Color.RED);
                isValid = false;
            }
        }

        // Proceed to dashboard if all inputs are valid
        if (isValid) {
            try {
                Parent secondScene = FXMLLoader.load(getClass().getResource("dashbord.fxml"));

                Stage secondStage = new Stage();
                secondStage.setTitle("Dashboard");
                secondStage.setScene(new Scene(secondScene));

                // Close the current stage
                Stage firstSceneStage = (Stage) username.getScene().getWindow();
                firstSceneStage.close();

                secondStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}