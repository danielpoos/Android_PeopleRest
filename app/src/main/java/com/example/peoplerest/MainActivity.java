package com.example.peoplerest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.peoplerest.databinding.ActivityMainBinding;
import com.example.peoplerest.databinding.PersonListItemBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    PersonListItemBinding bindingP;
    List<Person> people = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingP = PersonListItemBinding.inflate(getLayoutInflater());
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RequestTask task = new RequestTask();
        task.finalTask = () -> {
            binding.data.setAdapter(new PersonAdapter());
        };
        task.execute();
    }
    private class PersonAdapter extends ArrayAdapter<Person>{
        public PersonAdapter() {
            super(MainActivity.this, R.layout.person_list_item, people);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            bindingP = PersonListItemBinding.inflate(getLayoutInflater());
            Person actual = people.get(position);
            bindingP.personName.setText(actual.getName());
            bindingP.personAge.setText(String.format("(%d)",actual.getAge()));
            return bindingP.getRoot().getRootView();
        }
    }

    private class RequestTask extends AsyncTask<Void, Void, Response> {
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
            try{Thread.sleep(1000);}catch (InterruptedException e){
                Log.d("tag",e.toString());}
            /*try {
                response = RequestHandler.get("https://retoolapi.dev/E28xzM/people");
            } catch (IOException e) {
                Log.d("AAAAAAAAAA", e.toString());
            }
            this.response = */response = new Response(200, "[{\"id\":1,\"name\":\"Nelda Aufderhar\",\"email\":\"reilly.marcelina@example.org\",\"age\":24},{\"id\":2,\"name\":\"Margarita Runolfsson\",\"email\":\"mbeier@example.com\",\"age\":55},{\"id\":3,\"name\":\"Miss Stella Runte Jr.\",\"email\":\"frunolfsdottir@example.net\",\"age\":51},{\"id\":4,\"name\":\"Ms. Jenifer O'Keefe\",\"email\":\"armando.powlowski@example.org\",\"age\":23},{\"id\":5,\"name\":\"Elian Wisozk IV\",\"email\":\"unolan@example.net\",\"age\":80},{\"id\":6,\"name\":\"Dr. Kenton Auer\",\"email\":\"wfeeney@example.com\",\"age\":47},{\"id\":7,\"name\":\"Freddie Rau Sr.\",\"email\":\"uconsidine@example.com\",\"age\":32},{\"id\":8,\"name\":\"Melvina Breitenberg IV\",\"email\":\"qkuvalis@example.net\",\"age\":70},{\"id\":9,\"name\":\"Jayde Hodkiewicz\",\"email\":\"brady.gulgowski@example.net\",\"age\":53},{\"id\":10,\"name\":\"Brandon Aufderhar\",\"email\":\"reanna46@example.com\",\"age\":33},{\"id\":11,\"name\":\"Frieda Greenfelder\",\"email\":\"dfeil@example.org\",\"age\":22},{\"id\":12,\"name\":\"Mr. Ali Toy I\",\"email\":\"hermann.shanahan@example.net\",\"age\":35},{\"id\":13,\"name\":\"Mr. Dusty Osinski DDS\",\"email\":\"serena89@example.org\",\"age\":73},{\"id\":14,\"name\":\"Kiara Reilly\",\"email\":\"freddy.goodwin@example.com\",\"age\":23},{\"id\":15,\"name\":\"Barton Schimmel\",\"email\":\"schmeler.destini@example.com\",\"age\":54},{\"id\":16,\"name\":\"Mr. Garnet Wilkinson MD\",\"email\":\"bayer.kelley@example.org\",\"age\":55},{\"id\":17,\"name\":\"Prof. Davion Runte\",\"email\":\"thora.stroman@example.net\",\"age\":75},{\"id\":18,\"name\":\"Destini Gleason\",\"email\":\"turcotte.piper@example.org\",\"age\":32},{\"id\":19,\"name\":\"Norbert Treutel\",\"email\":\"sipes.kara@example.org\",\"age\":53},{\"id\":20,\"name\":\"Felicita Heidenreich Jr.\",\"email\":\"sean73@example.net\",\"age\":49}]");
            return response;
        }
        @Override protected void onPreExecute() {
            super.onPreExecute();
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        @Override protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            finalTask.run();
            binding.progressBar.setVisibility(View.GONE);
            Gson converter = new Gson();
            Person[] peopleArray = converter.fromJson(response.getContent(), Person[].class);
            people.clear();
            people.addAll(Arrays.asList(peopleArray));
        }
    }
}
