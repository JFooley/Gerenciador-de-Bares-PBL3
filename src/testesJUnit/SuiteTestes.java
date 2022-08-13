package testesJUnit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	TestClassPrato.class,
	TestClassProduto.class,
	TestClassVenda.class,
	TestClassCliente.class,
	TestClassFornecedor.class,
	TestFacadeMenu.class,
	TestFacadeMenuClientes.class,
	TestFacadeMenuUsuarios.class,
	TestFacadeMenuFornecedores.class,
	TestFacadeMenuProduto.class,
	TestFacadeMenuCardapio.class,
	TestFacadeMenuVenda.class,
	TestLogin.class,
	TestRelatorio.class	
})
public class SuiteTestes{

}
