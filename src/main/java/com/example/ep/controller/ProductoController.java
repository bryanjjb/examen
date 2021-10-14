
package com.example.ep.controller;

import com.example.ep.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.ep.repository.ProductoRepository;


@Controller
public class ProductoController {
   @Autowired
    private ProductoRepository productoRepository;
    @RequestMapping("/")
    public String mensaje(Model model){
        model.addAttribute("mensaje", "Bienvenidos");
        return "index";
    }
    @RequestMapping("/productos")
    public String post(Model model){
        model.addAttribute("productos", productoRepository.findAll());
        return "producto";
    }
    @RequestMapping("/formp")
    public String create(Model model) {
        return "add";
    }
    @RequestMapping("/addp")
    public String guardar(@RequestParam String Producto, @RequestParam double Precio,@RequestParam int Stock, Model model) {
        Producto producto = new Producto();
        producto.setPrecio(Precio);
        producto.setStock(Stock);
        System.out.println("sout:"+producto.getPrecio()+"/"+producto.getStock());
        productoRepository.save(producto);
        return "redirect:/productos";
    }
    @RequestMapping("/del/{idp}")
    public String delete(@PathVariable(value="id") Long id) {
        System.out.println("ID: "+id);
        Producto producto = productoRepository.findById(id).orElse(null);
        productoRepository.delete(producto);
        return "redirect:/productos";
    }
    @RequestMapping("/edit/{idp}")
    public String edit(@PathVariable(value="id") Long id, Model model) {
        model.addAttribute("producto", productoRepository.findById(id).orElse(null));
        return "edit";
    }
    @RequestMapping("/updatep")
    public String update(@RequestParam Long id, @RequestParam String Producto, @RequestParam double Precio,@RequestParam int Stock) {
        Producto producto = productoRepository.findById(id).orElse(null);
        producto.setPrecio(Precio);
        producto.setStock(Stock);
        productoRepository.save(producto);
        return "redirect:/productos";
    }
}
