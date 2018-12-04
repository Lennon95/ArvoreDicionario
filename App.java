import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.lang.StringBuilder;



public class App {

    public static void main(String[] args) throws IOException {
       
        Dicionario Dic = new Dicionario();
        Character[] letras = null;
        System.out.println(Dic);
        
        //Dic.imprime();
        
        GeneralTreeOfInteger arvore = new GeneralTreeOfInteger();
	arvore.add("", null);
        
        for (int j=0; j<5; j++) {
	        letras = Dic.keys().get(j).chars().mapToObj(c -> (char) c).toArray(Character[]::new);
		arvore.addArray((Character[]) letras);
	        System.out.println("Fim da loop " + j + " - " + Dic.keys().get(j)); 
	}
	        

        System.out.println("Elementos da arvore: caminhamento em largura:");
        System.out.println(arvore.positionsWidth());
       arvore.printTreeLevels();
//        System.out.println("Elementos da arvore: caminhamento pre:");
//       System.out.println(arvore.positionsPre());

        //System.out.println(Dic.getValor("Agatha"));
  //      System.out.println(Dic.keys());
        
	T[] query = new T[] {'A', 'l'};
        ArrayList<Character[]> search = arvore.searchTree(query);
	strBuild str = new StringBuilder("Palavras encontradas: \n");
	for (int i = 0; i < search.size(); i++) {
		for (Character c : search.get(i))
			str.append(c.toString);
		str.append("\n");
	}

	System.out.println(str);
    }
}
