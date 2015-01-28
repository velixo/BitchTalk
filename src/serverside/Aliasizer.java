package serverside;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Aliasizer {
	
	HashMap<String,String> aliases;
	
	public Aliasizer(){
		aliases = new HashMap<String,String>();		
	}
	
	public void set(String word, String alias){
		aliases.put(word.toLowerCase(), alias);
	}
	
	HashMap<String, String> getAliases() {
		return aliases;
	}
	
	public String aliasify(String s){
		Set<String> words = aliases.keySet();
		
		String hashifiedWordsInp = s;
		for(String word : words) {
			String hash = word.hashCode()+"";
			hashifiedWordsInp = hashifiedWordsInp.replace(word, hash);
		}
		
		Map<String, String> hashToAliasMap = new HashMap<String, String>();
		for(String word : words) {	//this maps every words hash to the words alias
			String hash = word.hashCode()+"";
			hashToAliasMap.put(hash, aliases.get(word));
		}
		
		String aliasifedInp = hashifiedWordsInp;
		for(String hash : hashToAliasMap.keySet()) {
			String alias = hashToAliasMap.get(hash);
			aliasifedInp = aliasifedInp.replace(hash, alias);
		}
		return aliasifedInp;
		
//		StringTokenizer st = new StringTokenizer(s);
//		StringBuilder sb = new StringBuilder();
//		while(st.hasMoreTokens()){
//			String t = st.nextToken();
//			String a = aliases.get(t);
//			if(a!=null){
//				sb.append(a + " ");				
//			}
//			else sb.append(t + " ");
//		}
//		
//		return sb.toString();
	}
}
