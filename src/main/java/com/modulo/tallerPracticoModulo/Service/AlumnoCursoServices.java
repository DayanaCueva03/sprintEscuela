package com.modulo.tallerPracticoModulo.Service;

import com.modulo.tallerPracticoModulo.Entidad.AlumnoCursoDTO;
import com.modulo.tallerPracticoModulo.Repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlumnoCursoServices {

@Autowired
    private AlumnoRepository alumnoRepository;

public List<AlumnoCursoDTO> obtenerAlumnosConCursos() {
    List<Object[]> alumnocursos = alumnoRepository.obtenerAlumnosConCursos();

    List<AlumnoCursoDTO>  alumnos= new ArrayList<>();

    // Si la lista de datos no está vacía, procesamos cada registro
    for (Object[] registro : alumnocursos) {
        // Extraemos la información y la agregamos a la lista
        alumnos.add(extraerInformacion(registro));
    }

    return alumnos;

}

    private AlumnoCursoDTO extraerInformacion(Object[] registros) {
        AlumnoCursoDTO alumnos = new AlumnoCursoDTO();


        if (registros != null && registros.length > 0) {

            if (registros[0] instanceof Long) {
                alumnos.setId((Long) registros[0]);
            }

            if (registros[1] instanceof String) {
                alumnos.setNombre((String) registros[1]);
            }
            if (registros[2] instanceof String) {
                alumnos.setApellido((String) registros[2]);
            }

            if (registros[3] instanceof String) {
                alumnos.setNombreCurso((String) registros[3]);
            }

            if (registros[4] instanceof String) {
                alumnos.setDescripcion((String) registros[4]);
            }

        }
        return alumnos;
    }








}
