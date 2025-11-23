📘 Lista 06 – Árvores Binárias

Disciplina: Estrutura de Dados Orientada a Objetos
Curso: Análise e Desenvolvimento de Sistemas
Data de entrega: 15/11/2025

Este repositório contém a implementação completa da Lista 06 sobre Árvores Binárias, incluindo operações avançadas, reconstrução de árvores, espelhamento, avaliação de expressões, e verificação de igualdade estrutural entre árvores.

📂 Conteúdo do Projeto

O projeto utiliza a classe BinarySearchTree<T> (BST) como base, estendida com implementações solicitadas na lista.

Abaixo, descrevo cada requisito da atividade e onde está implementado no código.

✅ 1. Funções adicionais
🔹 1.1 Exibir os nós folha em ordem crescente

Implementado com caminhamento in-order e filtragem de folhas.

🔹 1.2 Contar quantos nós têm exatamente um filho

Varredura recursiva verificando (left == null XOR right == null).

🔹 1.3 Imprimir todos os nós no nível k

Função recursiva que decrementa o nível até k == 0.

✅ 2. Adaptações no código
🔹 2.1 Calcular a altura da árvore (recursivo)

Implementação clássica:
altura = 1 + max(altura_esq, altura_dir).

🔹 2.2 Determinar se a árvore é estritamente binária

Uma árvore é estritamente binária quando todos os nós têm 0 ou 2 filhos.

🔹 2.3 Determinar se a árvore é completa

Verificação usando índice de nós ou BFS nível por nível.

✅ 3. Gerar o espelho da árvore

Função recursiva que troca esquerda e direita de cada nó, sem criar nova árvore.

O programa exibe:

Caminhamento central antes

Caminhamento central depois

✅ 4. Cálculos com valores inteiros

Funções implementadas:

Soma de todos os valores

Soma dos nós folha

Diferença entre as somas das subárvores esquerda e direita da raiz

✅ 5. Reconstrução da árvore a partir das sequências

Dadas:

Pré-ordem

Em-ordem

A árvore é reconstruída usando funções auxiliares recursivas.

Após reconstruir, o programa exibe o caminhamento pós-ordem.

✅ 6. Árvore de expressão aritmética

Dada uma expressão totalmente parentizada, como:

((A+B)*(C-D))


O código:

Monta automaticamente a árvore binária da expressão

Exibe a expressão em ordem infixa

Calcula o resultado se os operandos forem inteiros

Também foi implementado suporte para operadores como + - * /.

✅ 7. Verificar se duas árvores são idênticas

A função retorna:

1 → árvores idênticas

0 → árvores diferentes

Critérios:

mesma forma

mesmos valores nos mesmos nós

▶️ Como executar

Clone o repositório:

git clone https://github.com/RGPaix/Lista-06-Arvores-Binarias


Abra no IntelliJ, Eclipse ou VS Code com plugin Java.

Execute a classe principal:

Main_Ex06


ou similar, conforme nome usado no projeto.

🧪 Tecnologias Utilizadas

Java

Programação Orientada a Objetos

Estruturas de Dados (Árvore Binária / BST)

Recursão

📑 Organização do código
src/
├── org.example/
│    ├── Main_Ex06.java
│    └── classes/
│          ├── BinarySearchTree.java
│          ├── Node.java
│          └── ExpressionTree.java

👨‍🏫 Disciplina

Este projeto foi desenvolvido como parte da disciplina:

Estrutura de Dados Orientada a Objetos,
PUC Goiás.

📄 Licença

Este repositório é apenas para fins acadêmicos.