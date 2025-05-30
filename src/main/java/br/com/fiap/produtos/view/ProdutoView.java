package br.com.fiap.produtos.view;

import br.com.fiap.produtos.model.Categoria;
import br.com.fiap.produtos.model.Produto;
import br.com.fiap.produtos.repository.ProdutoCollectionRepository;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoView {

    public static Produto select() {
        Produto ret = (Produto) JOptionPane.showInputDialog(
                null,
                "Selecione um produto",
                "Menu",
                JOptionPane.QUESTION_MESSAGE,
                null,
                ProdutoCollectionRepository.findAll().toArray(),
                1
        );
        return ret;
    }

    public static void sucesso() {
        JOptionPane.showMessageDialog(null,
                "Produto salvo com sucesso");
    }

    public static void sucesso(Produto produto) {
        JOptionPane.showMessageDialog(null,
                "Produto " + produto.getNome().toUpperCase() + " salvo com sucesso");
    }

    public static void show(Produto produto) {
        String produtoString = String.format(
                "PRODUTO: " + produto.getNome() + System.lineSeparator() +
                "DESCRIÇÃO: " + produto.getDescricao() + System.lineSeparator() +
                "CATEGORIA: " + produto.getCategoria().toString() + System.lineSeparator() +
                "PREÇO: %,.2f", produto.getPreco()
        );
        JOptionPane.showMessageDialog(null,
                produtoString);
    }

    public static Produto form() {
        Categoria categoria = null;

        do {
            categoria = CategoriaView.select(null);
        } while (categoria == null);

        String nome = "";

        do {
            nome = JOptionPane.showInputDialog("Nome do Produto");

            if (nome.length() < 3) {
                JOptionPane.showMessageDialog(null,
                        "O nome do produto precisa ter no mínimo 3 caracteres");
            }
        } while (nome.equals(""));

        String descricao = "";

        do {
            descricao = JOptionPane.showInputDialog("Descrição do Produto");

            if (descricao.length() < 5) {
                JOptionPane.showMessageDialog(null,
                        "A descrição do produto precisa ter no mínimo 5 caracteres");
            }
        } while (descricao.equals(""));

        double p = 0;

        do {
            try {
                p = Double.parseDouble(JOptionPane.showInputDialog("Preço do Produto"));
            } catch (NumberFormatException e) {
                p = 0;
            }

            if (p <= 0) {
                JOptionPane.showMessageDialog(null,
                        "Valor inválido");
            }
        } while (p <= 0);

        BigDecimal preco = BigDecimal.valueOf(p);

        Produto ret = new Produto();
        ret.setNome(nome)
                .setCategoria(categoria)
                .setDescricao(descricao)
                .setPreco(preco)
                .setDataDeCadastro(LocalDateTime.now());
        return ret;
    }

    public static void update(Produto produto) {
        Categoria categoria = null;

        do {
            categoria = CategoriaView.select(produto.getCategoria());
            produto.setCategoria(categoria);
        } while (categoria == null);

        String nome = "";

        do {
            nome = JOptionPane.showInputDialog("Nome do Produto", produto.getNome());

            if (nome.length() < 3) {
                JOptionPane.showMessageDialog(null,
                        "O nome do produto precisa ter no mínimo 3 caracteres");
            }

            produto.setNome(nome);
        } while (nome.equals(""));

        String descricao = "";

        do {
            descricao = JOptionPane.showInputDialog("Descrição do Produto", produto.getDescricao());

            if (descricao.length() < 5) {
                JOptionPane.showMessageDialog(null,
                        "A descrição do produto precisa ter no mínimo 5 caracteres");
            }

            produto.setDescricao(descricao);
        } while (descricao.equals(""));

        double p = 0;

        do {
            try {
                p = Double.parseDouble(JOptionPane.showInputDialog("Preço do Produto", produto.getPreco()));
            } catch (NumberFormatException e) {
                p = 0;
            }

            if (p <= 0) {
                JOptionPane.showMessageDialog(null,
                        "Valor inválido");
            }
        } while (p <= 0);

        BigDecimal preco = BigDecimal.valueOf(p);
        produto.setPreco(preco);

        sucesso(produto);
        show(produto);
    }

}
