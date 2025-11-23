package org.example;

import org.example.classes.*;

public class Main_Ex06 {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        String expressao = "((3 + 5) * (2 - 1))";
        System.out.println("Expressão: " + expressao);

        BinarySearchTree.ExprNode raiz = bst.construirArvoreExpressao(expressao);

        System.out.println("Forma infixa: ");
        bst.exibirInfixa(raiz);
        System.out.println();

        int resultado = bst.avaliarExpressao(raiz);
        System.out.println("Resultado: " + resultado);
    }
}
