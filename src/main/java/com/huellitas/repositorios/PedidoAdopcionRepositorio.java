
package com.huellitas.repositorios;

import com.huellitas.entidades.PedidoAdopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoAdopcionRepositorio extends JpaRepository<PedidoAdopcion,String>{
    
}
