import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class App {

    public static void main(String[] args) throws IOException {
       
        Dicionario Dic = new Dicionario();
        char[] letras = null;
        System.out.println(Dic);
        
        //Dic.imprime();
        
        GeneralTreeOfInteger arvore = new GeneralTreeOfInteger();
        arvore.add("", null);
        
        //for(int j=0; j<Dic.keys().size(); j++) 
        for(int j=0; j<4; j++) 
        {        	
	        letras = Dic.keys().get(j).toCharArray();
	        System.out.println(letras);

	        if(!arvore.contains(letras[0]))
	        	arvore.add(letras[0], "");
	        for(int i=1; i<letras.length; i++)
	        {
	        		arvore.add(letras[i], letras[i-1]);

	        }
	    }
        

        System.out.println("Elementos da arvore: caminhamento em largura:");
        System.out.println(arvore.positionsWidth());
        
//        System.out.println("Elementos da arvore: caminhamento pre:");
//       System.out.println(arvore.positionsPre());

        //System.out.println(Dic.getValor("Agatha"));
        System.out.println(Dic.keys());
        
        
    }
}
