//Importamos los datos de la conexión
var conn=require('./connection');
//Importamos el paquete mysql
var mysql = require('mysql'),
//Creamos la conexión a nuestra base de datos con los datos almacenados en conn
connection = mysql.createConnection(
	conn
);
 
//Creamos un objeto al que llamaremos Alumnos
var ordenAsientos = {};
 


//Obtenemos los asientos por grupo
ordenAsientos.getOrdenAsientosByGrupo = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM ordenAsientos WHERE grupo_idgrupo = ' + connection.escape(id);
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
 
//Actualizar un orden de asiento
ordenAsientos.updateOrdenAsientos = function(asientoData, callback)
{
	
	if(connection)
	{
		var sql = 'UPDATE ordenAsientos SET asiento=' + connection.escape(asientoData.asiento) +
				' WHERE Alumnos_idAlumnos = ' + connection.escape(asientoData.Alumnos_idAlumnos);
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


module.exports = ordenAsientos;