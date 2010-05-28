--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = core, pg_catalog;

--
-- Data for Name: keyword_category; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY keyword_category (keyword_category_id, name_concept_id, description, creation_date, created_by, last_modification_date, last_modification_by) FROM stdin;
1	36	Insectos	\N	\N	\N	\N
2	37	Plantas	\N	\N	\N	\N
3	38	Paisajes: Paisajes de distintos lugares de Costa Rica	\N	\N	\N	\N
4	39	INBio: Fotos generales de procesos de la instituciÃ³n	\N	\N	\N	\N
5	40	Moluscos	\N	\N	\N	\N
6	41	Vertebrados	\N	\N	\N	\N
7	42	Nematodos	\N	\N	\N	\N
8	43	Liquenes	\N	\N	\N	\N
9	44	Tipo de bosques	\N	\N	\N	\N
10	45	Hongos	\N	\N	\N	\N
11	46	Microhongos	\N	\N	\N	\N
12	47	Biodiccionarios	\N	\N	\N	\N
\.


--
-- Data for Name: keyword; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY keyword (keyword_id, keyword_category_id, name_concept_id, creation_date, created_by, last_modification_date, last_modification_by) FROM stdin;
38	1	49	\N	\N	\N	\N
8	1	48	\N	\N	\N	\N
39	1	50	\N	\N	\N	\N
43	1	51	\N	\N	\N	\N
55	1	52	\N	\N	\N	\N
275	1	53	\N	\N	\N	\N
293	1	54	\N	\N	\N	\N
316	1	55	\N	\N	\N	\N
346	1	56	\N	\N	\N	\N
347	1	57	\N	\N	\N	\N
348	1	58	\N	\N	\N	\N
349	1	59	\N	\N	\N	\N
350	1	60	\N	\N	\N	\N
405	1	61	\N	\N	\N	\N
406	1	62	\N	\N	\N	\N
407	1	63	\N	\N	\N	\N
408	1	64	\N	\N	\N	\N
410	1	65	\N	\N	\N	\N
411	1	66	\N	\N	\N	\N
413	1	67	\N	\N	\N	\N
414	1	68	\N	\N	\N	\N
415	1	69	\N	\N	\N	\N
416	1	70	\N	\N	\N	\N
418	1	71	\N	\N	\N	\N
420	1	72	\N	\N	\N	\N
423	1	73	\N	\N	\N	\N
424	1	74	\N	\N	\N	\N
425	1	75	\N	\N	\N	\N
426	1	76	\N	\N	\N	\N
427	1	77	\N	\N	\N	\N
428	1	78	\N	\N	\N	\N
430	1	79	\N	\N	\N	\N
432	1	80	\N	\N	\N	\N
434	1	81	\N	\N	\N	\N
435	1	82	\N	\N	\N	\N
436	1	83	\N	\N	\N	\N
437	1	84	\N	\N	\N	\N
438	1	85	\N	\N	\N	\N
439	1	86	\N	\N	\N	\N
440	1	87	\N	\N	\N	\N
441	1	88	\N	\N	\N	\N
442	1	89	\N	\N	\N	\N
443	1	90	\N	\N	\N	\N
444	1	91	\N	\N	\N	\N
445	1	92	\N	\N	\N	\N
447	1	93	\N	\N	\N	\N
448	1	94	\N	\N	\N	\N
449	1	95	\N	\N	\N	\N
450	1	96	\N	\N	\N	\N
451	1	97	\N	\N	\N	\N
452	1	98	\N	\N	\N	\N
453	1	99	\N	\N	\N	\N
454	1	100	\N	\N	\N	\N
455	1	101	\N	\N	\N	\N
456	1	102	\N	\N	\N	\N
457	1	103	\N	\N	\N	\N
458	1	104	\N	\N	\N	\N
459	1	105	\N	\N	\N	\N
460	1	106	\N	\N	\N	\N
461	1	107	\N	\N	\N	\N
462	1	108	\N	\N	\N	\N
463	1	109	\N	\N	\N	\N
464	1	110	\N	\N	\N	\N
465	1	111	\N	\N	\N	\N
466	1	112	\N	\N	\N	\N
467	1	113	\N	\N	\N	\N
468	1	114	\N	\N	\N	\N
469	1	115	\N	\N	\N	\N
470	1	116	\N	\N	\N	\N
471	1	117	\N	\N	\N	\N
472	1	118	\N	\N	\N	\N
473	1	119	\N	\N	\N	\N
474	1	120	\N	\N	\N	\N
475	1	121	\N	\N	\N	\N
476	1	122	\N	\N	\N	\N
477	1	123	\N	\N	\N	\N
478	1	124	\N	\N	\N	\N
479	1	125	\N	\N	\N	\N
480	1	126	\N	\N	\N	\N
481	1	127	\N	\N	\N	\N
486	1	128	\N	\N	\N	\N
488	1	129	\N	\N	\N	\N
489	1	130	\N	\N	\N	\N
491	1	131	\N	\N	\N	\N
495	1	132	\N	\N	\N	\N
496	1	133	\N	\N	\N	\N
497	1	134	\N	\N	\N	\N
504	1	135	\N	\N	\N	\N
501	1	136	\N	\N	\N	\N
515	1	137	\N	\N	\N	\N
517	1	139	\N	\N	\N	\N
519	1	140	\N	\N	\N	\N
521	1	141	\N	\N	\N	\N
535	1	142	\N	\N	\N	\N
536	1	143	\N	\N	\N	\N
537	1	144	\N	\N	\N	\N
556	1	145	\N	\N	\N	\N
557	1	146	\N	\N	\N	\N
558	1	147	\N	\N	\N	\N
559	1	148	\N	\N	\N	\N
560	1	149	\N	\N	\N	\N
6	2	150	\N	\N	\N	\N
18	2	151	\N	\N	\N	\N
19	2	152	\N	\N	\N	\N
21	2	153	\N	\N	\N	\N
54	2	154	\N	\N	\N	\N
341	2	155	\N	\N	\N	\N
352	2	156	\N	\N	\N	\N
353	2	157	\N	\N	\N	\N
354	2	158	\N	\N	\N	\N
355	2	159	\N	\N	\N	\N
356	2	160	\N	\N	\N	\N
357	2	161	\N	\N	\N	\N
358	2	162	\N	\N	\N	\N
359	2	163	\N	\N	\N	\N
360	2	164	\N	\N	\N	\N
297	3	165	\N	\N	\N	\N
28	4	166	\N	\N	\N	\N
29	4	167	\N	\N	\N	\N
30	4	168	\N	\N	\N	\N
31	4	169	\N	\N	\N	\N
33	4	170	\N	\N	\N	\N
34	4	171	\N	\N	\N	\N
35	4	172	\N	\N	\N	\N
36	4	173	\N	\N	\N	\N
37	4	174	\N	\N	\N	\N
42	4	175	\N	\N	\N	\N
45	4	177	\N	\N	\N	\N
47	4	178	\N	\N	\N	\N
48	4	179	\N	\N	\N	\N
49	4	180	\N	\N	\N	\N
50	4	181	\N	\N	\N	\N
52	4	182	\N	\N	\N	\N
271	4	183	\N	\N	\N	\N
273	4	184	\N	\N	\N	\N
276	4	185	\N	\N	\N	\N
278	4	186	\N	\N	\N	\N
279	4	187	\N	\N	\N	\N
280	4	188	\N	\N	\N	\N
305	4	189	\N	\N	\N	\N
319	4	190	\N	\N	\N	\N
320	4	191	\N	\N	\N	\N
333	4	192	\N	\N	\N	\N
334	4	193	\N	\N	\N	\N
335	4	194	\N	\N	\N	\N
336	4	195	\N	\N	\N	\N
338	4	196	\N	\N	\N	\N
361	4	197	\N	\N	\N	\N
362	4	198	\N	\N	\N	\N
363	4	199	\N	\N	\N	\N
364	4	200	\N	\N	\N	\N
365	4	201	\N	\N	\N	\N
366	4	202	\N	\N	\N	\N
367	4	203	\N	\N	\N	\N
368	4	204	\N	\N	\N	\N
369	4	205	\N	\N	\N	\N
370	4	206	\N	\N	\N	\N
371	4	207	\N	\N	\N	\N
372	4	208	\N	\N	\N	\N
373	4	209	\N	\N	\N	\N
374	4	210	\N	\N	\N	\N
40	5	211	\N	\N	\N	\N
41	5	212	\N	\N	\N	\N
53	5	213	\N	\N	\N	\N
257	5	214	\N	\N	\N	\N
300	5	215	\N	\N	\N	\N
303	5	216	\N	\N	\N	\N
313	5	217	\N	\N	\N	\N
376	5	218	\N	\N	\N	\N
385	5	219	\N	\N	\N	\N
387	5	220	\N	\N	\N	\N
389	5	221	\N	\N	\N	\N
391	5	222	\N	\N	\N	\N
393	5	223	\N	\N	\N	\N
395	5	224	\N	\N	\N	\N
397	5	225	\N	\N	\N	\N
399	5	226	\N	\N	\N	\N
404	5	227	\N	\N	\N	\N
4	6	228	\N	\N	\N	\N
22	6	229	\N	\N	\N	\N
23	6	230	\N	\N	\N	\N
24	6	231	\N	\N	\N	\N
25	6	232	\N	\N	\N	\N
26	6	233	\N	\N	\N	\N
253	6	234	\N	\N	\N	\N
254	6	235	\N	\N	\N	\N
258	6	236	\N	\N	\N	\N
259	6	237	\N	\N	\N	\N
260	6	238	\N	\N	\N	\N
261	6	239	\N	\N	\N	\N
262	6	240	\N	\N	\N	\N
263	6	241	\N	\N	\N	\N
264	6	242	\N	\N	\N	\N
265	6	243	\N	\N	\N	\N
266	6	244	\N	\N	\N	\N
268	6	245	\N	\N	\N	\N
269	6	246	\N	\N	\N	\N
299	6	247	\N	\N	\N	\N
306	6	248	\N	\N	\N	\N
339	6	249	\N	\N	\N	\N
340	6	250	\N	\N	\N	\N
342	6	251	\N	\N	\N	\N
538	6	252	\N	\N	\N	\N
277	7	253	\N	\N	\N	\N
71	8	254	\N	\N	\N	\N
72	8	255	\N	\N	\N	\N
73	8	256	\N	\N	\N	\N
74	8	257	\N	\N	\N	\N
75	8	258	\N	\N	\N	\N
76	8	259	\N	\N	\N	\N
345	8	260	\N	\N	\N	\N
298	9	261	\N	\N	\N	\N
308	9	262	\N	\N	\N	\N
309	9	263	\N	\N	\N	\N
310	9	264	\N	\N	\N	\N
56	10	265	\N	\N	\N	\N
57	10	266	\N	\N	\N	\N
58	10	267	\N	\N	\N	\N
59	10	268	\N	\N	\N	\N
60	10	269	\N	\N	\N	\N
61	10	270	\N	\N	\N	\N
62	10	271	\N	\N	\N	\N
63	10	272	\N	\N	\N	\N
64	10	273	\N	\N	\N	\N
65	10	274	\N	\N	\N	\N
66	10	275	\N	\N	\N	\N
67	10	276	\N	\N	\N	\N
68	10	277	\N	\N	\N	\N
380	10	278	\N	\N	\N	\N
381	10	279	\N	\N	\N	\N
505	10	280	\N	\N	\N	\N
506	10	281	\N	\N	\N	\N
507	10	282	\N	\N	\N	\N
508	10	283	\N	\N	\N	\N
509	10	284	\N	\N	\N	\N
510	10	285	\N	\N	\N	\N
512	10	286	\N	\N	\N	\N
378	11	287	\N	\N	\N	\N
555	12	288	\N	\N	\N	\N
44	4	176	2002-07-11	IMAGE	2010-02-04	migracion
46	10	45	2002-07-11	IMAGE	2010-02-04	migracion
272	4	39	2002-08-23	IMAGE	2010-02-04	migracion
296	5	309	2002-08-23	IMAGE	2010-02-04	migracion
304	9	310	2002-08-23	IMAGE	2010-02-04	migracion
307	5	311	2002-08-23	IMAGE	2010-02-04	migracion
312	5	312	2002-08-23	IMAGE	2010-02-04	migracion
314	5	313	2002-08-23	IMAGE	2010-02-04	migracion
315	1	314	2002-08-23	IMAGE	2010-02-04	migracion
317	5	317	2002-08-23	IMAGE	2010-02-04	migracion
318	5	315	2002-08-23	IMAGE	2010-02-04	migracion
502	1	316	2004-09-06	ATTA	2010-02-04	migracion
421	1	318	2004-09-06	ATTA	2010-02-04	migracion
422	1	319	2004-09-06	ATTA	2010-02-04	migracion
433	1	320	2004-09-06	ATTA	2010-02-04	migracion
482	1	321	2004-09-06	ATTA	2010-02-04	migracion
483	1	322	2004-09-06	ATTA	2010-02-04	migracion
484	1	323	2004-09-06	ATTA	2010-02-04	migracion
485	1	324	2004-09-06	ATTA	2010-02-04	migracion
492	1	325	2004-09-06	ATTA	2010-02-04	migracion
493	1	326	2004-09-06	ATTA	2010-02-04	migracion
494	1	327	2004-09-06	ATTA	2010-02-04	migracion
561	1	328	2004-09-06	ATTA	2010-02-04	migracion
562	1	329	2004-09-06	ATTA	2010-02-04	migracion
563	1	330	2004-09-06	ATTA	2010-02-04	migracion
564	1	308	2002-08-23	IMAGE	2010-02-04	migracion
\.


