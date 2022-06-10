package lk.ijse.LibraryManagement.ViewConroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.LibraryManagement.Proxy.ProxyHandler;
import lk.ijse.LibraryManagement.dto.StudentDTO;
import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.custom.BookService;
import lk.ijse.LibraryManagement.service.custom.StudentService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ModifyStudentsController implements Initializable {

    @FXML
    private Label btnAddStudent;

    @FXML
    private Label btnViewStudent;

    @FXML
    private Label btnModifyStudent;

    @FXML
    private TextField txtSearchBook;

    @FXML
    private JFXTextField txtRegisterNo;

    @FXML
    private ComboBox<String> combostudentName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtGrade;

    @FXML
    private TableView<StudentDTO> tblstudent;

    @FXML
    private DatePicker BirthDay;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private ImageView lblPic;

    @FXML
    private Label picture;

    private StudentService service;
    private int stuSid;
    private List<StudentDTO> allStudent=new ArrayList<>();

    private ObservableList<StudentDTO>studentDTOObservableList=FXCollections.observableArrayList();

    @FXML
    void DeleteModifyStudent(MouseEvent event) {
        try {
            service =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            boolean isRemove =service.removeStudent(Integer.parseInt(txtRegisterNo.getText().trim()));
            if(isRemove){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Success").show();
                loadAllStudents();
            }else {
                new Alert(Alert.AlertType.WARNING,"Delete Failed").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void OpenAddStudents(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/AddStudent.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnModifyStudent.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    void pictureMouseClick(MouseEvent event) {

        FileChooser fileChooser=new FileChooser();

        File file = fileChooser.showOpenDialog(null);

        if(file!=null){
            System.out.println(file.getAbsolutePath());

            try {
                Image imageForFile = new Image(file.toURI().toURL().toExternalForm());

                System.out.println(imageForFile.getHeight());
                lblPic.setImage(imageForFile);
            } catch (MalformedURLException e) {


            }


        }

    }

    @FXML
    void OpenModifyStudent(MouseEvent event) {


//        try {
//            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ModifyStudents.fxml"));
//            Scene temp = new Scene(root);
//            Stage stage =(Stage) this.btnModifyStudent.getScene().getWindow();
//            stage.setScene(temp);
//            stage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    @FXML
    void OpenViewStudent(MouseEvent event) {

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
    private  int sid1;

    @FXML
    void UpdateModifyStudent(MouseEvent event) {
        StudentDTO selectedItem =tblstudent.getSelectionModel().getSelectedItem();

        try {
            StudentDTO dto= new StudentDTO();
            sid1 = dto.getSid();
            //dto.setSid(stuSid);
            dto.setSid(selectedItem.getSid());
            dto.setRegId(Integer.parseInt(txtRegisterNo.getText().trim()));
            dto.setName((String)combostudentName.getSelectionModel().getSelectedItem());
            //dto.setName(.getText().trim());
            dto.setAddress(txtAddress.getText().trim());
            dto.setBday(BirthDay.getValue().toString());
            dto.setGrade(txtGrade.getText().trim());
            dto.setPhoto("");

            StudentService service =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);

            boolean b= service.updateStudent(dto);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"update Successfully").show();
                loadAllStudents();
            }else {
                new Alert(Alert.AlertType.WARNING,"Update Failed").show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @FXML
    void comboStudentNameMouseclick(MouseEvent event) {
        try {
            service =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StudentDTO dto = null;
        try {
            dto = service.searchStudent((String)combostudentName.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            e.printStackTrace();
        }
        stuSid=dto.getSid();
        txtAddress.setText(dto.getAddress());
        BirthDay.setValue(LocalDate.parse(dto.getBday().toString()));
        txtGrade.setText(dto.getGrade());
        txtRegisterNo.setText(dto.getRegId()+"");


    }

    @FXML
    void lblPhotoMouseClick(MouseEvent event) {

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

    private int sid;
    @FXML
    void tblStudentMouseClick(MouseEvent event) {
        StudentDTO studentDTO = tblstudent.getSelectionModel().getSelectedItem();
        sid=studentDTO.getSid();
        combostudentName.setValue(studentDTO.getName());
        txtAddress.setText(studentDTO.getAddress());
        txtRegisterNo.setText(String.valueOf(studentDTO.getRegId()));
        BirthDay.setValue(LocalDate.parse(studentDTO.getBday()));
        txtGrade.setText(studentDTO.getGrade());
    }

    @FXML
    void txtSearchActionPerformed(ActionEvent event) throws Exception{
        for (StudentDTO studentDTO:allStudent) {
            if(studentDTO.getRegId()==Integer.parseInt(txtSearchBook.getText())){
                sid=studentDTO.getSid();
                combostudentName.setValue(studentDTO.getName());
                txtAddress.setText(studentDTO.getAddress());
                txtRegisterNo.setText(String.valueOf(studentDTO.getRegId()));
                BirthDay.setValue(LocalDate.parse(studentDTO.getBday()));
                txtGrade.setText(studentDTO.getGrade());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblstudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("RegId"));
        tblstudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblstudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblstudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("bday"));
        //tblstudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("grade"));

        try {
            service =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
            loadAllStudents();
            loadStudentName();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadAllStudents() {
        try {
            tblstudent.getColumns().removeAll();
            allStudent=service.getAll();
            tblstudent.setItems(FXCollections.observableArrayList(allStudent));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStudentName() throws Exception{

        ObservableList<String> name=FXCollections.observableArrayList();

        for(StudentDTO p:allStudent){
            name.add(String.valueOf(p.getName()));
        }
        combostudentName.setItems(name);
    }

}

