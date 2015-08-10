import java.util.*;

public class CircleGame {
    public int cardsLeft(String deck) {
    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	init(map);

    	StringBuilder sb = new StringBuilder(deck);
    	int idx = 0;
    	for (int i=0; i<10000; i++) {
    		if (sb.charAt(idx) == 'K') {
    			sb.deleteCharAt(idx);
    		}else {
    			if (sb.length() == 1) break;
    			int p = idx;
    			int q = (idx+1)%sb.length();
    			int x = map.get(sb.charAt(p)) + map.get(sb.charAt(q));
    			if (x == 13) {
    				if (idx >= sb.length()-2) idx = 0;
    				sb.deleteCharAt(Math.min(p,q));
    				sb.deleteCharAt(Math.max(p,q)-1);
    			}else {
    				idx = (idx + 1)%sb.length();
    			}
    		}

    		if (sb.length() == 0) break;
    	}

    	return sb.length();
    }


    void init(HashMap<Character, Integer> map) {
    	map.put('A',1);
    	map.put('2',2);
    	map.put('3',3);
    	map.put('4',4);
    	map.put('5',5);
    	map.put('6',6);
    	map.put('7',7);
    	map.put('8',8);
    	map.put('9',9);
    	map.put('T',10);
    	map.put('J',11);
    	map.put('Q',12);
    	map.put('K',13);
    }
}