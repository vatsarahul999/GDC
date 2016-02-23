package com.dm.GDC.FileParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PigParser implements FileParser {

	private Pattern p1 = Pattern
			.compile("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)");
	private Matcher m1;
	private Pattern p2 = Pattern.compile("[-][-][^~,^\\n]*[\\n]");
	private Matcher m2;
	private static final PigParser INSTANCE = new PigParser();

	private PigParser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static PigParser getInstance() {
		return INSTANCE;
	}

	@Override
	public String getComments(String Contents) {
		// TODO Auto-generated method stub
		m1 = p1.matcher(Contents);
		StringBuffer sb = new StringBuffer();
		while (m1.find()) {
			sb.append(Contents.substring(m1.start(), m1.end()));

		}
		sb.append("\n");
		m2 = p2.matcher(Contents);
		while (m2.find()) {
			sb.append(Contents.substring(m2.start(), m2.end()));

		}
		sb.append("\n");
		String res = sb.toString();
		return res;

	}

}
