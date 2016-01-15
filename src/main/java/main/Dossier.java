package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Dossier extends Fichier{
	public String nom;
	private ArrayList<Fichier> listeFichier = new ArrayList<>();
	
	public Dossier(String nom) {
		this.nom = nom;
	}

	@Override
	public boolean estUnDossier() {
		return true;
	}
	
	public void addFichier(Fichier fic){
		this.listeFichier.add(fic);
	}
	
	public ArrayList<Fichier> getListe(){
		return listeFichier;
	}
	
}
