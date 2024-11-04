package com.pizzalover125.pizzamod;

import com.mojang.brigadier.CommandDispatcher;
import com.pizzalover125.pizzamod.block.ModBlocks;
import com.pizzalover125.pizzamod.item.ModItemGroups;
import com.pizzalover125.pizzamod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static net.minecraft.server.command.CommandManager.literal;

public class PizzaMod implements ModInitializer {
	public static final String MOD_ID = "pizzamod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final String[] pizzaFacts = {
			"Pizza is @pizzalover125's favorite food.",
			"Hawaiian pizza originated in Canada.",
			"The Museum of Pizza in Philadelphia is exactly what it sounds like.",
			"Pizza was originally made in a square shape.",
			"Pizza is 500+ years old.",
			"Pizza has been delivered to the International Space Station.",
			"The biggest pizza has an area of 1200 square meters, or 12916 square feet.",
			"NASA has funded a startup that makes 3D printed pizzas.",
			"February 9th is World Pizza Day.",
			"Margherita pizza was named after Queen Margherita.",
			"Americans consume 100 acres of pizza daily.",
			"17% of American restaurants are pizzerias.",
			"1 billion frozen pizzas are sold in the US annually.",
			"The world's most expensive pizza costs $2,745.",
			"In the US, October is National Pizza Month.",
			"5 billion pizzas are sold worldwide each year.",
			"Every second, Americans order 350 slices of pizza.",
			"1/3 of pizzas sold in the US contain pepperoni.",
			"93% of Americans eat pizza at least once a month.",
			"In Japan, there is a pizza chain that offers a pizza topped with fried chicken, mayonnaise, and corn.",
			"The first pizzeria in the US opened in New York City in 1905.",
			"The average American eats approximately 46 slices of pizza each year.",
			"Pineapple on pizza is a contentious topic among pizza lovers.",
			"The word 'pizza' was first documented in 997 AD in Italy.",
			"Cheese was the most popular pizza topping in the US in 2020.",
			"The classic Neapolitan pizza is made with just tomatoes, mozzarella, fresh basil, and olive oil.",
			"There are over 70,000 pizzerias in the United States.",
			"In Italy, pizza is considered a culinary art and is protected by law.",
			"The first pizza delivery service started in 1889.",
			"Mozzarella di bufala, made from water buffalo milk, is the traditional cheese for Neapolitan pizza.",
			"The world's largest pizza was made in Rome in 2012 and measured 1261.65 square meters.",
			"A pizza without cheese is called a marinara pizza in Italy.",
			"The average delivery time for pizza is about 30 minutes.",
			"Pepperoni is the most popular pizza topping in the United States.",
			"In Russia, pizza is sometimes topped with salmon and sour cream.",
			"In Brazil, pizza is often served with green peas on top.",
			"There are more than 40,000 pizzerias in Italy alone.",
			"The most popular day for pizza orders in the US is Super Bowl Sunday.",
			"Pizza Hut was the first pizza chain to offer online ordering.",
			"There are nearly 1,000 types of pizza recognized around the world.",
			"The first frozen pizza was produced in 1957.",
			"In South Korea, you can find pizzas topped with sweet potato and mayonnaise.",
			"In the 16th century, tomatoes were thought to be poisonous.",
			"In 2018, a 500-pound pizza was made to celebrate National Pizza Day.",
			"There is a pizza festival in Naples, Italy, celebrating the art of pizza-making.",
			"Chicago-style deep-dish pizza originated in the Windy City in the 1940s.",
			"In 2014, a pizza topped with edible gold leaf was sold in Italy for $1,000.",
			"A pizza with a crust made entirely of cauliflower is a popular gluten-free alternative.",
			"The Guinness World Record for the largest pizza delivery was for 30,000 pizzas sent to the troops in 2015.",
			"The average pizza restaurant serves about 200 pizzas a day.",
			"Calzones are essentially folded pizzas, originating from Naples.",
			"The first frozen pizza was created by the company Celentano's in 1950.",
			"In Australia, some pizzas come topped with kangaroo meat.",
			"The world record for the most pizzas made in an hour is 1,480.",
			"In Italy, pizza is often eaten with a knife and fork.",
			"Many pizzerias offer a 'pizza of the month' featuring unique toppings.",
			"Pizza is considered a staple food in many countries around the world.",
			"The pepperoni pizza was invented in the US in the early 20th century.",
			"Sicilian pizza is known for its thick, square crust.",
			"New York-style pizza is characterized by its large, foldable slices.",
			"In Venezuela, a popular topping is cabbage and mayonnaise.",
			"The first pizza vending machine was installed in Italy in 2009.",
			"In some parts of Italy, it's customary to eat pizza at lunchtime.",
			"Some pizzerias allow customers to create their own pizza by selecting from a variety of toppings.",
			"The first known pizza menu was printed in a restaurant in Naples in 1830.",
			"Pizza can be traced back to ancient civilizations, with similar flatbreads found in Egyptian, Greek, and Roman cultures.",
			"In 2010, a pizzeria in Italy set a record for making a pizza that was 1,500 meters long.",
			"There are numerous pizza-related holidays celebrated in different cultures around the world.",
			"In Canada, 'poutine pizza' combines classic pizza with the flavors of poutine.",
			"In India, you can find pizzas topped with paneer and tikka spices.",
			"Some pizzerias offer dessert pizzas, often topped with sweet ingredients like chocolate and fruits.",
			"The average person will eat pizza once every 3 days over their lifetime.",
			"The first pizza ever delivered was made for a queen in 1889.",
			"The Italian city of Naples is known as the birthplace of pizza.",
			"In the US, pizza consumption peaks around major sporting events and holidays.",
			"In Germany, pizza is often topped with an assortment of vegetables and meats.",
			"Some cities have unique pizza styles, like Detroit-style pizza, known for its thick crust and rectangular shape.",
			"A popular pizza topping in Italy is prosciutto, often added after baking.",
			"Pizza is often featured at children's birthday parties and celebrations.",
			"There are competitions held worldwide for pizza making and creativity.",
			"In Italy, pizza is traditionally served as a single personal pie.",
			"The first commercial pizzeria was established in 1830 in Naples, Italy.",
			"Certain pizza chefs are known as pizzaiolos and undergo rigorous training.",
			"In some countries, like Japan, you can find pizzas with unique toppings like squid and mayonnaise.",
			"Pizza has inspired countless variations and fusion cuisines globally.",
			"The term 'pizza' comes from the Latin word 'pinsa,' meaning flatbread.",
			"The largest pizza chain in the world is Domino's Pizza.",
			"Some people consider a pizza without tomato sauce a 'white pizza.'",
			"Many pizzerias offer gluten-free crust options to cater to dietary restrictions.",
			"In Argentina, pizza is often served with a side of faina, a chickpea flatbread.",
			"The 'Chicago deep-dish' pizza is known for its buttery crust and layers of toppings.",
			"In Turkey, lahmacun is a type of flatbread pizza topped with minced meat and spices.",
			"Some pizzerias allow you to customize your pizza with a variety of crust types, including stuffed crust.",
			"The largest pizza party took place in Rome, with over 1,000 participants making pizzas.",
			"Pizza is often paired with beverages like soda, beer, and wine.",
			"In the US, specialty pizzas are often themed around popular culture.",
			"The traditional Italian pizza is cooked in a wood-fired oven for a distinctive flavor.",
			"A popular late-night food, pizza is often found at food trucks and late-night eateries.",
			"Some regions have specific pizza styles, like the 'New Haven apizza,' known for its charred crust.",
			"Pizza can be made with a variety of cheeses, including mozzarella, cheddar, and goat cheese.",
			"In 1987, the first pizza in space was sent aboard a Russian spacecraft.",
			"There are more than 700 types of cheese that can be used on pizzas worldwide.",
			"In the Philippines, the 'Halo-Halo' pizza features tropical fruits and sweet toppings.",
			"A pizza without sauce is known as a 'dry pie' in some regions.",
			"In some cultures, pizza is often eaten as a breakfast food.",
			"There are pizza festivals celebrating this beloved dish in cities around the world.",
			"Pizza-making is often a family activity, with everyone contributing their favorite toppings.",
			"In Canada, 'butter chicken pizza' has become a popular fusion dish.",
			"In South Africa, you can find pizzas topped with boerewors, a type of sausage.",
			"The 'four cheese' pizza, or quattro formaggi, is a favorite in Italy.",
			"Pizza can be topped with everything from traditional ingredients to avant-garde combinations.",
			"In many places, pizza is often enjoyed as a late-night snack or meal.",
			"Some pizzerias specialize in organic or locally sourced ingredients.",
			"The concept of pizza has evolved significantly since its inception in ancient times.",
			"In Finland, pizza can be topped with shrimp and mayonnaise.",
			"Some pizzerias have their own unique secret sauce recipes that make them stand out.",
			"In many cultures, pizza is often shared among friends and family during gatherings.",
			"Pizza has inspired a wide range of merchandise, from clothing to home decor.",
			"In Mexico, pizza can be found with unique toppings like jalapeños and chorizo.",
			"Pizza is often the go-to meal for busy families and college students.",
			"A popular pizza trend is using non-traditional bases, such as cauliflower or zucchini.",
			"In Italy, pizza is often enjoyed as a casual meal, not necessarily a gourmet experience.",
			"Many restaurants offer pizza-making classes for enthusiasts.",
			"Pizza is one of the most photographed foods on social media.",
			"A popular Italian phrase, 'mangia' means to eat and is often said before enjoying pizza.",
			"In some regions, pizza is served with chili oil or hot pepper flakes for added spice.",
			"The iconic pizza box was invented in 1960 to keep pizzas hot during delivery.",
			"Many pizzerias have signature pizzas that reflect their local culture and flavors.",
			"In Norway, pizza is often topped with a variety of fish, including herring and salmon.",
			"In the US, there are entire restaurants dedicated to gourmet pizza experiences.",
			"Pizza is often served at social events like game nights and family gatherings.",
			"The classic pepperoni pizza originated from Italian-American cuisine.",
			"Some pizzas are topped with dessert ingredients, like Nutella or fruit, for a sweet twist.",
			"Many cities host pizza-eating contests during festivals and events.",
			"Pizza is often considered a comfort food and is popular among all age groups.",
			"The word 'pizzaiolo' refers to a pizza maker, often with years of training.",
			"In many cultures, pizza is enjoyed as a street food, sold from carts and stalls.",
			"Some pizzerias offer 'pizza flights,' allowing you to sample several different types.",
			"The first pizza restaurant to use a pizza oven was opened in Naples, Italy.",
			"Pizza is often made with fresh herbs, like basil and oregano, to enhance flavor.",
			"In India, 'tandoori chicken pizza' combines traditional Indian flavors with pizza.",
			"Many people enjoy customizing their pizzas with unusual toppings and combinations.",
			"The concept of vegan pizza has gained popularity, featuring plant-based cheese and toppings.",
			"Pizza can be found in various forms, from personal-sized pies to large party pizzas.",
			"In some cultures, pizza is eaten with a fork and knife instead of by hand.",
			"Pizza is one of the most popular takeout and delivery foods worldwide.",
			"In the UK, 'deep-fried pizza' is a popular late-night snack.",
			"Some pizzerias feature rotating seasonal toppings to keep their menu fresh.",
			"Pizza is often a favorite food for celebrations, including parties and holidays.",
			"In Italy, it's common to find pizzas made with seasonal ingredients.",
			"The 'Detroit-style' pizza is known for its rectangular shape and thick crust.",
			"In Brazil, pizza is often topped with chocolate and fruit as a dessert option.",
			"Many pizzerias offer loyalty programs for frequent customers.",
			"In Australia, the 'Aussie pizza' typically features bacon and egg.",
			"Some pizzerias have unique partnerships with local breweries for pizza and beer pairings.",
			"In Hungary, pizza can be topped with sour cream and paprika.",
			"Pizza has made its way into popular culture, appearing in movies, shows, and songs.",
			"In many areas, pizza is often sold by the slice for convenient snacking.",
			"Some regions have a tradition of making pizza for special occasions.",
			"The 'Roman pizza' is characterized by its thin, crispy crust.",
			"Pizza is often included in school lunch menus in various countries.",
			"In Italy, pizza is a symbol of regional pride, with each area having its unique style.",
			"The term 'pizza' can also refer to any flatbread dish in certain cultures.",
			"Some pizzerias offer specialty crusts, such as garlic or cheese-stuffed options.",
			"In some parts of the US, you can find pizza topped with ingredients like cream cheese and lox.",
			"Pizza is often enjoyed during movie nights, game days, and family gatherings.",
			"Many pizzerias feature a 'build your own pizza' option on their menu.",
			"In Canada, 'donair pizza' combines traditional pizza with seasoned meat and sweet sauce.",
			"Pizza has evolved from a simple flatbread to a gourmet dining experience in some restaurants.",
			"Many pizzerias offer catering services for large events and gatherings.",
			"In South Korea, you can find pizza topped with sweet potato puree.",
			"Some pizzerias have locations that feature an open kitchen, allowing customers to watch their pizzas being made.",
			"Pizza can also be made using unconventional ingredients, like chickpea flour or quinoa.",
			"In some cultures, pizza is often served at breakfast with eggs and vegetables.",
			"In many places, pizza is celebrated with special promotions and discounts on National Pizza Day.",
			"The average price of a pizza can vary greatly depending on the region and ingredients used.",
			"Pizza can be made at home using store-bought dough or premade crusts for convenience.",
			"In Italy, a classic pizza is usually simple, focusing on high-quality ingredients.",
			"Pizza's popularity continues to grow globally, with new styles and flavors emerging regularly.",
			"Many pizzerias take pride in using locally sourced and organic ingredients.",
			"In some areas, pizza is considered a staple food for college students and young adults.",
			"The 'St. Louis-style' pizza is known for its cracker-like crust and Provel cheese.",
			"Pizza can be enjoyed in various forms, including stuffed crust, calzones, and pizza rolls.",
			"Some restaurants offer 'pizza kits' for customers to make their own pizzas at home.",
			"In Italy, pizza is often paired with a glass of wine for a complete dining experience.",
			"The 'Neapolitan' pizza is strictly regulated and must adhere to specific standards.",
			"Pizza toppings can range from traditional options like vegetables and meats to more unusual choices like fruit.",
			"Many pizzerias have unique and creative names inspired by pop culture and local traditions.",
			"In some regions, pizza is often topped with pickles or other unexpected ingredients.",
			"Pizza-making competitions are held worldwide, showcasing the creativity of chefs.",
			"In some countries, pizza is often served with a side of salad or appetizers.",
			"Pizza is considered an affordable meal option for families and large groups.",
			"In Finland, you can find pizzas topped with reindeer meat.",
			"Pizza is a versatile dish that can be tailored to suit various dietary needs and preferences.",
			"Many people enjoy experimenting with homemade pizza recipes using different toppings.",
			"In some places, pizza is served with a drizzle of balsamic reduction for added flavor.",
			"The first pizza restaurant to offer delivery service was opened in the US in the 1960s.",
			"In Australia, 'prawn pizza' is a popular seafood option.",
			"Pizza is often featured in fundraising events and community gatherings.",
			"In many cultures, sharing a pizza is a social activity that brings people together.",
			"Pizza is often associated with casual dining and relaxed atmospheres.",
			"In Italy, pizza is commonly enjoyed with fresh ingredients like arugula and cherry tomatoes.",
			"Some pizzerias offer a range of crust thicknesses to accommodate different preferences.",
			"The world record for the most pizzas made by one person in eight hours is over 100 pizzas.",
			"In some regions, pizza is served as a part of traditional holiday meals.",
			"Pizza has a unique ability to bring people together, making it a popular choice for gatherings.",
			"Many pizzerias feature signature sauces that differentiate them from competitors.",
			"In some cultures, pizza is eaten as a late-night snack after parties or events.",
			"The 'Napoli' pizza style emphasizes fresh, high-quality ingredients for authentic flavor.",
			"In the US, pizza is a popular choice for lunch meetings and office gatherings.",
			"Pizza can be made using a variety of cooking methods, including baking, grilling, and even frying.",
			"In some places, pizzas are made to order and cooked in traditional wood-fired ovens.",
			"Some pizzerias feature rotating seasonal menus that highlight local ingredients.",
			"In the US, pizza is often paired with garlic knots or breadsticks as a side dish.",
			"Pizza has become a cultural icon, inspiring countless memes and social media trends.",
			"The 'Detroit-style' pizza often features a sweet sauce on top of the cheese and toppings.",
			"Pizza is often enjoyed as a casual meal, perfect for sharing with friends and family.",
			"In Canada, 'pizza with poutine' combines two beloved comfort foods in one dish.",
			"Pizza can be made with a variety of flours, including whole wheat and gluten-free options.",
			"Many people have their own secret pizza recipes that they share with family and friends.",
			"In Italy, pizza is typically eaten during lunch or dinner, but not usually for breakfast.",
			"Pizza has transcended cultural boundaries, becoming a global favorite enjoyed in various forms.",
			"Some pizzerias have creative mascots or branding inspired by pizza culture.",
			"In some places, it's common to have pizza trucks that serve gourmet options at festivals.",
			"The 'white pizza' is made without tomato sauce, often featuring ricotta and garlic.",
			"In many areas, pizza is often enjoyed with a side of spicy dipping sauce.",
			"Pizza is often customized for specific dietary preferences, including vegan and keto options.",
			"The tradition of pizza dates back centuries, with roots in ancient Mediterranean cuisines.",
			"Many cities host annual pizza festivals celebrating this beloved dish with tastings and competitions.",
			"In some cultures, pizza is considered a festive food, often served during celebrations.",
			"The term 'pizza' is derived from the Italian word 'pizzicare,' meaning to pinch or pluck.",
			"Many pizzerias feature an open kitchen concept, allowing customers to see their pizzas being made.",
			"In some regions, pizza is often served with a side of soup or salad.",
			"Pizza is often featured in school events and celebrations, making it a popular choice for kids.",
			"The 'California-style' pizza is known for its innovative toppings and fresh ingredients.",
			"In some countries, pizza is commonly topped with unique spices and seasonings.",
			"The concept of pizza delivery has evolved with the rise of technology and mobile apps.",
			"Pizza is often associated with fun, casual dining experiences.",
			"In many cultures, pizza is a go-to comfort food during difficult times.",
			"Many pizzerias have embraced sustainability by using eco-friendly packaging and practices.",
			"In the US, pizza is a popular option for parties and gatherings of all sizes.",
			"The rise of artisanal pizza has led to a greater appreciation for traditional techniques and ingredients.",
			"Some pizzerias specialize in thin-crust options, focusing on crispy textures and minimal toppings.",
			"In some regions, pizza is considered a staple food for late-night cravings.",
			"The 'Sicilian' pizza is characterized by its thick crust and rectangular shape.",
			"In Canada, 'buffalo chicken pizza' is a popular choice for those who enjoy spicy flavors.",
			"Pizza has been featured in numerous television shows and movies, solidifying its place in pop culture.",
			"In many regions, pizza is enjoyed as a snack at sporting events and gatherings.",
			"Some pizzerias have adopted unique cooking techniques, such as smoking or grilling pizzas.",
			"Pizza has inspired a range of culinary innovations, including fusion dishes and unique flavor combinations.",
			"In some areas, pizza is often served as a street food, available from food trucks and stalls.",
			"Many pizzerias have adopted creative marketing strategies to attract customers.",
			"In Italy, traditional pizza is made using a simple recipe of flour, water, salt, and yeast.",
			"Pizza is often enjoyed with a glass of wine or craft beer, pairing well with various flavors.",
			"In some cultures, pizza is a symbol of friendship and togetherness.",
			"The concept of 'pizza parties' has become a popular way to celebrate special occasions.",
			"Many pizzerias feature local ingredients that reflect their region's culinary heritage.",
			"In some places, pizza is often served with a side of pickles or olives.",
			"The tradition of pizza-making has been passed down through generations in many families.",
			"In the US, pizza is often featured in holiday celebrations, including Super Bowl parties.",
			"Many pizzerias offer unique twists on classic recipes, creating signature dishes.",
			"In some cultures, pizza is enjoyed as a breakfast food, topped with eggs and vegetables.",
			"The rise of food delivery services has made it easier than ever to enjoy pizza at home.",
			"Many pizzerias have embraced technology by offering online ordering and contactless delivery.",
			"In some regions, pizza is often topped with unexpected ingredients like fruit or nuts.",
			"The concept of 'pizza night' has become a beloved tradition in many households.",
			"In some cultures, pizza is a favorite food among children and adults alike.",
			"Many pizzerias feature seasonal specials that highlight local produce and flavors.",
			"In Italy, pizza is often enjoyed as a casual meal with friends and family.",
			"The 'New York-style' pizza is known for its large, foldable slices and thin crust.",
			"In some areas, pizza is often served at picnics and outdoor gatherings.",
			"Many pizzerias have adopted unique themes or concepts to set themselves apart from competitors.",
			"In some places, pizza is a popular food choice for late-night munchies.",
			"The tradition of pizza-making continues to evolve with new trends and innovations."
	};

	private static final String[] pizzaJokes = {
			"Why did the pizza maker go broke? He just couldn’t make enough dough!",
			"What type of person doesn’t like pizza? A weir-dough!",
			"Why did the mushroom get invited to every pizza party? Because he was a fungi!",
			"What did the pizza say to the delivery guy? 'You’ve got a pizza my heart!'",
			"Why did the slice of pizza break up with the other slice? It found someone who was a little more saucy!",
			"What’s a pizza’s favorite movie? 'The Slice of Life!'",
			"How do you fix a broken pizza? With tomato paste!",
			"Why don’t pizzas like playing hide and seek? Because they always get caught in the crust!",
			"What did the pizza say to its crust? 'You complete me!'",
			"Why did the pizza apply for a job? It wanted to make some extra cheese!",
			"What’s a pizza’s favorite type of music? Anything with a good ‘beat’!",
			"Why was the pizza so good at golf? It always made the perfect slice!",
			"What do you call a pizza with no toppings? A plain cheese experience!",
			"Why did the pizza go to school? To get a little smarter about toppings!",
			"What do you call cheese that isn't yours? Nacho cheese! But I guess it could also be pizza cheese!",
			"How do pizzas communicate? They use 'crust' language!",
			"What did the pepperoni say to the cheese? 'You’re looking gouda today!'",
			"Why was the pizza so bad at tennis? Because it couldn’t handle the pressure!",
			"What did the pizza say to the oven? 'You really heat things up!'",
			"How do you make a pizza laugh? You tell it a cheesy joke!",
			"Why do pizzas never get lost? They always find their way home!",
			"What kind of person likes to eat pizza? A little cheesy!",
			"Why did the pizza cross the road? To get to the other slice!",
			"What’s a pizza’s favorite game? Slice and dice!",
			"Why did the pizza break up with the salad? It found a better topping!",
			"How does a pizza introduce itself? 'I’m the big cheese!'",
			"What do you call a pizza that tells jokes? A funny pie!",
			"What did the pizza say when it asked for a raise? 'I deserve a little extra dough!'",
			"Why did the pizza get a promotion? It was a cut above the rest!",
			"How do pizzas get around town? They take the crustway!",
			"What did the pizza say during the argument? 'You’re really crusting my nerves!'",
			"Why are pizzas so good at baseball? They know how to throw a perfect pitch!",
			"What do you get when you cross a pizza with a joke? A funny slice!",
			"Why did the pizza start a band? It had the perfect toppings for a hit!",
			"What did one pizza say to the other at the party? 'Let’s get this crust started!'",
			"Why do pizzas never play poker? They’re afraid of getting dealt a bad hand!",
			"What’s a pizza’s favorite exercise? Dough-robics!",
			"Why do pizzas make terrible detectives? They always leave too much evidence behind!",
			"What do you call a pizza that can play music? A tuneful pie!",
			"Why do pizzas never tell secrets? Because they might get spread too thin!",
			"What did the pizza say when it won the lottery? 'I’m going to buy a pizza palace!'",
			"Why are pizzas so bad at relationships? They always want to be the top crust!",
			"What did the dough say to the toppings? 'You’re on top of the world!'",
			"Why did the pizza refuse to play cards? It didn’t want to get 'sliced'!",
			"How does a pizza express its feelings? It says, 'I’m feeling saucy!'",
			"Why was the pizza always invited to parties? It knew how to bring the flavor!",
			"What do you call a pizza that can dance? A jig-saw pizza!",
			"What did the crust say to the pizza? 'I’m on a roll!'",
			"Why did the pizza chef get arrested? He was caught in a dough deal!",
			"What’s a pizza’s favorite vacation destination? The Isle of Cheese!",
			"How do you organize a pizza party? You 'slice' it up into pieces!",
			"Why did the pizza feel embarrassed? It didn’t have enough toppings to show off!",
			"What do you call a pizza that tells tall tales? A legendary pie!",
			"Why do pizzas never share their secrets? They’re afraid of being 'topped'!",
			"What’s a pizza’s favorite flower? A crust-anthemum!",
			"Why was the pizza so popular? It had a great personality and a lot of zest!",
			"What did the pizza say after a long day? 'I need to take a dough break!'",
			"Why did the pizza join the gym? To get some extra dough!",
			"What do you call a pizza that wins a race? A fast 'slice'!",
			"Why do pizzas never get lonely? They always have a 'topping' to talk to!",
			"What did the pizza say to the wine? 'You make everything better!'",
			"Why did the pizza get in trouble at school? It was caught loafing around!",
			"What do you call a pizza with a sunburn? A hot slice!",
			"How does a pizza apologize? It says, 'I’m sorry, I didn’t mean to crust you!'",
			"Why did the pizza stay home from work? It was feeling a little crusty!",
			"What’s a pizza’s favorite type of poetry? 'Sonnet with sauce!'",
			"What did the pizza say when it saw a beautiful pie? 'You’re the topping of my dreams!'",
			"Why did the pizza refuse to play the guitar? It was afraid of getting strung along!",
			"What’s a pizza’s favorite type of math? 'Pi'!",
			"Why do pizzas make terrible comedians? They always 'cheese' it up too much!",
			"What do you call a pizza with a bad attitude? A saucy slice!",
			"Why did the pizza chef always win at chess? He knew how to play his pieces right!",
			"How does a pizza express its creativity? By topping it off with flair!",
			"What do you call a pizza that can write? A literary slice!",
			"Why was the pizza so calm? It knew how to take things with a grain of salt!",
			"What’s a pizza’s favorite dance? The pepperoni twist!",
			"What do you call a pizza with an attitude problem? A sassy slice!",
			"How did the pizza feel after the workout? A little doughy!",
			"What did the pizza say to the fridge? 'You keep me cool!'",
			"Why do pizzas never get lost? They always know their way around the 'cheesy' streets!",
			"What do you call a pizza that can do magic? A crust-illusionist!",
			"What did the pizza say to its chef? 'You really know how to make me rise!'",
			"Why do pizzas love the beach? They enjoy soaking up the sun on their crusts!"
	};



	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		registerCommands();
	}

	private void registerCommands() {
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
					.then(literal("ascii1")
							.executes(context -> {
								ServerPlayerEntity player = context.getSource().getPlayer();

								if (player != null) {
									player.sendMessage(Text.literal("" +
											"                                                      ▓▓▓▓  ▓▓▓▓▓▓▓▓▓▓                                          \n" +
											"                                              ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒                                        \n" +
											"                                      ▒▒▒▒▓▓▒▒▓▓▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒▒▒▒▒▒▒▓▓                                        \n" +
											"                                ▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▓▓                                        \n" +
											"                            ▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒▒▒▓▓▓▓                                        \n" +
											"                        ▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓░░░░                                      \n" +
											"                      ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒░░░░░░░░░░                                    \n" +
											"                    ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒▒▒▓▓▓▓▒▒░░░░░░░░░░▓▓▓▓▓▓▓▓                                  \n" +
											"                ▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░██▓▓▓▓▓▓▓▓▓▓                                \n" +
											"              ▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓░░░░                            \n" +
											"            ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒░░░░░░░░░░░░░░░░░░░░░░  ▓▓▓▓▓▓▓▓  ░░░░                            \n" +
											"          ▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒▓▓▓▓▒▒░░░░░░░░░░░░▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░      ░░░░                          \n" +
											"          ▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░          ░░░░                        \n" +
											"      ▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒░░░░░░░░░░░░░░██▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░▓▓▓▓▓▓▓▓  ░░░░░░                      \n" +
											"    ▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒░░░░░░░░░░░░░░░░░░██▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░██▓▓▓▓▓▓▓▓▓▓  ░░░░░░                    \n" +
											"  ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒▓▓▓▓▒▒░░░░░░░░░░░░░░░░░░░░░░████▓▓▓▓▓▓▓▓░░░░░░██▓▓▓▓▓▓▓▓▓▓▓▓▓▓    ░░                    \n" +
											"  ▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓    ░░░░░░                \n" +
											"  ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒░░░░░░░░▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░████▓▓▓▓▓▓▓▓░░    ░░░░░░░░              \n" +
											"  ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░      ░░░░              \n" +
											"  ▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░      ░░░░            \n" +
											"  ▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒░░░░░░░░██▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░██▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░      ░░░░          \n" +
											"    ▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒░░░░░░░░░░░░████▓▓▓▓▓▓▓▓░░░░░░░░██▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░    ░░░░░░        \n" +
											"      ▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░░░░░░░░░░░░░████░░░░░░░░░░░░██▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░▓▓▓▓▓▓▓▓░░░░░░░░    ░░░░        \n" +
											"          ▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░████▓▓▓▓▓▓▓▓░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░    ░░░░░░      \n" +
											"              ▒▒▒▒▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░████░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░    ░░░░░░      \n" +
											"                  ▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░    ░░░░░░    \n" +
											"                    ▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░  ▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░████▓▓▓▓▓▓▓▓░░░░░░░░    ░░░░░░    \n" +
											"                            ▒▒▒▒▒▒░░░░  ▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████░░░░░░░░░░░░    ░░░░░░    \n" +
											"                                  ▒▒░░░░▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░  ░░░░░░░░  \n" +
											"                                  ▒▒░░    ▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░    ░░░░░░  \n" +
											"                                    ░░░░      ░░░░░░  ▒▒▒▒░░░░░░░░░░░░░░░░▓▓▒▒░░░░░░░░░░░░░░░░░░░░░░░░  ░░░░░░  \n" +
											"                                    ░░░░░░      ░░░░  ░░▒▒░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░    ░░░░  \n" +
											"                                      ░░░░      ░░░░░░░░  ▒▒░░░░░░  ░░░░▒▒▒▒▒▒▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░      ░░  \n" +
											"                                        ░░░░    ░░░░  ░░  ░░░░░░░░  ░░░░▓▓▒▒▒▒▒▒▒▒▒▒▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░  \n" +
											"                                          ░░░░  ░░░░░░░░    ░░░░░░  ░░▒▒        ▒▒▒▒▒▒▒▒▒▒▓▓░░░░░░░░░░░░░░  ░░  \n" +
											"                                            ░░░░░░░░▒▒      ░░░░░░░░░░░░              ▒▒▒▒▒▒░░░░  ▒▒▓▓▒▒░░  ░░  \n" +
											"                                              ░░░░░░░░      ░░░░░░░░░░░░              ░░    ░░░░░░░░▒▒░░░░  ░░  \n" +
											"                                              ░░░░░░▒▒        ░░░░░░░░░░                    ░░░░░░░░▒▒▒▒░░  ░░  \n" +
											"                                                ▒▒░░░░        ░░░░  ▒▒                        ░░░░░░▒▒▒▒░░  ░░  \n" +
											"                                                ░░░░░░        ░░░░░░░░                        ░░░░▒▒▒▒▒▒░░  ░░  \n" +
											"                                                  ░░░░        ░░░░                            ▒▒  ░░    ░░  ░░  \n" +
											"                                                  ░░░░        ░░░░                            ░░  ░░    ░░  ░░  \n" +
											"                                                  ░░          ▒▒░░                            ░░  ░░    ▒▒░░░░  \n" +
											"                                                  ░░            ░░                            ░░  ░░      ░░░░  \n" +
											"                                                                ░░                            ░░░░░░      ░░░░  \n" +
											"                                                                ░░                              ░░░░      ░░░░  \n" +
											"                                                                ░░                              ░░░░      ░░░░  \n" +
											"                                                                ░░                                        ░░░░  \n" +
											"                                                                ░░                                        ░░░░  \n" +
											"                                                              ░░  ░░                                    ░░░░░░░░\n" +
											"                                                              ░░░░                                      ░░      \n" +
											"                                                                ░░                                    ░░░░      \n" +
											"                                                                                                      ░░░░░░  ░░\n" +
											"                                                                                                        ░░░░░░  \n"), false);
								}

								return 1;
							})
					)
			);

			dispatcher.register(literal("pizza")
					.then(literal("ascii2")
							.executes(context -> {
								ServerPlayerEntity player = context.getSource().getPlayer();

								if (player != null) {
									player.sendMessage(Text.literal("" +
											"                              ▓▓▓▓██▓▓██▓▓                                                      \n" +
											"                            ██░░░░░░░░░░░░██▓▓██                                                \n" +
											"                            ██░░░░░░░░░░░░░░░░░░████                                            \n" +
											"                            ██░░░░░░░░░░░░░░░░░░░░░░▓▓██                                        \n" +
											"                          ██░░░░░░░░░░░░░░░░░░░░░░░░░░░░████                                    \n" +
											"                          ██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓██                                \n" +
											"                          ██▓▓▒▒▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░████                            \n" +
											"                        ██▓▓▒▒▓▓▓▓░░██████░░░░░░░░░░░░░░░░░░░░░░░░░░██                          \n" +
											"                        ██▒▒▒▒▓▓░░░░░░░░░░████████░░░░░░░░░░░░░░░░░░░░██                        \n" +
											"                        ██▒▒▓▓▓▓▓▓▓▓░░░░░░░░▒▒▒▒▒▒██████░░░░░░░░░░░░░░░░████                    \n" +
											"                        ██▓▓▓▓▓▓▓▓▓▓░░░░▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒████░░░░░░░░░░░░░░░░██                  \n" +
											"                      ██▓▓▓▓▓▓▓▓▓▓▓▓░░░░▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒██░░░░░░░░░░░░░░░░██                \n" +
											"                      ██▓▓░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒████░░░░░░░░░░░░░░██              \n" +
											"                      ██░░░░░░▒▒░░▓▓░░▒▒░░▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░░░░░░░░░░░░░██            \n" +
											"                    ██░░░░▒▒▒▒░░░░▓▓▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░░░▒▒▓▓▓▓▓▓▓▓██░░░░░░░░░░░░░░██          \n" +
											"                    ██▒▒▒▒▒▒▒▒░░▓▓▓▓▒▒▒▒▒▒▓▓▓▓▓▓▓▓░░░░▒▒░░▒▒░░▒▒▓▓░░▓▓██░░░░░░░░░░░░░░██        \n" +
											"                  ▓▓▒▒▒▒▒▒▓▓▒▒▒▒▓▓▒▒▒▒░░░░▓▓▓▓▓▓▓▓▓▓░░░░▒▒▒▒▒▒▒▒▓▓▒▒░░▒▒▓▓░░░░░░░░░░░░░░██      \n" +
											"                  ██▒▒▒▒██▒▒▓▓▒▒▒▒▒▒░░░░▓▓▓▓▓▓▓▓▓▓░░░░▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒░░░░████░░░░░░░░░░░░██    \n" +
											"                ██░░▒▒██▓▓██▓▓▒▒░░░░░░██████████████░░▒▒▒▒░░▒▒░░▒▒▒▒▒▒░░░░░░░░████░░░░░░░░░░██  \n" +
											"                ██░░░░██▒▒██▒▒▒▒░░▓▓▓▓▒▒▒▒▒▒▓▓▓▓▓▓██▒▒▓▓▒▒░░▒▒▒▒▒▒▒▒▓▓▒▒▒▒░░░░▒▒▒▒▓▓░░░░░░░░░░▓▓\n" +
											"              ██▒▒░░██▓▓▒▒██▒▒░░▓▓██▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▓▓▓▓▓▓░░▒▒▒▒▓▓▓▓▓▓▓▓▓▓▒▒░░░░▒▒▒▒▓▓░░░░░░░░██\n" +
											"              ██▒▒░░██▓▓▒▒██▒▒██▓▓▒▒▒▒▓▓▓▓▓▓▒▒▒▒██░░▓▓▓▓▓▓░░░░▓▓▓▓▓▓▓▓▒▒▒▒▓▓▓▓▓▓░░▓▓▓▓██░░░░░░██\n" +
											"              ██▒▒░░██▒▒▓▓▒▒██▓▓▒▒▓▓▓▓▓▓▒▒▒▒▒▒▓▓██▓▓▓▓░░░░▒▒▒▒▓▓▓▓▓▓▓▓▓▓▒▒▓▓▓▓░░░░▓▓▓▓██░░░░░░██\n" +
											"            ██░░░░░░██▒▒▓▓▒▒██▓▓▓▓▓▓▓▓▒▒▒▒▒▒▓▓██░░░░░░░░░░░░▓▓░░▓▓▓▓▓▓▒▒▒▒▒▒▓▓░░░░░░██░░░░░░░░██\n" +
											"            ██░░░░░░██▒▒▓▓▓▓██▓▓▓▓▓▓▒▒▒▒▒▒▓▓██▓▓▓▓░░▒▒▒▒▒▒░░▒▒░░▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░██░░░░░░██  \n" +
											"            ██░░░░░░▓▓██▓▓▓▓██▓▓▓▓▒▒▒▒▓▓████▓▓▓▓▓▓▓▓▒▒▓▓▒▒▒▒░░░░░░▓▓▓▓▓▓░░░░░░░░████░░░░░░██    \n" +
											"          ▓▓░░▓▓▓▓░░▓▓▓▓████▓▓██████████▓▓▓▓▒▒▓▓▓▓▓▓▒▒▒▒░░▒▒▒▒░░░░░░░░▒▒▒▒▒▒░░██▒▒▒▒░░████      \n" +
											"          ██▓▓▓▓▓▓▓▓▓▓██▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▓▓▓▓▓▓░░░░░░▒▒▒▒▒▒▒▒░░░░░░██████░░░░████          \n" +
											"        ▓▓▒▒▓▓▓▓▓▓▓▓▓▓██▒▒▓▓▒▒▒▒██▒▒▓▓▒▒▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░▒▒▓▓▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒░░▓▓░░            \n" +
											"        ██▓▓▓▓▓▓▓▓▒▒██▒▒▓▓▒▒▒▒▓▓██░░░░░░▓▓▓▓▓▓▓▓▓▓░░░░▓▓▒▒▒▒▒▒▒▒████▒▒▒▒░░░░░░██                \n" +
											"      ██▓▓▓▓▓▓▓▓▒▒▓▓██▓▓▒▒▒▒▓▓██▒▒▒▒░░▓▓▓▓░░▓▓░░▓▓▓▓▒▒▓▓▓▓▒▒▒▒██░░░░░░░░░░████                  \n" +
											"      ██░░▓▓▓▓▒▒▒▒▓▓██▒▒▒▒████▒▒▒▒▒▒░░▓▓▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒██░░░░░░░░░░██                      \n" +
											"    ██░░▓▓▓▓▓▓▓▓▓▓▓▓██▓▓██░░▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒▒▒░░░░▒▒▒▒░░████▒▒░░░░░░████                        \n" +
											"    ██░░▓▓▓▓▓▓░░▓▓▓▓░░██░░▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░██████░░░░░░██████                            \n" +
											"  ██░░▒▒▓▓▓▓▓▓░░▓▓▓▓░░░░░░▓▓▓▓▓▓▓▓▓▓░░░░██████████░░░░░░██████                                  \n" +
											"  ██▒▒▒▒░░░░░░░░▓▓▒▒▒▒▒▒░░▓▓▓▓▓▓░░░░▓▓▓▓▒▒▒▒▒▒▒▒▒▒░░██▓▓  ░░                                    \n" +
											"  ██▒▒▒▒▓▓░░░░░░░░▒▒▒▒▒▒▒▒▓▓▒▒░░░░██░░░░░░░░░░░░████                                            \n" +
											"  ██░░░░░░░░░░░░▒▒▒▒▓▓▓▓▒▒▒▒░░████░░░░░░░░██████                                                \n" +
											"██░░░░░░▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒██████░░░░░░██████                                                      \n" +
											"██░░░░▒▒▒▒▒▒▓▓▒▒▒▒▒▒████░░░░▒▒░░████                                                            \n" +
											"████░░▒▒▒▒░░████████░░░░░░░░████                                                                \n" +
											"██░░████████░░░░░░░░░░░░████                                                                    \n" +
											"██░░░░░░░░░░░░░░░░░░████                                                                        \n" +
											"  ████░░░░░░░░██████                                                                            \n" +
											"      ▓▓██████                                                                                  \n"), false);
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





		});
	}
}
