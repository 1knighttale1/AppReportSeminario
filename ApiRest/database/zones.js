const mongoose = require("./connect");
const ZONESCHEMA = {
    id: Number,
    departamento: String,
    nombre: String,
    zoom: Number,
    lat: Number,
    lng: Number,
    coordenadas: [{
        lat: [],
        long: []
        }]
}

const ZONES = mongoose.model("zones", ZONESCHEMA);
module.exports = ZONES;