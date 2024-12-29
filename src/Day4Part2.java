import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day4Part2 implements Runnable
{
	private List<List<Character>> matrix = new ArrayList<>();
	public static void main(String[] args)
	{
		Day4Part2 runner = new Day4Part2();
		runner.run();
	}

	@Override
	public void run()
	{
		readFile();
		int score = 0;
		for (int i = 0; i < matrix.size(); i++)
		{
			List<Character> line = matrix.get(i);
			for (int j = 0; j < line.size(); j++)
			{
				char c = fetchFromMatrix(i, j);
				if (c == 'A')
				{
					char upperLeft = fetchFromMatrix(i-1, j-1);
					char bottomLeft = fetchFromMatrix( i+1, j-1);
					char upperRight = fetchFromMatrix( i-1, j+1);
					char bottomRight = fetchFromMatrix(i+1, j+1);

					if (validChristmas(upperLeft, bottomRight) && validChristmas(upperRight, bottomLeft))
					{
						score++;
					}
				}
			}
		}
		System.out.println(score);

	}

	private void readFile()
	{
		try
		{
			List<String> lines = Files.readAllLines(Path.of("res/day4.txt"));
			for (String line: lines)
			{
				char[] chars = line.toCharArray();
				List<Character> thisLine = new ArrayList<>();
				for (char c: chars)
				{
					thisLine.add(c);
				}
				this.matrix.add(thisLine);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private char fetchFromMatrix(int i, int j)
	{
		if (i < 0 || j < 0)
		{
			return ' ';
		}
		if (i >= this.matrix.size())
		{
			return ' ';
		}
		if (j >= this.matrix.get(i).size())
		{
			return ' ';
		}
		return this.matrix.get(i).get(j);
	}

	private boolean validChristmas(char leftCorner, char rightCorner)
	{
		return (leftCorner == 'M' && rightCorner == 'S' || leftCorner == 'S' && rightCorner == 'M');
	}
}
