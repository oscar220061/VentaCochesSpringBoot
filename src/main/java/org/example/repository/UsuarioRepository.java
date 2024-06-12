package org.example.repository;

import org.example.entity.Usuario;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


}
