const mongoose = require("./connect");
const ZONESCHEMA = {
    nombre: String,
    ciudad:String,
    zoom: Number,
    lat: Number,
    lng: Number,
    coordenadas:[{
        lat:[],
        lng:[]
    }],
}

const ZONES = mongoose.model("zones", ZONESCHEMA);
module.exports = ZONES;