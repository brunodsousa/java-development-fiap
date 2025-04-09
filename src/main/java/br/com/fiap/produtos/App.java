package br.com.fiap.produtos;

import br.com.fiap.produtos.model.Categoria;
import br.com.fiap.produtos.model.Produto;
import br.com.fiap.produtos.repository.CategoriaCollectionRepository;
import br.com.fiap.produtos.repository.ProdutoCollectionRepository;
import br.com.fiap.produtos.view.CategoriaView;
import br.com.fiap.produtos.view.Opcao;
import br.com.fiap.produtos.view.OpcaoView;
import br.com.fiap.produtos.view.ProdutoView;

import javax.swing.*;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<Categoria> categorias = CategoriaCollectionRepository.findAll();

        Opcao opcao = null;

        do {
            opcao = OpcaoView.select();

            switch (opcao) {
                case CADASTRAR_CATEGORIA -> cadastrarCategoria();
                case CADASTRAR_PRODUTO -> cadastrarProduto();
                case ALTERAR_PRODUTO -> alterarProduto();
                case CONSULTAR_PRODUTO_POR_ID -> consultarProdutoPorId();
                case CONSULTAR_PRODUTO_POR_CATEGORIA -> consultarProdutoPorCategoria();
            }
        } while (opcao != Opcao.ENCERRAR_SISTEMA);
    }

    private static void cadastrarCategoria() {
        CategoriaView view = new CategoriaView();
        Categoria categoria = view.form();
        CategoriaCollectionRepository.save(categoria.getNome());
        view.sucesso(categoria);
    }

    private static void cadastrarProduto() {
        Produto produto = ProdutoView.form();
        ProdutoCollectionRepository.save(produto);
        ProdutoView.sucesso(produto);
    }

    private static void alterarProduto() {
        Produto produto = ProdutoView.select();
        ProdutoView.update(produto);
    }

    private static void consultarProdutoPorId() {
        Long id = 0L;

        do {
            try {
                id = Long.parseLong(JOptionPane.showInputDialog("Informe o ID do produto"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "ID inválido");
            }
        } while (id <= 0);

        Produto produto = ProdutoCollectionRepository.findById(id);

        if (produto != null) {
            ProdutoView.show(produto);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Produto não encontrado");
        }
    }

    public static void consultarProdutoPorCategoria() {
        Categoria categoria = CategoriaView.select(null);

        List<Produto> produtos = ProdutoCollectionRepository.findByCategoria(categoria);

        if (produtos.size() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Não foram encontrados produtos cadastrados para a categoria " + categoria.getNome());
        }

        produtos.forEach(ProdutoView::show);
    }

}
