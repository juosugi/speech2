package com.example.myapplication;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.speech.*;
import java.util.HashMap;
import java.util.Locale;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

//  音声認識
// http://android-java.hatenablog.jp/entry/2018/03/27/115126

//  音声合成
// いろいろ・・・

public class MainActivity extends AppCompatActivity {

    // 音声合成用
    private android.speech.tts.TextToSpeech mTts;

    // 音声認識用
    private static final int REQUEST_CODE = 12345;
    private TextView text1;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mTts = new android.speech.tts.TextToSpeech(this,null);

        //レイアウトにあるボタンを取得
        text1 = (TextView)findViewById(R.id.text1);
        Button button1 = (Button)findViewById(R.id.button1);

        //上で取得したボタンにクリックイベントを実装
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //音声認識用のインテントを作成
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //認識する言語を指定（この場合は日本語）
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.JAPANESE.toString());
                //認識する候補数の指定
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
                //音声認識時に表示する案内を設定
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "話してください");
                //音声認識を開始
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        //レイアウトにあるボタンを取得
        Button button2 = (Button)findViewById(R.id.button2);

        //上で取得したボタンにクリックイベントを実装
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  音声合成する
                speek();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        speek();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void speek(){
        //HashMap<String, String> params = new HashMap<String, String>();
        android.os.Bundle params = new Bundle();
        params.putString(TextToSpeech.Engine.KEY_PARAM_VOLUME, String.valueOf(0.8));

        Locale locale = Locale.JAPAN;
        mTts.setLanguage(locale);

        if(message.equals("")){
            message = "最初に音声認識してみてください。";
        }
//        mTts.speak("こんにちは。今日はいい天気です。", android.speech.tts.TextToSpeech.QUEUE_FLUSH, params,"messageID");
        mTts.speak(message, android.speech.tts.TextToSpeech.QUEUE_FLUSH, params,"messageID");
    }

    //音声認識が終わると自動で呼び出されるメソッド
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            //data から音声認識の結果を取り出す（リスト形式で）
            ArrayList<String> kekka = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            //認識結果が一つ以上ある場合はテキストビューに結果を表示する
            if (kekka.size() > 0) {
                //一番最初にある認識結果を表示する
                text1.setText(kekka.get(0));
                message = kekka.get(0);
            } else {
                //何らかの原因で音声認識に失敗した場合はエラーメッセージを表示
                text1.setText("音声の認識に失敗しました…");
            }
        }
    }
}
