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
import lk.ijse.LibraryManagement.business.custom.AuthorBO;
import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.custom.AuthorService;
import lk.ijse.LibraryManagement.service.custom.BookService;
import lk.ijse.LibraryManagement.service.custom.PositionService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

private AuthorBO authorBO;


    @FXML
    private Label AddBookbtn;

    @FXML
    private Label viewBookbtn;

    @FXML
    private Label modifyBookbtn;

    @FXML
    private ComboBox<String> comboAuther;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXButton btnAddAuthor;

    @FXML
    private Label lblPic;

    @FXML
    private JFXTextField txtMatchiningNo;

    @FXML
    private JFXTextField txtBatchNo;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtPublisher;

    @FXML
    private DatePicker publisherDate;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtSuplier;

    @FXML
    private DatePicker removeDate;

    @FXML
    private TextField txtOtherDetails;

    @FXML
    private JFXTextField txtpages;

    @FXML
    private TextField txtCatagory;


    @FXML
    private ComboBox<Integer> comboRackNo;

    @FXML
    private ComboBox<String> comboRowNo;

    @FXML
    private ComboBox<String> comboColoumNo;

    @FXML
    private TextField txtDvDecimal;

    @FXML
    private TextField txtRackNo;

    @FXML
    private TextField txtRowNo;

    @FXML
    private TextField txtColoumNo;

    @FXML
    private JFXButton btnAddPosition;

    @FXML
    private JFXButton btnaddBook;

    @FXML
    private ImageView picImg;

    @FXML
    private Label lblPhotoClick;

    @FXML
    private JFXButton btnHome;

    private AuthorService service;
    private PositionService pservice;

    @FXML
    void AddBooks(MouseEvent event) {

        // ==============position=============
        try {
            PositionService service = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.POSITION);
            int rackNo = Integer.parseInt(comboRackNo.getSelectionModel().getSelectedItem().toString());
            int rowNo = Integer.parseInt((String)comboRowNo.getSelectionModel().getSelectedItem());
            int colomnNo=Integer.parseInt((String)comboColoumNo.getSelectionModel().getSelectedItem());
            String dvNo = (txtDvDecimal.getText().trim()+"");

            PositionDTO positionDTO= new PositionDTO(0,dvNo,rackNo,rowNo,colomnNo);



            //===============Author=================
            AuthorService authorService =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.AUTHOR);

            String authorname=((String)comboAuther.getSelectionModel().getSelectedItem());
            AuthorDTO authorDTO = new AuthorDTO(0,authorname);
            //AuthorDTO authorDTO= authorService.searchAuthor((String)comboAuther.getSelectionModel().getSelectedItem());

            //============ Book author============================

            //int authorId= authorDTO.getAuthorId();
            // int machiningNo=Integer.parseInt(txtMatchiningNo.getText().trim());
            //  BookAuthorDTO bookAuthorDTO= new BookAuthorDTO(0,machiningNo,authorId);

            //===================== book=============================
            BookDTO dto = new BookDTO();
            dto.setBatchNo(Integer.parseInt(txtBatchNo.getText().trim()));
            dto.setBid(0);
            dto.setBookName(txtBookName.getText().trim());
            dto.setCategory(txtCatagory.getText().trim());
            dto.setMachiningNo(Integer.parseInt(txtMatchiningNo.getText().trim()));
            dto.setOther(txtOtherDetails.getText().trim());
            dto.setPages(txtpages.getText().trim());
            dto.setPosId(0);
            dto.setPrice(Double.parseDouble(txtPrice.getText().trim()));
            dto.setPublishDate(publisherDate.getValue().toString());
            dto.setPublisher(txtPublisher.getText().trim());
            dto.setReceiveDate("");
            dto.setSuplier(txtSuplier.getText().trim());
            dto.setRemovedDate(removeDate.getValue().toString());

            BookService bookService = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
            boolean isAdd = bookService.saveBook(dto,positionDTO,authorDTO);

            if(isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"added Succesfuly").show();

            }else {
                new Alert(Alert.AlertType.WARNING,"Added failed").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @FXML
    void AddNewAuthor(MouseEvent event) {
        if(txtAuthor.getText().trim().equalsIgnoreCase("")){
         new Alert(Alert.AlertType.WARNING,"Incomplete").show();
        }else {

            try {
                AuthorDTO dto = new AuthorDTO();
                dto.setAuthorId(0);
                dto.setAuthorName(txtAuthor.getText().trim());
                AuthorService service =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.AUTHOR);

                boolean b = service.saveAuthor(dto);

                if(b){
                    new Alert(Alert.AlertType.CONFIRMATION,"Added Successful").show();
                }else {
                    new Alert(Alert.AlertType.WARNING,"Added Failed").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    void addPic(MouseEvent event) {
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
    void SelectAutherActionPerform(ActionEvent event) {

    }

    @FXML
    void SelectAutherMouseClick(MouseEvent event) {

    }

    @FXML
    void addNewPosition(MouseEvent event) {

        try {
        PositionDTO dto = new PositionDTO();
        dto.setPosId(0);
        dto.setColNo(Integer.parseInt(txtColoumNo.getText().trim()));
        dto.setDvNo(Double.parseDouble(txtDvDecimal.getText().trim()));
        dto.setRackNo(Integer.parseInt(txtRackNo.getText().trim()));
        dto.setRowNo(Integer.parseInt(txtRowNo.getText().trim()));


            PositionService service = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.POSITION);
            boolean b = service.savePosition(dto);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Added Succesfuly").show();

            }else {
                new Alert(Alert.AlertType.WARNING,"Added Failed").show();
            }
        } catch (Exception e) {
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
    void openModifyBook(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ModifyBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.modifyBookbtn.getScene().getWindow();
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
            Stage stage =(Stage) this.viewBookbtn.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addBookController=this;
        try {
            service = ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.AUTHOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            loadAuthors();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pservice =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.POSITION);
            System.out.println("proxy");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            loadPositions();
            loadRowNo();
            loadRackNo();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadRowNo()throws Exception {

        ArrayList<PositionDTO> allList=(ArrayList<PositionDTO>) pservice.getAll();

        ObservableList<String> name=FXCollections.observableArrayList();

        for(PositionDTO p:allList){
            name.add(p.getRowNo()+"");


            System.out.println(p.getColNo());
        }
        comboRowNo.setItems(name);

    }

    private void loadRackNo() throws Exception{

        ArrayList<PositionDTO> allList=(ArrayList<PositionDTO>) pservice.getAll();

        ObservableList<Integer> name=FXCollections.observableArrayList();

        for(PositionDTO p:allList){

            name.add(p.getRackNo());

            System.out.println(p.getColNo());
        }

        comboRackNo.setItems(name);
    }

    private void loadPositions() throws Exception {
        ArrayList<PositionDTO> allList=(ArrayList<PositionDTO>) pservice.getAll();

        ObservableList<String> name=FXCollections.observableArrayList();

        for(PositionDTO p:allList){
            name.add(p.getColNo()+"");
            //name.add(p.getRackNo()+"");

            System.out.println(p.getColNo());
        }
        comboColoumNo.setItems(name);
        //comboRackNo.setItems(name);




    }

    private static AddBookController addBookController;
    public static AddBookController getAddBookController(){
        return addBookController;
    }

    private void loadAuthors() throws Exception {
        ArrayList<AuthorDTO> allItems = (ArrayList<AuthorDTO>) service.getAll();

        ObservableList<String> iNames=FXCollections.observableArrayList();
        for (AuthorDTO i: allItems) {
            iNames.add(String.valueOf(i.getAuthorName()));

        }

        comboAuther.setItems(iNames);
    }
}
