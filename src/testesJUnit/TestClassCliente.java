package testesJUnit;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import model.ClassCliente;
import model.Dados;

public class TestClassCliente {
	
	@Test
	public void testBuscaPorIDFornecedorExistente() {
		ClassCliente cliente = new ClassCliente(1000, "", "", "", "");
		Dados.getInstance().getListafornecedores().clear();
		
		Dados.getInstance().getListaclientes().add(cliente);
		
		assertSame(cliente, ClassCliente.buscaPorID(1000));
	}
	
	@Test
	public void testBuscaPorIDFornecedorInexistente() {
		Dados.getInstance().getListafornecedores().clear();
		
		assertNull(ClassCliente.buscaPorID(1000));
	}
}
