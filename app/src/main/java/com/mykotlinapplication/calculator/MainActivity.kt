package com.mykotlinapplication.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ans = 0.0
        var num = 0.0
        val sb = StringBuilder()
        var equal_count = 0

        fun appendString(input: String) {
            if (sb.toString().equals("Error")) {
                return
            }

            if (!sb.contains('+') && !sb.contains('-') && !sb.contains('*') && !sb.contains('/') && equal_count > 0) {
                Log.d("test", "String builder is cleared")
                if (sb.toString().toDouble() != ans) {
                    equal_count = 0
                    sb.clear()
                    display_answer.setText("")
                }
            }

            if (input == "0") {
                if ((sb.length > 0) && (sb.get(0) != '0')) {
                    sb.append("0")
                }
            } else if (input == ".") {
                // If the number is a decimal, stop adding decimal point
                var expr = sb.toString().split("+", "-", "*", "/")
                if (expr.get(expr.size - 1).contains(".")) {
                    return
                }

                // Avoid adding continuous decimal point
                if ((sb.length > 0) && ((sb.get(sb.length - 1) != '.'))) {
                    sb.append(".")
                } else if (sb.length == 0) {
                    sb.append(".")
                }
            } else if (input == "+" || input == "-" || input == "*" || input == "/") {
                Log.d("test", "sb = $sb")
                if (sb.get(sb.length - 1) != null && !sb.get(sb.length - 1).isDigit()) {
                    sb.deleteCharAt(sb.length - 1)
                }
                sb.append(input)
            } else {
                sb.append(input)
            }
            display.setText(sb.toString())
        }

        button_0.setOnClickListener {
            appendString("0")
        }

        button_1.setOnClickListener {
            appendString("1")
        }

        button_2.setOnClickListener {
            appendString("2")
        }

        button_3.setOnClickListener {
            appendString("3")
        }

        button_4.setOnClickListener {
            appendString("4")
        }

        button_5.setOnClickListener {
            appendString("5")
        }

        button_6.setOnClickListener {
            appendString("6")
        }

        button_7.setOnClickListener {
            appendString("7")
        }

        button_8.setOnClickListener {
            appendString("8")
        }

        button_9.setOnClickListener {
            appendString("9")
        }

        button_plus.setOnClickListener {
            appendString("+")
        }

        button_minus.setOnClickListener {
            appendString("-")
        }

        button_multiply.setOnClickListener {
            appendString("*")
        }

        button_divide.setOnClickListener {
            appendString("/")
        }

        button_dot.setOnClickListener {
            appendString(".")
        }

        button_equal.setOnClickListener {
            display_answer.setText("")
            var prev_op_index = 0

            if (!sb.equals("")) {
                for (i in 0..(sb.length - 1)) {
                    if (sb[i] == '+' || sb[i] == '-' || sb[i] == '*' || sb[i] == '/') {
                        if (prev_op_index == 0) {
                            ans = sb.substring(prev_op_index, i).toDouble()
                        } else {
                            when (sb[prev_op_index]) {
                                '+' -> ans += sb.substring(prev_op_index + 1, i).toDouble()
                                '-' -> ans -= sb.substring(prev_op_index + 1, i).toDouble()
                                '*' -> ans *= sb.substring(prev_op_index + 1, i).toDouble()
                                '/' -> ans /= sb.substring(prev_op_index + 1, i).toDouble()
                            }
                        }
                        prev_op_index = i
                    } else if (i == sb.length - 1) {
                        if (prev_op_index == 0) {
                            ans = sb.substring(prev_op_index, i + 1).toDouble()
                        } else {
                            var temp = sb.substring(prev_op_index + 1, i + 1)
                            when (sb[prev_op_index]) {
                                '+' -> ans += sb.substring(prev_op_index + 1, i + 1).toDouble()
                                '-' -> ans -= sb.substring(prev_op_index + 1, i + 1).toDouble()
                                '*' -> ans *= sb.substring(prev_op_index + 1, i + 1).toDouble()
                                '/' -> ans /= sb.substring(prev_op_index + 1, i + 1).toDouble()
                            }
                        }
                    }
                }

                sb.clear()
                if (ans == Double.POSITIVE_INFINITY) {
                    display_answer.append("Error")
                    sb.append("Error")
                } else {
                    display_answer.append(ans.toString())
                    sb.append(ans.toString())
                }

                equal_count++

            } else {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            }

        }

        button_ac.setOnClickListener {
            ans = 0.0
            num = 0.0
            sb.clear()
            display.setText("")
            display_answer.setText("")
            equal_count = 0
        }


    }
}
