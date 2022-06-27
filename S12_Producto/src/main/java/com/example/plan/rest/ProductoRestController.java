/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.plan.rest;

import com.example.plan.entity.Producto;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.plan.serviceImpl.ProductoService;



/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/producto")
public class ProductoRestController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/all")
    public List<Producto> getPosts() {
        return productoService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getPost(@PathVariable int id) {
        Producto producto = productoService.read(id);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) {        
        productoService.delete(id);
    }
    @PostMapping("/add")
    public Producto addPost(@RequestBody Producto producto, @RequestParam("imagen") MultipartFile imagen) {  
        if(imagen.isEmpty()){
            //Path dirimg = Paths.get("src//main//resources//static/images");
            //String ruta = dirimg.toFile().getAbsolutePath();
            //String ruta = "C://Users//Lisbeth//Pictures//images//files2";
            String ruta = "C://productos";
            
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutacompleta = Paths.get(ruta+"//"+imagen.getOriginalFilename());
                Files.write(rutacompleta, bytesImg);
                producto.setImagen(imagen.getOriginalFilename());
            } catch (IOException e) {
                System.out.println("Error: "+e);
            }
        }
        return productoService.create(producto);
    }
    @PutMapping("/edit")
    public Producto editPost(@RequestBody Producto producto) {  
        return productoService.update(producto);
    }
}
