--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.1
-- Dumped by pg_dump version 9.3.1
-- Started on 2014-03-21 16:20:00 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 2485 (class 0 OID 22770)
-- Dependencies: 170
-- Data for Name: app_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO app_user VALUES (10, 'john', 'john', '2014-03-19 13:42:13.977532+01');
INSERT INTO app_user VALUES (11, 'lucy', 'lucy', '2014-03-21 15:22:52.648454+01');


--
-- TOC entry 2526 (class 0 OID 0)
-- Dependencies: 190
-- Name: app_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('app_user_id_seq', 11, true);


--
-- TOC entry 2486 (class 0 OID 22778)
-- Dependencies: 171
-- Data for Name: badge; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2527 (class 0 OID 0)
-- Dependencies: 191
-- Name: badge_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('badge_id_seq', 1, false);


--
-- TOC entry 2487 (class 0 OID 22788)
-- Dependencies: 172
-- Data for Name: ingr_instance; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2488 (class 0 OID 22800)
-- Dependencies: 173
-- Data for Name: ingr_instance_has_location; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2528 (class 0 OID 0)
-- Dependencies: 192
-- Name: ingr_instance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ingr_instance_id_seq', 1, false);


--
-- TOC entry 2489 (class 0 OID 22806)
-- Dependencies: 174
-- Data for Name: ingr_instance_ratings; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2529 (class 0 OID 0)
-- Dependencies: 193
-- Name: ingr_instance_ratings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ingr_instance_ratings_id_seq', 1, false);


--
-- TOC entry 2490 (class 0 OID 22812)
-- Dependencies: 175
-- Data for Name: ingredient; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2491 (class 0 OID 22825)
-- Dependencies: 176
-- Data for Name: ingredient_has_ingr_instance; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2530 (class 0 OID 0)
-- Dependencies: 194
-- Name: ingredient_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ingredient_id_seq', 41, true);


--
-- TOC entry 2492 (class 0 OID 22831)
-- Dependencies: 177
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2531 (class 0 OID 0)
-- Dependencies: 195
-- Name: location_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('location_id_seq', 1, false);


--
-- TOC entry 2493 (class 0 OID 22846)
-- Dependencies: 178
-- Data for Name: recipe; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO recipe VALUES (8, 'Perfect recipe', 'Awesome recipe for a recipe', 'Stir recipe until becomes recipe', 'perfect,inception', NULL, NULL, NULL, '2014-03-19 13:42:14.027948+01', 10);
INSERT INTO recipe VALUES (11, 'Regular donner', 'Alleen vlees', 'Just ask the Turkish guy', 'donner,kebap,vlees', NULL, NULL, NULL, '2014-03-19 19:11:33.508081+01', 10);


--
-- TOC entry 2532 (class 0 OID 0)
-- Dependencies: 196
-- Name: recipe_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('recipe_id_seq', 11, true);


--
-- TOC entry 2494 (class 0 OID 22861)
-- Dependencies: 179
-- Data for Name: recipe_rating; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO recipe_rating VALUES (3, 11, 2, 10);
INSERT INTO recipe_rating VALUES (4, 11, 3, 11);


--
-- TOC entry 2533 (class 0 OID 0)
-- Dependencies: 197
-- Name: recipe_rating_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('recipe_rating_id_seq', 4, true);


--
-- TOC entry 2495 (class 0 OID 22866)
-- Dependencies: 180
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2534 (class 0 OID 0)
-- Dependencies: 198
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('reviews_id_seq', 1, false);


--
-- TOC entry 2496 (class 0 OID 22876)
-- Dependencies: 181
-- Data for Name: taste_category; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2535 (class 0 OID 0)
-- Dependencies: 199
-- Name: taste_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('taste_category_id_seq', 1, false);


--
-- TOC entry 2497 (class 0 OID 22883)
-- Dependencies: 182
-- Data for Name: taste_score; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2536 (class 0 OID 0)
-- Dependencies: 200
-- Name: taste_score_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('taste_score_id_seq', 1, false);


--
-- TOC entry 2498 (class 0 OID 22889)
-- Dependencies: 183
-- Data for Name: user_has_badge; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2499 (class 0 OID 22895)
-- Dependencies: 184
-- Data for Name: user_info; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2537 (class 0 OID 0)
-- Dependencies: 201
-- Name: user_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_info_id_seq', 1, false);


--
-- TOC entry 2500 (class 0 OID 22909)
-- Dependencies: 185
-- Data for Name: wine; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2501 (class 0 OID 22922)
-- Dependencies: 186
-- Data for Name: wine_has_wine_instance; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2538 (class 0 OID 0)
-- Dependencies: 202
-- Name: wine_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('wine_id_seq', 1, false);


--
-- TOC entry 2502 (class 0 OID 22928)
-- Dependencies: 187
-- Data for Name: wine_instance; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2503 (class 0 OID 22940)
-- Dependencies: 188
-- Data for Name: wine_instance_has_location; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2539 (class 0 OID 0)
-- Dependencies: 203
-- Name: wine_instance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('wine_instance_id_seq', 1, false);


--
-- TOC entry 2504 (class 0 OID 22946)
-- Dependencies: 189
-- Data for Name: wine_instance_ratings; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2540 (class 0 OID 0)
-- Dependencies: 204
-- Name: wine_instance_ratings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('wine_instance_ratings_id_seq', 1, false);


-- Completed on 2014-03-21 16:20:00 CET

--
-- PostgreSQL database dump complete
--

