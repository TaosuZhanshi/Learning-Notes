import org.junit.jupiter.api.Test;

import java.util.*;

public class test127 {
    private String beginWord;
    private String endWord;
    Set<String> words;

    class Node{
        int val;
        String str;
        public Node(String str, int val){
            this.val = val;
            this.str = str;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        this.beginWord = beginWord;
        this.endWord = endWord;
        words = new HashSet<String>();
        for(String str : wordList){
            words.add(str);
        }
        if(!words.contains(endWord)){
            return 0;
        }
        int ans = astarSearch();
        return ans == -1 ? 0 : ans + 1;
    }

    private int astarSearch(){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) ->  a.val - b.val);
        //总体来说，此程序中A*法的估价函数由两部分组成，一是distanceLogger中记载的由beginWord走出来的距离，二是距离endWord的距离，二者相加为估价函数
        Map<String, Integer> distanceLogger = new HashMap<>();
        Node node = new Node(beginWord, f(beginWord));
        priorityQueue.add(node);
        distanceLogger.put(beginWord, 0);
        while(!priorityQueue.isEmpty()){
            //获取当前存储的估价函数值最小的节点
            Node curMinDistNode = priorityQueue.poll();
            String curMinDistStr = curMinDistNode.str;
            int curMinDist = distanceLogger.get(curMinDistStr);
            if(curMinDistStr.equals(endWord)){
                break;
            }
            for(int i = 0; i < curMinDistStr.length(); ++i){
                for(int j = 0; j < 26; ++j){
                    String tempSub = curMinDistStr.substring(0, i) + String.valueOf((char) ('a' + j)) + curMinDistStr.substring(i +  1);
                    if(!words.contains(tempSub)){
                        continue;
                    }
                    if(!distanceLogger.containsKey(tempSub) || distanceLogger.get(tempSub) > curMinDist + 1){
                        distanceLogger.put(tempSub, curMinDist + 1);
                        priorityQueue.add(new Node(tempSub, curMinDist + 1 + f(tempSub)));
                    }
                }
            }
        }
        return distanceLogger.containsKey(endWord) ? distanceLogger.get(endWord) : -1;
    }

    private int f(String x){
        int ans = 0;
        for(int i = 0; i < x.length(); ++i){
            if(x.charAt(i) != endWord.charAt(i)){
                ans ++;
            }
        }
        return ans;
    }

    @Test
    public void test(){
        String[] wordList = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> list = Arrays.asList(wordList);
        int a = ladderLength("hit", "cog", list);
        System.out.println("a = " + a);
    }
}
