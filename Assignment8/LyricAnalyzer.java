package Assignment8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class LyricAnalyzer {
	private HashMap<String, ArrayList<Integer>> map;
	private int count;
	public LyricAnalyzer() {
		map = new HashMap<String, ArrayList<Integer>>();
		count = 0;
	}
	public void read(File file) throws IOException {
		FileReader reader = new FileReader(file);
		Scanner in = new Scanner(reader);
		String[] words;
		while(in.hasNextLine()) {
			words = in.nextLine().split(" ");
			for(int i = 0; i < words.length; i++) {
				count ++;
				if(i < words.length - 1)
					this.add(words[i], count);
				else
					this.add(words[i], -count);
			}
		}
		reader.close();
	}
	
	private void add(String lyricWord, int wordPosition) {
		if (map.containsKey(lyricWord)) {
			map.get(lyricWord).add(wordPosition);
		}
		else {
			ArrayList<Integer> positions = new ArrayList<>();
			positions.add(wordPosition);
			map.put(lyricWord, positions);
		}
	}
	public void displayWords() {
		Set set = map.keySet();
		ArrayList<String> list = new ArrayList<String>(set);
		Collections.sort(list);
		for(int i = 0; i < list.size(); i ++) {
			System.out.print(list.get(i).toUpperCase() + ": ");
			for(int j = 0; j < map.get(list.get(i)).size(); j ++) {
				System.out.print(map.get(list.get(i)).get(j));
				if(j < map.get(list.get(i)).size() - 1)
					System.out.print(",");
			}
			System.out.print("\n");
		}
		
	}
	
	public void writeLyrics(File file) throws IOException{
		String[] words = new String[this.count + 1];
		for(Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			for(int i = 0; i < entry.getValue().size(); i ++) {
				if(entry.getValue().get(i) > 0)
					words[entry.getValue().get(i)] = entry.getKey() + " ";
				else
					words[-entry.getValue().get(i)] = entry.getKey() + "\r\n";
			}
		}
		FileWriter writer = new FileWriter(file);
		for(int i = 1; i < words.length; i ++) {
			writer.write(words[i]);
		}
		writer.close();
	}
	public int count() {
		int onlycount = 0;
		for(Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			if(entry.getValue().size() == 1)
				onlycount ++;
		}
		return onlycount;
	}
	public String mostFrequentWord() {
		String word = "init";
		int maxFrequency = 0;
		for(Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			if(entry.getValue().size() > maxFrequency) {
				maxFrequency = entry.getValue().size();
				word = entry.getKey();
			}
			if(entry.getValue().size() == maxFrequency && entry.getKey().compareTo(word) < 0)
				word = entry.getKey();
		}
		return word;
	}

	public static void main(String[] args) throws IOException {
		String[] filename = {"test1.txt", "test2.txt", "test3.txt", "test4.txt"};
		for(String name : filename) {
			LyricAnalyzer analyzer = new LyricAnalyzer();
			File testfile = new File(name);
			analyzer.read(testfile);
			analyzer.displayWords();
			char [] namechars = new char[5];
			for(int i = 0; i < 5; i ++) {
				namechars[i] = name.charAt(i);
			}
			String outfilename = String.valueOf(namechars);
			outfilename += "_out.txt";
			File outtest = new File(outfilename);
			analyzer.writeLyrics(outtest);
			System.out.println("Unique word count: " + analyzer.count);
			System.out.println("Most frequency word: " + analyzer.mostFrequentWord() + "\n");
		}

	}

}
