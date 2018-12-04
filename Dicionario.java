import java.io.File;
import java.io.IOException;
import java.util.*;
import library.*;

public class Dicionario {

	private TreeMap<String, String> Dic;
	private Scanner sc;
	
	public Dicionario() throws IOException {
		Dic = new TreeMap<String, String>();
		le();
	}

	/**
	 * Le o arquivo CSV e popula o Dic
	 *
	 * @params
	 * @return 
	 */  
	private void le() throws IOException 
	{		
		File arq = new File("nomes.csv");
		sc = new Scanner(arq);
		
		while(sc.hasNextLine())
		{
			String line = sc.nextLine();	
			String[] palavra_desc = line.split(";");
			
			for(int i=0; i < 2; i++) 
			{
				Dic.put(palavra_desc[0], palavra_desc[1]);							
			}
		}					
	}
	
	/**
	 * Retorna o significado de uma palavra-chave
	 * O metodo tem complexidade O(1)
	 *
	 * @params String a palavra-chave do Map
	 * @return String o significado da palavra
	 */  
	public String getValor(String key)
	{
		return Dic.get(key);
	}
	
	/**
	 * Retorna uma lista das palavras-chave do dicionario
	 * O metodo tem complexidade O(1)
	 * 
	 * @params
	 * @return List	as palavras-chave do dicionario
	 */  
	public List<String> keys()
	{
		List<String> result = new ArrayList<String>();
		result.addAll(this.Dic.keySet());
		return result;
	}
		
}
