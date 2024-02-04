package net.com.gopal.ecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import net.com.gopal.ecommerceapp.R;
import net.com.gopal.ecommerceapp.adapter.CategoryAdapter;
import net.com.gopal.ecommerceapp.adapter.ProductAdapter;
import net.com.gopal.ecommerceapp.databinding.ActivityMainBinding;
import net.com.gopal.ecommerceapp.models.Product;
import net.com.gopal.ecommerceapp.utilities.ApiURl;
import net.com.gopal.ecommerceapp.utilities.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
//    BottomNavigationView bottomNav;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;

    ProductAdapter productAdapter;
    ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
binding = ActivityMainBinding.inflate(getLayoutInflater());
setContentView(binding.getRoot());
category();
product();
slider();


//        bottomNav = findViewById(R.id.bottomNav);
//        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if (id == R.id.nav_home) {
//                    loadFrag(new HomeFragment());
//                } else if (id == R.id.nav_fav) {
//                    loadFrag(new FavoriteFragment());
//                } else if (id == R.id.nav_video) {
//                    loadFrag(new VideoFragment());
//                }else
//                    loadFrag(new ProfileFragment());
//
//                return false;
//            }
//        });
//
//
//        bottomNav.setSelectedItemId(R.id.nav_video);
//    }
//
//    public void loadFrag(androidx.fragment.app.Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.container, fragment);
//        fragmentTransaction.commit();
//    }

    }

    private void slider() {
        ImageSlider imageSlider = findViewById(R.id.image_slider);
        ArrayList<SlideModel> slideModels  = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.placeholder,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.placeholder,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.placeholder,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.placeholder,ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }


    void category(){

        categories = new ArrayList<>();
//        categories.add(new Category( "Sports ","https://www.iconfinder.com/icons/5488100/baseball_bat_sports_icon","#18ab4e","vghjfghjghj",1));
//        categories.add(new Category( "Sports ","https://www.iconfinder.com/icons/5488100/baseball_bat_sports_icon","#18ab4e","vghjfghjghj",1));
//        categories.add(new Category( "Sports ","https://www.iconfinder.com/icons/5488100/baseball_bat_sports_icon","#18ab4e","vghjfghjghj",1));
//        categories.add(new Category( "Sports ","https://www.iconfinder.com/icons/5488100/baseball_bat_sports_icon","#18ab4e","vghjfghjghj",1));

        categoryAdapter = new CategoryAdapter(this, categories);

        getCategory();
        binding.categoryRecycleView.setLayoutManager(new GridLayoutManager(this,4));
        binding.categoryRecycleView.setAdapter(categoryAdapter);
    }
    void getCategory(){
        RequestQueue data = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiURl.GET_CATEGORIES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.e("Successfull", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("success")){
                        JSONArray categoryArray = jsonObject.getJSONArray("categories");
                        for (int i=0; i<categoryArray.length(); i++){
                            JSONObject object = categoryArray.getJSONObject(i);
                            Category category =  new Category(
                                    object.getString("name"),
                                    ApiURl.CATEGORIES_IMAGE_URL + object.getString("icon"),
                                    object.getString("color"),
                                    object.getString("brief"),
                                    object.getInt("id")
                            );
                            categories.add(category);
                        }
                        categoryAdapter.notifyDataSetChanged();
                    }else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
        data.add(stringRequest);
    }
    void product(){
        products = new ArrayList<>();
        products.add(new Product("Baby Clothes","https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.glamourmagazine.co.uk%2Fphotos%2F64ba7203c24fb9a18ac297ef%2F16%3A9%2Fw_1920%2Ch_1080%2Cc_limit%2FBABY%2520CLOTHES%2520210723%2520default-.jpg&tbnid=NOyN1IwPu5aPoM&vet=12ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx..i&imgrefurl=https%3A%2F%2Fwww.glamourmagazine.co.uk%2Farticle%2Fbest-baby-clothes&docid=Znfxk0NDUFiF_M&w=1920&h=1080&q=clothes&ved=2ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx",
                "",5,10,150,1));
        products.add(new Product("Baby Clothes","https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.glamourmagazine.co.uk%2Fphotos%2F64ba7203c24fb9a18ac297ef%2F16%3A9%2Fw_1920%2Ch_1080%2Cc_limit%2FBABY%2520CLOTHES%2520210723%2520default-.jpg&tbnid=NOyN1IwPu5aPoM&vet=12ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx..i&imgrefurl=https%3A%2F%2Fwww.glamourmagazine.co.uk%2Farticle%2Fbest-baby-clothes&docid=Znfxk0NDUFiF_M&w=1920&h=1080&q=clothes&ved=2ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx",
                "",5,10,150,1));
        products.add(new Product("Baby Clothes","https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.glamourmagazine.co.uk%2Fphotos%2F64ba7203c24fb9a18ac297ef%2F16%3A9%2Fw_1920%2Ch_1080%2Cc_limit%2FBABY%2520CLOTHES%2520210723%2520default-.jpg&tbnid=NOyN1IwPu5aPoM&vet=12ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx..i&imgrefurl=https%3A%2F%2Fwww.glamourmagazine.co.uk%2Farticle%2Fbest-baby-clothes&docid=Znfxk0NDUFiF_M&w=1920&h=1080&q=clothes&ved=2ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx",
                "",5,10,150,1));
        products.add(new Product("Baby Clothes","https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.glamourmagazine.co.uk%2Fphotos%2F64ba7203c24fb9a18ac297ef%2F16%3A9%2Fw_1920%2Ch_1080%2Cc_limit%2FBABY%2520CLOTHES%2520210723%2520default-.jpg&tbnid=NOyN1IwPu5aPoM&vet=12ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx..i&imgrefurl=https%3A%2F%2Fwww.glamourmagazine.co.uk%2Farticle%2Fbest-baby-clothes&docid=Znfxk0NDUFiF_M&w=1920&h=1080&q=clothes&ved=2ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx",
                "",5,10,150,1));
        products.add(new Product("Baby Clothes","https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.glamourmagazine.co.uk%2Fphotos%2F64ba7203c24fb9a18ac297ef%2F16%3A9%2Fw_1920%2Ch_1080%2Cc_limit%2FBABY%2520CLOTHES%2520210723%2520default-.jpg&tbnid=NOyN1IwPu5aPoM&vet=12ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx..i&imgrefurl=https%3A%2F%2Fwww.glamourmagazine.co.uk%2Farticle%2Fbest-baby-clothes&docid=Znfxk0NDUFiF_M&w=1920&h=1080&q=clothes&ved=2ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx",
                "",5,10,150,1));
        products.add(new Product("Baby Clothes","https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.glamourmagazine.co.uk%2Fphotos%2F64ba7203c24fb9a18ac297ef%2F16%3A9%2Fw_1920%2Ch_1080%2Cc_limit%2FBABY%2520CLOTHES%2520210723%2520default-.jpg&tbnid=NOyN1IwPu5aPoM&vet=12ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx..i&imgrefurl=https%3A%2F%2Fwww.glamourmagazine.co.uk%2Farticle%2Fbest-baby-clothes&docid=Znfxk0NDUFiF_M&w=1920&h=1080&q=clothes&ved=2ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx",
                "",5,10,150,1));
        products.add(new Product("Baby Clothes","https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.glamourmagazine.co.uk%2Fphotos%2F64ba7203c24fb9a18ac297ef%2F16%3A9%2Fw_1920%2Ch_1080%2Cc_limit%2FBABY%2520CLOTHES%2520210723%2520default-.jpg&tbnid=NOyN1IwPu5aPoM&vet=12ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx..i&imgrefurl=https%3A%2F%2Fwww.glamourmagazine.co.uk%2Farticle%2Fbest-baby-clothes&docid=Znfxk0NDUFiF_M&w=1920&h=1080&q=clothes&ved=2ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx",
                "",5,10,150,1));
        products.add(new Product("Baby Clothes","https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.glamourmagazine.co.uk%2Fphotos%2F64ba7203c24fb9a18ac297ef%2F16%3A9%2Fw_1920%2Ch_1080%2Cc_limit%2FBABY%2520CLOTHES%2520210723%2520default-.jpg&tbnid=NOyN1IwPu5aPoM&vet=12ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx..i&imgrefurl=https%3A%2F%2Fwww.glamourmagazine.co.uk%2Farticle%2Fbest-baby-clothes&docid=Znfxk0NDUFiF_M&w=1920&h=1080&q=clothes&ved=2ahUKEwi66pez2_CDAxUWSmwGHaroDSsQMygCegQIARBx",
                "",5,10,150,1));

        productAdapter = new ProductAdapter(this, products);

        binding.productRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        binding.productRecyclerView.setAdapter(productAdapter);


    }
}