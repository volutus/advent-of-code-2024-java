package day4;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day4
{
	private static final String SEARCH_WORD = "XMAS";

	public static void main(String[] args) throws Exception
	{
		List<List<Character>> matrix = new ArrayList<>();
		List<String> lines = Files.readAllLines(Path.of("res/day4.txt"));
		for (String line: lines)
		{
			char[] chars = line.toCharArray();
			List<Character> thisLine = new ArrayList<>();
			for (char c: chars)
			{
				thisLine.add(c);
			}
			matrix.add(thisLine);
		}

		// For each entry in the matrix, perform a search to see if the character has any matches on the 8 search axes
		// [+1][+0] | Right Horizontal
		// [+1][+1] | Right Diagonal, Down
		// [+0][+1] | Below
		// [-1][-1] | Left Diagonal, Down
		// [-1][+0] | Left Horizontal
		// [-1][+1] | Left Diagonal, Up
		// [+0][-1] | Above
		// [+1][-1] | Right Diagonal, Up
		List<Transform> transforms = new ArrayList<>();
		transforms.add(new Transform(1, 0));
		transforms.add(new Transform(1, 1));
		transforms.add(new Transform(0, 1));
		transforms.add(new Transform(-1, -1));
		transforms.add(new Transform(-1, 0));
		transforms.add(new Transform(-1, 1));
		transforms.add(new Transform(0, -1));
		transforms.add(new Transform(1, -1));

		int score = 0;
		for (int i = 0; i < matrix.size(); i++)
		{
			List<Character> line = matrix.get(i);
			for (int j = 0; j < line.size(); j++)
			{
				for (Transform transform: transforms)
				{
					String candidate = transform.search(matrix, i, j);
					if (candidate.equals(SEARCH_WORD))
					{
						score++;
					}
				}
			}
		}
		System.out.println(score);
	}
}

