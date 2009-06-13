--
-- PostgreSQL database dump
--

-- Started on 2009-06-12 18:13:52 CST

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1928 (class 1262 OID 20758)
-- Name: m3s; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE m3s WITH TEMPLATE = template0 ENCODING = 'UTF8';


\connect m3s

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 6 (class 2615 OID 20759)
-- Name: core; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA core;


--
-- TOC entry 1929 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA core; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA core IS 'tables of the m3s';


--
-- TOC entry 356 (class 2612 OID 20762)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: -
--

CREATE PROCEDURAL LANGUAGE plpgsql;


SET search_path = core, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1528 (class 1259 OID 20763)
-- Dependencies: 6
-- Name: gathering_media; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE gathering_media (
    media_id integer NOT NULL,
    gathering_detail_person_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20),
    gathering_number integer NOT NULL
);


--
-- TOC entry 1529 (class 1259 OID 20766)
-- Dependencies: 6
-- Name: import_control; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE import_control (
    system_file_name character varying(200) NOT NULL,
    status character varying NOT NULL,
    user_name character varying(16) NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20),
    user_file_name character varying(200) NOT NULL
);


--
-- TOC entry 1558 (class 1259 OID 27107)
-- Dependencies: 6
-- Name: institution; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE institution (
    institution_id integer NOT NULL,
    name character varying(80) NOT NULL,
    telephone character varying(30),
    fax character varying(30),
    street_address character varying(200),
    city character varying(25),
    state_province character varying(25),
    country character varying(25),
    acronym character varying(10),
    url character varying(100),
    logo_image_id integer
);


--
-- TOC entry 1530 (class 1259 OID 20772)
-- Dependencies: 6
-- Name: keyword; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE keyword (
    keyword_id integer NOT NULL,
    keyword_category_id integer NOT NULL,
    name_concept_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1531 (class 1259 OID 20775)
-- Dependencies: 6
-- Name: keyword_category; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE keyword_category (
    keyword_category_id integer NOT NULL,
    name_concept_id integer NOT NULL,
    description character varying(200),
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1532 (class 1259 OID 20778)
-- Dependencies: 6
-- Name: language; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE language (
    language_id integer NOT NULL,
    description_text_id integer,
    name_text_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20),
    name character varying(80) NOT NULL,
    description character varying(200)
);


--
-- TOC entry 1533 (class 1259 OID 20781)
-- Dependencies: 6
-- Name: media; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE media (
    media_id integer NOT NULL,
    media_type_id integer NOT NULL,
    use_policy_id integer NOT NULL,
    author_person_id integer,
    owner_person_id integer,
    owner_institution_id integer,
    description character varying(65535),
    location character varying(200),
    high_resolution_volume character varying(50),
    high_resolution_file_name character varying(50),
    site_id integer,
    site_description character varying(200),
    title character varying(200),
    series character varying(200),
    is_public character(1) NOT NULL,
    is_backup character(1) NOT NULL,
    old_image_file integer,
    old_image_id integer,
    contents character varying(100),
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20),
    media_date_year integer,
    media_date_month integer,
    media_date_day integer
);


--
-- TOC entry 1534 (class 1259 OID 20787)
-- Dependencies: 6
-- Name: media_attribute; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE media_attribute (
    media_attribute_id integer NOT NULL,
    description_text_id integer,
    name_text_id integer,
    media_attribute_table_name character varying(80),
    media_attribute_value_type character(1) NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20),
    media_attribute_type_predefined character(1) NOT NULL
);


--
-- TOC entry 1535 (class 1259 OID 20790)
-- Dependencies: 6
-- Name: media_attribute_type; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE media_attribute_type (
    media_type_id integer NOT NULL,
    media_attribute_id integer NOT NULL,
    metadata_standard_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20),
    standard_attribute_id integer NOT NULL
);


--
-- TOC entry 1536 (class 1259 OID 20793)
-- Dependencies: 6
-- Name: media_attribute_value; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE media_attribute_value (
    media_attribute_id integer NOT NULL,
    media_id integer NOT NULL,
    value_varchar character varying(200),
    value_id integer,
    value_number integer,
    value_date timestamp without time zone,
    created_by character varying(20),
    creation_date date,
    last_modification_by character varying(20),
    last_modification_date date
);


