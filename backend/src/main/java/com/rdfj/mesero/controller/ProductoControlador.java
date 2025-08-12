package com.rdfj.mesero.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdfj.mesero.dto.ProductoDTO;
import com.rdfj.mesero.entity.Producto;
import com.rdfj.mesero.mapper.ProductoMapper;
import com.rdfj.mesero.repository.RepositorioProducto;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/productos")
public class ProductoControlador {

    @Autowired
    private RepositorioProducto repositorioProducto;

    // Añadir producto
    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody ProductoDTO productoDTO){
        try {
            Producto producto = ProductoMapper.productoDTOToProducto(productoDTO);
            Producto nuevoProducto = repositorioProducto.save(producto);
            ProductoDTO nuevProductoDTO = ProductoMapper.productoToDTO(nuevoProducto);
            return ResponseEntity.ok(nuevProductoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear producto"+e.getMessage());
        }
    }

    // Eliminar producto
    @DeleteMapping("/eliminar/{nombreProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable String nombreProducto){
        try {
            Producto producto = repositorioProducto.findByNombre(nombreProducto);
            if (producto == null) {
                return ResponseEntity.status(404).body("Producto no encontrado");
            }
            repositorioProducto.delete(producto);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar producto" +e.getMessage());
        }
    }

    // Buscar por nombre
    @GetMapping("/{nombreProducto}")
    public ResponseEntity<?> buscarNombre(@PathVariable String nombreProducto){
        try {
            Producto producto = repositorioProducto.findByNombre(nombreProducto);
            ProductoDTO productoDTO = ProductoMapper.productoToDTO(producto);
            return ResponseEntity.ok(productoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al bscar producto" +e.getMessage());
        }
    }

    // Editar producto
    @PutMapping("/editar/{nombreProducto}")
    public ResponseEntity<?> editar(@PathVariable String nombreProducto, @RequestBody ProductoDTO productoDTO){
        try {
            Producto producto = repositorioProducto.findByNombre(nombreProducto);
            if (producto == null) {
                return ResponseEntity.status(404).body("Producto no encontrado");
            }

            producto.setNombre(productoDTO.getNombre());
            producto.setCategoria(productoDTO.getCategoria());
            producto.setPrecio(productoDTO.getPrecio());

            Producto productoActualizado = repositorioProducto.save(producto);

            ProductoDTO productoActualizadoDTO = ProductoMapper.productoToDTO(productoActualizado);
            
            return ResponseEntity.ok(productoActualizadoDTO);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al editar producto: " +e.getMessage());
        }        
    }

    // Mostrar todos
    @GetMapping
    public ResponseEntity<?> mostrarTodos(){
        try {
            List<Producto> productos = repositorioProducto.findAll();
            if (productos.isEmpty()) {
                return ResponseEntity.status(404).body("No existen productos");
            }

            List<ProductoDTO> productosDto = productos.stream()
                .map(ProductoMapper::productoToDTO)
                .collect(Collectors.toList());

            return ResponseEntity.ok(productosDto);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al buscar productos" +e.getMessage());
        }
    }
    
}
