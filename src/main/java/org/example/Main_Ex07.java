package org.example;

import org.example.classes.BinarySearchTree;

import java.util.Arrays;

public class Main_Ex07 {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();

        for (int v : Arrays.asList(9, 7, 15, 3, 0, 10, 19, 14, 1, 5))
            bst1.add(v);

        for (int v : Arrays.asList(9, 7, 15, 3, 0, 10, 19, 14, 5))
            bst2.add(v);

        System.out.println("Árvore 1:");
        bst1.printArvore(0);

        System.out.println("Árvore 2:");
        bst2.printArvore(0);

        System.out.println("São idênticas? (1 - sim / 0 - não): " + (bst1.saoIdenticas(bst2) ? 1 : 0));

        bst1.remove(1);

        System.out.println("Após alteração (remoção do 1). São idênticas? (1 - sim / 0 - não): " + (bst1.saoIdenticas(bst2) ? 1 : 0));
    }
}
