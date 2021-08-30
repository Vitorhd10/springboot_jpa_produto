package com.apirest.estudo3.services;


import com.apirest.estudo3.models.Produto;
import com.apirest.estudo3.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> listaProdutos() {
        return produtoRepository.findAll();
    }

    public Produto listaProdutounico(Long id) {
        return produtoRepository.findById(id).get();
    }

    public Produto salvar(Produto produto) {
        Produto produto1 = produtoRepository.save(produto);
        return produto1;
    }

}
