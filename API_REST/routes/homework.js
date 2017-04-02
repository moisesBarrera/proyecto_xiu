//Importamos express
var express = require('express');
//Creamos el objeto para definir las rutas
var router = express.Router();
//Importamos el modelo que ejecutará las sentencias SQL
var tareasModel = require('../models/tareas');


//Modelo de tarea
function formatearTarea(datos){
    return {
      idtarea : datos.idtarea,
      nombre : datos.nombre,
      grupo_idgrupo : datos.grupo_idgrupo,
      esEnEquipo : datos.esEnEquipo
    };
}

//Modelo de tarea_has_Alumnos
function formatearTareaHasAlumnos(datos){
  return {
    tarea_idtarea : datos.tarea_idtarea,
    Alumnos_idAlumnos : datos.Alumnos_idAlumnos,
    calificacion : datos.calificacion,
    gruposDeTrabajo_idgruposDeTrabajo : datos.gruposDeTrabajo_idgruposDeTrabajo
  };
}

//Modelo de grupo de trabajo.
function formatearGruposDeTrabajo(datos){
  return {
    gruposDeTrabajo : datos.gruposDeTrabajo,
    nombre : datos.nombre,
    tarea_idtarea : datos.tarea_idtarea
  }
}

//Crear una tarea
router.get('/crearTarea',function(request,response){
  var datosTarea = formatearTarea(request.query);
  //console.log(datosTarea);
  tareasModel.insertTarea(datosTarea,function(error, datos)
    {
      //console.log(error);
      //console.log(datos);
      if(!error)
      {
        response.status(200).json({"mensaje":true, "id":datos});
      }
      else
      {
        response.status(500).json({"mensaje":false});
      }
    });
});


//Registrar Equipos para la tarea
router.get('/CrearEquipo',function(request,response){
  var datosEquipo = formatearGruposDeTrabajo(request.query);
  //console.log(datosEquipo);
  tareasModel.insertEquipo(datosEquipo,function(error, datos)
    {
      if(!error)
      {
        response.status(200).json({"mensaje":true});
      }
      else
      {
        response.status(500).json({"mensaje":false});
      }
    });

});

//Asignar Integrantes a tarea ya sea para equipo o no
router.get('/asignarIntegrantesATarea',function(request,response){
  var datosTareaHasAlumnos = formatearTareaHasAlumnos(request.query);
  //console.log(datosTareaHasAlumnos);
  tareasModel.insertIntegrantes(datosTareaHasAlumnos,function(error, datos)
    {
      if(!error)
      {
        response.status(200).json({"mensaje":true});
      }
      else
      {
        response.status(500).json({"mensaje":false});
      }
    });
});

//Consultar equipos por tarea
router.get('/getEquiposPorTarea',function(request,response){
  var idTarea = request.query.id;
  tareasModel.getEquiposPorTarea(idTarea,function(error, datos)
    {
      if(!error)
      {
        response.status(200).json({"mensaje":true, "Equipos":datos});
      }
      else
      {
        response.status(500).json({"mensaje":false});
      }
    });
});

//Consultar equipo
router.get('/getEquipo',function(request,response){
  var idEquipo = request.query.id;
  var datosrespuesta = {
    mensaje : null,
    nombre : null,
    Integrantes : null
  };
  tareasModel.getEquipo(idEquipo,function(error, datos)
    {
      if(!error)
      {
        datosrespuesta.mensaje = true;
        datosrespuesta.nombre = datos[0].nombre;
        tareasModel.getIntegrantesEquipo(idEquipo,function(error,datos){
          if(!error){
            datosrespuesta.Integrantes = datos;
            response.status(200).json(datosrespuesta);
          } else {
            response.status(500).json({"mensaje":false});
          }
        });
      }
      else
      {
        response.status(500).json({"mensaje":false});
      }
    });
});

router.get('/getTareaPorGrupo',function(request,response){
  var id = request.query.id;
  tareasModel.getTareaPorGroupo(id,function(error, datos)
    {
      if(!error)
      {
        response.status(200).json({"mensaje":true, "Informacion":datos});
      }
      else
      {
        response.status(500).json({"mensaje":false});
      }
    });
});


//Modificar tarea
router.get('/updateTarea',function(request,response){
  var datosTarea = formatearTarea(request.query);
  tareasModel.updateTarea(datosTarea,function(error, datos)
    {
      if(!error)
      {
        response.status(200).json({"mensaje":true});
      }
      else
      {
        response.status(500).json({"mensaje":false});
      }
    });
});

//Actualizar integrantes de equipo
router.get('/updateIntegranteDeEquipo',function(request,response){

});

//Asignar calificación de tarea
router.get('/asignarCalificacion',function(request,response){
  var datosTareaHasAlumnos = formatearTareaHasAlumnos(request.query);
  tareasModel.updateTareaHasAlumno(datosTareaHasAlumnos,function(error, datos)
    {
      if(!error)
      {
        response.status(200).json({"mensaje":true});
      }
      else
      {
        response.status(500).json({"mensaje":false});
      }
    });
});

//Eliminar tarea
router.get('/deleteTarea',function(request,response){
  var id = request.query.id;
  tareasModel.deleteTarea(id,function(error, datos)
    {
      if(!error)
      {
        response.status(200).json({"mensaje":true});
      }
      else
      {
        response.status(500).json({"mensaje":false});
      }
    });
});

//Eliminar tareas todas
router.get('/deleteTodasTareas',function(request,response){

  var id = request.query.id;
  tareasModel.deleteAllTarea(id,function(error, datos)
    {
      if(!error)
      {
        response.status(200).json({"mensaje":true});
      }
      else
      {
        response.status(500).json({"mensaje":false});
      }
    });
});

module.exports = router;
