const fs = require('fs')
const bodyParser = require('body-parser')
const jsonServer = require('json-server')
const jwt = require('jsonwebtoken')

const server = jsonServer.create()

const router = jsonServer.router('./db.json')

const db = JSON.parse(fs.readFileSync('./db.json', 'UTF-8'))

const middlewares = jsonServer.defaults();
const PORT = process.env.PORT || 3000;


server.use(middlewares);


server.use(jsonServer.defaults());
server.use(bodyParser.urlencoded({extended: true}))
server.use(bodyParser.json())

const SECRET_KEY = '123456789'
const expiresIn = '1h'

function createToken(payload) {
    return jwt.sign(
        payload, 
        SECRET_KEY, 
        {expiresIn})
}

function verifyToken(token) {
    return jwt.verify(
        token, 
        SECRET_KEY,  
        (err, decode) => decode !== undefined ?  decode : err)
}

function isAuthenticated({email, password}){
    return db.users.findIndex(user => user.email === email && user.password === password) !== -1
}

server.post('/register', (req, res) => {
  const {username, email, password} = req.body;

  exist_user = db.users.findIndex(x => x.email === email)
  if(exist_user !== -1) {
    return res.status(401).json({
      status: 401,
      message: "email đã được sữ dụng vui lòng dùng email khác !!",
    })
  }

  const new_user = {
    'id': db.users.length+1,
    username,
    email,
    password
  }

  db.users.push(new_user);
  fs.writeFileSync('./db.json', JSON.stringify(db), () => {
    if (err) return console.log(err);
    console.log('writing to ' + fileName);
  })
  res.status(201).json({
    status: 201,
    message: "Success",
    data: new_user
  })
})

//login
server.post('/login', (req, res) => {
    // const {email, password} = req.body
    const email = req.body.email
    const password = req.body.password

    if (isAuthenticated({email, password}) === false) {
      const status = 401
      const message = 'mật khẩu hoặc email đã sai vui lòng kiểm tra lại !!'
      res.status(status).json({status, message})
      return
    }
    const access_token = createToken({email, password})
    res.status(200).json({
      status: 200,
      message: "Success",
      data: {
        access_token
      }
    })
})

server.use('/auth',(req, res, next) => {
  if (req.headers.authorization == undefined || req.headers.authorization.split(' ')[0] !== 'Bearer') {
    const status = 401
    const message = 'Bad authorization header'
    res.status(status).json({status, message})
    return
  }
  try {
    let verifyTokenResult;
     verifyTokenResult = verifyToken(req.headers.authorization.split(' ')[1]);

     if (verifyTokenResult instanceof Error) {
       const status = 401
       const message = 'Error: access_token is not valid'
       res.status(status).json({status, message})
       return
     }
     next()
  } catch (err) {
    const status = 401
    const message = 'Token đã hết hạn'
    res.status(status).json({status, message})
  }
})


//view all users
server.get('/auth/users', (req, res) => {
  res.status(200).json({
    status: 200,
    data: {
      "users" : db.users
    }
  })
})

//Xem thông tin user theo email
server.get('/auth/users/:email', ((req, res)=> {
  //let userdb = JSON.parse(fs.readFileSync('./database.json', 'UTF-8'));
	const email = req.params.email;
 
	const exist_email = db.users.findIndex(user =>  user.email == email)
	const result = db.users.filter(user =>  user.email == email)
	if (exist_email !== -1)
	{
		const status = 200
		return res.status(status).json({status, result})
	} else {
    return res.status(401).json({
      status: 401,
      message: "Email không được tìm thấy",
    })
}}))

//delete user by email
server.delete('/auth/users/:email', (req, res) => {
  const email = req.params.email

  const exist_email = db.users.findIndex(user =>  user.email == email)
  if(exist_email !== -1) {
    db.users.splice(exist_email, 1);

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    return res.status(204).json({
      status: 204,
      message: "Success",
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Email không được tìm thấy!!",
    })
  }

})

//view all orders
server.get('/auth/orders', (req, res) => {
  res.status(200).json({
    status: 200,
    message: "Success",
    data: {
      "orders" : db.orders
    }
  })
})

