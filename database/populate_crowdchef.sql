--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.1
-- Dumped by pg_dump version 9.3.1
-- Started on 2014-03-23 18:46:44

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 2219 (class 0 OID 16759)
-- Dependencies: 170
-- Data for Name: app_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO app_user VALUES (1, 'john', 'john', '2014-03-21 15:47:58.241+01');
INSERT INTO app_user VALUES (2, 'anne', 'anne', '2014-03-22 23:37:02.909+01');
INSERT INTO app_user VALUES (3, 'sarah', 'sarah', '2014-03-22 23:37:57.728+01');


--
-- TOC entry 2258 (class 0 OID 0)
-- Dependencies: 190
-- Name: app_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('app_user_id_seq', 4, true);


--
-- TOC entry 2220 (class 0 OID 16767)
-- Dependencies: 171
-- Data for Name: badge; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2259 (class 0 OID 0)
-- Dependencies: 191
-- Name: badge_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('badge_id_seq', 1, false);


--
-- TOC entry 2221 (class 0 OID 16777)
-- Dependencies: 172
-- Data for Name: ingr_instance; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2226 (class 0 OID 16820)
-- Dependencies: 177
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2222 (class 0 OID 16789)
-- Dependencies: 173
-- Data for Name: ingr_instance_has_location; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2260 (class 0 OID 0)
-- Dependencies: 192
-- Name: ingr_instance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ingr_instance_id_seq', 1, false);


