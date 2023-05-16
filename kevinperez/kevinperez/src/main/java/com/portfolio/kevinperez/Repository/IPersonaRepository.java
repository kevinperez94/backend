package com.portfolio.kevinperez.Repository;

import com.portfolio.kevinperez.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long>{
    public Optional<Persona> findByNombre(String nombre);

    public boolean existsByNombre(String nombre);
}
