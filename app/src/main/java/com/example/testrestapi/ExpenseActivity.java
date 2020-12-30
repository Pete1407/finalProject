package com.example.testrestapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ExpenseActivity extends AppCompatActivity {
    public RangeSeekBar rangeSeekBar;
    public Spinner depositchoice;
    public Spinner middleChoice;
    public Spinner waterChoice;
    public Spinner electroChoice;

    public ActionBar bar;
    public LinearLayout linear;
    public static final int MIN = 0;
    public static final int MAX = 20000;
    public TextView min;
    public TextView max;

    public EditText input_deposit;

    public EditText input_middle;
    public TextView unit_middle;

    public TextView unitWater;
    public TextView unitElectro;
    public TextView unitDeposit;

    public EditText electro_input;
    public EditText water_input;

    public Button confirm_expense;

    ////// Variable use store in Database  //////
    public int minPrice;
    public int maxPrice;
    public String typeWater;
    public int  priceWater;
    public String typeElectro;
    public int priceElectro;
    public String typeDeposit;
    public int depositPrice;
    public String typeCommonFee;
    public int commonFee;

    public String dormID;

    private EditText postcode;

    public String username;
    public String password;
    public String email;

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public EditText multipleText;
    public String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);




        this.dormID = getIntent().getStringExtra("dormID");
        this.username = getIntent().getStringExtra("username");
        this.password = getIntent().getStringExtra("password");
        this.email = getIntent().getStringExtra("email");

        //Toast.makeText(this,"Email: "+email+"\n"+"Username: "+username,Toast.LENGTH_LONG).show();


        System.out.println("DORMID: "+dormID);

        this.bar = getSupportActionBar();

        int title = R.string.expenseTitle;
        String a = getResources().getString(title);
        this.bar.setTitle(a);

        // หน่วยของค่าใช้จ่าย
        this.unitWater = (TextView) findViewById(R.id.unit_water);
        this.unitElectro = (TextView) findViewById(R.id.unit_electro);
        this.unitDeposit = (TextView) findViewById(R.id.unit_deposit);
        this.unitWater.setVisibility(View.GONE);
        this.unitElectro.setVisibility(View.GONE);
        this.multipleText = (EditText) findViewById(R.id.multipleText);
        this.water_input = (EditText) findViewById(R.id.water_input);
        this.electro_input = (EditText) findViewById(R.id.electro_input);
        this.water_input.setVisibility(View.GONE);
        this.electro_input.setVisibility(View.GONE);


        this.waterChoice = (Spinner) findViewById(R.id.waterChoice);
        this.electroChoice = (Spinner) findViewById(R.id.electroChoice);

        this.bar.setDisplayHomeAsUpEnabled(true);
        this.bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC143C")));

        this.postcode = (EditText) findViewById(R.id.postcode);
        this.middleChoice = (Spinner) findViewById(R.id.middle_choice);
        this.depositchoice = (Spinner) findViewById(R.id.deposit_choice);
        this.input_deposit = (EditText) findViewById(R.id.input_deposit);
        this.input_middle = (EditText) findViewById(R.id.input_middle);
        this.unit_middle = (TextView) findViewById(R.id.unit_middle);


        this.input_middle.setVisibility(View.GONE);
        this.unit_middle.setVisibility(View.GONE);


        this.input_deposit.setVisibility(View.GONE);
        this.unitDeposit.setVisibility(View.GONE);

        this.confirm_expense = (Button) findViewById(R.id.confirmExpense);

        this.min = (TextView) findViewById(R.id.min);
        this.max = (TextView) findViewById(R.id.max);

        this.rangeSeekBar = (RangeSeekBar)findViewById(R.id.rangebar);
        this.rangeSeekBar.setTextAboveThumbsColorResource(R.color.colorAccent);
        this.rangeSeekBar.setSelectedMinValue(MIN);
        this.rangeSeekBar.setSelectedMaxValue(MAX);
        this.rangeSeekBar.setRangeValues(MIN,MAX);
        this.min.setText("น้อยที่สุด:"+MIN);
        this.max.setText("มากที่สุุด:"+MAX);
        this.rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                Number minNumber = bar.getSelectedMinValue();
                Number maxNumber = bar.getSelectedMaxValue();

                int minn = (int) minNumber;
                int maxn = (int) maxNumber;

                min.setText("น้อยที่สุด:"+minn);
                max.setText("มากที่สุด:"+maxn);

                minPrice = minn;
                maxPrice = maxn;

                //Toast.makeText(getApplicationContext(),"Min:"+minNumber+"\n"+"Max:"+maxNumber,Toast.LENGTH_LONG).show();
            }
        });

        this.setChoiceDeposit();
        this.setChoiceCommonFee();
        this.setChoiceWater();
        this.setChoiceElectro();

        this.multipleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                description = text;
            }
        });


    }



    public void setChoiceWater(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.waterBill,android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        this.waterChoice.setAdapter(adapter);

        final String[] array = getResources().getStringArray(R.array.waterBill);

        this.waterChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   String choice = parent.getItemAtPosition(position).toString();
                   if(choice.equals(array[0]))
                   {
                       water_input.setVisibility(View.GONE);
                       unitWater.setVisibility(View.GONE);
                   }
                   else if(choice.equals(array[1]))
                   {
                       //System.out.println(choice);
                       unitWater.setText("บาทต่อยูนิต");
                       water_input.setVisibility(View.VISIBLE);
                       unitWater.setVisibility(View.VISIBLE);

                       typeWater = choice;

                       water_input.addTextChangedListener(new TextWatcher() {
                           @Override
                           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                           }

                           @Override
                           public void onTextChanged(CharSequence s, int start, int before, int count) {

                           }

                           @Override
                           public void afterTextChanged(Editable s) {
                               String text = s.toString();
                               if(text.isEmpty())
                               {
                                   priceWater = 0;

                               }
                               else
                               {
                                   priceWater = Integer.parseInt(text);

                               }

                           }
                       });
                       //priceWater = Integer.parseInt(water_input.getText().toString());
                   }
                   else if(choice.equals(array[2]))
                   {
                       //System.out.println(choice);
                       unitWater.setText("บาทต่อห้อง/เดือน");
                       water_input.setVisibility(View.VISIBLE);
                       unitWater.setVisibility(View.VISIBLE);

                       typeWater = choice;

                       water_input.addTextChangedListener(new TextWatcher() {
                           @Override
                           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                           }

                           @Override
                           public void onTextChanged(CharSequence s, int start, int before, int count) {

                           }

                           @Override
                           public void afterTextChanged(Editable s) {
                                String text = s.toString();
                                if(text.isEmpty())
                                {
                                    priceWater = 0;
                                }
                                else
                                {
                                    priceWater = Integer.parseInt(text);
                                }


                           }
                       });

                       //priceWater = Integer.parseInt(water_input.getText().toString());
                   }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       /*
        this.promotionAdapter = ArrayAdapter.createFromResource(this,R.array.promotion_choice,android.R.layout.simple_dropdown_item_1line);
        this.promotionAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        this.promotion_choice.setAdapter(this.promotionAdapter);
        this.promotion_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice = parent.getItemAtPosition(position).toString();
                if(choice.equals("มีโปรโมชั่น"))
                {
                    promoDescription.setVisibility(View.VISIBLE);
                    multipletext.setVisibility(View.VISIBLE);
                }
                else
                {
                    promoDescription.setVisibility(View.GONE);
                    multipletext.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */
    }

    public void setChoiceElectro(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.electroBill,android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        this.electroChoice.setAdapter(adapter);

        final String[] array = getResources().getStringArray(R.array.electroBill);

        this.electroChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice = parent.getItemAtPosition(position).toString();
                if(choice.equals(array[0]))
                {
                    unitElectro.setVisibility(View.GONE);
                    electro_input.setVisibility(View.GONE);
                }
                else if(choice.equals(array[1]))
                {
                    unitElectro.setText("บาทต่อยูนิต");
                    unitElectro.setVisibility(View.VISIBLE);
                    electro_input.setVisibility(View.VISIBLE);

                    typeElectro = choice;

                    electro_input.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String text = s.toString();
                            if(text.isEmpty())
                            {
                                priceElectro = 0;
                            }
                            else
                            {
                                priceElectro = Integer.parseInt(text);
                            }
                        }
                    });
                    //priceElectro = Integer.parseInt(electro_input.getText().toString());

                }
                else if(choice.equals(array[2]))
                {
                    unitElectro.setVisibility(View.GONE);
                    electro_input.setVisibility(View.GONE);

                    typeElectro = choice;
                    priceElectro = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setChoiceDeposit(){
        final String[] depochoice = getResources().getStringArray(R.array.depositChoice);

        ArrayAdapter<CharSequence> depoAdapter = ArrayAdapter.createFromResource(this,R.array.depositChoice,android.R.layout.simple_dropdown_item_1line);
        depoAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        this.depositchoice.setAdapter(depoAdapter);
        this.depositchoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(parent.getItemAtPosition(position).toString());
                String choiceUser = parent.getItemAtPosition(position).toString();
                if(choiceUser.equals(depochoice[0]))
                {
                    typeDeposit = choiceUser;
                    input_deposit.setVisibility(View.GONE);
                    unitDeposit.setVisibility(View.GONE);
                    depositPrice = 0;
                }
                else if(choiceUser.equals(depochoice[1]))
                {
                    unitDeposit.setText("บาท");
                    input_deposit.setVisibility(View.VISIBLE);
                    unitDeposit.setVisibility(View.VISIBLE);

                    typeDeposit = choiceUser;

                    input_deposit.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String text = s.toString();
                            System.out.println(text);
                            if(text.isEmpty())
                            {
                                System.out.println("Into if condition detect isEmpty");
                                depositPrice = 0;
                            }
                            else
                            {
                                System.out.println("Into else condition detect !isEmpty");
                                depositPrice = Integer.parseInt(text);
                            }

                        }
                    });
                    //depositPrice = Integer.parseInt(input_deposit.getText().toString());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setChoiceCommonFee(){
        ArrayAdapter<CharSequence> middleAdapter = ArrayAdapter.createFromResource(this,R.array.middle_Choice,android.R.layout.simple_dropdown_item_1line);
        middleAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        this.middleChoice.setAdapter(middleAdapter);
        this.middleChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice = parent.getItemAtPosition(position).toString();
                if(choice.equals("มีค่าส่วนกลาง"))
                {
                    input_middle.setVisibility(View.VISIBLE);
                    unit_middle.setVisibility(View.VISIBLE);

                    typeCommonFee = choice;

                    input_middle.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String text = s.toString();
                            if(text.isEmpty())
                            {
                                commonFee = 0;
                            }
                            else
                            {
                                commonFee = Integer.parseInt(text);
                            }

                        }
                    });
                    //commonFee = Integer.parseInt(input_middle.getText().toString());
                }
                else
                {
                    input_middle.setVisibility(View.GONE);
                    unit_middle.setVisibility(View.GONE);

                    typeCommonFee = choice;
                    commonFee = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void confirmExpense(View v){
        sharedPreferences = getSharedPreferences("expense",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Intent i = new Intent(ExpenseActivity.this,ChooseStyleDorm_Activity.class);
        i.putExtra("dormID",dormID);
        i.putExtra("minPrice",minPrice);
        i.putExtra("maxPrice",maxPrice);
        i.putExtra("typeWater",typeWater);
        i.putExtra("priceWater",priceWater);
        i.putExtra("typeElectro",typeElectro);
        i.putExtra("priceElectro",priceElectro);
        i.putExtra("typeDeposit",typeDeposit);
        i.putExtra("depositPrice",depositPrice);
        i.putExtra("typeCommonFee",typeCommonFee);
        i.putExtra("commonFee",commonFee);
        i.putExtra("description",description);
        i.putExtra("username",username);
        i.putExtra("password",password);
        i.putExtra("email",email);

        editor.putString("dormID",dormID);
        editor.putInt("minPrice",minPrice);
        editor.putInt("maxPrice",maxPrice);
        editor.putString("typeWater",typeWater);
        editor.putInt("priceWater",priceWater);
        editor.putString("typeElectro",typeElectro);
        editor.putInt("priceElectro",priceElectro);
        editor.putString("typeDeposit",typeDeposit);
        editor.putInt("depositPrice",depositPrice);
        editor.putString("typeCommonFee",typeCommonFee);
        editor.putInt("commonFee",commonFee);
        editor.putString("description",description);
        editor.putString("username",username);
        editor.putString("password",password);
        editor.putString("email",email);
        editor.commit();


        startActivity(i);
    }

}
