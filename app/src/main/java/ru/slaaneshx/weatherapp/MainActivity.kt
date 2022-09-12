package ru.slaaneshx.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import ru.slaaneshx.weatherapp.databinding.ActivityMainBinding

const val API_KEY = "1bdee3c77c0f428bb10103155221406"
const val WEATHER_URL = "https://api.weatherapi.com/v1/current.json"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bGet.setOnClickListener {
            getResponse()
        }
    }

    private fun getResponse(name: String = "London") {
        val url = "$WEATHER_URL?key=$API_KEY&q=$name&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val responswJSON = JSONObject(response)
                var temperature = responswJSON.getJSONObject("current").getString("temp_c")
                Log.d("MyLog", temperature)
            },
            { Log.e("WeatherErrorLog", "Volley error $it") })
        queue.add(stringRequest)
    }
}