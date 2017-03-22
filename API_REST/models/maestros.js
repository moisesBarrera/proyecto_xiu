//Importamos los datos de la conexión
var conn=require('./connection');
//Importamos el paquete mysql
var mysql = require('mysql'),
//Creamos la conexión a nuestra base de datos con los datos almacenados en conn
connection = mysql.createConnection(
	conn
);
 
//Creamos un objeto al que llamaremos usuarios
var maestros = {};
 
//Obtenemos un maestro por su id
maestros.getMaestroById = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM maestros WHERE usuario_idusuario = ' + connection.escape(id);
		connection.query(sql, function(error, row) 
		{
			if(error)
			{
				throw error;
			}
			else
			{
				callback(null, row);
			}
		});
	}
}

//Añadir un nuevo maestro
maestros.insertMaestro = function(masterData,callback)
{
	if (connection) 
	{
		connection.query('INSERT INTO maestros SET ?', masterData, function(error, result) 
		{
			if(error)
			{
				
				throw error;
			}
			else
			{
				//devolvemos el id del usuario insertado
				callback(null, result.insertId);
			}
		});
	}
}
 
//Actualizar un usuario
maestros.updateMaestro = function(masterData, callback)
{
	
	if(connection)
	{
		var sql = 'UPDATE usuarios SET ' + connection.escape(datosUsuario.contrasena)  +' WHERE usuario_idusuario = ' + datosUsuario.idusuarios;
		connection.query(sql, function(error, result) 
		{
			if(error)
			{
				throw error;
			}
			else
			{
				callback(null,{"mensaje":"Actualizado"});
			}
		});
	}
}
 

module.exports = maestros;