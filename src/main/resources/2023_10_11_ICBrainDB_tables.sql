--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2023-11-10 16:11:28

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 16528)
-- Name: brain; Type: SCHEMA; Schema: -; Owner: savobraindb_admin
--

CREATE SCHEMA brain;


ALTER SCHEMA brain OWNER TO "savobraindb_admin";

SET default_tablespace = '';

SET default_table_access_method = "heap";

--
-- TOC entry 219 (class 1259 OID 24931)
-- Name: eeg_file; Type: TABLE; Schema: brain; Owner: savobraindb_admin
--

CREATE TABLE brain."eeg_file" (
    "id" integer NOT NULL,
    "human" character varying,
    "eeg_id" character varying NOT NULL,
    "description" character varying,
    "path" character varying NOT NULL,
    "filename" character varying NOT NULL
);


ALTER TABLE brain."eeg_file" OWNER TO "savobraindb_admin";

--
-- TOC entry 218 (class 1259 OID 24929)
-- Name: eeg_file_id_seq; Type: SEQUENCE; Schema: brain; Owner: savobraindb_admin
--

CREATE SEQUENCE brain."eeg_file_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brain."eeg_file_id_seq" OWNER TO "savobraindb_admin";

--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 218
-- Name: eeg_file_id_seq; Type: SEQUENCE OWNED BY; Schema: brain; Owner: savobraindb_admin
--

ALTER SEQUENCE brain."eeg_file_id_seq" OWNED BY brain."eeg_file"."id";


--
-- TOC entry 203 (class 1259 OID 16529)
-- Name: human; Type: TABLE; Schema: brain; Owner: savobraindb_admin
--

CREATE TABLE brain."human" (
    "age" integer,
    "human" character varying NOT NULL,
    "nationality" character varying DEFAULT ''::character varying,
    "r_city" character varying DEFAULT ''::character varying,
    "r_country" character varying(3) DEFAULT ''::character varying,
    "r_district" character varying(5) DEFAULT ''::character varying,
    "sex" integer NOT NULL,
    "ethnos" character varying,
    "comment" character varying DEFAULT ''::character varying,
    "nationality_en" character varying
);


ALTER TABLE brain."human" OWNER TO "savobraindb_admin";

--
-- TOC entry 205 (class 1259 OID 16539)
-- Name: mutation; Type: TABLE; Schema: brain; Owner: savobraindb_admin
--

CREATE TABLE brain."mutation" (
    "id" integer NOT NULL,
    "gene" character varying,
    "human" character varying NOT NULL,
    "chromosome" character varying,
    "position" bigint,
    "ref_nucl" character varying,
    "mutation" character varying,
    "type" integer
);


ALTER TABLE brain."mutation" OWNER TO "savobraindb_admin";

--
-- TOC entry 204 (class 1259 OID 16537)
-- Name: mutation_id_seq; Type: SEQUENCE; Schema: brain; Owner: savobraindb_admin
--

CREATE SEQUENCE brain."mutation_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brain."mutation_id_seq" OWNER TO "savobraindb_admin";

--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 204
-- Name: mutation_id_seq; Type: SEQUENCE OWNED BY; Schema: brain; Owner: savobraindb_admin
--

ALTER SEQUENCE brain."mutation_id_seq" OWNED BY brain."mutation"."id";


--
-- TOC entry 207 (class 1259 OID 16555)
-- Name: test; Type: TABLE; Schema: brain; Owner: savobraindb_admin
--

CREATE TABLE brain."test" (
    "test" integer NOT NULL,
    "name" character varying NOT NULL,
    "description" character varying NOT NULL,
    "alias" character varying,
    "name_en" character varying,
    "description_en" character varying
);


ALTER TABLE brain."test" OWNER TO "savobraindb_admin";

--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN "test"."alias"; Type: COMMENT; Schema: brain; Owner: savobraindb_admin
--

COMMENT ON COLUMN brain."test"."alias" IS 'English version';


