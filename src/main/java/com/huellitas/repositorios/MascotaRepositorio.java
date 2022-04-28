
package com.huellitas.repositorios;

import com.huellitas.entidades.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota,String> {
    
}
