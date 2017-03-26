//Importamos express
var express = require('express');
//Creamos el objeto para definir las rutas
var router = express.Router();
//Importamos el modelo que ejecutará las sentencias SQL
var escuelaModel = require('../models/escuela');


//Modelo de usuario
function formatearEscuela(datos){
  return {
      idEscuela:datos.idEscuela,
      nombre:datos.nombre,
      turno:datos.turno,
      cct:datos.cct,
      sector:datos.sector,
      zonaEscolar:datos.zonaEscolar,
      ubicacion:datos.ubicacion,
      usuarios_idusuarios:datos.usuarios_idusuarios
    };
}

router.get('/escuelas', function(request, response) {  
   escuelaModel.getescuelas(function(error, data)
    {
          response.status(200).json(data);
    });
});
//Coger escuela por id de usuario
router.get('/getEscuela', function(request, response) {  
  var id = request.query.id;
  escuelaModel.getEscuelaById(id,function(error, datos)
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
//Insertar Escuela
/*
Ejemplo de uso:
en el Body:
{ 
"nombre": "Usuario de Prueba"
,etc
}
*/

//Modificar un maestro
router.get('/updateEscuela', function(request, response) {  
    var datosEscuela = formatearEscuela(request.query);
    console.log(datosEscuela);
    escuelaModel.updateEscuela(datosEscuela,function(error, datos)
    {
      //si la escuela se ha actualizado correctamente mostramos un mensaje
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
