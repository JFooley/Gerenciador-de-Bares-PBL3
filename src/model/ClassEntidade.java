package model;

/**
 * Superclasse abstrata que contem apenas o atributo do código (ID)
 * e é derivada para gerar todas as outras classes modelos com 
 * exceção da classe Dados.
 * 
 * @author João Gabriel
 */
public abstract class ClassEntidade {
	private int codigo;
	
	// Construtor
	/**
	 * Inicializa a instancia da classe associando os valores passados 
	 * como parametro aos atributos internos do objeto.
	 * 
	 * @param codigo	Inteiro de 4 digitos
	 */
	public ClassEntidade(int codigo) {
		this.codigo = codigo;
	}

	// Getter
	public int getCodigo() {
		return codigo;
	}
	
}
