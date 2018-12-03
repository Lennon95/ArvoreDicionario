
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class GeneralTreeOfInteger<T>{

    // Classe interna Node
    private class Node {

        public Node father;
        public T element;
        public LinkedList<Node> subtrees;
        
        public Node(T element) {
            father = null;
            this.element = element;
            subtrees = new LinkedList<>();
        }

        public void addSubtree(Node n) {
            n.father = this;
            subtrees.add(n);
        }

        public boolean removeSubtree(Node n) {
            n.father = null;
            return subtrees.remove(n);
        }

        public Node getSubtree(int i) {
            if ((i < 0) || (i >= subtrees.size())) {
                throw new IndexOutOfBoundsException();
            }
            return subtrees.get(i);
        }
               
        public int getSubtreesSize() {
            return subtrees.size();
        }

    }

    // Atributos
    private Node root;
    private int count;

    // Metodos
    public GeneralTreeOfInteger() {
        root = null;
        count = 0;
    }

    public T getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return root.element;
    }

    public void setRoot(T element) {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        root.element = element;
    }

    public boolean isRoot(T element) {
        if (root != null) {
            if (root.element.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public void clear() {
        root = null;
        count = 0;
    }

    public T getParent(T element) {
        Node n = searchNodeRef(element, root);
        if (n == null || n.father == null) {
            return null;
        } else {
            return n.father.element;
        }
    }

    public boolean contains(T element) {
        Node nAux = searchNodeRef(element, root);
        return (nAux != null);
    }

    public boolean isExternal(T element) {
        Node n = searchNodeRef(element, root);
        if (n != null) {
            return n.getSubtreesSize() == 0;
        }
        return false;
    }

    public boolean isInternal(T element) {
        Node n = searchNodeRef(element, root);
        if (n != null) {
            return n.getSubtreesSize() > 0;
        }
        return false;
    }
    
     private Node searchNodeRef(T element, Node target) {
        Node res = null;
        if (target != null) {
            if (element.equals(target.element)) {
                res = target;
            } else {
                Node aux = null;
                int i = 0;
                while ((aux == null) && (i < target.getSubtreesSize())) {
                    aux = searchNodeRef(element, target.getSubtree(i));
                    i++;
                }
                res = aux;
            }
        }
        return res;
    }
     
     //mesma coisa que o searchNodeRef, porem nao verifica ele mesmo
     private Node searchNodeRef2(T element, Node target) {
         Node res = null;
         if (target != null) {
                 Node aux = null;
                 int i = 0;
                 while ((aux == null) && (i < target.getSubtreesSize())) {
                     aux = searchNodeRef(element, target.getSubtree(i));
                     i++;
                 }
                 res = aux;             
         }
         return res;
     }
          
    public boolean add(T element, T father) {
        Node n = new Node(element);
        Node nAux = null;
        boolean res = false;
        if (father == null) {   // Insere na raiz 	
            if (root != null) { //Atualiza o pai da raiz
                n.addSubtree(root);
                root.father = n;
            }
            root = n;   //Atualiza a raiz
            res = true;
            count++;
        } else {        //Insere no meio da Ã�rvore
            nAux = searchNodeRef(father, root);
            Node nAux2 = searchNodeRef2(nAux.element, nAux);
         
            if (nAux != null && searchNodeRef(element, nAux)==null && nAux2==null)
            //ve se nAux existe //verifica se ja nao existe o elemento no nodo //verifica se nao existe um outro nodo com mesmo valor mais abaixo
            {                	
                nAux.addSubtree(n);
                n.father = nAux;
                res = true;
                count++;     
                System.out.println("1) letra: " + n.element + " pai: " + n.father.element);
            }
            else if(nAux2!=null)
            {          	
            	nAux2.addSubtree(n);            	
                n.father = nAux2;
                res = true;
                count++;     
                System.out.println("2) letra: " + n.element + " pai: " + n.father.element);
            }
        }  
       
        return res;
    }

    public boolean removeBranch(T element) {
        Node nAux = null;
        Node father = null;
        boolean rem = false;
        if (root != null) {
            if (root.element.equals(element)) {
                root = null;
                count=0;
                rem = true;
            } else {
                nAux = searchNodeRef(element, root);
                if (nAux != null) {
                    int c = count(nAux);
                    father = nAux.father;
                    father.removeSubtree(nAux);
                    rem = true;
                    count = count - c;
                }
            }
        }
        return rem;
    }

    public LinkedListOfInteger positionsPre() {
        LinkedListOfInteger lista = new LinkedListOfInteger();
        positionsPreAux(root, lista);
        return lista;
    }

    private void positionsPreAux(Node n, LinkedListOfInteger lista) {
        if (n != null) {
            lista.add(n.element);
            for (int i = 0; i < n.getSubtreesSize(); i++) {
                positionsPreAux(n.getSubtree(i), lista);
            }
        }
    }

    public LinkedListOfInteger positionsPos() {
        LinkedListOfInteger lista = new LinkedListOfInteger();
        positionsPosAux(root, lista);
        return lista;
    }

    private void positionsPosAux(Node n, LinkedListOfInteger lista) {
        if (n != null) {
            for (int i = 0; i < n.getSubtreesSize(); i++) {
                positionsPosAux(n.getSubtree(i), lista);
            }
            lista.add(n.element);
        }
    }

    public LinkedListOfInteger positionsWidth() {
        LinkedListOfInteger lista = new LinkedListOfInteger();

        Queue<Node> fila = new Queue<>();
        Node atual = null;

        if (root != null) {
            fila.enqueue(root);
            while (!fila.isEmpty()) {
                atual = fila.dequeue();
                lista.add(atual.element);
                for (int i = 0; i < atual.getSubtreesSize(); i++) {
                    fila.enqueue(atual.getSubtree(i));
                }
            }
        }

        return lista;
    }
    
    public int level(T element) {
            Node n = this.searchNodeRef(element, root);
            if (n==null)
                throw new NoSuchElementException();
            else  {
                int cont=0;
                while (n != root) {
                    n = n.father;
                    cont ++;
                }
                return cont;
            }
    }    

    private int count(Node n) {
        if (n == null) {
            return 0;
        } else {
            int c=0;
            for(int i=0; i<n.getSubtreesSize(); i++) {
                c += count(n.getSubtree(i));
            }            
            return 1 + c;
        }
    }
  
}
