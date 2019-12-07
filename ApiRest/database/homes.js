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
    calle: String,
    precio: Number,
    descripcion: String,
    fecha_construccion: Date,
    lat: Number,
    lng: Number,
    vecindario: String,
    imagen: String,
    galeria: Array,
    contacto: Number,
    fecha_registro: {
        type:Date,
        default:Date.now()
    }
}

const HOMES = mongoose.model("homes", HOMESCHEMA);
module.exports = HOMES;