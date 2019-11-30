package algorithm.geektime;

import structure.Stack;

import java.time.LocalDate;

/**
 * 不带括号、整数的四则混合运算
 *
 * @ClassName: Alm07
 * @Author: jicheng
 * @CreateDate: 2019/11/30 6:51 PM
 */
public class Alm07 {


    public long solution(String formula) {
        if (formula == null || "".equals(formula)) {
            throw new IllegalArgumentException("操作数不能为空啊！");
        }

        int operators[] = new int[256];
        operators['-'] = 1;
        operators['+'] = 1;
        operators['/'] = 7;
        operators['*'] = 7;


        char[] chars = formula.toCharArray();
        Stack<Long> numbersStack = new Stack<>();
        Stack<Character> operatorsStack = new Stack<>();
        long tmpNum = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar >= '0' && aChar <= '9') {
                // 操作数
                long currentNum = (long) (aChar - '0');
                tmpNum = tmpNum * 10 + currentNum;
                continue;
            }
            if (operators[aChar] != 0) {
                // 操作符
                Long num = Long.valueOf(tmpNum);
                numbersStack.push(num);
                tmpNum = 0;
                if (operatorsStack.isEmpty()
                        || operators[operatorsStack.peek().charValue()] < operators[aChar]) {
                    operatorsStack.push(aChar);
                } else {
                    while (!operatorsStack.isEmpty()
                            &&operators[operatorsStack.peek().charValue()] >= operators[aChar]) {
                        //这种要出栈运算
                        calculation(numbersStack, operatorsStack);

                    }
                    operatorsStack.push(aChar);

                }

            }

        }

        numbersStack.push(Long.valueOf(tmpNum));

        while(!operatorsStack.isEmpty()){
            calculation(numbersStack,operatorsStack);
        }

        return numbersStack.pop().longValue();
    }

    private void calculation(Stack<Long> numbersStack, Stack<Character> operatorsStack) {
        Long num1 = numbersStack.pop();
        if (num1 == null) {
            throw new IllegalArgumentException("式子不对啊，操作数少了！");
        }
        Long num2 = numbersStack.pop();
        if (num2 == null) {
            throw new IllegalArgumentException("式子不对啊，操作数少了！");
        }
        Character operator = operatorsStack.pop();
        if (operator == null) {
            throw new IllegalArgumentException("式子不对啊，操作符号少了！");
        }
        Long result = calculation(num2, num1, operator);
        numbersStack.push(result);
    }

    private Long calculation(Long num1, Long num2, Character operator) {
        char c = operator.charValue();
        Long result;
        if (c == '+') {
            result = num1.longValue() + num2.longValue();
        } else if (c == '-') {
            result = num1.longValue() - num2.longValue();
        } else if (c == '*') {
            result = num1.longValue() * num2.longValue();
        } else if (c == '/') {
            result = num1.longValue() / num2.longValue();
        } else {
            throw new IllegalArgumentException("没有这个操作符！");
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Alm07().solution("5-2*3+4*58"));
    }

}
