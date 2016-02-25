package msp.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	public static void copyFile(String src,String dest) throws IOException {
//		src="data\\test\\central\\hh";
//		dest="data\\test\\box1\\hh";
		src=src.replace('\\', '/');
		dest=dest.replace('\\', '/');
        FileInputStream in=new FileInputStream(src);
        File file=new File(dest);
        if(!file.exists()){
        	createFile(file);
        }
            
        FileOutputStream out=new FileOutputStream(file);
        int c;
        byte buffer[]=new byte[1024];
        while((c=in.read(buffer))!=-1){
            for(int i=0;i<c;i++)
                out.write(buffer[i]);        
        }
        in.close();
        out.close();
    }
	public static void createFile(File file)throws IOException {
        if(!file.exists()){
        	file.getParentFile().mkdirs();
//        	file.mkdirs();
        	file.createNewFile();
        }
    }
	
	public static void createFile(String dest)throws IOException {
		dest=dest.replace('\\', '/');
        File file=new File(dest);
        if(!file.exists()){
        	file.getParentFile().mkdirs();
//        	file.mkdirs();
        	file.createNewFile();
        }
    }
	
	public static void deleteFile(String dest)throws IOException {
		dest=dest.replace('\\', '/');
        File file=new File(dest);
        if(file.exists()){
        	file.delete();
        }
    }
	public static void copyFile(File src,File dest) throws IOException {
		copyFile(src.getAbsolutePath(),dest.getAbsolutePath());
	}
	public static void getFilesIn(File dir,List<File> list) throws IOException {
		if(dir.exists()){
			list.add(dir);
			if(dir.isDirectory())
				for(File file:dir.listFiles())
				{
					getFilesIn(file,list);					
				}
		}
	}
	public static List<File> getFilesIn(File dir) throws IOException {
		List<File> list = new ArrayList<File>();
		getFilesIn(dir,list);
		return list;
	}
	public static List<File> getFilesIn(String dest) throws IOException {
		dest=dest.replace('\\', '/');
        File file=new File(dest);
		return getFilesIn(file);
	}
	
	public static void splitFile(String src, String[] dests) {
		src = src.replace('\\', '/');
		for (int i = 0; i < dests.length; i++) {
			dests[i] = dests[i].replace('\\', '/');
		}
		
		int len = dests.length;
		BufferedWriter[] out = new BufferedWriter[len];
		try {
			BufferedReader in = new BufferedReader(new FileReader(src));
			for (int i = 0; i < len; i++) {
				out[i] = new BufferedWriter(new FileWriter(dests[i]));
			}
			int c = in.read();
			int count = 0;
			while (c != -1) {
				out[count % len].write(c);
				count = (count % len) + 1;
				c = in.read();
			}
			in.close();
			for (int i = 0; i < len; i++) {
				out[i].close();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void mergeFile(String[] dests, String src) {
		src = src.replace('\\', '/');
		for (int i = 0; i < dests.length; i++) {
			dests[i] = dests[i].replace('\\', '/');
		}
		
		int len = dests.length;
		List<BufferedReader> inList = new ArrayList<BufferedReader>();
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(src));
			for (int i = 0; i < len; i++) {
				inList.add(new BufferedReader(new FileReader(dests[i])));
			}
			while (inList.size() > 0) {
				for (int j = 0; j < inList.size(); j++) {
					int c = inList.get(j).read();
					if (c != -1) {
						out.write(c);					
					} else {
						inList.get(j).close();
						inList.remove(j);
						
						j--;
					}
				}
			}
			

			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	

	public static void main(String[] args){
//		try {
//			FileUtils.copyFile("data\\test\\central\\hh", "data\\test\\box1\\hh");
			String src = "data\\test\\central\\hh";
			String[] dests = {"data\\test\\box1\\hh", "data\\test\\box2\\hh"};
			FileUtils.mergeFile(dests, "data\\test\\central\\hh");
//			FileUtils.splitFile("data\\test\\central\\hh", dests);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	

}
