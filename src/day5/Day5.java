package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day5 implements Runnable
{
	private static final String FILENAME = "res/day5.txt";

	private List<String> lines;
	private final Map<Integer, List<Integer>> rules = new HashMap<>();
	private final List<List<Page>> updates = new ArrayList<>();
	private int part1Sum;
	private int part2Sum;

	public static void main(String[] args)
	{
		Day5 runner = new Day5();
		runner.run();
	}

	@Override
	public void run()
	{
		readFile();
		processLines();
		checkPageUpdates();
		printResults();
	}

	private void readFile()
	{
		try
		{
			this.lines = Files.readAllLines(Path.of(FILENAME));
		}
		catch (IOException ioe)
		{
			System.err.println("Error reading input file: " + ioe);
		}
	}

	private void processLines()
	{
		int splitPoint = lines.indexOf("");
		List<String> rules = lines.subList(0, splitPoint);
		List<String> updates = lines.subList(splitPoint + 1, lines.size());
		for (String rule: rules)
		{
			String[] parts = rule.split("\\|");
			addRule(parts[0], parts[1]);
		}

		for (String line: updates)
		{
			String[] parts = line.split(",");
			addUpdate(parts);
		}
	}

	private void addRule(String keyString, String valueString)
	{
		int key = Integer.parseInt(keyString);
		int value = Integer.parseInt(valueString);
		if (!this.rules.containsKey(key))
		{
			this.rules.put(key, new ArrayList<>());
		}
		this.rules.get(key).add(value);
	}

	private void addUpdate(String[] updates)
	{
		List<String> listOfStrings = Arrays.stream(updates).toList();
		List<Integer> list = listOfStrings.stream().map(Integer::valueOf).toList();
		List<Page> pages = new ArrayList<>();
		for (Integer value: list)
		{
			pages.add(new Page(value, rules));
		}
		this.updates.add(pages);
	}

	private void checkPageUpdates()
	{
		for (List<Page> updateList: updates)
		{
			List<Page> sorted = updateList.stream().sorted().toList();
			if (sorted.equals(updateList))
			{
				part1Sum += Page.middle(updateList);
			}
			else
			{
				part2Sum += Page.middle(sorted);
			}
		}
	}

	private void printResults()
	{
		System.out.println("PART 1: " + part1Sum);
		System.out.println("PART 2: " + part2Sum);
	}
}