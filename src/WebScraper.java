package com.company;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class WebScraper {
    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    public static void main(String[] args) {
        String[] words = wordsList("http://erdani.com/tdpl/hamlet.txt");
        System.out.println(words.length);
        int wordCount = wordFrequency("prince","http://erdani.com/tdpl/hamlet.txt" );
        System.out.println(wordCount);
    }

    public static String[] wordsList(String url) {
        String content = urlToString(url);
        String[] words = content.split(" ");
        return words;
    }

    public static int wordFrequency(String word, String url) {
        int frequency = 0;
        String [] words = wordsList(url);
        for (int i = 0; i < words.length; i++) {
            if (word.equalsIgnoreCase(words[i])) {
                frequency++;
            }
        }
        //currently doesnt work if words include special characters at start or end.
        return frequency;
    }
}