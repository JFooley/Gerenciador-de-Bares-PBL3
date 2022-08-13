package controller.CRUD.cardapio;

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
import model.ClassPrato;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela interna da tab de cardápio
* 
* @author João Gabriel
*/
public class MenuCardapioController implements Initializable{

    @FXML
    private Button CardapioCadastrarButton;

    @FXML
    private TableColumn<ClassPrato, String> CardapioColunaCategoria;

    @FXML
    private TableColumn<ClassPrato, Integer> CardapioColunaCodigo;

    @FXML
    private TableColumn<ClassPrato, String> CardapioColunaNome;

    @FXML
    private TableColumn<ClassPrato, Float> CardapioColunaPreco;

    @FXML
    private Button CardapioEditarButton;

    @FXML
    private Button CardapioExcluirButton;

    @FXML
    private Pane CardapioOptionPane;

    @FXML
    private TableView<ClassPrato> CardapioTableView;
    
    @FXML
    private Button PratosButton;

    @FXML
    void CardapioCadastrarButtonAction(ActionEvent event) {
		try {
			AnchorPane CriarPrato = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Cardapio/CriarCardapioView.fxml"));
			CardapioOptionPane.getChildren().clear();
			CardapioOptionPane.getChildren().add(CriarPrato);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void CardapioEditarButtonAction(ActionEvent event) {
       	if (CardapioTableView.getSelectionModel().getSelectedItem() != null) {
    		try {
    			Dados.getInstance().setTemp(CardapioTableView.getSelectionModel().getSelectedItem());
    			AnchorPane EditarPrato = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Cardapio/EditarCardapioView.fxml"));
    			CardapioOptionPane.getChildren().clear();
    			CardapioOptionPane.getChildren().add(EditarPrato);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}

    }

    @FXML
    void CardapioExcluirButtonAction(ActionEvent event) {
       	if (CardapioTableView.getSelectionModel().getSelectedItem() != null) {
    		int codigo = CardapioTableView.getSelectionModel().getSelectedItem().getCodigo();
    		
    		FacadeMenu.excluirPrato(codigo);
    		
    		CardapioOptionPane.getChildren().clear();
    	}
    }
    
    @FXML
    void PratosButtonAction(ActionEvent event) {
    	if (CardapioTableView.getSelectionModel().getSelectedItem() != null) {
    		try {
    			Dados.getInstance().setTemp(CardapioTableView.getSelectionModel().getSelectedItem());
    			
    			AnchorPane TabelaCardapio = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Cardapio/TabelaCardapioView.fxml"));
    			CardapioOptionPane.getChildren().clear();
    			CardapioOptionPane.getChildren().add(TabelaCardapio);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}	
    	} 
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	// Setta o conteúdo das colunas
    	CardapioColunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    	CardapioColunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	CardapioColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	CardapioColunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
    	
    	CardapioTableView.setItems(Dados.getInstance().getListapratos());
	}
}
