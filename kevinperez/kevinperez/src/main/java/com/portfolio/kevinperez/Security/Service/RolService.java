
package com.portfolio.kevinperez.Security.Service;

import com.portfolio.kevinperez.Security.Entity.Rol;
import com.portfolio.kevinperez.Security.Enums.RolNombre;
import com.portfolio.kevinperez.Security.Repository.iRolRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolService {
    @Autowired
    iRolRepository irolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return irolRepository.findByRolNonmbre(rolNombre);
    }
    
    public void save(Rol rol){
        irolRepository.save(rol);
    }
}
