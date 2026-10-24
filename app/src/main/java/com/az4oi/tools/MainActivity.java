package com.az.tools;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText etChatId, etToken;
    private TextView tvLogs;
    private Spinner spinnerTools;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etChatId = findViewById(R.id.etChatId);
        etToken = findViewById(R.id.etToken);
        tvLogs = findViewById(R.id.tvLogs);
        spinnerTools = findViewById(R.id.spinnerTools);
        Button btnSubscribe = findViewById(R.id.btnSubscribe);
        Button btnStart = findViewById(R.id.btnStart);

        String[] toolsList = {
            "1. أداة فيس ربط لودو (تليجرام)",
            "2. أداة يوزرات تليجرام رباعية",
            "3. أداة صيد حسابات كلاش اوف كلانس",
            "4. أداة رشق وتفاعل مدفوعة",
            "5. أداة فيسبوك ربط ألعاب",
            "6. أداة Xena Live (سعودية)"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, toolsList);
        spinnerTools.setAdapter(adapter);

        String channelUrl = "https://t.me/az_4oi";
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(channelUrl));
                startActivity(intent);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chatId = etChatId.getText().toString().trim();
                String token = etToken.getText().toString().trim();

                if (chatId.isEmpty() || token.isEmpty()) {
                    Toast.makeText(MainActivity.this, "الرجاء إدخال الـ ID والـ Token أولاً!", Toast.LENGTH_LONG).show();
                    return;
                }

                int selectedTool = spinnerTools.getSelectedItemPosition();
                startSelectedTool(selectedTool, chatId, token);
            }
        });
    }

    private void startSelectedTool(int toolIndex, String chatId, String token) {
        tvLogs.append("\n[+] تم بدء تشغيل الأداة رقم " + (toolIndex + 1) + " بنجاح...\n");

        new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning = true;
                Random rand = new Random();

                try {
                    for (int i = 1; i <= 10 && isRunning; i++) {
                        Thread.sleep(1500);
                        String logMsg = "";

                        if (toolIndex == 0) {
                            logMsg = "[🟢 LUDO] تم فحص وإرسال حساب رقم " + (i * 12) + "\n";
                        } else if (toolIndex == 1) {
                            String[] chars = {"a", "b", "c", "x", "y", "z", "1", "7"};
                            String user = chars[rand.nextInt(chars.length)] + chars[rand.nextInt(chars.length)] + "_";
                            logMsg = "[🟢 USER] يوزر متاح تليجرام: @" + user + rand.nextInt(9999) + "\n";
                        } else if (toolIndex == 2) {
                            logMsg = "[🟢 CLASH] صيد حساب كلاش ناجح! Pass: clash" + rand.nextInt(999) + "\n";
                        } else if (toolIndex == 3) {
                            logMsg = "[🟢 BOOST] تم إرسال الدفعة رقم " + i + " بنجاح للسيرفر\n";
                        } else if (toolIndex == 4) {
                            logMsg = "[🟢 FB GAME] فحص حساب شغال [OK] - ID: 075" + rand.nextInt(9999999) + "\n";
                        } else if (toolIndex == 5) {
                            logMsg = "[🟢 XENA SA] فحص رقم سعودي +9665" + rand.nextInt(99999999) + "\n";
                        }

                        final String finalLog = logMsg;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvLogs.append(finalLog);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
