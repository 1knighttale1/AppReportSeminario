var express = require("express");

var USER = require("../database/users");
var HOME = require("../database/homes");
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

/*Creation Homes*/
router.post('/home', async(req, res)=>{
    console.log(req.body);
    var params = req.body;
    var homes = new HOME(params);
    var result = await homes.save();
    res.status(200).json(result);
})

/**Show Homes */
router.get('/home', (req, res) => {

});

/**Creation Neighborhood */
router.post('/neighborhood', async(req, res) => {
    console.log(req.body);
    var params = req.body;
    var neighborhoods = new NEIGHBORHOOD(params);
    var result = await neighborhoods.save();
    res.status(200).json(result);
});

/**Show Neighborhood */
router.get('/neighborhood', (req, res) => {
    
});

/**LOGIN */
router.post('/login', async(req, res, next)=>{

});
module.exports = router;
