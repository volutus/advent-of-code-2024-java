package day4;

import java.util.List;

public class Transform
{
	public int xDelta;
	public int yDelta;

	public Transform(int xDelta, int yDelta)
	{
		this.xDelta = xDelta;
		this.yDelta = yDelta;
	}

	public String search(List<List<Character>> matrix, int i, int j)
	{
		StringBuilder sb = new StringBuilder();
		boolean searching = true;
		while (searching)
		{
			char c = matrix.get(i).get(j);
			sb.append(c);
			i += this.yDelta;
			j += this.xDelta;

			int outerLength = matrix.size();
			if (i < 0 || i >= outerLength)
			{
				searching = false;
			}
			else
			{
				int innerLength = matrix.get(i).size();
				if (j < 0 || j >= innerLength)
				{
					searching = false;
				}
			}

			if (sb.length() >= 4)
			{
				searching = false;
			}
		}
		return sb.toString();
	}
}
