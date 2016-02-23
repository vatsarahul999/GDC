package com.dm.GDC.FileParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HiveParser implements FileParser {
	private Pattern p1 = Pattern.compile("[-][-][^~,^\\n]*[\\n]");
	private Matcher m1;
	public static final HiveParser INSTANCE = new HiveParser();

	public static HiveParser getInstance() {
		return INSTANCE;
	}

	private HiveParser() {
		super();
	}

	@Override
	public String getComments(String Contents) {
		m1 = p1.matcher(Contents);
		StringBuffer sb = new StringBuffer();
		while (m1.find()) {
			sb.append(Contents.substring(m1.start(), m1.end()));
			sb.append("\n");
		}
		

		String res = sb.toString();
		return res;

	}

}
