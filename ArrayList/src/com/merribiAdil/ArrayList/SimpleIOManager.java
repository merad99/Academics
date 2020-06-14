package com.merribiAdil.ArrayList;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Objects;


public class SimpleIOManager<T extends Object> implements IOManager<Object>{

	String path= " ";
	
	  public SimpleIOManager(String path) throws FileNotFoundException, IllegalArgumentException {
		  //setPath(path);
	  }
	
	public void setPath(String path) throws FileNotFoundException, IllegalArgumentException {
		File file = new File(path); 
		
		System.out.println(path);
		
		if(file.isDirectory()) {
			String Error = "This is a Directory not a File.";
			throw new IllegalArgumentException(Error);
		}
		if(!file.exists()) {
			String Error = "File does not exist";
			throw new FileNotFoundException(Error);
		}
		this.path = path;
	}

	public String getPath() {
		
		return path;
	}

	public Object read() throws IOException, ClassNotFoundException, IllegalStateException, EOFException{
		
		Object returnObject;
		FileInputStream file = null;
		ObjectInputStream objectInput = null;
		
		if(path.isEmpty()) {
			String Error = "file path not set yet.";
			throw new IllegalStateException (Error);
		}
		
		try {
			file = new FileInputStream(path);
			objectInput = new ObjectInputStream(file);
			
			returnObject = (Object)objectInput.readObject();
			return returnObject;
		} catch (ClassNotFoundException | IOException e ) {
			
			if(Objects.nonNull(objectInput)) {
				objectInput.close();
			}
			if(Objects.nonNull(file)) {
				file.close();
			}
			throw e;
		}
		
	}
	

	public void write(Object object) throws IOException, IllegalStateException {
		FileOutputStream file = null;
		ObjectOutputStream objectOutput = null;
		
		if(path.isEmpty()) {
			String Error = "file path not set yet.";
			throw new IllegalStateException (Error);
		}
		try {
			file = new FileOutputStream(path);
			objectOutput = new ObjectOutputStream(file);
			
			objectOutput.writeObject(object);
			
		} catch (IOException e) {
			
			if(Objects.nonNull(objectOutput)) {
				objectOutput.close();
			}
			if(Objects.nonNull(file)) {
				file.close();
			}
			throw e;
		}
		
	}

}
