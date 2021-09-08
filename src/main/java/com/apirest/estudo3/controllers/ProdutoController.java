package com.apirest.estudo3.controllers;

import com.apirest.estudo3.models.Produto;
import com.apirest.estudo3.repository.ProdutoRepository;
import com.apirest.estudo3.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Id;
import java.util.List;

@Controller
@RestController
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/salvar")
    private ModelAndView listProduto(@ModelAttribute Produto produto) {
        ModelAndView mv = new ModelAndView("salvo");
        List<Produto> produtos = this.produtoService.listaProdutos();
        mv.addObject("produto", produtos);
        return mv;
    }


    @PostMapping(value = "/salvar")
    public ModelAndView salvar(@ModelAttribute Produto produto) {
        ModelAndView mv = new ModelAndView("salvo");
        Produto produto1 = produtoService.salvar(produto);
        List<Produto> produtos = this.produtoService.listaProdutos();
        mv.addObject("produto", produtos);
        return mv;
    }

    @GetMapping("/salvo")
    public String salvo(Model model) {
        return "salvo";
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

    @GetMapping("/salvar/{id}")
    public ModelAndView edit(@PathVariable long id, Produto produto) {
        Produto produtos = this.produtoService.listaProdutoUnico(id);

        ModelAndView mv = new ModelAndView("/edit");
        mv.addObject("produtoId", produtos.getId());
        return mv;
    }

    @PostMapping("/salvar/{id}")
    public ModelAndView update(@PathVariable long id) {
        ModelAndView mv = new ModelAndView("salvo");
        Produto produtos = this.produtoService.listaProdutoUnico(id);
        return mv;
    }

    @GetMapping("salvar/{id}/delete")
    public ModelAndView delete(@PathVariable("id") Model model, long id) {
        ModelAndView mv = new ModelAndView("salvo");
        this.produtoService.delete(id);
        return mv ;
    }


}