import java.io.*;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.zip.GZIPInputStream;


public class MWEParis7 {

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

		while (str != null) {
			str = str.trim();
			boolean flag = false; 
			int dep = 0; 
			int addr = -1; 
			StringTokenizer st = new StringTokenizer(str, "()", true);
			while (st.hasMoreTokens()) {
				String tok = st.nextToken(); tok = tok.trim(); 
				//System.out.println("@ " + tok); 
				//if (tok.equals(" ")) {}
				if (tok.length()>0) {
					if (tok.equals("(")) { dep++; //System.out.println(dep + " " + addr);  
					}
					else if (tok.equals(")")) {
						dep--; //System.out.println(dep + " " + addr); 
                                                if (addr >= dep)  { addr = -1; flag = false; System.out.println();  }
					}
					else if (tok.length()==0) {}
					else {
						if (tok.contains(" ")) {
							if (flag) System.out.print(tok + " "); 
						}
						if (tok.endsWith("+") && !tok.contains(" ")) { 
							//System.out.println("!" + dep); 
							flag = true; addr = dep -1; 
							System.out.print(tok + "\t"); 
						}
					}
				}
			}
			str = d.readLine();

		}
		d.close();
		System.out.println(); 
	}
}
