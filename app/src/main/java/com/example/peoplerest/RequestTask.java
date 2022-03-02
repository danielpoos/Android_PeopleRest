package com.example.peoplerest;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

public class RequestTask extends AsyncTask<Void, Void, Response> {
    Response response;
    Runnable finalTask;
    public Runnable getFinalTask() {
        return finalTask;
    }
    public void setFinalTask(Runnable finalTask) {
        this.finalTask = finalTask;
    }
    public RequestTask(){
        this.finalTask = null;
    }
    @Override protected Response doInBackground(Void... voids) {
        Response response = null;
        try {
            response = RequestHandler.get("https://retoolapi.dev/E28xzM/people");
        } catch (IOException e) {
            Log.d("AAAAAAAAAA", e.toString());
        }
        this.response = response;
        return response;
    }
    @Override protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        finalTask.run();
    }
}
