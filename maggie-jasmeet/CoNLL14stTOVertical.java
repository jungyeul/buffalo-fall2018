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
System.out.println("## " + str); 
				String delim = " ";
				String[] entry;
				entry = str.split(delim);

				for (int i=0; i<entry.length; i++) {
					String tok = entry[i];
					ar[id++][0] = tok; 
				}
			}
			else if (str.startsWith("A")) {
System.out.println("## " + str);
//				System.out.println(str); 
				String label = new String(); 
				String correct = new String(); 

				String delim = "|"; 
				StringTokenizer st = new StringTokenizer(str, delim);
				int addr = 0; 
				int start = -1; 
				int end = -1; 

				//System.out.println(id); 

				while (st.hasMoreTokens()) {
					String tok = st.nextToken();
					/*
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
					 */
					addr++; 
					//System.out.println("### " + addr + "\t" + tok); 

					if (addr == 1) {
						start = -1; 
						end = -1; 
						String temp = tok.substring(tok.indexOf(" "), tok.length()).trim(); 
						start = Integer.parseInt(temp.substring(0, temp.indexOf(" ")).trim()); 
						end = Integer.parseInt(temp.substring(temp.indexOf(" ")+1, temp.length()).trim()); 
					}
					else if (addr == 2) { 
						label = tok; 
						ar[end][1] = tok + "\t"; 
					}
					else if (addr == 3) {
						correct = tok;  
						ar[end][1] += tok; 
					}


				}
				flag = false; 
			}
			else if (str.length()==0) {

				if (id>0) {
					for (int i=1; i<id;i++) { // 0 -> print "S" ; 1 -> not print "S"; 
						if (ar[i][1]!=null) {
							System.out.println(ar[i][0] + "\t" + ar[i][1]); 
						}
						else {
							System.out.println(ar[i][0] + "\tO\tO" ); 
						}
					}
					System.out.println(); 
				}				
				id = 0; 
				ar = new String[1024][2];
			}

			str = d.readLine();
		}
		d.close();
	}
}

