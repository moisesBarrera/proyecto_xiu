//Importamos express
var express = require('express');
//Creamos el objeto para definir las rutas
var router = express.Router();
//Importamos el modelo que ejecutará las sentencias SQL
var maestrosModel = require('../models/maestros');


//Modelo de usuario
function formatearMaestro(datos){
  return {
      idmaestros:datos.idmaestros,
      nombre:datos.nombre,
      apellido:datos.apellido,
      rfc:datos.rfc,
      cedulaProfesional:datos.cedulaProfesional,
      gradoMaxEstudios:datos.gradoMaxEstudios,
      curp:datos.curp,
      domicilioParticular:datos.domicilioParticular,
      telefono:datos.telefono,
      usuario_idusuario:datos.usuario_idusuario
    };
}


router.get('/maestros', function(request, response) {  
   maestrosModel.getmaestros(function(error, data)
    {
          response.status(200).json(data);
    });
});

//Coger maestro por id de usuario
router.get('/getMaestro', function(request, response) {  
  var id = request.query.id;
  maestrosModel.getMaestroById(id,function(error, datos)
      {
        if (!error && datos.length > 0)
        {
          response.status(200).json({"mensaje":true,"Informacion":datos});
        }
        else
        {
          response.status(404).json({"mensaje":false});
        }
      });
    });
//Insertar Maestro
/*
Ejemplo de uso:
en el Body:
{ 
"nombre": "Usuario de Prueba"
,etc
}
*/

//Modificar un maestro
router.get('/updateMaestro', function(request, response) {  
    var datosMaestro = formatearMaestro(request.query);
    //console.log(datosMaestro);
    maestrosModel.updateMaestro(datosMaestro,function(error, datos)
    {
      //si el maestro se ha actualizado correctamente mostramos un mensaje
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
