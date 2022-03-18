// Time Complexity : O(k^(n/k)) ; k - options at each character
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BraceExpression {
    List<String> result;
    public String[] expand(String s) {
        // null
        if(s == null || s.length() == 0) return new String[0];
        result = new ArrayList<>();

        //pre-process string as blocks
        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;
        while(i < s.length()){
            List<Character> block = new ArrayList<>();
            char c = s.charAt(i);
            if(c == '{'){
                while(s.charAt(i) != '}'){
                    i++;
                    if(s.charAt(i) != ','){
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            }else{
                block.add(c);
            }
            blocks.add(block);
            i++;
        }

        dfs(blocks, new StringBuilder(), 0);
        String[] arr = new String[result.size()];
        result.toArray(arr);
        Arrays.sort(arr);
        return arr;
    }

    //idx to track blocks
    private void dfs(List<List<Character>> blocks, StringBuilder sb, int index){
        //base
        if(index == blocks.size()){
            result.add(sb.toString());
            return;
        }

        //logic
        //current block
        List<Character> block = blocks.get(index);
        for(int i = 0; i < block.size(); i++){
            char c = block.get(i);
            //action
            sb.append(c);
            //recurse
            dfs(blocks, sb, index+1);
            //backtrack
            sb.setLength(sb.length() - 1);
        }
    }
}
