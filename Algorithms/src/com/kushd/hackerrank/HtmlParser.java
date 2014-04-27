package com.kushd.hackerrank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HtmlParser {
	
	
	public static void main(String[] args) {
        try {
        	HTMLParsing parser = new HTMLParsing();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String strLine = null;
			
				strLine = br.readLine();
				int nn = Integer.parseInt(strLine.trim());
				String[] lines = new String[nn];
				for(int i=0;i<nn;i++){
					strLine = br.readLine();
					lines[i] = strLine;
				}
				parser.process(lines);
				Map<String,List<String>> map = parser.getMap();
				Set<String> keys = map.keySet();
				List<String> list = new ArrayList<String>();
				for(String key : keys){
					list.add(key);
				}
				Collections.sort(list);
				for(String entry : list){
					System.out.print(entry+":");
					List<String> attrs = map.get(entry);
					Collections.sort(attrs);
					for(int kk=0;kk<attrs.size();kk++){
						System.out.print(attrs.get(kk));
						if(kk!= (attrs.size()-1)){
							System.out.print(",");
						}
					}
					System.out.print("\n");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}

class HTMLParsing {
	
	private Map<String,List<String>> map;
	
	public HTMLParsing() {
		this.map = new HashMap<String,List<String>>();
	}
	
	public Map<String,List<String>> getMap(){
		return map;
	}
	
	public void process(String[] lines){
		for(String line : lines){
			boolean findingTag =false;
			String findingAttr = null;
			char[] array = line.toCharArray();
			int i=0;
			while(i<array.length){
				if(findingAttr != null){
					while(array[i]==' '){
						i++;
					}
					while(array[i]!='>'&&array[i]!='/'){
						String attr="";
						while(array[i]!='=' && array[i]!=' '&& array[i]!='>'&&array[i]!='/'){
							attr = attr + array[i];
							i++;
						}
						List<String> attrlist = map.get(findingAttr);
						if(!attrlist.contains(attr)){
							attrlist.add(attr);
						}
						while(array[i]==' '){
							i++;
						}
						if(array[i]=='='){
							i++;
							while(array[i]==' '){
								i++;
							}
							char start = array[i];
							if(start == '\'' || start == '"'){
								i++;
								while(array[i]!=start){
									if(array[i] == '\\'){
										i=i+1;
									}
									i++;
								}
								i++;
							}else{
								while(array[i]!=' '&& array[i]!='>'&&array[i]!='/'){
									if(array[i] == '\\'){
										i=i+1;
									}
									i++;
								}
							}
							while(array[i]==' '){
								i++;
							}
						}
					}
					findingAttr=null;
				}
				if(findingTag){
					if(array[i]!='/'){
						String tag = "";
						while(array[i] != ' ' && array[i] != '>' && array[i] != '/'){
							tag = tag + array[i];
							i++;
						}
						if(!map.containsKey(tag)){
							map.put(tag, new ArrayList<String>());
						}
						if(array[i] == ' ' ){
							findingAttr=tag;
						}
					}
					findingTag=false;
				}
				if(array[i]=='<'){
					findingTag=true;
				}
				i++;
			}
		}
	}
	
	
}
