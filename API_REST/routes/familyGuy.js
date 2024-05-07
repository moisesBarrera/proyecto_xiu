//Importamos express
var express = require('express');
//Creamos el objeto para definir las rutas
var router = express.Router();
//Importamos el modelo que ejecutará las sentencias SQL
var padresModel = require('../models/padresFamilia');


//Modelo de usuario
function formatearPadresFamilia(datos){
    return {
      idPadresFamilia : datos.idPadresFamilia,
      nombre : datos.nombre,
      apellidos : datos.apellidos,
      telefono:datos.telefono,
      direccion:datos.direccion,
      email:datos.email,
      usuarios_idusuarios:datos.usuarios_idusuarios,
    };
}

//Coger todos los padres de Familia
router.get('/padresFamilia', function(request, response) {  
   padresModel.getpadresDeFamilia(function(error, data)
    {
          response.status(200).json(data);
    });
});
//Coger padre de Familia por id
router.get('/padreFamiliaById', function(request, response) {  
  var id = request.query.idPadresFamilia;
  padresModel.getPadreDeFamiliaById(id,function(error, datos)
      {
        if (!error && datos.length > 0)
        {
          padresModel.getHijosdeUnpadre(id,function(error,data){
            if(!error){
              response.status(200).json({"mensaje":true, "datosPadre":datos,"Hijos":data});
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

router.get('/padreFamiliaByUser', function(request, response) {  
  var id = request.query.idPadresFamilia;
  padresModel.getPadreDeFamiliaByUser(id,function(error, datos)
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
router.get('/insertFamilyGuy', function(request, response) { 
    var datosPadre = formatearPadresFamilia(request.query);
    //console.log(datosUsuario);
    padresModel.insertPadreDeFamilia(datosPadre,function(error, datos)
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
router.get('/updateFamilyGuy', function(request, response) {  
    var datosPadre = formatearPadresFamilia(request.query);
    console.log(datosPadre);
    padresModel.updatePadreDeFamilia(datosPadre,function(error, datos)
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

router.get('/deleteFamilyGuy', function(request, response) {  
	var id = request.query.id;
    padresModel.deletePadreDeFamilia(id,function(error, datos)
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
