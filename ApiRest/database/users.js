const mongoose = require("./connect");
const USERSCHEMA = {
    ci: Number,
    name: String,
    password: String,
    email: String,
    phone: Number,
    sex: String,
    address: String,
    registerdate: Date
}

const USERS = mongoose.model("users", USERSCHEMA);
module.exports = USERS;
/*
const USERS = mongoose.model("users", USERSCHEMA);
module.exports = {model: USERS, schema:USERSCHEMA};
*/