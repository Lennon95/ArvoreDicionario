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
	
	public void imprime()
	{
		System.out.println(Dic.keySet());
		System.out.println(Dic.values());
	}

	
	public String getValor(String a)
	{
		return Dic.get(a);
	}
	
	public List<String> keys()
	{
		List<String> result = new ArrayList<String>();
		result.addAll(this.Dic.keySet());
		return result;
	}
		
}
