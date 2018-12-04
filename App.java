import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.lang.StringBuilder;
import library.*;

public class App {

    public static void main(String[] args) throws IOException { 
        Dicionario dic = new Dicionario();
        GeneralTreeOfInteger arvore = new GeneralTreeOfInteger();
	carregaDicio(dic, arvore);	
        
	Character[] query = new Character[] {'I', 's'};
	String str = consultaPalavras(arvore, query);
	System.out.println("Palavras encontradas:");
	System.out.println(str);

	query = new Character[] {'H', 'e', 'l'};	
	str = consultaPalavras(arvore, query);
	System.out.println("Palavras encontradas:");
	System.out.println(str);
    }

    public static void carregaDicio(Dicionario dic, GeneralTreeOfInteger tree) {
        for (int j = 0; j < dic.keys().size(); j++) {
	        Character[] letras = dic.keys().get(j).chars().mapToObj(c -> (char) c).toArray(Character[]::new);
		tree.addArray(letras);
	}
    }
	
    public static String consultaPalavras(GeneralTreeOfInteger tree, Character[] query) {
        ArrayList<Object[]> search = tree.searchTree(query);
	StringBuilder str = new StringBuilder("");

	for (int i = 0; i < search.size(); i++) {
		for (Object o : search.get(i))
			str.append(o);
		str.append("\n");
	}
	return str.toString();
    }
}
