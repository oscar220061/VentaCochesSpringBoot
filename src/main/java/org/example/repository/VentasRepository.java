package org.example.repository;

import org.example.entity.Ventas;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "ventasRepository")
public interface VentasRepository extends JpaRepository<Ventas, Integer> {
    boolean existsByMatricula(String matricula);
}
