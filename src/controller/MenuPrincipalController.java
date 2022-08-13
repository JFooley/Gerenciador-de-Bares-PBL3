package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.Login;

/**
 * Classe controller responsável por definir o funcionamento da tela de menu principal do sistema
 * 
 * @author João Gabriel
 */
public class MenuPrincipalController implements Initializable{

    @FXML
    private AnchorPane CardapioAnchorPane;

    @FXML
    private AnchorPane ClientesAnchorPane;

    @FXML
    private AnchorPane EstoqueAnchorPane;

    @FXML
    private AnchorPane FornecedoresAnchorPane;

    @FXML
    private AnchorPane ProdutosAnchorPane;

    @FXML
    private AnchorPane RelatorioPane;

    @FXML
    private AnchorPane UsuarioAnchorPane;

    @FXML
    private AnchorPane VendasAnchorPane;

    @FXML
    private Button LogoutButton;
        
    @FXML
    void LogoutButtonAction(ActionEvent event) {
		try {
			Login.getInstance().logout();
			AnchorPane LoginPane = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
			Node node = (Node) event.getSource();
			node.getScene().setRoot(LoginPane);

		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		AnchorPane RelatorioView;
		AnchorPane MenuVendasView;
		AnchorPane MenuClientesView;
		try {
			RelatorioView = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/RelatorioView.fxml"));
			RelatorioPane.getChildren().clear();
			RelatorioPane.getChildren().add(RelatorioView);
						
			MenuVendasView = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Vendas/MenuVendasView.fxml"));
			VendasAnchorPane.getChildren().clear();
			VendasAnchorPane.getChildren().add(MenuVendasView);
			
			MenuClientesView = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Clientes/MenuClientesView.fxml"));
			ClientesAnchorPane.getChildren().clear();
			ClientesAnchorPane.getChildren().add(MenuClientesView);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (Login.getInstance().getUsuarioAut().getAdm() == true) {// Usuário é um gerente
			AnchorPane MenuFornecedoresView;
			AnchorPane MenuUsuarioView;
			AnchorPane MenuProdutosView;
			AnchorPane MenuEstoqueView;
			AnchorPane MenuCardapioView;
			try {
				MenuUsuarioView = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Usuario/MenuUsuarioView.fxml"));
				UsuarioAnchorPane.getChildren().clear();
				UsuarioAnchorPane.getChildren().add(MenuUsuarioView);
				
				MenuFornecedoresView = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Fornecedores/MenuFornecedoresView.fxml"));
				FornecedoresAnchorPane.getChildren().clear();
				FornecedoresAnchorPane.getChildren().add(MenuFornecedoresView);
				
				MenuProdutosView = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Produtos/MenuProdutosView.fxml"));
				ProdutosAnchorPane.getChildren().clear();
				ProdutosAnchorPane.getChildren().add(MenuProdutosView);
				
				MenuEstoqueView = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Estoque/MenuEstoqueView.fxml"));
				EstoqueAnchorPane.getChildren().clear();
				EstoqueAnchorPane.getChildren().add(MenuEstoqueView);
				
				MenuCardapioView = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Cardapio/MenuCardapioView.fxml"));
				CardapioAnchorPane.getChildren().clear();
				CardapioAnchorPane.getChildren().add(MenuCardapioView);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { // Usuário é um funcionário
			try {
				// Por algum motivo não da pra usar a mesma tela em todos 
				
				AnchorPane TelaErroDeAcesso = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaErroDeAcesso.fxml"));
				UsuarioAnchorPane.getChildren().add(TelaErroDeAcesso);
				
				AnchorPane TelaErroDeAcesso1 = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaErroDeAcesso.fxml"));
				FornecedoresAnchorPane.getChildren().add(TelaErroDeAcesso1);
				
				AnchorPane TelaErroDeAcesso2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaErroDeAcesso.fxml"));
				ProdutosAnchorPane.getChildren().add(TelaErroDeAcesso2);
				
				AnchorPane TelaErroDeAcesso3 = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaErroDeAcesso.fxml"));
				EstoqueAnchorPane.getChildren().add(TelaErroDeAcesso3);
				
				AnchorPane TelaErroDeAcesso4 = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaErroDeAcesso.fxml"));
				CardapioAnchorPane.getChildren().add(TelaErroDeAcesso4);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
