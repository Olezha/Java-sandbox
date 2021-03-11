package edu.olezha.sandbox.task;

import java.util.*;
import java.util.stream.Collectors;

public class Twits {

    public static void main(String[] args) {
        List<String> twits = new ArrayList<>();
        twits.add("#Java and #Scala are the languages of cognitive and AI development. IBM sees cognitive development is the future.");
        twits.add("Some more info on the IBM investment in #Scala and Lightbendhttp8/ibm-lightbend-join-forces-enterpri");
        twits.add("IBM and @lightbend are building an integrated platform for #Java and #Scala #cognitive app development");
        System.out.println(getTwits(twits));
    }

    private static List<String> getTwits(List<String> twits) {
        Map<String, Integer> tags = new HashMap<>();
        for (String twit : twits) {
            String[] words = twit.split(" ");
            for (String word : words) {
                if (word.startsWith("#")) {
                    Integer occurrences = tags.get(word);
                    tags.put(word, occurrences == null ? 1 : ++occurrences);
                }
            }
        }

        return tags.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
