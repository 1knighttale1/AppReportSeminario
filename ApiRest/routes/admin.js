var express = require('express');
var router = express.Router();

/* GET admin listing. */
router.get('/', function(req, res) {
  //res.sendfile(path.join(__dirname, '/../views/admin.html'));
  res.render('admin');
});

router.get('/options', function(req, res){
  //console.log('opciones admin');
  var params = req.query;
  
  if(params.user != "admin" || params.password != "admin"){
    res.json({
      msn: "usuario o contraseña invalida"
    });     
    console.log('datos no validos');
    return;
  }
  res.render('options');



});
module.exports = router;
