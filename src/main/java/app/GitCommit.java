package app;

import java.io.IOException;

import org.eclipse.jgit.errors.CorruptObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.TreeWalk;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GitCommit {
	
	private ObjectId id;
	private StringProperty auteur;
	private StringProperty message;
	private StringProperty date;
	private GitTree treeRoot;
	private RevCommit revCommit;
	private Repository repository;
	
	
	
	public GitCommit(RevCommit commit, Repository repository) throws MissingObjectException, IncorrectObjectTypeException, CorruptObjectException, IOException {
		this.auteur = new SimpleStringProperty(commit.getAuthorIdent().getName() + " <" + commit.getAuthorIdent().getEmailAddress() + ">");
		this.message = new SimpleStringProperty(commit.getFullMessage());
		this.date = new SimpleStringProperty(commit.getAuthorIdent().getWhen().toString());
		this.id = commit.getId();
		this.revCommit = commit;
		this.repository = repository;
		fillTree();
	}
	
	private void fillTree() throws MissingObjectException, IncorrectObjectTypeException, CorruptObjectException, IOException{
		treeRoot = new GitTree(revCommit.getName(),revCommit.getId(),null);
		GitTree currentTree = treeRoot;
		
		TreeWalk walk = new TreeWalk(repository);
		int currentDepth = walk.getDepth();
		walk.addTree(revCommit.getTree());
		walk.setRecursive(false);
		
		while(walk.next()){
			GitObject object;
			
			if (walk.getDepth() == currentDepth - 1){
				currentTree = currentTree.getParent();
			}
			if (walk.isSubtree()){
				object = new GitTree(walk.getNameString(), walk.getObjectId(0), currentTree);
				currentTree.getChildren().add(object);
				currentTree = (GitTree) object;
				walk.enterSubtree();
			}else {
				object = new GitBlob(walk.getNameString(), walk.getObjectId(0));
				currentTree.getChildren().add(object);
			}
			currentDepth = walk.getDepth();
		}
	}
	
	
	public GitTree getTreeRoot(){
		return treeRoot;
	}
	
	public StringProperty getMessage(){
		return this.message;
	}
}
