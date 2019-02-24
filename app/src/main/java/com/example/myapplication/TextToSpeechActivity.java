package com.example.myapplication;

import java.util.Locale;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

//public class TextToSpeechActivity extends Activity implements android.speech.tts.TextToSpeech OnInitListener {

//　　@Override
//　　public void onInit(int status) {
//　　　　// TODO Auto-generated method stub
//　　　　if(status == TextToSpeech.SUCCESS) {
//　　　　　　// 言語をUSに設定
//　　　　　　Locale locale = Locale.US;
//　　　　　　if(tts.isLanguageAvailable(locale) >= TextToSpeech.LANG_AVAILABLE) {
//　　　　　　　　tts.setLanguage(locale);
//　　　　　　}
//　　　　　　else {
//　　　　　　　　Log.e("TTS", "Not support locale.");
//　　　　　　}
//　　　　else {
//　　　　　　Log.e("TTS", "Init error.");
//　　　　}
//　　}
//
//}
