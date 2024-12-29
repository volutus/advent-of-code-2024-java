import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Page implements Comparable<Page>
{
	private final int value;
	private final Map<Integer, List<Integer>> rules;

	public Page(int value, Map<Integer, List<Integer>> rules)
	{
		this.value = value;
		this.rules = rules;
	}

	private List<Integer> getRulesForKey(int value)
	{
		return this.rules.getOrDefault(value, new ArrayList<>());
	}

	@Override
	public int compareTo(Page that)
	{
		List<Integer> rulesForThis = this.getRulesForKey(this.value);
		List<Integer> rulesForThat = this.getRulesForKey(that.value);
		if (rulesForThat.contains(this.value))
		{
			return 1;
		}
		if (rulesForThis.contains(that.value))
		{
			return -1;
		}
		return 0;
	}

	@Override
	public String toString()
	{
		return this.value + "";
	}

	public static int middle(List<Page> list)
	{
		Page middle = list.get(list.size() / 2);
		return middle.value;
	}
}
