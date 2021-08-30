package com.apirest.estudo3.controllers;


import com.apirest.estudo3.models.Produto;
import com.apirest.estudo3.repository.ProdutoRepository;
import com.apirest.estudo3.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.ID;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/produtos")
    public List<Produto> listaProdutos() {
        return produtoService.listaProdutos();
    }

    @GetMapping("/produto/{id}")
    public Produto listaProdutounico(@PathVariable(value = "id") Long id) {
        return produtoService.listaProdutounico(id);
    }

    @GetMapping("/salvo")
    public String showProdutoList(Model model) {
        model.addAttribute("produto");
        return "salvo";
    }

    @PostMapping(value = "/sucesso")
    public String salvar(@ModelAttribute Produto produto, BindingResult errors, Model model) {

        Produto produto1 = produtoService.salvar(produto);
        return "redirect:/salvo";

    }


}