//view order by id
server.get('/auth/orders/:id', (req, res) => {
  const order_id = req.params.id

  const exist_order = db.orders.findIndex(order => order.id == order_id)
  if(exist_order !== -1) {
      res.status(200).json({
            status: 200,
            data: {
              'orders': db.orders[exist_order]
            }
          })
    } else {
      return res.status(401).json({
        status: 401,
        message: "Order not found!!",
      })
    }
  })
//{"giays":[{"id":1,"name":"The Russian","type":"fiction","available":true},{"id":2,"name":"Just as I Am","type":"non-fiction","available":false},{"id":3,"name":"The Vanishing Half","type":"fiction","available":true},{"id":4,"name":"The Midnight Library","type":"fiction","available":true},{"id":5,"name":"Untamed","type":"non-fiction","available":true},{"id":6,"name":"Viscount Who Loved Me","type":"fiction","available":true}],"orders":[{"id":1,"giayId":1,"customerName":"Evalyn_Schuppe51","quantity":1,"timestamp":1645417257150},{"id":2,"giayId":4,"quantity":1,"timestamp":1645628437612},{"id":3,"giayId":6,"customerName":"Billie Greenholt","quantity":1,"timestamp":1677578542007},{"id":4,"giayId":6,"customerName":"thangne update","quantity":1,"timestamp":1677578607510},{"id":5,"giayId":6,"customerName":"thg3","quantity":1,"timestamp":1677730293793},{"id":6,"giayId":1,"customerName":"thg3","quantity":1,"timestamp":1679660122681}],"users":[{"id":1,"usersname":"admin","email":"admin@gmail.com","password":"admin123"},{"id":2,"usersname":"chile","email":"chile@gmail.com","password":"chile"},{"id":3,"username":"thg","email":"thg@hmail.com","password":"12345"},{"id":4,"username":"thg2","email":"kaka@hmail.com","password":"12345"},{"id":5,"username":"thg3","email":"babaa@hmail.com","password":"12345"},{"id":7},{"id":7,"username":"thg3","email":"babgfa@hmail.com","password":"12345"},{"id":8,"username":"thg3","email":"babnnn@gmail.com","password":"12345"},{"id":9,"username":"thg3","email":"bannbvnn@gmail.com","password":"12345"}]}
//add new order
server.post('/auth/orders', (req, res) => {
  const {giayId, customerName} = req.body
  const exist_giay_id = db.giays.findIndex(giay => giay.id === giayId)

  if(exist_giay_id === -1) {
    return res.status(401).json({
      status: 401,
      message: "sản phẩm đã hết hàng , vui lòng chọn sản phẩm khác",
    })
  }

  const order_giay = db.giays[exist_giay_id]
  if(order_giay.available) {
    const new_order = {
      'id': db.orders.length+1,
      giayId,
      customerName,
      "quantity": 1,
      "timestamp": new Date().getTime()
    }
  
    db.orders.push(new_order);
    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })
    return res.status(200).json({
      status: 200,
      message: "Success",
      data: new_order
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "giay is out of stock!!",
    })
  }
})

//delete order by id
server.delete('/auth/orders/:id', (req, res) => {
  const order_id = req.params.id

  const exist_order = db.orders.findIndex(order => order.id == order_id)
  if(exist_order !== -1) {
    db.orders.splice(exist_order, 1);

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    return res.status(204).json({
      status: 204,
      message: "Success",
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Order not found!!",
    })
  }

})

//update username
server.patch('/auth/orders/:id', (req, res) => {
  const order_id = req.params.id
  const customerName = req.body.customerName

  const exist_order = db.orders.findIndex(order => order.id == order_id)
  if(exist_order !== -1) {
    db.orders[exist_order].customerName = customerName

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    res.status(200).json({
      status: 200,
      message: "Success",
      data: {
        'orders': db.orders[exist_order]
      }
    })
  } else {
    res.status(401).json({
      status: 401,
      message: "Order not found!!",
    })
  }

})

server.use(router)

server.listen(PORT, () => {
  console.log('Run Auth API Server')
})