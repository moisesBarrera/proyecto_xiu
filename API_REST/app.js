var express = require("express");
var router=express.Router();
var bodyParser  = require("body-parser");
var aplicacion = express();
var usuarios=require("./routes/users");
var maestros=require("./routes/masters");
var escuelas=require("./routes/school");
var padres=require("./routes/familyGuy");
var groups=require("./routes/groups");
var students=require("./routes/students");
router.get('/', function(request, response) {  
   response.status(200).json({"mensaje":"Servidor iniciado"});
});
aplicacion.use(bodyParser.json()); 
//incluimos el archivo en el que se almacenan las rutas de cada entidad
aplicacion.use(router);  
aplicacion.use(usuarios);
aplicacion.use(maestros);
aplicacion.use(escuelas);
aplicacion.use(groups);
aplicacion.use(students);
 

aplicacion.listen(5000, function() { 
console.log("Servidor iniciado");
});