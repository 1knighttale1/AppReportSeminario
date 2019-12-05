var express = require("express");

var USER = require("../database/users");
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
router.get("/user", (req, res) => {
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
module.exports = router;
