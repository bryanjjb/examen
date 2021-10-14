
package com.example.ep.controller;

import com.example.ep.entity.Categoria;
import com.example.ep.entity.Producto;
import com.example.ep.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.ep.repository.ProductoRepository;


@Controller
public class CategoriaController {
   @Autowired
     private CategoriaRepository categoriaRepository;
    @RequestMapping("/")
    public String mensaje(Model model){
        model.addAttribute("mensaje", "Bienvenidos");
        return "index";
    }
   
    @RequestMapping("/categorias")
    public String post(Model model){
        model.addAttribute("categoria", categoriaRepository.findAll());
        return "categoria";
    }
    @RequestMapping("/formc")
    public String create(Model model) {
        return "add";
    }
    @RequestMapping("/addc")
    public String guardar(@RequestParam String Nombre,  Model model) {
       Categoria categoria = new Categoria();
       categoria.setNombre(Nombre);
        System.out.println("sout:"+categoria.getNombre());
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }
    @RequestMapping("/del/{idc}")
    public String delete(@PathVariable(value="id") Long id) {
        System.out.println("ID: "+id);
       Categoria categoria = categoriaRepository.findById(id).orElse(null);
       categoriaRepository.delete(categoria);
        return "redirect:/categorias";
    }
    @RequestMapping("/edit/{idc}")
    public String edit(@PathVariable(value="id") Long id, Model model) {
        model.addAttribute("producto", categoriaRepository.findById(id).orElse(null));
        return "edit";
    }
    @RequestMapping("/updatec")
    public String update(@RequestParam Long id, @RequestParam String Nombre) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        categoria.setNombre(Nombre);
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }
}
