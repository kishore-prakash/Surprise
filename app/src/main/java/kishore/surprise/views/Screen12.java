package kishore.surprise.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import kishore.surprise.MainActivity;
import kishore.surprise.R;
import kishore.surprise.util.MyData;

public class Screen12 extends Activity implements View.OnClickListener {

    private final String TAG = Screen12.class.getName();
    private StringBuilder message = new StringBuilder();

    private TextView question;
    private DatePicker datePicker;
    private Button nextButton;

    private int birthDay = 26;
    private int birthMonth = 10;
    private int birthYear = 1992;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen12);

        question = (TextView) findViewById(R.id.question);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
//        datePicker.updateDate(1992, 7, 1);

        nextButton = (Button) findViewById(R.id.next);
        nextButton.setOnClickListener(this);

        birthDay = Integer.parseInt(getResources().getString(R.string.birth_day));
        birthMonth = Integer.parseInt(getResources().getString(R.string.birth_month));
        birthYear = Integer.parseInt(getResources().getString(R.string.birth_year));
    }

    @Override
    public void onClick(View v) {
        message.append(question.getText().toString());
        message.append(": ");
        message.append(datePicker.getDayOfMonth()+"-");
        message.append(datePicker.getMonth()+"-");
        message.append(datePicker.getYear());
        message.append("\n");
        MyData.instance.insert(message);

        if (datePicker.getDayOfMonth() == birthDay && datePicker.getMonth() == birthMonth-1 && datePicker.getYear() == birthYear) {
            nextButton.setVisibility(View.GONE);
            final Intent intent = new Intent(this, VideoActivity.class);
            Handler handler = new Handler();
            Toast.makeText(this, "WOW, Today is your birthday.", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Then why don't you increase the volume", Toast.LENGTH_LONG).show();
            handler.postDelayed(new Runnable() {
                public void run() {
                    startActivity(intent);
                }
            }, 5000);
        } else {
            Toast.makeText(this, "Hey liar, I know your birthday.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
