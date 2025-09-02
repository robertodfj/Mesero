package com.rdfj.mesero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdfj.mesero.dto.ProductoDTO;
import com.rdfj.mesero.entity.Producto;
import com.rdfj.mesero.mapper.ProductoMapper;
import com.rdfj.mesero.service.ServicioProducto;

@RestController
@RequestMapping("/producto")
public class ControladorProducto {
    
    @Autowired
    private ServicioProducto servicioProducto;

    // Añadir producto
    @RequestMapping("/crear")
    public ResponseEntity<?> crearProducto(@RequestBody ProductoDTO dto){
        try {

            Producto nuevoProducto = ProductoMapper.dtoToProducto(dto);

            servicioProducto.añadirProducto(nuevoProducto);

            return ResponseEntity.ok("Producto añadido correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al añadir producto: " +e.getMessage());
        }
    }

    // Editar producto
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarProducto(@PathVariable int id, @RequestBody ProductoDTO dto){
        try {
            Producto productoEditado = ProductoMapper.dtoToProducto(dto);

            servicioProducto.editarProducto(id, productoEditado.getNombre(), productoEditado.getPrecioCompraUD(), productoEditado.getPrecioVentaUD(), productoEditado.getCategoria());

            return ResponseEntity.ok("Producto editado correctamente: " + productoEditado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al editar producto: " +e.getMessage());
        }
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable int id){
        try {
            servicioProducto.eliminarProducto(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar producto: " +e.getMessage());
        }
    }
}
