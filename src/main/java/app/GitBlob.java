package app;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.revwalk.RevBlob;

public class GitBlob extends GitObject{
	public GitBlob(String nom, ObjectId id) {
		this.nom = nom;
		this.id = id;
	}

	public boolean isTree() {
		return false;
	}
}
