package survey.suresh.com.survey;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class QAObj {

    public String q1ans;
    public String q2ans;
    public String q3ans;
    public String phone;

    public Map<String, Boolean> stars = new HashMap<>();

    public QAObj() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public QAObj(String q1ans, String q2ans, String q3ans, String phone) {
        this.q1ans = q1ans;
        this.q2ans = q2ans;
        this.q3ans = q3ans;
        this.phone = phone;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("q1ans", q1ans);
        result.put("q2ans", q2ans);
        result.put("q3ans", q3ans);
        result.put("phone", phone);

        Date currentTime = Calendar.getInstance().getTime();
        result.put("time", currentTime);

        return result;
    }

}