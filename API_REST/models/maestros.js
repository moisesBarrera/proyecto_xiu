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
 
//Obtenemos todos los maestros
maestros.getmaestros= function(callback)
{
	if (connection) 
	{
		connection.query('SELECT * FROM maestros', function(error, rows) {
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
				callback(true,null);
			}
			else
			{
				callback(null, row);
			}
		});
	}
}

 
//Actualizar un usuario
maestros.updateMaestro = function(masterData, callback)
{
	
	if(connection)
	{
		var sql = 'UPDATE maestros SET nombre=' + connection.escape(masterData.nombre)+
				' ,apellido = ' + connection.escape(masterData.apellido) +
				' ,rfc = ' + connection.escape(masterData.rfc) + 
				' ,cedulaProfesional = ' + connection.escape(masterData.cedulaProfesional) +
				' ,gradoMaxEstudios = ' +connection.escape(masterData.gradoMaxEstudios) + 
				' ,curp = ' + connection.escape(masterData.curp) +
				' ,domicilioParticular = ' + connection.escape(masterData.domicilioParticular) + 
				' ,telefono = ' + connection.escape(masterData.telefono) +
				' WHERE usuario_idusuario = ' + connection.escape(masterData.usuario_idusuario);
				console.log(sql);
		connection.query(sql, function(error, result) 
		{
			if(error)
			{
				callback(true,null);
			}
			else
			{
				callback(null,{"mensaje":"Actualizado"});
			}
		});
	}
}
 

module.exports = maestros;