--
-- Data for Name: media_attribute; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY media_attribute (media_attribute_id, description_text_id, name_text_id, media_attribute_table_name, media_attribute_value_type, creation_date, created_by, last_modification_date, last_modification_by, media_attribute_type_predefined) FROM stdin;
12	\N	31	pixels height	N	\N	\N	\N	\N	N
13	\N	32	pixels width	N	\N	\N	\N	\N	N
15	\N	34	production date	D	\N	\N	\N	\N	N
5	\N	24	org.inbio.m3s.persistance.pam.ExposureMode	V	\N	\N	\N	\N	N
6	\N	25	org.inbio.m3s.persistance.pam.FNumber	V	\N	\N	\N	\N	N
1	\N	20	org.inbio.m3s.persistance.pam.Iso	N	\N	\N	\N	\N	N
2	\N	21	org.inbio.m3s.persistance.pam.CamaraMaker	V	\N	\N	\N	\N	N
3	\N	22	org.inbio.m3s.persistance.pam.CamaraModel	V	\N	\N	\N	\N	N
4	\N	23	org.inbio.m3s.persistance.pam.MeeteringMode	V	\N	\N	\N	\N	N
7	\N	26	org.inbio.m3s.persistance.pam.FocalLength	V	\N	\N	\N	\N	N
8	\N	27	org.inbio.m3s.persistance.pam.ExposureBias	V	\N	\N	\N	\N	N
9	\N	28	org.inbio.m3s.persistance.pam.WhiteBalance	V	\N	\N	\N	\N	N
11	\N	30	org.inbio.m3s.persistance.pam.Flash	V	\N	\N	\N	\N	N
14	\N	33	org.inbio.m3s.persistance.pam.ExposureTime	V	\N	\N	\N	\N	N
10	\N	29	org.inbio.m3s.persistance.pam.Saturation	V	\N	\N	\N	\N	N
17	\N	289	file type	V	\N	\N	\N	\N	N
16	\N	290	org.inbio.m3s.persistance.pam.FileExtension	V	\N	\N	\N	\N	N
19	\N	293	Aspect Ratio	V	\N	\N	\N	\N	N
20	\N	294	Pixels Horizontal	V	\N	\N	\N	\N	N
21	\N	295	Pixels Vertical	V	\N	\N	\N	\N	N
22	\N	296	Sound	V	\N	\N	\N	\N	N
23	\N	297	Signal Format	V	\N	\N	\N	\N	N
24	\N	298	Compression	V	\N	\N	\N	\N	N
25	\N	299	Audio Data Encoding	V	\N	\N	\N	\N	N
26	\N	300	Bits per Sample	V	\N	\N	\N	\N	N
27	\N	301	Tagged Id	V	\N	\N	\N	\N	N
28	\N	302	Duration	V	\N	\N	\N	\N	N
29	\N	303	Number of Channels	V	\N	\N	\N	\N	N
30	\N	304	Sampling Frequency	V	\N	\N	\N	\N	N
18	\N	292	Format Name	V	\N	\N	\N	\N	N
31	307	306	Categoría Anterior	N	2009-12-14	migracion	\N	\N	N
32	333	331	Film Brand	N	2010-02-04	migracion	\N	\N	N
33	\N	332	Scanning Date	D	2010-02-04	migracion	\N	\N	N
34	\N	334	Image Date	D	2010-02-04	migracion	\N	\N	N
35	\N	\N	youtube	V	2010-02-08	jgutierrez	\N	\N	N
38	\N	\N	GPS Longitude	V	\N	\N	\N	\N	N
39	\N	\N	GPS Latitude	V	\N	\N	\N	\N	N
\.


