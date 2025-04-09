package br.com.fiap.produtos.repository;

import br.com.fiap.produtos.model.Categoria;
import br.com.fiap.produtos.model.Produto;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ProdutoCollectionRepository {

    private static List<Produto> produtos;

    static {
        produtos = new Vector<>();

        Produto kindle = new Produto();
        kindle.setCategoria(CategoriaCollectionRepository.findById(1L))
                .setNome("kindle")
                .setDescricao("e-reader da Amazon")
                .setDataDeCadastro(LocalDateTime.now())
                .setPreco(BigDecimal.valueOf(899.99));

        Produto iphone = new Produto();
        iphone.setCategoria(CategoriaCollectionRepository.findById(2L))
                .setNome("Iphone 14 Pro Max")
                .setDescricao("Aparelho celular de última geração da Apple")
                .setDataDeCadastro(LocalDateTime.now())
                .setPreco(BigDecimal.valueOf(12999.99));

        Arrays.asList(kindle, iphone).forEach(ProdutoCollectionRepository::save);
    }

    public static List<Produto> findAll() {
        return produtos;
    }

    public static Produto findById(Long id) {
        return produtos.stream()
                .filter(produto -> produto.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Produto> findByCategoria(Categoria categoria) {
        return produtos.stream()
                .filter(produto -> produto.getCategoria().equals(categoria))
                .toList();
    }

    public static Produto save(Produto produto) {
        if (!produtos.contains(produto)) {
            produto.setId((long)produtos.size() + 1);
            produtos.add(produto);
            return produto;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Produto já cadastrado");
            return null;
        }
    }

}
