package serverside;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Aliasizer {
	
	HashMap<String,String> aliases;
	
	public Aliasizer(){
		aliases = new HashMap<String,String>();		
	}
	
	public void set(String word, String alias){
		aliases.put(word.toLowerCase(), alias);
		
	}
	//FIXME problem med specialtecken.
	public String Aliasify(String s){
		StringTokenizer st = new StringTokenizer(s);
		StringBuilder sb = new StringBuilder();
		while(st.hasMoreTokens()){
			String t = st.nextToken();
			String a = aliases.get(t);
			if(a!=null){
				sb.append(a + " ");				
			}
			else sb.append(t + " ");
		}
		
		return sb.toString();
	}
}
