const mongoose = require('mongoose');
const databasename = "apirest";
mongoose.connect("mongodb://172.22.0.2:27017/" + databasename);
module.exports = mongoose;