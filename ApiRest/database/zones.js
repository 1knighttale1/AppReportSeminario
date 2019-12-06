const mongoose = require("./connect");
const ZONESCHEMA = {
    id: String,
    departamento: String,
    nombre: String,
    zoom: Number,
    lat: Number,
    long: Number,
    coordenadas: [

    ]
}

const ZONES = mongoose.model("zones", ZONESCHEMA);
module.exports = ZONES;