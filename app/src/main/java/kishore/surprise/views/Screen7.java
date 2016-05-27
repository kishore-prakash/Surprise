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

public class Screen7 extends Activity implements View.OnClickListener {

    private final String TAG = Screen7.class.getName();

    private TextView question;
    private Button counterButton;
    private Button resetButton;
    private Button next;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen7);

        question = (TextView) findViewById(R.id.question);
        counterButton = (Button) findViewById(R.id.counter_button);
        resetButton = (Button) findViewById(R.id.reset_counter);
        next = (Button) findViewById(R.id.next);

        counterButton.setOnClickListener(this);
        next.setOnClickListener(this);
        resetButton.setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.next:
                if (count == 26) {
                    Intent intent = new Intent(this, Screen8.class);
                    startActivity(intent);
                } else {
                    for (int i=0; i < 2; i++)
                        Toast.makeText(this, "Hey you lazy, didn't count rite? Actually the counter is not working properly." +
                                "Reset and start again", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.reset_counter:
                count = 0;
                counterButton.setText(Integer.toString(count));
                break;

            case R.id.counter_button:
                count++;
                if (count > 21) {
                    counterButton.setText(Integer.toString(count-1));
                } else if (count < 16) {
                    counterButton.setText(Integer.toString(count));
                }
                break;
        }
    }
}
