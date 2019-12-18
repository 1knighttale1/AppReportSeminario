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
  USER.find({}).select("_id name email phone address tipo").exec((err, docs) =>{
    if(err){
      res.status(300).json({
        msn: "Error en la base de datos"
      });
    }
    res.render('options', {users: docs});
  });
  //res.render('options', {users: "hola mundo"});
});

//formulario para resgistrar usuarios
router.get('/register', function(req, res){
  console.log('ingresando a register');
  res.render('register');
});

//delete users
router.get("/user/delete", async(req,res) => {
  var id = req.query.id;
  console.log(id);
  if (id == null) {
    res.status(300).json({
      msn: "introducir id"    
    });
    return;
  }
  var result = await USER.remove({_id: id});

  //res.status(200).json(result);
  console.log('user deleted');
  res.render('main');
});

//new user
router.post('/user/new', async(req, res) => {
  //console.log(req.body);
  var params = req.body;
  if(params.tipo == "agente"){
    console.log("se registro agente");
    params["tipo"] = "agente";
  }else{
    params["tipo"] = "cliente";
    console.log("se registro cliente");
  }
  params["registerdate"] = new Date();
  var users = new USER(params);
  var result = await users.save();
  console.log('user saved');
  res.render('main');
});

module.exports = router;