--
-- Data for Name: media_category; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY media_category (media_category_id, description_text_id, name_text_id, creation_date, created_by, last_modification_date, last_modification_by) FROM stdin;
1	\N	6	\N	imagen	\N	\N
2	\N	7	\N	audio	\N	\N
3	\N	8	\N	video	\N	\N
\.


--
-- Data for Name: media_type; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY media_type (media_type_id, description_text_id, name_text_id, media_category_id, creation_date, created_by, last_modification_date, last_modification_by) FROM stdin;
1	\N	9	1	\N	dsc	\N	\N
2	\N	10	1	\N	escaneada	\N	\N
3	\N	11	1	\N	diapositiva	\N	\N
4	\N	35	3	\N	video MOV	\N	\N
5	\N	305	1	2009-12-14	migracion	\N	\N
6	\N	35	3	2009-02-10	youtube	\N	\N
\.


--
-- Data for Name: metadata_standard; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY metadata_standard (metadata_standard_id, name, description, metadata_standard_implementation_class, creation_date, created_by, last_modification_date, last_modification_by) FROM stdin;
2	FILE_INFO	\N	org.inbio.m3s.usecases.admin.impl.FileInfoMetadataExtractorImpl	2007-11-22	jgutierrez	2007-11-22	jgutierrez
1	EXIF	usado para imagenes	org.inbio.m3s.usecases.admin.impl.EXIFMetadataExtractorImpl	\N	\N	2007-11-22	jgutierrez
3	MET	usado para videos	org.inbio.m3s.usecases.admin.impl.VideoMetadataExtractor	2008-06-06	jgutierrez	2008-06-06	jgutierrez
4	MIGRACION	usado para las imagenes migradas de ATTA	\N	2009-12-14	migracion	\N	\N
5	YOUTUBE	\N	\N	\N	\N	\N	\N
\.


--
-- Data for Name: media_attribute_type; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY media_attribute_type (media_type_id, media_attribute_id, metadata_standard_id, creation_date, created_by, last_modification_date, last_modification_by, standard_attribute_id) FROM stdin;
1	1	1	\N	\N	\N	\N	0
1	2	1	\N	\N	\N	\N	1
1	3	1	\N	\N	\N	\N	2
1	4	1	\N	\N	\N	\N	3
1	5	1	\N	\N	\N	\N	4
1	6	1	\N	\N	\N	\N	5
1	7	1	\N	\N	\N	\N	6
1	8	1	\N	\N	\N	\N	7
1	9	1	\N	\N	\N	\N	8
1	10	1	\N	\N	\N	\N	9
1	11	1	\N	\N	\N	\N	10
1	12	1	\N	\N	\N	\N	11
1	13	1	\N	\N	\N	\N	12
1	14	1	\N	\N	\N	\N	13
1	15	1	\N	\N	\N	\N	14
1	16	2	\N	\N	\N	\N	0
1	17	2	\N	\N	\N	\N	1
4	19	3	2008-06-12	administrator	2008-06-12	administrator	1
4	20	3	2008-06-12	administrator	2008-06-12	administrator	2
4	21	3	2008-06-12	administrator	2008-06-12	administrator	3
4	22	3	2008-06-12	administrator	2008-06-12	administrator	4
4	23	3	2008-06-12	administrator	2008-06-12	administrator	5
4	24	3	2008-06-12	administrator	2008-06-12	administrator	6
4	25	3	2008-06-12	administrator	2008-06-12	administrator	7
4	26	3	2008-06-12	administrator	2008-06-12	administrator	8
4	27	3	2008-06-12	administrator	2008-06-12	administrator	9
4	28	3	2008-06-12	administrator	2008-06-12	administrator	10
4	29	3	2008-06-12	administrator	2008-06-12	administrator	11
4	30	3	2008-06-12	administrator	2008-06-12	administrator	12
4	18	3	2008-06-12	administrator	2008-06-12	administrator	0
4	17	2	\N	\N	\N	\N	1
4	16	2	\N	\N	\N	\N	0
5	12	4	2009-12-14	migracion	\N	\N	0
5	13	4	2009-12-14	migracion	\N	\N	0
5	16	4	2009-12-14	migracion	\N	\N	0
5	17	4	2009-12-14	migracion	\N	\N	0
5	31	4	2009-12-14	migracion	\N	\N	0
5	32	4	2010-02-04	migracion	\N	\N	0
5	33	4	2010-02-04	migracion	\N	\N	0
5	1	4	2010-02-04	migracion	\N	\N	0
6	35	3	2010-02-10	jgutierrez	\N	\N	0
4	34	4	2010-02-04	migracion	\N	\N	0
5	37	4	2010-04-21	admin	\N	\N	0
5	36	4	2010-04-21	admin	\N	\N	0
\.


