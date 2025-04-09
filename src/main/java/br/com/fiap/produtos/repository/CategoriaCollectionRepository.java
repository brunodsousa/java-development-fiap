package br.com.fiap.produtos.repository;

import br.com.fiap.produtos.model.Categoria;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class CategoriaCollectionRepository {

    private static List<Categoria> categorias;

    static {
        categorias = new Vector<>();
        String[] categorias = {"Eletrônicos", "Celulares", "Livros", "Games"};
        Arrays.asList(categorias).forEach(CategoriaCollectionRepository::save);
    }

    public static List<Categoria> findAll() {
        return categorias;
    }

    public static Categoria findById(Long id) {
        return categorias.stream()
                .filter(categoria -> categoria.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Categoria> findByNome(String nome) {
        return categorias.stream()
                .filter(categoria -> categoria.getNome().equalsIgnoreCase(nome))
                .toList();
    }

    public static Categoria save(String nome) {
        Categoria categoria = new Categoria(nome);
        if (!categorias.contains(categoria)) {
            categoria.setId((long)categorias.size() + 1);
            categorias.add(categoria);
            return categoria;
        } else {
            JOptionPane.showMessageDialog(null, "Categoria já cadastrada");
            return null;
        }
    }

}
