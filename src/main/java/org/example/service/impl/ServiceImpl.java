package org.example.service.impl;

import org.example.entity.Usuario;
import org.example.entity.Ventas;
import org.example.repository.UsuarioRepository;
import org.example.repository.VentasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.api.MatriculaService;
import org.example.converter.EntityConverter;
import org.example.entity.CocheEntity;
import org.example.entity.Matricula;
import org.example.repository.CocheEntityRepository;
import org.example.service.ServiceCoches;

import org.openapitools.model.Coche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ServiceImpl implements ServiceCoches {
    private static final Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

    @Autowired
    private CocheEntityRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VentasRepository ventasRepository;

    @Autowired
    EntityConverter converter;

    @Autowired
    MatriculaService matriculaService;


    @Override
    public Coche getCochePorMatricula(String matricula) {
        return repository.findById(matricula)
                .map(converter::toCoche)
                .orElse(new Coche());

    }

    @Override
    public List<Coche> getCochesPorColor(String color) {
        ArrayList<Coche> listaCoches = new ArrayList<>();
        for(CocheEntity entity: repository.getByColor(color)){
            listaCoches.add(converter.toCoche(entity));
        }

        return listaCoches;
    }

    @Override
    public List<Coche> getCochesPorMarca(String marca) {
        ArrayList<Coche> listaCoches = new ArrayList<>();
        for(CocheEntity entity: repository.getByMarca(marca)){
            listaCoches.add(converter.toCoche(entity));
        }

        return listaCoches;
    }
    @Override
    public List<Coche> getCochesPorModelo(String modelo) {
        ArrayList<Coche> listaCoches = new ArrayList<>();
        for(CocheEntity entity: repository.getByModelo(modelo)){
            listaCoches.add(converter.toCoche(entity));
        }

        return listaCoches;
    }

    @Override
    public List<Coche> getCochesPorAnio(Integer year) {
        ArrayList<Coche> listaCoches = new ArrayList<>();
        for(CocheEntity entity: repository.getByYear(year)){
            listaCoches.add(converter.toCoche(entity));
        }

        return listaCoches;
    }
    @Override
    public List<Coche> getCochesPorPrecio(Double precio) {
        ArrayList<Coche> listaCoches = new ArrayList<>();
        for(CocheEntity entity: repository.getByPrecio(precio)){
            listaCoches.add(converter.toCoche(entity));
        }

        return listaCoches;
    }



    @Override
    public CocheEntity crearCoche(String marca, String modelo, String color, Integer year, Double precio) {

        Matricula matricula = matriculaService.getMatriculaDisponible();

        if (matricula == null || matricula.getMatriculas() == null || matricula.getMatriculas().isEmpty()) {
            return new CocheEntity();
        }
        String nuevaMatricula = matricula.getMatriculas().get(0);
        if (getCochePorMatricula(nuevaMatricula).getMatricula() == null) {
            CocheEntity nuevoCoche = new CocheEntity(nuevaMatricula, marca, modelo, color, year, precio);
            repository.save(nuevoCoche);
            return nuevoCoche;
        } else {
            return new CocheEntity();
        }
    }

    @Override
    public void updateCoche(Coche coche) {
        CocheEntity cocheEntity = converter.toEntity(coche);
        repository.save(cocheEntity);
    }

    @Override
    public void updateColorCoche(String matricula, String nuevoColor) {
        CocheEntity cocheEntity = repository.getById(matricula);
        cocheEntity.setColor(nuevoColor);
        repository.save(cocheEntity);
    }
    @Override
    public void updatePrecioCoche(String matricula, Double precio) {
        CocheEntity cocheEntity = repository.getById(matricula);
        cocheEntity.setPrecio(precio);
        repository.save(cocheEntity);
    }

    @Override
    public void deleteCoche(String matricula) {
        repository.deleteById(matricula);
    }

    @Override
    public List<CocheEntity> getCochesPorMarcaYModelo(String marca, String modelo){
        return repository.getByMarcaAndModelo(marca, modelo);
    }

    @Override
    public List<Coche> getCochesPorMarcaOPrecio(String marca, Double precio){
        List<CocheEntity> cochesPorMarcaEntity = repository.getByMarca(marca);
        List<CocheEntity> cochesPorPrecioEntity = repository.getByPrecio(precio);
        ArrayList<Coche> listaCoches = new ArrayList<>();
        for(CocheEntity coche : cochesPorMarcaEntity){
            listaCoches.add(converter.toCoche(coche));
        }
        for(CocheEntity coche : cochesPorPrecioEntity){
            listaCoches.add(converter.toCoche(coche));
        }

        return listaCoches.stream().distinct().toList();
    }

    //Usuarios

    @Override
    public Usuario getUsuarioPorId(int id) {
        return usuarioRepository.findById(id).orElse(new Usuario());
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }


    //Ventas
    @Override
    public boolean cocheVendido(String matricula){
        return ventasRepository.existsByMatricula(matricula);
    }

    @Override
    public Optional<Ventas> getVentaPorId(int id) {
        return ventasRepository.findById(id);
    }

    @Override
    public Ventas venderCoche(Coche coche, Usuario usuario) {
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fecha.format(formateador);
        int idUsuario = usuario.getId();
        Ventas venta = new Ventas(coche.getMatricula(), idUsuario, fechaFormateada, coche.getPrecio());
        ventasRepository.save(venta);
        return venta;
    }


}