--
-- Data for Name: media_use; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY media_use (media_use_id, description_text_id, name_text_id, creation_date, created_by, last_modification_date, last_modification_by) FROM stdin;
1	\N	3	\N	\N	\N	\N
2	\N	4	\N	\N	\N	\N
3	\N	5	\N	\N	\N	\N
\.


--
-- Data for Name: privilege; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY privilege (privilege_id, name, creation_date, created_by, last_modification_date, last_modification_by) FROM stdin;
1	Lectura	2007-10-12	\N	\N	\N
2	Escritura	2007-10-12	\N	\N	\N
3	AdministraciÃ³n	2007-10-12	\N	\N	\N
\.


--
-- Data for Name: security_users; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY security_users (name, description, priority, user_type, password, deleted, system_group, creation_date, created_by, last_modification_date, last_modification_by) FROM stdin;
administrador	administrador del sistema	\N	\N	inbiom3s	\N	\N	2008-04-30	\N	\N	\N
dsolano	daniel solano	\N	\N	botanica1	\N	\N	2008-05-19	\N	\N	\N
jgutierrez	jaime gutierrez	\N	\N	inbiom3s	\N	\N	2008-06-06	\N	\N	\N
eulate	elena ulate	\N	\N	artropodos2	\N	\N	2008-09-18	administrtor	2008-09-18	administrator
hesquivel	herson esquivel	\N	\N	inbio1234	\N	\N	2008-10-05	administrator	2008-10-05	administrator
chernand	carlos hernandez	\N	\N	orophus	\N	\N	2008-09-02	\N	\N	\N
aherrera	Alvaro Herrera	\N	\N	colibri	\N	\N	2008-10-31	administrador	2008-10-31	administrador
dvargas	diego vargas	\N	\N	inbio08	\N	\N	\N	\N	\N	\N
ealvarado	Eduardo Alvarado	\N	\N	ealvarado01	\N	\N	2009-01-19	administrator	\N	\N
mmata	Milagro Mata	\N	\N	Turri2009	\N	\N	2009-01-19	administrator	\N	\N
jchaves	Jose Luis Chaves	\N	\N	Jo5e01	\N	\N	2009-01-19	administrator	\N	\N
enavarro	Enia Navarro	\N	\N	dania91	\N	\N	2009-01-19	administrator	\N	\N
fgonzalez	Frank Gonzalez	\N	\N	botanica2	\N	\N	2009-09-02	admin	2009-09-02	admin
aavila	Alejandra Avila	\N	\N	alejandra	\N	\N	2009-12-01	admin	2009-12-01	admin
consultorias	consultorias	\N	\N	consultorias	\N	\N	2009-12-01	admin	2009-12-01	admin
eboza	Eduardo Boza	\N	\N	eduardo	\N	\N	\N	\N	\N	\N
\.


--
-- Data for Name: project; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY project (project_id, project_manager_name, name, description, creation_date, created_by, last_modification_date, last_modification_by) FROM stdin;
3	dsolano	Darwin	proyecto darwin	2008-05-16	administrator	\N	\N
4	chernand	chernand	proyecto personal	2008-09-02	administrador	2008-09-02	administrador
5	eulate	eulate	proyecto personal	2008-09-18	admnistrador	2008-09-18	adminitrator
7	aherrera	aherrera	proyecto personal	2008-10-31	admin	\N	\N
8	aherrera	Santa Juana	\N	2008-11-05	admin	2008-11-05	admin
6	hesquivel	hesquivel	proyecto personal	2008-10-05	administrator	2008-05-10	administrator
9	dvargas	dvargas	proyecto personal	2008-11-07	admin	2008-11-07	admin
10	jchaves	jchaves	proyecto personal	2009-01-19	admin	2009-01-19	admin
11	ealvarado	ealvarado	proyecto personal	2009-01-19	admin	2009-01-19	admin
12	mmata	mmata	proyecto personal	2009-01-19	admin	2009-01-19	admin
13	enavarro	enavarro	proyecto personal	2009-01-19	admin	2009-01-19	admin
14	fgonzalez	fgonzalez	proyecto personal	2009-09-02	admin	2009-09-02	admin
15	chernand	Veragua	Solicitado por Carlos Hernández	2009-11-03	admin	2009-11-03	admin
16	aherrera	KBA-CI	Solicitado por Alvaro Herrera	2010-05-13	admin	2010-05-13	admin
\.


--
-- Data for Name: system_user; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY system_user (user_id, fullname, username, password, created_by, creation_date, last_modification_by, last_modification_date, enabled, person_id, roles) FROM stdin;
1	Administrador	administrator	inbiom3s	admin	2010-04-30	admin	2010-04-30	t	\N	ROLE_ADMIN,ROLE_USER
3	Jaime Gutiérrez Alfaro	jgutierrez	inbiom3s	admin	2010-04-30	admin	2010-04-30	t	\N	ROLE_ADMIN,ROLE_USER
2	Daniel Solano	dsolano	botanica1	admin	2010-04-30	admin	2010-04-30	t	\N	ROLE_ADMIN,ROLE_USER
4	Carlos Hernández	chernand	orophus	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
5	Elena Ulate	eulate	artropodos2	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
6	Herson Esquivel	hesquivel	herson	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
7	Alvaro Herrera	aherrera	colibri	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
8	\N	dvargas	inbio08	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
9	\N	jchaves	Jo5e01	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
10	\N	ealvarado	ealvarado01	\N	\N	\N	\N	t	\N	ROLE_USER
11	Milagro Mata	mmata	Turri2009	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
12	Enia Navarro	enavarro	dania91	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
13	Frank Gonzalez	fgonzalez	botanica2	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
14	José Montero	jmontero	artropodos3	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
15	Carlos Viquez	cviquez	artropodos4	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
16	Manuel Zumbado	mzumbado	artropodos5	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
17	Manuel Solís	msolis	artropodos6	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
18	Ángel Solís	asolis	artropodos7	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
19	Cristian Granados	cgranados	bichitos	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
20	Alejandra Avila	aavila	alejandra	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
21	Consultorias	consultorias	consultorias	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
22	Marías Mora	mmora	maria	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
23	Esteban Mata	esmata	esteban	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
24	David Solano	dasolano	david	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
25	Geannina Sulca	gsulca	geannina	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
26	Paula Corrales	pcorrales	paula	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
27	Fabio Hidalgo	fhidalgo	fabio	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
28	Aldrin Coto	acoto	aldrin	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
29	Ronny Hernandez	rhernand	ronny	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
30	Jorge Vargas	jvargas	jorge	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
33	Eduardo Boza	eboza	eduardo	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
32	Julio Ramirez	jramirez	julio	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
34	Alejandro Calvo	alecalvo	alejandro	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
35	Marviva	marviva	marviva	\N	\N	\N	\N	t	\N	ROLE_USER
36	Alex Monro	amonro	pila	\N	\N	\N	\N	t	\N	ROLE_USER
37	Esteban Ocampo	eocampo	esteban	\N	\N	\N	\N	t	\N	ROLE_ADMIN,ROLE_USER
\.


