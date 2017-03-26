//Importamos los datos de la conexión
var conn=require('./connection');
//Importamos el paquete mysql
var mysql = require('mysql'),
//Creamos la conexión a nuestra base de datos con los datos almacenados en conn
connection = mysql.createConnection(
	conn
);
 
//Creamos un objeto al que llamaremos escuelas
var escuelas = {};
 
//Obtenemos todos los escuelas
escuelas.getescuelas = function(callback)
{
	if (connection) 
	{
		connection.query('SELECT * FROM escuela', function(error, rows) {
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
escuelas.getEscuelaById = function(idusuario,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM escuela WHERE usuarios_idusuarios = ' + connection.escape(idusuario);
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
escuelas.updateEscuela = function(datosEscuela, callback)
{
	
	if(connection)
	{
		var sql = 'UPDATE escuela SET nombre=' + connection.escape(datosEscuela.nombre)+
				' ,turno = ' + connection.escape(datosEscuela.turno) +
				' ,cct = ' + connection.escape(datosEscuela.cct) + 
				' ,sector = ' + connection.escape(datosEscuela.sector) +
				' ,zonaEscolar = ' +connection.escape(datosEscuela.zonaEscolar) + 
				' ,ubicacion = ' + connection.escape(datosEscuela.ubicacion) +
				' WHERE usuarios_idusuarios = ' + connection.escape(datosEscuela.usuarios_idusuarios);
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
 


module.exports =escuelas;