package app;

import java.util.ArrayList;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.revwalk.RevTree;

public class GitTree extends GitObject{
	private ArrayList<GitObject> childrens = new ArrayList<GitObject>();
	private GitTree parent;
	
	public GitTree(String nom, ObjectId id, GitTree parent) {
		this.nom = nom;
		this.id = id;
		this.parent = parent;
	}
	
	public ArrayList<GitObject> getChildren(){
		return childrens;
	}

	public boolean isTree() {
		return true;
	}
	
	public GitTree getParent(){
		return parent;
	}
}
