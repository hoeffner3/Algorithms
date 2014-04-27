package com.kushd.moderate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ExternalSorting {
	
	private static final long blockSize = 64000000; //In bytes
	private static final String tmpDirectory = "/tmp/externalsorting";
	
	public List<File> sortInBatch(File file, Comparator<String> cmp) throws IOException{
		List<File> files = new ArrayList<File>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		try{
			List<String> lineList = new ArrayList<String>();
			String line = "";
			try{
				while(line != null){
					long curSize = 0;
					while(curSize < blockSize && (line = br.readLine()) != null){
						lineList.add(line);
						curSize = curSize + line.length();
					}
					files.add(sortAndSave(lineList,cmp));
					lineList.clear();
				}
			}catch(Exception e){
				if(!lineList.isEmpty()){
					files.add(sortAndSave(lineList,cmp));
					lineList.clear();
				}
			}
		}finally{
			br.close();
		}
		
		
		return files;
	}

	private File sortAndSave(List<String> lineList, Comparator<String> cmp) throws IOException {
		Collections.sort(lineList,cmp);
		File tempFile = File.createTempFile("flatfile", tmpDirectory);
		tempFile.deleteOnExit();
		OutputStream out = new FileOutputStream(tempFile);
		BufferedWriter bwr = new BufferedWriter(new OutputStreamWriter(out));
		try{
			String lastLine = null;
			for(String line : lineList){
				if(!line.equals(lastLine)){
					bwr.write(line);
					bwr.newLine();
					lastLine = line;
				}
			}
		}finally{
			bwr.close();
		}
		return tempFile;
	}
	
	public int mergeFiles(List<File> fileList, final Comparator<String> cmp, String outFile) throws IOException{
		PriorityQueue<CachedBufferedReader> pqueue = new PriorityQueue<CachedBufferedReader>(10, new Comparator<CachedBufferedReader>() {
			@Override
			public int compare(CachedBufferedReader o1, CachedBufferedReader o2) {
				// TODO Auto-generated method stub
				return cmp.compare(o1.peek(), o2.peek());
			}
		});
		for(File f : fileList){
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			CachedBufferedReader cbr = new CachedBufferedReader(br);
			if(cbr.peek() != null){
				pqueue.add(cbr);
			}
		}
		BufferedWriter bwr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		int count=0;
		try{
			String lastLine = null;
			while(!pqueue.isEmpty()){
				CachedBufferedReader cbr = pqueue.poll();
				String line = cbr.pop();
				if(!line.equals(lastLine)){
					bwr.write(line);
					bwr.newLine();
					lastLine = line;
					count++;
				}
				if(cbr.peek() == null){
					cbr.br.close();
				}else{
					pqueue.add(cbr);
				}
			}
		}finally{
			bwr.close();
			for(CachedBufferedReader cbr : pqueue){
				cbr.br.close();
			}
		}
		return count;
	}
	

}

class CachedBufferedReader {
	
	public BufferedReader br;
	public String curLine;
	
	public CachedBufferedReader(BufferedReader br) throws IOException {
		this.br = br;
		load();
		
	}
	
	public void load() throws IOException{
		curLine = br.readLine();
	}
	
	public String peek() {
        return curLine;
	}

	public String pop() throws IOException {
        String answer = new String(curLine);
        load();
        return answer;
	}
}
