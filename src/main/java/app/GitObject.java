package app;
import org.eclipse.jgit.lib.ObjectId;

public abstract class GitObject {
	protected ObjectId id;
	protected String nom;
	
	public ObjectId getId(){
		return this.id;
	}
	
	public String getNom(){
		return nom;
	}
	
	public abstract boolean isTree();
}
