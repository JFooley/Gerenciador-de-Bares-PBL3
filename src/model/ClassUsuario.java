package model;

/**
 * Classe que define os atributos e métodos dos objetos responsáveis
 * por representar os Usuários no sistema.
 * 
 * @author João Gabriel
 * @see ClassEntidade
 */
public class ClassUsuario extends ClassEntidade {
	// Atributos
	private String user;
	private String password;
	private boolean adm;
	
	// Construtor
	/**
	 * Inicializa a instancia da classe associando os valores passado 
	 * como parametro aos atributos internos do objeto.
	 * 
	 * @param codigo	Inteiro de 4 digitos
	 * @param user		String com o login de usuário do usuário
	 * @param password	Inteiro com a sena numérica do usuário
	 * @param adm		Booleano com True caso o usuário seja gerente ou False caso o usuário seja funcionário
	 */
	public ClassUsuario(int codigo, String user, String password, boolean adm) {
		super(codigo);
		this.setUser(user);
		this.setPassword(password);
		this.setAdm(adm);
	}

	// Getters e Setters
	public String getUser() {
		return user;
	}
	public boolean getAdm() {
		return adm;
	}
	public String getPassword() {
		return password;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAdm(boolean adm) {
		this.adm = adm;
	}
	
}
