package testesJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ClassCliente;
import model.ClassPrato;
import model.ClassProduto;
import model.ClassVenda;


public class TestClassVenda {
	ObservableList<ClassProduto> ingredientes = FXCollections.observableArrayList();
	ClassCliente cliente = new ClassCliente(1, "", "", "", "");

	@Test
	public void TestaSomaPrecosComTrêsPratos() {
		ObservableList<ClassPrato> pratos = FXCollections.observableArrayList();
		
		ClassPrato prato1 = new ClassPrato(1111, "Macarrão", "-", (float) 2.00 , "-", ingredientes);
		ClassPrato prato2 = new ClassPrato(2222, "Feijão", "-",(float) 3.00, "-", ingredientes);
		ClassPrato prato3 = new ClassPrato(3333, "Arroz", "-",(float) 1.00 , "-", ingredientes);
		
		pratos.add(prato1);
		pratos.add(prato2);
		pratos.add(prato3);
		
		Date dataHora = new Date();
		
		ClassVenda venda = new ClassVenda(2, dataHora, pratos, 0, "PIX", cliente);
		venda.somarPratos();
		
		assertEquals((float) 6, venda.getPrecoTotal());
		
	}
	
	@Test
	public void TestaSomaComNenhumPrato() {
		ObservableList<ClassPrato> pratos = FXCollections.observableArrayList();
		
		Date dataHora = new Date();
		
		ClassVenda venda = new ClassVenda(2, dataHora, pratos, 0, "PIX", cliente);
		venda.somarPratos();
		
		assertEquals((float) 0, venda.getPrecoTotal());
	}
	
	
	@Test
	public void TestaSomaComPratoDeValorNegativo() {
		ObservableList<ClassPrato> pratos = FXCollections.observableArrayList();
		
		ClassPrato prato1 = new ClassPrato(1111, "Macarrão", "-", (float) -2.00 , "-", ingredientes);
		ClassPrato prato2 = new ClassPrato(2222, "Feijão", "-",(float) -3.00, "-", ingredientes);
		ClassPrato prato3 = new ClassPrato(3333, "Arroz", "-",(float) -1.00 , "-", ingredientes);
		
		pratos.add(prato1);
		pratos.add(prato2);
		pratos.add(prato3);
		
		Date dataHora = new Date();
		
		ClassVenda venda = new ClassVenda(2, dataHora, pratos, 0, "PIX", cliente);
		venda.somarPratos();
		
		assertEquals((float) -6, venda.getPrecoTotal());
	} 

}
