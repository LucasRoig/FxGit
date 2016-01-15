package main;



import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class CommitViewController {
	@FXML
	private TableView<Commit> commitTable;
	@FXML
	private TableColumn<Commit, String> autorColumn;
	@FXML
	private TableColumn<Commit, String> dateColumn;
	@FXML
	private TableColumn<Commit, String> messageColumn;
	@FXML
	private TextField messageField;
	@FXML
	private TreeView<String> tree;
	
	private Main main;
	@FXML
	private void initialize() {
        // Initialize the person table with the two columns.
        autorColumn.setCellValueFactory(cellData -> cellData.getValue().autorProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().date);
        messageColumn.setCellValueFactory(cellData -> cellData.getValue().shortMessage);
        showCommitMessage(null);
        makeTree(null);
        commitTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCommitMessage(newValue));
        commitTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> makeTree(newValue));
        
    }
	public void makeTree(Commit c){
		if (c != null){
			TreeItem<String> root = new TreeItem<>("root");
			for(Fichier fichier : c.rootDossier.getListe()){
				root.getChildren().add(new TreeItem<String>(fichier.nom));
			}
			root.setExpanded(true);
			tree.setRoot(root);
		}else{
			tree.setRoot(null);
		}
		
		
	}
	
	public void setMainApp(Main main) {
        this.main = main;
        commitTable.setItems(main.getCommitList());
    }
	
	private void showCommitMessage(Commit commit){
		if(commit != null){
			messageField.setText(commit.getMessage().getValue());
		}else{
			messageField.setText("");
		}
	}
}
