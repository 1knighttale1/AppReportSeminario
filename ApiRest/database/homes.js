const mongoose = require("./connect");
const HOMESCHEMA = {
    id: Number,
    ciudad: String,
    tipo: String,
    estado: String,
    cuartos: Number,
    baños: Number,
    superficie: Number,
    antiguedad: Number,
    street: String,
    descripcion: String,
    price: Number,
    lat: String,
    lon: String,
    neighborhood: String,
    gallery: Array,
    contact: Number,
    date: {
        type:Date,
        default:Date.now()
    }
}

const HOMES = mongoose.model("homes", HOMESCHEMA);
module.exports = HOMES;