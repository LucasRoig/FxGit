package nouvelleidee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;


public class RepositoryData {

	Repository repository;
	String path;
	ArrayList<String> hashList = new ArrayList<>();
	
	public RepositoryData(String path) throws IOException {
		this.path = path;
		
		RepositoryBuilder builder = new RepositoryBuilder();
		builder.setGitDir(new File(path + "/.git"));
		builder.setMustExist(true);
		try {
			repository = builder.build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObjectImporter oi = new ObjectImporter(path + "/.git/objects");
		oi.importHashList();
		this.hashList = oi.getHashList();
		
		for (String string : hashList) {
			System.out.println("\n \n" + string + "\n");
			ObjectId ref = repository.resolve(string);
			ObjectLoader loader = repository.open(ref);
			loader.copyTo(System.out);
		}
	}
	
	
}
