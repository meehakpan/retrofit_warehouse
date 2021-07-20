# retrofit_warehouse

AN API DEVELOPMENT IN PYTHON, FLASK MONGO ENGINE

Setting up a commonuication from one software instance to another made possible through an API
An Application Programming Interface (API) a simplified  interface that defines types of request that can be made, how they are made and when they are being processed.

In this project, I developed an API that allows you to send a sequence of Post/Get/Put/Patch/Delete/Increase/Decrease request to different end points, and also return/modify data routed to the API.

I used flask frame work to create this RESTFUL API and POSTMAN to test it.

Setup
The API contains four endpoints, Signup, Login and Items and Item. The Singup allows me to registered user's details, and since I want users access to be authenticated by Oauth Bearers token where you users are authorized and authenticated via email and password to receive access token to enforce logins. Postman was useful to achieve that. The ltems and Items end points were specifically for the products in the Mongo DB.

Data
I use Mongo DB to store and I used Mongo DB Compass to enforce easy connectivity between Flask Server, Postman.
A sample my document DB look like this:

{"_id":{"$oid":"602195a29de94dd3066c5314"},"name":"Ponds","manufacturer":"Unilever","quantity":{"$numberInt":"12"},"price":{"$numberDouble":"10.0"}}

{"_id":{"$oid":"602195a29de94dd3066c5315"},"name":"Kreme Avokado","manufacturer":"Ziala","quantity":{"$numberInt":"12"},"price":{"$numberDouble":"10.0"}}

{"_id":{"$oid":"6021966b9de94dd3066c5316"},"name":"Ponds","manufacturer":"Unilever","quantity":{"$numberInt":"12"},"price":{"$numberDouble":"10.0"}}

{"_id":{"$oid":"6021966b9de94dd3066c5317"},"name":"Kreme Avokado","manufacturer":"Ziala","quantity":{"$numberInt":"12"},"price":{"$numberDouble":"10.0"}}

{"_id":{"$oid":"602196749de94dd3066c5318"},"name":"Ponds","manufacturer":"Unilever","quantity":{"$numberInt":"12"},"price":{"$numberDouble":"10.0"}}

To Initialize Python scripted Flask API
You need to import modules and initialize the API, like so:
# ok from now on you can choose env file here
# python will autmatically set the environment variable for you
# you don't have to set it up manually
os.environ['ENV_FILE_LOCATION'] = '.env'
app.config.from_envvar('ENV_FILE_LOCATION')
mail = Mail(app)

# imports requiring app and mail
from resources.routes import initialize_routes

api = Api(app, errors=errors)
bcrypt = Bcrypt(app)
jwt = JWTManager(app)

initialize_db(app)
initialize_routes(api)


Endpoints
As previously mentioned, my API will have four endpoints, Signup, Login, Items and Item.

The result of this is — if our API was located at www.api.com, communication with the Signup class would be provided at www.api.com/signup and Login at www.api.com/login and so on. To create an endpoint, we define a Python class or interface (with any name you want) and connect it to our desired endpoint like this:

public interface ApiInterface {

    @POST("/api/auth/login")
    Call<HashMap<String, String>> loginToWarehouse(@Body AuthModel auth);
    
Because flask needs to know that this class is an endpoint for our API, hence, you pass Resource in with the class definition like so: 


@POST("/api/auth/login")
    Call<HashMap<String, String>> loginToWarehouse(@Body AuthModel auth);


    @POST("/api/auth/signup")
    Call<HashMap<String, String>> createNewAccount(@Body AuthModel auth);

    @POST("/api/items")
    Call<HashMap<String, String>> postNewItem(@Body Item item);

Running a Local Server
Since I am writing out the API, I have to test it!
To do that, I have to host the API, which can be done locally by typing activating the enviromental variables
and running python run.py app.run like this:

(venv) C:\Users\imesc\movie-bag>python run.py

When we run.py we should see something like this

 * Tip: There are .env or .flaskenv files present. Do "pip install python-dotenv" to use them.
 * Serving Flask app "app" (lazy loading)
 * Environment: production
   WARNING: This is a development server. Do not use it in a production deployment.
   Use a production WSGI server instead.
 * Debug mode: off
 * Running on http://127.0.0.1:3500/ (Press CTRL+C to quit)https://github.com/meehakpan/retrofit_warehouse/tree/master

Once the server is setup, you can test the API as we build it using Postman, if you haven’t used it before it is the de-facto standard for API testing. And, don’t worry — it’s incredibly simple to use — download Postman from here: https://www.postman.com/downloads/
Before I proceed, you can find the full code here:

