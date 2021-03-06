//Importamos los datos de la conexión
var conn=require('./connection');
//Importamos el paquete mysql
var mysql = require('mysql'),
//Creamos la conexión a nuestra base de datos con los datos almacenados en conn
connection = mysql.createConnection(
	conn
);
 
//Creamos un objeto al que llamaremos usuarios
var usuarios = {};
 
//Obtenemos todos los usuarios
usuarios.getUsuarios = function(callback)
{
	if (connection) 
	{
		connection.query('SELECT * FROM usuarios', function(error, rows) {
			if(error)
			{
				callback(true,null);
			}
			else
			{
				callback(null, rows);
			}
		});
	}
}
 
//Obtenemos un usuario por su id
usuarios.getUsuarioById = function(usuario,contrasena,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM usuarios WHERE usuario = ' + connection.escape(usuario)+' and contrasena='+connection.escape(contrasena);
		connection.query(sql, function(error, row) 
		{
			if(error)
			{
				callback(true,null);
			}
			else
			{
				callback(null, row);
			}
		});
	}
}

//Añadir un nuevo usuario
usuarios.insertUsuario = function(usuarioData,callback)
{
	if (connection) 
	{
		var sql = 'CALL REGISTRAR("'+usuarioData.usuario+'","'+usuarioData.contrasena+'")';
		//console.log(sql +"-------");
		connection.query(sql, function(error, result) 
		{
			//console.log(result[0][0].id);
			if(error)
			{
				callback(true,null);
			}
			else
			{
				//devolvemos el id del usuario insertado
				callback(null, result[0][0].id);
			}
		});
	}
}
 
//Actualizar un usuario
usuarios.updateUsuario = function(datosUsuario, callback)
{
	
	if(connection)
	{
		var sql = 'UPDATE usuarios SET contrasena = ' + connection.escape(datosUsuario.contrasena)  +' WHERE idusuarios = ' + datosUsuario.idusuarios;
		connection.query(sql, function(error, result) 
		{
			if(error)
			{
				callback(true,null);
			}
			else
			{
				callback(null,true);
			}
		});
	}
}
 
//Eliminar un usuario por su id
usuarios.deleteUsuario = function(id, callback)
{
	if(connection)
	{
		var sql = 'DELETE FROM usuarios WHERE idusuarios = ' + connection.escape(id);
		connection.query(sql, function(error, result) 
			{
				if(error)
				{
					callback(true,null);
				}
				else
				{
					callback(null,true);
				}
			});
	}
			
}

module.exports =usuarios;