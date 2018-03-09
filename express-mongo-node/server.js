const express = require('express');
const bodyParser = require('body-parser');
const MongoClient = require('mongodb').MongoClient;
const app = express();
app.set('view engine', 'ejs')

app.use(bodyParser.urlencoded({
    extended: true
}));
//mongodb://localhost:27017/mydatabase
MongoClient.connect('mongodb://dbuser:123@ds027618.mlab.com:27618/practicedb', (err, client) => {
    if (err) 
    return console.log(err);
    db = client.db('practicedb'); // whatever your database name is
    app.listen(3000, () => {
        console.log('listening on 3000')
    })
});
app.get('/', (req, res) => {
    db.collection('quotes').find().toArray((err, result) => {
        if (err) return console.log(err)
        // renders index.ejs
        res.render('index.ejs', {quotes: result})
      })
});

app.post('/', (req, res) => {
    db.collection('quotes').save(req.body, (err, result) => {
      if (err) return console.log(err)
  
      console.log('saved to database')
      res.redirect('/')
    })
  })

app.get('/index', (req, res) => {
    console.log(__dirname);
    res.sendFile(__dirname + '/index.html');
});

  app.get('/quotes', (req, res) => {
      res.send('list page')
      db.collection('quotes').find().toArray(function(err, results) {
        console.log(results)
        // send HTML file populated with quotes here
      })
  })