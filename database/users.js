const mongoose = require("./connect");
const USERSCHEMA = {
    name: {
        type: String,
        required: [true, 'You must put a name'],
        match: /^[a-z]{3,16}$/,
    },

    email: {
        type: String,
        required: 'Email is missing',
        match: /^(([^<>()\[\]\.,;:\s @\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i,
    },

    password: {
        type: String,
        required: ['Password is required'],
    },
    registerdate: {
        type: Date,
        default: Date.now()
    },
    sex: {
        type: String,
    },
    address: {
        type: String,
    }

}
const USERS = mongoose.model("users", USERSCHEMA);
module.export = USERS;