--
-- Data for Name: text_translation; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY text_translation (text_translation_id, text_id, name, creation_date, created_by, last_modification_date, last_modification_by, locale) FROM stdin;
205	185	Talleres	\N	\N	\N	\N	es
1	3	Editorial	\N	\N	\N	\N	es
2	4	Venta	\N	\N	\N	\N	es
15	8	Videos	\N	\N	\N	\N	es
13	7	Audio	\N	\N	\N	\N	es
19	10	Escaneada	\N	\N	\N	\N	es
21	11	Diapositiva	\N	\N	\N	\N	es
23	12	Solo para UBI	\N	\N	\N	\N	es
24	13	Uso institucional	\N	\N	\N	\N	es
25	14	Consultar con el propietario(a)	\N	\N	\N	\N	es
26	15	No asignada	\N	\N	\N	\N	es
27	16	Consultar con el autor	\N	\N	\N	\N	es
28	17	Consultar con el curador del grupo	\N	\N	\N	\N	es
29	18	Consultar con Alvaro Herrera	\N	\N	\N	\N	es
31	20	ISO-ASA	\N	\N	\N	\N	es
34	23	Meetering Mode	\N	\N	\N	\N	es
39	28	Balance de Blancos	\N	\N	\N	\N	es
41	30	Flash	\N	\N	\N	\N	es
48	36	Insectos	\N	\N	\N	\N	es
49	37	Plantas	\N	\N	\N	\N	es
50	38	Paisajes	\N	\N	\N	\N	es
51	39	INBio	\N	\N	\N	\N	es
52	40	Moluscos	\N	\N	\N	\N	es
53	41	Vertebrados	\N	\N	\N	\N	es
54	42	Nematodos	\N	\N	\N	\N	es
55	43	Liquenes	\N	\N	\N	\N	es
56	44	Tipo de bosques	\N	\N	\N	\N	es
57	45	Hongos	\N	\N	\N	\N	es
58	46	Microhongos	\N	\N	\N	\N	es
59	47	Biodiccionarios	\N	\N	\N	\N	es
61	49	Insecto	\N	\N	\N	\N	es
62	50	Coleoptera	\N	\N	\N	\N	es
64	52	Lepidoptera	\N	\N	\N	\N	es
65	53	Hymenoptera	\N	\N	\N	\N	es
66	54	Diptera	\N	\N	\N	\N	es
67	55	Homoptera	\N	\N	\N	\N	es
68	56	Blataria	\N	\N	\N	\N	es
69	57	Escorpiones	\N	\N	\N	\N	es
70	58	Orthoptera	\N	\N	\N	\N	es
71	59	Hemiptera	\N	\N	\N	\N	es
72	60	Odonata	\N	\N	\N	\N	es
76	63	Ahogapollos	\N	\N	\N	\N	es
75	62	Escarabajo	\N	\N	\N	\N	es
78	65	Scarab	\N	\N	\N	\N	es
79	66	Coleptero	\N	\N	\N	\N	es
80	67	Joboto	\N	\N	\N	\N	es
81	68	Jogoto	\N	\N	\N	\N	es
82	69	Choboto	\N	\N	\N	\N	es
83	70	Gallina ciega	\N	\N	\N	\N	es
86	72	Dynastinos	\N	\N	\N	\N	es
87	73	Cuernos	\N	\N	\N	\N	es
88	74	Cornizuelo	\N	\N	\N	\N	es
89	75	Caquero	\N	\N	\N	\N	es
91	77	Ruedacaca	\N	\N	\N	\N	es
92	78	Rodacaca	\N	\N	\N	\N	es
93	79	Cetoninos	\N	\N	\N	\N	es
94	80	Frutero	\N	\N	\N	\N	es
95	81	Hiperparasitoide	\N	\N	\N	\N	es
96	82	Multiparasitismo	\N	\N	\N	\N	es
97	83	Cleptoparasito	\N	\N	\N	\N	es
98	84	Parasitismo social	\N	\N	\N	\N	es
99	85	Ectoparasitoide	\N	\N	\N	\N	es
100	86	Endoparasitoide	\N	\N	\N	\N	es
101	87	Colonizador	\N	\N	\N	\N	es
102	88	Huespede	\N	\N	\N	\N	es
103	89	Inquilino	\N	\N	\N	\N	es
104	90	Agallador	\N	\N	\N	\N	es
105	91	Koinobionte	\N	\N	\N	\N	es
106	92	Idiobionte	\N	\N	\N	\N	es
107	93	Solitario	\N	\N	\N	\N	es
108	94	Social	\N	\N	\N	\N	es
109	95	Eusocial	\N	\N	\N	\N	es
110	96	Presocial	\N	\N	\N	\N	es
111	97	Subsocial	\N	\N	\N	\N	es
112	98	Comensal	\N	\N	\N	\N	es
113	99	Fungivoro (Fungifago)	\N	\N	\N	\N	es
114	100	Haplodiploide	\N	\N	\N	\N	es
115	101	Partenognesis	\N	\N	\N	\N	es
116	102	Poliembrionia	\N	\N	\N	\N	es
117	103	Depredador	\N	\N	\N	\N	es
118	104	Hipermetamorfosis	\N	\N	\N	\N	es
119	105	Abeja	\N	\N	\N	\N	es
120	106	Abeja africanizada	\N	\N	\N	\N	es
121	107	Abejorro	\N	\N	\N	\N	es
122	108	Avispa	\N	\N	\N	\N	es
123	109	Avispa parasitoide	\N	\N	\N	\N	es
124	110	Avispa depredadora	\N	\N	\N	\N	es
125	111	Avispa social	\N	\N	\N	\N	es
126	112	Avispa solitaras	\N	\N	\N	\N	es
128	114	Microhimenptera	\N	\N	\N	\N	es
129	115	Hormiga	\N	\N	\N	\N	es
130	116	Reina	\N	\N	\N	\N	es
131	117	Obrera	\N	\N	\N	\N	es
133	119	Grillo	\N	\N	\N	\N	es
134	120	Saltamontes	\N	\N	\N	\N	es
135	121	Chapulines	\N	\N	\N	\N	es
136	122	Esperanzas	\N	\N	\N	\N	es
137	123	Mimetismo	\N	\N	\N	\N	es
138	124	Saltadores	\N	\N	\N	\N	es
139	125	Insectos Hojas	\N	\N	\N	\N	es
140	126	Salta Hojas	\N	\N	\N	\N	es
141	127	Langostas	\N	\N	\N	\N	es
142	128	Adulto	\N	\N	\N	\N	es
145	130	Moscas	\N	\N	\N	\N	es
147	132	Tabanids	\N	\N	\N	\N	es
148	133	Mariposa	\N	\N	\N	\N	es
149	134	Polilla	\N	\N	\N	\N	es
152	135	Papilion	\N	\N	\N	\N	es
153	136	Escama	\N	\N	\N	\N	es
154	137	Mantis religiosa	\N	\N	\N	\N	es
155	138	Probscide	\N	\N	\N	\N	es
157	139	Rezadoras	\N	\N	\N	\N	es
158	140	Mulas del diablo	\N	\N	\N	\N	es
159	141	Maria seca	\N	\N	\N	\N	es
160	142	Picudo	\N	\N	\N	\N	es
161	143	Gorgojo	\N	\N	\N	\N	es
162	144	Curculionido	\N	\N	\N	\N	es
163	145	Arboles	\N	\N	\N	\N	es
164	146	Canopy	\N	\N	\N	\N	es
165	147	Copa	\N	\N	\N	\N	es
166	148	Dosel	\N	\N	\N	\N	es
167	149	Escarabajos de tierra	\N	\N	\N	\N	es
170	150	Bromelias	\N	\N	\N	\N	es
171	151	Plantas	\N	\N	\N	\N	es
173	153	Helechos	\N	\N	\N	\N	es
174	154	Bosque	\N	\N	\N	\N	es
175	155	Cactus	\N	\N	\N	\N	es
176	156	Flores	\N	\N	\N	\N	es
177	157	Troncos	\N	\N	\N	\N	es
178	158	Arbustos	\N	\N	\N	\N	es
179	159	Frutos	\N	\N	\N	\N	es
180	160	Bejucos	\N	\N	\N	\N	es
181	161	Epfitas	\N	\N	\N	\N	es
182	162	Hierbas	\N	\N	\N	\N	es
183	163	Hojas	\N	\N	\N	\N	es
185	165	Paisaje	\N	\N	\N	\N	es
188	168	Inventario	\N	\N	\N	\N	es
189	169	Curadores	\N	\N	\N	\N	es
194	174	Etiquetado	\N	\N	\N	\N	es
195	175	Colecciones	\N	\N	\N	\N	es
200	180	Publicaciones	\N	\N	\N	\N	es
202	182	Bioensayos	\N	\N	\N	\N	es
207	187	Trampas	\N	\N	\N	\N	es
209	189	INBioParque	\N	\N	\N	\N	es
211	191	Estaciones	\N	\N	\N	\N	es
212	192	Base de datos	\N	\N	\N	\N	es
216	196	Muestras	\N	\N	\N	\N	es
219	199	Cultivo de tejidos	\N	\N	\N	\N	es
222	202	Editorial	\N	\N	\N	\N	es
223	203	Atta	\N	\N	\N	\N	es
224	204	Ecomapas	\N	\N	\N	\N	es
225	205	ProEBI	\N	\N	\N	\N	es
226	206	Visitas especiales	\N	\N	\N	\N	es
227	207	Convenios	\N	\N	\N	\N	es
230	210	SINAC	\N	\N	\N	\N	es
231	211	Moluscos marinos	\N	\N	\N	\N	es
232	212	Conchas marinas	\N	\N	\N	\N	es
233	213	Moluscos Terrestres	\N	\N	\N	\N	es
234	214	Caracoles terrestres	\N	\N	\N	\N	es
235	215	Babosas marinas	\N	\N	\N	\N	es
236	216	Ostra	\N	\N	\N	\N	es
237	217	Caracoles marinos	\N	\N	\N	\N	es
238	218	Babosas terrestres	\N	\N	\N	\N	es
239	219	Moluscos dulceacuicolas	\N	\N	\N	\N	es
240	220	Bivalvo	\N	\N	\N	\N	es
241	221	Pulpo	\N	\N	\N	\N	es
242	222	Calamar	\N	\N	\N	\N	es
243	223	Colmillo de mar	\N	\N	\N	\N	es
244	224	Cucaracha de mar	\N	\N	\N	\N	es
245	225	concha marina	\N	\N	\N	\N	es
246	226	Moluscos	\N	\N	\N	\N	es
248	228	Reptiles	\N	\N	\N	\N	es
249	229	Anfibios	\N	\N	\N	\N	es
250	230	Ranas	\N	\N	\N	\N	es
251	231	Lagartijas	\N	\N	\N	\N	es
252	232	Salamandras	\N	\N	\N	\N	es
253	233	Mamiferos	\N	\N	\N	\N	es
254	234	Ballenas	\N	\N	\N	\N	es
255	235	Delfines	\N	\N	\N	\N	es
256	236	Culebras	\N	\N	\N	\N	es
257	237	Serpientes	\N	\N	\N	\N	es
260	240	Cocodrilo	\N	\N	\N	\N	es
261	241	Lagartos	\N	\N	\N	\N	es
262	242	Iguana	\N	\N	\N	\N	es
263	243	Tortugas	\N	\N	\N	\N	es
265	245	Sapos	\N	\N	\N	\N	es
266	246	Cecilidos	\N	\N	\N	\N	es
267	247	Aves	\N	\N	\N	\N	es
268	248	Gekos	\N	\N	\N	\N	es
269	249	Ratones	\N	\N	\N	\N	es
270	250	Taltuza	\N	\N	\N	\N	es
271	251	Garrobos	\N	\N	\N	\N	es
272	252	Peces	\N	\N	\N	\N	es
281	261	Bosque nuboso	\N	\N	\N	\N	es
282	262	Bosque primario	\N	\N	\N	\N	es
283	263	Bosque secundario	\N	\N	\N	\N	es
284	264	Bosque intervenido	\N	\N	\N	\N	es
285	265	Colmenas	\N	\N	\N	\N	es
286	266	Copas	\N	\N	\N	\N	es
287	267	Coraloides	\N	\N	\N	\N	es
288	268	Costras	\N	\N	\N	\N	es
289	269	Dentados	\N	\N	\N	\N	es
290	270	Discos	\N	\N	\N	\N	es
291	271	Estrellas de tierra	\N	\N	\N	\N	es
292	272	Gelatinosos	\N	\N	\N	\N	es
293	273	Globosos	\N	\N	\N	\N	es
294	274	Hongos ostra	\N	\N	\N	\N	es
295	275	Lenguas de tierra	\N	\N	\N	\N	es
296	276	Nidos	\N	\N	\N	\N	es
297	277	Orejas de palo	\N	\N	\N	\N	es
298	278	Sombrillas	\N	\N	\N	\N	es
299	279	Trompetas	\N	\N	\N	\N	es
300	280	Dedos de muerto	\N	\N	\N	\N	es
301	281	Hongos faloides	\N	\N	\N	\N	es
302	282	Espatulados	\N	\N	\N	\N	es
305	285	Hongos capitados	\N	\N	\N	\N	es
306	286	Cultivo de Hongos	\N	\N	\N	\N	es
307	287	Microhongos	\N	\N	\N	\N	es
42	31	Pixels de Largo	\N	\N	\N	\N	es
43	32	Pixels de Alto	\N	\N	\N	\N	es
37	26	Longitud Focal (F)	\N	focal length	\N	\N	es
309	289	Tipo de Archivo (MIME)	\N	\N	\N	\N	es
311	290	Extension del Archivo	\N	\N	\N	\N	es
313	291	valor de prueba = viva la liga	2008-03-11	administrator	2008-03-11	administrator	es
3	5	Publicación	\N	\N	\N	\N	es
7	1	Español	\N	\N	\N	\N	es
9	2	Inglés	\N	\N	\N	\N	es
11	6	Imágenes	\N	\N	\N	\N	es
17	9	Cámara Digital	\N	\N	\N	\N	es
30	19	Polí­tica de uso no definida.	\N	\N	\N	\N	es
36	25	Número f	\N	F-Number	\N	\N	es
40	29	Saturación	\N	\N	\N	\N	es
44	33	Tiempo de Exposición	\N	exposure time	\N	\N	es
45	34	Fecha de Producción	\N	\N	\N	\N	es
32	21	Fabricante de la Cámara	\N	\N	\N	\N	es
33	22	Modelo de la Cámara	\N	\N	\N	\N	es
35	24	Modo de Exposición	\N	exposure mode	\N	\N	es
38	27	Bias de la Exposición	\N	exposure bias	\N	\N	es
73	61	Abejón	\N	\N	\N	\N	es
84	71	Centro América	\N	\N	\N	\N	es
184	164	Orquídeas	\N	\N	\N	\N	es
186	166	Bioalfabetización	\N	\N	\N	\N	es
190	170	Técnicos	\N	\N	\N	\N	es
191	171	Recolección	\N	\N	\N	\N	es
192	172	Parataxónomos	\N	\N	\N	\N	es
193	173	Ilustración	\N	\N	\N	\N	es
196	176	Identificación	\N	\N	\N	\N	es
198	178	Bioinformática	\N	\N	\N	\N	es
201	181	Bioprospección	\N	\N	\N	\N	es
203	183	Taxónomos	\N	\N	\N	\N	es
204	184	Especímenes	\N	\N	\N	\N	es
206	186	Parataxónomos marinos	\N	\N	\N	\N	es
208	188	Capacitación	\N	\N	\N	\N	es
210	190	Conservación para el Desarrollo	\N	\N	\N	\N	es
213	193	Gestión Social	\N	\N	\N	\N	es
214	194	Prospección química	\N	\N	\N	\N	es
215	195	Extracción	\N	\N	\N	\N	es
217	197	Biotecnología	\N	\N	\N	\N	es
218	198	Preparación de muestras	\N	\N	\N	\N	es
220	200	Microbiología	\N	\N	\N	\N	es
221	201	Biología molecular	\N	\N	\N	\N	es
229	209	Dirección General	\N	\N	\N	\N	es
228	208	Administración	\N	\N	\N	\N	es
258	238	Víbora	\N	\N	\N	\N	es
259	239	Caimán	\N	\N	\N	\N	es
264	244	Murciélagos	\N	\N	\N	\N	es
274	254	Líquenes crustáceos	\N	\N	\N	\N	es
275	255	Lí­quenes epfilos (sobre hojas)	\N	\N	\N	\N	es
276	256	Lí­quene escuamulosos	\N	\N	\N	\N	es
277	257	Lí­quenes foliosos	\N	\N	\N	\N	es
278	258	Lí­quenes fruticulosos	\N	\N	\N	\N	es
279	259	Lí­quenes gelatinosos	\N	\N	\N	\N	es
280	260	Lí­quenes	\N	\N	\N	\N	es
303	283	Estructuras microscópicas	\N	\N	\N	\N	es
304	284	Parásitos de insectos	\N	\N	\N	\N	es
308	288	Biodiccionario Botánico	\N	\N	\N	\N	es
315	293	Aspect Ratio	2008-06-12	administrator	2008-06-12	administrator	es
316	294	Pixels Horizontal	2008-06-12	administrator	2008-06-12	administrator	es
317	295	Pixels Vertical	2008-06-12	administrator	2008-06-12	administrator	es
318	296	Sound	2008-06-12	administrator	2008-06-12	administrator	es
319	297	Signal Format	2008-06-12	administrator	2008-06-12	administrator	es
320	298	Compression	2008-06-12	administrator	2008-06-12	administrator	es
321	299	Audio Data Encoding	2008-06-12	administrator	2008-06-12	administrator	es
322	300	Bits per Sample	2008-06-12	administrator	2008-06-12	administrator	es
323	301	Tagged Id	2008-06-12	administrator	2008-06-12	administrator	es
324	302	Duration	2008-06-12	administrator	2008-06-12	administrator	es
325	303	Number of Channels	2008-06-12	administrator	2008-06-12	administrator	es
326	304	Sampling Frequency	2008-06-12	administrator	2008-06-12	administrator	es
46	35	Video formato MOV	\N	\N	\N	\N	es
314	292	Format Name	2008-06-12	administrator	2008-06-12	administrator	es
146	131	Dípteros	\N	\N	\N	\N	es
172	152	Botánica	\N	\N	\N	\N	es
273	253	Nemátodos	\N	\N	\N	\N	es
63	51	Entomología	\N	\N	\N	\N	es
327	305	migracion	2009-12-14	migracion	\N	\N	es
328	306	categoria anterior	2009-12-14	migracion	\N	\N	es
329	307	0:No Asignada;1:Diapositiva;2:Dibujo B/N;3:Dibujo Color;4:Fotografia Papel;5:Mapa/Distribucion;6:Fotografia Camara;7:Montage program;45:Ejemplar de herbario;46:Imagen del capturador digital	2009-12-14	migracion	\N	\N	es
60	48	Arañas	\N	\N	\N	\N	es
77	64	Cucarrón	\N	\N	\N	\N	es
90	76	Chocorrón	\N	\N	\N	\N	es
132	118	Ortóptero	\N	\N	\N	\N	es
187	167	Malacología	\N	\N	\N	\N	es
197	177	Digitación	\N	\N	\N	\N	es
199	179	Sistemas de Información Geográfica	\N	\N	\N	\N	es
144	129	Tábanos	\N	\N	\N	\N	es
247	227	Gastrápodos	\N	\N	\N	\N	es
127	113	Himenóptera	\N	\N	\N	\N	es
330	308	Gastropoda	2010-02-04	migracion	\N	\N	es
331	309	Opisthobranchia	2010-02-04	migracion	\N	\N	es
332	310	Manglar	2010-02-04	migracion	\N	\N	es
333	311	Gastrocelo	2010-02-04	migracion	\N	\N	es
334	312	Sacoglossa	2010-02-04	migracion	\N	\N	es
335	313	Prosobranchia	2010-02-04	migracion	\N	\N	es
336	314	Vista Dorsal	2010-02-04	migracion	\N	\N	es
338	316	Proboscide	2010-02-04	migracion	\N	\N	es
339	317	Nudibranchia	2010-02-04	migracion	\N	\N	es
337	315	Ascoglossa	2010-02-04	migracion	\N	\N	es
4	3	Editorial 	\N	\N	\N	\N	en
5	4	For Sale	\N	\N	\N	\N	en
6	5	Publication	\N	\N	\N	\N	en
8	1	Spanish	\N	\N	\N	\N	en
10	2	English	\N	\N	\N	\N	en
12	6	Images	\N	\N	\N	\N	en
16	8	Videos	\N	\N	\N	\N	en
14	7	Audio	\N	\N	\N	\N	en
18	9	Digital Still Camara	\N	\N	\N	\N	en
20	10	Scanned	\N	\N	\N	\N	en
22	11	Slide	\N	\N	\N	\N	en
47	35	Ipod Video	\N	\N	\N	\N	en
74	62	Beetle	\N	\N	\N	\N	en
85	71	Central America	\N	\N	\N	\N	en
143	128	Adult	\N	\N	\N	\N	en
150	133	Butterfly	\N	\N	\N	\N	en
151	134	Moth	\N	\N	\N	\N	en
168	149	Ground Beetles	\N	\N	\N	\N	en
169	145	Tree	\N	\N	\N	\N	en
310	289	File (MIME) Type	\N	\N	\N	\N	en
312	290	File Extension	\N	\N	\N	\N	en
340	318	Horned beetle	2010-02-04	migracion	\N	\N	en
341	319	Horn	2010-02-04	migracion	\N	\N	en
342	320	Flower beetle	2010-02-04	migracion	\N	\N	en
343	321	Katydid	2010-02-04	migracion	\N	\N	en
344	322	Crickets	2010-02-04	migracion	\N	\N	en
345	323	Cone-head	2010-02-04	migracion	\N	\N	en
346	324	Grasshopper	2010-02-04	migracion	\N	\N	en
347	325	Flies	2010-02-04	migracion	\N	\N	en
348	326	Deer Flies	2010-02-04	migracion	\N	\N	en
349	327	Horse flies	2010-02-04	migracion	\N	\N	en
350	328	White grub	2010-02-04	migracion	\N	\N	en
351	329	Dung Beetle	2010-02-04	migracion	\N	\N	en
352	330	Fruit Beetle	2010-02-04	migracion	\N	\N	en
353	331	Film Brand	2010-02-04	migracion	\N	\N	en
354	332	Scanning Date	2010-02-04	migracion	\N	\N	en
355	333	1:FujiChrome;2:FujiVelvia;3:KodakChrome;4:KodakEktachrome;5:FujiChrome;6:FujiSensia;28:Kodak	2010-02-04	migracion	\N	\N	en
356	334	Image Date	2010-02-04	migracion	\N	\N	en
156	133	Borboleta	\N	\N	\N	\N	pt
\.


