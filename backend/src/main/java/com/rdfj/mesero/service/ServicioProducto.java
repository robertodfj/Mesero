package com.rdfj.mesero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Inventario;
import com.rdfj.mesero.entity.InventarioProducto;
import com.rdfj.mesero.entity.Producto;
import com.rdfj.mesero.entity.Usuario;
import com.rdfj.mesero.repository.RepositorioProducto;
import com.rdfj.mesero.repository.RepositorioUsuario;

@Service
public class ServicioProducto {
    
    @Autowired
    private RepositorioProducto repositorioProducto;

    @Autowired 
    private RepositorioUsuario repositorioUsuario;

    // Bar al que pertenece el producto
    private Bar getBar(){
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario usuario = repositorioUsuario.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Bar bar = usuario.getBar();
        if (bar == null) {
            throw new RuntimeException("El usuario no esta asignado a ningun bar");
        }

        return bar;
    }

    // Nuevo Producto
    public Producto añadirProducto(Producto producto){
        Producto nuevoProducto = new Producto();

        Inventario inventario = getBar().getInventario();

        // Crear el InventarioProducto con cantidad 0
        InventarioProducto inventarioProducto = new InventarioProducto();
        inventarioProducto.setInventario(inventario);
        inventarioProducto.setProducto(nuevoProducto);
        inventarioProducto.setCantidad(0);
        inventarioProducto.setUnidadMedida("unidad");
        inventarioProductoRepository.save(inventarioProducto);

        return nuevoProducto;

    }

    // Eliminar producto
    public void eliminarProducto(Integer id){
        Bar bar = getBar();
        Producto producto = repositorioProducto.findById(id)
            .orElseThrow(()-> new RuntimeException("El producto no existe"));
        if (!producto.getBar().equals(bar)) {
            throw new RuntimeException("No puedes eliminar productos de otro bar");
        }
        
        repositorioProducto.delete(producto);
    }

    public Producto buscarPorId(Integer id) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Bar bar = usuario.getBar();

        return repositorioProducto.findByIdAndBar(id, bar).orElse(null);
    }

    // Vet todos los productos
    public List<Producto> verProductos(){
        Bar bar = getBar();
        return repositorioProducto.findAllByBarId(bar.getId());
    }


}
