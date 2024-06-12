package org.example.converter;

import org.example.entity.CocheEntity;
import org.openapitools.model.Coche;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {

    public CocheEntity toEntity(Coche coche){
        CocheEntity entity = new CocheEntity(coche.getMatricula(), coche.getMarca(), coche.getModelo(), coche.getColor(), coche.getAnioFabricacion(), coche.getPrecio());
        return entity;


    }public Coche toCoche(CocheEntity entity){
        Coche coche = new Coche();
        coche.setMatricula(entity.getMatricula());
        coche.setMarca(entity.getMarca());
        coche.setModelo(entity.getModelo());
        coche.setColor(entity.getColor());
        coche.setAnioFabricacion(entity.getYear());
        coche.setPrecio(entity.getPrecio());
        return coche;
    }
}
