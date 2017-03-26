//Importamos los datos de la conexión
var conn=require('./connection');
//Importamos el paquete mysql
var mysql = require('mysql'),
//Creamos la conexión a nuestra base de datos con los datos almacenados en conn
connection = mysql.createConnection(
	conn
);
 
//Creamos un objeto al que llamaremos Alumnos
var Alumnos = {};
 
//Obtenemos todos los Alumnos
Alumnos.getAlumnos = function(callback)
{
	if (connection) 
	{
		connection.query('SELECT * FROM Alumnos', function(error, rows) {
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
Alumnos.getAlumnosById = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM Alumnos WHERE idAlumnos = ' + connection.escape(id);
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
Alumnos.getAlumnosByGrupo = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM Alumnos WHERE grupo_idgrupo = ' + connection.escape(id);
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
Alumnos.insertAlumnos = function(alumnoData,callback)
{
	if (connection) 
	{
		var sql = 'INSERT INTO Alumnos (nombre, apellido, genero, curp, matricula, usuario_idusuario, observaciones, grupo_idgrupo) VALUES'+
		'('+alumnoData.nombre+','+alumnoData.apellido+','+alumnoData.genero+','+alumnoData.curp+','+alumnoData.matricula
		+','+alumnoData.usuario_idusuario+','+alumnoData.observaciones+','+alumnoData.grupo_idgrupo+')';
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
Alumnos.updateAlumnos = function(alumnoData, callback)
{
	
	if(connection)
	{
		var sql = 'UPDATE Alumnos SET nombre=' + connection.escape(alumnoData.nombre) +
				' ,apellido = ' + connection.escape(alumnoData.apellido) +
				' ,genero = ' + connection.escape(alumnoData.genero) +
				' ,curp = ' + connection.escape(alumnoData.curp) +
				' ,matricula = ' + connection.escape(alumnoData.matricula) +
				' ,observaciones = ' + connection.escape(alumnoData.observaciones) +
				' ,grupo_idgrupo = ' + connection.escape(alumnoData.grupo_idgrupo) +
				' WHERE idAlumnos = ' + connection.escape(alumnoData.idAlumnos);
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
 
//Eliminar un usuario por su id
Alumnos.deleteAlumnos = function(id, callback)
{
	if(connection)
	{
		var sql = 'DELETE FROM Alumnos WHERE idAlumnos = ' + connection.escape(id);
		connection.query(sql, function(error, result) 
			{
				if(error)
				{
					callback(true,null);
				}
				else
				{
					callback(null,{"mensaje":"Borrado"});
				}
			});
	}
			
}

module.exports =Alumnos;