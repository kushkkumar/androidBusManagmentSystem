/********************************************************************Connections************************************************************************************************ */

const express=require('express');
const app=express()
app.use(express.json())

const bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));



const { Client } = require('pg');

const client = new Client({
    connectionString: 'postgres://fqzflraoeyxebj:44942fdb5e7611a57ce4ded25b9677a9a3a029abcb58f88b2cffe9c4909e6f81@ec2-184-72-236-57.compute-1.amazonaws.com:5432/dc4ch3b43u5jnn',
    ssl: true,
    ssl:{
        rejectUnauthorized:false
    }
  });
  client.connect();

//**********************************************************************************************************************************************************************************************//



//************************************************************Customer app Requests***************************************************************************************************************** */

//customer registration form

app.post('/customerRegistration',(request,response)=>{
    customerRegistration(request,response);
})

//customer login credentials

app.post('/loginRegistration',(request,response)=>{
    

    const uname=request.body.username;
    const pass=request.body.password;

    client.query("select cust_user_name, cust_password,balance from customer where cust_user_name = '"+uname+"' and cust_password = '"+pass+"';",(err,result)=>{

        if(err) response.status(400);
        else{

            response.status(202).send(result.rows[0]);
        }
    });
})

//update user balance

app.post('/addMoney',(req,response)=>{
    const uname=req.body.username;
    const balupdate=req.body.balupdate;
    var number=parseInt(balupdate);
    console.log(balupdate);
    console.log(uname);
    client.query("select balance from customer where cust_user_name ='"+uname+"';",(error,result)=>{
        if(error) throw err;
        number+=(result.rows[0]['balance'])
        console.log(number)
        client.query("update customer set balance="+number+" where cust_user_name = '"+uname+"';",(err,res)=>{
            if(err) response.status(500).send();
            else
            response.status(502).send();
        })
     });

   
    })



    app.post("/paymentDetails",(request,response)=>{
        const username=request.body.username;
        client.query("select cust_id from customer where cust_user_name='"+username+"';",(err,re)=>{
            if(err) throw err;
            else{
                var custid=re.rows[0]['cust_id'];       
        client.query("select * from transaction where cust_id="+custid+";",(error,res)=>{
            if(error) throw error;
            else{
                response.send(JSON.stringify(res.rows));
                console.log(JSON.stringify(res.rows[0]));
            }
        })
    }
})
    })



function customerRegistration(request,response){
   
    const password=request.body.password;
    const name=request.body.name;
    const dob=request.body.dob; 
    const phone=request.body.phone;
    const email=request.body.email;
    
    client.query("select * from customer",(err,res)=>{
        if(err) throw err;
        const username=name+((res.rowCount+1).toString());
        console.log(username);

        client.query("insert into customer(cust_name,cust_dob,cust_phone_no,cust_email_id,cust_user_name,cust_password,balance) values ('"+name+"','"+dob+"',"+phone+",'"+email+"','"+username+"','"+password+"',100);",(error,result)=>{
            if(error){
                response.status(204).send();
                console.log(error)
            }

            else{
            response.status(202).send({
                cust_user_name:username,
                balance:"100"
            });
            console.log("1 data inserted");
        }
        })

    });
}

/************************************************************************************************************************************************************************** */ 



/*******************************************************************Conductor App requests********************************************************************************************************* */



app.post("/conductorLogin",(request,response)=>{
    const uname=request.body.username;
    const password =request.body.password;
    console.log(uname)

    client.query("select * from bus where bus_user_name ='"+uname+"' and bus_password='"+password+"';",(err,res)=>{
        if(err) response.status(200).send();
        else{
            response.status(202).send(res.rows[0]);
        }
    })
})

app.post("/checkCustomerPresent",(request,response)=>{
    const uname=request.body.username;
    console.log(uname);

    client.query("select * from customer where cust_user_name='"+uname+"';",(err,res)=>{
        if(err) response.status(600).send();
        else{
            if(res.rows[0]['balance']>=200){
                response.status(200).send(res.rows[0]);
            }
            else{
                response.status(604).send();
            }
        }
    })
})





app.post('/transactionDetails',(request,response)=>{

    const busid=parseInt(request.body.busid);
    console.log(busid)
    client.query("SELECT * FROM transaction where bus_id="+busid+";", (err, res) => {
        if (err) throw err;
       response.send(JSON.stringify(res.rows));
       
      });

})


app.post('/routeDetails',(request,response)=>{
    client.query("SELECT * FROM bus_route;", (err, res) => {
        if (err) throw err;
       response.send(JSON.stringify(res.rows));
       console.log(JSON.stringify(res.rows))
       
      });
});




app.post('/billingDetails',(request,response)=>{
    var routeFrom=request.body.routeFrom;
    var routeTo=request.body.routeTo;
    console.log(routeFrom,routeTo);
    client.query("SELECT * FROM route_table where route_start='"+routeFrom+"' and route_end='"+routeTo+"';", (err, res) => {
        if(err){ response.status(202).send();
            console.log(err)}
       response.status(200).send(res.rows[0]);
       console.log(JSON.stringify(res.rows[0]))
       
      });
})


app.post("/insertToTransaction",(request,response)=>{
    busid=parseInt(request.body.busid);
    custid=parseInt(request.body.custid);
    routeid=parseInt(request.body.routeid)
    coh=parseInt(request.body.coh);
    cph=parseInt(request.body.cph);
    transDate=request.body.transDate
    total=coh*cph

    client.query("insert into transaction(bus_id,cust_id,route_id,count_of_head,cost_per_head,total_cost,transaction_date) values ("+busid+","+custid+","+routeid+","+coh+","+cph+","+total+",'"+transDate+"');",(err,result)=>{
        if (err) throw err;
        else
        response.send();
        console.log("s")
    })
})
















/********************************************************************************************************************************************************************************* */




/******************************************************************************All the tables to be displayed in server***************************************************************************** */
//display tables

app.get('/table/customer',(request,response)=>{
    client.query('select * from customer;',(err,result)=>{
        if(err) throw err;
        response.send(result.rows);
        client.end;
    })
})

app.get('/table/bus',(request,response)=>{
    client.query('select * from bus;',(err,result)=>{
        if(err) throw err;
        response.send(result.rows);
        client.end;
    })
})

app.get('/table/admin',(request,response)=>{
    client.query('select * from admin;',(err,result)=>{
        if(err) throw err;
        response.send(result.rows);
        client.end;
    })
})

app.get('/table/route_table',(request,response)=>{
    client.query('select * from route_table;',(err,result)=>{
        if(err) throw err;
        response.send(result.rows);
        client.end;
    })
})

app.get('/table/transaction',(request,response)=>{
    client.query('select * from transaction;',(err,result)=>{
        if(err) throw err;
        response.send(result.rows);
        client.end;
    })
})



//first page
app.get('/',(req,response)=>{
    response.send("hello")  
    uname="kush7"
    
})


app.get('/table/bus_route',(req,res)=>{
    client.query('select * from bus_route;',(err,result)=>{
        if(err) throw err;
        res.send(result.rows);
    })
})

/*********************************************************************************************************************************************************************** */


app.listen(process.env.PORT||8000,()=>console.log("its working ")) ////recieiving port
