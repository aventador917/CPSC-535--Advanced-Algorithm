// CPSC535 Project 1 : Rewriting greeting cards 
// Team member:
//          Zhiwei Su,  887367936, zwsu@csu.fullerton.edu
//          Paul Tang,  802713818, aventador917@csu.fullerton.edu


import java.util.HashMap;
import java.util.LinkedList;

public class greetingECards{
    public static void main(String[] args){
        //test case 1
        String s1 = "My dear Anna, let me congratulate you on the beautiful car that you purchased today. It looks very posh. I hope you got a good deal. Cars are expensive but much needed. Best regards, Naomi.";
        LinkedList<String[]> ls1 = new LinkedList<>();
        ls1.add(new String[]{"Anna", "Jovi and Victor"});
        ls1.add(new String[]{"car", "house"});
        ls1.add(new String[]{"today", "last week"});
        ls1.add(new String[]{"posh", "well built"});
        System.out.println("******TEST CASE 1******");
        System.out.println("INPUT ECARDS: \n------\n" + s1 + "\n------");
        System.out.println("NEW ECARDS: \n------\n" + rewritingGreetingECards(s1, ls1) + "\n------\n\n");

        //test case 2
        String s2 = "Our newest students have been asked to stay today until the end of the classes. The old principal.";
        LinkedList<String[]> ls2 = new LinkedList<>();
        ls2.add(new String[]{"new", "old"});
        ls2.add(new String[]{"student", "teacher"});
        ls2.add(new String[]{"to", "yester"});
        ls2.add(new String[]{"old", "young"});
        ls2.add(new String[]{"end", "beginning"});
        System.out.println("******TEST CASE 2******");
        System.out.println("INPUT ECARDS: \n------\n" + s2 + "\n------");
        System.out.println("NEW ECARDS: \n------\n" + rewritingGreetingECards(s2, ls2) + "\n------\n");

        //test case 3
        String s3 = "The campus is on the site of former citrus groves in northeast Fullerton. It is bordered on the east by the Orange Freeway (SR-57), on the west by State College Boulevard, on the north by Yorba Linda Boulevard, and on the south by Nutwood Avenue.";
        LinkedList<String[]> ls3 = new LinkedList<>();
        ls3.add(new String[]{"north", "south"});
        ls3.add(new String[]{"east", "west"});
        ls3.add(new String[]{"Orange", "Apple"});
        ls3.add(new String[]{"former", "current"});
        ls3.add(new String[]{"Fullerton", "Irvine"});
        System.out.println("******TEST CASE 3******");
        System.out.println("INPUT ECARDS: \n------\n" + s3 + "\n------");
        System.out.println("NEW ECARDS: \n------\n" + rewritingGreetingECards(s3, ls3) + "\n------\n");

    }

    private static String rewritingGreetingECards(String input, LinkedList<String[]> ls){
        StringBuilder newECards = new StringBuilder();
        //int n = input.length();
        int m = ls.size();
        String[] words = input.split(" ");
        

        // using hashmap to store all the pairs
        HashMap<String, String> hm = new HashMap<>();
        for (int i = 0; i < m; i ++) {
            String curr = ls.get(i)[0];
            
            //curr = curr.toLowerCase();
            if (!hm.containsKey(curr)) {
                hm.put(curr, ls.get(i)[1]);
            }
        }

        // replace ecards word by word
        for (int i = 0; i < words.length; i ++){
            String currWord = words[i];
            char lastChar = currWord.charAt(currWord.length() - 1);

            if (lastChar < 'A' || lastChar > 'z') {
                currWord = currWord.substring(0, currWord.length() - 1);
            }

            // check whether the first character is uppercase            
            //boolean upperCase = false;
            // if (currWord.charAt(0) >= 'A' && currWord.charAt(0) <= 'Z') {
            //     upperCase = true;
            //     currWord = currWord.toLowerCase();
            // }

            // append curr word given by LS
            if (hm.containsKey(currWord)) {
                String newWord = hm.get(currWord);
                //if (upperCase) {
                //    newWord = Character.toUpperCase(newWord.charAt(0)) + newWord.substring(1);
                //}
                newECards.append(newWord);
            } else {
                
                //check plural, convert to singular if needed
                if (hm.containsKey(currWord.substring(0, currWord.length() - 1))) {
                    currWord = hm.get(currWord.substring(0, currWord.length() - 1)) + 's';
                }

                // check superlative
                if (currWord.length() >= 3 &&hm.containsKey(currWord.substring(0, currWord.length() - 3))) {
                    currWord = hm.get(currWord.substring(0, currWord.length() - 3)) + "est";
                }

                // if (upperCase) {
                //     currWord = Character.toUpperCase(currWord.charAt(0)) + currWord.substring(1);
                // }

                newECards.append(currWord);
            }
            // newECards.append(lastChar);
            if (lastChar < 'A' || lastChar > 'z') {
                newECards.append(lastChar);
            }
            newECards.append(' ');
        }

        return newECards.toString();
    }
} 