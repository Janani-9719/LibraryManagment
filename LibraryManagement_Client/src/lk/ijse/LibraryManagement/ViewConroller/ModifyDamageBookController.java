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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.LibraryManagement.Proxy.ProxyHandler;
import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.DamageDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.custom.BookAuthorService;
import lk.ijse.LibraryManagement.service.custom.BookService;
import lk.ijse.LibraryManagement.service.custom.DamageService;
import lk.ijse.LibraryManagement.service.custom.PositionService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifyDamageBookController implements Initializable {
    @FXML
    private Label btnaddDamageBook;

    @FXML
    private Label btnViewDamageBook;

    @FXML
    private Label btnModifyBook;

    @FXML
    private TextField txtDamageBook;

    @FXML
    private JFXTextField txtBatchNo;

    @FXML
    private ComboBox<String> comboBook;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtPublisher;

    @FXML
    private JFXTextField txtPages;

    @FXML
    private DatePicker PublishDate;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtSupplier;

    @FXML
    private DatePicker RemoveDate;

    @FXML
    private JFXTextField txtOther;

    @FXML
    private Label lblPic;
    @FXML
    private TextField txtColoumNo;

    @FXML
    private JFXTextField txtCatagory;

    @FXML
    private TextField txtRackno;

    @FXML
    private TextField txtRowNo;

    @FXML
    private TextField txtDvDecimal;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXTextField txtMachiningNo;

    @FXML
    private DatePicker ReceiveDate;
    private BookService bservice;



    @FXML
    void DeleteDamageBooks(MouseEvent event) {

        try {
            DamageService damageService=ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DAMAGE);
            DamageDTO dto = damageService.searchDamageBooks((String)comboBook.getSelectionModel().getSelectedItem());

            boolean isRemove = damageService.removeDamageBooks(Integer.parseInt(txtDamageBook.getText().trim()));
            if(isRemove){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Successful").show();

            }else {
                new Alert(Alert.AlertType.WARNING,"Delete Failed").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @FXML
    void searchAll(ActionEvent event) throws Exception{

        bservice = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        BookDTO bookDTO = bservice.searchBook((String)comboBook.getSelectionModel().getSelectedItem());

        //=============================Author===============================================
        BookAuthorService bookAuthorService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.AUTHORBOOK);
        AuthorDTO authorDTO= bookAuthorService.getAuthorForBook(bookDTO.getBid());

        txtAuthor.setText(authorDTO.getAuthorName());

        //=======================================================================
        PositionService positionService =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.POSITION);
        PositionDTO positionDTO = positionService.searchPositionByPosID(bookDTO.getPosId());
        if(positionDTO!=null){
            txtRackno.setText(positionDTO.getRackNo()+"");
            txtCatagory.setText(bookDTO.getCategory());
            txtColoumNo.setText(positionDTO.getRowNo()+"");
            txtRowNo.setText(positionDTO.getRowNo()+"");

            //bookId = bookDTO.getBid();
            txtBatchNo.setText(bookDTO.getBatchNo()+"");
            txtDvDecimal.setText(positionDTO.getDvNo()+"");
            txtMachiningNo.setText(bookDTO.getMachiningNo()+"");
            txtOther.setText(bookDTO.getOther());
            txtPages.setText(bookDTO.getPages());
            txtPrice.setText(bookDTO.getPrice()+"");
            txtSupplier.setText(bookDTO.getSuplier());
            PublishDate.setValue(LocalDate.parse(bookDTO.getPublishDate().toString()));
            txtPublisher.setText(bookDTO.getPublisher());
          //  ReceiveDate.setValue(LocalDate.parse(bookDTO.getReceiveDate().toString()));
            RemoveDate.setValue(LocalDate.parse(bookDTO.getRemovedDate().toString()));
            txtSupplier.setText(bookDTO.getSuplier());


        }




    }


    @FXML
    void OpenHome(MouseEvent event) {

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
    void OpenViewDamageBooks(MouseEvent event) {


        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ViewDamageBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnViewDamageBook.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    void openAddDamageBooks(MouseEvent event) {


        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/AddDamageBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnaddDamageBook.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            bservice =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            loadBookName();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadBookName() throws Exception{

        ArrayList<BookDTO> allList=(ArrayList<BookDTO>) bservice.getAll();

        ObservableList<String> name=FXCollections.observableArrayList();

        for(BookDTO p:allList){
            name.add(String.valueOf(p.getBookName()));


        }
        comboBook.setItems(name);

    }
    }



