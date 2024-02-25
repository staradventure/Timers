package com.TwoFish.timers;

import static com.TwoFish.timers.Utils.transform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Button btn_set1;
    private Button btn_set2;
    private Button btn_set3;
    private Button btn_set4;
    private Button btn_set5;
    private Button btn_set6;
    private LinearLayout ll_set_timer1;
    private LinearLayout ll_set_timer2;
    private LinearLayout ll_set_timer3;
    private LinearLayout ll_set_timer4;
    private LinearLayout ll_set_timer5;
    private LinearLayout ll_set_timer6;
    private EditText et_hour1;
    private EditText et_hour2;
    private EditText et_hour3;
    private EditText et_hour4;
    private EditText et_hour5;
    private EditText et_hour6;
    private EditText et_minute1;
    private EditText et_minute2;
    private EditText et_minute3;
    private EditText et_minute4;
    private EditText et_minute5;
    private EditText et_minute6;
    private EditText et_second1;
    private EditText et_second2;
    private EditText et_second3;
    private EditText et_second4;
    private EditText et_second5;
    private EditText et_second6;
    private Button btn_start1;
    private Button btn_start2;
    private Button btn_start3;
    private Button btn_start4;
    private Button btn_start5;
    private Button btn_start6;
    private volatile TextView tv_timer1;
    private volatile TextView tv_timer2;
    private volatile TextView tv_timer3;
    private volatile TextView tv_timer4;
    private volatile TextView tv_timer5;
    private volatile TextView tv_timer6;
    private Button btn_pause_resume_1;
    private Button btn_pause_resume_2;
    private Button btn_pause_resume_3;
    private Button btn_pause_resume_4;
    private Button btn_pause_resume_5;
    private Button btn_pause_resume_6;
    private Button btn_reset1;
    private Button btn_reset2;
    private Button btn_reset3;
    private Button btn_reset4;
    private Button btn_reset5;
    private Button btn_reset6;
    private ScheduledExecutorService scheduler;
    private Handler handler = new Handler();
    private volatile boolean isPaused1 = false;
    private volatile boolean isPaused2 = false;
    private volatile boolean isPaused3 = false;
    private volatile boolean isPaused4 = false;
    private volatile boolean isPaused5 = false;
    private volatile boolean isPaused6 = false;

    private ScheduledFuture<?> timerFuture1;
    private ScheduledFuture<?> timerFuture2;
    private ScheduledFuture<?> timerFuture3;
    private ScheduledFuture<?> timerFuture4;
    private ScheduledFuture<?> timerFuture5;
    private ScheduledFuture<?> timerFuture6;

    private static final String CHANNEL_ID1 = "notification_channel1";
    private static final String CHANNEL_ID2 = "notification_channel2";
    private static final String CHANNEL_ID3 = "notification_channel3";
    private static final String CHANNEL_ID4 = "notification_channel4";
    private static final String CHANNEL_ID5 = "notification_channel5";
    private static final String CHANNEL_ID6 = "notification_channel6";
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findings();
        preparing();
        scheduler = Executors.newScheduledThreadPool(6);

        btn_set1.setOnClickListener(v -> {
            ll_set_timer1.setVisibility(ll_set_timer1.getVisibility() == LinearLayout.VISIBLE ? LinearLayout.GONE : LinearLayout.VISIBLE);
        });
        btn_set2.setOnClickListener(v -> {
            ll_set_timer2.setVisibility(ll_set_timer2.getVisibility() == LinearLayout.VISIBLE ? LinearLayout.GONE : LinearLayout.VISIBLE);
        });
        btn_set3.setOnClickListener(v -> {
            ll_set_timer3.setVisibility(ll_set_timer3.getVisibility() == LinearLayout.VISIBLE ? LinearLayout.GONE : LinearLayout.VISIBLE);
        });
        btn_set4.setOnClickListener(v -> {
            ll_set_timer4.setVisibility(ll_set_timer4.getVisibility() == LinearLayout.VISIBLE ? LinearLayout.GONE : LinearLayout.VISIBLE);
        });
        btn_set5.setOnClickListener(v -> {
            ll_set_timer5.setVisibility(ll_set_timer5.getVisibility() == LinearLayout.VISIBLE ? LinearLayout.GONE : LinearLayout.VISIBLE);
        });
        btn_set6.setOnClickListener(v -> {
            ll_set_timer6.setVisibility(ll_set_timer6.getVisibility() == LinearLayout.VISIBLE ? LinearLayout.GONE : LinearLayout.VISIBLE);
        });

        btn_start1.setOnClickListener(v -> {
            // Start Timer 1
            int hour = Integer.parseInt(!et_hour1.getText().toString().isEmpty() ? et_hour1.getText().toString() : "0");
            int minute = Integer.parseInt(!et_minute1.getText().toString().isEmpty()?et_minute1.getText().toString():"0");
            int second = Integer.parseInt(!et_second1.getText().toString().isEmpty()?et_second1.getText().toString():"0");
            int time = (hour * 3600) + (minute * 60) + second;
            // Timer 1
            timerFuture1=scheduler.scheduleAtFixedRate(new Runnable() {
                int seconds = time; // Start from 10 seconds

                @Override
                public void run() {
                    if (!isPaused1) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_timer1.setText(transform(seconds));
                            }
                        });
                        seconds--;
                        if (seconds <= 0) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv_timer1.setText("时间到！");
                                    playRingtone();
                                    createNotificationChannel(1);
                                    showNotification(1);
                                }
                            });
                        }
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
        });
        btn_start2.setOnClickListener(v -> {
            // Start Timer 2
            int hour = Integer.parseInt(!et_hour2.getText().toString().isEmpty() ? et_hour2.getText().toString() : "0");
            int minute = Integer.parseInt(!et_minute2.getText().toString().isEmpty()?et_minute2.getText().toString():"0");
            int second = Integer.parseInt(!et_second2.getText().toString().isEmpty()?et_second2.getText().toString():"0");
            int time = (hour * 3600) + (minute * 60) + second;
            // Timer 2
            timerFuture2=scheduler.scheduleAtFixedRate(new Runnable() {
                int seconds = time; // Start from 10 seconds

                @Override
                public void run() {
                    if (!isPaused2) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_timer2.setText(transform(seconds));
                            }
                        });
                        seconds--;
                        if (seconds <= 0) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv_timer2.setText("时间到！");
                                    playRingtone();
                                    createNotificationChannel(2);
                                    showNotification(2);
                                }
                            });
                        }
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
        });
        btn_start3.setOnClickListener(v -> {
            // Start Timer 3
            int hour = Integer.parseInt(!et_hour3.getText().toString().isEmpty() ? et_hour3.getText().toString() : "0");
            int minute = Integer.parseInt(!et_minute3.getText().toString().isEmpty()?et_minute3.getText().toString():"0");
            int second = Integer.parseInt(!et_second3.getText().toString().isEmpty()?et_second3.getText().toString():"0");
            int time = (hour * 3600) + (minute * 60) + second;
            // Timer 3
            timerFuture3=scheduler.scheduleAtFixedRate(new Runnable() {
                int seconds = time; // Start from 10 seconds

                @Override
                public void run() {
                    if (!isPaused3) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_timer3.setText(transform(seconds));
                            }
                        });
                        seconds--;
                        if (seconds <= 0) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv_timer3.setText("时间到！");
                                    playRingtone();
                                    createNotificationChannel(3);
                                    showNotification(3);
                                }
                            });
                        }
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
        });
        btn_start4.setOnClickListener(v -> {
            // Start Timer 4
            int hour = Integer.parseInt(!et_hour4.getText().toString().isEmpty() ? et_hour4.getText().toString() : "0");
            int minute = Integer.parseInt(!et_minute4.getText().toString().isEmpty()?et_minute4.getText().toString():"0");
            int second = Integer.parseInt(!et_second4.getText().toString().isEmpty()?et_second4.getText().toString():"0");
            int time = (hour * 3600) + (minute * 60) + second;
            // Timer 4
            timerFuture4=scheduler.scheduleAtFixedRate(new Runnable() {
                int seconds = time; // Start from 10 seconds

                @Override
                public void run() {
                    if (!isPaused4) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_timer4.setText(transform(seconds));
                            }
                        });
                        seconds--;
                        if (seconds <= 0) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv_timer4.setText("时间到！");
                                    playRingtone();
                                    createNotificationChannel(4);
                                    showNotification(4);
                                }
                            });
                        }
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
        });
        btn_start5.setOnClickListener(v -> {
            // Start Timer 5
            int hour = Integer.parseInt(!et_hour5.getText().toString().isEmpty() ? et_hour5.getText().toString() : "0");
            int minute = Integer.parseInt(!et_minute5.getText().toString().isEmpty()?et_minute5.getText().toString():"0");
            int second = Integer.parseInt(!et_second5.getText().toString().isEmpty()?et_second5.getText().toString():"0");
            int time = (hour * 3600) + (minute * 60) + second;
            // Timer 5
            timerFuture5=scheduler.scheduleAtFixedRate(new Runnable() {
                int seconds = time; // Start from 10 seconds

                @Override
                public void run() {
                    if (!isPaused5) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_timer5.setText(transform(seconds));
                            }
                        });
                        seconds--;
                        if (seconds <= 0) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv_timer5.setText("时间到！");
                                    playRingtone();
                                    createNotificationChannel(5);
                                    showNotification(5);
                                }
                            });
                        }
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
        });
        btn_start6.setOnClickListener(v -> {
            // Start Timer 6
            int hour = Integer.parseInt(!et_hour6.getText().toString().isEmpty() ? et_hour6.getText().toString() : "0");
            int minute = Integer.parseInt(!et_minute6.getText().toString().isEmpty()?et_minute6.getText().toString():"0");
            int second = Integer.parseInt(!et_second6.getText().toString().isEmpty()?et_second6.getText().toString():"0");
            int time = (hour * 3600) + (minute * 60) + second;
            // Timer 6
            timerFuture6=scheduler.scheduleAtFixedRate(new Runnable() {
                int seconds = time; // Start from 10 seconds

                @Override
                public void run() {
                    if (!isPaused6) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_timer6.setText(transform(seconds));
                            }
                        });
                        seconds--;
                        if (seconds <= 0) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv_timer6.setText("时间到！");
                                    playRingtone();
                                    createNotificationChannel(6);
                                    showNotification(6);
                                }
                            });
                        }
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
        });

        btn_pause_resume_1.setOnClickListener(v -> {
            // Pause/Resume Timer 1
            isPaused1= !isPaused1;
        });
        btn_pause_resume_2.setOnClickListener(v -> {
            // Pause/Resume Timer 2
            isPaused2= !isPaused2;
        });
        btn_pause_resume_3.setOnClickListener(v -> {
            // Pause/Resume Timer 3
            isPaused3= !isPaused3;
        });
        btn_pause_resume_4.setOnClickListener(v -> {
            // Pause/Resume Timer 4
            isPaused4= !isPaused4;
        });
        btn_pause_resume_5.setOnClickListener(v -> {
            // Pause/Resume Timer 5
            isPaused5= !isPaused5;
        });
        btn_pause_resume_6.setOnClickListener(v -> {
            // Pause/Resume Timer 6
            isPaused6= !isPaused6;
        });

        btn_reset1.setOnClickListener(v -> {
            // Reset Timer 1
            if (timerFuture1 != null) timerFuture1.cancel(false);
            et_hour1.setText("");
            et_minute1.setText("");
            et_second1.setText("");
            tv_timer1.setText("");
            isPaused1=false;
        });
        btn_reset2.setOnClickListener(v -> {
            // Reset Timer 2
            if (timerFuture2 != null) timerFuture2.cancel(false);
            et_hour2.setText("");
            et_minute2.setText("");
            et_second2.setText("");
            tv_timer2.setText("");
            isPaused2=false;
        });
        btn_reset3.setOnClickListener(v -> {
            // Reset Timer 3
            if (timerFuture3 != null) timerFuture3.cancel(false);
            et_hour3.setText("");
            et_minute3.setText("");
            et_second3.setText("");
            tv_timer3.setText("");
            isPaused3=false;
        });
        btn_reset4.setOnClickListener(v -> {
            // Reset Timer 4
            if (timerFuture4 != null) timerFuture4.cancel(false);
            et_hour4.setText("");
            et_minute4.setText("");
            et_second4.setText("");
            tv_timer4.setText("");
            isPaused4=false;
        });
        btn_reset5.setOnClickListener(v -> {
            // Reset Timer 5
            if (timerFuture5 != null) timerFuture5.cancel(false);
            et_hour5.setText("");
            et_minute5.setText("");
            et_second5.setText("");
            tv_timer5.setText("");
            isPaused5=false;
        });
        btn_reset6.setOnClickListener(v -> {
            // Reset Timer 6
            if (timerFuture6 != null) timerFuture6.cancel(false);
            et_hour6.setText("");
            et_minute6.setText("");
            et_second6.setText("");
            tv_timer6.setText("");
            isPaused6=false;
        });
    }

    private void preparing() {
        ll_set_timer1.setVisibility(LinearLayout.GONE);
        ll_set_timer2.setVisibility(LinearLayout.GONE);
        ll_set_timer3.setVisibility(LinearLayout.GONE);
        ll_set_timer4.setVisibility(LinearLayout.GONE);
        ll_set_timer5.setVisibility(LinearLayout.GONE);
        ll_set_timer6.setVisibility(LinearLayout.GONE);
    }

    private void findings() {
        btn_set1 = findViewById(R.id.btn_set1);
        btn_set2 = findViewById(R.id.btn_set2);
        btn_set3 = findViewById(R.id.btn_set3);
        btn_set4 = findViewById(R.id.btn_set4);
        btn_set5 = findViewById(R.id.btn_set5);
        btn_set6 = findViewById(R.id.btn_set6);
        ll_set_timer1 = findViewById(R.id.ll_set_timer1);
        ll_set_timer2 = findViewById(R.id.ll_set_timer2);
        ll_set_timer3 = findViewById(R.id.ll_set_timer3);
        ll_set_timer4 = findViewById(R.id.ll_set_timer4);
        ll_set_timer5 = findViewById(R.id.ll_set_timer5);
        ll_set_timer6 = findViewById(R.id.ll_set_timer6);
        et_hour1 = findViewById(R.id.et_hour1);
        et_hour2 = findViewById(R.id.et_hour2);
        et_hour3 = findViewById(R.id.et_hour3);
        et_hour4 = findViewById(R.id.et_hour4);
        et_hour5 = findViewById(R.id.et_hour5);
        et_hour6 = findViewById(R.id.et_hour6);
        et_minute1 = findViewById(R.id.et_minute1);
        et_minute2 = findViewById(R.id.et_minute2);
        et_minute3 = findViewById(R.id.et_minute3);
        et_minute4 = findViewById(R.id.et_minute4);
        et_minute5 = findViewById(R.id.et_minute5);
        et_minute6 = findViewById(R.id.et_minute6);
        et_second1 = findViewById(R.id.et_second1);
        et_second2 = findViewById(R.id.et_second2);
        et_second3 = findViewById(R.id.et_second3);
        et_second4 = findViewById(R.id.et_second4);
        et_second5 = findViewById(R.id.et_second5);
        et_second6 = findViewById(R.id.et_second6);
        btn_start1 = findViewById(R.id.btn_start1);
        btn_start2 = findViewById(R.id.btn_start2);
        btn_start3 = findViewById(R.id.btn_start3);
        btn_start4 = findViewById(R.id.btn_start4);
        btn_start5 = findViewById(R.id.btn_start5);
        btn_start6 = findViewById(R.id.btn_start6);
        tv_timer1 = findViewById(R.id.tv_timer1);
        tv_timer2 = findViewById(R.id.tv_timer2);
        tv_timer3 = findViewById(R.id.tv_timer3);
        tv_timer4 = findViewById(R.id.tv_timer4);
        tv_timer5 = findViewById(R.id.tv_timer5);
        tv_timer6 = findViewById(R.id.tv_timer6);
        btn_pause_resume_1 = findViewById(R.id.btn_pause_resume_1);
        btn_pause_resume_2 = findViewById(R.id.btn_pause_resume_2);
        btn_pause_resume_3 = findViewById(R.id.btn_pause_resume_3);
        btn_pause_resume_4 = findViewById(R.id.btn_pause_resume_4);
        btn_pause_resume_5 = findViewById(R.id.btn_pause_resume_5);
        btn_pause_resume_6 = findViewById(R.id.btn_pause_resume_6);
        btn_reset1 = findViewById(R.id.btn_reset1);
        btn_reset2 = findViewById(R.id.btn_reset2);
        btn_reset3 = findViewById(R.id.btn_reset3);
        btn_reset4 = findViewById(R.id.btn_reset4);
        btn_reset5 = findViewById(R.id.btn_reset5);
        btn_reset6 = findViewById(R.id.btn_reset6);
    }

    private void playRingtone() {
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        mediaPlayer.start();
    }

    private void createNotificationChannel(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel";
            String description = "Default channel";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(giveString2(id), name, importance);
            channel.setDescription(description);
            channel.setSound(null,null);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void showNotification(int id) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, giveString2(id))
                .setSmallIcon(R.drawable.baseline_timer_off_24)
                .setContentTitle("时间到！")
                .setContentText(giveString(id))
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    private String giveString(int id){
        switch (id){
            case 1:
                return "Timer 1 时间到！";
            case 2:
                return "Timer 2 时间到！";
            case 3:
                return "Timer 3 时间到！";
            case 4:
                return "Timer 4 时间到！";
            case 5:
                return "Timer 5 时间到！";
            case 6:
                return "Timer 6 时间到！";
        }
        return "时间到！";
    }

    private String giveString2(int id){
        switch (id){
            case 1:
                return CHANNEL_ID1;
            case 2:
                return CHANNEL_ID2;
            case 3:
                return CHANNEL_ID3;
            case 4:
                return CHANNEL_ID4;
            case 5:
                return CHANNEL_ID5;
            case 6:
                return CHANNEL_ID6;
        }
        return "时间到！";
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
    }
}