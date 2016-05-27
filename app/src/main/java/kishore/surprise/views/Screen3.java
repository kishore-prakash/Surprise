package kishore.surprise.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import kishore.surprise.MainActivity;
import kishore.surprise.R;
import kishore.surprise.util.MyData;

public class Screen3 extends Activity implements View.OnClickListener {

    private final String TAG = Screen3.class.getName();
    private StringBuilder message = new StringBuilder();

    private TextView question;
    private Button yesButton;
    private Button noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen3);
        question = (TextView) findViewById(R.id.question);
        yesButton = (Button) findViewById(R.id.buttonYes);
        noButton = (Button) findViewById(R.id.buttonNo);

        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        message.append(question.getText().toString());
        message.append(": ");
        message.append(button.getText().toString());
        message.append("\n");
        MyData.instance.insert(message);
        final Intent intent = new Intent(this, Screen4.class);
        switch (view.getId()) {
            case R.id.buttonNo:
                Toast.makeText(this, "Even though you are not interested try the next one.", Toast.LENGTH_LONG).show();
                noButton.setVisibility(View.GONE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        startActivity(intent);
                    }
                }, 3500);
                break;

            case R.id.buttonYes:
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
