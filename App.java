import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.lang.StringBuilder;
import library.*;

public class App {

    public static void main(String[] args) throws IOException {
       
        Dicionario Dic = new Dicionario();
        Character[] letras = null;
        System.out.println(Dic);
             
        GeneralTreeOfInteger arvore = new GeneralTreeOfInteger();
	arvore.add("", null);
        
        for (int j = 0; j < Dic.keys().size(); j++) {
	        letras = Dic.keys().get(j).chars().mapToObj(c -> (char) c).toArray(Character[]::new);
		arvore.addArray((Character[]) letras);
	        System.out.println("Fim da loop " + j + " - " + Dic.keys().get(j)); 
	}
        
	Object[] query = new Object[] {""};
        ArrayList<Object[]> search = arvore.searchTree(query);
	StringBuilder str = new StringBuilder("Palavras encontradas: \n");
	for (int i = 0; i < search.size(); i++) {
		for (Object o : search.get(i))
			str.append(o);
		str.append("\n");
	}

	System.out.println(str);
    }
}
