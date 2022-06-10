package lk.ijse.LibraryManagement.ViewConroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.LibraryManagement.Proxy.ProxyHandler;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.BookStudentDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.dto.StudentDTO;
import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.custom.BookService;
import lk.ijse.LibraryManagement.service.custom.BookStudentService;
import lk.ijse.LibraryManagement.service.custom.PositionService;
import lk.ijse.LibraryManagement.service.custom.StudentService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SubmitBookController implements Initializable {

    @FXML
    private Label btnReserveBook;

    @FXML
    private Label btnViewReservation;

    @FXML
    private Label btnSubmitbook;

    @FXML
    private ComboBox<Integer> comboRegNo;

    @FXML
    private JFXTextField txtGrade;

    @FXML
    private Label lblpic;

    @FXML
    private DatePicker BorrowDate;

    @FXML
    private DatePicker ReturnDate;

    @FXML
    private JFXTextField txtBookId;

    @FXML
    private JFXTextField txtLastBookName;

    @FXML
    private JFXTextField txtStuentName;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private JFXButton btnHome;

    @FXML
    private Label lblStatus;

    private StudentService sservice;

    @FXML
    void ComboStudentRegItemStateChange(MouseEvent event) {

    }

    @FXML
    void SubmitBook(MouseEvent event) throws Exception {
        String date = null;

        int posId = 0;
        int dvNo=0;
        int rackNo=0;
        int rowNo=0;
        int colNo=0;

//        int stuID = Integer.bitCount(comboRegNo.getSelectionModel().getSelectedItem());
        int stId= Integer.parseInt(txtStuentName.getText());


        //=============================================================================
        StudentService studentService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
        StudentDTO stuDto = new StudentDTO();
        stuDto.setSid(stId);

        List<StudentDTO> stList= studentService.getAll();
        for(StudentDTO stList1:stList){
            if(txtStuentName.getText().equals(stList1.getName())){
                stId =stList1.getSid();
            }
        }


        //========================================Book Student================================
        BookStudentService bookStudentService=ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENTBOOK);
        BookStudentDTO bookStudentDTO = bookStudentService.getLastReservationDetails(stId);



        bookStudentDTO.setReturnDate(date);

        //=========================Book================================================
        BookService bookService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        BookDTO bookDTO =new BookDTO();

        int bid = Integer.parseInt(txtBookId.getText());

        posId= bookStudentDTO.getBookDto().getPosId();
        PositionService positionService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.POSITION);
        PositionDTO positionDTO=positionService.searchPositionByPosID(posId);

        if(positionDTO!=null){
            dvNo= (int) positionDTO.getDvNo();
            rackNo =positionDTO.getRackNo();
            rowNo=positionDTO.getRowNo();
            colNo=positionDTO.getColNo();

        }

        PositionDTO positionDTO1 = new PositionDTO(posId,dvNo,rackNo,rowNo,colNo);

        bookDTO.setBid(bid);

        boolean isAdded =false;
        if(ReturnDate.getValue()==null){
            isAdded = bookStudentService.saveBookStudent(bookStudentDTO,bookDTO,stuDto,positionDTO);

            }else {
            isAdded=bookStudentService.updateBookStudent(bookStudentDTO,bookDTO,stuDto,positionDTO);
        }

        if(isAdded){
            new Alert(Alert.AlertType.CONFIRMATION,"Added Succesful").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Added Failed").show();
        }
        }





    @FXML
    void lblPicMouseClick(MouseEvent event) {

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
    void openReserveBook(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ReserveBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnReserveBook.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void openViewBook(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ViewBooks.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnViewReservation.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            sservice = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            loadRegIds();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadRegIds()throws Exception {

        ArrayList<StudentDTO> allIds=(ArrayList<StudentDTO>)sservice.getAll();
        ObservableList<Integer> ids=FXCollections.observableArrayList();
        for(StudentDTO s:allIds){
            ids.add(s.getRegId());
        }
        comboRegNo.setItems(ids);

    }
    }

