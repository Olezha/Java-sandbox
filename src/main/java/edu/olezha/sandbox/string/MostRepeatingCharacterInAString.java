package edu.olezha.sandbox.string;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MostRepeatingCharacterInAString {

    public static void main(String[] args) {
        log.info("{}", mostRepeatingCharacterInAString("aaAbbcdddee"));
        log.info("{}", mostRepeatingCharacterInAString("aaAbbbcdddee"));

        log.info("{}", noMoreThan50PercentOfDuplicateCharacters("aaAbbbcdddee"));
        log.info("{}", noMoreThan50PercentOfDuplicateCharacters("aaAbbbcdddeebbbbbbb"));
        log.info("{}", noMoreThan50PercentOfDuplicateCharacters("aabbaa"));
    }

    private static Map.Entry<Character, Integer> mostRepeatingCharacterInAString(String string) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : string.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        log.info("{}", list);

        return list.get(list.size() - 1);
    }

    private static boolean noMoreThan50PercentOfDuplicateCharacters(String string) {
        return mostRepeatingCharacterInAString(string).getValue() <= string.length() / 2;
    }
}
