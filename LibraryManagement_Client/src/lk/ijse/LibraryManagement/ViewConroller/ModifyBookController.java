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
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.custom.AuthorService;
import lk.ijse.LibraryManagement.service.custom.BookAuthorService;
import lk.ijse.LibraryManagement.service.custom.BookService;
import lk.ijse.LibraryManagement.service.custom.PositionService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifyBookController implements Initializable {

    @FXML
    private Label btnaddBook;

    @FXML
    private Label btnViewBooks;

    @FXML
    private Label btnModifyBook;

    @FXML
    private TextField txtSearchBook;

    @FXML
    private ComboBox<String> comboBook;

    @FXML
    private JFXTextField txtMachiningNo;

    @FXML
    private JFXTextField txtBatchNo;

    @FXML
    private DatePicker ReceiveDate;

    @FXML
    private JFXTextField txtPublisher;

    @FXML
    private DatePicker publisherDate;

    @FXML
    private JFXTextField txtPages;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtSupplier;

    @FXML
    private JFXTextField txtOther;

    @FXML
    private DatePicker RemoveDate;

    @FXML
    private Label lblPic;

    @FXML
    private ComboBox<String> comboSelectCategory;

    @FXML
    private ComboBox<Integer> comboRackNo;

    @FXML
    private ComboBox<Integer> comboRowNo;

    @FXML
    private ComboBox<Integer> comboColoumNo;

    @FXML
    private TextField txtDvDecimal;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private ComboBox<String> comboAuthor;

    private BookService bService;
    private AuthorService aservice;
    private PositionService positionService;

    @FXML
    void DeleteBook(MouseEvent event) throws Exception{
        BookService bookService=ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        BookDTO dto =bookService.searchBook(comboBook.getSelectionModel().getSelectedItem() );
        boolean b = bookService.removeBook(dto.getBid());

        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Success").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Delete failed").show();
        }
