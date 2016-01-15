package application;

import java.util.Observable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Commit {
	public StringProperty auteur;
	public StringProperty date;
	public StringProperty message = new SimpleStringProperty("");
	public StringProperty shortMessage = new SimpleStringProperty("");
	public Dossier rootDossier = new Dossier("root");
	public String clef = "";
	
	public Commit(String date, String auteur, String clef){
		this.auteur = new SimpleStringProperty(auteur);
		this.date = new SimpleStringProperty(date);
		this.clef = clef;
	}
	
	public StringProperty autorProperty() {
		return auteur;
	}
	
	public void setMessage(String message){
		this.message.set(message);
	}
	public void setShortMessage(String message){
		this.shortMessage.set(message);
	}
	
	public StringProperty getMessage(){
		return this.message;
	}
}
