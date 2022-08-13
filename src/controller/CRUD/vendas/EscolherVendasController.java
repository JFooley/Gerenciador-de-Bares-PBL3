package controller.CRUD.vendas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.ClassPrato;
import model.Dados;

/**
* Classe controller responsável por definir o funcionamento da janela em que o usuário
* seleciona os pratos que compõe uma venda durante sua criação.
* 
* @author João Gabriel
*/
public class EscolherVendasController implements Initializable{

	ObservableList<ClassPrato> pratos = FXCollections.observableArrayList();;

    @FXML
    private Button AdicionarButton;

    @FXML
    private TableColumn<ClassPrato, String> CardapioColunaCategoria;

    @FXML
    private TableColumn<ClassPrato, Integer> CardapioColunaCodigo;

    @FXML
    private TableColumn<ClassPrato, String> CardapioColunaNome;

    @FXML
    private TableColumn<ClassPrato, Float> CardapioColunaPreco;
    
    @FXML
    private TableView<ClassPrato> CardapioTableView;

    @FXML
    private Text ContadorText;

    @FXML
    private Text Mensagem;

    @FXML
    void AdicionarButtonAction(ActionEvent event) {
    	if (CardapioTableView.getSelectionModel().getSelectedItem() == null) {
    		Mensagem.setText("Selecione o produto!");
    		Mensagem.setVisible(true);
    	} else {
        	ClassPrato prato = CardapioTableView.getSelectionModel().getSelectedItem();
        	ClassPrato pratoProv = new ClassPrato(prato.getCodigo(), prato.getNome(), prato.getDescricao(), prato.getPreco(), prato.getCategoria(), prato.getReceita());
    		pratos.add(pratoProv);
        	Dados.getInstance().setReceitaTemp(pratos);
        	   
        	ContadorText.setText("Quantidade de pratos: ".concat(Integer.toString(pratos.size())));
        	    
        	Mensagem.setText("Prato adicionado!");
        	Mensagem.setVisible(true);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CardapioColunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		CardapioColunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		CardapioColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		CardapioColunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		CardapioTableView.setItems(Dados.getInstance().getListapratos());
	}
}
