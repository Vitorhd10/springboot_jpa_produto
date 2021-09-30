package com.apirest.estudo3.controllers;

import com.apirest.estudo3.models.Produto;
import com.apirest.estudo3.repository.ProdutoRepository;
import com.apirest.estudo3.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Id;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RestController
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/produtos")
    public List<Produto> listaProdutos() {
        return this.produtoService.listaProdutos();
    }

    @PostMapping("/produtos")
    public Produto criarProduto(Produto produto) {
        return this.produtoService.salvar(produto);
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Produto produto = produtoService.listaProdutoUnico(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto> updatedProduto(@PathVariable long id, @RequestBody Produto produto){
        produto.setNome(produto.getNome());
        produto.setQuantidade(produto.getQuantidade());
        produto.setValor(produto.getValor());

        Produto updatedProduto = produtoService.salvar(produto);
        return ResponseEntity.ok(updatedProduto);
    }

    @GetMapping("/salvar")
    private ModelAndView listProduto(@ModelAttribute Produto produto) {
        ModelAndView mv = new ModelAndView("salvo");
        List<Produto> produtos = this.produtoService.listaProdutos();
        mv.addObject("produto", produtos);
        return mv;
    }


    @PostMapping(value = "/salvar")
    public ModelAndView salvar(@ModelAttribute Produto produto) {
        Produto produto1 = produtoService.salvar(produto);
        ModelAndView mv = new ModelAndView("salvo");
        List<Produto> produtos = this.produtoService.listaProdutos();
        mv.addObject("produto", produtos);
        return mv;
    }

    @GetMapping(value = "/index")
    public ModelAndView newProduto() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/")
    public ModelAndView formIndex(Model model) {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/salvar/{id}/edit")
    public ModelAndView edit(@PathVariable long id, Produto produto) {
        Produto produtos = this.produtoService.listaProdutoUnico(id);
        ModelAndView mv = new ModelAndView("/edit");

        mv.addObject("produtoId", produtos.getId());
        mv.addObject("produtoNome", produtos.getNome());
        mv.addObject("produtoValor", produtos.getValor());
        mv.addObject("produtoQuantidade", produtos.getQuantidade());
        return mv;
    }

    @PostMapping("/salvar/{id}/edit")
    public ModelAndView update(@PathVariable long id, Produto produto) {
        Produto produto1 = produtoService.salvar(produto);
        ModelAndView mv = new ModelAndView("salvo");
        List<Produto> produtos = this.produtoService.listaProdutos();
        mv.addObject("produto", produto);
        return mv;
    }

    @GetMapping("salvar/{id}/delete")
    public ModelAndView delete(@PathVariable long id, Produto produto) {
        this.produtoService.delete(id);
        ModelAndView mv = new ModelAndView("salvo");
        List<Produto> produtos = this.produtoService.listaProdutos();
        mv.addObject("produto", produtos);
        return mv;
    }


}