--
-- TOC entry 206 (class 1259 OID 16553)
-- Name: test_test_seq; Type: SEQUENCE; Schema: brain; Owner: savobraindb_admin
--

CREATE SEQUENCE brain."test_test_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brain."test_test_seq" OWNER TO "savobraindb_admin";

--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 206
-- Name: test_test_seq; Type: SEQUENCE OWNED BY; Schema: brain; Owner: savobraindb_admin
--

ALTER SEQUENCE brain."test_test_seq" OWNED BY brain."test"."test";


--
-- TOC entry 211 (class 1259 OID 16577)
-- Name: testquestion; Type: TABLE; Schema: brain; Owner: savobraindb_admin
--

CREATE TABLE brain."testquestion" (
    "id" integer NOT NULL,
    "test" bigint NOT NULL,
    "question" character varying NOT NULL,
    "position" integer NOT NULL,
    "testresponsetype" character varying NOT NULL,
    "question_en" character varying
);


ALTER TABLE brain."testquestion" OWNER TO "savobraindb_admin";

--
-- TOC entry 210 (class 1259 OID 16575)
-- Name: testquestion_id_seq; Type: SEQUENCE; Schema: brain; Owner: savobraindb_admin
--

CREATE SEQUENCE brain."testquestion_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brain."testquestion_id_seq" OWNER TO "savobraindb_admin";

--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 210
-- Name: testquestion_id_seq; Type: SEQUENCE OWNED BY; Schema: brain; Owner: savobraindb_admin
--

ALTER SEQUENCE brain."testquestion_id_seq" OWNED BY brain."testquestion"."id";


--
-- TOC entry 209 (class 1259 OID 16566)
-- Name: testresponsetype; Type: TABLE; Schema: brain; Owner: savobraindb_admin
--

CREATE TABLE brain."testresponsetype" (
    "id" integer NOT NULL,
    "text" character varying NOT NULL,
    "value" integer NOT NULL,
    "position" integer NOT NULL,
    "testresponsetype" character varying NOT NULL,
    "text_en" character varying
);


ALTER TABLE brain."testresponsetype" OWNER TO "savobraindb_admin";

--
-- TOC entry 208 (class 1259 OID 16564)
-- Name: testresponsetype_id_seq; Type: SEQUENCE; Schema: brain; Owner: savobraindb_admin
--

CREATE SEQUENCE brain."testresponsetype_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brain."testresponsetype_id_seq" OWNER TO "savobraindb_admin";

--
-- TOC entry 2917 (class 0 OID 0)
-- Dependencies: 208
-- Name: testresponsetype_id_seq; Type: SEQUENCE OWNED BY; Schema: brain; Owner: savobraindb_admin
--

ALTER SEQUENCE brain."testresponsetype_id_seq" OWNED BY brain."testresponsetype"."id";


--
-- TOC entry 213 (class 1259 OID 16593)
-- Name: testresults; Type: TABLE; Schema: brain; Owner: savobraindb_admin
--

CREATE TABLE brain."testresults" (
    "id" integer NOT NULL,
    "testquestion" integer NOT NULL,
    "human" character varying NOT NULL,
    "value" integer NOT NULL
);


ALTER TABLE brain."testresults" OWNER TO "savobraindb_admin";

--
-- TOC entry 212 (class 1259 OID 16591)
-- Name: testresults_id_seq; Type: SEQUENCE; Schema: brain; Owner: savobraindb_admin
--

CREATE SEQUENCE brain."testresults_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brain."testresults_id_seq" OWNER TO "savobraindb_admin";

--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 212
-- Name: testresults_id_seq; Type: SEQUENCE OWNED BY; Schema: brain; Owner: savobraindb_admin
--

ALTER SEQUENCE brain."testresults_id_seq" OWNED BY brain."testresults"."id";


--
-- TOC entry 215 (class 1259 OID 16658)
-- Name: testsummary; Type: TABLE; Schema: brain; Owner: savobraindb_admin
--

