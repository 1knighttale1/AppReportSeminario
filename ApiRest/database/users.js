const mongoose = require("./connect");
const USERSCHEMA = {
    name: String,
    password: String,
    email: String,
    phone: Number,
    sex: String,
    address: String,
    registerdate: Date.now(),
    tipo: String
}

const USERS = mongoose.model("users", USERSCHEMA);
module.exports = USERS;
/*
const USERS = mongoose.model("users", USERSCHEMA);
module.exports = {model: USERS, schema:USERSCHEMA};
*/