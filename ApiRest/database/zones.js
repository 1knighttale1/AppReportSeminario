const mongoose = require("./connect");
const ZONESCHEMA = {
    nombre: String,
    ciudad:String,
    zoom: Number,
    lat: Number,
    lng: Number,
    coordlat: Array,
    coordlng: Array,
   /* coordenadas:[{
        lat:[],
        lng:[]
    }],  no por ahora :'v*/
}

const ZONES = mongoose.model("zones", ZONESCHEMA);
module.exports = ZONES;