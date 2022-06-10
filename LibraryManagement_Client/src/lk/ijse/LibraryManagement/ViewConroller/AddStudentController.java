package lk.ijse.LibraryManagement.ViewConroller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.LibraryManagement.Proxy.ProxyHandler;
import lk.ijse.LibraryManagement.dto.StudentDTO;
import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.custom.StudentService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {

    private String path;
    private String bday;
    @FXML
    private Label addStu;

    @FXML
    private Label btnViewStudent;

    @FXML
    private Label btnModifyStudent;

    @FXML
    private JFXTextField txtRegisterNo;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private DatePicker BirthDay;

    @FXML
    private JFXTextField txtGrade;

    @FXML
    private JFXButton btnHome;

    @FXML
    private ImageView picImg;

    @FXML
    private Label imgtxtbtn;


    public AddStudentController() {



    }




    @FXML
    void AddStudent(MouseEvent event) {
        try {
            StudentDTO dto= new StudentDTO();
            dto.setSid(0);
            dto.setName(txtName.getText().trim());
            dto.setAddress(txtAddress.getText().trim());
            dto.setBday(BirthDay.getValue().toString());
            dto.setGrade(txtGrade.getText().trim());
            dto.setPhoto("");
            dto.setRegId(Integer.parseInt(txtRegisterNo.getText().trim()));
            StudentService service =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);

            boolean b= service.saveStudent(dto);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Added Successfully").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Added Failed").show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void imgbtnMouseClick(MouseEvent event) {
        FileChooser fileChooser=new FileChooser();

        File file = fileChooser.showOpenDialog(null);

        if(file!=null){
            System.out.println(file.getAbsolutePath());

            try {
                Image imageForFile = new Image(file.toURI().toURL().toExternalForm());

                System.out.println(imageForFile.getHeight());
                picImg.setImage(imageForFile);
            } catch (MalformedURLException e) {


            }


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
    void openViewStudent(MouseEvent event) {


        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ViewStudent.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnViewStudent.getScene().getWindow();
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
