package org.example;

import org.example.classes.BinarySearchTree;

import java.util.Arrays;

public class Main_Ex01 {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int v : Arrays.asList(9,7,15,3,1,10,19,14,2,5)){
            bst.add(v);
        }
        System.out.println("Valores em Ordem: "+bst);
        System.out.println("Tamanho: "+bst.size());
        System.out.println("Altura: "+bst.height());
        System.out.println("Máximo: "+bst.max());
        System.out.println("Mínimo: "+bst.min());
        System.out.print("Pré Ordem: ");
        bst.preOrder(x-> System.out.print(x+" "));
        System.out.println();
        System.out.print("Pós Ordem: ");
        bst.postOrder(x-> System.out.print(x+" "));
        System.out.println();
        System.out.print("Em largura (BFS): ");
        bst.levelOrder(x-> System.out.print(x+" "));
        System.out.println();

        System.out.println("Árvore original:");
        bst.printArvore(0);

        bst.exibirFolhasEmOrdem();

        System.out.println("Nós com exatamente um filho: " + bst.contarNosComUmFilho());

        int nivel = 2;
        bst.imprimirNivel(nivel);
    }
}