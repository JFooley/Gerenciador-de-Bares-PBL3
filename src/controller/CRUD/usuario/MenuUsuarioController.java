package controller.CRUD.usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.ClassUsuario;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela interna a tab de usuarios
* 
* @author João Gabriel
*/
public class MenuUsuarioController implements Initializable{

    @FXML
    private Button UsuarioCadastrarButton;

    @FXML
    private TableColumn<ClassUsuario, Boolean> UsuarioColunaAcesso;

    @FXML
    private TableColumn<ClassUsuario, Integer> UsuarioColunaCodigo;

    @FXML
    private TableColumn<ClassUsuario, String> UsuarioColunaNome;

    @FXML
    private TableColumn<ClassUsuario, String> UsuarioColunaSenha;

    @FXML
    private Button UsuarioEditarButton;

    @FXML
    private Button UsuarioExcluirButton;

    @FXML
    private Pane UsuarioOptionPane;

    @FXML
    private TableView<ClassUsuario> UsuarioTableView;
    
    @FXML
    void ExcluirAction(ActionEvent event) {
    	if (UsuarioTableView.getSelectionModel().getSelectedItem() != null && UsuarioTableView.getSelectionModel().getSelectedItem().getCodigo() != 0) {
        	// Pega o código do usuário selecionado
        	int codigo = UsuarioTableView.getSelectionModel().getSelectedItem().getCodigo();
        	
        	// Chama o método de excluir da classe facade 
        	FacadeMenu.excluirUsuario(codigo);
    	}
    }

    @FXML
    void EditarAction(ActionEvent event) {
    	if (UsuarioTableView.getSelectionModel().getSelectedItem() != null) {
    		Dados.getInstance().setTemp(UsuarioTableView.getSelectionModel().getSelectedItem());
    	} else {
    		Dados.getInstance().setTemp(null);
    	}
    	
		try {
			AnchorPane EditarUsuario = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/CRUD/Usuario/EditarUsuarioView.fxml"));
			UsuarioOptionPane.getChildren().clear();
			UsuarioOptionPane.getChildren().add(EditarUsuario);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void CadastrarButtonEvent(ActionEvent event) {
		try {
			AnchorPane CriarUsuario = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Usuario/CriarUsuarioView.fxml"));
			UsuarioOptionPane.getChildren().clear();
			UsuarioOptionPane.getChildren().add(CriarUsuario);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    public void limpaPane() {
		UsuarioOptionPane.getChildren().clear();
    };

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	// Seta o conteúdo das colunas
	    UsuarioColunaAcesso.setCellValueFactory(new PropertyValueFactory<>("adm"));
	    UsuarioColunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	    UsuarioColunaNome.setCellValueFactory(new PropertyValueFactory<>("user"));
	    UsuarioColunaSenha.setCellValueFactory(new PropertyValueFactory<>("password"));
	    
	    UsuarioColunaAcesso.setCellFactory(c -> new TableCell<>() {
    		@Override
    		protected void updateItem(Boolean adm, boolean empty) {
    			super.updateItem(adm, empty);
    			if (adm == null || empty) {
    				setText(null);
    			} else {
    				if (adm == true) {
        				setText("Gerente");
        			} else {
        				setText("Funcionário");
        			}
    			}
    		}
	    });
	    
	    UsuarioTableView.setItems(Dados.getInstance().getListausuarios());		
	}
}
