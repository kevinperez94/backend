package com.portfolio.kevinperez.Controller;

import com.portfolio.kevinperez.Dto.dtoEducacion;
import com.portfolio.kevinperez.Entity.Educacion;
import com.portfolio.kevinperez.Security.Controller.Mensaje;
import com.portfolio.kevinperez.Service.Seducacion;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CEducacion {

    @Autowired
    Seducacion sEducacion;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = sEducacion.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = sEducacion.getOne(id).get();
        return ResponseEntity.ok(educacion);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable("id") int id) {
        if (!sEducacion.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sEducacion.delete(id);
        return ResponseEntity.ok(new Mensaje("Educacion eliminada"));
    }

    @PostMapping("/create")
    public ResponseEntity<Mensaje> create(@RequestBody dtoEducacion dtoeducacion) {
        if (StringUtils.isBlank(dtoeducacion.getNombreE())) {
            return ResponseEntity.badRequest().body(new Mensaje("El nombre es obligatorio"));
        }
        if (sEducacion.existsByNombreE(dtoeducacion.getNombreE())) {
            return ResponseEntity.badRequest().body(new Mensaje("Ese nombre ya existe"));
        }

        Educacion educacion = new Educacion(
                dtoeducacion.getNombreE(), dtoeducacion.getDescripcionE()
        );
        sEducacion.save(educacion);
        return ResponseEntity.ok(new Mensaje("Educacion creada"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoeducacion) {
        if (!sEducacion.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        if (sEducacion.existsByNombreE(dtoeducacion.getNombreE()) && sEducacion.getByNombreE(dtoeducacion.getNombreE()).get().getId() != id) {
            return ResponseEntity.badRequest().body(new Mensaje("Ese nombre ya existe"));
        }
        if (StringUtils.isBlank(dtoeducacion.getNombreE())) {
            return ResponseEntity.badRequest().body(new Mensaje("El campo no puede estar vacio"));
        }

        Educacion educacion = sEducacion.getOne(id).get();

        educacion.setNombreE(dtoeducacion.getNombreE());
        educacion.setDescripcionE(dtoeducacion.getDescripcionE());

        sEducacion.save(educacion);

        return ResponseEntity.ok(new Mensaje("Educacion actualizada"));
    }
}
