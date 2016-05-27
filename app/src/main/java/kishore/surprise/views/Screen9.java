package kishore.surprise.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import kishore.surprise.MainActivity;
import kishore.surprise.R;
import kishore.surprise.util.MyData;

public class Screen9 extends Activity implements View.OnClickListener {

    private final String TAG = Screen9.class.getName();
    private StringBuilder message = new StringBuilder();

    private TextView question;
    private Button yesButton;
    private Button noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen9);

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
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        message.append(question.getText().toString());
        message.append(button.getText().toString());
        message.append("\n");
        MyData.instance.insert(message);

        Intent intent = new Intent(this, Screen11.class);
        switch (view.getId()) {

            case R.id.buttonYes:
                switch (question.getText().toString()) {
                    case "Really?":
                        question.setText("Pakka?");
                        break;

                    case "Pakka?":
                        question.setText("Sure?");
                        break;

                    case "Sure?":
                        question.setText("Lock Madla?");
                        break;

                    case "Lock Madla?":
                        Toast.makeText(this, "Then you should try ALPENLIEBE, Anti-Boaring", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        break;

                    default:
                        question.setText("Really?");
                        break;
                }
                break;

            case R.id.buttonNo:
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
