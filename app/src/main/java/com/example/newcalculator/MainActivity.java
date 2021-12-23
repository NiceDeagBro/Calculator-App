package com.example.newcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    public char point = '.';
    public char multiply = '×';
    public char divide = '÷';
    public char add = '+';
    public char subtract = '-';
    public char sqrt = 't';

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('×')) x *= parseFactor(); // multiplication
                    else if (eat('÷')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonZeroClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        tv.append("0");
    }

    public void buttonOneClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        tv.append("1");
    }

    public void buttonTwoClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        tv.append("2");
    }

    public void buttonThreeClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        tv.append("3");
    }

    public void buttonFourClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        tv.append("4");
    }

    public void buttonFiveClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        tv.append("5");
    }

    public void buttonSixClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        tv.append("6");
    }

    public void buttonSevenClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        tv.append("7");
    }

    public void buttonEightClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        tv.append("8");
    }

    public void buttonNineClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        tv.append("9");
    }

    public void buttonAddClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        String line = tv.getText().toString();
        StringBuffer sb = new StringBuffer(line);

        if ((line.charAt(line.length()-1) == subtract || (line.charAt(line.length()-1) == multiply)) || (line.charAt(line.length()-1) == add) || (line.charAt(line.length()-1) == divide) || (line.charAt(line.length()-1) == sqrt) || (line.charAt(line.length()-1) == point)){
            sb.deleteCharAt(line.length()-1);
            tv.setText(sb);
            tv.append("+");
        }
        else{
            tv.append("+");
        }
    }

    public void buttonSubtractClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        String line = tv.getText().toString();
        StringBuffer sb = new StringBuffer(line);

        if ((line.charAt(line.length()-1) == subtract || (line.charAt(line.length()-1) == multiply)) || (line.charAt(line.length()-1) == add) || (line.charAt(line.length()-1) == divide) || (line.charAt(line.length()-1) == sqrt) || (line.charAt(line.length()-1) == point)){
            sb.deleteCharAt(line.length()-1);
            tv.setText(sb);
        }
        tv.append("-");
    }

    public void buttonMultiplyClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        String line = tv.getText().toString();
        StringBuffer sb = new StringBuffer(line);

        if ((line.charAt(line.length()-1) == subtract || (line.charAt(line.length()-1) == multiply)) || (line.charAt(line.length()-1) == add) || (line.charAt(line.length()-1) == divide) || (line.charAt(line.length()-1) == sqrt) || (line.charAt(line.length()-1) == point)){
            sb.deleteCharAt(line.length()-1);
            tv.setText(sb);
        }
        tv.append("×");
    }

    public void buttonDivideClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        String line = tv.getText().toString();
        StringBuffer sb = new StringBuffer(line);

        if ((line.charAt(line.length()-1) == subtract) || (line.charAt(line.length()-1) == add) || (line.charAt(line.length()-1) == divide) || (line.charAt(line.length()-1) == sqrt) || (line.charAt(line.length()-1) == point)){
            sb.deleteCharAt(line.length()-1);
            tv.setText(sb);
        }
        tv.append("÷");
    }

    public void buttonSqrtClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        String line = tv.getText().toString();
        StringBuffer sb = new StringBuffer(line);

        if ((line.charAt(line.length()-1) == sqrt) || (line.charAt(line.length()-1) == point)){
            sb.deleteCharAt(line.length()-1);
            tv.setText(sb);
        }
        tv.append("sqrt");
    }


    public void buttonPointClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        String line = tv.getText().toString();
        StringBuffer sb = new StringBuffer(line);

        if ((line.charAt(line.length()-1) == subtract || (line.charAt(line.length()-1) == multiply)) || (line.charAt(line.length()-1) == add) || (line.charAt(line.length()-1) == divide) || (line.charAt(line.length()-1) == sqrt) || (line.charAt(line.length()-1) == point)){
            sb.deleteCharAt(line.length()-1);
            tv.setText(sb);
        }
        tv.append(".");
    }

    public void buttonPlusMinusClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        String line = tv.getText().toString();
        StringBuffer sb = new StringBuffer(line);

        if((line.charAt(line.length()-1) == subtract)){
            sb.deleteCharAt(line.length()-1);
            tv.setText(sb);
            tv.append("+");
        }

        else if((line.charAt(line.length()-1) == add)){
            sb.deleteCharAt(line.length()-1);
            tv.setText(sb);
            tv.append("-");
        }

        else if((line.charAt(line.length()-1) == multiply)){
            sb.deleteCharAt(line.length()-1);
            tv.setText(sb);
            tv.append("÷");
        }

        else if((line.charAt(line.length()-1) == divide)){
            sb.deleteCharAt(line.length()-1);
            tv.setText(sb);
            tv.append("×");
        }
        else{

        }
    }

    public void buttonNaClick(View v)
    {
        Toast.makeText(getApplicationContext(),"This function is not available. To access this feature, purchase the premium version",Toast.LENGTH_LONG).show();
    }

    public void buttonClearClick(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvResult);
        tv.setText("");
    }

    public void buttonEqualsClick(View v){
        TextView tv = (TextView)findViewById(R.id.tvResult);
        String input = tv.getText().toString();
        String result = String.valueOf(eval(input));
        DecimalFormat format = new DecimalFormat("0.#");
        DecimalFormat format2 = new DecimalFormat("0.###");


        if ((eval(input) == Math.floor(eval(input))) && !Double.isInfinite(eval(input))) {
            tv.setText(format.format(eval(input)));
        }
        else{
            tv.setText(format2.format(eval(result)));
        }
    }

}