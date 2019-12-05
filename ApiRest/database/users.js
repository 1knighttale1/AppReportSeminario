const mongoose = require("./connect");
const USERSCHEMA = {
    name: String,
    email: String,
    password: String,
    registerdate: Date,
    sex: String,
    address: String
}

const USERS = mongoose.model("users", USERSCHEMA);
module.exports = USERS;
/*
const USERS = mongoose.model("users", USERSCHEMA);
module.exports = {model: USERS, schema:USERSCHEMA};
*/