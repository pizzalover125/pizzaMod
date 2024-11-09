package com.pizzalover125.pizzamod;

import com.pizzalover125.pizzamod.item.ModItems;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Random;

import static net.minecraft.server.command.CommandManager.literal;

public class PizzaModCommands {
    private static final String[] pizzaFacts = PizzaModFacts.getPizzaFacts();
    private static final String[] pizzaJokes = PizzaModJokes.getPizzaJokes();

    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("pizza")
                    .then(literal("give-slice")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {
                                    ItemStack pizzaSlice = new ItemStack(ModItems.PIZZA_SLICE, 1);

                                    boolean success = player.getInventory().insertStack(pizzaSlice);

                                    if (success) {
                                        player.sendMessage(Text.literal("You received a slice of pizza!"), false);
                                    } else {
                                        player.sendMessage(Text.literal("Couldn't give a pizza slice - inventory full!"), false);
                                    }
                                }

                                return 1;
                            })
                    )
            );

            dispatcher.register(literal("pizza")
                    .then(literal("give-slice-stack")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {
                                    ItemStack pizzaSlices = new ItemStack(ModItems.PIZZA_SLICE, 64);
                                    boolean success = player.getInventory().insertStack(pizzaSlices);

                                    if (success) {
                                        player.sendMessage(Text.literal("You received 64 slices of pizza!"), false);
                                    } else {
                                        player.sendMessage(Text.literal("Couldn't give more pizza slices - inventory full!"), false);
                                    }
                                }

                                return 1;
                            })
                    )
            );

            dispatcher.register(literal("pizza")
                    .then(literal("give-whole")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {
                                    ItemStack pizzaSlices = new ItemStack(ModItems.PIZZA, 1);
                                    boolean success = player.getInventory().insertStack(pizzaSlices);

                                    if (success) {
                                        player.sendMessage(Text.literal("You received a whole pizza!"), false);
                                    } else {
                                        player.sendMessage(Text.literal("Couldn't give more pizza - inventory full!"), false);
                                    }
                                }

                                return 1;
                            })
                    )
            );

            dispatcher.register(literal("pizza")
                    .then(literal("give-whole-stack")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {
                                    ItemStack pizzaSlices = new ItemStack(ModItems.PIZZA, 64);
                                    boolean success = player.getInventory().insertStack(pizzaSlices);

                                    if (success) {
                                        player.sendMessage(Text.literal("You received 64 whole pizzas!"), false);
                                    } else {
                                        player.sendMessage(Text.literal("Couldn't give more pizza - inventory full!"), false);
                                    }
                                }

                                return 1;
                            })
                    )
            );

            dispatcher.register(literal("pizza")
                    .then(literal("give-whole-inventory")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {
                                    ItemStack pizzaSlices = new ItemStack(ModItems.PIZZA, 2304);
                                    boolean success = player.getInventory().insertStack(pizzaSlices);

                                    if (success) {
                                        player.sendMessage(Text.literal("You received 2304 whole pizzas!"), false);
                                    } else {
                                        player.sendMessage(Text.literal("Couldn't give more pizza - inventory full!"), false);
                                    }
                                }

                                return 1;
                            })
                    )
            );

            dispatcher.register(literal("pizza")
                    .then(literal("fact")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {
                                    Random random = new Random();
                                    int randomIndex = random.nextInt(pizzaFacts.length);
                                    String randomFact = pizzaFacts[randomIndex];

                                    player.sendMessage(Text.literal("Here's your random Pizza Fact: " + randomFact), false);
                                }

                                return 1;
                            })
                    )
            );

            dispatcher.register(literal("pizza")
                    .then(literal("10-facts")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {
                                    for (int i = 0; i < 10; i++) {
                                        Random random = new Random();
                                        int randomIndex = random.nextInt(pizzaFacts.length);
                                        String randomFact = pizzaFacts[randomIndex];

                                        player.sendMessage(Text.literal("Here's your random Pizza Fact: " + randomFact), false);
                                    }
                                }

                                return 1;
                            })
                    )
            );

            dispatcher.register(literal("pizza")
                    .then(literal("64-facts")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {

                                    for (int i = 0; i < 64; i++) {
                                        Random random = new Random();
                                        int randomIndex = random.nextInt(pizzaFacts.length);
                                        String randomFact = pizzaFacts[randomIndex];
                                        player.sendMessage(Text.literal("Here's your random Pizza Fact: " + randomFact), false);
                                    }
                                }

                                return 1;
                            })
                    )
            );

            dispatcher.register(literal("pizza")
                    .then(literal("joke")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {
                                    Random random = new Random();
                                    int randomIndex = random.nextInt(pizzaJokes.length);
                                    String randomJoke = pizzaJokes[randomIndex];
                                    player.sendMessage(Text.literal("Here's your random Pizza Joke: " + randomJoke), false);
                                }

                                return 1;
                            })
                    )
            );

            dispatcher.register(literal("pizza")
                    .then(literal("10-jokes")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {
                                    for (int i = 0; i < 10; i++) {
                                        Random random = new Random();
                                        int randomIndex = random.nextInt(pizzaJokes.length);
                                        String randomJoke = pizzaJokes[randomIndex];
                                        player.sendMessage(Text.literal("Here's your random Pizza Joke: " + randomJoke), false);
                                    }
                                }

                                return 1;
                            })
                    )
            );

            dispatcher.register(literal("pizza")
                    .then(literal("64-jokes")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {
                                    for (int i = 0; i < 64; i++) {
                                        Random random = new Random();
                                        int randomIndex = random.nextInt(pizzaJokes.length);
                                        String randomJoke = pizzaJokes[randomIndex];
                                        player.sendMessage(Text.literal("Here's your random Pizza Joke: " + randomJoke), false);
                                    }
                                }

                                return 1;
                            })
                    )
            );

            dispatcher.register(literal("pizza")
                    .then(literal("ascii")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {
                                    player.sendMessage(Text.literal("" +
                                            "      ████████                  \n" +
                                            "      ██▓▓▓▓▓▓████              \n" +
                                            "      ██████▓▓▓▓▓▓██            \n" +
                                            "    ██░░░░░░██▓▓▓▓▓▓██          \n" +
                                            "    ██░░░░░░██▓▓▓▓▓▓▓▓██        \n" +
                                            "    ██░░░░░░░░██▓▓▓▓▓▓▓▓██      \n" +
                                            "    ██░░░░░░░░░░██▓▓▓▓▓▓▓▓██    \n" +
                                            "  ██░░░░████░░░░░░██▓▓▓▓▓▓▓▓██  \n" +
                                            "  ██░░██▓▓▓▓██░░░░░░██▓▓▓▓▓▓██  \n" +
                                            "  ██░░██▓▓▓▓██░░░░░░░░████▓▓▓▓██\n" +
                                            "  ██░░░░████░░░░░░░░██▓▓▓▓██▓▓██\n" +
                                            "██░░░░░░░░░░░░░░░░░░██▓▓▓▓██▓▓██\n" +
                                            "██░░░░████░░░░░░░░░░░░██████████\n" +
                                            "██░░██▓▓▓▓██░░░░░░████████      \n" +
                                            "██░░██▓▓▓▓████████              \n" +
                                            "██████████                      \n"), false);
                                }

                                return 1;
                            })
                    )
            );

            dispatcher.register(literal("pizza")
                    .then(literal("author-about")
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();

                                if (player != null) {
                                    player.sendMessage(Text.literal("" +
                                            "Hey! I'm pizzalover125. An aspiring programmer looking to " +
                                            "change the world with his code, one line at a time. While I " +
                                            "haven’t changed the world yet, each project I build, skill I " +
                                            "acquire, and experience I gain brings me closer to that goal." +
                                            "See more of my projects at https://pizzalover125.tech/." +
                                            ""), false);
                                }

                                return 1;
                            })
                    )
            );
        });
    }
}
