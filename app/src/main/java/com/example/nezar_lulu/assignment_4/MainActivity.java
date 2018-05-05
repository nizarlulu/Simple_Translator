package com.example.nezar_lulu.assignment_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText from = findViewById(R.id.FROM);
        final EditText to = findViewById(R.id.TO);
        final Button ar = findViewById(R.id.AR);
        final Button en = findViewById(R.id.EN);
        final String from_ar = "ar";
        final String to_en = "en";
        final String from_en = "en";
        final String to_ar = "ar";
        final String languagePair_ar = from_ar + "-" + to_en;
        final String languagePair_en = from_en + "-" + to_ar;
        final String yandexKey = "trnsl.1.1.20171218T080426Z.d1886a5a2674537d.6fb35d4e765f28e550a8fd45e0be0e4bb3bda429";
        requestQueue = Volley.newRequestQueue(this);

        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = from.getText().toString();
                String text2 = text1.replaceAll(" ", "%13");
                String yandexUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexKey
                        + "&text=" + text2 + "&lang=" + languagePair_ar;

                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.GET, yandexUrl, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("JSON", "onResponse:response:" + response);
                                try {
                                    String translation = response.getString("text");
                                    translation = translation.substring(translation.indexOf('[') + 1);
                                    translation = translation.substring(0, translation.indexOf("]"));
                                    translation = translation.substring(translation.indexOf("\"") + 1);
                                    translation = translation.substring(0, translation.indexOf("\""));
                                    to.setText(translation);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("JSON", "onErrorResponse:error", error);
                            }
                        });
                requestQueue.add(jsObjRequest);

            }
        });
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = from.getText().toString();
                String text2 = text1.replaceAll(" ", "%13");
                String yandexUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexKey
                        + "&text=" + text2 + "&lang=" + languagePair_en;

                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.GET, yandexUrl, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("JSON", "onResponse:response:" + response);
                                try {
                                    String translation = response.getString("text");
                                    translation = translation.substring(translation.indexOf('[') + 1);
                                    translation = translation.substring(0, translation.indexOf("]"));
                                    translation = translation.substring(translation.indexOf("\"") + 1);
                                    translation = translation.substring(0, translation.indexOf("\""));
                                    to.setText(translation);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("JSON", "onErrorResponse:error", error);
                            }
                        });
                requestQueue.add(jsObjRequest);
            }
        });

    }

}
