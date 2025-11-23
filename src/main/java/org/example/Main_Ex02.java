package org.example;

import org.example.classes.BinarySearchTree;

import java.util.Arrays;

public class Main_Ex02 {
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

        System.out.println("Altura (recursiva): " + bst.alturaRecursiva());
        System.out.println("É estritamente binária? " + bst.ehEstrictamenteBinaria());
        System.out.println("É completa? " + bst.ehCompleta());

        System.out.println();

        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        for (int v : Arrays.asList(8,4,12,2,6,10,14)) {
            bst1.add(v);
        }
        System.out.println("Valores em Ordem: " + bst1);
        System.out.println("Tamanho: " + bst1.size());
        System.out.println("Altura: " + bst1.height());
        System.out.println("Máximo: " + bst1.max());
        System.out.println("Mínimo: " + bst1.min());
        System.out.print("Pré Ordem: ");
        bst1.preOrder(x -> System.out.print(x + " "));
        System.out.println();
        System.out.print("Pós Ordem: ");
        bst1.postOrder(x -> System.out.print(x + " "));
        System.out.println();
        System.out.print("Em largura (BFS): ");
        bst1.levelOrder(x -> System.out.print(x + " "));
        System.out.println();

        System.out.println("Árvore original:");
        bst1.printArvore(0);

        System.out.println("Altura (recursiva): " + bst1.alturaRecursiva());
        System.out.println("É estritamente binária? " + bst1.ehEstrictamenteBinaria());
        System.out.println("É completa? " + bst1.ehCompleta());
    }
}
