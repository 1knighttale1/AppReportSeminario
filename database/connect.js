const mongose = require('mongoose');
mongose.connect("mongodb://172.21.0.2/AppReportSeminario", {
    useNewUrlParser: true
}).then(() => {
    console.log('Conection to mongodb successful');
}).catch(err => {
    console.log('Conection to mongodb failed');
});
module.exports = mongoose;