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
    calle: String,
    descripcion: String,
    precio: Number,
    lat: Number,
    long: Number,
    vecindario: String,
    imagen: String,
    galeria: Array,
    contacto: Number,
    fecha: {
        type:Date,
        default:Date.now()
    }
}

const HOMES = mongoose.model("homes", HOMESCHEMA);
module.exports = HOMES;