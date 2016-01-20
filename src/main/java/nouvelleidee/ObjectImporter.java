package nouvelleidee;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;



public class ObjectImporter {
	String path;
	ArrayList<String> hashList;
	
	public ObjectImporter(String path) {
		this.path = path;
	}
	
	public void importHashList(){
		hashList = new ArrayList<>();
		File root = new File(path);	
		FilenameFilter filter = new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return dir.isDirectory() && name != "pack" && name != "index";
			}
		};
		
		
		for (File file : root.listFiles(filter)) {
			importHashFromSubDirectories(file); 
		}
	}
	
	private void importHashFromSubDirectories(File dir){
		FileFilter filter = new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return !pathname.isDirectory();
			}
		};
		
		for (File file : dir.listFiles(filter)) {
			hashList.add(dir.getName() + file.getName());
		}
	}
	
	public ArrayList<String> getHashList(){
		return hashList;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public String getPath(){
		return this.path;
	}
}
