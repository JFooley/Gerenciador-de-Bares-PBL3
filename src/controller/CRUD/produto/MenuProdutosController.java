package controller.CRUD.produto;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.ClassProduto;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela interna a tab de produtos
* 
* @author João Gabriel
*/
public class MenuProdutosController implements Initializable{
    
    @FXML
    private Button ProdutosCadastrarButton;

    @FXML
    private TableColumn<ClassProduto, Integer> ProdutosColunaCodigo;

    @FXML
    private TableColumn<ClassProduto, String> ProdutosColunaNome;

    @FXML
    private TableColumn<ClassProduto, Float> ProdutosColunaPreco;

    @FXML
    private TableColumn<ClassProduto, Float> ProdutosColunaQuantidade;

    @FXML
    private Button ProdutosEditarButton;

    @FXML
    private Button ProdutosExcluirButton;

    @FXML
    private Pane ProdutosOptionPane;

    @FXML
    private TableView<ClassProduto> ProdutosTableView;
    
    @FXML
    void ProdutosCadastrarButtonAction(ActionEvent event) {
		try {
			AnchorPane CriarProduto = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Produtos/CriarProdutoView.fxml"));
			ProdutosOptionPane.getChildren().clear();
			ProdutosOptionPane.getChildren().add(CriarProduto);
			ProdutosTableView.refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void ProdutosEditarButtonAction(ActionEvent event) {
		try {
			Dados.getInstance().setTemp(ProdutosTableView.getSelectionModel().getSelectedItem());
			
			AnchorPane EditarProduto = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Produtos/EditarProdutoView.fxml"));
			ProdutosOptionPane.getChildren().clear();
			ProdutosOptionPane.getChildren().add(EditarProduto);
			ProdutosTableView.refresh();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void ProdutosExcluirButtonAction(ActionEvent event) {
    	if (ProdutosTableView.getSelectionModel().getSelectedItem() != null) {
    		int codigo = ProdutosTableView.getSelectionModel().getSelectedItem().getCodigo();
    		
    		FacadeMenu.excluirProduto(codigo);
    	}
    }
    
    @FXML
    void RefreshAction(MouseEvent event) {
    	ProdutosTableView.refresh();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	// Seta o conteúdo das colunas
	    ProdutosColunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	    ProdutosColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    ProdutosColunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
	    ProdutosColunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
	    
	    ProdutosTableView.setItems(Dados.getInstance().getListaprodutos());
	    ProdutosTableView.refresh();
	}

}
