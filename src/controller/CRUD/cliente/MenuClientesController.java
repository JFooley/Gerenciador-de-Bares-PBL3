package controller.CRUD.cliente;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.ClassCliente;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela da tab cliente
* 
* @author João Gabriel
*/
public class MenuClientesController implements Initializable {

    @FXML
    private TableColumn<ClassCliente, String> ClienteColunaCPF;

    @FXML
    private TableColumn<ClassCliente, String> ClienteColunaCodigo;

    @FXML
    private TableColumn<ClassCliente, String> ClienteColunaEmail;

    @FXML
    private TableColumn<ClassCliente, String> ClienteColunaNome;

    @FXML
    private TableColumn<ClassCliente, String> ClienteColunaTelefone;

    @FXML
    private Button ClientesCadastrarButton;

    @FXML
    private Button ClientesEditarButton;

    @FXML
    private Button ClientesExcluirButton;

    @FXML
    private Pane ClientesOptionPane;

    @FXML
    private TableView<ClassCliente> ClientesTableView;

    @FXML
    void ClientesCadastrarButtonAction(ActionEvent event) {
		try {
			AnchorPane CriarCliente = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Clientes/CriarClienteView.fxml"));
			ClientesOptionPane.getChildren().clear();
			ClientesOptionPane.getChildren().add(CriarCliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void ClientesEditarButtonAction(ActionEvent event) {
		try {
			Dados.getInstance().setTemp(ClientesTableView.getSelectionModel().getSelectedItem());
			
			AnchorPane EditarCliente = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Clientes/EditarClienteView.fxml"));
			ClientesOptionPane.getChildren().clear();
			ClientesOptionPane.getChildren().add(EditarCliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void ClientesExcluirButtonAction(ActionEvent event) {
    	if (ClientesTableView.getSelectionModel().getSelectedItem() != null) {
    		int codigo = ClientesTableView.getSelectionModel().getSelectedItem().getCodigo();
    		
    		FacadeMenu.excluirCliente(codigo);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ClienteColunaCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		ClienteColunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		ClienteColunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		ClienteColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		ClienteColunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		
		ClientesTableView.setItems(Dados.getInstance().getListaclientes());
	}

}
