package com.june.streamsAndLambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

		Player player1 = new Player("FredFlintstone", 60.00, 2000);
		Player player2 = new Player("WilmaFlintstone", 80.00, 1975);
		Player player3 = new Player("PebblesFlintstone", 90.00, 1950);

		List<Player> players = new ArrayList<>(Arrays.asList(player1, player2, player3));

		Player[] playerArray = { player1, player2, player3 };

		Long playersOver2000 = players.stream().filter(player -> player.getAge() >= 2000).count();
		System.out.println("number of players older than 2000 " + playersOver2000);

		List<Integer> ages = Arrays.stream(playerArray).map(player -> player.getAge()).collect(Collectors.toList());

		ages.forEach(player -> System.out.println(player));

		Optional<Integer> ageOfOldestPlayer = players.stream().map(player -> player.getAge())
				.max((age1, age2) -> age1 - age2);

		ageOfOldestPlayer.ifPresent(age -> System.out.println("oldest player is " + age + " years old"));

		OptionalDouble averageAgeOfPlayers = players.stream().mapToInt(player -> player.getAge()).average();

		averageAgeOfPlayers.ifPresent(age -> System.out.println("The average age is " + Math.round(age)));

		OptionalInt ageOfOldest2 = players.stream().mapToInt(player -> player.getAge()).max();

		BinaryOperator<Player> oldestOfTwoPlayers = (firstPlayer,
				secondPlayer) -> firstPlayer.getAge() > secondPlayer.getAge() ? firstPlayer : secondPlayer;

		Optional<Player> oldestPerson = players.stream().reduce(oldestOfTwoPlayers);

		System.out.println(oldestPerson.get().getUsername());

	}
}
