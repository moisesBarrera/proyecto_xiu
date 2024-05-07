//Importamos los datos de la conexión
var conn=require('./connection');
//Importamos el paquete mysql
var mysql = require('mysql'),
//Creamos la conexión a nuestra base de datos con los datos almacenados en conn
connection = mysql.createConnection(
	conn
);
 
//Creamos un objeto al que llamaremos Alumnos
var Tareas = {};
 
//Añadir un nueva tarea
Tareas.insertTarea= function(tareaData,callback)
{
	if (connection) 
	{
		var sql = 'call nueva_tarea('+connection.escape(tareaData.nombre)+','+connection.escape(tareaData.grupo_idgrupo)+','+connection.escape(tareaData.esEnEquipo)+')';
		//console.log(sql +"-------");
		console.log(sql);
		connection.query(sql, function(error, result) 
		{
			//console.log(result[0][0].id);
			console.log(error);
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

//Añadir un nueva tarea
Tareas.insertEquipo= function(equipoData,callback)
{
	if (connection) 
	{
		var sql = 'INSERT INTO gruposDeTrabajo (nombre, tarea_idtarea) VALUES ('+connection.escape(equipoData.nombre)+','+connection.escape(equipoData.tarea_idtarea)+')';
		//console.log(sql +"-------");
		console.log(sql);
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

//Añadir integrantes a equipo
Tareas.insertIntegrantes= function(tareaHasAlumnosData,callback)
{
	if (connection) 
	{
		var sql = 'INSERT INTO tarea_has_Alumnos (tarea_idtarea,Alumnos_idAlumnos, gruposDeTrabajo_idgruposDeTrabajo) VALUES '+
		'('+connection.escape(tareaHasAlumnosData.tarea_idtarea)+','+connection.escape(tareaHasAlumnosData.Alumnos_idAlumnos)+','+connection.escape(tareaHasAlumnosData.gruposDeTrabajo_idgruposDeTrabajo)+')';
		//console.log(sql +"-------");
		console.log(sql);
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

//Obtenemos todos las tareas por grupo
Tareas.getTareaPorGroupo = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM tarea WHERE grupo_idgrupo='+connection.escape(id);
		console.log(sql);
		connection.query(sql, function(error, rows) {
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

//Obtenemos todos las tareas por grupo
Tareas.getTareabyId = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM tarea WHERE idtarea='+connection.escape(id);
		console.log(sql);
		connection.query(sql, function(error, rows) {
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

//Obtenemos todos los equipos por tarea
Tareas.getEquiposPorTarea = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM gruposDeTrabajo WHERE tarea_idtarea = '+connection.escape(id);
		console.log(sql);
		connection.query(sql, function(error, rows) {
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
 
//Obtenemos los datos de un equipo
Tareas.getEquipo = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM gruposDeTrabajo WHERE idgruposDeTrabajo = ' + connection.escape(id);
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

//Obtenemos los integrantes de un equipo
Tareas.getIntegrantesEquipo = function(id,callback)
{
	if (connection) 
	{
		var sql = 'SELECT * FROM tarea_has_Alumnos WHERE gruposDeTrabajo_idgruposDeTrabajo = ' + connection.escape(id);
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


//Obtenemos los integrantes de un equipo
Tareas.updateTarea = function(datosTarea,callback)
{
	if (connection) 
	{
		var sql = 'UPDATE tarea set nombre='+connection.escape(datosTarea.nombre)+' WHERE idtarea = '+connection.escape(datosTarea.idtarea);
		//console.log(sql);
		connection.query(sql, function(error, row) 
		{
			if(error)
			{
				callback(true,null);
			}
			else
			{
				callback(null, true);
			}
		});
	}
}

//Obtenemos los integrantes de un equipo
Tareas.updateTareaHasAlumno = function(datosTareaHasAlumno,callback)
{
	if (connection) 
	{
		var sql = 'UPDATE tarea_has_Alumnos set calificacion='+connection.escape(datosTareaHasAlumno.calificacion)+' WHERE tarea_idtarea = '+connection.escape(datosTareaHasAlumno.tarea_idtarea)+' and Alumnos_idAlumnos='+connection.escape(datosTareaHasAlumno.Alumnos_idAlumnos);
		console.log(sql);
		connection.query(sql, function(error, row) 
		{
			if(error)
			{
				callback(true,null);
			}
			else
			{
				callback(null, true);
			}
		});
	}
}



//Eliminamos la tarea
Tareas.deleteTarea = function(id,callback)
{
	if (connection) 
	{
		var sql = 'DELETE FROM tarea WHERE idtarea ='+connection.escape(id);
		//console.log(sql);
		connection.query(sql, function(error, row) 
		{
			if(error)
			{
				callback(true,null);
			}
			else
			{
				callback(null, true);
			}
		});
	}
}


//Eliminamos todas las tarea de un grupo
Tareas.deleteAllTarea = function(id,callback)
{
	if (connection) 
	{
		var sql = 'DELETE FROM tarea WHERE grupo_idgrupo ='+connection.escape(id);
		//console.log(sql);
		connection.query(sql, function(error, row) 
		{
			if(error)
			{
				callback(true,null);
			}
			else
			{
				callback(null, true);
			}
		});
	}
}



module.exports = Tareas;