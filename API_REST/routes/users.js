//Importamos express
var express = require('express');
//Creamos el objeto para definir las rutas
var router = express.Router();
//Importamos el modelo que ejecutará las sentencias SQL
var usuariosModel = require('../models/usuarios');


//Modelo de usuario
function formatearUsuario(datos){
  var datosUsuario = {
      idusuarios : datos.idusuarios,
      usuario : datos.usuario,
      contrasena : datos.contrasena
    };

    return datosUsuario;
}

//Coger todos los usuarios
router.get('/usuarios', function(request, response) {  
   usuariosModel.getUsuarios(function(error, data)
    {
          response.status(200).json(data);
    });
});
//Coger usuario por id
router.get('/login', function(request, response) {  
  var usuario = request.query.usuario;
  var contrasena = request.query.contrasena;
  usuariosModel.getUsuarioById(usuario,contrasena,function(error, datos)
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
router.get('/register', function(request, response) { 
    var datosUsuario = formatearUsuario(request.query.usuario);
    //console.log(datosUsuario);
    usuariosModel.insertUsuario(datosUsuario,function(error, datos)
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
router.get('/updateUsuario', function(request, response) {  
    var datosUsuario = formatearUsuario(request.query.id,null,request.query.contrasena);
    console.log(datosUsuario);
    usuariosModel.updateUsuario(datosUsuario,function(error, datos)
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

router.get('/deleteUsuario', function(request, response) {  
	var id = request.query.id;
    usuariosModel.deleteUsuario(id,function(error, datos)
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
