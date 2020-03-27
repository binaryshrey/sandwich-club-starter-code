package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    //defining the variables
    private ImageView mSandwichImageView;
    private TextView mAlsoKnowAsTextView;
    private TextView mOriginTextView;
    private TextView mIngredientTextView;
    private TextView mDescriptionTextView;
    private Sandwich mSandwich;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //initializing
        mSandwichImageView = (ImageView)findViewById(R.id.image_iv);
        mAlsoKnowAsTextView = (TextView)findViewById(R.id.also_known_tv);
        mOriginTextView = (TextView)findViewById(R.id.origin_tv);
        mIngredientTextView = (TextView)findViewById(R.id.ingredients_tv);
        mDescriptionTextView = (TextView)findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        //storing the value of clicked item and passing it as json to parseSandwichJson()
        String json = sandwiches[position];
        mSandwich = JsonUtils.parseSandwichJson(json);
        if (mSandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        Picasso.with(this)
                .load(mSandwich.getImage())
                .into(mSandwichImageView);

        //setting the title
        setTitle(mSandwich.getMainName());
        //setting the text using setText()
        mAlsoKnowAsTextView.setText(TextUtils.join(", ", mSandwich.getAlsoKnownAs()));
        mOriginTextView.setText(mSandwich.getPlaceOfOrigin());
        mIngredientTextView.setText(TextUtils.join(", ", mSandwich.getIngredients()));
        mDescriptionTextView.setText(mSandwich.getDescription());

    }
}
