package com.example.scalcultor;

import static android.os.Build.VERSION_CODES.P;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculate {
    private int scale; //保留精度
    private Stack<BigDecimal> numberStack = null; //数字栈
    private Stack<Character> symbolStack = null; //符号栈

    public Calculate(int scale) {
        this.scale = scale;
    }

    public Calculate() {
        this(32);
    }

    public String calculate(String numStr) {
        //用“=”表示结束
        if(numStr.length() > 1 && !"=".equals(numStr.charAt(numStr.length()-1) + "")) {
            numStr += "=";
        }

        if(!isStandard(numStr)) {
            return null;
        }

        //初始化栈
        if(numberStack == null) {
            numberStack = new Stack<BigDecimal>();
        }
        numberStack.clear();
        if(symbolStack == null) {
            symbolStack = new Stack<Character>();
        }
        symbolStack.clear();

        StringBuffer temp = new StringBuffer(); //缓存数字

        for(int i = 0; i < numStr.length(); i++) { //获取字符，若是数字加入到temp中
            char ch = numStr.charAt(i);
            if(isNumber(ch)) {
                temp.append(ch);
            }
            else if(ch == 's' || ch == 'c' || ch == 't') { //sin cos tan
                for(int j = 0; j < numStr.length(); j++) {
                    char str = numStr.charAt(j);
                    if(isNumber(str)) {
                        temp.append(str);
                    }
                    else {
                        if(str == '=') {
                            for(int k = 0; k < 4; k++) {
                                symbolStack.pop();
                            }
                            String s = temp.toString();
                            double d = Math.toRadians(Double.parseDouble(s));
                            double arc = 0;
                            switch (symbolStack.peek()) {
                                case 's':
                                    arc = Math.sin(d);
                                    break;
                                case 'c':
                                    arc = Math.cos(d);
                                    break;
                                case 't':
                                    arc = Math.tan(d);
                                    break;
                            }

                            BigDecimal bd = new BigDecimal(arc);
                            bd = bd.setScale(2, RoundingMode.HALF_UP);
                            //Log.e("zoe", s +" " + d + " " + bd);
                            numberStack.push(bd);
                        }
                        else {
                            symbolStack.push(str);
                        }
                    }
                }
                break;
            }
            else if(ch == 'l') { //ln log
                for(int j = 0; j < numStr.length(); j++) {
                    char str = numStr.charAt(j);
                    if(isNumber(str)) {
                        temp.append(str);
                    }
                    else {
                        if(str == '=') {
                            for(int k = 0; k < 2; k++) {
                                symbolStack.pop();
                            }
                            String s = temp.toString();
                            double d = 0;
                            switch (symbolStack.peek()) {
                                case 'n':
                                    d = Math.log(Double.parseDouble(s));
                                case 'g':
                                    d = Math.log10(Double.parseDouble(s));
                                    //Log.e("zoe", String.valueOf(d));
                            }
                            BigDecimal bigDecimal = new BigDecimal(d);
                            //bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
                            //Log.e("zoe", s +" " + d + " " + bigDecimal);
                            numberStack.push(bigDecimal);
                        }
                        else {
                            symbolStack.push(str);
                        }
                    }
                }
                break;
            }
            else if(ch == '!') {
                symbolStack.push(ch);
                String s = temp.toString();
                double d = 0;
                d = Factorial(s);
                //Log.e("zoe", s +" " + d);
                BigDecimal bigDecimal = new BigDecimal(d);
                numberStack.push(bigDecimal);
                break;
            }
            else if(ch == '√') {
                for(int j = 1; j < numStr.length(); j++) {
                    char str = numStr.charAt(j);
                    if(isNumber(str)) {
                        temp.append(str);
                    }
                    else {
                        if(str == '=') {
                            String s = temp.toString();
                            double d = 0;
                            d = Math.sqrt(Double.parseDouble(s));
                            //Log.e("zoe", s +" " + d);
                            BigDecimal bigDecimal = new BigDecimal(d);
                            numberStack.push(bigDecimal);
                        }
                        else {
                            symbolStack.push(str);
                        }
                    }
                }
                break;
            }
            else if(ch == '^') {
                temp = new StringBuffer();
                String tempStr = "";
                for(int j = 0; j < numStr.length(); j++) {
                    char str1 = numStr.charAt(j);
                    //char str2 = numStr.charAt(j+1);
                    if(isNumber(str1)) {
                        temp.append(str1);
                        if(numStr.charAt(j+1) == '^') {
                            tempStr = temp.toString();
                            temp = new StringBuffer();
                        }
                    }
                    else {
                        if(str1 == '=') {
                            double d = 0;
                            String s = temp.toString();
                            d = getPower(tempStr, s);
                            BigDecimal bigDecimal = new BigDecimal(d);
                            numberStack.push(bigDecimal);
                        }
                        else symbolStack.push(str1);
                    }
                }
                break;
            }
            else { //+-*/()
                String tempStr = temp.toString();
                if(!tempStr.isEmpty()) {
                    BigDecimal num = new BigDecimal(tempStr);
                    numberStack.push(num); //数字压栈
                    temp = new StringBuffer(); //重置缓存
                }
                while (!comparePri(ch) && !symbolStack.empty()) {//判断运算符优先级，若当前优先级低于栈顶的优先级，则先把前面计算出来
                    BigDecimal b = numberStack.pop();
                    BigDecimal a = numberStack.pop();
                    switch ((char) symbolStack.pop()) {
                        case '+':
                            numberStack.push(a.add(b));
                            break;
                        case '-':
                            numberStack.push(a.subtract(b));
                            break;
                        case '*':
                            numberStack.push(a.multiply(b));
                            break;
                        case '/':
                            try {
                                numberStack.push(a.divide(b));
                            } catch (ArithmeticException e) {
                                numberStack.push(a.divide(b, this.scale, BigDecimal.ROUND_HALF_EVEN));
                            }
                            break;
                        default:
                            break;
                    }
                }
                if(ch != '=') {
                    symbolStack.push(new Character(ch));
                    if(ch == ')') {
                        symbolStack.pop();
                        symbolStack.pop();
                    }
                }
            }
        }
        return numberStack.pop().toString(); //返回结果字符串
    }

    private double getPower(String tempStr, String temp) {
        double x = Double.parseDouble(tempStr);
        double y = Double.parseDouble(temp);
        if(y == 0) return 1;
        if(y == 1) return x;
        return x * getPower(String.valueOf(x), String.valueOf(y-1));
    }

    private double Factorial(String s) {
        double sum = 1;
        double num = Double.parseDouble(s);
        if(num < 0) {
            throw new IllegalArgumentException("必须为正整数!");
        }
        if(num == 1) {
            return 1;
        }
        else {
            sum = num * Factorial(String.valueOf(num - 1));
            return sum;
        }
    }

    private boolean comparePri(char symbol) { //比较优先级，当前运算符优先级比栈顶高则true
        if(symbolStack.empty()) return true; //空栈
        char top = (char) symbolStack.peek(); //查看栈顶元素
        if(top == '(') return true;
        switch (symbol) {
            case '(': //‘（’优先级最高
                return true;
            case '√':
            case '^':
            case '!': {
                if(top == '+' || top == '-' || top == '*' || top == '/') return true;
                else return false;
            }
            case '*':
            case '/': {
                if(top == '+' || top == '-') return true;
                else return false;
            }
            case '+':
            case '-':
            case ')':
            case '=':
                return false;
            default:
                break;
        }
        return true;
    }

    private boolean isStandard(String numStr) { //检查合法性
        if(numStr == null || numStr.isEmpty()) {
            return false;
        }
        Stack<Character> stack = new Stack<Character>(); //用来保存括号，检查括号匹配
        boolean b = false; //标记=
        for(int i = 0; i < numStr.length(); i++) {
            char n = numStr.charAt(i);
            //判断字符是否合法
            if(!(isNumber(n) || "(".equals(n + "") || ")".equals(n + "") || "+".equals(n + "")
                    || "-".equals(n + "")  || "*".equals(n + "") || "/".equals(n + "")  || "=".equals(n + "")
                    || "s".equals(n + "") || "i".equals(n + "") || "n".equals(n + "")
                    || "c".equals(n + "") || "o".equals(n + "")
                    || "t".equals(n + "") || "a".equals(n + "")
                    || "l".equals(n + "") || "g".equals(n + "")
                    || "!".equals(n + "") || "√".equals(n + "") || "^".equals(n + ""))) {
                Log.e("zoe", "no match");
                return false;
            }
            //左括号压栈
            if("(".equals(n + "")) {
                stack.push(n);
            }
            if(")".equals(n + "")) {
                if(stack.isEmpty() || !"(".equals((char) stack.pop() + ""))//括号是否匹配
                    return false;
            }
            if("=".equals(n + "")) {
                if(b) return false;
                b = true;
            }
        }
        if(!stack.isEmpty()) return false; //缺少右括号
        if (!("=".equals(numStr.charAt(numStr.length() - 1) + ""))) return false;
        return true;
    }

    private boolean isNumber(char ch) { //判断是否是数字
        if((ch >= '0' && ch <= '9') || ch == '.') return true;
        return false;
    }

}

