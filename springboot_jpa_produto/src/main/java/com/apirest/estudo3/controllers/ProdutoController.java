package com.apirest.estudo3.controllers;


import com.apirest.estudo3.models.Produto;
import com.apirest.estudo3.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProdutoController {

    @Autowired
     ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public List<Produto> listaProdutos(){
        return produtoRepository.findAll();
    }

    @GetMapping ("/produtos/{id}")
    public Produto listaProdutoUnico(@PathVariable(value="id") long id){
        return produtoRepository.findById(id);
    }

    @PostMapping("/produto")
    public  Produto salvarProduto(@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

}
