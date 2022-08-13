package testesJUnit;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import model.ClassFornecedores;
import model.Dados;

public class TestClassFornecedor {
	
	@Test
	public void testBuscaPorIDFornecedorExistente() {
		ClassFornecedores fornecedor = new ClassFornecedores(1000, "", "", "");
		Dados.getInstance().getListafornecedores().clear();
		
		Dados.getInstance().getListafornecedores().add(fornecedor);
		
		assertSame(fornecedor, ClassFornecedores.buscaPorID(1000));
	}
	
	@Test
	public void testBuscaPorIDFornecedorInexistente() {
		Dados.getInstance().getListafornecedores().clear();
		
		assertNull(ClassFornecedores.buscaPorID(1000));
	}
}