--
-- Data for Name: use_policy; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY use_policy (use_policy_id, description_text_id, name_text_id, creation_date, created_by, last_modification_date, last_modification_by, name, description) FROM stdin;
1	\N	12	\N	\N	\N	\N	\N	\N
2	\N	13	\N	\N	\N	\N	\N	\N
3	\N	14	\N	\N	\N	\N	\N	\N
4	\N	15	\N	\N	\N	\N	\N	\N
5	\N	16	\N	\N	\N	\N	\N	\N
6	\N	17	\N	\N	\N	\N	\N	\N
7	\N	18	\N	\N	\N	\N	\N	\N
8	\N	19	\N	\N	\N	\N	\N	\N
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY "user" (user_id, name, password) FROM stdin;
1	administrador	inbiom3s
2	dsolano	botanica1
3	jgutierrez	inbiom3s
5	eulate	artropodos2
6	hesquivel	inbio1234
4	chernand	orophus
7	aherrera	colibri
8	dvargas	inbio08
9	jchaves	Jo5e01
10	ealvarado	ealvarado01
11	mmata	Turri2009
12	enavarro	dania91
13	fgonzalez	botanica2
14	jmontero	artropodos3
15	cviquez	artropodos4
16	mzumbado	artropodos5
17	msolis	artropodos6
18	asolis	artropodos7
19	cgranados	cristian
20	aavila	alejandra
21	consultorias	consultorias
33	eboza	eduardo
\.


