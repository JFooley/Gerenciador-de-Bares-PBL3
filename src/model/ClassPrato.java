package model;

import javafx.collections.ObservableList;

/**
 * Classe que define os atributos e métodos dos objetos responsáveis
 * por representar os Pratos (Itens do cardápio) no sistema.
 * 
 * @author João Gabriel
 * @see ClassEntidade
 */
public class ClassPrato extends ClassEntidade{
	// Atributos
	private String nome, descricao, categoria;
	private float preco;
	private ObservableList<ClassProduto> receita;
	
	// Construtor
	/**
	 * Inicializa a instancia da classe associando os valores passado 
	 * como parametro aos atributos internos do objeto.
	 * 
	 * @param codigo	Inteiro de 4 digitos
	 * @param nome		String com o nome do prato
	 * @param descricao	String com a descrição do prato
	 * @param preco 	Float com o preço do prato
	 * @param categoria	String com a categoria do prato
	 */
	public ClassPrato(int codigo, String nome, String descricao, float preco, String categoria, ObservableList<ClassProduto> receita) {
		super(codigo);
		this.setNome(nome);
		this.setDescricao(descricao);
		this.setPreco(preco);
		this.setCategoria(categoria);
		this.setReceita(receita);
	}
	
	// Metodos
	/**
	 * Checa se o stock possui os ingredientes para fazer esse prato comparando
	 */
	public boolean checkStock() {
		for (ClassProduto ingrediente : this.receita) { // Itera sobre os ingredientes
			if (!Dados.getInstance().getListacodigos().contains(ingrediente.getCodigo())) {
				return false;
			}
			for (ClassProduto produtostock : Dados.getInstance().getListaprodutos()) { // Itera sobre a lista de produtos
				if (produtostock.getCodigo() == ingrediente.getCodigo()) { // Encontra o produto com o código igual ao ingrediente específico 
					if (produtostock.getQuantidade() == 0 || produtostock.getQuantidade() < ingrediente.getQuantidade()) { // Verifica se a quantidade do ingrediente é superior ao stock
						return false;
					}
					break;
				}
			}
		}
		return true;
		
		
	}
	
	// Getters e Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public ObservableList<ClassProduto> getReceita() {
		return receita;
	}
	public void setReceita(ObservableList<ClassProduto> receita) {
		this.receita = receita;
	}
}
