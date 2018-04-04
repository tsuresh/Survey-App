package survey.suresh.com.survey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "Smiley";

    String q1ans, q2ans, q3ans;

    Button l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14;

    TabHost tabs;
    DatabaseReference mDatabase;
    SmileRating q1;
    SmileRating q3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        q1 = findViewById(R.id.q1);
        q3 = findViewById(R.id.q3);

        l1= findViewById(R.id.lan1);
        l2= findViewById(R.id.lan2);
        l3= findViewById(R.id.lan3);
        l4= findViewById(R.id.lan4);
        l5= findViewById(R.id.lan5);
        l6= findViewById(R.id.lan6);
        l7= findViewById(R.id.lan7);
        l8= findViewById(R.id.lan8);
        l9= findViewById(R.id.lan9);
        l10= findViewById(R.id.lan10);
        l11= findViewById(R.id.lan11);
        l12= findViewById(R.id.lan12);
        l13= findViewById(R.id.lan13);
        l14= findViewById(R.id.lan14);


        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        l5.setOnClickListener(this);
        l6.setOnClickListener(this);
        l7.setOnClickListener(this);
        l8.setOnClickListener(this);
        l9.setOnClickListener(this);
        l10.setOnClickListener(this);
        l11.setOnClickListener(this);
        l12.setOnClickListener(this);
        l13.setOnClickListener(this);
        l14.setOnClickListener(this);


        tabs = findViewById(R.id.tabhost); //Id of tab host
        tabs.setHorizontalScrollBarEnabled(true);

        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("Q1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Q1");
        tabs.addTab(spec);

        TabHost.TabSpec spec1 = tabs.newTabSpec("Q2");
        spec1.setContent(R.id.tab2);
        spec1.setIndicator("Q2");
        tabs.addTab(spec1);

        TabHost.TabSpec spec2 = tabs.newTabSpec("Q3");
        spec2.setContent(R.id.tab3);
        spec2.setIndicator("Q3");
        tabs.addTab(spec2);

        // Write a message to the database
        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        */

        q1.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {

                tabs.setCurrentTab(1);

                switch (smiley) {
                    case SmileRating.BAD:
                        q1ans = "bad";
                        Log.i(TAG, "Bad");
                        break;
                    case SmileRating.GOOD:
                        q1ans = "good";
                        Log.i(TAG, "Good");
                        break;
                    case SmileRating.GREAT:
                        q1ans = "great";
                        Log.i(TAG, "Great");
                        break;
                    case SmileRating.OKAY:
                        q1ans = "okay";
                        Log.i(TAG, "Okay");
                        break;
                    case SmileRating.TERRIBLE:
                        q1ans = "terrible";
                        Log.i(TAG, "Terrible");
                        break;
                }
            }
        });

        q3.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {

                switch (smiley) {
                    case SmileRating.BAD:
                        q3ans = "bad";
                        Log.i(TAG, "Bad");
                        break;
                    case SmileRating.GOOD:
                        q3ans = "good";
                        Log.i(TAG, "Good");
                        break;
                    case SmileRating.GREAT:
                        q3ans = "great";
                        Log.i(TAG, "Great");
                        break;
                    case SmileRating.OKAY:
                        q3ans = "okay";
                        Log.i(TAG, "Okay");
                        break;
                    case SmileRating.TERRIBLE:
                        q3ans = "terrible";
                        Log.i(TAG, "Terrible");
                        break;
                }
                Toast.makeText(MainActivity.this,"Your review has been successfully posted.",Toast.LENGTH_LONG).show();
                writeReview();
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.lan1:
                q2ans = "lan1";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan2:
                q2ans = "lan2";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan3:
                q2ans = "lan3";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan4:
                q2ans = "lan4";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan5:
                q2ans = "lan5";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan6:
                q2ans = "lan6";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan7:
                q2ans = "lan7";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan8:
                q2ans = "lan8";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan9:
                q2ans = "lan9";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan10:
                q2ans = "lan10";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan11:
                q2ans = "lan11";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan12:
                q2ans = "lan12";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan13:
                q2ans = "lan13";
                tabs.setCurrentTab(2);
                break;
            case R.id.lan14:
                q2ans = "lan14";
                tabs.setCurrentTab(2);
                break;
        }
    }

    private void writeReview() {

        String key = mDatabase.child("").push().getKey();
        QAObj post = new QAObj(q1ans, q2ans, q3ans);
        Map<String, Object> postValues = post.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, postValues);

        mDatabase.updateChildren(childUpdates);

        q1.setSelectedSmile(BaseRating.NONE);
        q3.setSelectedSmile(BaseRating.NONE);

        q1ans = "";
        q2ans = "";
        q3ans = "";

        tabs.setCurrentTab(0);

    }
}
