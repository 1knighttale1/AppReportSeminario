const mongoose = require("./connect");
const HOMESCHEMA = {
    ciudad: String,
    tipo: String,
    departamentos: Number,
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
    zona: String,
    colegio: String,
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