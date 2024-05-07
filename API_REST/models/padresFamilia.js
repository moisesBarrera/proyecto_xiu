//Importamos los datos de la conexión
var conn=require('./connection');
//Importamos el paquete mysql
var mysql = require('mysql'),
//Creamos la conexión a nuestra base de datos con los datos almacenados en conn
connection = mysql.createConnection(
	conn
);
 
//Creamos un objeto al que llamaremos padreDeFamilia
var padreDeFamilia = {};
 
//Obtenemos todos los padreDeFamilia
padreDeFamilia.getpadresDeFamilia = function(callback)
{
	if (connection) 
	{
		connection.query('SELECT * FROM PadresFamilia', function(error, rows) {
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
padreDeFamilia.getPadreDeFamiliaById = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM PadresFamilia WHERE idPadresFamilia = ' + connection.escape(id);
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
padreDeFamilia.getPadreDeFamiliaByUser = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM PadresFamilia WHERE usuarios_idusuarios = ' + connection.escape(id);
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

padreDeFamilia.getHijosdeUnpadre = function(id,callback){

	if (connection) 
	{
		var sql = "SELECT Alumnos.nombre,Alumnos.apellido, grupo.nombre as 'GroupName' FROM Alumnos "+
					'INNER JOIN Alumnos_has_PadresFamilia '+
    				'ON Alumnos.idAlumnos = Alumnos_has_PadresFamilia.Alumnos_idAlumnos '+
    				'INNER JOIN grupo '+
    				'ON Alumnos.grupo_idgrupo = grupo.idgrupo '+
    				'WHERE Alumnos_has_PadresFamilia.PadresFamilia_idPadresFamilia = ' + connection.escape(id);
    				console.log(sql);
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
padreDeFamilia.insertPadreDeFamilia = function(padreData,callback)
{
	if (connection) 
	{
		var sql = "INSERT INTO PadresFamilia (nombre, apellidos, telefono, direccion, email, usuarios_idusuarios) VALUES"+
		"('"+padreData.nombre+"', '"+padreData.apellidos+"', '"+padreData.telefono+"', '"+padreData.direccion+"', '"+padreData.email+"','"+padreData.usuarios_idusuarios +"')";
		//console.log(sql +"-------");
		//console.log(sql);
		connection.query(sql, function(error, result) 
		{
			//console.log(result[0][0].id);
			console.log(result);
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
padreDeFamilia.updatePadreDeFamilia = function(datosPadres, callback)
{
	
	if(connection)
	{
		var sql = 'UPDATE PadresFamilia SET nombre=' + connection.escape(datosPadres.nombre)+
				' ,apellidos = ' + connection.escape(datosPadres.apellidos) +
				' ,telefono = ' + connection.escape(datosPadres.telefono) +
				' ,direccion = ' +connection.escape(datosPadres.direccion) + 
				' ,email = ' + connection.escape(datosPadres.email) +
				' WHERE idPadresFamilia = ' + connection.escape(datosPadres.idPadresFamilia);
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
padreDeFamilia.deletePadreDeFamilia = function(id, callback)
{
	if(connection)
	{
		var sql = 'DELETE FROM PadresFamilia WHERE idPadresFamilia = ' + connection.escape(id);
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

module.exports =padreDeFamilia;