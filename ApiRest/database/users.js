const mongoose = require("./connect");
const USERSCHEMA = {
    name: String,
    email: String,
    password: String,
    phone: Number,
    sex: String,
    address: String,
    registerdate: Date,
    tipo: String
}

const USERS = mongoose.model("users", USERSCHEMA);
module.exports = USERS;
/*
const USERS = mongoose.model("users", USERSCHEMA);
module.exports = {model: USERS, schema:USERSCHEMA};
*/