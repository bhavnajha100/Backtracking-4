// Time Complexity : k^d * klogk - where k is number of characters in the {} and d is depth which is n/k where n is length of string
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

class BraceExpansion {
    public String[] expand(String s) {
        List<String> list = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        int n = s.length();
        int i = 0;
        while (i < n) {
            List<Character> block = new ArrayList<>();
            if (s.charAt(i) == '{') {
                i++;
                while (s.charAt(i) != '}') {
                    // keep adding char in current block
                    if (s.charAt(i) != ',') {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            } else {
                // add normal character a-z in curr block
                block.add(s.charAt(i));
            }
            // sorting
            Collections.sort(block); // klogk
            blocks.add(block);
            i++;
        }
        System.out.println(blocks);
        backtrack(blocks, 0, new StringBuilder(), list);
        String[] result = new String[list.size()];
        for (int k = 0; k < list.size(); k++) {
            result[k] = list.get(k);
        }
        return result;
    }

    private void backtrack(List<List<Character>> blocks, int idx, StringBuilder sb, List<String> list) {
        // base
        if (idx == blocks.size()) {
            list.add(sb.toString());
            return;
        }
        // logic
        List<Character> currBlock = blocks.get(idx);
        // iterate over current block
        for (int i = 0; i < currBlock.size(); i++) {
            char c = currBlock.get(i);
            // Action
            sb.append(c);
            // Recurse
            backtrack(blocks, idx + 1, sb, list);
            // backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}