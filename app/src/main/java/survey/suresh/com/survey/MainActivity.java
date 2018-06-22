package survey.suresh.com.survey;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String q1ans;
    String reviewstr = "";

    ImageButton q1_ans1, q1_ans2, q1_ans3;
    Button q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12;

    TabHost tabs;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        q1_ans1 = findViewById(R.id.q1_ans_1);
        q1_ans2 = findViewById(R.id.q1_ans_2);
        q1_ans3 = findViewById(R.id.q1_ans_3);

        q1_ans1.setOnClickListener(this);
        q1_ans2.setOnClickListener(this);
        q1_ans3.setOnClickListener(this);

        q1 = findViewById(R.id.a1);
        q2 = findViewById(R.id.a2);
        q3 = findViewById(R.id.a3);
        q4 = findViewById(R.id.a4);
        q5 = findViewById(R.id.a5);
        q6 = findViewById(R.id.a6);
        q7 = findViewById(R.id.a7);
        q8 = findViewById(R.id.a8);
        q9 = findViewById(R.id.a9);
        q10 = findViewById(R.id.a10);
        q11 = findViewById(R.id.a11);
        q12 = findViewById(R.id.a12);

        q1.setOnClickListener(this);
        q2.setOnClickListener(this);
        q3.setOnClickListener(this);
        q4.setOnClickListener(this);
        q5.setOnClickListener(this);
        q6.setOnClickListener(this);
        q7.setOnClickListener(this);
        q8.setOnClickListener(this);
        q9.setOnClickListener(this);
        q10.setOnClickListener(this);
        q11.setOnClickListener(this);
        q12.setOnClickListener(this);

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
                tabs.setCurrentTab(2);
                break;
            case R.id.q1_ans_3:
                q1ans = "DELIGHTED";
                writeReview();
                break;
            case R.id.a1:
                reviewstr = q1.getText().toString();
                writeReview();
                break;
            case R.id.a2:
                reviewstr = q2.getText().toString();
                writeReview();
                break;
            case R.id.a3:
                reviewstr = q3.getText().toString();
                writeReview();
                break;
            case R.id.a4:
                reviewstr = q4.getText().toString();
                writeReview();
                break;
            case R.id.a5:
                reviewstr = q5.getText().toString();
                writeReview();
                break;
            case R.id.a6:
                reviewstr = q6.getText().toString();
                writeReview();
                break;
            case R.id.a7:
                reviewstr = q7.getText().toString();
                writeReview();
                break;
            case R.id.a8:
                reviewstr = q8.getText().toString();
                writeReview();
                break;
            case R.id.a9:
                reviewstr = q9.getText().toString();
                writeReview();
                break;
            case R.id.a10:
                reviewstr = q10.getText().toString();
                writeReview();
                break;
            case R.id.a11:
                reviewstr = q11.getText().toString();
                writeReview();
                break;
            case R.id.a12:
                reviewstr = q12.getText().toString();
                writeReview();
                break;

        }
    }

    private void writeReview() {

        final String[] phoneno = {""};

        if (q1ans.equals("DISSATISFIED")) {

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
                    QAObj post = new QAObj(q1ans, reviewstr, phoneno[0]);
                    Map<String, Object> postValues = post.toMap();
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put(key, postValues);

                    mDatabase.updateChildren(childUpdates);

                    Toast.makeText(MainActivity.this,"Thank you for your review.",Toast.LENGTH_SHORT).show();

                    q1ans = "";
                    reviewstr = "";

                    tabs.setCurrentTab(0);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                    String key = mDatabase.child("").push().getKey();
                    QAObj post = new QAObj(q1ans, reviewstr, phoneno[0]);
                    Map<String, Object> postValues = post.toMap();
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put(key, postValues);

                    mDatabase.updateChildren(childUpdates);

                    Toast.makeText(MainActivity.this,"Thank you for your review.",Toast.LENGTH_SHORT).show();

                    q1ans = "";
                    reviewstr = "";

                    tabs.setCurrentTab(0);
                }
            });

            builder.show();

        } else {

            String key = mDatabase.child("").push().getKey();
            QAObj post = new QAObj(q1ans, reviewstr, phoneno[0]);
            Map<String, Object> postValues = post.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put(key, postValues);

            mDatabase.updateChildren(childUpdates);

            Toast.makeText(MainActivity.this,"Thank you for your review.",Toast.LENGTH_SHORT).show();

            q1ans = "";
            reviewstr = "";

            tabs.setCurrentTab(0);

        }



    }
}
