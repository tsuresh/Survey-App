package survey.suresh.com.survey;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class QAObj {

    public String q1ans;
    public String rstring;
    public String phone;

    public Map<String, Boolean> stars = new HashMap<>();

    public QAObj() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public QAObj(String q1ans, String rstring, String phone) {
        this.q1ans = q1ans;
        this.rstring = rstring;
        this.phone = phone;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("q1ans", q1ans);
        result.put("review", rstring);
        result.put("phone", phone);

        Date currentTime = Calendar.getInstance().getTime();
        result.put("time", currentTime);

        return result;
    }

}