CREATE TABLE brain."testsummary" (
    "id" integer NOT NULL,
    "human" character varying NOT NULL,
    "test" integer,
    "alias" character varying NOT NULL,
    "value" real,
    "description" "text"
);


ALTER TABLE brain."testsummary" OWNER TO "savobraindb_admin";

--
-- TOC entry 217 (class 1259 OID 24918)
-- Name: testsummary2test; Type: TABLE; Schema: brain; Owner: savobraindb_admin
--

CREATE TABLE brain."testsummary2test" (
    "id" integer NOT NULL,
    "test" integer NOT NULL,
    "testsammaryalias" character varying NOT NULL,
    "description" character varying,
    "description_en" character varying
);


ALTER TABLE brain."testsummary2test" OWNER TO "savobraindb_admin";

--
-- TOC entry 216 (class 1259 OID 24916)
-- Name: testsummary2test_id_seq; Type: SEQUENCE; Schema: brain; Owner: savobraindb_admin
--

CREATE SEQUENCE brain."testsummary2test_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brain."testsummary2test_id_seq" OWNER TO "savobraindb_admin";

--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 216
-- Name: testsummary2test_id_seq; Type: SEQUENCE OWNED BY; Schema: brain; Owner: savobraindb_admin
--

ALTER SEQUENCE brain."testsummary2test_id_seq" OWNED BY brain."testsummary2test"."id";


--
-- TOC entry 214 (class 1259 OID 16656)
-- Name: testsummary_id_seq; Type: SEQUENCE; Schema: brain; Owner: savobraindb_admin
--

CREATE SEQUENCE brain."testsummary_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brain."testsummary_id_seq" OWNER TO "savobraindb_admin";

--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 214
-- Name: testsummary_id_seq; Type: SEQUENCE OWNED BY; Schema: brain; Owner: savobraindb_admin
--

ALTER SEQUENCE brain."testsummary_id_seq" OWNED BY brain."testsummary"."id";


--
-- TOC entry 2755 (class 2604 OID 24934)
-- Name: eeg_file id; Type: DEFAULT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."eeg_file" ALTER COLUMN "id" SET DEFAULT "nextval"('"brain"."eeg_file_id_seq"'::"regclass");


--
-- TOC entry 2748 (class 2604 OID 16542)
-- Name: mutation id; Type: DEFAULT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."mutation" ALTER COLUMN "id" SET DEFAULT "nextval"('"brain"."mutation_id_seq"'::"regclass");


--
-- TOC entry 2749 (class 2604 OID 16558)
-- Name: test test; Type: DEFAULT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."test" ALTER COLUMN "test" SET DEFAULT "nextval"('"brain"."test_test_seq"'::"regclass");


--
-- TOC entry 2751 (class 2604 OID 16580)
-- Name: testquestion id; Type: DEFAULT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testquestion" ALTER COLUMN "id" SET DEFAULT "nextval"('"brain"."testquestion_id_seq"'::"regclass");


--
-- TOC entry 2750 (class 2604 OID 16569)
-- Name: testresponsetype id; Type: DEFAULT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testresponsetype" ALTER COLUMN "id" SET DEFAULT "nextval"('"brain"."testresponsetype_id_seq"'::"regclass");


--
-- TOC entry 2752 (class 2604 OID 16596)
-- Name: testresults id; Type: DEFAULT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testresults" ALTER COLUMN "id" SET DEFAULT "nextval"('"brain"."testresults_id_seq"'::"regclass");


--
-- TOC entry 2753 (class 2604 OID 16661)
-- Name: testsummary id; Type: DEFAULT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testsummary" ALTER COLUMN "id" SET DEFAULT "nextval"('"brain"."testsummary_id_seq"'::"regclass");


--
-- TOC entry 2754 (class 2604 OID 24921)
-- Name: testsummary2test id; Type: DEFAULT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testsummary2test" ALTER COLUMN "id" SET DEFAULT "nextval"('"brain"."testsummary2test_id_seq"'::"regclass");


