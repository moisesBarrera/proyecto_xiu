//Importamos express
var express = require('express');
//Creamos el objeto para definir las rutas
var router = express.Router();
//Importamos el modelo que ejecutará las sentencias SQL
var alumnosModel = require('../models/alumnos');


//Modelo de usuario
function formatearAlumnos(datos){
    return {
      idAlumnos : datos.idAlumnos,
      nombre : datos.nombre,
      apellido : datos.apellido,
      genero : datos.genero,
      curp : datos.curp,
      matricula : datos.matricula,
      usuario_idusuario : datos.usuario_idusuario,
      observaciones : datos.observaciones,
      grupo_idgrupo : datos.grupo_idgrupo,
      idPadre : datos.idPadre
    };
}

//Coger todos los grupos
router.get('/alumnos', function(request, response) {  
   alumnosModel.getAlumnos(function(error, data)
    {
          response.status(200).json(data);
    });
});
//Coger un grupo por id
router.get('/alumnoById', function(request, response) {  
  var id = request.query.idAlumnos;
  alumnosModel.getAlumnosById(id,function(error, datos)
      {
        if (!error && datos.length > 0)
        {
          alumnosModel.getFather(id,function(error,data){
            if(!error){
              response.status(200).json({"mensaje":true, "Hijo":datos,"Padre":data});
            } else {
              response.status(500).json({"mensaje":false});
            }
          });
        }
        else
        {
          response.status(404).json({"mensaje":false});
        }
      });
    });

//Coger un grupo por usuario
router.get('/alumnoByGrupo', function(request, response) {  
  var id = request.query.grupo_idgrupo;
  alumnosModel.getAlumnosByGrupo(id,function(error, datos)
      {
        if (!error && datos.length > 0)
        {
          response.status(200).json({"mensaje":true, "Informacion":datos});
        }
        else
        {
          response.status(404).json({"mensaje":false});
        }
      });
    });
//Insertar usuario
/*
Ejemplo de uso:
en el Body:
{ 
"nombre": "Usuario de Prueba"
}
*/
router.get('/insertAlumno', function(request, response) { 
    var datosAlumno = formatearAlumnos(request.query);
    alumnosModel.insertAlumnos(datosAlumno,function(error, datos)
    {
      //console.log(datos);
      //console.log(error);
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

//Modificar un usuario
router.get('/updateAlumno', function(request, response) {  
    var datosAlumno = formatearAlumnos(request.query);
    alumnosModel.updateAlumnos(datosAlumno,function(error, datos)
    {
      //si el usuario se ha actualizado correctamente mostramos un mensaje
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
//Borrar un usuario

router.get('/deleteAlumno', function(request, response) {  
	var id = request.query.id;
    alumnosModel.deleteAlumnos(id,function(error, datos)
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
