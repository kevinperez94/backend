package com.portfolio.kevinperez.Service;

import com.portfolio.kevinperez.Entity.Persona;
import com.portfolio.kevinperez.Repository.IPersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImpPersonaService {

    @Autowired
    IPersonaRepository ipersonaRepository;

    public List<Persona> getPersona() {
        List<Persona> persona = ipersonaRepository.findAll();
        return persona;
    }

    public void savePersona(Persona persona) {
        ipersonaRepository.save(persona);
    }

    public void deletePersona(Long id) {
        ipersonaRepository.deleteById(id);
    }

    public Persona findPersona(Long id) {
        Persona persona = ipersonaRepository.findById(id).orElse(null);
        return persona;
    }

}
