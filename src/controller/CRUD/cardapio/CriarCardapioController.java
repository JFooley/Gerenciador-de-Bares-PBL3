package controller.CRUD.cardapio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.ClassProduto;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller respons�vel por definir o funcionamento da tela de cria��o de pratos
* interna na tela de card�pio.
* 
* @author Jo�o Gabriel
*/
public class CriarCardapioController implements Initializable{

    @FXML
    private TextField CategoriaTextField;

    @FXML
    private Button ContinuarButton;

    @FXML
    private TextArea DescricaoTextArea;

    @FXML
    private Text Mensagem;

    @FXML
    private TextField NomeTextField;

    @FXML
    private TextField PrecoTextField;

    @FXML
    void ContinuarButtonAction(ActionEvent event) {
    	if (NomeTextField.getText() == "" || CategoriaTextField.getText() == "" || DescricaoTextArea.getText() == "" || PrecoTextField.getText() == "") {
    		Mensagem.setText("Preencha os campos!");
    		Mensagem.setVisible(true);
    	} else {
    		try {
        		Stage stage = new Stage();
        		AnchorPane escolherPrato = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Cardapio/EscolherCardapioView.fxml"));
        		Scene cena = new Scene(escolherPrato);
        		stage.setScene(cena);
        		stage.show();      	
        		
           		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent t) {
                    	finalizarPrato();
                    }
                });
           		
           		Dados.getInstance().setStageTemp(stage);
    		} catch (IOException ioe) {
    			ioe.printStackTrace();
    		}
    	}
    }
    
    public void finalizarPrato() {
    	ObservableList<ClassProduto> receita = Dados.getInstance().getProdutosTemp();
    	
    	if (receita != null) {
    		try {
            	String nome = NomeTextField.getText();
            	String categoria = CategoriaTextField.getText();
            	String descricao = DescricaoTextArea.getText();
            	float preco = Float.parseFloat(PrecoTextField.getText());

            	FacadeMenu.criarPrato(nome, descricao, preco, categoria, receita);
            	
            	NomeTextField.setText("");
            	CategoriaTextField.setText("");
            	DescricaoTextArea.setText("");
            	PrecoTextField.setText("");
    		} catch (NumberFormatException nfe) {
        		Mensagem.setText("Digite um pre�o v�lido!");
        		Mensagem.setVisible(true);
    		}	
    	} 
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FacadeMenu.validarTFNumerico(PrecoTextField);
	}

}
