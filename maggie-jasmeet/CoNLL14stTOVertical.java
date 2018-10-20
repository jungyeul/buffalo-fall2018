import java.io.*;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.zip.GZIPInputStream;

public class CoNLL14stTOVertical {

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

		String[][] ar = new String[1024][2];
		int id = 0; 

boolean flag = true; 

		while (str != null) {
			str = str.trim();

			if (str.startsWith("S ")) {
				String delim = " ";
				String[] entry;
				entry = str.split(delim);

				for (int i=0; i<entry.length; i++) {
					String tok = entry[i];
					ar[id++][0] = tok; 
				}
			}
			else if (str.startsWith("A")) {
				System.out.println(str); 
				/*
				   A 7 8|||Vform|||cause|||REQUIRED|||-NONE-|||0
				   A
				   7
				   8
				   Vform
				   cause
				   REQUIRED
				 */
				String label = new String(); 
				String correct = new String(); 

				String delim = "| "; 
				StringTokenizer st = new StringTokenizer(str, delim);
				int addr = 0; 
				int start = -1; 
				int end = -1; 

				System.out.println(id); 

				while (st.hasMoreTokens()) {
					String tok = st.nextToken();
					if (addr == 1)  {
						start = Integer.parseInt(tok); 
					}
					else if (addr == 2) {
						end = Integer.parseInt(tok); 
                                                //System.out.println(ar[end][0]);
					}
					else if (addr == 3) {
						label = tok; 
						ar[end][1] = tok + "\t"; 
					}
					else if (addr == 4) {
						correct = tok;  
						ar[end][1] += tok; 
					}
					addr++; 
				}
flag = false; 
			}
			else if (str.length()==0) {

if (id>0) {
for (int i=0; i<id;i++) {
System.out.println(ar[i][0] + "\t" + ar[i][1]); 
}
//if (!flag) { break; }
}				id = 0; 
				ar = new String[1024][2];
			}

			str = d.readLine();
		}
		d.close();
	}
}

/*
   StringTokenizer st = new StringTokenizer(str, delim);
   while (st.hasMoreTokens()) {
   String tok = st.nextToken();
   }
 */
/*
   Enumeration k = hash.keys();
   while(k.hasMoreElements()) {
   String key = (String) k.nextElement();
   System.out.println(key + "\t" + hash.get(key));
   }
 */
