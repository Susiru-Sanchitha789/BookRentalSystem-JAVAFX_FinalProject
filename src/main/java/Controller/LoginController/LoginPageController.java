package Controller.LoginController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dto.LoginMemberDTO;
import javafx.scene.control.Alert;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
    ObservableList<LoginMemberDTO> loginMemberDTOS= FXCollections.observableArrayList();
    LoginPageService loginPageService=new LoginPageFromControllerImpl();

    Stage DashboardStage=new Stage();
    @FXML
    private TextField Passwordid;

    @FXML
    private TextField Usernameid;

    @FXML
    void LoginOnAction(ActionEvent event) {

        String Name=Usernameid.getText().trim();
        String Password=Passwordid.getText().trim();

        boolean found=true;
        for(LoginMemberDTO memberDTO : loginMemberDTOS){
            if(Name.equalsIgnoreCase(memberDTO.getName()) && Password.equalsIgnoreCase(memberDTO.getPassword())){
                found=false;
                String position=memberDTO.getPosition();
                if(position.equalsIgnoreCase("Admin")){
                       DashBoardLoader();
                }else if(position.equalsIgnoreCase("Staff")){
                       DashBoardLoader();
                }
              break;
            }
        }

        if(found){
            //System.out.println("Incorrect");
            String Namecheck=Usernameid.getText().trim();
            String Passwordcheck=Passwordid.getText().trim();
            for(LoginMemberDTO CheckMember : loginMemberDTOS){
                if(!Namecheck.equalsIgnoreCase(CheckMember.getName()) && !Passwordcheck.equalsIgnoreCase(CheckMember.getPassword())){
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Login Failed");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Incorrect Username or Password!");

                    DialogPane dialogPane = errorAlert.getDialogPane();
                    dialogPane.setPrefSize(275, 120);
                    errorAlert.showAndWait();
                    break;
                }
            }
        }

    }

    public void DatabaseLoader(){
        try {
          ObservableList<LoginMemberDTO>memberlist=loginPageService.Databaseloader();
          loginMemberDTOS.addAll(memberlist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseLoader();
    }

    public void DashBoardLoader(){
        Usernameid.setText("");
        Passwordid.setText("");
        try {
            DashboardStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashBoard.fxml"))));
            DashboardStage.show();
            DashboardStage.setTitle("Admin/Staff DashBoard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
