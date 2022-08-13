package controller.CRUD.estoque;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.ClassFornecedores;
import model.ClassProduto;
import model.Dados;

/**
* Classe controller responsável por definir o funcionamento da tela interna a tab estoque
* 
* @author João Gabriel
*/
public class MenuEstoqueController implements Initializable{

    @FXML
    private Button EstoqueAdicionarButton;

    @FXML
    private TableColumn<ClassProduto, Integer> EstoqueColunaCodigo;

    @FXML
    private TableColumn<ClassProduto, ClassFornecedores> EstoqueColunaFornecedor;

    @FXML
    private TableColumn<ClassProduto, String> EstoqueColunaNome;

    @FXML
    private TableColumn<ClassProduto, Float> EstoqueColunaQuantidade;

    @FXML
    private TableColumn<ClassProduto, Date> EstoqueColunaValidade;

    @FXML
    private Button EstoqueExcluirButton;

    @FXML
    private Pane EstoqueOptionPane;

    @FXML
    private TableView<ClassProduto> EstoqueTableView;
    
    @FXML
    private TextField IDTextField;

    @FXML
    private Button PesquisaButton;

    @FXML
    void EstoqueAdicionarButtonAction(ActionEvent event) {
		try {
			AnchorPane AdicionarUnidade = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Estoque/CriarEstoqueView.fxml"));
			EstoqueOptionPane.getChildren().clear();
			EstoqueOptionPane.getChildren().add(AdicionarUnidade);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void EstoqueExcluirButtonAction(ActionEvent event) {
    	if (EstoqueTableView.getSelectionModel().getSelectedItem() != null) {
    		int codigoUnidade = EstoqueTableView.getSelectionModel().getSelectedItem().getCodigo();
    		ClassProduto produto = ClassProduto.buscaPorID(Dados.getInstance().getEstoqueTemp().getCodigo());
    		produto.removeUnidade(codigoUnidade);
    		produto.sortStock();
    		produto.updateQuantidade();
    	}
    }
    
    @FXML
    void PesquisaButtonAction(ActionEvent event) {
		EstoqueOptionPane.getChildren().clear();
    	if (IDTextField.getText() != "") {
    		try {
    			int codigo = Integer.parseInt(IDTextField.getText());
    			ClassProduto produto = ClassProduto.buscaPorID(codigo);
    			if (produto == null) {
        			IDTextField.setPromptText("Produto inexistente!");
        			IDTextField.setText("");
        			EstoqueTableView.setItems(null);
    			} else {
    				EstoqueTableView.setItems(produto.getStock());
    				Dados.getInstance().setEstoqueTemp(produto);
    			}
    		} catch (NumberFormatException nfe) {
    			IDTextField.setPromptText("Código inválido!");
    			IDTextField.setText("");
    			EstoqueTableView.setItems(null);
    		}
    	}
    }
    
    @FXML
    void RefreshAction(MouseEvent event) {
    	EstoqueTableView.refresh();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	// Setta o texto da célula
    	EstoqueColunaValidade.setCellFactory(c -> new TableCell<>() {
    		@Override
    		protected void updateItem(Date validade, boolean empty) {
    			super.updateItem(validade, empty);
    			if (validade == null || empty) {
    				setText(null);
    			} else {
    	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    				setText(sdf.format(validade));
    			}
    		}
    	});
    	
    	EstoqueColunaFornecedor.setCellFactory(c -> new TableCell<>() { // Constroi TODAS as células da coluna
    		@Override
    		protected void updateItem(ClassFornecedores fornecedor, boolean empty) { // Recebe o atributo que essa coluna vai mostrar e um booleano padrão (tem q ter ele)
    			super.updateItem(fornecedor, empty);
    			if (fornecedor == null || empty) { // Caso em que a célula é vazia
    				setText(null);
    			} else { // Caso em que a celula não é vazia
    				setText(fornecedor.getNome());
    			}
    		}
    	});
    	
    	// Seta o conteúdo das colunas
    	EstoqueColunaValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
    	EstoqueColunaFornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
    	EstoqueColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	EstoqueColunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    	EstoqueColunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	}

}
