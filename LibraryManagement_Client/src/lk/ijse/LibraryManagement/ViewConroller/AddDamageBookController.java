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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddDamageBookController implements Initializable {

    @FXML
    private Label btnDamage;


    @FXML
    private Label btnViewDamageBook;

    @FXML
    private Label btnAddModifyBook;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXTextField txtBatchNo;

    @FXML
    private ComboBox<String> comboBook;

    @FXML
    private DatePicker ReceiveDate;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtPublisher;

    @FXML
    private JFXTextField txtPages;

    @FXML
    private DatePicker publisherDate;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtSupplier;

    @FXML
    private DatePicker RemoveDate;

    @FXML
    private JFXTextField txtOther;

    @FXML
    private ImageView picImg;

    @FXML
    private Label piclbl;

    @FXML
    private JFXTextField txtCatagory;

    @FXML
    private TextField txtRackNo;

    @FXML
    private TextField txtRowNo;

    @FXML
    private TextField txtColoumNo;

    @FXML
    private TextField txtDvDecimal;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXTextField txtMatchiningNo;

    private BookService bservice;
    private int bookId;

    @FXML
    void AddDamageBook(MouseEvent event)throws Exception {
        DamageDTO dto= new DamageDTO(0,bookId);
        DamageService service = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DAMAGE);
        boolean isAdd = service.saveDamageBooks(dto);
        if(isAdd) {
            new Alert(Alert.AlertType.CONFIRMATION, "Added Succesful").show();


        }else {
            new Alert(Alert.AlertType.WARNING,"Added failed").show();
        }

    }

    private int bid;

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
            txtRackNo.setText(positionDTO.getRackNo()+"");
            txtCatagory.setText(bookDTO.getCategory());
            txtColoumNo.setText(positionDTO.getRowNo()+"");
            txtRowNo.setText(positionDTO.getRowNo()+"");

            bookId = bookDTO.getBid();
            txtBatchNo.setText(bookDTO.getBatchNo()+"");
            txtDvDecimal.setText(positionDTO.getDvNo()+"");
            txtMatchiningNo.setText(bookDTO.getMachiningNo()+"");
            txtOther.setText(bookDTO.getOther());
            txtPages.setText(bookDTO.getPages());
            txtPrice.setText(bookDTO.getPrice()+"");
            txtSupplier.setText(bookDTO.getSuplier());
            publisherDate.setValue(LocalDate.parse(bookDTO.getPublishDate().toString()));
            txtPublisher.setText(bookDTO.getPublisher());
            ReceiveDate.setValue(LocalDate.parse(bookDTO.getReceiveDate().toString()));
            RemoveDate.setValue(LocalDate.parse(bookDTO.getRemovedDate().toString()));
            txtSupplier.setText(bookDTO.getSuplier());


        }





    }

    @FXML
    void SearchBookComboMouseClick(MouseEvent event) throws Exception {


    }


    @FXML
    void addPicture(MouseEvent event) {

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
    void openModyfyBook(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ModifyDamageBooks.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnAddModifyBook.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void openViewDamageBook(MouseEvent event) {
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
    void searchBk(ActionEvent event) throws Exception {
        bservice = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        BookDTO bookDTO = bservice.searchBook(txtSearch.getText().trim());

        //=============================Author===============================================
        BookAuthorService bookAuthorService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.AUTHORBOOK);
        AuthorDTO authorDTO = bookAuthorService.getAuthorForBook(bookDTO.getBid());

        txtAuthor.setText(authorDTO.getAuthorName());

        //=======================================================================
        PositionService positionService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.POSITION);
        PositionDTO positionDTO = positionService.searchPositionByPosID(bookDTO.getPosId());
        if (positionDTO != null) {
            comboBook.setValue(bookDTO.getBookName());
            txtRackNo.setText(positionDTO.getRackNo() + "");
            txtCatagory.setText(bookDTO.getCategory());
            txtColoumNo.setText(positionDTO.getRowNo() + "");
            txtRowNo.setText(positionDTO.getRowNo() + "");

            //bookId = bookDTO.getBid();
            txtBatchNo.setText(bookDTO.getBatchNo() + "");
            txtDvDecimal.setText(positionDTO.getDvNo() + "");
            txtMatchiningNo.setText(bookDTO.getMachiningNo() + "");
            txtOther.setText(bookDTO.getOther());
            txtPages.setText(bookDTO.getPages());
            txtPrice.setText(bookDTO.getPrice() + "");
            txtSupplier.setText(bookDTO.getSuplier());
            publisherDate.setValue(LocalDate.parse(bookDTO.getPublishDate().toString()));
            txtPublisher.setText(bookDTO.getPublisher());
            ReceiveDate.setValue(LocalDate.parse(bookDTO.getReceiveDate().toString()));
            RemoveDate.setValue(LocalDate.parse(bookDTO.getRemovedDate().toString()));
            txtSupplier.setText(bookDTO.getSuplier());


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
