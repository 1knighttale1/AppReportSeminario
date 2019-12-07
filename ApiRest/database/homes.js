const mongoose = require("./connect");
const HOMESCHEMA = {
    ciudad: String,
    tipo: String,
    Condominio:Boolean,
    departamentos: String,
    estado: String,
    habitaciones: Number,
    baños: Number,
    superficie: Number,
    antiguedad: Number,
    calle: String,
    precio: Number,
    descripcion: String,
    lat: Number,
    long: Number,
    vecindario: String,
    galeria: Array,
    contacto: Number,
    fecha: {
        type:Date,
        default:Date.now()
    }
}

const HOMES = mongoose.model("homes", HOMESCHEMA);
module.exports = HOMES;