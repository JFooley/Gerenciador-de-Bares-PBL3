package model;

/**
 * Classe que define os atributos e métodos dos objetos responsáveis
 * por representar os Fornecedores no sistema.
 * 
 * @author João Gabriel
 * @see ClassEntidade
 */
public class ClassFornecedores extends ClassEntidade {
	// Atributos
	private String cnpj, nome, endereco;
	
	// Construtor
	/**
	 * Inicializa a instancia da classe associando os valores passados 
	 * como parametro aos atributos internos do objeto.
	 * 
	 * @param codigo	Inteiro de 4 digitos
	 * @param cnpj		String do CNPJ do fornecedor
	 * @param nome		String do nome do fornecedor
	 * @param endereco	String do endereço do fornecedor
	 */
	public ClassFornecedores(int codigo, String cnpj, String nome, String endereco) {
		super(codigo);
		this.cnpj = cnpj;
		this.nome = nome;
		this.endereco = endereco;
	}
	
	/**
	 * Percorre a lista de fornecedores e retorna o objeto que 
	 * possua o mesmo código passado como parâmetro.
	 * @param codigo	Numero inteiro que será usado como parâmetro de busca
	 * @return entidade	Objeto selecionado pelo método (que possui o mesmo código)
	 */
	public static ClassFornecedores buscaPorID(int codigo){
		for (ClassFornecedores entidade : Dados.getInstance().getListafornecedores()) {
			if (entidade.getCodigo() == codigo) {
				return entidade;
			}
		}
		return null;
	}

	// Getters e Setters
	public String getCnpj() {
		return cnpj;
	}
	public String getNome() {
		return nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
