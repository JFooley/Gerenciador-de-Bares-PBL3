package controller;
	
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.*;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * Principal classe do programa, onde ele é inicializado
 * 
 * @author João Gabriel
 */
public class Main extends Application {
	/**
	 * Método que inicializa a janela principal do programa
	 * 
	 * @param Stage Stage principal do programa onde a cena inicial será exibida
	 */
	@Override
	public void start(Stage Stage) {
		try {
			AnchorPane LoginPane = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
			Scene CenaLogin = new Scene(LoginPane);
			
			Stage.setScene(CenaLogin);
			Stage.setResizable(false);
			Stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Principal método do programa que é executado primeiro e cumpre a função da classe Main de ser 
	 * o ponto de partida do sistema. É nele onde as classes principais da base do funcionamento do 
	 * programa são instanciadas e os métodos chamados.
	 * 
	 * @param args	Possíveis argumentos do método Main que podem ser passado por via do terminal
	 */
	public static void main(String[] args) {
		
		// DADOS PRE-CADASTRADOS
		ObservableList<ClassProduto> receita1 = FXCollections.observableArrayList();
		ObservableList<ClassProduto> receita2 = FXCollections.observableArrayList();
		
		ClassUsuario user = new ClassUsuario(1000, "gabriel", "12345", false);
		Dados.getInstance().getListausuarios().add(user);
		
		ClassFornecedores fornecedor = new ClassFornecedores(3333, "33.343.455/23", "J&G Destribuidadora", "Av. Elvira Freitas, 824");
		Dados.getInstance().getListafornecedores().add(fornecedor);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date data = sdf.parse("01/01/2022");
			ClassProduto produtounidade = new ClassProduto(2222, "Queijo", (float) 4.50, data, fornecedor, (float) 300);
			ClassProduto produto = new ClassProduto(1111, "Queijo", (float) 4.50);
			ClassProduto produtounidade1 = new ClassProduto(2111, "Carne", (float) 12.00, data, fornecedor, (float) 500);
			ClassProduto produto1 = new ClassProduto(2111, "Carne", (float) 12.00);
			produto1.getStock().add(produtounidade1);
			produto1.updateQuantidade();
			produto.getStock().add(produtounidade);
			produto.updateQuantidade();
			Dados.getInstance().getListaprodutos().add(produto);
			Dados.getInstance().getListaprodutos().add(produto1);
			ClassPrato prato = new ClassPrato(5555, "Prato A", "Mto bom", (float) 13.50, "Tipo A", receita1);
			ClassPrato prato2 = new ClassPrato(6666, "Prato B", "Mto ruim", (float) 18.50, "Tipo B", receita2);
			prato.getReceita().add(produto);
			prato2.getReceita().add(produto1);
			Dados.getInstance().getListapratos().add(prato);
			Dados.getInstance().getListapratos().add(prato2);
			ClassCliente cliente = new ClassCliente(8888, "Cliente", "(75)98127-2922", "85870971560", "email123@gmail.com");
			ObservableList<ClassPrato> pratos = FXCollections.observableArrayList();
			pratos.add(prato);
			pratos.add(prato2);
			ClassVenda venda = new ClassVenda(7777, data, pratos, (float) 25.50, "PIX", cliente);
			venda.somarPratos();
			Dados.getInstance().getListavendas().add(venda);
			Dados.getInstance().getListaclientes().add(cliente);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Dados.getInstance().getListacodigos().add(1000);
		Dados.getInstance().getListacodigos().add(1111);
		Dados.getInstance().getListacodigos().add(2111);
		Dados.getInstance().getListacodigos().add(2222);
		Dados.getInstance().getListacodigos().add(3333);
		Dados.getInstance().getListacodigos().add(4444);
		Dados.getInstance().getListacodigos().add(5555);
		Dados.getInstance().getListacodigos().add(6666);
		Dados.getInstance().getListacodigos().add(7777);
		Dados.getInstance().getListacodigos().add(8888);
		// FIM DA SECÇÃO DE PRE-CADASTRO
		
		launch(args);
	}
}
