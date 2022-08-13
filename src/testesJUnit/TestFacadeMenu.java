package testesJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Dados;
import model.FacadeMenu;

public class TestFacadeMenu {
	
	@BeforeEach
	public void setUp() {
		Dados.getInstance().getListacodigos().clear();
	}
	
	@Test
	public void testGerarCodigoListaVazia() {
		int codigo = FacadeMenu.GerarCodigo(Dados.getInstance().getListacodigos());
		
		assertFalse(Dados.getInstance().getListacodigos().contains(codigo), "Código não existe na lista de códigos");
	}
	
	@Test
	public void testGerarCodigoListaCheia() {
		for (int i = 0 ; i < 10000 ; i++) {
			Dados.getInstance().getListacodigos().add(i);
		}
		
		int codigo = FacadeMenu.GerarCodigo(Dados.getInstance().getListacodigos());
		
		assertTrue(Integer.toString(codigo).length() == (int) 5, "O código gerado é de 5 caracteres");		
	}
	
	@Test
	public void testExcluirCodigoExistente() {
		Dados.getInstance().getListacodigos().add(9999);
		
		FacadeMenu.ExcluirCodigo(9999);
		
		assertFalse(Dados.getInstance().getListacodigos().contains(9999), "A lista de códigos não contem mais o código");
	}
	
	@Test
	public void testExcluirCodigoInexistente() {
		int sizePreExclusao = Dados.getInstance().getListacodigos().size();
		
		FacadeMenu.ExcluirCodigo(1111);
		
		int sizePosExclusao = Dados.getInstance().getListacodigos().size();
		
		assertEquals(sizePreExclusao, sizePosExclusao, "O tamanho da lista não alterou");
	}
	
}
