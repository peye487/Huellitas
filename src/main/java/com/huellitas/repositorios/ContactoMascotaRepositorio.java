
package com.huellitas.repositorios;

import com.huellitas.entidades.ContactoMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoMascotaRepositorio extends JpaRepository<ContactoMascota,String>  {
    
}
