package controller.CRUD.vendas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ClassPrato;
import model.ClassVenda;
import model.Dados;

/**
* Classe controller responsável por definir o funcionamento da tableview associada ao pane interno
* da tela de vendas
* 
* @author João Gabriel
*/
public class TabelaVendasController implements Initializable{

    @FXML
    private TableColumn<ClassPrato, String> NomeColuna;

    @FXML
    private TableColumn<ClassPrato, String> CategoriaColuna;
    
    @FXML
    private TableView<ClassPrato> PratosTableView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		NomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
		CategoriaColuna.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		
		ClassVenda venda = (ClassVenda) Dados.getInstance().getTemp();
		PratosTableView.setItems(venda.getPratos());
	}
}
