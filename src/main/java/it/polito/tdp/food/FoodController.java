/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.Simulatore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;
	private Simulatore sim;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPorzioni"
    private TextField txtPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalorie"
    private Button btnCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxFood"
    private ComboBox<Food> boxFood; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.boxFood.getItems().clear();
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...\n\n");
    	
    	Integer numPorzioni = 0;
    	try {
    		numPorzioni = Integer.parseInt(this.txtPorzioni.getText());
    	}
    	catch(NumberFormatException e) {
    		txtResult.appendText("Inserisci un numero INTERO!!");
    		return;
    	}
    	
    	String result = this.model.creaGrafo(numPorzioni);
    	this.txtResult.appendText(result);
    	
    	this.boxFood.getItems().addAll(this.model.getAllVertici());
    	this.btnCalorie.setDisable(false);
    	this.btnSimula.setDisable(false);
    }
    
    @FXML
    void doCalorie(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Analisi calorie...\n\n");
    	
    	Food scelto = this.boxFood.getValue();
    	if(scelto == null) {
    		txtResult.appendText("Scegli prima un CIBO!!");
    		return;
    	}
    	
    	Map<Food, Double> result = this.model.getCalCongiunteMax(scelto);
    	
    	this.txtResult.appendText("Elenco dei 5 cibi con calorie congiunte massime:\n");
    	Integer i = 0;
    	for(Food f : result.keySet()) {
    		
    		if(i<5) {
    			this.txtResult.appendText("- "+f.toString()+", "+result.get(f)+" cal\n");
    			i++;
    		}
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Simulazione...\n");
    	
    	this.sim = new Simulatore();
    	
    	Integer k;
    	try {
    		k = Integer.parseInt(this.txtK.getText());
    		
    		if(k<1 || k>10)
    			throw new NumberFormatException();
    	}
    	catch(NumberFormatException e) {
    		txtResult.appendText("Inserisci un numero INTERO compreso tra 1 e 10!!");
    		return;
    	}
    	
    	Food scelto = this.boxFood.getValue();
    	if(scelto == null) {
    		txtResult.appendText("Scegli prima un CIBO!!");
    		return;
    	}
    	
    	this.sim.init(k, scelto, model);
    	this.sim.simula();
    	
    	this.txtResult.appendText("Sono stati preparati "+this.sim.getNumCibiPreparati()+" cibi in "+this.sim.getTempoTOT()+" minuti");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCalorie != null : "fx:id=\"btnCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
