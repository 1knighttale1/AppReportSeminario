var express = require("express");

var USER = require("../database/users");
var HOME = require("../database/homes");
var ZONE = require("../database/zones");
var router = express.Router();
var jwt = require("jsonwebtoken");

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('index', { title: 'Express' });
  });
/*Creation Homes*/
router.post('/homes', async(req, res)=>{
    console.log(req.body);
    var params = req.body;
    var homes = new HOME(params);
    var result = await homes.save();
    res.status(200).json(result);
})

/*Show Homes*/
router.get('/homes', (req, res) => {
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
    });
});


/*Looking a specific home*/
router.get('/homes/search', (req, res) => {
    
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
            console.log("Looking a specific home");
            return;
        });
    }else{
        res.status(200).json({
            msn: "No se encontro la casa"
        });
    }
});

/**Show previus details Homes */
router.get('/homes/', (req, res) => {
    var cad = "contacto lat long precio imagen"; //detalles previos
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
    //busqueda por direccion
    if(params.search != null){
        filter["directions"] = new RegExp(params.search, "g");
    }
    //busqueda por precio
    if(params.min != null && params.max){
        filter["precio"] = {"$gt": Number(params.min), "$lt": Number(params.max)};
    }
    
    HOME.find(filter).skip(skip).limit(limit).select(cad).exec((err, docs) => {
        if (err){
            res.status(300).json({
                msn:"Error en la base de datos"
            });
            return;
        }
        res.status(200).json(docs);
        console.log("Looking homes");
        return;
    });
});

/** --------------------------------------------------------------------------- */

/**Creation Zones */
router.post('/zone', async(req, res) => {
    console.log(req.body);
    var params = req.body;
    var zone = new ZONE(params);
    var result = await zone.save();
    res.status(200).json(result);
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
    if(params.search != null){
        filter["directions"] = new RegExp(params.search, "g");
    }
    ZONE.find(filter).limit(limit).skip(skip).exec((err, docs) => {
        res.status(200).json(docs);
    });
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
