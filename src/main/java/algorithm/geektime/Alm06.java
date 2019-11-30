package algorithm.geektime;

import structure.Stack;

/**
 * 左右括号匹配的问题
 *
 * @ClassName: Alm06
 * @Author: jicheng
 * @CreateDate: 2019/11/30 6:29 PM
 */
public class Alm06 {

    /**
     * 括号的使用有：{[()]}
     * 其他字符视为非法，返回false
     *
     * @param brackets
     * @return
     */
    public boolean solutions(String brackets) {
        if (brackets == null) {
            return false;
        }
        if ("".equals(brackets)) {
            return true;
        }

        int left[] = new int[127];
        int right[] = new int[127];
        left['{'] = 1;
        left['['] = 1;
        left['('] = 1;

        right['}'] = 1;
        right[']'] = 1;
        right[')'] = 1;

        int smallBracketDistance = ')' - '(';
        int midBracketDistance = ']' - '[';
        int bigBracketDistance = '}' - '{';

        char[] chars = brackets.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (left[aChar] == 0 && right[aChar] == 0) {
                return false;
            }
            if (left[aChar] == 1) {
                stack.push(aChar);
                continue;
            }
            if (right[aChar] == 1) {
                Character pop = stack.pop();
                if (pop == null) {
                    return false;
                }
                int lsmall = aChar - smallBracketDistance;
                int lmid = aChar - midBracketDistance;
                int lbig = aChar - bigBracketDistance;
                char top = pop.charValue();
                if (top == lsmall || top == lmid || top == lbig){
                    // 比对栈顶元素
                    continue;
                }else{
                    return false;
                }
            }

        }
        if(!stack.isEmpty()){
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Alm06().solutions("()[]{}[()]"));
    }
}
