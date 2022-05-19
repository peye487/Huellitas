
package com.huellitas.repositorios;

import com.huellitas.entidades.Zona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepositorio extends JpaRepository<Zona,String>{
    
    @Query(value="SELECT * FROM zona z GROUP BY z.provincia", nativeQuery=true)
    public List<Zona>listarprovincia();

    
}
