//Importamos los datos de la conexión
var conn=require('./connection');
//Importamos el paquete mysql
var mysql = require('mysql'),
//Creamos la conexión a nuestra base de datos con los datos almacenados en conn
connection = mysql.createConnection(
	conn
);
 
//Creamos un objeto al que llamaremos grupos
var grupos = {};
 
//Obtenemos todos los grupos
grupos.getGrupos = function(callback)
{
	if (connection) 
	{
		connection.query('SELECT * FROM grupo', function(error, rows) {
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
 
//Obtenemos un padre de familia por su id
grupos.getGruposById = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM grupo WHERE idgrupo = ' + connection.escape(id);
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

//Obtenemos un padre de familia por su usuario
grupos.getGruposByUser = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM grupo WHERE usuarios_idusuarios = ' + connection.escape(id);
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
grupos.insertGrupos = function(grupoData,callback)
{
	if (connection) 
	{
		var sql = 'INSERT INTO grupo (nombre, usuarios_idusuarios) VALUES'+
		' ('+connection.escape(grupoData.nombre)+', '+connection.escape(grupoData.usuarios_idusuarios) +')';
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
				callback(null, true);
			}
		});
	}
}
 
//Actualizar un usuario
grupos.updateGrupos = function(grupoData, callback)
{
	
	if(connection)
	{
		var sql = 'UPDATE grupo SET nombre=' + connection.escape(grupoData.nombre) +
				' WHERE idgrupo = ' + connection.escape(grupoData.idgrupo);
				//console.log(sql);
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
grupos.deleteGrupos = function(id, callback)
{
	if(connection)
	{
		var sql = 'DELETE FROM grupo WHERE idgrupo = ' + connection.escape(id);
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

module.exports =grupos;