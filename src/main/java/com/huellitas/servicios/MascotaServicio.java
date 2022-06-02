package com.huellitas.servicios;

import com.huellitas.entidades.ContactoMascota;
import com.huellitas.entidades.Foto;
import com.huellitas.entidades.Mascota;
import com.huellitas.entidades.Zona;
import com.huellitas.enums.EstadoMascota;
import com.huellitas.repositorios.MascotaRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MascotaServicio {
    @Autowired
    private MascotaRepositorio mascotaRepositorio;
    
    @Autowired
    private ContactoMascotaServicio contactoMascotaServicio;
    
    @Autowired
    private FotoServicio fotoServicio;

    public Mascota buscarPorId(String id) throws Exception {
        Optional<Mascota> respuesta = mascotaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Mascota mascota = respuesta.get();
            return mascota;
        } else {
            throw new Exception("No se encontro la mascota solicitada");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Mascota crear(MultipartFile archivo, String sexo, String tipo, Integer edad, String raza, String observaciones, String provincia, String idContacto) throws Exception {
        validar(sexo, tipo, edad, raza);
        Mascota mascota = new Mascota();
        mascota.setEdad(edad);
        mascota.setObservaciones(observaciones);
        mascota.setRaza(raza);
        mascota.setSexo(sexo);
        mascota.setTipo(tipo);
        mascota.setZona(provincia);
        
        ContactoMascota contacto = contactoMascotaServicio.buscarPorId(idContacto);
        mascota.setContacto(contacto);
        
        mascota.setFechaAlta(new Date());
        
        Foto foto = fotoServicio.guardar(archivo);
        mascota.setFoto(foto);
        
        mascota.setEstadoMascota(EstadoMascota.DISPONIBLE);

        //String passEncriptado = new BC
        return mascotaRepositorio.save(mascota);
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminarMascota(String id) throws Exception {
        Mascota mascota = buscarPorId(id);
        
        mascota.setFechaBaja(new Date());
        mascotaRepositorio.save(mascota);
    }

    @Transactional(rollbackFor = Exception.class)
    public void modificarMascota(String id, String sexo, String tipo, Integer edad, String raza, String observaciones) throws Exception {
        validar(sexo, tipo, edad, raza);

        Mascota mascota = buscarPorId(id);/*se llama directamente el metodo buscar id para no volver a usar el optional etc*/
        
        mascota.setEdad(edad);
        mascota.setObservaciones(observaciones);
        mascota.setRaza(raza);
        mascota.setSexo(sexo);
        mascota.setTipo(tipo);

    }

    @Transactional(rollbackFor = Exception.class)
    public void habilitarMascota(String id) throws Exception {
        
        Mascota mascota = buscarPorId(id);
        
        mascota.setFechaAlta(new Date());
        mascota.setFechaBaja(null);
        mascotaRepositorio.save(mascota);
      
    }
    
    @Transactional(readOnly = true)
    public List<Mascota> listar(){
       return mascotaRepositorio.findAll();
    }
   
   @Transactional(rollbackFor = Exception.class)
    public void adoptarMascota(String id) throws Exception{ /* este metodo generaria la adopcion de la mascota */

        Mascota mascota = buscarPorId(id);
        
        mascota.setFechaBaja(new Date());
        mascota.setEstadoMascota(EstadoMascota.ADOPTADO);
        
        mascotaRepositorio.save(mascota);
    }
    
    @Transactional(readOnly = true)
    public List<Mascota>listaMascotasDisponibles(){
        List<Mascota> mascotasDisponibles = mascotaRepositorio.listarMascotasDisponibles();
        return mascotasDisponibles;
    }
    
    @Transactional(readOnly = true)
    public List<Mascota> filtroMascotas(String tipo){
        List<Mascota> mascotas = mascotaRepositorio.filtroMascota(tipo);
        return mascotas;
    }


    private void validar(String sexo, String tipo, Integer edad, String raza) throws Exception {
        if (sexo == null || sexo.isEmpty()) {
            throw new Exception("El sexo no puede ser nulo");
        }

        if (tipo == null || tipo.isEmpty()) {
            throw new Exception("El tipo no puede ser nulo");
        }

        if (raza == null || raza.isEmpty()) {
            throw new Exception("La raza no puede ser nula");
        }

        if (edad == null || edad <= 0) {
            throw new Exception("La edad no puede ser nula o menor a cero");
        }
    }

}
