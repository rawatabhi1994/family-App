package com.example.abhirawat.yourfamily;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    private TextView tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tvBack = (TextView) findViewById(R.id.tvBack);

    }

    public void Back(View view) {
        finish();
    }
}
