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
import com.rdfj.mesero.service.ServicioProducto;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/productos")
public class ProductoControlador {

    @Autowired
    private ServicioProducto servicioProducto;

    // Crear producto
    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        try {
            Producto producto = ProductoMapper.productoDTOToProducto(productoDTO);
            Producto nuevoProducto = servicioProducto.añadirProducto(producto);
            return ResponseEntity.ok(ProductoMapper.productoToDTO(nuevoProducto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear producto: " + e.getMessage());
        }
    }

    // Eliminar producto por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {
        try {
            servicioProducto.eliminarProducto(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar producto: " + e.getMessage());
        }
    }

    // Editar producto
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarProducto(@PathVariable Integer id, @Valid @RequestBody ProductoDTO productoDTO) {
        try {
            Producto producto = servicioProducto.buscarPorId(id);
            if (producto == null) {
                return ResponseEntity.status(404).body("Producto no encontrado");
            }

            producto.setNombre(productoDTO.getNombre());
            producto.setCategoria(productoDTO.getCategoria());
            producto.setPrecio(productoDTO.getPrecio());

            Producto actualizado = servicioProducto.añadirProducto(producto);
            return ResponseEntity.ok(ProductoMapper.productoToDTO(actualizado));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al editar producto: " + e.getMessage());
        }
    }

    // Mostrar todos los productos del bar del usuario
    @GetMapping
    public ResponseEntity<?> mostrarTodos() {
        try {
            List<Producto> productos = servicioProducto.verProductos();
            if (productos.isEmpty()) {
                return ResponseEntity.status(404).body("No existen productos en tu bar");
            }
            List<ProductoDTO> productosDto = productos.stream()
                    .map(ProductoMapper::productoToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(productosDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al listar productos: " + e.getMessage());
        }
    }
}
