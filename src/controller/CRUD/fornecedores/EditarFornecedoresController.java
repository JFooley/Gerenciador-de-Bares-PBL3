package controller.CRUD.fornecedores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.ClassFornecedores;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela de editar fornecedores
* interna na tela de fornecedores
* 
* @author João Gabriel
*/
public class EditarFornecedoresController implements Initializable{
	
	private ClassFornecedores fornecedor = null;

    @FXML
    private TextField CNPJTextField;

    @FXML
    private Button ConfirmarButton;

    @FXML
    private Text Mensagem;

    @FXML
    private TextField EnderecoTextField;

    @FXML
    private TextField NomeTextField;

    @FXML
    void ConfirmarEditar(ActionEvent event) {
    	if (fornecedor != null ) {
    		if (CNPJTextField.getText() == "" || NomeTextField.getText() == "" || EnderecoTextField.getText() == "") {
        		Mensagem.setText("Preencha os campos!");
        		Mensagem.setVisible(true);
    		} else {
                FacadeMenu.editarFornecedor(fornecedor.getCodigo(), CNPJTextField.getText(), NomeTextField.getText(), EnderecoTextField.getText());
                Dados.getInstance().setTemp(null);
            
                NomeTextField.setText("");
                CNPJTextField.setText("");
                EnderecoTextField.setText("");
                fornecedor = null;
    		}
    	} else {
    		Mensagem.setText("Selecione o fornecedor!");
    		Mensagem.setVisible(true);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Dados.getInstance().getTemp() != null) {
			fornecedor = (ClassFornecedores) Dados.getInstance().getTemp();
			
			NomeTextField.setText(fornecedor.getNome());
			CNPJTextField.setText(fornecedor.getCnpj());
			EnderecoTextField.setText(fornecedor.getEndereco());
		} 
	}

}
