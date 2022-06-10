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
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.custom.BookService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewDamageBookController implements Initializable {
    @FXML
    private TableView<BookDTO> viewDamageBook;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnView;

    @FXML
    private Label btnAddDamageBook;

    @FXML
    private Label btnViewDamageBook;

    @FXML
    private Label btnModifyBook;

    private BookService bookService;
    private ObservableList<BookDTO> bookDTOObservableList=FXCollections.observableArrayList();

    @FXML
    void ViewDamageBooks(MouseEvent event) {

        try {
            loadDamageBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadDamageBooks() throws Exception {

        viewDamageBook.getColumns().removeAll();
        ArrayList<BookDTO> allBooks=(ArrayList<BookDTO>)bookService.getAll();
        viewDamageBook.setItems(FXCollections.observableArrayList(allBooks));
    }

    @FXML
    void openAddDamageBook(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/AddDamageBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnAddDamageBook.getScene().getWindow();
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
    void openModifyBook(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/ModifyBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnModifyBook.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            bookService =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<BookDTO> observableList=FXCollections.observableArrayList();

        viewDamageBook.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bid"));
        viewDamageBook.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        viewDamageBook.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookName"));
        viewDamageBook.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("category"));
        viewDamageBook.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("machiningNo"));
        viewDamageBook.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("other"));
        viewDamageBook.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Pages"));
        viewDamageBook.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("price"));
        viewDamageBook.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        viewDamageBook.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("Publisher"));
        viewDamageBook.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("receiveDate"));
        viewDamageBook.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("removedDate"));
        viewDamageBook.getColumns().get(12).setCellValueFactory(new PropertyValueFactory<>("Suplier"));
        viewDamageBook.getColumns().get(13).setCellValueFactory(new PropertyValueFactory<>("posId"));


    }
}
