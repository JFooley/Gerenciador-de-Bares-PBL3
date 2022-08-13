package model;

import java.util.Comparator;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe que define os atributos e métodos dos objetos responsáveis
 * por representar os Produtos no sistema.
 * 
 * @author João Gabriel
 * @see ClassEntidade
 */
public class ClassProduto extends ClassEntidade {
	// Atributos
	private String nome;
	private Date validade;
	private float preco, quantidade;
	private ObservableList<ClassProduto> stock;
	private ClassFornecedores fornecedor;
	
	// Construtor
	/**
	 * Inicializa a instancia da classe associando os valores passado 
	 * como parametro aos atributos internos do objeto. Esse construtor
	 * é utilizado para os objetos da lista de produtos.
	 * 
	 * @param codigo	Inteiro de 4 digitos
	 * @param nome 		String com o nome do produto
	 * @param preco		Float com o preço do produto
	 */
	public ClassProduto(int codigo, String nome, float preco) {
		super(codigo);
		this.setNome(nome);
		this.setPreco(preco);
		this.quantidade = 0;
		this.stock = FXCollections.observableArrayList();
		this.fornecedor = null;
		}
	
	/**
	 * Inicializa a instancia da classe associando os valores passado 
	 * como parametro aos atributos internos do objeto. Esse construtor
	 * é utilizado para objetos do Stock
	 * 
	 * @param codigo		Inteiro de 4 digitos
	 * @param nome 			String com o nome do produto
	 * @param preco			Float com o preço do produto
	 * @param validade		String com a data de validade do produto
	 * @param fornecedor	Objeto ClassFornecedores com o fornecedor do produto
	 * @param quantidade	Float com a quantidade do produto em estoque
	 */
	public ClassProduto(int codigo, String nome, float preco, Date validade, ClassFornecedores fornecedor, float quantidade) {
		super(codigo);
		this.setNome(nome);
		this.setPreco(preco);
		this.setValidade(validade);
		this.setFornecedor(fornecedor);
		this.setQuantidade(quantidade);
		}
	

	// Métodos
	/**
	 * Intera sobre o stock interno e atualiza o valor do atributo quantidade
	 *  do objeto que chamou o método.
	 */
	public void updateQuantidade() {
		quantidade = 0;
		for (ClassProduto produto : this.stock) {
			quantidade += produto.quantidade;
		}
	}
	
	/**
	 * Re-organiza a lista do stock pela data da menor para a maior.
	 */
	public void sortStock() {
		stock.sort(Comparator.comparing(ClassProduto::getValidade)); // Organiza a lista do stock com base na data mais proxima para mais antiga
	}
	
	/**
	 * Cria um objeto específico com fornecedor e validade especficiada 
	 * e o adiciona dentro do stock.
	 * 
	 * @param codigo 		Codigo (ID) inteiro do objeto
	 * @param validade		Data de validade do tipo Date
	 * @param fornecedor	Objeto ClassFornecedor do fornecedor do produto
	 * @param quantidade	Float da quantidade do produto
	 */
	public void adicionaStock(int codigo, Date validade, ClassFornecedores fornecedor, float quantidade) {
		ClassProduto produto = new ClassProduto(codigo, this.nome, this.preco, validade, fornecedor, quantidade);
		
		this.stock.add(produto); // Adiciona o produto ao stock
		this.sortStock(); // Reorganiza a lista
	}
	
	/**
	 * Remove quantos objetos sejam necessários do stock até que 
	 * a quantidade passada como parâmetro seja suprida. 
	 * @param quantidade	Float com a quantidade em gramas/mililitros
	 */
	public void removeStock(float quantidade) {		
		if (this.stock.size() == 0) {
			throw new NullPointerException();
		}
		while (true) {
			if (quantidade < this.stock.get(0).getQuantidade()) { // Passo base
				this.stock.get(0).quantidade -= quantidade;
				this.sortStock(); // Re-organiza a lista do stock
				this.updateQuantidade(); // Atualiza a quantidade de produtos
				return;
			}
			else { // Passo recursivo
				quantidade -= this.stock.get(0).getQuantidade();
				this.stock.remove(0);
				if (quantidade == 0) {
					this.sortStock(); // Re-organiza a lista do stock
					this.updateQuantidade(); // Atualiza a quantidade de produtos
					return;
				}
			}
		}
	}
	
	/**
	 * Remove a unidade do stock especificada pelo código
	 * passado como parâmetro.
	 * @param codigo	Inteiro único associado ao objeto (ID)
	 */
	public void removeUnidade(int codigo) {
		for (ClassProduto produto : this.stock) {
			if (codigo == produto.getCodigo()) {
				int indice = this.stock.indexOf(produto); 
				this.stock.remove(indice); 
				sortStock(); // Re-organiza a lista do stock
				updateQuantidade(); // Atualiza a quantidade de produtos
				return;
			}
		}
	}
	
	/**
	 * Percorre a lista de produtos e retorna o objeto que 
	 * possua o mesmo código passado como parâmetro.
	 * @param codigo	Numero inteiro que será usado como parâmetro de busca
	 * @return entidade	Objeto selecionado pelo método (que possui o mesmo código)
	 */
	public static ClassProduto buscaPorID(int codigo){
		for (ClassProduto entidade : Dados.getInstance().getListaprodutos()) {
			if (entidade.getCodigo() == codigo) {
				return entidade;
			}
		}
		return null;
	}
	
	// Getters e setters
	public String getNome() {
		return nome;
	}
	public float getPreco() {
		return preco;
	}
	public Date getValidade() {
		return validade;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}
	public ClassFornecedores getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(ClassFornecedores fornecedor) {
		this.fornecedor = fornecedor;
	}
	public ObservableList<ClassProduto> getStock() {
		return stock;
	}
	public void setStock(ObservableList<ClassProduto> stock) {
		this.stock = stock;
	}
	
	
}
