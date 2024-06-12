package org.example.repository;

import org.example.entity.CocheEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier(value = "cocheEntityRepository")
public interface CocheEntityRepository extends JpaRepository<CocheEntity,String> {

   @Override
   CocheEntity getById(String s);

   List<CocheEntity> getByColor(String color);

   List<CocheEntity> getByMarca(String marca);

   List<CocheEntity> getByModelo(String modelo);

   List<CocheEntity> getByYear(Integer year);

   List<CocheEntity> getByPrecio(Double precio);

   List<CocheEntity> getByMarcaAndModelo(String marca, String modelo);
}
