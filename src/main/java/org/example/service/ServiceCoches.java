package org.example.service;


import org.example.entity.CocheEntity;
import org.example.entity.Usuario;
import org.example.entity.Ventas;
import org.openapitools.model.Coche;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ServiceCoches {


    //Coche

    Coche getCochePorMatricula(String matricula);

    List<Coche> getCochesPorColor(String color);

    List<Coche> getCochesPorMarca(String marca);

    List<Coche> getCochesPorModelo(String modelo);

    List<Coche> getCochesPorAnio(Integer year);

    List<Coche> getCochesPorPrecio(Double precio);

    CocheEntity crearCoche(String marca, String modelo, String color, Integer year, Double precio);

    void updateCoche(Coche coche);

    void updateColorCoche(String matricula, String color);

    void updatePrecioCoche(String matricula, Double nuevoPrecio);

    void deleteCoche(String matricula);

    List<CocheEntity> getCochesPorMarcaYModelo(String marca, String modelo);

    List<Coche> getCochesPorMarcaOPrecio(String marca, Double precio);


    //Usuario

    Usuario getUsuarioPorId(int id);

    void crearUsuario(Usuario usuario);

    //Ventas
    boolean cocheVendido(String matricula);

    Optional<Ventas> getVentaPorId(int id);

    Ventas venderCoche(Coche coche, Usuario usuario);
}
