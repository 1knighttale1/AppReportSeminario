var express = require('express');
var router = express.Router();
var USER = require("../database/users");

/* GET admin listing. */
router.get('/', function(req, res) {
  //res.sendfile(path.join(__dirname, '/../views/admin.html'));
  res.render('admin');
});

//ingresar a inicio de admin
router.get('/main', function(req, res){
  var params = req.query;
  if(params.user != "admin" || params.password != "admin"){
    res.json({
      msn: "usuario o contraseña invalida"
    });     
    console.log('datos no validos');
    return;
  }
  res.render('main');
});

//ingresar a opciones
router.get('/options', function(req, res){
  var users = {};
  USER.find({}).select("_id name email phone address tipo").exec((err, docs) =>{
    if(err){
      res.status(300).json({
        msn: "Error en la base de datos"
      });
    }
    //users = docs;
    res.render('options', {users: docs})
  });
  //res.render('options', {users: "hola mundo"});
});

module.exports = router;
