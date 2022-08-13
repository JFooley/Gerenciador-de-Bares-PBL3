package controller.CRUD.fornecedores;

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
import model.ClassFornecedores;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela interna da tab fornecedores
* 
* @author João Gabriel
*/
public class MenuFornecedoresController implements Initializable{

    @FXML
    private TableColumn<ClassFornecedores, String> FornecedorColunaCNPJ;

    @FXML
    private TableColumn<ClassFornecedores, Integer> FornecedorColunaCodigo;

    @FXML
    private TableColumn<ClassFornecedores, String> FornecedorColunaEndereco;

    @FXML
    private TableColumn<ClassFornecedores, String> FornecedorColunaNome;

    @FXML
    private Button FornecedoresCadastrarButton;

    @FXML
    private Button FornecedoresEditarButton;

    @FXML
    private Button FornecedoresExcluirButton;

    @FXML
    private Pane FornecedoresOptionPane;

    @FXML
    private TableView<ClassFornecedores> FornecedoresTableView;
    
    @FXML
    void FornecedoresCadastrarButtonAction(ActionEvent event) {
		try {
			AnchorPane CriarFornecedores = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Fornecedores/CriarFornecedoresView.fxml"));
			FornecedoresOptionPane.getChildren().clear();
			FornecedoresOptionPane.getChildren().add(CriarFornecedores);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void FornecedoresEditarButtonAction(ActionEvent event) {
		try {
			Dados.getInstance().setTemp(FornecedoresTableView.getSelectionModel().getSelectedItem());
			
			AnchorPane EditarFornecedores = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Fornecedores/EditarFornecedoresView.fxml"));
			FornecedoresOptionPane.getChildren().clear();
			FornecedoresOptionPane.getChildren().add(EditarFornecedores);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void FornecedoresExcluirButtonAction(ActionEvent event) {
    	if (FornecedoresTableView.getSelectionModel().getSelectedItem() != null) {
    		int codigo = FornecedoresTableView.getSelectionModel().getSelectedItem().getCodigo();
    		
    		FacadeMenu.excluirFornecedor(codigo);
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	// Seta o conteúdo das colunas
	    FornecedorColunaCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
	    FornecedorColunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	    FornecedorColunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
	    FornecedorColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    
    	FornecedoresTableView.setItems(Dados.getInstance().getListafornecedores());
	}

}
