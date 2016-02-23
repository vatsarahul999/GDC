package com.dm.GDC.Writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileCreator {
	StringBuffer contents;
	
	

	public FileCreator() {
		super();
		this.contents = new StringBuffer();
	}

	public void addContents(String args) {
		// TODO Auto-generated method stub
		contents.append(args);
	}

	public void writeToDisk(String args) throws Exception {
		// TODO Auto-generated method stub
		File f = new File(args);
		if(!f.exists())
		{
			f.mkdirs();
		}
		File fin = new File(args+"/readme.md");
		BufferedWriter br = new BufferedWriter(new FileWriter(fin));
		br.write(contents.toString());
		br.close();
	}

}
