package main;
	
import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static BorderPane root;
	private ObservableList<Commit> commitList = FXCollections.observableArrayList();
	private Repository repository;
	
	public Main(){
		openRepository("/home/lucas/Bureau/test/.git");
		try {
			fillCommitList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fillCommitList() throws IOException{
		RevWalk walk = new RevWalk(repository);
    	Ref head = repository.getRef("HEAD");
    	RevCommit commit = walk.parseCommit(head.getObjectId());
    	walk.markStart(commit);
        for (RevCommit rev : walk){
        	Commit c = new Commit(rev.getAuthorIdent().getWhen().toString(), rev.getAuthorIdent().getName(), rev.getName());
        	c.setMessage(rev.getFullMessage());
        	c.setShortMessage(rev.getShortMessage());
        	TreeWalk treeWalk = new TreeWalk(repository);
            treeWalk.setRecursive(false);
            treeWalk.addTree(rev.getTree());
            while(treeWalk.next()){
            	if (treeWalk.isSubtree()) {
            	    treeWalk.enterSubtree();
                } else {
                    c.rootDossier.addFichier(new Document(treeWalk.getPathString()));
                }
            }
        	this.commitList.add(c);
        }
	}
	public void openRepository(String path){
		FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
        repositoryBuilder.setMustExist(true);
        repositoryBuilder.setGitDir( new File(path));
        try {
			this.repository = repositoryBuilder.build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void start(Stage primaryStage) throws IOException {
		try {
			root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Git Viewer");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		showCommitView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void showCommitView(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("CommitView.fxml"));
			AnchorPane commitView = loader.load();
			root.setCenter(commitView);
			
			CommitViewController controller = loader.getController();
			controller.setMainApp(this);
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public ObservableList<Commit>getCommitList(){
		return commitList;
	}
}
