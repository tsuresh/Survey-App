package survey.suresh.com.survey;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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
    ImageButton q1_ans1, q1_ans2, q1_ans3, q3_ans1, q3_ans2, q3_ans3;
    ListView sportslist;

    String sports[] = {"Hiking and Trekking", "Football", "Running", "Rugby", "Volleyball", "Basketball","Gym and Fitness","Cricket","Badminton","Swimming","Snorkeling","Tennis","Table Tennis","Cycling"};

    TabHost tabs;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        q1_ans1 = findViewById(R.id.q1_ans_1);
        q1_ans2 = findViewById(R.id.q1_ans_2);
        q1_ans3 = findViewById(R.id.q1_ans_3);

        sportslist = findViewById(R.id.sportslist);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textView, sports);
        sportslist.setAdapter(arrayAdapter);
        sportslist.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                q2ans = sports[i];
                tabs.setCurrentTab(2);
            }
        });

        q3_ans1 = findViewById(R.id.q3_ans_1);
        q3_ans2 = findViewById(R.id.q3_ans_2);
        q3_ans3 = findViewById(R.id.q3_ans_3);


        q1_ans1.setOnClickListener(this);
        q1_ans2.setOnClickListener(this);
        q1_ans3.setOnClickListener(this);

        q3_ans1.setOnClickListener(this);
        q3_ans2.setOnClickListener(this);
        q3_ans3.setOnClickListener(this);



        tabs = findViewById(R.id.tabhost); //Id of tab host
        tabs.setHorizontalScrollBarEnabled(true);

        tabs.setup();
        tabs.setActivated(false);

        TabHost.TabSpec spec = tabs.newTabSpec("QUESTION 1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("QUESTION 1");
        tabs.addTab(spec);

        TabHost.TabSpec spec1 = tabs.newTabSpec("QUESTION 2");
        spec1.setContent(R.id.tab2);
        spec1.setIndicator("QUESTION 2");
        tabs.addTab(spec1);

        TabHost.TabSpec spec2 = tabs.newTabSpec("QUESTION 3");
        spec2.setContent(R.id.tab3);
        spec2.setIndicator("QUESTION 3");
        tabs.addTab(spec2);

        // Write a message to the database
        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        */



        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.q1_ans_1:
                q1ans = "DISSATISFIED";
                tabs.setCurrentTab(1);
                break;
            case R.id.q1_ans_2:
                q1ans = "SATISFIED";
                tabs.setCurrentTab(1);
                break;
            case R.id.q1_ans_3:
                q1ans = "DELIGHTED";
                tabs.setCurrentTab(1);
                break;
            case R.id.q3_ans_1:
                q3ans = "DISSATISFIED";
                writeReview();
                break;
            case R.id.q3_ans_2:
                q3ans = "SATISFIED";
                writeReview();
                break;
            case R.id.q3_ans_3:
                q3ans = "DELIGHTED";
                writeReview();
                break;
        }
    }

    private void writeReview() {

        final String[] phoneno = {""};

        if(q1ans.equals("DISSATISFIED") || q3ans.equals("DISSATISFIED")){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Please enter your phone number.");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    phoneno[0] = input.getText().toString();

                    String key = mDatabase.child("").push().getKey();
                    QAObj post = new QAObj(q1ans, q2ans, q3ans, phoneno[0]);
                    Map<String, Object> postValues = post.toMap();
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put(key, postValues);

                    mDatabase.updateChildren(childUpdates);

                    Toast.makeText(MainActivity.this,"Thank you for your review.",Toast.LENGTH_SHORT).show();

                    q1ans = "";
                    q2ans = "";
                    q3ans = "";

                    tabs.setCurrentTab(0);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                    String key = mDatabase.child("").push().getKey();
                    QAObj post = new QAObj(q1ans, q2ans, q3ans, phoneno[0]);
                    Map<String, Object> postValues = post.toMap();
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put(key, postValues);

                    mDatabase.updateChildren(childUpdates);

                    Toast.makeText(MainActivity.this,"Thank you for your review.",Toast.LENGTH_SHORT).show();

                    q1ans = "";
                    q2ans = "";
                    q3ans = "";

                    tabs.setCurrentTab(0);
                }
            });

            builder.show();

        } else {

            String key = mDatabase.child("").push().getKey();
            QAObj post = new QAObj(q1ans, q2ans, q3ans, phoneno[0]);
            Map<String, Object> postValues = post.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put(key, postValues);

            mDatabase.updateChildren(childUpdates);

            Toast.makeText(MainActivity.this,"Thank you for your review.",Toast.LENGTH_SHORT).show();

            q1ans = "";
            q2ans = "";
            q3ans = "";

            tabs.setCurrentTab(0);

        }



    }
}
