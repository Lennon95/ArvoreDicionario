import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.lang.StringBuilder;
import library.*;

public class App {

	public static void main(String[] args) throws IOException { 
		Dicionario dic = new Dicionario();
		GeneralTree arvore = new GeneralTree();
		carregaDicio(dic, arvore);	

		Character[] query;
		while (true) {	
			System.out.println("Digite a palavra a ser pesquisada ou 'sair' para sair: ");
			String inp = input();
			if (inp.equals("sair")) break;
			query = inp.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
			String str = consultaPalavras(arvore, query);
			if (verifica(str)) {
				System.out.println("Digite uma palavra da lista para ver significado: ");
				inp = input();
				if (dic.getValor(inp) != null) {
					System.out.println("Palavra: " + dic.getValor(inp));
				} else {
					System.out.println("Palavra nao encontrada.");
				}
			}
		}
	}

	public static void carregaDicio(Dicionario dic, GeneralTree tree) {
		for (int j = 0; j < dic.keys().size(); j++) {
			Character[] letras = dic.keys().get(j).chars().mapToObj(c -> (char) c).toArray(Character[]::new);
			tree.addArray(letras);
		}
	}

	public static String consultaPalavras(GeneralTree tree, Character[] query) {
		ArrayList<Object[]> search = tree.searchTree(query);
		StringBuilder str = new StringBuilder("");

		for (int i = 0; i < search.size(); i++) {
			for (Object o : search.get(i))
				str.append(o);
			str.append("\n");
		}
		return str.toString();
	}


	public static String input() {
		Scanner entrada = new Scanner(System.in);
		String palavra;

		palavra = entrada.nextLine();       
		return palavra;
	}

	public static boolean verifica(String str) {
		if (str.equals("")) {
			System.out.println("Palavra nao encontrada.");
			return false;
		} else {
			System.out.println("Palavras encontradas:");
			System.out.println(str);
			return true;
		}
	}
}