;
    }

    @FXML
    void searchAll(ActionEvent event) throws Exception{

        BookService bservice = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        BookDTO bookDTO = bservice.searchBook((String)comboBook.getSelectionModel().getSelectedItem());

        //=============================Author===============================================
        BookAuthorService bookAuthorService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.AUTHORBOOK);
        AuthorDTO authorDTO= bookAuthorService.getAuthorForBook(bookDTO.getBid());

        comboAuthor.setValue(authorDTO.getAuthorName());

        //=======================================================================
        PositionService positionService =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.POSITION);
        PositionDTO positionDTO = positionService.searchPositionByPosID(bookDTO.getPosId());
        if(positionDTO!=null){
            comboRackNo.setValue(positionDTO.getRackNo());
            comboSelectCategory.setValue(bookDTO.getCategory());
            comboColoumNo.setValue(positionDTO.getColNo());
            comboRowNo.setValue(positionDTO.getRowNo());

            //bookId = bookDTO.getBid();
            txtBatchNo.setText(bookDTO.getBatchNo()+"");
            txtDvDecimal.setText(positionDTO.getDvNo()+"");
            txtMachiningNo.setText(bookDTO.getMachiningNo()+"");
            txtOther.setText(bookDTO.getOther());
            txtPages.setText(bookDTO.getPages());
            txtPrice.setText(bookDTO.getPrice()+"");
            txtSupplier.setText(bookDTO.getSuplier());
            publisherDate.setValue(LocalDate.parse(bookDTO.getPublishDate().toString()));
            txtPublisher.setText(bookDTO.getPublisher());
//            ReceiveDate.setValue(LocalDate.parse(bookDTO.getReceiveDate().toString()));
            RemoveDate.setValue(LocalDate.parse(bookDTO.getRemovedDate()));
            txtSupplier.setText(bookDTO.getSuplier());


        }

    }


    @FXML
    void OpenViewBooks(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ViewBooks.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnViewBooks.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void UpdateBook(MouseEvent event) {

        BookDTO dto =new BookDTO();
        try {
            BookService bookService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
            PositionService positionService =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.POSITION);
            int coloumNo=Integer.parseInt(comboColoumNo.getSelectionModel().getSelectedItem()+"");
            int rowNo = Integer.parseInt(comboRowNo.getSelectionModel().getSelectedItem()+"");
            int rackNo = Integer.parseInt(comboRackNo.getSelectionModel().getSelectedItem()+"");

            PositionDTO posDTo = positionService.searchPositionByThreeId(rackNo,coloumNo,rowNo);
            int posId = posDTo.getPosId();

            PositionDTO positionDTO = new PositionDTO(
                    posId,
                    Double.parseDouble(txtDvDecimal.getText()),
                    Integer.parseInt(comboRackNo.getSelectionModel().getSelectedItem()+""),
                    Integer.parseInt(comboRowNo.getSelectionModel().getSelectedItem()+""),
                    Integer.parseInt(comboColoumNo.getSelectionModel().getSelectedItem()+"")
            );

            dto.setBatchNo(Integer.parseInt(txtBatchNo.getText().trim()));
            dto.setBid(0);
            dto.setBookName(comboBook.getSelectionModel().getSelectedItem());
            dto.setCategory(comboSelectCategory.getSelectionModel().getSelectedItem());
            dto.setMachiningNo(Integer.parseInt(txtMachiningNo.getText()));
            dto.setOther(txtOther.getText().trim());
            dto.setPages(txtPages.getText().trim());
            dto.setPosId(posId);
            dto.setPrice(Double.parseDouble(txtPrice.getText().trim()));
            dto.setPublishDate(publisherDate.getValue().toString());
            dto.setPublisher(txtPublisher.getText().trim());
            dto.setReceiveDate(ReceiveDate.getValue().toString());
            dto.setSuplier(txtSupplier.getText().trim());
            dto.setRemovedDate(RemoveDate.getValue().toString());


            BookAuthorService bookAuthorService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.AUTHORBOOK);
            AuthorService authorService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.AUTHOR);
            AuthorDTO authorDTO = authorService.searchAuthor(comboAuthor.getSelectionModel().getSelectedItem());
            AuthorDTO bookAuthdto = bookAuthorService.getAuthorForBook(Integer.parseInt(txtMachiningNo.getText().trim()));


            boolean updateAuthor = bookAuthorService.updateAuthorForBook(authorDTO,Integer.parseInt(txtMachiningNo.getText().trim()));
            boolean updateBook = bookService.updateBook(dto,positionDTO);

            if(updateBook){
                new Alert(Alert.AlertType.CONFIRMATION,"Update Sucessful");
            }else {
                new Alert(Alert.AlertType.WARNING,"Update Failed");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void comboAuthorMouseClick(MouseEvent event) {

    }

    @FXML
    void comboBookMouseClick(MouseEvent event) {

    }

    @FXML
    void openAdBook(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/AddBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnaddBook.getScene().getWindow();
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            bService =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            loadBookName();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            aservice=ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.AUTHOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            loadAuthorName();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            positionService=ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.POSITION);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            loadRackNo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            loadRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            loadColoum();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            bService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            loadCatagory();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadCatagory() throws Exception {
        ArrayList<BookDTO> Bkc =(ArrayList<BookDTO>)bService.getAll ();
        ObservableList<String> st=FXCollections.observableArrayList();
        for(BookDTO b:Bkc){
            st.add(b.getCategory());
        }
        comboSelectCategory.setItems(st);
    }

    private void loadColoum() throws Exception {

        ArrayList<PositionDTO> allPosi=(ArrayList<PositionDTO>)positionService.getAll();
        ObservableList<Integer> pos=FXCollections.observableArrayList();
        for (PositionDTO f:allPosi){
            pos.add(f.getColNo());
        }
        comboColoumNo.setItems(pos);
    }

    private void loadRow() throws Exception {
        ArrayList<PositionDTO> allPosi=(ArrayList<PositionDTO>)positionService.getAll();
        ObservableList<Integer> pos=FXCollections.observableArrayList();
        for (PositionDTO f:allPosi){
            pos.add(f.getRowNo());
        }
        comboRowNo.setItems(pos);
    }

    private void loadRackNo() throws Exception {
        ArrayList<PositionDTO> allPosi=(ArrayList<PositionDTO>)positionService.getAll();
        ObservableList<Integer> pos=FXCollections.observableArrayList();
        for (PositionDTO f:allPosi){
            pos.add(f.getRackNo());
        }
        comboRackNo.setItems(pos);
    }

    private void loadAuthorName() throws Exception{
        ArrayList<AuthorDTO> allList=(ArrayList<AuthorDTO>)aservice.getAll();

        ObservableList<String> name=FXCollections.observableArrayList();

        for(AuthorDTO p:allList){
            name.add(String.valueOf(p.getAuthorName()));


        }
        comboAuthor.setItems(name);

    }

    private void loadBookName() throws Exception{

        ArrayList<BookDTO> allList=(ArrayList<BookDTO>)bService.getAll();

        ObservableList<String> name=FXCollections.observableArrayList();

        for(BookDTO p:allList){
            name.add(String.valueOf(p.getBookName()));


        }
        comboBook.setItems(name);

    }
    }




