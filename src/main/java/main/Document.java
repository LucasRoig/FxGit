package main;

public class Document extends Fichier{
	public Document(String nom) {
		this.nom = nom;
	}

	@Override
	public boolean estUnDossier() {
		return false;
	}
	
	
}
