package org.example.api;

import org.example.entity.Matricula;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "matriculaService", url = "https://api.generadordematriculas.com/v1")
public interface MatriculaService {
    @GetMapping("/generate-license-plate/es")
    Matricula getMatriculaDisponible();
}

