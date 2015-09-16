package com.example.adan.taxcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText grossPayEditText, totalDeductions;
    EditText finalGrossPayEditText, finalPayEditText;

    CheckBox loansCheckBox;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grossPayEditText = (EditText) findViewById(R.id.grossPayEditTxt);
        totalDeductions = (EditText) findViewById(R.id.totalDeductions);

        finalGrossPayEditText = (EditText) findViewById(R.id.finalGrossPayEditText);
        finalPayEditText = (EditText) findViewById(R.id.finalPayEditText);

        loansCheckBox = (CheckBox) findViewById(R.id.loansCheckBox);




    }


    private double generalTax;
    private double nationalInsurance;


    private void calculateGeneralTax() {

        double userGrossPay = Double.parseDouble(grossPayEditText.getText().toString());

        if (userGrossPay <= 10600) {
            //Do nothing.
            generalTax += 0;
        } else if (userGrossPay >= 10600 && userGrossPay <= 31785) {
            //Calculate 20% and reduce it.
            userGrossPay -= 10600;
            generalTax = 0.20 * userGrossPay;

        } else if (userGrossPay >= 31786 && userGrossPay <= 150000) {
            //Calculate 40%
            userGrossPay -= 10600;
            generalTax = 0.40 * userGrossPay;

        } else if (userGrossPay > 150001) {
            /* Calculate 45% of it and reduce it. */
            userGrossPay -= 10600;
            generalTax = 0.45 * userGrossPay;

        }

    }

    private void calculateNationalTax() {

        double userGrossPay = Double.parseDouble(grossPayEditText.getText().toString());

        //National Insurance

        if (userGrossPay <= 10600) {
            nationalInsurance += 0;
        } else if (((userGrossPay / 12 >= 620) && (userGrossPay / 12 <= 3260))) {
            //Calculate 12% and reduce it.
            userGrossPay -= 10600;
            nationalInsurance = (0.12 * userGrossPay);

        } else if ((userGrossPay / 12) >= 3260) {
            //Calculate 2%
            userGrossPay -= 10600;
            nationalInsurance =  (0.02 * userGrossPay);

        }

    }



    private double studentLoans;


    public void onCheckboxClicked(View view) {

        double userGrossPay = Double.parseDouble(grossPayEditText.getText().toString());

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();


        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.loansCheckBox:
                if (checked && userGrossPay >= 17335) {
                    userGrossPay -= 10600;
                    studentLoans = (0.09 * userGrossPay);
                }else {
                    studentLoans += 0;
                }break;
        }
    }



    public void totalDeductions(View v){

        double userGrossPay = Double.parseDouble(grossPayEditText.getText().toString());




        calculateGeneralTax();
        calculateNationalTax();



        double totalDeductionsMath = generalTax + nationalInsurance + studentLoans;
        userGrossPay -= totalDeductionsMath;



        totalDeductions.setText("£ "+totalDeductionsMath + "");
        finalPayEditText.setText("£ "+userGrossPay + "");

        userGrossPay += totalDeductionsMath;
        finalGrossPayEditText.setText("£ "+userGrossPay + "");





    }


    public void clearScreen(View v){

        grossPayEditText.setText("0");
        totalDeductions.setText("");
        finalPayEditText.setText("");
        finalGrossPayEditText.setText("");

        nationalInsurance = 0;
        generalTax =0;

    }







}
