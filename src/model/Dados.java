package model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * Classe que contem todas as listas responsáveis por guardar os
 * objetos que são gerados pelas classes do pacote modelos e a 
 * lista de códigos de todas as entidades do sistema.
 * 
 * @author João Gabriel
 */
public class Dados {
	// Listas
	private List<Integer> listacodigos = new ArrayList<Integer>();
	private ObservableList<ClassUsuario> listausuarios = FXCollections.observableArrayList();
	private ObservableList<ClassFornecedores> listafornecedores = FXCollections.observableArrayList();
	private ObservableList<ClassProduto> listaprodutos = FXCollections.observableArrayList();
	private ObservableList<ClassPrato> listapratos = FXCollections.observableArrayList();
	private ObservableList<ClassVenda> listavendas = FXCollections.observableArrayList();
	private ObservableList<ClassCliente> listaclientes = FXCollections.observableArrayList();
	
	// Dados temporários
	private Object temp;
	private ClassProduto estoqueTemp;
	private Stage stageTemp;
	private ObservableList<ClassProduto> produtosTemp;
	private ObservableList<ClassPrato> receitaTemp;

	// Instancia Singleton
	private static Dados instancia;
	
	// Construtor
	/**
	 * Inicializa a instancia da classe e cria um usuário padrão na lista de usuarios.
	 */
	private Dados() {
		ClassUsuario defaultUser = new ClassUsuario(0000, "admin", "123", true);
		this.listausuarios.add(defaultUser);
	}
	
	/**
	 * Método Singleton que garente que a classe tenha apenas uma instância
	 * 
	 * @return	instancia	Instancia unica e global da classe Dados
	 */
	public static synchronized Dados getInstance() {
		if (instancia == null) {
			instancia = new Dados();
		}
		return instancia;
	}
	
	// Getters e Setters
	public List<Integer> getListacodigos() {
		return listacodigos;
	}
	public void setListacodigos(List<Integer> listacodigos) {
		this.listacodigos = listacodigos;
	}
	public ObservableList<ClassUsuario> getListausuarios() {
		return listausuarios;
	}
	public void setListausuarios(ObservableList<ClassUsuario> listausuarios) {
		this.listausuarios = listausuarios;
	}
	public ObservableList<ClassFornecedores> getListafornecedores() {
		return listafornecedores;
	}
	public void setListafornecedores(ObservableList<ClassFornecedores> listafornecedores) {
		this.listafornecedores = listafornecedores;
	}
	public ObservableList<ClassProduto> getListaprodutos() {
		return listaprodutos;
	}
	public void setListaprodutos(ObservableList<ClassProduto> listaprodutos) {
		this.listaprodutos = listaprodutos;
	}
	public ObservableList<ClassPrato> getListapratos() {
		return listapratos;
	}
	public void setListapratos(ObservableList<ClassPrato> listapratos) {
		this.listapratos = listapratos;
	}
	public ObservableList<ClassVenda> getListavendas() {
		return listavendas;
	}
	public void setListavendas(ObservableList<ClassVenda> listavendas) {
		this.listavendas = listavendas;
	}
	public Object getTemp() {
		return temp;
	}
	public void setTemp(Object temp) {
		this.temp = temp;
	}
	public ObservableList<ClassCliente> getListaclientes() {
		return listaclientes;
	}
	public void setListaclientes(ObservableList<ClassCliente> listaclientes) {
		this.listaclientes = listaclientes;
	}
	public ClassProduto getEstoqueTemp() {
		return estoqueTemp;
	}
	public void setEstoqueTemp(ClassProduto estoqueTemp) {
		this.estoqueTemp = estoqueTemp;
	}
	public Stage getStageTemp() {
		return stageTemp;
	}
	public void setStageTemp(Stage stageTemp) {
		this.stageTemp = stageTemp;
	}

	public ObservableList<ClassProduto> getProdutosTemp() {
		return produtosTemp;
	}
	public void setProdutosTemp(ObservableList<ClassProduto> produtosTemp) {
		this.produtosTemp = produtosTemp;
	}
	public ObservableList<ClassPrato> getReceitaTemp() {
		return receitaTemp;
	}
	public void setReceitaTemp(ObservableList<ClassPrato> receitaTemp) {
		this.receitaTemp = receitaTemp;
	}
}
