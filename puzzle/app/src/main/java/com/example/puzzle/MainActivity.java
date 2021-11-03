package com.example.puzzle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.puzzle.view.GameView;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    int level = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = (GameView) this.findViewById(R.id.game_view);
        gameView.setOnFinishListener(new GameView.OnFinishListener() {
            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "恭喜你完成拼图！", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.level_1) {
            level = 3;
            gameView.setLevel(level);
        } else if (id == R.id.level_2) {
            level = 4;
            gameView.setLevel(level);
        } else if (id == R.id.level_3) {
            level = 5;
            gameView.setLevel(level);
        }
        return super.onOptionsItemSelected(item);
    }
}