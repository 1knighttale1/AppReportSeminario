var express = require("express");

var USER = require("../database/users");
var HOME = require("../database/homes");
var ZONE = require("../database/zones");
var router = express.Router();
var jwt = require("jsonwebtoken");

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('index', { title: 'Express' });
    console.log('main page')
  });

/*Creation Homes*/
router.post('/home', async(req, res)=>{
    var params = req.body;
    var homes = new HOME(params);
    var result = await homes.save();
    res.status(200).json(result);
    console.log('home saved');
})

/*Show Homes*/
router.get('/home/all', (req, res) => {
    var params = req.query;
    var limit = 100;
    if (params.limit != null) {
        limit = parseInt(params.limit);
    } 
    var skip = 0;
    if (params.skip != null) {
        skip = parseInt(params.skip);
    }
    HOME.find({}).limit(limit).skip(skip).exec((err, docs) => {
        res.status(200).json(docs);
    console.log('showing homes');
    });
});


/*Looking a specific home*/
router.get('/home/search', (req, res) => {
    
    var params = req.query;
    var filter = {};

    if(params.id != null){
        filter["id"] = Number(params.id);
        HOME.find(filter).exec((err, docs) => {
            if (err){
                res.status(300).json({
                    msn:"Error en la base de datos"
                });
                return;
            }
            res.status(200).json(docs);
            console.log("Showing a specific home");
            return;
        });
    }else{
        res.status(200).json({
            msn: "No se encontro la casa"
        });
    }
});

/**Show previus details Homes */
router.get('/home/', (req, res) => {
    var cad = "imagen descripcion contacto"; //detalles previos
    var skip = 0;
    var limit = 20;
    var filter = {};
    var params = req.query;

    if(params.skip != null){
        skip = Number(params.skip);
    }
    if(params.limit != null){
        limit = Number(params.limit);
    }
    //busqueda por descripcion
    if(params.search != null){
        filter["descripcion"] = new RegExp(params.search, "g");
    }
    //busqueda por zona
    if(params.zona != null){
        filter["zona"] = params.zona;
    }
    //por numero de baños
    if(params.baños != null){
        filter["baños"] = Numer(params.baños);
        cad += " baños";
    }
    //por tipo de casa
    if(params.tipo != null){
        filter["tipo"] = params.tipo;
        cad += " tipo";
    }
    //busqueda por colegio cercano
    if(params.colegio != null){
        filter["colegio"] = params.colegio;
        cad += " colegio"; 
    }
    //busqueda por estado
    if(params.estado != null){
        filter["estado"] = params.estado;
        cad += " estado";
    }
    //por numero de habitaciones
    if(params.habitaciones != null){
        filter["habitaciones"] = Numer(params.habitaciones);
        cad += " habitaciones";
    }
    //busqueda por superficie
    if(params.superficie != null){
        filter["superficie"] = Number(params.superficie);
        cad += " superficie";
    }
 /*   if(params.Smin != null || params.Smax != null){
        filter["superficie"] = {"$gt": Number(params.Smin), "$lt": Number(params.Smax)};
        cad += " superficie";
    }*/
    //busqueda por año de construccion
    if(params.fecha_construccion != null){
        filter["fecha_construccion"] = params.fecha_construccion;
        cad += " fecha_construccion";
    }
    //busqueda por precio
    if(params.min != null || params.max != null){
        filter["precio"] = {"$gt": Number(params.min), "$lt": Number(params.max)};
        cad += " precio";
    }
    

    
    HOME.find(filter).skip(skip).limit(limit).select(cad).exec((err, docs) => {
        if (err){
            res.status(300).json({
                msn:"Error en la base de datos"
            });
            return;
        }
    res.status(200).json(docs);
    console.log("showing homes");
    return;
    });
});

/**Delete Home */
router.delete("/home", async(req,res) => {
    var id = req.query.id;
    //console.log(req.query.id);
    if (id == null) {
      res.status(300).json({
        msn: "introducir id"    
      });
      return;
    }
    var result = await HOME.remove({_id: id});
    res.status(200).json(result);
    console.log('home deleted');
  });

/** --------------------------------------------------------------------------- */

/**Creation Zones */
router.post('/zone', async(req, res) => {
    //console.log(req.body);
    var params = req.body;
    var zones = new ZONE(params);
    var result = await zones.save();
    res.status(200).json(result);
    console.log('zone saved');
});

/**Show Neighborhood */
router.get('/zone', (req, res) => {
    var params = req.query;
    var limit = 100;
    var filter = {};
    if (params.limit != null) {
        limit = parseInt(params.limit);
    } 
    var skip = 0;
    if (params.skip != null) {
        skip = parseInt(params.skip);
    }
    if(params.nombre != null){
        filter["nombre"] = params.nombre;
    }
    if(params.ciudad != null){
        filter["ciudad"] = params.ciudad;
    }
    ZONE.find(filter).limit(limit).skip(skip).exec((err, docs) => {
        res.status(200).json(docs);
    console.log('showing zones');
    });
});

/**Delete Zone */
router.delete("/zone", async(req,res) => {
    var id = req.query.id;
    if (id == null) {
      res.status(300).json({
        msn: "introducir id"    
      });
      return;
    }
    var result = await ZONE.remove({_id: id});
    res.status(200).json(result);
    console.log('zone deleted');
  });

/** ------------------------------------------------------------------------------ */
/*Register Users*/
router.post('/user', async(req, res) => {
    //console.log(req.body);
    var params = req.body;
    if(params.tipo == null){
        console.log("se registro agente");
        params["tipo"] = params.tipo;
    }else{
        console.log("se registro cliente");
    }
    params["registerdate"] = new Date();
    var users = new USER(params);
    var result = await users.save();
    res.status(200).json(result);
    console.log('user saved');
});


/*Show Users*/
router.get('/user', (req, res) => {
    var params = req.query;
    var limit = 100;
    if (params.limit != null) {
        limit = parseInt(params.limit);
    } 
    var skip = 0;
    if (params.skip != null) {
        skip = parseInt(params.skip);
    }
    USER.find({}).limit(limit).skip(skip).exec((err, docs) => {
        res.status(200).json(docs);
    console.log('showing users');
    });
});

/**Delete User */
router.delete("/user", async(req,res) => {
    var id = req.query.id;
    console.log(req.query.id);
    if (id == null) {
      res.status(300).json({
        msn: "introducir id"    
      });
      return;
    }
    var result = await USER.remove({_id: id});
    res.status(200).json(result);
    console.log('user deleted');
  });

/** ----------------------------------------------------------------------------------- */
/**LOGIN */
router.post('/login', async(req, res, next)=>{
    //console.log(req.body);
    var params = req.body;
    USER.find({email:params.email, password:params.password}).exec((err, docs)=>{
        if (err){
            res.status(300).json({
                "msn": "Problemas con la base de datos"
            });
            return;
        }
        if (docs.length == 0){
            res.status(300).json({
                "msn": "Usuario y/o Password Incorrecto"
            });
            return;
        } else {
            //cracion del token
            jwt.sign({name: params.email, password: params.password}, "password", (err, token)=>{
                if(err){
                    res.status(300).json({
                        "msn": "Error con JSONWEBTOKEN"
                    });
                    return;
                }
                res.status(200).json({
                    "token": token
                });
                return;
            });
        }
    });
});



module.exports = router;
