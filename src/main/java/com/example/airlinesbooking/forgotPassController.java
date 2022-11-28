package com.example.airlinesbooking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class forgotPassController {

    @FXML
    private TextField fgp_email;

    @FXML
    private Label fgp_label;

    @FXML
    private Button fgp_mailBtn;

    @FXML
    private TextField fgp_otp;
    @FXML
    private Button fgp_validateOTP;
    @FXML
    private TextField fgp_username;

    @FXML
    private Label fgp_passwordLabel;
    private String username , email , otp;

//    @FXML
    @FXML
    private void validateUserInDatabase(ActionEvent event) throws SQLException {
        if (!Objects.equals(fgp_username.getText(), "") && !Objects.equals(fgp_email.getText(), "")){
              username = fgp_username.getText();
              Connection con = connectionProvider.getConnection();
              String query = "SELECT user_email FROM userdata WHERE user_name = ?";
              PreparedStatement pstmtLogin = con.prepareStatement(query);
              pstmtLogin.setString(1, username);
              ResultSet resultSet = pstmtLogin.executeQuery();
              resultSet.next();
              email = resultSet.getString("user_email");
              con.close();
              if (email.equals(fgp_email.getText())){
                  otp = new String(generateOtp.OTP(7));
                  String msg = "Your Otp is "+ otp;
                  sendMail.send_Mail(email,msg , "BookMyFlight : Forgot Password");
                  fgp_label.setVisible(true);
                  fgp_otp.setVisible(true);
                  fgp_validateOTP.setVisible(true);
              }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please enter email associated to your username");
                    alert.show();
              }
        }
    }
    public void validateOtp(ActionEvent event) throws SQLException {
        if (!Objects.equals(fgp_otp.getText(), "")){
//            System.out.println("here : "+otp);                // error test here
//            System.out.println("here3 : "+fgp_otp.getText());
            if (Objects.equals(fgp_otp.getText(), otp)){
                Connection con = connectionProvider.getConnection();
                String query = "SELECT user_password FROM userdata WHERE user_name = ?";
                PreparedStatement pstmtLogin = con.prepareStatement(query);
                pstmtLogin.setString(1, username);
                ResultSet resultSet = pstmtLogin.executeQuery();
                resultSet.next();
                String password = resultSet.getString("user_password");
                con.close();
                fgp_passwordLabel.setText("Your Password : "+password);
                fgp_mailBtn.setDisable(true);
                fgp_validateOTP.setDisable(true);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid OTP");
                alert.show();
            }

        }
    }
    public void goBackToLogin(ActionEvent event) throws IOException {
        DataBaseConnection.changeScene(event,"login.fxml","Login",null);
    }
}
