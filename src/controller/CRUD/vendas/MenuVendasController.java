package controller.CRUD.vendas;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.ClassCliente;
import model.ClassVenda;
import model.Dados;
import model.FacadeMenu;

/**
* Classe controller responsável por definir o funcionamento da tela interna da tab de vendas
* 
* @author João Gabriel
*/
public class MenuVendasController implements Initializable{
	
    @FXML
    private TableColumn<ClassVenda, Integer> VendasColunaCodigo;

    @FXML
    private TableColumn<ClassVenda, Date> VendasColunaData;

    @FXML
    private TableColumn<ClassVenda, Float> VendasColunaValor;
    
    @FXML
    private TableColumn<ClassVenda, String> VendasColunaPagamento;
    
    @FXML
    private TableColumn<ClassVenda, ClassCliente> VendasColunaCliente;

    @FXML
    private Button VendasEditarButton;

    @FXML
    private Button VendasExcluirButton;

    @FXML
    private Button VendasRegistrarButton;
    
    @FXML
    private Button VendasPratosButton;
    
    @FXML
    private Pane VendasOptionPane;

    @FXML
    private TableView<ClassVenda> VendasTableView;
    
    @FXML
    void VendasEditarButtonAction(ActionEvent event) {
       	if (VendasTableView.getSelectionModel().getSelectedItem() != null) {
    		try {
    			Dados.getInstance().setTemp(VendasTableView.getSelectionModel().getSelectedItem());
    			AnchorPane EditarVenda = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Vendas/EditarVendasView.fxml"));
    			VendasOptionPane.getChildren().clear();
    			VendasOptionPane.getChildren().add(EditarVenda);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }

    @FXML
    void VendasExcluirButtonAction(ActionEvent event) {
    	if (VendasTableView.getSelectionModel().getSelectedItem() != null ) {
        	int codigo = VendasTableView.getSelectionModel().getSelectedItem().getCodigo();

        	FacadeMenu.excluirVenda(codigo);
    	}
    }

    @FXML
    void VendasRegistrarButtonAction(ActionEvent event) {
		try {
			AnchorPane RegistrarVenda = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Vendas/CriarVendasView.fxml"));
			VendasOptionPane.getChildren().clear();
			VendasOptionPane.getChildren().add(RegistrarVenda);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

    @FXML
    void VendasPratosButtonAction(ActionEvent event) {
    	if (VendasTableView.getSelectionModel().getSelectedItem() != null) {
    		try {
    			Dados.getInstance().setTemp(VendasTableView.getSelectionModel().getSelectedItem());
    			
    			AnchorPane TabelaVendas = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/CRUD/Vendas/TabelaVendasView.fxml"));
    			VendasOptionPane.getChildren().clear();
    			VendasOptionPane.getChildren().add(TabelaVendas);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}	
    	} 
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	// Setta o conteúdo das colunas
    	VendasColunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	VendasColunaData.setCellValueFactory(new PropertyValueFactory<>("dataHora"));
    	VendasColunaValor.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
    	VendasColunaPagamento.setCellValueFactory(new PropertyValueFactory<>("pagamento"));
    	VendasColunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
    	
    	// Setta o texto da célula
    	VendasColunaData.setCellFactory(c -> new TableCell<>() {
    		@Override
    		protected void updateItem(Date dataHora, boolean empty) {
    			super.updateItem(dataHora, empty);
    			if (dataHora == null || empty) {
    				setText(null);
    			} else {
    	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    				setText(sdf.format(dataHora));
    			}
    		}
    	});
    	
    	VendasColunaCliente.setCellFactory(c -> new TableCell<>() {
     		@Override
    		protected void updateItem(ClassCliente cliente, boolean empty) {
    			super.updateItem(cliente, empty);
    			if (cliente == null || empty) {
    				setText(null);
    			} else {
    	    		setText(cliente.getNome());
    			}
    		}
    	});
    	    	
    	VendasTableView.setItems(Dados.getInstance().getListavendas());
	}

    
}
