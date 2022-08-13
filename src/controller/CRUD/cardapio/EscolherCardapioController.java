package controller.CRUD.cardapio;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.ClassProduto;
import model.Dados;

/**
* Classe controller responsável por definir o funcionamento da janela em que o usuário
* seleciona os produtos que compõe um prato durante sua criação.
* 
* @author João Gabriel
*/
public class EscolherCardapioController implements Initializable{

	ObservableList<ClassProduto> receita = FXCollections.observableArrayList();;
	
    @FXML
    private Button AdicionarButton;
    
    @FXML
    private Text Mensagem;
    
    @FXML
    private Text ContadorText;

    @FXML
    private TableColumn<ClassProduto, Integer> ProdutosColunaCodigo;

    @FXML
    private TableColumn<ClassProduto, String> ProdutosColunaNome;

    @FXML
    private TableColumn<ClassProduto, Float> ProdutosColunaPreco;

    @FXML
    private TableColumn<ClassProduto, String> ProdutosColunaQuantidade;

    @FXML
    private TableView<ClassProduto> ProdutosTableView;

    @FXML
    private TextField QuantidadeTextField;

    @FXML
    void AdicionarButtonAction(ActionEvent event) {
    	if (ProdutosTableView.getSelectionModel().getSelectedItem() == null) {
    		Mensagem.setText("Selecione o produto!");
    		Mensagem.setVisible(true);
    	} else {
    		try {
        		float quantidade = Float.parseFloat(QuantidadeTextField.getText());
        		ClassProduto produtoProv = ProdutosTableView.getSelectionModel().getSelectedItem();
        	    ClassProduto produto = new ClassProduto(produtoProv.getCodigo(), produtoProv.getNome(), produtoProv.getPreco(), produtoProv.getValidade(), produtoProv.getFornecedor(), quantidade);
        	    receita.add(produto);
        	    Dados.getInstance().setProdutosTemp(receita);
        	   
        	    ContadorText.setText("Quantidade de produtos: ".concat(Integer.toString(receita.size())));
        	    
        		Mensagem.setText("Produto adicionado!");
        		Mensagem.setVisible(true);
    		} catch (NumberFormatException nfe) {
        		Mensagem.setText("Quantidade inválida!");
        		Mensagem.setVisible(true);
    		}
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ProdutosColunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		ProdutosColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		ProdutosColunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		ProdutosColunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		
		ProdutosTableView.setItems(Dados.getInstance().getListaprodutos());
	}
}
