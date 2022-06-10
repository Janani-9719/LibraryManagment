package lk.ijse.LibraryManagement.ViewConroller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class ViewBookController implements Initializable {


    @FXML
    private Label btnAddBook;

    @FXML
    private Label btnViewBook;

    @FXML
    private Label btnModifyBook;

    @FXML
    private TableView<BookDTO> viewBookTable;

    @FXML
    private TextField txtSearchBook;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private JFXButton btnView;


    private BookService bookService;
    private ObservableList<BookDTO>bookDTOObservableList=FXCollections.observableArrayList();

    @FXML
    void OpenModifyBook(MouseEvent event) {

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

    @FXML
    void openAddBook(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/LibraryManagement/View/AddBook.fxml"));
            Scene temp = new Scene(root);
            Stage stage =(Stage) this.btnAddBook.getScene().getWindow();
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
            Stage stage =(Stage) this.HomeButton.getScene().getWindow();
            stage.setScene(temp);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void searchBookactionPerformed(ActionEvent event) {

    }

    @FXML
    void viewBooks(MouseEvent event) {
        try {
            loadBook();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadBook()throws Exception {

        viewBookTable.getColumns().removeAll();
        ArrayList<BookDTO> allBooks=(ArrayList<BookDTO>)bookService.getAll();
        viewBookTable.setItems(FXCollections.observableArrayList(allBooks));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            bookService =ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.BOOK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<BookDTO> observableList=FXCollections.observableArrayList();

        viewBookTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bid"));
        viewBookTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        viewBookTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookName"));
        viewBookTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("category"));
        viewBookTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("machiningNo"));
        viewBookTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("other"));
        viewBookTable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Pages"));
        viewBookTable.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("price"));
        viewBookTable.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        viewBookTable.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("Publisher"));
        viewBookTable.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("receiveDate"));
        viewBookTable.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("removedDate"));
        viewBookTable.getColumns().get(12).setCellValueFactory(new PropertyValueFactory<>("Suplier"));
        viewBookTable.getColumns().get(13).setCellValueFactory(new PropertyValueFactory<>("posId"));



    }
}
