package model;

/**
 * Classe responsável por realizar a autenticação de um usuário
 * a partir de sua senha e nome e que armazena esse usuário autenticado;
 * 
 * @author João Gabriel
 */
public class Login {
	// Atributos
	private ClassUsuario usuarioAut = null;
	
	// Instancia Singleton
	private static Login instancia;
	
	/**
	 * Método Singleton que garente que a classe tenha apenas uma instância
	 * 
	 * @return	instancia	Instancia unica e global da classe Dados
	 */
	public static synchronized Login getInstance() {
		if (instancia == null) {
			instancia = new Login();
		}
		return instancia;
	}
	
	// Métodos
	/**
	 * Recebe um usuário e senha e procura se existe um usuário na lista de usuários
	 * que possua esses dados. Caso exista, adiciona o usuário ao atributo interno da
	 * classe e retorna verdadeiro.
	 * @param usuario	String sem espaços que serve de nome de login do usuario
	 * @param password	Inteiro que serve de senha para o usuario
	 * @return boolean	Retorna verdadeiro se a autenticação foi bem sucedida 
	 */
	public boolean autentica(String usuario, String password) {
		for (ClassUsuario user : Dados.getInstance().getListausuarios()) {
			if (user.getUser().compareTo(usuario) == 0 && user.getPassword().compareTo(password) == 0) {
				this.usuarioAut = user;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Método que remove o usuario autenticado
	 * @return true	retorna true se a operação for bem sucedida
	 */
	public boolean logout() {
		usuarioAut = null;
		return true;
	}
	
	// Getter
	public ClassUsuario getUsuarioAut() {
		return usuarioAut;
	}
}

