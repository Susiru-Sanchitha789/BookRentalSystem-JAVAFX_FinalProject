package Controller.LoginController;

import javafx.collections.ObservableList;
import model.dto.LoginMemberDTO;

import java.sql.SQLException;

public interface LoginPageService {

    public ObservableList<LoginMemberDTO> Databaseloader() throws SQLException;

}
