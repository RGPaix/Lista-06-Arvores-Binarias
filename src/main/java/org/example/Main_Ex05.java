package org.example;

import org.example.classes.BinarySearchTree;

import java.util.Arrays;

public class Main_Ex05 {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int v : Arrays.asList(9, 7, 15, 3, 1, 10, 19, 14, 2, 5)) {
            bst.add(v);
        }
        System.out.println("Valores em Ordem: " + bst);
        System.out.println("Tamanho: " + bst.size());
        System.out.println("Altura: " + bst.height());
        System.out.println("Máximo: " + bst.max());
        System.out.println("Mínimo: " + bst.min());
        System.out.print("Pré Ordem: ");
        bst.preOrder(x -> System.out.print(x + " "));
        System.out.println();
        System.out.print("Pós Ordem: ");
        bst.postOrder(x -> System.out.print(x + " "));
        System.out.println();
        System.out.print("Em largura (BFS): ");
        bst.levelOrder(x -> System.out.print(x + " "));
        System.out.println();

        System.out.println("Árvore original:");
        bst.printArvore(0);

        int[] preOrdem = {9, 7, 3, 1, 2, 5, 15, 10, 14, 19};
        int[] emOrdem = {1, 2, 3, 5, 7, 9, 10, 14, 15, 19};

        System.out.println("Reconstruindo a árvore...");
        bst.reconstruirEExibirPosfixado(preOrdem, emOrdem);

        System.out.println("\nÁrvore reconstruída:");
        bst.printArvore(0);
    }
}
