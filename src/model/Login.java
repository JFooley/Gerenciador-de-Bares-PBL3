package model;

/**
 * Classe respons�vel por realizar a autentica��o de um usu�rio
 * a partir de sua senha e nome e que armazena esse usu�rio autenticado;
 * 
 * @author Jo�o Gabriel
 */
public class Login {
	// Atributos
	private ClassUsuario usuarioAut = null;
	
	// Instancia Singleton
	private static Login instancia;
	
	/**
	 * M�todo Singleton que garente que a classe tenha apenas uma inst�ncia
	 * 
	 * @return	instancia	Instancia unica e global da classe Dados
	 */
	public static synchronized Login getInstance() {
		if (instancia == null) {
			instancia = new Login();
		}
		return instancia;
	}
	
	// M�todos
	/**
	 * Recebe um usu�rio e senha e procura se existe um usu�rio na lista de usu�rios
	 * que possua esses dados. Caso exista, adiciona o usu�rio ao atributo interno da
	 * classe e retorna verdadeiro.
	 * @param usuario	String sem espa�os que serve de nome de login do usuario
	 * @param password	Inteiro que serve de senha para o usuario
	 * @return boolean	Retorna verdadeiro se a autentica��o foi bem sucedida 
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
	 * M�todo que remove o usuario autenticado
	 * @return true	retorna true se a opera��o for bem sucedida
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

