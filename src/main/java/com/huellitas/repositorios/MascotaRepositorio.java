
package com.huellitas.repositorios;

import com.huellitas.entidades.Mascota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota,String> {
    
    @Query("SELECT m FROM Mascota m WHERE m.estadoMascota LIKE 'disponible'")
    public List<Mascota>listarMascotasDisponibles();

    
    
    
}
