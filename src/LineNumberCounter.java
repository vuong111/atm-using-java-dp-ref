import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class LineNumberCounter {
	public static int linesTotal;
	public static int classesTotal;
	public static int instanceFieldsTotal;
	public static int methodsTotal;

	public static void main(String[] args) {
		listDirectory("/workspace/ATM@/src/atm/"); //or absolutePath/canonicalPath
		System.out.println("=================");
		System.out.println("Total: " + linesTotal + " lines (" + 
				classesTotal + " classes | " +
				instanceFieldsTotal + " fields | " +
				methodsTotal + " methods)");
	}
	
	public static void listDirectory(String path) {
		File entry = new File(path);
		
		try {
			if (!entry.exists()) {
				System.out.println(entry.getName() + " not found.");
				return;
			}
			
			if (entry.isFile()) {
				String filePath = entry.getCanonicalPath();
				
				int dotPos = filePath.lastIndexOf(".");
				
				String extension = filePath.substring(dotPos + 1);
				
				if (extension.equals("java"))
				{
					/*get class full name (package.name)
					filePath = D:\workspace\ATM@\src\atm\transaction\Transfer.java
					===> classFullName = atm.transaction.Transfer
					*/
					int packagePos = filePath.lastIndexOf("atm\\");
					String classFullName = filePath.substring(packagePos, dotPos).replace("\\", ".");
					
					try {
						Class c = Class.forName(classFullName);
						System.out.println(filePath + " -> " +
								countLines(filePath) + " lines (" +
								c.getDeclaredFields().length + " fields | " +
								c.getDeclaredMethods().length + " methods)");
						
						classesTotal++;
						linesTotal += countLines(filePath);
						instanceFieldsTotal += c.getDeclaredFields().length;
						methodsTotal += c.getDeclaredMethods().length;
						
					} catch (ClassNotFoundException e) {
						System.out.println("Error: " + e);
					}
				}
			}
			else if (entry.isDirectory()) {
				String[] fileName = entry.list();
				if (fileName == null)
					return;
				
				for (int i = 0; i < fileName.length; i++) {
					File item = new File(entry.getPath(), fileName[i]);
					listDirectory(item.getPath());
				}
			}			
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
	
	//đếm số dòng của 1 file
	public static int countLines(String filename) throws IOException {		
		LineNumberReader reader  = new LineNumberReader(new FileReader(filename));

		while ((reader.readLine()) != null) {}
		
		try {
			return reader.getLineNumber();
		}
		finally {
			reader.close();
		}
	}
}
