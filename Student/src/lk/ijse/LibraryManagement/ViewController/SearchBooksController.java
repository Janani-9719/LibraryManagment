package lk.ijse.LibraryManagement.ViewController;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.LibraryManagement.Proxy.ProxyHandler;
import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.custom.BookAuthorService;
import lk.ijse.LibraryManagement.service.custom.BookService;
import lk.ijse.LibraryManagement.service.custom.PositionService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchBooksController implements Initializable {


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
    private JFXTextField txtMatchiningNo;

    private BookService bservice;

    @FXML
    void SearchBookComboMouseClick(MouseEvent event) {

    }

    @FXML
    void SerchBook(ActionEvent event)throws Exception {

        bservice = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        BookDTO bookDTO = bservice.searchBook(txtSearch.getText().trim());

        //=============================Author===============================================
        BookAuthorService bookAuthorService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.AUTHORBOOK);
        AuthorDTO authorDTO= bookAuthorService.getAuthorForBook(bookDTO.getBid());

        txtAuthor.setText(authorDTO.getAuthorName());

        //=======================================================================
        PositionService positionService =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.POSITION);
        PositionDTO positionDTO = positionService.searchPositionByPosID(bookDTO.getPosId());
        if(positionDTO!=null){
            comboBook.setValue(bookDTO.getBookName());
            txtRackNo.setText(positionDTO.getRackNo()+"");
            txtCatagory.setText(bookDTO.getCategory());
            txtColoumNo.setText(positionDTO.getRowNo()+"");
            txtRowNo.setText(positionDTO.getRowNo()+"");

            //bookId = bookDTO.getBid();
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

           // bookId = bookDTO.getBid();
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

