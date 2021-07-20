package com.imesconsult.retrofit.warehouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.imesconsult.retrofit.warehouse.models.AuthModel;
import com.imesconsult.retrofit.warehouse.models.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //10.0.2.2 is a special alias to the host loopback interface
    private static final String BASE_URL = "http://10.0.2.2:3500";
    private static final String TAG = "MainActivity";
    private final String RETROFIT_TAG = "RETROFITWAREHOUSE";
    private String jwtToken;

    private List<Item> allItems = new ArrayList<>();
    private String[] allItemsStrings;
    ApiInterface apiInterface;
    ApiInterface securedApiInterface;
    private ListView listView;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getClient(BASE_URL, null).create(ApiInterface.class);

        listView = this.findViewById(R.id.listView);

        this.allItemsStrings = itemListToArrayString(this.allItems);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, allItemsStrings);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        Button signUpButton = this.findViewById(R.id.signUpBtn);
        Button loginButton = this.findViewById(R.id.loginBtn);
        Button getButton = this.findViewById(R.id.getAllItemsBtn);
        Button postButton = this.findViewById(R.id.postItemsButton);
        Button putButton = this.findViewById(R.id.putItemBtn);
        Button deleteButton = this.findViewById(R.id.deleteItemBtn);
        Button increaseButton = this.findViewById(R.id.increaseItemBtn);
        Button decreaseButton = this.findViewById(R.id.decreaseItemBtn);



        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //signUpToApi("test@email.com", "password");
                signUpToApi("mytestemail@gmail.com", "mytestpassword");
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loginToApi("test@email.com", "password");
                loginToApi("myflaskemail@gmail.com", "myflaskypassword");
            }
        });

//        signUpToApi("test2@email.com", "password");



        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(securedApiInterface == null){
                    Toast.makeText(MainActivity.this, "You need to login first", Toast.LENGTH_SHORT).show();
                    return;
                }

                Item it1 = new Item("German","German",12, (float) 30.0);
                Item it2 = new Item("No longer at ease","Wolesoyinka",50, (float) 100.0);

                postNewItem(it1);
                postNewItem(it2);
            }
        });

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(securedApiInterface == null){
                    Toast.makeText(MainActivity.this, "You need to login first", Toast.LENGTH_SHORT).show();
                    return;
                }

                getAllItems();
            }
        });




    }


    public String[] itemListToArrayString(List<Item> items){
        String[] strings = new String[items.size()];

        for (int i = 0; i < items.size(); i++) {
            strings[i] = items.get(i).toString();
        }
        return strings;
    }

    public void getAllItems(){
        Call<List<Item>> call = securedApiInterface.getAllItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful()){
                    allItems = response.body();
                    allItemsStrings = itemListToArrayString(allItems);
                    adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, allItemsStrings);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }


    public void postNewItem(Item item){
        Call<HashMap<String, String>> call = securedApiInterface.postNewItem(item);

        call.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                if(response.isSuccessful()){
                    String id = response.body().get("id");
                    Toast.makeText(MainActivity.this, "Item POST successful", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loginToApi(String email, String password){
       Call<HashMap<String, String>> call = apiInterface.loginToWarehouse(new AuthModel(email, password));

       call.enqueue(new Callback<HashMap<String, String>>() {
           @Override
           public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
               if(response.isSuccessful()){
                   jwtToken = response.body().get("token");
                   securedApiInterface = ApiClient.getClient(BASE_URL, jwtToken).create(ApiInterface.class);
                   Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

               }
               else{
                   Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
               Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
               Log.d(RETROFIT_TAG, t.toString());
           }
       });
    }


    public void signUpToApi(String email, String password){
        Call<HashMap<String, String>> call = apiInterface.createNewAccount(new AuthModel(email, password));

        call.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                    if(response.isSuccessful()){
                        String id = response.body().get("id");
                        Toast.makeText(MainActivity.this, "Created new account", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                Log.d(RETROFIT_TAG, t.toString());
            }
        });


    }
}