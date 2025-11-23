package org.example.classes;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<T> implements Iterable<T> {

    private TreeNode<T> root;
    private int size;
    private final Comparator<? super T> cmp;

    public BinarySearchTree(){
        this(null);
    }
    public BinarySearchTree(Comparator<? super T> cmp){
        this.cmp = cmp;
    }

    public boolean add(T value){
        Objects.requireNonNull(value,"Valor não pode ser nulo!");
        int anterior = size;
        root = insert(root, value);
        return size > anterior;
    }

    public boolean contains(T value){
        Objects.requireNonNull(value,"Valor não pode ser nulo!");
        return findNode(root, value) != null;
    }

    public boolean remove(T value){
        int anterior = size;
        root = delete(root, value);
        return size < anterior;
    }

    public void clear(){
        root = null;
        size = 0;
    }
    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int height() {
        return nodeHeight(root);
    }

    public Optional<T> min(){
        if(root == null)
            return Optional.empty();
        TreeNode<T> n = root;
        while (n.left != null)
            n = n.left;
        return Optional.of(n.value);
    }

    public Optional<T> max(){
        if(root == null)
            return Optional.empty();
        TreeNode<T> n = root;
        while (n.right != null)
            n = n.right;
        return Optional.of(n.value);
    }

    public boolean validate(){
        List<T> in = new ArrayList<>();
        inOrder(v -> in.add(v));
        for(int i =1;i<in.size();i++){
            if(compare(in.get(i-1), in.get(i)) >= 0)
                return false;
        }
        return true;
    }

    public TreeNode<T> findNode(TreeNode<T> n, T value){
        while (n!=null){
            int c = compare(value, n.value);
            if(c==0) return n;
            n= (c < 0) ? n.left : n.right;
        }
        return null;
    }

    private TreeNode<T> insert(TreeNode<T> n, T value){
        if(n == null){
            size++;
            return new TreeNode<>(value);
        }
        int c = compare(value, n.value);
        if(c < 0){
            n.left = insert(n.left,value);
        } else if(c > 0) {
            n.right = insert(n.right,value);
        }
        else {
            return n;
        }
        recompute(n);
        return n;
    }

    private TreeNode<T> delete(TreeNode<T> n, T value){
        if(n==null) return null;

        int c = compare(value, n.value);
        if(c<0){
            n.left = delete(n.left, value);
        }else if(c>0){
            n.right = delete(n.right, value);
        }else{
            //Caso 1: Sem Filhos
            if (n.left == null && n.right == null) {
                size--;
                return null;
                //Caso 2: 1 filho
            }else if(n.left == null || n.right == null){
                size--;
                return n.right!=null ? n.right : n.left;
                //Caso 3: 2 filhos
            }else {
                TreeNode<T> succ = n.right;
                while (succ.left !=null) succ = succ.left;
                n.value = succ.value;
                n.right = delete(n.right, succ.value);
            }
        }
        recompute(n);
        return n;
    }

    @SuppressWarnings("unchecked")
    private int compare(T a, T b) {
        if(cmp != null) return cmp.compare(a,b);
        return ((Comparable<? super T>)a).compareTo(b);
    }

    private void recompute(TreeNode<T> n) {
        n.height = Math.max(nodeHeight(n.left), nodeHeight(n.right))+1;
    }

    private int nodeHeight(TreeNode<T> n) {
        return (n==null) ? 0 : n.height;
    }

    public int alturaRecursiva() {
        return alturaRecursiva(root);
    }

    private int alturaRecursiva(TreeNode<T> n) {
        if (n == null) return -1; // ou 0 se quiser contar nós em vez de arestas
        int esquerda = alturaRecursiva(n.left);
        int direita = alturaRecursiva(n.right);
        return Math.max(esquerda, direita) + 1;
    }

    public boolean ehEstrictamenteBinaria() {
        return ehEstrictamenteBinaria(root);
    }

    private boolean ehEstrictamenteBinaria(TreeNode<T> n) {
        if (n == null) return true;
        // Nó folha
        if (n.left == null && n.right == null) return true;
        // Nó com dois filhos
        if (n.left != null && n.right != null)
            return ehEstrictamenteBinaria(n.left) && ehEstrictamenteBinaria(n.right);
        // Caso tenha só um filho
        return false;
    }

    public boolean ehCompleta() {
        if (root == null) return true;

        Queue<TreeNode<T>> fila = new ArrayDeque<>();
        fila.add(root);
        boolean encontrouNulo = false;

        while (!fila.isEmpty()) {
            TreeNode<T> atual = fila.remove();

            if (atual.left != null) {
                if (encontrouNulo) return false; // apareceu nó após um "buraco"
                fila.add(atual.left);
            } else {
                encontrouNulo = true;
            }

            if (atual.right != null) {
                if (encontrouNulo) return false;
                fila.add(atual.right);
            } else {
                encontrouNulo = true;
            }
        }
        return true;
    }

    public void preOrder(TreeNode<T> n, Consumer<T> act){
        if(n==null) return;
        act.accept(n.value);
        preOrder(n.left, act);
        preOrder(n.right,act);
    }

    public void preOrder(Consumer<T> act){
        Objects.requireNonNull(act);
        preOrder(root, act);
    }

    public void inOrder(TreeNode<T> n, Consumer<T> act){
        if(n==null) return;
        inOrder(n.left, act);
        act.accept(n.value);
        inOrder(n.right,act);
    }

    public void inOrder(Consumer<T> act){
        Objects.requireNonNull(act);
        inOrder(root, act);
    }

    public void postOrder(TreeNode<T> n, Consumer<T> act){
        if(n==null) return;
        postOrder(n.left, act);
        postOrder(n.right,act);
        act.accept(n.value);
    }

    public void postOrder(Consumer<T> act){
        Objects.requireNonNull(act);
        postOrder(root, act);
    }

    public void exibirFolhasEmOrdem() {
        System.out.print("Nós folhas em ordem crescente: ");
        exibirFolhasEmOrdem(root);
        System.out.println();
    }

    private void exibirFolhasEmOrdem(TreeNode<T> n) {
        if (n == null) return;
        exibirFolhasEmOrdem(n.left);
        if (n.left == null && n.right == null)
            System.out.print(n.value + " ");
        exibirFolhasEmOrdem(n.right);
    }

    public void levelOrder(Consumer<T> act){
        Objects.requireNonNull(act);
        if(root == null) return;
        Queue<TreeNode<T>> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()){
            TreeNode<T> n = q.remove();
            act.accept(n.value);
            if(n.left != null) q.add(n.left);
            if(n.right != null) q.add(n.right);
        }
    }

    public void reconstruirEExibirPosfixado(int[] preOrdem, int[] emOrdem) {
        if (preOrdem == null || emOrdem == null || preOrdem.length != emOrdem.length) {
            System.out.println("As sequências são inválidas.");
            return;
        }

        // Reconstruir recursivamente
        this.root = reconstruirRec(preOrdem, emOrdem, 0, preOrdem.length - 1, 0, emOrdem.length - 1);

        System.out.print("Caminhamento Pós-Fixado da árvore reconstruída: ");
        postOrder(x -> System.out.print(x + " "));
        System.out.println();
    }

    private TreeNode<T> reconstruirRec(int[] pre, int[] in, int preIni, int preFim, int inIni, int inFim) {
        if (preIni > preFim || inIni > inFim)
            return null;

        // A raiz é sempre o primeiro elemento do prefixado
        int valorRaiz = pre[preIni];
        TreeNode<T> no = new TreeNode<>((T) Integer.valueOf(valorRaiz));

        // Localiza a posição da raiz no vetor emOrdem
        int indiceRaiz = -1;
        for (int i = inIni; i <= inFim; i++) {
            if (in[i] == valorRaiz) {
                indiceRaiz = i;
                break;
            }
        }

        // Número de elementos na subárvore esquerda
        int numEsquerda = indiceRaiz - inIni;

        // Constrói recursivamente as subárvores
        no.left = reconstruirRec(pre, in, preIni + 1, preIni + numEsquerda, inIni, indiceRaiz - 1);
        no.right = reconstruirRec(pre, in, preIni + numEsquerda + 1, preFim, indiceRaiz + 1, inFim);

        return no;
    }

    public void espelhar() {
        System.out.print("Valores em ordem antes do espelhamento: ");
        inOrder(x -> System.out.print(x + " "));
        System.out.println();

        espelharRecursivo(root);

        System.out.print("Valores em ordem depois do espelhamento: ");
        inOrder(x -> System.out.print(x + " "));
        System.out.println();
    }

    private void espelharRecursivo(TreeNode<T> n) {
        if (n == null) return;

        // Troca as subárvores esquerda e direita
        TreeNode<T> temp = n.left;
        n.left = n.right;
        n.right = temp;

        // Chama recursivamente para as subárvores
        espelharRecursivo(n.left);
        espelharRecursivo(n.right);
    }

    public int contarNosComUmFilho() {
        return contarNosComUmFilho(root);
    }

    private int contarNosComUmFilho(TreeNode<T> n) {
        if (n == null) return 0;
        int count = 0;
        if ((n.left == null && n.right != null) || (n.left != null && n.right == null))
            count = 1;
        return count + contarNosComUmFilho(n.left) + contarNosComUmFilho(n.right);
    }

    public int somaTotal() {
        return somaTotal(root);
    }

    private int somaTotal(TreeNode<T> n) {
        if (n == null) return 0;
        return ((Integer) n.value) + somaTotal(n.left) + somaTotal(n.right);
    }

    public int somaFolhas() {
        return somaFolhas(root);
    }

    private int somaFolhas(TreeNode<T> n) {
        if (n == null) return 0;
        if (n.left == null && n.right == null)
            return (Integer) n.value;
        return somaFolhas(n.left) + somaFolhas(n.right);
    }

    public int diferencaEntreSubarvores() {
        if (root == null) return 0;

        int somaEsquerda = somaTotal(root.left);
        int somaDireita = somaTotal(root.right);

        return Math.abs(somaEsquerda - somaDireita);
    }

    //Nó especial para árvore de expressão aritmética
    public static class ExprNode {
        String value;
        ExprNode left, right;

        ExprNode (String value){
            this.value = value;
        }
    }

    //Construir árvore de expressão aritmética
    public ExprNode construirArvoreExpressao(String expr){
        Stack<ExprNode> operadores = new Stack<>();
        Stack<ExprNode> operandos = new Stack<>();

        for (char c: expr.toCharArray()){
            if (c == ' ' || c == '(') continue;

            if (Character.isDigit(c)){
                operandos.push(new ExprNode(String.valueOf(c)));
            } else if ("+-*/".indexOf(c) >= 0){
                operadores.push(new ExprNode(String.valueOf(c)));
            } else if (c == ')'){
                ExprNode operador = operadores.pop();
                operador.right = operandos.pop();
                operador.left = operandos.pop();
                operandos.push(operador);
            }
        }
        return operandos.pop();
    }

    //Exibir a expressão aritmética original
    public void exibirInfixa(ExprNode node){
        if (node == null) return;
        if (node.left != null) System.out.println("(");
        exibirInfixa(node.left);
        System.out.println(node.value);
        exibirInfixa(node.right);
        if (node.right != null) System.out.println(")");
    }

    //Calcula o resultado da expressão aritmética
    public int avaliarExpressao(ExprNode node){
        if (node == null) return 0;

        if (node.left == null & node.right == null)
            return Integer.parseInt(node.value);

        int esq = avaliarExpressao(node.left);
        int dir = avaliarExpressao(node.right);

        return switch (node.value){
            case "+" -> esq + dir;
            case "-" -> esq - dir;
            case "*" -> esq * dir;
            case "/" -> esq /dir;
            default -> throw new IllegalArgumentException("Operador inválido" + node.value);
        };
    }

    public boolean saoIdenticas(BinarySearchTree<T> outra){
        return saoIdenticas(this.root, outra.root);
    }

    //Verificar se duas árvores são idênticas
    public boolean saoIdenticas(TreeNode<T> a, TreeNode<T> b){
        if ( a == null && b == null) return true;
        if ( a == null || b == null) return false;
        if (compare(a.value, b.value) != 0) return false;

        return saoIdenticas(a.left, b.left) && saoIdenticas(a.right, b.right);
    }

    public void imprimirNivel(int k) {
        System.out.print("Nós do nível " + k + ": ");
        imprimirNivel(root, k);
        System.out.println();
    }

    private void imprimirNivel(TreeNode<T> n, int k) {
        if (n == null) return;
        if (k == 0) {
            System.out.print(n.value + " ");
            return;
        }
        imprimirNivel(n.left, k - 1);
        imprimirNivel(n.right, k - 1);
    }

    public void printArvore(int nivel){
        imprime_estrutura(root, nivel);
    }

    private void imprime_estrutura(TreeNode<T> A, int nivel){
        if(A==null) return;
        imprime_estrutura(A.right, nivel+1);
        for(int i=0 ;i<nivel; ++i)
            System.out.print("\t");
        System.out.println(A.value);
        imprime_estrutura(A.left, nivel+1);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Deque<TreeNode<T>> stack = init();
            private Deque<TreeNode<T>> init(){
                Deque<TreeNode<T>> s = new ArrayDeque<>();
                pushLeft(root,s);
                return s;
            }
            private void pushLeft(TreeNode<T> n, Deque<TreeNode<T>> s){
                while (n != null){
                    s.push(n);
                    n = n.left;
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public T next() {
                if(!hasNext()) throw new NoSuchElementException();
                TreeNode<T> n = stack.pop();
                if(n.right !=null) pushLeft(n.right,stack);
                return n.value;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        boolean[] first = {true};
        inOrder(v-> {
            if(!first[0]) sb.append(", ");
            sb.append(v);
            first[0] = false;
        });
        return sb.append("]").toString();
    }
}