--
-- TOC entry 2775 (class 2606 OID 24939)
-- Name: eeg_file eeg_file_pkey; Type: CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."eeg_file"
    ADD CONSTRAINT "eeg_file_pkey" PRIMARY KEY ("id");


--
-- TOC entry 2757 (class 2606 OID 16536)
-- Name: human human_pkey; Type: CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."human"
    ADD CONSTRAINT "human_pkey" PRIMARY KEY ("human");


--
-- TOC entry 2759 (class 2606 OID 16547)
-- Name: mutation mutation_pkey; Type: CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."mutation"
    ADD CONSTRAINT "mutation_pkey" PRIMARY KEY ("id");


--
-- TOC entry 2761 (class 2606 OID 16563)
-- Name: test test_pkey; Type: CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."test"
    ADD CONSTRAINT "test_pkey" PRIMARY KEY ("test");


--
-- TOC entry 2765 (class 2606 OID 16585)
-- Name: testquestion testquestion_pkey; Type: CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testquestion"
    ADD CONSTRAINT "testquestion_pkey" PRIMARY KEY ("id");


--
-- TOC entry 2763 (class 2606 OID 16574)
-- Name: testresponsetype testresponsetype_pkey; Type: CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testresponsetype"
    ADD CONSTRAINT "testresponsetype_pkey" PRIMARY KEY ("id");


--
-- TOC entry 2767 (class 2606 OID 16601)
-- Name: testresults testresults_pkey; Type: CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testresults"
    ADD CONSTRAINT "testresults_pkey" PRIMARY KEY ("id");


--
-- TOC entry 2771 (class 2606 OID 24926)
-- Name: testsummary2test testsummary2test_pkey; Type: CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testsummary2test"
    ADD CONSTRAINT "testsummary2test_pkey" PRIMARY KEY ("id");


--
-- TOC entry 2769 (class 2606 OID 16666)
-- Name: testsummary testsummary_pkey; Type: CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testsummary"
    ADD CONSTRAINT "testsummary_pkey" PRIMARY KEY ("id");


--
-- TOC entry 2773 (class 2606 OID 24928)
-- Name: testsummary2test uniquet2ts; Type: CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testsummary2test"
    ADD CONSTRAINT "uniquet2ts" UNIQUE ("test", "testsammaryalias");


--
-- TOC entry 2776 (class 2606 OID 16548)
-- Name: mutation mutation_fk; Type: FK CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."mutation"
    ADD CONSTRAINT "mutation_fk" FOREIGN KEY ("human") REFERENCES brain."human"("human");


--
-- TOC entry 2777 (class 2606 OID 16586)
-- Name: testquestion testq_fk; Type: FK CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testquestion"
    ADD CONSTRAINT "testq_fk" FOREIGN KEY ("test") REFERENCES brain."test"("test");


--
-- TOC entry 2778 (class 2606 OID 16602)
-- Name: testresults testres_fk; Type: FK CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testresults"
    ADD CONSTRAINT "testres_fk" FOREIGN KEY ("testquestion") REFERENCES brain."testquestion"("id");


--
-- TOC entry 2779 (class 2606 OID 16607)
-- Name: testresults testres_hfk; Type: FK CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testresults"
    ADD CONSTRAINT "testres_hfk" FOREIGN KEY ("human") REFERENCES brain."human"("human");


--
-- TOC entry 2780 (class 2606 OID 16667)
-- Name: testsummary toHumanFk; Type: FK CONSTRAINT; Schema: brain; Owner: savobraindb_admin
--

ALTER TABLE ONLY brain."testsummary"
    ADD CONSTRAINT "toHumanFk" FOREIGN KEY ("human") REFERENCES brain."human"("human");


-- Completed on 2023-11-10 16:11:29

--
-- PostgreSQL database dump complete
--

