package controller.CRUD.cardapio;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ClassPrato;
import model.ClassProduto;
import model.Dados;

/**
* Classe controller responsável por definir o funcionamento da tableview associada ao pane interno
* da tela de cardápio
* 
* @author João Gabriel
*/
public class TabelaCardapioController implements Initializable{
	
    @FXML
    private TableColumn<ClassProduto, String> NomeColuna;

    @FXML
    private TableColumn<ClassProduto, Float> QuantidadeColuna;
    
    @FXML
    private TableView<ClassProduto> PratosTableView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		NomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
		QuantidadeColuna.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		
		ClassPrato prato = (ClassPrato) Dados.getInstance().getTemp();
		PratosTableView.setItems(prato.getReceita());
	}
}
