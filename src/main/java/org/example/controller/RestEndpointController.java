package org.example.controller;

import org.example.converter.EntityConverter;
import org.example.entity.CocheEntity;
import org.example.entity.Usuario;
import org.example.entity.Ventas;
import org.example.service.ServiceCoches;
import org.openapitools.api.RestEndpointControllerApi;
import org.openapitools.model.Coche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RestEndpointController implements RestEndpointControllerApi {

    @Autowired
    private ServiceCoches serviceCoches;

    @Autowired
    EntityConverter converter;

    //GET
    @Override
    public ResponseEntity<Object> getCochePorMatriculaUsingGET(String matricula) {

        Coche coche = serviceCoches.getCochePorMatricula(matricula);

        if (coche.getMatricula() != null) {
            return ResponseEntity.status(HttpStatus.OK).body(coche);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Object> getCochesPorColorUsingGET(String color) {
        List<Coche> listaCoches = serviceCoches.getCochesPorColor(color);
        if (!listaCoches.isEmpty()) {
            return ResponseEntity.ok(listaCoches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Object> getCochesPorMarcaUsingGET(String marca) {
        List<Coche> listaCoches = serviceCoches.getCochesPorMarca(marca);
        if (!listaCoches.isEmpty()) {
            return ResponseEntity.ok(listaCoches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Object> getCochesPorModeloUsingGET(String modelo) {
        List<Coche> listaCoches = serviceCoches.getCochesPorModelo(modelo);
        if (!listaCoches.isEmpty()) {
            return ResponseEntity.ok(listaCoches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Object> getCochesPorAnioUsingGET(Integer year) {
        List<Coche> listaCoches = serviceCoches.getCochesPorAnio(year);
        if (!listaCoches.isEmpty()) {
            return ResponseEntity.ok(listaCoches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Object> getCochesPorPrecioUsingGET(Double precio) {
        List<Coche> listaCoches = serviceCoches.getCochesPorPrecio(precio);
        if (!listaCoches.isEmpty()) {
            return ResponseEntity.ok(listaCoches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //POST

    //create
    @Override
    public ResponseEntity<Object> crearCocheUsingPOST(Map<String, String> requestBody) {
        try {
            String marca = requestBody.get("marca");
            String modelo = requestBody.get("modelo");
            String color = requestBody.get("color");
            Integer year = Integer.parseInt(requestBody.get("anioFabricacion"));
            Double precio = Double.parseDouble(requestBody.get("precio"));

            CocheEntity nuevoCoche = serviceCoches.crearCoche(marca, modelo, color, year, precio);

            if (nuevoCoche.getMatricula() != null) {
                return ResponseEntity.status(HttpStatus.OK).body(nuevoCoche);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //update
    @Override
    public ResponseEntity<Object> updtaeCocheUsingPOST(Coche coche) {

        if (serviceCoches.getCochePorMatricula(coche.getMatricula()).getMatricula() != null) {
            serviceCoches.updateCoche(coche);
            return ResponseEntity.status(HttpStatus.OK).body(coche);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    //PATCH
    @Override
    public ResponseEntity<Object> updateColorCocheUsingPATCH(String matricula, Map<String, String> requestBody) {

        if (serviceCoches.getCochePorMatricula(matricula).getMatricula() != null) {
            String nuevoColor = requestBody.get("color");
            serviceCoches.updateColorCoche(matricula, nuevoColor);
            return ResponseEntity.status(HttpStatus.OK).body(serviceCoches.getCochePorMatricula(matricula));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Object> updatePrecioCocheUsingPATCH(String matricula, Map<String, String> requestBody) {

        if (serviceCoches.getCochePorMatricula(matricula).getMatricula() != null){
            Double nuevoPrecio = Double.parseDouble(requestBody.get("precio"));
            serviceCoches.updatePrecioCoche(matricula, nuevoPrecio);
            return ResponseEntity.status(HttpStatus.OK).body(serviceCoches.getCochePorMatricula(matricula));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Object> deleteCocheUsingDELETE(String matricula){
        if(serviceCoches.getCochePorMatricula(matricula).getMatricula() != null){
            Coche coche = serviceCoches.getCochePorMatricula(matricula);
            serviceCoches.deleteCoche(matricula);
            return ResponseEntity.status(HttpStatus.OK).body(coche);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping("/coches/marca/{marca}/modelo/{modelo}")
    public ResponseEntity<Object> getCochesPorMarcaYModelo(@PathVariable String marca, @PathVariable String modelo ){
        List<CocheEntity> listaCoches = serviceCoches.getCochesPorMarcaYModelo(marca, modelo);
        if(listaCoches.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.ok(listaCoches);
        }

    }

    @RequestMapping("/coches/marca-o-precio/{marca}/{precio}")
    public ResponseEntity<Object> getCochesPorMarcaOPrecio(@PathVariable String marca, @PathVariable Double precio){
        if (marca == null && precio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Coche> listaCoches;

        if (marca != null && precio != null) {
            listaCoches = serviceCoches.getCochesPorMarcaOPrecio(marca, precio);
        } else if (marca != null) {
            listaCoches = serviceCoches.getCochesPorMarca(marca);
        } else {
            listaCoches = serviceCoches.getCochesPorPrecio(precio);
        }
        if (listaCoches.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(listaCoches);
        }
    }

    @PostMapping("/coches/venta/matricula/{matricula}/idUsuario/{idUsuario}")
    public ResponseEntity<Object> venderCoche(@PathVariable String matricula, @PathVariable int idUsuario){
        if(serviceCoches.getCochePorMatricula(matricula).getMatricula() == null || serviceCoches.getUsuarioPorId(idUsuario) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else if(serviceCoches.cocheVendido(matricula)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            Coche coche = serviceCoches.getCochePorMatricula(matricula);
            Usuario usuario = serviceCoches.getUsuarioPorId(idUsuario);
            Ventas venta = serviceCoches.venderCoche(coche, usuario);
            return ResponseEntity.ok(venta);
        }
    }
}