--
-- TOC entry 2227 (class 0 OID 16835)
-- Dependencies: 178
-- Data for Name: recipe; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO recipe VALUES (1, 'Spinach Artichoke Lasagna', 'This recipe, adapted to use the traditional flavors of Classico® Pasta Sauce, was originally submitted by Allrecipes home cook DMCCRACKEN', 'Preheat oven to 350 degrees F (175 degrees C). Spray a 9x13 inch baking dish with cooking spray.

Bring a large pot of lightly salted water to a boil. Add noodles and cook for 8 to 10 minutes or until al dente; drain.

', 'anne', 'http://allrecipes.com/Recipe/Spinach-Artichoke-Lasagna/Detail.aspx?prop24=hn_slide1_Spinach-Artichoke-Lasagna&evt19=1', NULL, NULL, '2014-03-22 23:43:23.163+01', 2);
INSERT INTO recipe VALUES (2, 'Springtime Spaghetti', 'Zucchini and carrots are sauteed in butter, mixed in a creamy sauce, and tossed with spaghetti. This is a great side dish that goes really well with barbeque.', 'Bring a large pot of lightly salted water to a boil, cook spaghetti for 8 to 10 minutes, until al dente, and drain.

Melt the butter in a skillet over medium heat, and saute the zucchini, carrots, and garlic until tender. Stir in the heavy cream, Parmesan cheese, and dill. Cook and stir until thickened. Mix with the cooked spaghetti to serve.', 'john', 'http://allrecipes.com/Recipe/Springtime-Spaghetti/Detail.aspx?event8=1&prop24=SR_Thumb&e11=spaghetti&e8=Quick%20Search&event10=1&e7=Recipe&soid=sr_results_p1i3', NULL, NULL, '2014-03-22 23:46:40.058+01', 1);
INSERT INTO recipe VALUES (3, 'Tuna Pizza', 'Easy to make yummy pizza using a pre-made pizza crust.', 'Preheat an oven to 400 degrees F (200 degrees C).

Spread the softened cream cheese on the pre-baked crust. Sprinkle the tuna and onions over the pizza; then top with shredded mozzarella cheese and optional red pepper flakes. Bake in preheated oven until the cheese has melted and started to brown, about 15 to 20 minutes.', 'maria', 'http://allrecipes.com/Recipe/Tuna-Pizza/Detail.aspx?event8=1&prop24=SR_Thumb&e11=pizza&e8=Quick%20Search&event10=1&e7=Recipe&soid=sr_results_p1i10', NULL, NULL, '2014-03-22 23:48:59.712+01', 3);
INSERT INTO recipe VALUES (4, 'Indonesian Fried Rice', 'I learned how to make this very simple dish by watching the warung owners in Indonesia, but adapted it to my own tastes.', 'Bring the rice and water to boil in a pot. Cover, reduce heat to low, and simmer 20 minutes.

Heat the oil in a wok, and cook the onion, garlic, and green chile until tender. Mix in the carrot and celery. Stir in the rice, and mix in kecap manis, tomato sauce, and soy sauce. Continue cooking about 1 minute, until heated through. Transfer to bowls, and garnish with cucumber slices.

Place eggs in the wok, and cook until set. Place in the bowls over the rice and vegetables.', 'anne', 'http://allrecipes.com/Recipe/Indonesian-Fried-Rice/Detail.aspx?event8=1&prop24=SR_Thumb&e11=indonesian%20fried%20rice&e8=Quick%20Search&event10=1&e7=Recipe&soid=sr_results_p1i1', NULL, NULL, '2014-03-22 23:53:00.78+01', 2);
INSERT INTO recipe VALUES (5, 'Mushroom Risotto', 'Delicious mushroom risotto made with vegetable broth, cream, and a variety of fresh vegetables. Serve as a side dish or filling main course', 'Heat olive oil in a large skillet over medium-high heat. Saute the onion and garlic in the olive oil until onion is tender and garlic is lightly browned. Remove garlic, and stir in the parsley, celery, salt, and pepper. Cook until celery is tender, then add the mushrooms. Reduce heat to low, and continue cooking until the mushrooms are soft.

Pour the milk and cream into the skillet, and stir in the rice. Heat to a simmer. Stir the vegetable stock into the rice one cup at a time, until it is absorbed.', 'john', 'http://allrecipes.com/Recipe/Mushroom-Risotto/Detail.aspx?event8=1&prop24=SR_Thumb&e11=risotto&e8=Quick%20Search&event10=1&e7=%2fhelp%2ffeedback.aspx&soid=sr_results_p1i2', NULL, NULL, '2014-03-22 23:56:06.866+01', 1);
INSERT INTO recipe VALUES (6, 'Mediterranean Pasta', 'Chicken breast chunks flavored with bacon, artichoke hearts and herbs in a tomato sauce all over a steaming bowl of linguine.', 'Bring a large pot of lightly salted water to a boil. Add linguine and cook for 8 to 10 minutes or until al dente; drain.

Place bacon in a large, deep skillet. Cook over medium-high heat until evenly brown. Drain, crumble and set aside.', 'maria', 'http://allrecipes.com/Recipe/Mediterranean-Pasta/Detail.aspx?event8=1&prop24=SR_Thumb&e11=pasta&e8=Quick%20Search&event10=1&e7=Recipe&soid=sr_results_p1i6', NULL, NULL, '2014-03-23 00:01:19.997+01', 3);
INSERT INTO recipe VALUES (7, 'Mie Goreng - Indonesian Fried Noodles', 'This tasty noodle dish is the same one my mom used to make for me when I was growing up. It''s definitely comfort food. You can alter it with adding your favorite meats and veggies', 'Bring a pan of water to a boil, and cook the ramen until tender, about 3 minutes. Plunge the noodles into cold water to stop the cooking, drain in a colander set in the sink, and drizzle the noodles with 1 tablespoon of vegetable oil. Set aside.

', 'anne', 'http://allrecipes.com/Recipe/Mie-Goreng---Indonesian-Fried-Noodles/Detail.aspx?event8=1&prop24=SR_Thumb&e11=mie%20goreng&e8=Quick%20Search&event10=1&e7=Recipe&soid=sr_results_p1i1', NULL, NULL, '2014-03-23 00:03:45.812+01', 2);
INSERT INTO recipe VALUES (8, 'Marshmallow Cake', 'This is a very light version of a cheesecake that I learned from a friend in Japan. A perfect recipe for people looking for something sweet to top off a meal', 'In a saucepan on low heat, combine marshmallows and milk. Cook until marshmallows are melted, and mixture starts to boil. Remove from heat.

In a large bowl, beat cream cheese until smooth. Stir in egg yolk. Fold in 1/3 of the melted marshmallow mixture, then quickly fold in remaining marshmallow until no streaks remain. Stir in lemon juice.', 'john', 'http://allrecipes.com/Recipe/Marshmallow-Cake-2/Detail.aspx?event8=1&prop24=SR_Thumb&e11=chess%20cake&e8=Quick%20Search&event10=1&e7=Recipe&soid=sr_results_p1i9', NULL, NULL, '2014-03-23 00:05:44.692+01', 1);
INSERT INTO recipe VALUES (9, 'Iskender Kebab', 'I tried this dish at a Turkish restaurant in London and was amazed by its delicious taste! I later found out that this dish was named after Alexander the Great, whom the Persians called ''Iskender.'' Apparently it was his favourite food', 'Preheat oven to 350 degrees F (175 degrees C). Arrange pita bread on a baking sheet, and lightly toast in the oven. Cut pita bread into bite-size pieces, and keep warm.

Heat the olive oil in a skillet over medium heat. Stir in the chicken, onion, and garlic, and cook until chicken juices run clear. Mix in tomato puree. Season with cumin, salt, and pepper. Continue cooking 10 minutes.', 'maria', 'http://allrecipes.com/Recipe/Iskender-Kebab/Detail.aspx?event8=1&prop24=SR_Thumb&e11=kebab&e8=Quick%20Search&event10=1&e7=Recipe&soid=sr_results_p1i4', NULL, NULL, '2014-03-23 00:07:41.062+01', 3);
INSERT INTO recipe VALUES (10, 'Salmon with Tomatoes', 'This is a delicious and quick lunch or dinner meal. Serve over rice, pasta, polenta, or eat it right off your plate. So good you''ll want to lick the plate clean!', 'In a medium saucepan, bring the rice and water to a boil. Reduce heat to low, cover, and cook 20 minutes.

Heat the garlic oil in a skillet over medium heat. Season the salmon with salt, pepper, dill, and paprika, and cook in the hot oil 1 to 2 minutes on each side, until tender enough to break apart. Break salmon into cubes with a spatula or fork. Mix in the tomatoes, garlic, and lemon juice. Continue cooking until salmon is easily flaked with a fork.', 'anne', 'http://allrecipes.com/Recipe/Salmon-with-Tomatoes/Detail.aspx?event8=1&prop24=SR_Thumb&e11=tomatoe&e8=Quick%20Search&event10=1&e7=Recipe&soid=sr_results_p1i2', NULL, NULL, '2014-03-23 00:09:38.88+01', 2);
INSERT INTO recipe VALUES (12, 'Lamb Ribs with Honey and Wine', 'Combination of Middle Eastern and European dishes. Best if served with steamed vegetables and hot cooked rice', 'To Marinate: Place lamb in a 9x13 inch baking dish. In a small bowl combine the onions, garlic, honey, oil, soy sauce, wine, pepper, salt, lemon juice and cinnamon. Mix well and pour mixture all over lamb. Cover and refrigerate to marinate for 1 hour.

Preheat oven to 400 degrees F (200 degrees C).

Bake in the preheated oven for 70 minutes or until cooked through.', 'anne', NULL, NULL, NULL, '2014-03-23 18:14:11.317+01', 2);
INSERT INTO recipe VALUES (11, 'Classic White Wine Spritzer', 'The simple no-frills classic! For more flavor, you can add some peach schnapps or flavored soda (orange) for a better taste', 'Pour wine into glasses, and top off with club soda. Serve immediately', 'john', NULL, NULL, NULL, '2014-03-23 18:08:36.157+01', 1);


--
-- TOC entry 2224 (class 0 OID 16801)
-- Dependencies: 175
-- Data for Name: ingredient; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ingredient VALUES (1, 10, 'white rice', 'uncooked long grain white rice', 'cup', 1);
INSERT INTO ingredient VALUES (2, 10, 'Parmesan cheese', 'grated Parmesan cheese', 'cup', 1);
INSERT INTO ingredient VALUES (4, 8, 'milk', NULL, 'cup', 1);
INSERT INTO ingredient VALUES (5, 8, 'marshmallow', NULL, 'package', 1);
INSERT INTO ingredient VALUES (6, 7, 'ramen noodle', NULL, 'package', 3);
INSERT INTO ingredient VALUES (7, 7, 'vegetable oil', NULL, 'tablespoon', 1);
INSERT INTO ingredient VALUES (8, 6, 'linguine pasta', NULL, 'package', 1);
INSERT INTO ingredient VALUES (9, 6, 'chicken', 'boneless breast chicken', 'pound', 1);
INSERT INTO ingredient VALUES (10, 5, 'olive oil', NULL, 'tablespoon', 1);
INSERT INTO ingredient VALUES (11, 5, 'mushroom', NULL, 'cup', 1);
INSERT INTO ingredient VALUES (12, 4, 'grain white rice ', NULL, 'cup', 1);
INSERT INTO ingredient VALUES (13, 4, 'onion', 'chopped', 'slice', 1);
INSERT INTO ingredient VALUES (14, 3, 'cream cheese', NULL, 'package', 1);
INSERT INTO ingredient VALUES (15, 3, 'pizza crust', NULL, 'package', 1);
INSERT INTO ingredient VALUES (16, 2, 'spaghetti', NULL, 'ounce', 8);
INSERT INTO ingredient VALUES (17, 2, 'butter', NULL, 'tablespoon', 2);
INSERT INTO ingredient VALUES (18, 1, 'lasagna noodle', NULL, 'uncooked', 9);
INSERT INTO ingredient VALUES (19, 1, 'chopped spinach', NULL, 'package', 1);
INSERT INTO ingredient VALUES (3, 9, 'bread', NULL, 'round', 4);
INSERT INTO ingredient VALUES (20, 9, 'garlic', NULL, 'clove', 1);
INSERT INTO ingredient VALUES (21, 11, 'club soda', NULL, 'cup', 1);
INSERT INTO ingredient VALUES (22, 11, 'white wine', NULL, 'cup', 1);
INSERT INTO ingredient VALUES (23, 12, 'lamb ribs', NULL, 'pound', 3);
INSERT INTO ingredient VALUES (24, 12, 'honey', NULL, 'teaspoon', 3);


--
-- TOC entry 2225 (class 0 OID 16814)
-- Dependencies: 176
-- Data for Name: ingredient_has_ingr_instance; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2223 (class 0 OID 16795)
-- Dependencies: 174
-- Data for Name: ingr_instance_ratings; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2261 (class 0 OID 0)
-- Dependencies: 193
-- Name: ingr_instance_ratings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ingr_instance_ratings_id_seq', 1, false);


--
-- TOC entry 2262 (class 0 OID 0)
-- Dependencies: 194
-- Name: ingredient_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ingredient_id_seq', 25, true);


--
-- TOC entry 2263 (class 0 OID 0)
-- Dependencies: 195
-- Name: location_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('location_id_seq', 1, false);


--
-- TOC entry 2264 (class 0 OID 0)
-- Dependencies: 196
-- Name: recipe_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('recipe_id_seq', 13, true);


--
-- TOC entry 2228 (class 0 OID 16850)
-- Dependencies: 179
-- Data for Name: recipe_rating; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO recipe_rating VALUES (1, 1, 5, 3);
INSERT INTO recipe_rating VALUES (2, 2, 5, 2);
INSERT INTO recipe_rating VALUES (3, 3, 4, 1);
INSERT INTO recipe_rating VALUES (4, 4, 3, 2);
INSERT INTO recipe_rating VALUES (5, 5, 2, 3);
INSERT INTO recipe_rating VALUES (6, 6, 1, 2);
INSERT INTO recipe_rating VALUES (7, 7, 2, 3);
INSERT INTO recipe_rating VALUES (8, 8, 4, 2);
INSERT INTO recipe_rating VALUES (9, 9, 3, 3);
INSERT INTO recipe_rating VALUES (10, 10, 5, 2);
INSERT INTO recipe_rating VALUES (11, 11, 2, 1);
INSERT INTO recipe_rating VALUES (12, 12, 4, 2);


--
-- TOC entry 2265 (class 0 OID 0)
-- Dependencies: 197
-- Name: recipe_rating_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('recipe_rating_id_seq', 13, true);


--
-- TOC entry 2229 (class 0 OID 16855)
-- Dependencies: 180
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO reviews VALUES (1, 12, 'I modified this recipe. I used half a red onion instead of 2 and used 1/4 cup Parrot Bay Coconut Rum ', '2014-03-23 18:18:54.643+01', 1);
INSERT INTO reviews VALUES (2, 12, 'This is by far the best way to prepare lamb ribs/chops. I have made this recipe several times and it is perfect', '2014-03-23 18:19:23.286+01', 2);
INSERT INTO reviews VALUES (3, 11, 'Thank you for the recipe.. I didn''t have club soda so I used Sierra Mist and added a splash of triple sec.. It was DELICIOUS!', '2014-03-23 18:22:49.029+01', 3);
INSERT INTO reviews VALUES (4, 11, 'Excellent. I agree with the submitter, one can customize this a variety of ways! Thank you.', '2014-03-23 18:23:05.932+01', 1);
INSERT INTO reviews VALUES (5, 10, 'An excellent, fresh-tasting way to eat salmon. Since I didn''t have any garlic oil on hand, I used olive oil and added… ', '2014-03-23 18:24:04.315+01', 2);
INSERT INTO reviews VALUES (6, 10, 'This recipe is worth 10 stars! I made this for my family and the kids asked me to fix it again the very…', '2014-03-23 18:24:24.826+01', 3);
INSERT INTO reviews VALUES (7, 9, 'As someone from the plae of the original recepie of Iskender Kebap, I liked the idea how it is twisted for making it at home', '2014-03-23 18:25:23.603+01', 2);
INSERT INTO reviews VALUES (8, 9, 'You can also try this recipe with very thinly cut beef slices or minced meat. For the best taste, real butter could be used', '2014-03-23 18:25:48.831+01', 1);
INSERT INTO reviews VALUES (10, 8, 'Great Pie! It was very simple to make. I did not have any cream in the house so I subsituted cool whip ', '2014-03-23 18:26:52.545+01', 2);
INSERT INTO reviews VALUES (11, 8, 'I have been to Japan but never had the chance to try Japanese cheesecake, however this recipe is really good', '2014-03-23 18:27:10.301+01', 1);


--
-- TOC entry 2266 (class 0 OID 0)
-- Dependencies: 198
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('reviews_id_seq', 11, true);


--
-- TOC entry 2230 (class 0 OID 16865)
-- Dependencies: 181
-- Data for Name: taste_category; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2267 (class 0 OID 0)
-- Dependencies: 199
-- Name: taste_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('taste_category_id_seq', 1, false);


--
-- TOC entry 2231 (class 0 OID 16872)
-- Dependencies: 182
-- Data for Name: taste_score; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2268 (class 0 OID 0)
-- Dependencies: 200
-- Name: taste_score_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('taste_score_id_seq', 1, false);


--
-- TOC entry 2232 (class 0 OID 16878)
-- Dependencies: 183
-- Data for Name: user_has_badge; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2233 (class 0 OID 16884)
-- Dependencies: 184
-- Data for Name: user_info; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2269 (class 0 OID 0)
-- Dependencies: 201
-- Name: user_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_info_id_seq', 1, false);


--
-- TOC entry 2234 (class 0 OID 16898)
-- Dependencies: 185
-- Data for Name: wine; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO wine VALUES (6, 11, 'Classic White Wine Spritzer', 'The simple no-frills classic! For more flavor, you can add some peach schnapps or flavored soda (orange) for a better taste.', '2014-03-23 18:08:53.899+01', 1);
INSERT INTO wine VALUES (7, 12, 'White Wine', NULL, '2014-03-23 18:14:44.146+01', 2);


--
-- TOC entry 2236 (class 0 OID 16917)
-- Dependencies: 187
-- Data for Name: wine_instance; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2235 (class 0 OID 16911)
-- Dependencies: 186
-- Data for Name: wine_has_wine_instance; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2270 (class 0 OID 0)
-- Dependencies: 202
-- Name: wine_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('wine_id_seq', 7, true);


--
-- TOC entry 2237 (class 0 OID 16929)
-- Dependencies: 188
-- Data for Name: wine_instance_has_location; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2271 (class 0 OID 0)
-- Dependencies: 203
-- Name: wine_instance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('wine_instance_id_seq', 1, false);


--
-- TOC entry 2238 (class 0 OID 16935)
-- Dependencies: 189
-- Data for Name: wine_instance_ratings; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2272 (class 0 OID 0)
-- Dependencies: 204
-- Name: wine_instance_ratings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('wine_instance_ratings_id_seq', 1, false);


-- Completed on 2014-03-23 18:46:47

--
-- PostgreSQL database dump complete
--

