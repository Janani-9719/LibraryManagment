package lk.ijse.LibraryManagement.ViewConroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController  implements Initializable {



    @FXML
    private ImageView StudentDetailsBtn;

    @FXML
    private ImageView ReserveBookBtn;

    @FXML
    private ImageView Bookdetailsbtn;

    @FXML
    private ImageView DamageBookBtn;

    @FXML
    void OpenBookdetails(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/AddBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.Bookdetailsbtn.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void OpenReserveBooks(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ReserveBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.ReserveBookBtn.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    void OpenStudentDetails(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/AddStudent.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.StudentDetailsBtn.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void OpenDamageBooks(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/AddDamageBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.DamageBookBtn.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
