package model;

/**
 * Classe que define os atributos e m�todos dos objetos respons�veis
 * por representar os Clientes no sistema.
 * 
 * @author Jo�o Gabriel
 * @see ClassEntidade
 */
public class ClassCliente extends ClassEntidade{
	private String nome, telefone, cpf, email;
	
	// Construtor
	/**
	 * Inicializa a instancia da classe associando os valores passado 
	 * como parametro aos atributos internos do objeto.
	 * 
	 * @param codigo	Inteiro de 4 digitos
	 * @param nome		String com o nome do cliente
	 * @param telefone	String com o telefone do cliente
	 * @param cpf		String com o CPF do cliente
	 * @param email		String com o email do cliente
	 */

	public ClassCliente(int codigo, String nome, String telefone, String cpf, String email) {
		super(codigo);
		this.setNome(nome);
		this.setTelefone(telefone);
		this.setCpf(cpf);
		this.setEmail(email);
	}

	/**
	 * Percorre a lista de Clientes e retorna o objeto que 
	 * possua o mesmo c�digo passado como par�metro.
	 * @param codigo	Numero inteiro que ser� usado como par�metro de busca
	 * @return entidade	Objeto selecionado pelo m�todo (que possui o mesmo c�digo)
	 */
	public static ClassCliente buscaPorID(int codigo){
		for (ClassCliente entidade : Dados.getInstance().getListaclientes()) {
			if (entidade.getCodigo() == codigo) {
				return entidade;
			}
		}
		return null;
	}
	
	// Getters e Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
