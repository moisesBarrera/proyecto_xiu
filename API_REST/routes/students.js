//Importamos express
var express = require('express');
//Creamos el objeto para definir las rutas
var router = express.Router();
//Importamos el modelo que ejecutará las sentencias SQL
var gruposModel = require('../models/grupos');


//Modelo de usuario
function formatearGrupos(datos){
    return {
      idgrupo : datos.idgrupo,
      nombre : datos.nombre,
      usuarios_idusuarios : datos.usuarios_idusuarios
    };
}

//Coger todos los grupos
router.get('/grupos', function(request, response) {  
   gruposModel.getGrupos(function(error, data)
    {
          response.status(200).json(data);
    });
});
//Coger un grupo por id
router.get('/gruposById', function(request, response) {  
  var id = request.query.idgrupo;
  gruposModel.getGruposById(id,function(error, datos)
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

//Coger un grupo por usuario
router.get('/gruposByUser', function(request, response) {  
  var id = request.query.usuarios_idusuarios;
  gruposModel.getGruposByUser(id,function(error, datos)
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
router.get('/insertGrupo', function(request, response) { 
    var datosgrupo = formatearGrupos(request.query);
    gruposModel.insertGrupos(datosgrupo,function(error, datos)
    {
      //console.log(datos);
      //console.log(error);
      if(!error)
      {
        response.status(200).json({"mensaje":true,"id":datos});
      }
      else
      {
        response.status(500).json({"mensaje":false});
      }
    });
});

//Modificar un usuario
router.get('/updateGrupo', function(request, response) {  
    var datosgrupo = formatearGrupos(request.query);
    console.log(datosgrupo);
    gruposModel.updateGrupos(datosgrupo,function(error, datos)
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

router.get('/deleteGrupo', function(request, response) {  
	var id = request.query.id;
    gruposModel.deleteGrupos(id,function(error, datos)
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
