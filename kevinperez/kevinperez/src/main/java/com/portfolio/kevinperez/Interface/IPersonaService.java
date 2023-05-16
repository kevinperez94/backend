package com.portfolio.kevinperez.Interface;

import com.portfolio.kevinperez.Entity.Persona;
import java.util.List;

public interface IPersonaService {

    //traer una lista de Personas
    public List<Persona> getPersona();

    //guardar un objeto de tipo Persona
    public void savePersona(Persona persona);

    //eliminar un objeto buscado por Id
    public void deletePersona(Long id);

    //buscar una persona por Id
    public Persona findPersona(Long id);
}
