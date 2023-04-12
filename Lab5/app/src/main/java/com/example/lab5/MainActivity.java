package com.example.lab5;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.edtTimePicker);
        EditText editText2 = findViewById(R.id.edtDatePicker);
        //hiện time daypicker dialog
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        ImageView imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.btnSubmit);
        TextView textView = findViewById(R.id.txtProcess);
        Button buttonDialog1 = findViewById(R.id.btnDialog1);
        Button buttonDialog2 = findViewById(R.id.btnDialog2);
        Button buttonDialog3 = findViewById(R.id.btnDialog3);
        Button buttonDialog4 = findViewById(R.id.btnDialog4);
        Button buttonDialog5 = findViewById(R.id.btnDialog5);

// Set default date for DatePickerDialog to current date
        editText2.setText(day + "/" + (month + 1) + "/" + year);

        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //nếu ngày hoặc tháng nhỏ hơn 10 thì thêm 0 vào trước
                                String day = String.valueOf(dayOfMonth);
                                String mon = String.valueOf(month + 1);
                                if (dayOfMonth < 10) {
                                    day = "0" + day;
                                }
                                if (month + 1 < 10) {
                                    mon = "0" + mon;
                                }
                                String date = day + "/" + mon + "/" + year;
                                editText2.setText(date);
                            }
                        }, year, month, day);

                datePickerDialog.show();
            }

        });

        //hiện time picker dialog


        editText.setText(hour + ":" + minute);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //nếu giờ hoặc phút nhỏ hơn 10 thì thêm 0 vào trước
                                String hour = String.valueOf(hourOfDay);
                                String min = String.valueOf(minute);
                                if (hourOfDay < 10) {
                                    hour = "0" + hour;
                                }
                                if (minute < 10) {
                                    min = "0" + min;
                                }
                                String time = hour + ":" + min;
                                editText.setText(time);
                            }
                        }, hour, minute, true);

                timePickerDialog.show();
            }
        });


        CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Tính toán giá trị tiến độ và cập nhật thanh progress bar
                int progress = (int) ((10000 - millisUntilFinished) / 100);
                progressBar.setProgress(progress);
                textView.setText(progress + "%");

            }

            public void onFinish() {
                // Xử lý khi quá trình download hoàn thành
                progressBar.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);


            }


        };

        //button download
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi người dùng nhấn nút download
                progressBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
                countDownTimer.start();

            }
        });

        //dialog 1
        buttonDialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo"); //thiết lập tiêu đề
                builder.setIcon(R.drawable.dialog_icon); //thiết lập icon
                builder.setMessage("Bạn có muốn thoát không?"); //thiết lập nội dung
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Thoát Thành Công!", Toast.LENGTH_SHORT).show();
                    }
                }); //thiết lập nút có
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Không Thoát!", Toast.LENGTH_SHORT).show();
                    }
                }); //thiết lập nút không
                builder.setNeutralButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Hủy Thành Công!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                }); //thiết lập nút hủy
                builder.setCancelable(false); //không cho phép hủy dialog bằng nút back
                builder.show(); //hiển thị dialog

            }
        });

        //dialog 2
        buttonDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mang[] = {"Chó", "Mèo", "Gà", "Vịt", "Heo"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Chọn con vật mà bạn thích"); //thiết lập tiêu đề
                builder.setIcon(R.drawable.dialog_icon); //thiết lập icon
                builder.setItems(mang, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Bạn thích " + mang[which], Toast.LENGTH_SHORT).show();
                    }
                }); //thiết lập danh sách các lựa chọn
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Hủy Thành Công!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                }); //thiết lập nút hủy
                builder.setCancelable(false); //không cho phép hủy dialog bằng nút back
                builder.show(); //hiển thị dialog


            }
        });

        //dialog 3 //single choice
        buttonDialog3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mang[] = {"Chó", "Mèo", "Gà", "Vịt", "Heo"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Chọn con vật mà bạn thích"); //thiết lập tiêu đề
                builder.setIcon(R.drawable.dialog_icon); //thiết lập icon
                builder.setSingleChoiceItems(mang, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Bạn thích " + mang[which], Toast.LENGTH_SHORT).show();
                    }
                }); //thiết lập danh sách các lựa chọn
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Hủy Thành Công!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                }); //thiết lập nút hủy
                builder.setCancelable(false); //không cho phép hủy dialog bằng nút back
                builder.show(); //hiển thị dialog

            }
        });

        //dialog 4 //multi choice
        buttonDialog4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mang[] = {"Chó", "Mèo", "Gà", "Vịt", "Heo"};
                boolean checked[] = {false, false, false, false, false};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Chọn con vật mà bạn thích"); //thiết lập tiêu đề
                builder.setIcon(R.drawable.dialog_icon); //thiết lập icon
                builder.setMultiChoiceItems(mang, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            Toast.makeText(MainActivity.this, "Bạn thích " + mang[which], Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Bạn không thích " + mang[which], Toast.LENGTH_SHORT).show();
                        }
                    }
                }); //thiết lập danh sách các lựa chọn
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Hủy Thành Công!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                }); //thiết lập nút hủy
                builder.setCancelable(false); //không cho phép hủy dialog bằng nút back
                builder.show(); //hiển thị dialog

            }
        });

        //dialog 5 custom dialog
        buttonDialog5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.layout_login);
                dialog.setTitle("Custom Dialog");
                dialog.setCancelable(false);
                Button buttonLogin = dialog.findViewById(R.id.btnLogin);
                Button btnRegister = dialog.findViewById(R.id.btnRegister);
                Button buttonCancel = dialog.findViewById(R.id.btn_Cancel);
                buttonLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Login Thành Công!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Đăng ký Thành Công!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Hủy Thành Công!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });


                dialog.show();
            }
        });




}
}