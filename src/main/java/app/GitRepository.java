package app;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GitRepository {
	private String path;
	private Repository repository;
	private ObservableList<GitCommit> commitList;
	
	public GitRepository(String path) throws IOException {
		this.path = path;
		RepositoryBuilder builder = new RepositoryBuilder();
		builder.setMustExist(true);
		builder.setGitDir(new File(path));
		this.repository = builder.build();
		updateCommitList();
		
	}
	
	public void updateCommitList() throws IOException{
		commitList = FXCollections.observableArrayList();
		RevWalk walk = new RevWalk(repository);
		Ref headRef = repository.getRef("HEAD");
    	RevCommit headCommit = walk.parseCommit(headRef.getObjectId());
    	walk.markStart(headCommit);
		for (RevCommit  commit : walk){
			commitList.add(new GitCommit(commit, this.repository));
		}
	}
	
	public ObservableList<GitCommit> getCommitList(){
		return commitList;
	}
}