--
-- TOC entry 1537 (class 1259 OID 20796)
-- Dependencies: 6
-- Name: media_category; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE media_category (
    media_category_id integer NOT NULL,
    description_text_id integer,
    name_text_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1538 (class 1259 OID 20799)
-- Dependencies: 6
-- Name: media_keyword; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE media_keyword (
    media_id integer NOT NULL,
    keyword_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1539 (class 1259 OID 20802)
-- Dependencies: 6
-- Name: media_project; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE media_project (
    media_id integer NOT NULL,
    project_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1540 (class 1259 OID 20805)
-- Dependencies: 6
-- Name: media_type; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE media_type (
    media_type_id integer NOT NULL,
    description_text_id integer,
    name_text_id integer NOT NULL,
    media_category_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1541 (class 1259 OID 20808)
-- Dependencies: 6
-- Name: media_use; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE media_use (
    media_use_id integer NOT NULL,
    description_text_id integer,
    name_text_id integer NOT NULL,
    creation_date date,
    created_by character varying(45),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1542 (class 1259 OID 20811)
-- Dependencies: 6
-- Name: media_use_media; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE media_use_media (
    media_id integer NOT NULL,
    media_use_id integer NOT NULL,
    approved character(1) NOT NULL,
    approved_by_person_id integer,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1543 (class 1259 OID 20814)
-- Dependencies: 6
-- Name: metadata_standard; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE metadata_standard (
    metadata_standard_id integer NOT NULL,
    name character varying(80) NOT NULL,
    description character varying(200),
    metadata_standard_implementation_class character varying(80),
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1544 (class 1259 OID 20817)
-- Dependencies: 6
-- Name: observed_taxon_media; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE observed_taxon_media (
    observation_id integer NOT NULL,
    taxon_id integer,
    media_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1557 (class 1259 OID 27099)
-- Dependencies: 6
-- Name: person; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE person (
    person_id integer NOT NULL,
    first_name character varying(40) NOT NULL,
    last_name character varying(40) NOT NULL,
    initials character varying(20),
    birth_year numeric,
    death_year numeric,
    occupation character varying(200),
    telephone character varying(30),
    fax character varying(30),
    street_address character varying(200),
    city character varying(25),
    state_province character varying(25),
    country character varying(25),
    email character varying(50),
    url character varying(100),
    seccond_last_name character varying(40),
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1545 (class 1259 OID 20820)
-- Dependencies: 6
-- Name: privilege; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE privilege (
    privilege_id integer NOT NULL,
    name character varying(80) NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1546 (class 1259 OID 20823)
-- Dependencies: 6
-- Name: project; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE project (
    project_id integer NOT NULL,
    project_manager_name character varying(16) NOT NULL,
    name character varying(80) NOT NULL,
    description character varying(200),
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1547 (class 1259 OID 20826)
-- Dependencies: 6
-- Name: security_users; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE security_users (
    name character varying(16) NOT NULL,
    description character varying(32),
    priority real,
    user_type real,
    password character varying(20) NOT NULL,
    deleted character(1),
    system_group character(1),
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1548 (class 1259 OID 20829)
-- Dependencies: 6
-- Name: specimen_media; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE specimen_media (
    specimen_id integer NOT NULL,
    media_id integer NOT NULL,
    specimen_view_id integer,
    description character varying(200),
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1549 (class 1259 OID 20832)
-- Dependencies: 6
-- Name: specimen_view; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE specimen_view (
    specimen_view_id integer NOT NULL,
    name character varying(80) NOT NULL,
    description character varying(200),
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1550 (class 1259 OID 20835)
-- Dependencies: 6
-- Name: taxon_media; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE taxon_media (
    taxon_id integer NOT NULL,
    media_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1551 (class 1259 OID 20838)
-- Dependencies: 6
-- Name: text; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE text (
    text_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1552 (class 1259 OID 20841)
-- Dependencies: 6
-- Name: text_translation; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE text_translation (
    text_translation_id integer NOT NULL,
    text_id integer NOT NULL,
    language_id integer NOT NULL,
    name character varying(200) NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1553 (class 1259 OID 20844)
-- Dependencies: 6
-- Name: type_specimen_media; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE type_specimen_media (
    type_specimen_id integer NOT NULL,
    media_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1554 (class 1259 OID 20847)
-- Dependencies: 6
-- Name: use_policy; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE use_policy (
    use_policy_id integer NOT NULL,
    description_text_id integer,
    name_text_id integer NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20),
    name character varying(80),
    description character varying(80)
);


--
-- TOC entry 1555 (class 1259 OID 20850)
-- Dependencies: 6
-- Name: user; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE "user" (
    user_id integer NOT NULL,
    name character varying(20) NOT NULL,
    password character varying(20) NOT NULL
);


--
-- TOC entry 1934 (class 0 OID 0)
-- Dependencies: 1555
-- Name: COLUMN "user".name; Type: COMMENT; Schema: core; Owner: -
--

COMMENT ON COLUMN "user".name IS 'nombre de la cuenta de usuario';


--
-- TOC entry 1935 (class 0 OID 0)
-- Dependencies: 1555
-- Name: COLUMN "user".password; Type: COMMENT; Schema: core; Owner: -
--

COMMENT ON COLUMN "user".password IS 'Password del usuario. Este no debe guardarse en la tabla solo se requiere para la creaci?n del usuario. Es decir el procedimiento de la base de datos debe reemplazarlo por una hilera vac?a.';


--
-- TOC entry 1556 (class 1259 OID 20853)
-- Dependencies: 6
-- Name: user_project_privilege; Type: TABLE; Schema: core; Owner: -; Tablespace: 
--

CREATE TABLE user_project_privilege (
    project_id integer NOT NULL,
    privilege_id integer NOT NULL,
    user_name character varying(16) NOT NULL,
    creation_date date,
    created_by character varying(20),
    last_modification_date date,
    last_modification_by character varying(20)
);


--
-- TOC entry 1826 (class 2606 OID 20869)
-- Dependencies: 1528 1528 1528 1528
-- Name: gathering_media_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY gathering_media
    ADD CONSTRAINT gathering_media_pkey PRIMARY KEY (media_id, gathering_detail_person_id, gathering_number);


--
-- TOC entry 1828 (class 2606 OID 20871)
-- Dependencies: 1529 1529 1529
-- Name: import_control_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY import_control
    ADD CONSTRAINT import_control_pkey PRIMARY KEY (system_file_name, user_name);


--
-- TOC entry 1832 (class 2606 OID 20873)
-- Dependencies: 1531 1531
-- Name: keyword_category_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY keyword_category
    ADD CONSTRAINT keyword_category_pkey PRIMARY KEY (keyword_category_id);


--
-- TOC entry 1830 (class 2606 OID 20875)
-- Dependencies: 1530 1530
-- Name: keyword_id_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY keyword
    ADD CONSTRAINT keyword_id_pkey PRIMARY KEY (keyword_id);


--
-- TOC entry 1834 (class 2606 OID 20877)
-- Dependencies: 1532 1532
-- Name: language_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY language
    ADD CONSTRAINT language_pkey PRIMARY KEY (language_id);


--
-- TOC entry 1838 (class 2606 OID 20879)
-- Dependencies: 1534 1534
-- Name: media_attribute_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY media_attribute
    ADD CONSTRAINT media_attribute_pkey PRIMARY KEY (media_attribute_id);


--
-- TOC entry 1840 (class 2606 OID 20881)
-- Dependencies: 1535 1535 1535
-- Name: media_attribute_type_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY media_attribute_type
    ADD CONSTRAINT media_attribute_type_pkey PRIMARY KEY (media_type_id, media_attribute_id);


--
-- TOC entry 1842 (class 2606 OID 20883)
-- Dependencies: 1536 1536 1536
-- Name: media_attribute_value_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY media_attribute_value
    ADD CONSTRAINT media_attribute_value_pkey PRIMARY KEY (media_attribute_id, media_id);


--
-- TOC entry 1844 (class 2606 OID 20885)
-- Dependencies: 1537 1537
-- Name: media_category_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY media_category
    ADD CONSTRAINT media_category_pkey PRIMARY KEY (media_category_id);


--
-- TOC entry 1846 (class 2606 OID 20887)
-- Dependencies: 1538 1538 1538
-- Name: media_keyword_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY media_keyword
    ADD CONSTRAINT media_keyword_pkey PRIMARY KEY (media_id, keyword_id);


--
-- TOC entry 1836 (class 2606 OID 20889)
-- Dependencies: 1533 1533
-- Name: media_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY media
    ADD CONSTRAINT media_pkey PRIMARY KEY (media_id);


--
-- TOC entry 1848 (class 2606 OID 20891)
-- Dependencies: 1539 1539 1539
-- Name: media_project_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY media_project
    ADD CONSTRAINT media_project_pkey PRIMARY KEY (media_id, project_id);


--
-- TOC entry 1850 (class 2606 OID 20893)
-- Dependencies: 1540 1540
-- Name: media_type_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY media_type
    ADD CONSTRAINT media_type_pkey PRIMARY KEY (media_type_id);


--
-- TOC entry 1854 (class 2606 OID 20895)
-- Dependencies: 1542 1542 1542
-- Name: media_use_media_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY media_use_media
    ADD CONSTRAINT media_use_media_pkey PRIMARY KEY (media_id, media_use_id);


--
-- TOC entry 1852 (class 2606 OID 20897)
-- Dependencies: 1541 1541
-- Name: media_use_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY media_use
    ADD CONSTRAINT media_use_pkey PRIMARY KEY (media_use_id);


--
-- TOC entry 1856 (class 2606 OID 20899)
-- Dependencies: 1543 1543
-- Name: metadata_standard_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY metadata_standard
    ADD CONSTRAINT metadata_standard_pkey PRIMARY KEY (metadata_standard_id);


--
-- TOC entry 1862 (class 2606 OID 20901)
-- Dependencies: 1546 1546
-- Name: name_unique; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY project
    ADD CONSTRAINT name_unique UNIQUE (name);


--
-- TOC entry 1872 (class 2606 OID 20903)
-- Dependencies: 1550 1550 1550
-- Name: pk; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY taxon_media
    ADD CONSTRAINT pk PRIMARY KEY (taxon_id, media_id);


--
-- TOC entry 1870 (class 2606 OID 20905)
-- Dependencies: 1549 1549
-- Name: pk180_specimen_view_id; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY specimen_view
    ADD CONSTRAINT pk180_specimen_view_id PRIMARY KEY (specimen_view_id);


--
-- TOC entry 1888 (class 2606 OID 27114)
-- Dependencies: 1558 1558
-- Name: pk_institution; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY institution
    ADD CONSTRAINT pk_institution PRIMARY KEY (institution_id);


--
-- TOC entry 1858 (class 2606 OID 20907)
-- Dependencies: 1544 1544 1544
-- Name: pk_observed_taxon_media; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY observed_taxon_media
    ADD CONSTRAINT pk_observed_taxon_media PRIMARY KEY (observation_id, media_id);


--
-- TOC entry 1886 (class 2606 OID 27106)
-- Dependencies: 1557 1557
-- Name: pk_person; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT pk_person PRIMARY KEY (person_id);


--
-- TOC entry 1878 (class 2606 OID 20909)
-- Dependencies: 1553 1553 1553
-- Name: pk_type_specimen_media; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY type_specimen_media
    ADD CONSTRAINT pk_type_specimen_media PRIMARY KEY (type_specimen_id, media_id);


--
-- TOC entry 1860 (class 2606 OID 20911)
-- Dependencies: 1545 1545
-- Name: privilege_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY privilege
    ADD CONSTRAINT privilege_pkey PRIMARY KEY (privilege_id);


--
-- TOC entry 1864 (class 2606 OID 20913)
-- Dependencies: 1546 1546
-- Name: project_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY project
    ADD CONSTRAINT project_pkey PRIMARY KEY (project_id);


--
-- TOC entry 1866 (class 2606 OID 20915)
-- Dependencies: 1547 1547
-- Name: security_users_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY security_users
    ADD CONSTRAINT security_users_pkey PRIMARY KEY (name);


--
-- TOC entry 1868 (class 2606 OID 20917)
-- Dependencies: 1548 1548 1548
-- Name: specimen_media_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY specimen_media
    ADD CONSTRAINT specimen_media_pkey PRIMARY KEY (specimen_id, media_id);


--
-- TOC entry 1874 (class 2606 OID 20919)
-- Dependencies: 1551 1551
-- Name: text_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY text
    ADD CONSTRAINT text_pkey PRIMARY KEY (text_id);


--
-- TOC entry 1876 (class 2606 OID 20921)
-- Dependencies: 1552 1552
-- Name: text_translation_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY text_translation
    ADD CONSTRAINT text_translation_pkey PRIMARY KEY (text_translation_id);


--
-- TOC entry 1880 (class 2606 OID 20923)
-- Dependencies: 1554 1554
-- Name: use_policy_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY use_policy
    ADD CONSTRAINT use_policy_pkey PRIMARY KEY (use_policy_id);


--
-- TOC entry 1882 (class 2606 OID 20925)
-- Dependencies: 1555 1555
-- Name: user_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 1884 (class 2606 OID 20927)
-- Dependencies: 1556 1556 1556 1556
-- Name: user_project_privilege_pkey; Type: CONSTRAINT; Schema: core; Owner: -; Tablespace: 
--

ALTER TABLE ONLY user_project_privilege
    ADD CONSTRAINT user_project_privilege_pkey PRIMARY KEY (project_id, privilege_id, user_name);


--
-- TOC entry 1907 (class 2606 OID 20932)
-- Dependencies: 1539 1863 1546
-- Name: fk14d4e5af68284d2c; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_project
    ADD CONSTRAINT fk14d4e5af68284d2c FOREIGN KEY (project_id) REFERENCES project(project_id);


--
-- TOC entry 1922 (class 2606 OID 20937)
-- Dependencies: 1556 1863 1546
-- Name: fk1c30899768284d2c; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY user_project_privilege
    ADD CONSTRAINT fk1c30899768284d2c FOREIGN KEY (project_id) REFERENCES project(project_id);


--
-- TOC entry 1923 (class 2606 OID 20942)
-- Dependencies: 1859 1556 1545
-- Name: fk1c308997728a49ac; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY user_project_privilege
    ADD CONSTRAINT fk1c308997728a49ac FOREIGN KEY (privilege_id) REFERENCES privilege(privilege_id);


--
-- TOC entry 1924 (class 2606 OID 20947)
-- Dependencies: 1865 1556 1547
-- Name: fk1c308997cd68a5b9; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY user_project_privilege
    ADD CONSTRAINT fk1c308997cd68a5b9 FOREIGN KEY (user_name) REFERENCES security_users(name);


--
-- TOC entry 1908 (class 2606 OID 20952)
-- Dependencies: 1540 1551 1873
-- Name: fk232bec643632d814; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_type
    ADD CONSTRAINT fk232bec643632d814 FOREIGN KEY (name_text_id) REFERENCES text(text_id);


--
-- TOC entry 1909 (class 2606 OID 20957)
-- Dependencies: 1540 1551 1873
-- Name: fk232bec648750e085; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_type
    ADD CONSTRAINT fk232bec648750e085 FOREIGN KEY (description_text_id) REFERENCES text(text_id);


--
-- TOC entry 1910 (class 2606 OID 20962)
-- Dependencies: 1540 1537 1843
-- Name: fk232bec64b59d39b5; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_type
    ADD CONSTRAINT fk232bec64b59d39b5 FOREIGN KEY (media_category_id) REFERENCES media_category(media_category_id);


--
-- TOC entry 1895 (class 2606 OID 20967)
-- Dependencies: 1554 1533 1879
-- Name: fk37dc98d5a67121bb; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media
    ADD CONSTRAINT fk37dc98d5a67121bb FOREIGN KEY (use_policy_id) REFERENCES use_policy(use_policy_id);


--
-- TOC entry 1896 (class 2606 OID 20972)
-- Dependencies: 1849 1540 1533
-- Name: fk37dc98d5f632d5b5; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media
    ADD CONSTRAINT fk37dc98d5f632d5b5 FOREIGN KEY (media_type_id) REFERENCES media_type(media_type_id);


--
-- TOC entry 1920 (class 2606 OID 20977)
-- Dependencies: 1873 1554 1551
-- Name: fk528eac993632d814; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY use_policy
    ADD CONSTRAINT fk528eac993632d814 FOREIGN KEY (name_text_id) REFERENCES text(text_id);


--
-- TOC entry 1921 (class 2606 OID 20982)
-- Dependencies: 1551 1554 1873
-- Name: fk528eac998750e085; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY use_policy
    ADD CONSTRAINT fk528eac998750e085 FOREIGN KEY (description_text_id) REFERENCES text(text_id);


--
-- TOC entry 1911 (class 2606 OID 20987)
-- Dependencies: 1873 1541 1551
-- Name: fk53b71b1d3632d814; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_use
    ADD CONSTRAINT fk53b71b1d3632d814 FOREIGN KEY (name_text_id) REFERENCES text(text_id);


--
-- TOC entry 1912 (class 2606 OID 20992)
-- Dependencies: 1551 1541 1873
-- Name: fk53b71b1d8750e085; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_use
    ADD CONSTRAINT fk53b71b1d8750e085 FOREIGN KEY (description_text_id) REFERENCES text(text_id);


--
-- TOC entry 1913 (class 2606 OID 20997)
-- Dependencies: 1835 1533 1542
-- Name: fk5d176442638671cc; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_use_media
    ADD CONSTRAINT fk5d176442638671cc FOREIGN KEY (media_id) REFERENCES media(media_id);


--
-- TOC entry 1914 (class 2606 OID 21002)
-- Dependencies: 1541 1851 1542
-- Name: fk5d176442e84416bf; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_use_media
    ADD CONSTRAINT fk5d176442e84416bf FOREIGN KEY (media_use_id) REFERENCES media_use(media_use_id);


--
-- TOC entry 1916 (class 2606 OID 21007)
-- Dependencies: 1865 1546 1547
-- Name: fk6896fbca2a254e7d; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY project
    ADD CONSTRAINT fk6896fbca2a254e7d FOREIGN KEY (project_manager_name) REFERENCES security_users(name);


--
-- TOC entry 1897 (class 2606 OID 21012)
-- Dependencies: 1551 1873 1534
-- Name: fk694ff7b23632d814; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_attribute
    ADD CONSTRAINT fk694ff7b23632d814 FOREIGN KEY (name_text_id) REFERENCES text(text_id);


--
-- TOC entry 1898 (class 2606 OID 21017)
-- Dependencies: 1551 1873 1534
-- Name: fk694ff7b28750e085; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_attribute
    ADD CONSTRAINT fk694ff7b28750e085 FOREIGN KEY (description_text_id) REFERENCES text(text_id);


--
-- TOC entry 1893 (class 2606 OID 21022)
-- Dependencies: 1873 1551 1532
-- Name: fk85a1f8c73632d814; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY language
    ADD CONSTRAINT fk85a1f8c73632d814 FOREIGN KEY (name_text_id) REFERENCES text(text_id);


--
-- TOC entry 1894 (class 2606 OID 21027)
-- Dependencies: 1532 1551 1873
-- Name: fk85a1f8c78750e085; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY language
    ADD CONSTRAINT fk85a1f8c78750e085 FOREIGN KEY (description_text_id) REFERENCES text(text_id);


--
-- TOC entry 1902 (class 2606 OID 21032)
-- Dependencies: 1536 1837 1534
-- Name: fk8618dea44b7862df; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_attribute_value
    ADD CONSTRAINT fk8618dea44b7862df FOREIGN KEY (media_attribute_id) REFERENCES media_attribute(media_attribute_id);


--
-- TOC entry 1925 (class 2606 OID 27115)
-- Dependencies: 1558 1835 1533
-- Name: fk_institution1; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY institution
    ADD CONSTRAINT fk_institution1 FOREIGN KEY (logo_image_id) REFERENCES media(media_id);


--
-- TOC entry 1917 (class 2606 OID 21037)
-- Dependencies: 1833 1552 1532
-- Name: fka353a9f6a24f6e8; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY text_translation
    ADD CONSTRAINT fka353a9f6a24f6e8 FOREIGN KEY (language_id) REFERENCES language(language_id);


--
-- TOC entry 1918 (class 2606 OID 21042)
-- Dependencies: 1873 1551 1552
-- Name: fka353a9f6e6171c8; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY text_translation
    ADD CONSTRAINT fka353a9f6e6171c8 FOREIGN KEY (text_id) REFERENCES text(text_id);


--
-- TOC entry 1899 (class 2606 OID 21047)
-- Dependencies: 1855 1535 1543
-- Name: fkc2424fa7399fbe07; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_attribute_type
    ADD CONSTRAINT fkc2424fa7399fbe07 FOREIGN KEY (metadata_standard_id) REFERENCES metadata_standard(metadata_standard_id);


--
-- TOC entry 1900 (class 2606 OID 21052)
-- Dependencies: 1535 1837 1534
-- Name: fkc2424fa74b7862df; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_attribute_type
    ADD CONSTRAINT fkc2424fa74b7862df FOREIGN KEY (media_attribute_id) REFERENCES media_attribute(media_attribute_id);


--
-- TOC entry 1901 (class 2606 OID 21057)
-- Dependencies: 1535 1849 1540
-- Name: fkc2424fa7f632d5b5; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_attribute_type
    ADD CONSTRAINT fkc2424fa7f632d5b5 FOREIGN KEY (media_type_id) REFERENCES media_type(media_type_id);


--
-- TOC entry 1903 (class 2606 OID 21062)
-- Dependencies: 1537 1873 1551
-- Name: fkc45175283632d814; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_category
    ADD CONSTRAINT fkc45175283632d814 FOREIGN KEY (name_text_id) REFERENCES text(text_id);


--
-- TOC entry 1904 (class 2606 OID 21067)
-- Dependencies: 1537 1551 1873
-- Name: fkc45175288750e085; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_category
    ADD CONSTRAINT fkc45175288750e085 FOREIGN KEY (description_text_id) REFERENCES text(text_id);


--
-- TOC entry 1890 (class 2606 OID 21072)
-- Dependencies: 1530 1531 1831
-- Name: keyword_category; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY keyword
    ADD CONSTRAINT keyword_category FOREIGN KEY (keyword_category_id) REFERENCES keyword_category(keyword_category_id);


--
-- TOC entry 1892 (class 2606 OID 21077)
-- Dependencies: 1873 1531 1551
-- Name: keyword_category_text_fkey; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY keyword_category
    ADD CONSTRAINT keyword_category_text_fkey FOREIGN KEY (name_concept_id) REFERENCES text(text_id);


--
-- TOC entry 1891 (class 2606 OID 21082)
-- Dependencies: 1551 1873 1530
-- Name: keyword_concept; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY keyword
    ADD CONSTRAINT keyword_concept FOREIGN KEY (name_concept_id) REFERENCES text(text_id);


--
-- TOC entry 1905 (class 2606 OID 21087)
-- Dependencies: 1829 1538 1530
-- Name: media_category_fk2; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_keyword
    ADD CONSTRAINT media_category_fk2 FOREIGN KEY (keyword_id) REFERENCES keyword(keyword_id);


--
-- TOC entry 1919 (class 2606 OID 21092)
-- Dependencies: 1533 1553 1835
-- Name: media_fk; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY type_specimen_media
    ADD CONSTRAINT media_fk FOREIGN KEY (media_id) REFERENCES media(media_id) MATCH FULL;


--
-- TOC entry 1906 (class 2606 OID 21097)
-- Dependencies: 1533 1835 1538
-- Name: media_keyword_fk1; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY media_keyword
    ADD CONSTRAINT media_keyword_fk1 FOREIGN KEY (media_id) REFERENCES media(media_id);


--
-- TOC entry 1889 (class 2606 OID 21102)
-- Dependencies: 1547 1529 1865
-- Name: name; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY import_control
    ADD CONSTRAINT name FOREIGN KEY (user_name) REFERENCES security_users(name);


--
-- TOC entry 1915 (class 2606 OID 21107)
-- Dependencies: 1835 1544 1533
-- Name: observedTaxon_media_fk; Type: FK CONSTRAINT; Schema: core; Owner: -
--

ALTER TABLE ONLY observed_taxon_media
    ADD CONSTRAINT "observedTaxon_media_fk" FOREIGN KEY (media_id) REFERENCES media(media_id);


--
-- TOC entry 1930 (class 0 OID 0)
-- Dependencies: 6
-- Name: core; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA core FROM PUBLIC;
REVOKE ALL ON SCHEMA core FROM miniatta;
GRANT ALL ON SCHEMA core TO PUBLIC;


--
-- TOC entry 1932 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 1933 (class 0 OID 0)
-- Dependencies: 1551
-- Name: text; Type: ACL; Schema: core; Owner: -
--

REVOKE ALL ON TABLE text FROM PUBLIC;
REVOKE ALL ON TABLE text FROM miniatta;
GRANT ALL ON TABLE text TO miniatta;
GRANT ALL ON TABLE text TO PUBLIC;


-- Completed on 2009-06-12 18:13:53 CST

--
-- PostgreSQL database dump complete
--

