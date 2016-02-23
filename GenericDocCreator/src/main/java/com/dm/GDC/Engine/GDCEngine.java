package com.dm.GDC.Engine;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.dm.GDC.FileParser.HiveParser;
import com.dm.GDC.FileParser.PigParser;
import com.dm.GDC.FileParser.ShellParser;
import com.dm.GDC.Reader.FileDetails;
import com.dm.GDC.Writer.FileCreator;

public class GDCEngine {
	List<FileDetails> filelst;
	String inputPath;
	String outputPath;
	boolean pigFilesExist;
	boolean hiveFilesExist;
	boolean shellFilesExist;

	StringBuffer psb;
	StringBuffer hsb;
	StringBuffer ssb;

	GDCEngine() {
		this.inputPath = System.getProperty("user.dir");
		this.outputPath = inputPath + "/docs/";
		this.filelst = new ArrayList<FileDetails>();
		this.psb = new StringBuffer();
		this.hsb = new StringBuffer();
		this.ssb = new StringBuffer();
		pigFilesExist = false;
		hiveFilesExist = false;
		shellFilesExist = false;
	}

	public static void main(String[] args) throws Exception {
		GDCEngine eng = new GDCEngine();
		eng.checkParameters();
		eng.ScanInputPath();
		eng.IndentifyFilesandComments();
		eng.WriteComments();
		System.out.println("The process has" + " been completed");

	}

	private void WriteComments() throws Exception {
		// TODO Auto-generated method stub
		FileCreator fm = new FileCreator();
		fm.addContents(ssb.toString());
		fm.addContents(psb.toString());
		fm.addContents(hsb.toString());
		fm.writeToDisk(this.outputPath);

	}

	private void IndentifyFilesandComments() {
		// TODO Auto-generated method stub
		String type = null;
		StringBuffer psb1 = new StringBuffer();
		StringBuffer hsb1 = new StringBuffer();
		StringBuffer ssb1 = new StringBuffer();

		for (FileDetails fi : filelst) {
			type = fi.getFileType();
			switch (type) {
			case "pig":
				pigFilesExist = true;
				psb1.append("\n")
						.append(MakeHeader(fi.getRelativeFilePath(), '-'))
						.append(PigParser.getInstance().getComments(
								fi.getFileContents()));
				break;
			case "hql":
				hiveFilesExist = true;
				hsb1.append("\n")
						.append(MakeHeader(fi.getRelativeFilePath(), '-'))
						.append(HiveParser.getInstance().getComments(
								fi.getFileContents()));
				break;
			case "sh":
				shellFilesExist = true;
				ssb1.append("\n")
						.append(MakeHeader(fi.getRelativeFilePath(), '-'))
						.append(ShellParser.getInstance().getComments(
								fi.getFileContents()));
				break;
			default:
				System.out.print(".");
				break;

			}
		}
		if (pigFilesExist)
			psb.append(MakeHeader("Pig Script", '#'));
		if (hiveFilesExist)
			hsb.append(MakeHeader("Hive Script", '#'));
		if (shellFilesExist)
			ssb.append(MakeHeader("Shell Script", '#'));
		psb.append(psb1.toString());
		hsb.append(hsb1.toString());
		ssb.append(ssb1.toString());

		System.out.println();

	}

	private String MakeHeader(String string, char ch) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i <= 99; i++) {
			sb.append(ch);
		}
		sb.append("\n");
		for (int i = 0; i <= 50 - string.length() / 2; i++) {
			sb.append(" ");
		}
		sb.append(string);
		for (int i = 0; i <= 50 - string.length() / 2; i++) {
			sb.append(" ");
		}
		sb.append("\n");
		for (int i = 0; i <= 99; i++) {
			sb.append(ch);
		}
		sb.append("\n");

		return sb.toString();
	}

	private void ScanInputPath() throws Exception {
		Queue<File> dirs = new LinkedList<File>();
		File rootDir = new File(this.inputPath);
		dirs.add(new File(this.inputPath));
		FileDetails fd = null;
		while (!dirs.isEmpty()) {
			for (File f : dirs.poll().listFiles()) {

				if (f.isDirectory()) {
					dirs.add(f);
				} else if (f.isFile()) {

					fd = new FileDetails(rootDir.getPath(), f);
					this.filelst.add(fd);

				}
			}
		}

	}

	private void checkParameters() {
		if (System.getProperty("inputPath") != null)
			this.inputPath = System.getProperty("inputPath");
		this.outputPath = inputPath + "/docs/";
		if ((System.getProperty("outputPath") != null))
			this.outputPath = System.getProperty("outputPath");
		System.out.println("The Directory that will be scanned is "
				+ this.inputPath);
		System.out
				.println("The Document will be created in " + this.outputPath);
	}

}
