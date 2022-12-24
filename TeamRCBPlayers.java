
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

public class TeamRCBPlayers {

	JsonPath teamJS = new JsonPath(teaamrcb.teamrcb());

	@Test(priority = 1)
	public void testTotalForeignPlayers() {

		List<String> allCountries = teamJS.getJsonObject("player.country");
		List<String> foreignCountries = new ArrayList<String>();
		if (teamJS != null && allCountries.size() >= 4) {
			foreignCountries = allCountries.stream().filter(item -> !item.contains("India")).toList();
		}
		Assert.assertEquals(foreignCountries.size(), 4);
	}

	@Test(priority = 2)
	public void testwicketPlayerInTeam() {

		List<String> roles = teamJS.getJsonObject("player.role");
		Optional<String> wicketKeeper = Optional.empty();
		if (!roles.isEmpty()) {
			wicketKeeper = roles.stream().filter(item -> item.equals("Wicket-keeper")).findAny();
		}
		Assert.assertEquals(wicketKeeper.isPresent(), true);

	}
}
