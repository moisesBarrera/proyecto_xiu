//Importamos express
var express = require('express');
//Creamos el objeto para definir las rutas
var router = express.Router();
//Importamos el modelo que ejecutará las sentencias SQL
var asientosModel = require('../models/asientos');


//Modelo de usuario
function formatearAsientos(datos){
    return {
      idordenAsientos : datos.idordenAsientos,
      asiento : datos.asiento,
      Alumnos_idAlumnos : datos.Alumnos_idAlumnos,
      grupo_idgrupo : datos.grupo_idgrupo
    };
}



//Coger un grupo por usuario
router.get('/seatingByGroup', function(request, response) {  
  var id = request.query.grupo_idgrupo;
  asientosModel.getOrdenAsientosByGrupo(id,function(error, datos)
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

//Modificar un usuario
router.get('/updateOrdenAsientos', function(request, response) {  
    var datosAsientos = formatearAsientos(request.query);
    asientosModel.updateOrdenAsientos(datosAsientos,function(error, datos)
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

module.exports = router;
