const mongoose = require("./connect");
const HOMESCHEMA = {
    
};

const HOME = mongoose.model("home", HOMESCHEMA);
module.exports = HOME;