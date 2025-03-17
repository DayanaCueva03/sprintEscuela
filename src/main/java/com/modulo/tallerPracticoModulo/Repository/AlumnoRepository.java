package com.modulo.tallerPracticoModulo.Repository;

import com.modulo.tallerPracticoModulo.Entidad.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @Query(value = "SELECT \n" +
            "    al.id AS alumno_id, \n" + //0
            "    al.nombre AS alumno_nombre,\n" + //1
            "    al.apellido AS alumno_apellido,\n" + //2
            "    cu.nombre AS curso_nombre,\n" + //3
            "\tcu.descripcion AS curso_descricion\n" + //4
            "FROM \n" +
            "    alumno al\n" +
            "JOIN \n" +
            "    alumno_curso ac ON al.id = ac.alumno_id\n" +
            "JOIN \n" +
            "    curso cu ON ac.curso_id = cu.id; " , nativeQuery = true)
    List<Object[]> obtenerAlumnosConCursos();


}