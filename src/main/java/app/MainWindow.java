package app;

import java.io.IOException;

import org.eclipse.jgit.lib.Repository;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow extends Application {
	
	static private GitRepository repository;
	@Override
	public void start(Stage primaryStage){
		Scene scene = new Scene(new BorderPane(),100,100);
		primaryStage.setScene(scene);
		primaryStage.show();
		try {
			repository = new GitRepository("/home/lucas/Bureau/test/.git");
			System.out.println("Repo initialisé");
			
			/* ---DEBUG-----
			System.out.println("Nb commit : " + repository.getCommitList().size());
			for(GitCommit commit : repository.getCommitList()){
				System.out.println(commit.getMessage().getValue());
			} 
			for (GitObject object : repository.getCommitList().get(0).getTreeRoot().getChildren()){
				System.out.println(object.nom);
				if(object.isTree()){
					for (GitObject object2 : ((GitTree) object).getChildren()){
						System.out.println("dans dossier :" + object2.nom);
					}
				}
			}*/
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
