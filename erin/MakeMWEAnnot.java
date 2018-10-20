import java.io.*;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.zip.GZIPInputStream;


public class MakeMWEAnnot {

	public static void main (String[] args) throws IOException {

		String fileName = args[0];
		//	BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(new File (fileName))));
		//                OutputStreamWriter out = new OutputStreamWriter (new FileOutputStream(fileName+".1"), "UTF-8");
		BufferedReader d;
		if (fileName.endsWith(".gz")) {
			d = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(new File(fileName)))));
		}
		else {
			d = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
		}
		String str = new String();
		str = d.readLine();
String line = new String(); 

		while (str != null) {
			str = str.trim();
String head = new String(); 
if (str.contains("\t")) {
head = str.substring(0, str.indexOf("\t")).trim(); 
str = str.substring(str.indexOf("\t")+1, str.length()).trim(); 
}

			StringTokenizer st = new StringTokenizer(str, "()", true);
			while (st.hasMoreTokens()) {
				String tok = st.nextToken(); tok = tok.trim(); 
//System.out.println(tok); 
if (!tok.contains(" ") && (tok.equals("A")|| 
tok.equals("ADV")|| tok.equals("C")|| tok.equals("CL")|| tok.equals("D")|| tok.equals("ET")|| 
tok.equals("I")|| tok.equals("N")|| tok.equals("P")|| tok.equals("PRO")|| tok.equals("V") )) {
//System.out.println(tok); 
tok = tok + "+"; 
}
if (tok.equals("(")) tok = " " + tok; 
line += tok + ""; 
			}

line = line.trim();  line = head + "\t" + line; 
System.out.println(line); line = new String(); 


			str = d.readLine();
		}
		d.close();
	}
}
