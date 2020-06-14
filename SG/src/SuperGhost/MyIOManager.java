package SuperGhost;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Objects;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import sharedCode.SharedGameData;

public class MyIOManager<T extends Object> implements IOManager<T> {
  
	  String path = "";
	  
	  public MyIOManager(String path) throws FileNotFoundException, IllegalArgumentException {
		  setPath(path);
	  }
	
	/**
	 * Sets the file path that this IOManager will read and write an object from. This 
	 * file location will be used by {@link #read()} and {@link #write(Object)}
	 * @param path The file location to be read and written to
	 * @throws FileNotFoundException when the specified path can not be found.
	 * @throws IllegalArgumentException When the specified path points to a folder/directory rather than a file
	 */
	public void setPath(String path) throws FileNotFoundException, IllegalArgumentException{
		
	  
	 
		File f = new File(path);
 
		

			if(!f.exists()){
				String error = "The file does not exist.";
		        throw new FileNotFoundException (error);
	         }
			
           if( f.isDirectory()) {
        	   String error = "It is directory, not a file.";
	            throw new IllegalArgumentException(error);
            } 
           
           this.path = path;
	
		
		 
}
	
	/**
	 * The file path that is IOManager read and write an object from. This 
	 * file location will is used by {@link #read()} and {@link #write(Object)}.
	 * This method may not return a null value. If the path is not set this should
	 * return an empty string.
	 * @param path The file location to be read and written to
	 */
	public String getPath() {
		
		return path;
	}
	
	/**
	 * Reads the object saved in the file located at the path returned by {@link #getPath()}
	 * @return An object saved in the file 
	 * @throws IOException When something goes wrong in the IO process
	 * @throws ClassNotFoundException If the object located at the is a class that is not implemented
	 * by this version of your software
	 * @throws IllegalStateException If the file path has not been set yet
	 */
	public SharedGameData read() throws IOException, ClassNotFoundException, IllegalStateException{
		
		SharedGameData returnObject;
		// Reading the object from a file 
		FileInputStream file = null;
		ObjectInputStream in = null;
 
		
		if(path.isEmpty()){
			String error = "read: The file path is not set yet.";
	        throw new IllegalStateException (error);
         }
		
		try {
			file = new FileInputStream(path); 
			in = new ObjectInputStream(file); 

			// Method for deserialization of object of type T
			returnObject = (SharedGameData)in.readObject();
			
			return returnObject;
			
		} catch (ClassNotFoundException | IOException e) {

			if(Objects.nonNull(in)) {
			in.close();
			}

			if(Objects.nonNull(file)) {
				file.close();
			}

			throw e;
		}
		
		
	}
	
	/**
	 * Writes and object to saved at the file located at the path returned by {@link #getPath()}
	 * @param object The object saved in the file 
	 * @throws IOException When something goes wrong in the IO process
	 * @throws IllegalStateException If the file path has not been set yet
	 */
	public void write(T object) throws IOException, IllegalStateException{
		

		//Saving of object in a file 
		FileOutputStream file = null;
		ObjectOutputStream out = null;
		
		if(path.isEmpty()){
			String error = "write: The file path is not set yet.";
	        throw new IllegalStateException (error);
         }
		
		
		try {
			
			file = new FileOutputStream(path); 
			out = new ObjectOutputStream(file); 

			// Method for serialization of object 
			out.writeObject(object);
			
		} catch (Exception e) {
			
			if(Objects.nonNull(out)) {
				out.close();
			}

			if(Objects.nonNull(file)) {
				file.close();
			}

			throw e;
		}  
		
		
		
	}
	
	
}
