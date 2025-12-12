package Controller.LoginController;

import DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.LoginMemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageFromControllerImpl implements LoginPageService {

    @Override
    public ObservableList<LoginMemberDTO> Databaseloader() throws SQLException {
       ObservableList<LoginMemberDTO>LoginMember=FXCollections.observableArrayList();

        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM loginmembers");
        ResultSet resultSet=preparedStatement.executeQuery();

        while (resultSet.next()){
            LoginMemberDTO loginMemberDTO=new LoginMemberDTO(

                    resultSet.getString("Name"),
                    resultSet.getString("Password"),
                    resultSet.getString("Position")


            );
            LoginMember.add(loginMemberDTO);
        }
        return LoginMember;

    }


}
