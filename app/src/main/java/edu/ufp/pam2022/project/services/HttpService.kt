package edu.ufp.pam2022.project.services
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import edu.ufp.pam2022.project.services.repositoryVolley.VolleyRequest
import org.json.JSONException
import org.json.JSONObject


class HttpService(App: AppCompatActivity) {
    enum class HttpAsyncMethod {
        GET, POST, PUT
    }
    private val urlStr="https://localhost:8000"

    private var volleyRequestQueue: RequestQueue
    private var farruscoHelper: FarruscoHelper? =null

    init
     {
             //=================== Setup Volley to make async HTTP Request ===================
             Log.e(this.javaClass.simpleName, "onCreate(): going to set VOLLEY context...")
             // Get RequestQueue to execute async calls
             this.volleyRequestQueue = VolleyRequest.getInstance(App).requestQueue
             //Create a helper class with context to execute http requests
             farruscoHelper = FarruscoHelper(volleyRequestQueue,App)
     }

    fun getMainMovieTableContent(){
        val testQueryStr = "/movies";
        //2. Use Volley async HttpRequest

        farruscoHelper?.launchVolleyAsyncHttpRequest(urlStr,testQueryStr,HttpAsyncMethod.GET)
    }
    fun logIn(Email: String,Password:String): Any? {
        val testQueryStr = "/login"
        //2. Use Volley async HttpRequest
        val json=JSONObject()
        try {
            json.put("email", Email)
            json.put("password", Password)
        } catch (e: JSONException) {
            e.printStackTrace()
          }
    return  farruscoHelper?.launchVolleyAsyncHttpRequest(urlStr,testQueryStr,HttpAsyncMethod.POST,json)


    }
     fun register(Username:String,Email: String,Password:String){
        val testQueryStr = "/register"
        //2. Use Volley async HttpRequest
        val json=JSONObject()
        try {
            json.put("Username", Username)
            json.put("Email", Email)
            json.put("Password", Password)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        farruscoHelper?.launchVolleyAsyncHttpRequest(urlStr,testQueryStr,HttpAsyncMethod.POST,json)
    }
    private  class FarruscoHelper(
        private var volleyRequestQueue: RequestQueue,App: AppCompatActivity
        ){
            val TAG_TO_CANCEL_HTTP_REQUEST = "TAG_TO_CANCEL_HTTP_REQUEST"

             /**
              * Volley uses and async task but delivers parsed responses on the main thread,
              * which may be convenient for populating UI controls with received data.
               * However, this may be critical to many important semantics provided by the library,
              * particularly related to canceling requests... done on Activity onStop().
              *
              * Please check also the Cronet Library, which provides Chromium network stack to perform
               * network operations in Android apps:
              *   implementation 'com.google.android.gms:play-services-cronet:16.0.0'
               */
              fun launchVolleyAsyncHttpRequest(urlStr: String, queryStr: String, type:HttpAsyncMethod,Json: JSONObject): Any{

                  val url = "$urlStr$queryStr"
                  Log.e(this.javaClass.simpleName, "launchVolleyAsyncHttpRequest(): url=$url")

                  when (type) {
                         HttpAsyncMethod.POST -> {
                           val stringRequest = JsonObjectRequest(
                             Request.Method.POST, url, Json, { //Handle Response
                                   response ->
                              Log.e(
                                  this.javaClass.simpleName,
                                  "launchVolleyAsyncHttpRequest(): Response.Listener Response=${response}"
                                  )
                             }, { //Handle Error
                                error ->
                            Log.e(
                                this.javaClass.simpleName,
                                "launchVolleyAsyncHttpRequest(): Response.Listener Error=$error"
                            )
                        }
                    )
                    // Set the cancel tag on the request
                    stringRequest.tag = TAG_TO_CANCEL_HTTP_REQUEST
                    // Add the request to the RequestQueue.
                             volleyRequestQueue.add(stringRequest)
                    return stringRequest
                }
                    HttpAsyncMethod.PUT -> {
                    val stringRequest = JsonObjectRequest(
                        Request.Method.PUT, url, Json,
                        { //Handle Response
                                response ->
                            Log.e(
                                this.javaClass.simpleName,
                                "launchVolleyAsyncHttpRequest(): Response.Listener Response=${response}"
                            )
                        },
                        { //Handle Error
                                error ->
                            Log.e(
                                this.javaClass.simpleName,
                                "launchVolleyAsyncHttpRequest(): Response.Listener Error=$error"
                            )
                        }
                    )
                    // Set the cancel tag on the request

                    stringRequest.tag = TAG_TO_CANCEL_HTTP_REQUEST
                    // Add the request to the RequestQueue.
                        volleyRequestQueue.add(stringRequest)
                    return stringRequest
                }
                else -> return ""
            }
        }
        fun launchVolleyAsyncHttpRequest(urlStr: String, queryStr: String, type:HttpAsyncMethod): Any{

            val url = "$urlStr$queryStr"
            Log.e(this.javaClass.simpleName, "launchVolleyAsyncHttpRequest(): url=$url")

            when (type) {
                HttpAsyncMethod.GET-> {
                    val stringRequest = StringRequest(
                        Request.Method.GET, url,
                        { //Handle Response
                                response ->
                            Log.e(this.javaClass.simpleName,
                                "launchVolleyAsyncHttpRequest(): Response.Listener Response=${response}")

                        },
                        { //Handle Error
                                error ->
                            Log.e(this.javaClass.simpleName,
                                "launchVolleyAsyncHttpRequest(): Response.Listener Error=$error")
                        }
                    )
                    // Set the cancel tag on the request
                    stringRequest.tag = TAG_TO_CANCEL_HTTP_REQUEST
                    // Add the request to the RequestQueue.
                    return  stringRequest
                }
                else-> return ""
            }
        }
    }

}