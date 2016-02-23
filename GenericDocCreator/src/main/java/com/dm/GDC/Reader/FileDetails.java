package com.dm.GDC.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileDetails {
	String fileContents;
	String fileName;
	String filePath;
	String relativeFilePath;
	String fileType;

	public String getFileType() {
		return fileType;
	}

	public void setFileType() {
		String[] arr = this.fileName.split("\\.");
		if (arr.length == 2)
			this.fileType = arr[1];
		else
			this.fileType = "Unidentified File";
	}

	public FileDetails(String inputPath, File f) throws Exception {
		super();
		setFileContents(f);
		this.fileName = f.getName();
		this.filePath = f.getPath();
		setRelativePath(inputPath, f);
		setFileType();
	}

	private void setRelativePath(String inputPath, File f) {
		// TODO Auto-generated method stub
		String str = f.getPath();
		int len = inputPath.length();
		this.relativeFilePath = str.substring(len);

	}

	private void setFileContents(File f) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = br.readLine();
		StringBuffer sb = new StringBuffer();

		while (line != null) {
			sb.append(line);
			sb.append("\n");
			line = br.readLine();
		}
		br.close();
		this.fileContents = sb.toString();

	}

	public String getFileContents() {
		// TODO Auto-generated method stub
		return this.fileContents;
	}

	public String getFileName() {
		// TODO Auto-generated method stub
		return this.fileName;
	}

	public String getFilePath() {
		// TODO Auto-generated method stub
		return this.filePath;
	}

	public String getRelativeFilePath() {
		// TODO Auto-generated method stub
		return this.relativeFilePath;
	}

}
