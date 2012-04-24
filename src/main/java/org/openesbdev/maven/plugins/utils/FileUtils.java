package org.openesbdev.maven.plugins.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FileUtils {

	private FileUtils() {
	}

	public static String getRelativePath(File home, File f) {
		return matchPathLists(getPathList(home), getPathList(f));
	}

	private static List<String> getPathList(File f) {
		List<String> l = new ArrayList<String>();
		File r;
		try {
			r = f.getCanonicalFile();
			while (r != null) {
				l.add(r.getName());
				r = r.getParentFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
			l = null;
		}
		return l;
	}

	private static String matchPathLists(List<String> r, List<String> f) {
		int i;
		int j;
		String s;
		// start at the beginning of the lists
		// iterate while both lists are equal
		s = "";
		i = r.size() - 1;
		j = f.size() - 1;

		// first eliminate common root
		while ((i >= 0) && (j >= 0) && (r.get(i).equals(f.get(j)))) {
			i--;
			j--;
		}

		// for each remaining level in the home path, add a ..
		for (; i >= 0; i--) {
			s += ".." + File.separator;
		}

		// for each level in the file path, add the path
		for (; j >= 1; j--) {
			s += f.get(j) + File.separator;
		}

		// file name
		s += f.get(j);
		return s;
	}
	
	public static void copyFile(File source, File destination) throws IOException {
	      if (!source.exists() || !source.isFile()) {
	          throw new IOException("Source is not valid for copying.");
	      }
	      
	      if (!destination.exists()) {
	          destination.getParentFile().mkdirs();
	          destination.createNewFile();
	      }
	      
	      final File realDest = destination.isDirectory() ? 
	          new File(destination, source.getName()) : 
	          destination;
	      
	      FileInputStream input = null;
	      FileOutputStream output = null;
	      
	      try {
	          input = new FileInputStream(source);
	          output = new FileOutputStream(realDest);
	          
	          byte[] buffer = new byte[4096];
	          while (input.available() > 0) {
	              output.write(buffer, 0, input.read(buffer));
	          }
	      } finally {
	          if (input != null) {
	              input.close();
	          }
	          
	          if (output != null) {
	              output.close();
	          }
	      }
	  }

}