--
-- Data for Name: user_project_privilege; Type: TABLE DATA; Schema: core; Owner: m3s
--

COPY user_project_privilege (project_id, privilege_id, user_name, creation_date, created_by, last_modification_date, last_modification_by) FROM stdin;
10	1	jchaves	2009-01-19	admin	\N	\N
10	3	jchaves	2009-01-19	admin	\N	\N
10	2	jchaves	2009-01-19	admin	\N	\N
11	1	ealvarado	2009-01-19	admin	\N	\N
11	2	ealvarado	2009-01-19	admin	\N	\N
11	3	ealvarado	2009-01-19	admin	\N	\N
12	1	mmata	2009-01-19	admin	\N	\N
12	2	mmata	2009-01-19	admin	\N	\N
12	3	mmata	2009-01-19	admin	\N	\N
13	1	enavarro	2009-01-19	admin	\N	\N
13	2	enavarro	2009-01-19	admin	\N	\N
13	3	enavarro	2009-01-19	admin	\N	\N
14	1	fgonzalez	2009-09-02	admin	\N	\N
14	2	fgonzalez	2009-09-02	admin	\N	\N
14	3	fgonzalez	2009-09-02	admin	\N	\N
3	1	fgonzalez	2009-09-02	admin	\N	\N
3	2	fgonzalez	2009-09-02	admin	\N	\N
4	3	chernand	2008-09-02	administrator	2008-09-02	administrator
3	1	dsolano	2007-10-16	administrator	2008-04-30	administrator
3	2	dsolano	2007-10-16	administrator	2008-04-30	administrator
3	3	dsolano	2007-10-16	administrator	2008-04-30	administrator
4	1	chernand	2008-09-02	administrator	2008-09-02	administrator
4	2	chernand	2008-09-02	administrator	2008-09-02	administrator
3	3	fgonzalez	2009-09-02	admin	\N	\N
15	1	chernand	2009-11-03	admin	2009-11-03	admin
15	2	chernand	2009-11-03	admin	2009-11-03	admin
15	3	chernand	2009-11-03	admin	2009-11-03	admin
15	1	eulate	2009-11-03	admin	2009-11-03	admin
15	2	eulate	2009-11-03	admin	2009-11-03	admin
15	3	eulate	2009-11-03	admin	2009-11-03	admin
3	1	eboza	\N	\N	\N	\N
3	2	eboza	\N	\N	\N	\N
3	3	eboza	\N	\N	\N	\N
5	1	eulate	2008-09-18	administrator	2008-09-18	administrator
5	2	eulate	2008-09-18	administrator	2008-09-18	administrator
5	3	eulate	2008-09-18	administrator	2008-09-18	administrator
5	1	chernand	2008-09-18	administrator	2008-09-18	administrator
5	2	chernand	2008-09-18	administrator	2008-09-18	administrator
5	3	chernand	2008-09-18	administrator	2008-09-18	administrator
6	1	hesquivel	2008-10-05	administrator	\N	\N
6	3	hesquivel	2008-10-05	administrator	\N	\N
6	2	hesquivel	2008-10-05	administrator	\N	\N
7	1	aherrera	2008-10-31	admin	\N	\N
7	3	aherrera	2008-10-31	admin	\N	\N
7	2	aherrera	2008-10-31	admin	\N	\N
8	1	aherrera	2008-11-05	admin	\N	\N
8	2	aherrera	2008-11-05	admin	\N	\N
8	3	aherrera	2008-11-05	admin	\N	\N
9	1	dvargas	2008-11-07	admin	\N	\N
9	2	dvargas	2008-11-07	admin	\N	\N
9	3	dvargas	2008-11-07	admin	\N	\N
16	1	aherrera	2010-05-13	admin	2010-05-13	admin
16	2	aherrera	2010-05-13	admin	2010-05-13	admin
16	3	aherrera	2010-05-13	admin	2010-05-13	admin
\.


--
-- PostgreSQL database dump complete
--

