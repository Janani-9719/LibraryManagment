package lk.ijse.LibraryManagement.ViewConroller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.LibraryManagement.Proxy.ProxyHandler;
import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.dto.StudentDTO;
import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.custom.StudentService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewStudentController implements Initializable {

    @FXML
    private Label btnaddStudent;

    @FXML
    private Label viewStudent;

    @FXML
    private Label btnModifyStudent;

    @FXML
    private TableView<StudentDTO> tblStudent;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnView;

    private StudentService studentService;
    private ObservableList<StudentDTO>studentDTOObservableList=FXCollections.observableArrayList();

    @FXML
    void openAddStudent(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/AddStudent.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnaddStudent.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void openHome(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/LibraryDashboard.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnHome.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void openModifyStudent(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ModifyStudents.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnModifyStudent.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void openViewStudent(MouseEvent event) throws Exception{

        loadStudent();


    }

    private void loadStudent()throws Exception {
        tblStudent.getColumns().removeAll();
        ArrayList<StudentDTO> allStudent=(ArrayList<StudentDTO>)studentService.getAll();
        tblStudent.setItems(FXCollections.observableArrayList(allStudent));




    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            studentService =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
            ObservableList<StudentDTO> observableList= FXCollections.observableArrayList();
            tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("RegId"));
            tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
            tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
            tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("bday"));
            tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("grade"));




        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
