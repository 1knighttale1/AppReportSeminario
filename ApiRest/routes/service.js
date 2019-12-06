var express = require("express");

var USER = require("../database/users");
var HOME = require("../database/homes");
var LISTING = require("../database/homes");
var ZONE =require("../database/zones");
//const USER = user.model;
//const USERSCHEMA = user.schema;

var router = express.Router();

/*Register Users*/
router.post('/user', async(req, res) => {
    console.log(req.body);
    var params = req.body;
    params["registerdate"] = new Date();
    var users = new USER(params);
    var result = await users.save();
    res.status(200).json(result);
});

/*Register Homes*/
router.post('/home', async(req, res)=>{
    console.log(req.body);
    var params = req.body;
    var homes = new HOME(params);
    var result = await homes.save();
    res.status(200).json(result);
})

/*Register Neighborhood */
router.post('/zones', async(req, res) => {
    console.log(req.body);
    var params = req.body;
    var zone = new ZONE(params);
    var result = await zone.save();
    res.status(200).json(result);
});
/*-----------------------------------------------------------*/
/*Show Users*/
router.get('/user', (req, res) => {
    var params = req.query;
    var limit = 100;
    var skip = 0;
    if (params.limit != null) {
        limit = parseInt(params.limit);
    } 
    if (params.skip != null) {
        skip = parseInt(params.skip);
    }
    USER.find({}).limit(limit).skip(skip).exec((err, docs) => {
        res.status(200).json(docs);
    });
});

/*Looking a specific home*/
router.get('/home', (req, res) => {
    
    var params = req.query;
    var filter = {};

    if(params.id != null){
        filter["id"] = Number(params.id);
        LISTING.find(filter).exec((err, docs) => {
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

/**Show Homes */
router.get('/homes', (req, res) => {

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
    
    LISTING.find(filter).skip(skip).limit(limit).select(cad).exec((err, docs) => {
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



/**Show Neighborhood */
router.get('/neighborhood', (req, res) => {
    
});

/**LOGIN */
router.post('/login', async(req, res, next)=>{

});
module.exports = router;
