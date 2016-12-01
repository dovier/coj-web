--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: varchar_ci; Type: DOMAIN; Schema: public; Owner: -
--

CREATE DOMAIN varchar_ci AS character varying(255) NOT NULL DEFAULT ''::character varying;


--
-- Name: _varchar_ci_equal(varchar_ci, varchar_ci); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION _varchar_ci_equal(varchar_ci, varchar_ci) RETURNS boolean
    LANGUAGE sql STRICT
    AS $_$SELECT LOWER($1) = LOWER($2)$_$;


--
-- Name: _varchar_ci_greater_equals(varchar_ci, varchar_ci); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION _varchar_ci_greater_equals(varchar_ci, varchar_ci) RETURNS boolean
    LANGUAGE sql STRICT
    AS $_$SELECT LOWER($1) >= LOWER($2)$_$;


--
-- Name: _varchar_ci_greater_than(varchar_ci, varchar_ci); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION _varchar_ci_greater_than(varchar_ci, varchar_ci) RETURNS boolean
    LANGUAGE sql STRICT
    AS $_$SELECT LOWER($1) > LOWER($2)$_$;


--
-- Name: _varchar_ci_less_equal(varchar_ci, varchar_ci); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION _varchar_ci_less_equal(varchar_ci, varchar_ci) RETURNS boolean
    LANGUAGE sql STRICT
    AS $_$SELECT LOWER($1) <= LOWER($2)$_$;


--
-- Name: _varchar_ci_less_than(varchar_ci, varchar_ci); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION _varchar_ci_less_than(varchar_ci, varchar_ci) RETURNS boolean
    LANGUAGE sql STRICT
    AS $_$SELECT LOWER($1) < LOWER($2)$_$;


--
-- Name: _varchar_ci_not_equal(varchar_ci, varchar_ci); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION _varchar_ci_not_equal(varchar_ci, varchar_ci) RETURNS boolean
    LANGUAGE sql STRICT
    AS $_$SELECT LOWER($1) != LOWER($2)$_$;


--
-- Name: problemclassfn(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION problemclassfn() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
DELETE FROM plagicoj_result WHERE id_source_submission IN (SELECT submit_id FROM submition WHERE pid = OLD.pid);
DELETE FROM plagicoj_result WHERE id_destination_submission IN (SELECT submit_id FROM submition WHERE pid = OLD.pid);
RETURN NEW;
END;
$$;


--
-- Name: problemclassinsfn(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION problemclassinsfn() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
DELETE FROM plagicoj_result WHERE id_source_submission IN (SELECT submit_id FROM submition WHERE pid = NEW.pid);
DELETE FROM plagicoj_result WHERE id_destination_submission IN (SELECT submit_id FROM submition WHERE pid = NEW.pid);
RETURN NEW;
END;
$$;


--
-- Name: problemclasstrigfn(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION problemclasstrigfn() RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
DELETE FROM plagicoj_result WHERE id_source_submission IN (SELECT submit_id FROM submition WHERE pid = OLD.pid);
DELETE FROM plagicoj_result WHERE id_destination_submission IN (SELECT submit_id FROM submition WHERE pid = OLD.pid);
END;
$$;


--
-- Name: update_classification_estimated_code_length(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION update_classification_estimated_code_length() RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE
  curClassification RECORD;
BEGIN
UPDATE classifications SET estimated_code_length = -1;
FOR curClassification IN EXECUTE 
	'select * from classifications'
LOOP
	UPDATE classifications SET estimated_code_length = ABS(COALESCE((
    SELECT AVG(T.lengthcode - 	
		(SELECT COALESCE(SUM(estimated_code_length),0) from classifications JOIN (SELECT id_classification FROM problem_classification WHERE pid = T.pid AND id_classification <> curClassification.id_classification ) R ON R.id_classification = classifications.id_classification) 
	)
FROM (SELECT LENGTH(code) AS lengthcode,pid FROM submition JOIN source ON sid = submit_id 
	WHERE pid IN
		(
			SELECT DISTINCT pid FROM problem_classification 
			WHERE
				pid IN (SELECT pid FROM problem_classification WHERE id_classification = curClassification.id_classification)
			AND
				pid NOT IN
				(SELECT pid FROM problem_classification WHERE id_classification <> curClassification.id_classification 
				AND id_classification in ( Select id_classification from classifications where estimated_code_length = -1 ) )
		)
	) T 
     ),0)) where classifications.id_classification = curClassification.id_classification;	
END LOOP;
END;
$$;


--
-- Name: <; Type: OPERATOR; Schema: public; Owner: -
--

CREATE OPERATOR < (
    PROCEDURE = _varchar_ci_less_than,
    LEFTARG = varchar_ci,
    RIGHTARG = varchar_ci,
    COMMUTATOR = >,
    NEGATOR = >=,
    RESTRICT = scalarltsel,
    JOIN = scalarltjoinsel
);


--
-- Name: <=; Type: OPERATOR; Schema: public; Owner: -
--

CREATE OPERATOR <= (
    PROCEDURE = _varchar_ci_less_equal,
    LEFTARG = varchar_ci,
    RIGHTARG = varchar_ci,
    COMMUTATOR = >=,
    NEGATOR = >,
    RESTRICT = scalarltsel,
    JOIN = scalarltjoinsel
);


--
-- Name: <>; Type: OPERATOR; Schema: public; Owner: -
--

CREATE OPERATOR <> (
    PROCEDURE = _varchar_ci_not_equal,
    LEFTARG = varchar_ci,
    RIGHTARG = varchar_ci,
    COMMUTATOR = <>,
    NEGATOR = =,
    RESTRICT = neqsel,
    JOIN = neqjoinsel
);


--
-- Name: =; Type: OPERATOR; Schema: public; Owner: -
--

CREATE OPERATOR = (
    PROCEDURE = _varchar_ci_equal,
    LEFTARG = varchar_ci,
    RIGHTARG = varchar_ci,
    COMMUTATOR = =,
    NEGATOR = <>,
    MERGES,
    HASHES,
    RESTRICT = eqsel,
    JOIN = eqjoinsel
);


--
-- Name: >; Type: OPERATOR; Schema: public; Owner: -
--

CREATE OPERATOR > (
    PROCEDURE = _varchar_ci_greater_than,
    LEFTARG = varchar_ci,
    RIGHTARG = varchar_ci,
    COMMUTATOR = <,
    NEGATOR = <=,
    RESTRICT = scalargtsel,
    JOIN = scalargtjoinsel
);


--
-- Name: >=; Type: OPERATOR; Schema: public; Owner: -
--

CREATE OPERATOR >= (
    PROCEDURE = _varchar_ci_greater_equals,
    LEFTARG = varchar_ci,
    RIGHTARG = varchar_ci,
    COMMUTATOR = <=,
    NEGATOR = <,
    RESTRICT = scalargtsel,
    JOIN = scalargtjoinsel
);


SET default_with_oids = false;

--
-- Name: account_activation; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE account_activation (
    act_id integer NOT NULL,
    username character varying NOT NULL,
    user_key character varying NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    registration boolean DEFAULT true NOT NULL
);


--
-- Name: account_activation_act_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE account_activation_act_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: account_activation_act_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE account_activation_act_id_seq OWNED BY account_activation.act_id;


--
-- Name: achievements; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE achievements (
    aid integer NOT NULL,
    name character varying(200),
    description character varying(300),
    condition character varying(300),
    legend character varying DEFAULT ''::character varying NOT NULL,
    enabled boolean DEFAULT true NOT NULL
);


--
-- Name: admin_clarification; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE admin_clarification (
    id_clarification integer NOT NULL,
    reply boolean DEFAULT false NOT NULL
);


--
-- Name: admin_log; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE admin_log (
    id integer NOT NULL,
    log text,
    log_date timestamp without time zone DEFAULT now() NOT NULL,
    username character varying NOT NULL
);


--
-- Name: admin_log_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE admin_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: admin_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE admin_log_id_seq OWNED BY admin_log.id;


--
-- Name: announcements; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE announcements (
    aid integer NOT NULL,
    content character varying NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    username character varying NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    contest_id integer DEFAULT 0 NOT NULL
);


--
-- Name: announcements_aid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE announcements_aid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: announcements_aid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE announcements_aid_seq OWNED BY announcements.aid;


--
-- Name: authorities; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE authorities (
    username character varying NOT NULL,
    authority character varying NOT NULL,
    id integer NOT NULL
);


--
-- Name: authorities_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE authorities_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: authorities_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE authorities_id_seq OWNED BY authorities.id;


--
-- Name: balloontrackers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE balloontrackers (
    uid integer NOT NULL,
    cid integer NOT NULL
);


--
-- Name: clarification; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE clarification (
    id_team bigint,
    title character varying(100),
    text character varying(100000) NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    publicclarification boolean DEFAULT false NOT NULL,
    is_read boolean DEFAULT false NOT NULL,
    allusers boolean DEFAULT false NOT NULL,
    id_clarification integer NOT NULL,
    id_contest bigint NOT NULL,
    pid integer NOT NULL,
    general boolean DEFAULT false NOT NULL,
    reply boolean DEFAULT false NOT NULL,
    request boolean DEFAULT false NOT NULL
);


--
-- Name: clarification_id_clarification_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE clarification_id_clarification_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: clarification_id_clarification_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE clarification_id_clarification_seq OWNED BY clarification.id_clarification;


--
-- Name: classifications; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE classifications (
    id_classification integer NOT NULL,
    name text NOT NULL,
    estimated_code_length integer DEFAULT 0,
    aid integer DEFAULT 0 NOT NULL
);


--
-- Name: classifications_id_classification_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE classifications_id_classification_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: classifications_id_classification_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE classifications_id_classification_seq OWNED BY classifications.id_classification;


--
-- Name: cojmail_log; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE cojmail_log (
    cm_id integer NOT NULL,
    usuario character varying NOT NULL,
    request character varying(10000) NOT NULL,
    date timestamp without time zone NOT NULL
);


--
-- Name: cojmail_log_cm_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE cojmail_log_cm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: cojmail_log_cm_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE cojmail_log_cm_id_seq OWNED BY cojmail_log.cm_id;


--
-- Name: contest_cid_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE contest_cid_sequence
    START WITH 1430
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: contest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE contest (
    cid integer DEFAULT nextval('contest_cid_sequence'::regclass) NOT NULL,
    name character varying NOT NULL,
    initdate timestamp without time zone DEFAULT now() NOT NULL,
    enddate timestamp without time zone DEFAULT now() NOT NULL,
    private boolean DEFAULT false NOT NULL,
    total_users integer DEFAULT 0 NOT NULL,
    style integer DEFAULT 0 NOT NULL,
    status character varying DEFAULT 'Schedule'::character varying NOT NULL,
    deadtime integer DEFAULT 0 NOT NULL,
    frtime integer DEFAULT 0 NOT NULL,
    block boolean DEFAULT true NOT NULL,
    penalty integer DEFAULT 20 NOT NULL,
    enabled boolean DEFAULT false NOT NULL,
    creation_time timestamp without time zone DEFAULT now() NOT NULL,
    rglimit timestamp without time zone DEFAULT now() NOT NULL,
    registration integer DEFAULT 0 NOT NULL,
    ioimark integer DEFAULT 100 NOT NULL,
    ppoints integer DEFAULT 1000 NOT NULL,
    unfreeze_time integer DEFAULT 0 NOT NULL,
    contestant integer DEFAULT 1 NOT NULL,
    gold integer DEFAULT 1 NOT NULL,
    silver integer DEFAULT 1 NOT NULL,
    bronze integer DEFAULT 1 NOT NULL,
    virtual_template boolean DEFAULT false NOT NULL,
    levels integer DEFAULT 0 NOT NULL,
    accepted_by_level integer DEFAULT 1 NOT NULL,
    accepted_limit integer DEFAULT 1 NOT NULL,
    repointing boolean DEFAULT false NOT NULL,
    guest_group character varying(150),
    grouped boolean DEFAULT false NOT NULL,
    balloon boolean DEFAULT false NOT NULL,
    event_started_processed boolean DEFAULT false NOT NULL,
    event_ended_processed boolean DEFAULT false NOT NULL,
    event_created_processed boolean DEFAULT false NOT NULL,
    event_processed boolean DEFAULT false NOT NULL,
    template integer DEFAULT 0 NOT NULL,
    uid integer DEFAULT 550 NOT NULL,
    virtual boolean DEFAULT false NOT NULL,
    enabled_submission boolean DEFAULT true NOT NULL,
    show_scoreboard boolean DEFAULT true NOT NULL,
    show_status_out boolean DEFAULT true NOT NULL,
    allow_registration boolean DEFAULT true NOT NULL,
    unfreeze_auto boolean DEFAULT true NOT NULL,
    show_scoreboard_out boolean DEFAULT true NOT NULL,
    show_status boolean DEFAULT true NOT NULL,
    show_problem_out boolean DEFAULT false NOT NULL,
    show_ontest boolean DEFAULT true NOT NULL,
    enabled_mail boolean DEFAULT true NOT NULL,
    enabled_source_code_view boolean DEFAULT true NOT NULL,
    overview character varying,
    saris boolean DEFAULT false NOT NULL,
    show_stats boolean DEFAULT true NOT NULL,
    show_stats_out boolean DEFAULT true NOT NULL,
    gallery boolean DEFAULT true NOT NULL
);


--
-- Name: contest_awards; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE contest_awards (
    aid integer NOT NULL,
    cid integer NOT NULL
);


--
-- Name: special_awards; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE special_awards (
    aid integer NOT NULL,
    name character varying(100),
    description character varying(300)
);


--
-- Name: contest_awards_aid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE contest_awards_aid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: contest_awards_aid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE contest_awards_aid_seq OWNED BY special_awards.aid;


--
-- Name: contest_awards_users_aid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE contest_awards_users_aid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: contest_awards_users_aid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE contest_awards_users_aid_seq OWNED BY contest_awards.aid;


--
-- Name: contest_brief; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE contest_brief (
    cid integer NOT NULL,
    overview character varying,
    id integer NOT NULL
);


--
-- Name: contest_brief_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE contest_brief_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: contest_brief_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE contest_brief_id_seq OWNED BY contest_brief.id;


--
-- Name: contest_dataset_verdict; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE contest_dataset_verdict (
    submit_id integer NOT NULL,
    testnum integer NOT NULL,
    cid integer NOT NULL,
    message character varying,
    status character varying NOT NULL,
    "userTime" bigint,
    "cpuTime" bigint,
    memory bigint
);


--
-- Name: contest_flags; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE contest_flags (
    cid integer NOT NULL,
    show_scoreboard boolean DEFAULT true NOT NULL,
    show_status_out boolean DEFAULT true NOT NULL,
    cfid integer NOT NULL,
    allow_registration boolean DEFAULT true NOT NULL,
    unfreeze_auto boolean DEFAULT true NOT NULL,
    show_scoreboard_out boolean DEFAULT true NOT NULL,
    show_status boolean DEFAULT true NOT NULL,
    show_problem_out boolean DEFAULT false NOT NULL,
    show_ontest boolean DEFAULT true NOT NULL,
    enabled_mail boolean DEFAULT true NOT NULL,
    enabled_source_code_view boolean DEFAULT true NOT NULL,
    enabled_submission boolean DEFAULT true NOT NULL,
    show_stats boolean DEFAULT true NOT NULL,
    show_stats_out boolean DEFAULT true NOT NULL,
    saris boolean DEFAULT false NOT NULL
);


--
-- Name: contest_flags_cfid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE contest_flags_cfid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: contest_flags_cfid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE contest_flags_cfid_seq OWNED BY contest_flags.cfid;


--
-- Name: contest_judges; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE contest_judges (
    cid integer NOT NULL,
    id_admin integer NOT NULL
);


--
-- Name: contest_registration; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE contest_registration (
    rid integer NOT NULL,
    registration character varying NOT NULL
);


--
-- Name: contest_registration_rid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE contest_registration_rid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: contest_registration_rid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE contest_registration_rid_seq OWNED BY contest_registration.rid;


--
-- Name: contest_source; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE contest_source (
    sid integer NOT NULL,
    code text,
    error text
);


--
-- Name: contest_style; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE contest_style (
    sid integer NOT NULL,
    style_name character varying NOT NULL,
    enabled boolean DEFAULT false NOT NULL
);


--
-- Name: contest_style_sid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE contest_style_sid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: contest_style_sid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE contest_style_sid_seq OWNED BY contest_style.sid;


--
-- Name: contest_submition; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE contest_submition (
    submit_id integer NOT NULL,
    uid integer NOT NULL,
    pid integer NOT NULL,
    cid integer NOT NULL,
    status character varying NOT NULL,
    language character varying NOT NULL,
    "time" double precision DEFAULT 0,
    memory bigint DEFAULT 0,
    fontsize integer DEFAULT 0 NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    virtual boolean DEFAULT false NOT NULL,
    username character varying,
    enabled boolean DEFAULT true NOT NULL,
    testcase integer DEFAULT 1 NOT NULL,
    min_case integer DEFAULT 0 NOT NULL,
    max_case integer DEFAULT 0 NOT NULL,
    average_case integer DEFAULT 0 NOT NULL,
    balloon_notification boolean DEFAULT false NOT NULL,
    cpu_time integer DEFAULT 0 NOT NULL,
    ac_cases integer DEFAULT 0 NOT NULL,
    accepted boolean DEFAULT false NOT NULL,
    total_test_cases integer DEFAULT 0
);


--
-- Name: contest_submition_submit_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE contest_submition_submit_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: contest_submition_submit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE contest_submition_submit_id_seq OWNED BY contest_submition.submit_id;


--
-- Name: contributions; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE contributions (
    uid integer,
    cid integer
);


--
-- Name: country; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE country (
    zip character varying DEFAULT 'CU'::character varying NOT NULL,
    name character varying DEFAULT 'CUBA'::character varying NOT NULL,
    country_id integer NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    zip_two character varying DEFAULT ''::character varying NOT NULL,
    website character varying DEFAULT 'http://'::character varying NOT NULL
);


--
-- Name: country_country_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE country_country_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: country_country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE country_country_id_seq OWNED BY country.country_id;


--
-- Name: dataset_verdict_json; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE dataset_verdict_json (
    sid integer NOT NULL,
    json_data character varying
);


--
-- Name: datasets; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE datasets (
    id integer NOT NULL,
    problem_id integer DEFAULT 0 NOT NULL,
    input text DEFAULT ''::text NOT NULL,
    output text DEFAULT ''::text NOT NULL
);


--
-- Name: datasets_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE datasets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: datasets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE datasets_id_seq OWNED BY datasets.id;


--
-- Name: draft; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE draft (
    draft_id integer NOT NULL,
    username character varying NOT NULL,
    content text NOT NULL,
    title character varying,
    date timestamp without time zone DEFAULT now() NOT NULL,
    size integer DEFAULT 0 NOT NULL
);


--
-- Name: draft_draft_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE draft_draft_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: draft_draft_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE draft_draft_id_seq OWNED BY draft.draft_id;


--
-- Name: entries_eid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE entries_eid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: entries; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE entries (
    uid integer,
    date timestamp(6) without time zone,
    eid integer DEFAULT nextval('entries_eid_seq'::regclass) NOT NULL,
    rate integer DEFAULT 0 NOT NULL,
    admin_enabled boolean DEFAULT false NOT NULL,
    text text,
    reply boolean DEFAULT false NOT NULL,
    forward boolean DEFAULT false NOT NULL,
    has_links boolean DEFAULT false NOT NULL,
    has_users boolean DEFAULT false NOT NULL
);


--
-- Name: entry_rate; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE entry_rate (
    uid integer NOT NULL,
    eid integer NOT NULL
);


--
-- Name: events; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE events (
    type integer,
    date timestamp without time zone,
    args text,
    id integer NOT NULL,
    enabled boolean DEFAULT true NOT NULL
);


--
-- Name: events_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE events_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: events_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE events_id_seq OWNED BY events.id;


--
-- Name: problem; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE problem (
    pid integer NOT NULL,
    title character varying NOT NULL,
    description text,
    input text,
    output text,
    input_example text,
    output_example text NOT NULL,
    author character varying,
    comments character varying,
    point double precision DEFAULT 5 NOT NULL,
    "time" integer NOT NULL,
    memory integer NOT NULL,
    fontsize integer NOT NULL,
    contest integer DEFAULT 0 NOT NULL,
    date character varying DEFAULT ('now'::text)::date NOT NULL,
    abb character varying DEFAULT 'abb'::character varying NOT NULL,
    uid integer DEFAULT 1 NOT NULL,
    enable boolean DEFAULT true NOT NULL,
    case_time_limit integer DEFAULT 0 NOT NULL,
    multidata boolean DEFAULT false NOT NULL,
    disable_24h boolean DEFAULT true NOT NULL,
    special_judge boolean DEFAULT false NOT NULL,
    comments_es character varying,
    comments_pt character varying,
    description_pt character varying,
    description_es character varying,
    title_pt character varying,
    title_es character varying,
    input_es character varying,
    input_pt character varying,
    output_pt character varying,
    output_es character varying,
    event_created_processed boolean DEFAULT false NOT NULL,
    forum_link character varying(500),
    id_source integer
);


--
-- Name: exercise_exercice_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE exercise_exercice_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: exercise_exercice_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE exercise_exercice_id_seq OWNED BY problem.pid;


--
-- Name: faq; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE faq (
    answer text,
    question text,
    id integer NOT NULL
);


--
-- Name: faq_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE faq_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: faq_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE faq_id_seq OWNED BY faq.id;


--
-- Name: followers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE followers (
    uid integer NOT NULL,
    fid integer NOT NULL
);


--
-- Name: global_access_rules; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE global_access_rules (
    rid bigint NOT NULL,
    rule character varying DEFAULT '*'::character varying NOT NULL
);


--
-- Name: global_access_rules_rid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE global_access_rules_rid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: global_access_rules_rid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE global_access_rules_rid_seq OWNED BY global_access_rules.rid;


--
-- Name: global_flags; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE global_flags (
    enabled_source_code_view boolean DEFAULT true NOT NULL,
    access_rules character varying DEFAULT '*'::character varying NOT NULL,
    global_id integer NOT NULL,
    enabled_submission boolean DEFAULT true NOT NULL,
    maintenance_mode boolean DEFAULT false NOT NULL,
    mail_notification_disabled boolean DEFAULT false NOT NULL,
    enabled_mail boolean DEFAULT false NOT NULL
);


--
-- Name: global_flags_global_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE global_flags_global_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: global_flags_global_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE global_flags_global_id_seq OWNED BY global_flags.global_id;


--
-- Name: group; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "group" (
    id integer NOT NULL,
    description character varying NOT NULL
);


--
-- Name: group_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE group_id_seq OWNED BY "group".id;


--
-- Name: individual_virtual_contest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE individual_virtual_contest (
    vid bigint NOT NULL,
    username character varying NOT NULL,
    cid integer NOT NULL,
    creation_time timestamp without time zone DEFAULT now() NOT NULL,
    start_time timestamp without time zone NOT NULL,
    end_time timestamp without time zone DEFAULT now() NOT NULL,
    father integer DEFAULT 0 NOT NULL,
    is_public boolean DEFAULT false NOT NULL
);


--
-- Name: individual_virtual_contest_vid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE individual_virtual_contest_vid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: individual_virtual_contest_vid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE individual_virtual_contest_vid_seq OWNED BY individual_virtual_contest.vid;


--
-- Name: institution; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE institution (
    zip character varying DEFAULT 'UCI'::character varying NOT NULL,
    name character varying DEFAULT 'Universidad de las Ciencias Informáticas'::character varying NOT NULL,
    country_id integer NOT NULL,
    inst_id integer NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    website character varying DEFAULT 'http://'::character varying NOT NULL
);


--
-- Name: institution_inst_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE institution_inst_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: institution_inst_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE institution_inst_id_seq OWNED BY institution.inst_id;


--
-- Name: internal_mail; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE internal_mail (
    title character varying,
    text character varying NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    is_read boolean DEFAULT false NOT NULL,
    idmail integer NOT NULL,
    idfrom character varying NOT NULL,
    idto character varying NOT NULL,
    list character varying DEFAULT ''::character varying NOT NULL,
    size integer DEFAULT 0 NOT NULL,
    enabled boolean DEFAULT true NOT NULL
);


--
-- Name: internal_mail_idmail_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE internal_mail_idmail_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: internal_mail_idmail_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE internal_mail_idmail_seq OWNED BY internal_mail.idmail;


--
-- Name: language; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE language (
    language character varying,
    name_bin character varying,
    lid integer NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    descripcion character varying,
    key character varying DEFAULT 'C'::character varying NOT NULL,
    ext character varying DEFAULT 'none'::character varying NOT NULL,
    aid integer,
    priority integer,
    compiler_command character varying
);


--
-- Name: language_contest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE language_contest (
    lid integer NOT NULL,
    cid integer NOT NULL
);


--
-- Name: language_stats; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE language_stats (
    lid integer NOT NULL,
    ac integer DEFAULT 0 NOT NULL,
    wa integer DEFAULT 0 NOT NULL,
    ce integer DEFAULT 0 NOT NULL,
    rte integer DEFAULT 0 NOT NULL,
    tle integer DEFAULT 0 NOT NULL,
    mle integer DEFAULT 0 NOT NULL,
    fle integer DEFAULT 0 NOT NULL,
    ole integer DEFAULT 0 NOT NULL,
    pe integer DEFAULT 0 NOT NULL,
    sv integer DEFAULT 0 NOT NULL,
    uq integer DEFAULT 0 NOT NULL,
    ivf integer DEFAULT 0 NOT NULL
);


--
-- Name: language_stats_contest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE language_stats_contest (
    lid integer NOT NULL,
    ac integer DEFAULT 0 NOT NULL,
    wa integer DEFAULT 0 NOT NULL,
    ce integer DEFAULT 0 NOT NULL,
    rte integer DEFAULT 0 NOT NULL,
    tle integer DEFAULT 0 NOT NULL,
    mle integer DEFAULT 0 NOT NULL,
    fle integer DEFAULT 0 NOT NULL,
    ole integer DEFAULT 0 NOT NULL,
    pe integer DEFAULT 0 NOT NULL,
    sv integer DEFAULT 0 NOT NULL,
    uq integer DEFAULT 0 NOT NULL,
    cid integer NOT NULL,
    ivf integer DEFAULT 0 NOT NULL
);


--
-- Name: language_virtual_stats; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE language_virtual_stats (
    lid integer NOT NULL,
    ac integer DEFAULT 0 NOT NULL,
    wa integer DEFAULT 0 NOT NULL,
    ce integer DEFAULT 0 NOT NULL,
    rte integer DEFAULT 0 NOT NULL,
    tle integer DEFAULT 0 NOT NULL,
    mle integer DEFAULT 0 NOT NULL,
    fle integer DEFAULT 0 NOT NULL,
    ole integer DEFAULT 0 NOT NULL,
    pe integer DEFAULT 0 NOT NULL,
    sv integer DEFAULT 0 NOT NULL,
    uq integer DEFAULT 0 NOT NULL,
    ivf integer DEFAULT 0 NOT NULL
);


--
-- Name: languages_lid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE languages_lid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: languages_lid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE languages_lid_seq OWNED BY language.lid;


--
-- Name: locale; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE locale (
    lid integer NOT NULL,
    description character varying NOT NULL,
    i18n character varying NOT NULL
);


--
-- Name: locale_lid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE locale_lid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: locale_lid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE locale_lid_seq OWNED BY locale.lid;


--
-- Name: news; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE news (
    title character varying NOT NULL,
    description text NOT NULL,
    content text NOT NULL,
    uid integer NOT NULL,
    nid integer NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL
);


--
-- Name: news_nid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE news_nid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: news_nid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE news_nid_seq OWNED BY news.nid;


--
-- Name: plagicoj_result; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE plagicoj_result (
    id_source_submission integer NOT NULL,
    id_destination_submission integer NOT NULL,
    dictum double precision
);


--
-- Name: plagicoj_result_judge_revision; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE plagicoj_result_judge_revision (
    evaluation integer,
    comment text,
    id_source_submission integer,
    id_destination_submission integer,
    id_plagicoj_result_judge_revision integer NOT NULL,
    date timestamp without time zone DEFAULT now(),
    id_user character varying(250),
    detector_type integer DEFAULT (-1) NOT NULL
);


--
-- Name: plagicoj_result_judge_revisio_id_plagicoj_result_judge_revi_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE plagicoj_result_judge_revisio_id_plagicoj_result_judge_revi_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: plagicoj_result_judge_revisio_id_plagicoj_result_judge_revi_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE plagicoj_result_judge_revisio_id_plagicoj_result_judge_revi_seq OWNED BY plagicoj_result_judge_revision.id_plagicoj_result_judge_revision;


--
-- Name: poll; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE poll (
    pid integer NOT NULL,
    question text,
    answer1 text,
    answer2 text,
    answer3 text,
    answer4 text,
    answer5 text,
    enabled boolean DEFAULT false NOT NULL
);


--
-- Name: poll_answer; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE poll_answer (
    aid integer NOT NULL,
    pid integer,
    uid integer,
    option integer
);


--
-- Name: poll_answer_aid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE poll_answer_aid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: poll_answer_aid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE poll_answer_aid_seq OWNED BY poll_answer.aid;


--
-- Name: poll_pid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE poll_pid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: poll_pid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE poll_pid_seq OWNED BY poll.pid;


--
-- Name: preference_profile; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE preference_profile (
    uid integer,
    date timestamp without time zone DEFAULT now(),
    valorations character varying
);


--
-- Name: problem_classification; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE problem_classification (
    id_classification bigint NOT NULL,
    pid bigint NOT NULL,
    complexity integer DEFAULT 3 NOT NULL
);


--
-- Name: problem_contest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE problem_contest (
    pid integer NOT NULL,
    cid integer NOT NULL,
    ac integer DEFAULT 0 NOT NULL,
    wa integer DEFAULT 0 NOT NULL,
    pe integer DEFAULT 0 NOT NULL,
    ce integer DEFAULT 0 NOT NULL,
    rte integer DEFAULT 0 NOT NULL,
    mle integer DEFAULT 0 NOT NULL,
    tle integer DEFAULT 0 NOT NULL,
    fle integer DEFAULT 0 NOT NULL,
    ole integer DEFAULT 0 NOT NULL,
    uq integer DEFAULT 0 NOT NULL,
    ivf integer DEFAULT 0 NOT NULL,
    accu integer DEFAULT 0 NOT NULL,
    level integer DEFAULT 1 NOT NULL,
    release_date timestamp without time zone DEFAULT now() NOT NULL,
    orderp integer DEFAULT 1 NOT NULL,
    color character varying(7) DEFAULT '#FFFFFF'::character varying NOT NULL
);


--
-- Name: problem_id_source_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE problem_id_source_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: problem_language; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE problem_language (
    pid integer NOT NULL,
    lid integer NOT NULL
);


--
-- Name: problem_limits; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE problem_limits (
    problem_id bigint NOT NULL,
    language_id bigint NOT NULL,
    max_case_execution_time bigint,
    max_total_execution_time bigint,
    max_memory bigint,
    max_source_code_lenght bigint
);


--
-- Name: problem_source; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE problem_source (
    name character varying,
    id_source integer DEFAULT nextval('problem_id_source_seq'::regclass) NOT NULL,
    author character varying
);


--
-- Name: problem_stats; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE problem_stats (
    pid integer NOT NULL,
    ac integer DEFAULT 0 NOT NULL,
    wa integer DEFAULT 0 NOT NULL,
    ce integer DEFAULT 0 NOT NULL,
    rte integer DEFAULT 0 NOT NULL,
    tle integer DEFAULT 0 NOT NULL,
    mle integer DEFAULT 0 NOT NULL,
    fle integer DEFAULT 0 NOT NULL,
    ole integer DEFAULT 0 NOT NULL,
    pe integer DEFAULT 0 NOT NULL,
    sv integer DEFAULT 0 NOT NULL,
    uq integer DEFAULT 0 NOT NULL,
    accu integer DEFAULT 0 NOT NULL,
    stats_id integer NOT NULL,
    ivf integer DEFAULT 0 NOT NULL
);


--
-- Name: problem_stats_contest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE problem_stats_contest (
    pid integer NOT NULL,
    ac integer DEFAULT 0 NOT NULL,
    wa integer DEFAULT 0 NOT NULL,
    ce integer DEFAULT 0 NOT NULL,
    rte integer DEFAULT 0 NOT NULL,
    tle integer DEFAULT 0 NOT NULL,
    mle integer DEFAULT 0 NOT NULL,
    fle integer DEFAULT 0 NOT NULL,
    ole integer DEFAULT 0 NOT NULL,
    pe integer DEFAULT 0 NOT NULL,
    sv integer DEFAULT 0 NOT NULL,
    uq integer DEFAULT 0 NOT NULL,
    cid integer NOT NULL,
    ivf integer DEFAULT 0 NOT NULL,
    accu integer DEFAULT 0 NOT NULL
);


--
-- Name: problem_stats_stats_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE problem_stats_stats_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: problem_stats_stats_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE problem_stats_stats_id_seq OWNED BY problem_stats.stats_id;


--
-- Name: psetter_problem; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE psetter_problem (
    user_id integer NOT NULL,
    problem_id integer NOT NULL
);


--
-- Name: recommendation_profile; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE recommendation_profile (
    uid integer,
    recommendation character varying,
    date timestamp without time zone DEFAULT now() NOT NULL
);


--
-- Name: roles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE roles (
    rid integer NOT NULL,
    role character varying DEFAULT 'ROLE_USER'::character varying NOT NULL
);


--
-- Name: roles_rid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE roles_rid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: roles_rid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE roles_rid_seq OWNED BY roles.rid;


--
-- Name: send_mail; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE send_mail (
    title character varying,
    text character varying NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    idmail integer NOT NULL,
    username character varying NOT NULL,
    size integer DEFAULT 0 NOT NULL,
    send_to character varying NOT NULL
);


--
-- Name: send_mail_idmail_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE send_mail_idmail_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: send_mail_idmail_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE send_mail_idmail_seq OWNED BY send_mail.idmail;


--
-- Name: shared_file_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE shared_file_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: shared_files; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE shared_files (
    fid integer DEFAULT nextval('shared_file_seq'::regclass) NOT NULL,
    path text,
    name character varying(300),
    size bigint
);


--
-- Name: source; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE source (
    sid integer NOT NULL,
    code text,
    error text
);


--
-- Name: submition; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE submition (
    uid integer NOT NULL,
    pid integer NOT NULL,
    status character varying NOT NULL,
    language character varying NOT NULL,
    "time" integer DEFAULT 0,
    memory bigint DEFAULT 0,
    fontsize integer DEFAULT 0 NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    submit_id integer NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    username character varying NOT NULL,
    testcase integer DEFAULT 0 NOT NULL,
    max_case integer DEFAULT 0 NOT NULL,
    min_case integer DEFAULT 0 NOT NULL,
    average_case integer DEFAULT 0 NOT NULL,
    lock boolean DEFAULT false NOT NULL,
    rejudge_status integer DEFAULT 0 NOT NULL,
    course_id integer DEFAULT 0 NOT NULL,
    event_processed boolean DEFAULT false,
    cpu_time integer DEFAULT 0 NOT NULL,
    ac_tests integer DEFAULT 0 NOT NULL,
    judge_flag integer DEFAULT 0 NOT NULL,
    ac_cases integer DEFAULT 0 NOT NULL,
    accepted boolean DEFAULT false NOT NULL,
    first_ac boolean DEFAULT false NOT NULL
);


--
-- Name: submition_submit_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE submition_submit_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: submition_submit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE submition_submit_id_seq OWNED BY submition.submit_id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE users (
    uid integer NOT NULL,
    username character varying NOT NULL,
    password character varying,
    lang character varying DEFAULT 'C'::character varying NOT NULL,
    enabled boolean DEFAULT false NOT NULL,
    country_id integer DEFAULT 1 NOT NULL,
    inst_id integer DEFAULT 1 NOT NULL,
    nick character varying DEFAULT 'new'::character varying NOT NULL,
    locale integer DEFAULT 1 NOT NULL,
    rg_date timestamp without time zone DEFAULT now() NOT NULL,
    registration_date timestamp without time zone DEFAULT now() NOT NULL,
    email_notifications boolean DEFAULT false NOT NULL,
    lid integer DEFAULT 2 NOT NULL,
    gender integer DEFAULT 1 NOT NULL,
    online boolean DEFAULT false NOT NULL,
    show_email boolean DEFAULT false NOT NULL,
    last_ip character varying DEFAULT 'none'::character varying NOT NULL,
    access_rule character varying DEFAULT '*'::character varying NOT NULL,
    last_connected_date timestamp without time zone,
    see_solutions boolean DEFAULT false NOT NULL,
    change_time timestamp without time zone DEFAULT now() NOT NULL,
    enableadveditor boolean DEFAULT true NOT NULL,
    update_nick boolean DEFAULT true NOT NULL,
    tags character varying,
    contest_notifications boolean DEFAULT false NOT NULL,
    entries_notifications boolean DEFAULT false NOT NULL,
    submition_notifications boolean DEFAULT false NOT NULL,
    problem_notifications boolean DEFAULT false NOT NULL,
    role character varying DEFAULT 'ROLE_USER'::character varying NOT NULL,
    status character varying DEFAULT 'not active'::character varying NOT NULL,
    wboard_notifications boolean DEFAULT false NOT NULL,
    newprivatemessage_notifications boolean DEFAULT false,
    ban_reason text,
    ban_date timestamp without time zone
);


--
-- Name: tbl_user_user_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tbl_user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: tbl_user_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tbl_user_user_id_seq OWNED BY users.uid;


--
-- Name: team_profile; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE team_profile (
    uid integer NOT NULL,
    user_1 character varying DEFAULT 'none'::character varying NOT NULL,
    user_2 character varying DEFAULT 'none'::character varying NOT NULL,
    user_3 character varying DEFAULT 'none'::character varying NOT NULL,
    coach character varying DEFAULT 'none'::character varying NOT NULL
);


--
-- Name: tmp; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tmp (
    sid integer,
    code text
);


--
-- Name: translation_pending_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE translation_pending_id_seq
    START WITH 40
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: translation_pending; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE translation_pending (
    id integer DEFAULT nextval('translation_pending_id_seq'::regclass) NOT NULL,
    username character varying NOT NULL,
    date timestamp without time zone NOT NULL,
    title character varying NOT NULL,
    description character varying NOT NULL,
    input character varying NOT NULL,
    output character varying NOT NULL,
    locale character varying,
    pid integer,
    comments character varying
);


--
-- Name: user_achievement; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_achievement (
    aid integer NOT NULL,
    uid integer NOT NULL,
    awarded boolean DEFAULT false NOT NULL,
    count integer DEFAULT 0,
    level integer DEFAULT 0 NOT NULL
);


--
-- Name: user_clarification; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_clarification (
    id_clarification integer NOT NULL,
    teamfor integer NOT NULL,
    ucid integer NOT NULL,
    read boolean DEFAULT false NOT NULL
);


--
-- Name: user_clarification_ucid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_clarification_ucid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_clarification_ucid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE user_clarification_ucid_seq OWNED BY user_clarification.ucid;


--
-- Name: user_comments; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_comments (
    uid integer,
    eid integer,
    date timestamp(6) without time zone,
    content text,
    cid integer NOT NULL,
    enabled boolean DEFAULT true
);


--
-- Name: user_comments_cid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_comments_cid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_comments_cid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE user_comments_cid_seq OWNED BY user_comments.cid;


--
-- Name: user_contest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_contest (
    uid integer NOT NULL,
    cid integer NOT NULL,
    accepted integer DEFAULT 0 NOT NULL,
    penalty integer DEFAULT 0 NOT NULL,
    lastacc timestamp without time zone DEFAULT now() NOT NULL,
    ioimark real DEFAULT 0 NOT NULL,
    points double precision DEFAULT 0 NOT NULL,
    virtual boolean DEFAULT false NOT NULL,
    a_time integer DEFAULT 100000000 NOT NULL,
    a_beforeac integer DEFAULT 0 NOT NULL,
    a_afterac integer DEFAULT 0 NOT NULL,
    b_time integer DEFAULT 100000000 NOT NULL,
    b_beforeac integer DEFAULT 0 NOT NULL,
    b_afterac integer DEFAULT 0 NOT NULL,
    c_time integer DEFAULT 100000000 NOT NULL,
    c_beforeac integer DEFAULT 0 NOT NULL,
    c_afterac integer DEFAULT 0 NOT NULL,
    d_time integer DEFAULT 100000000 NOT NULL,
    d_beforeac integer DEFAULT 0 NOT NULL,
    d_afterac integer DEFAULT 0 NOT NULL,
    e_time integer DEFAULT 100000000 NOT NULL,
    e_beforeac integer DEFAULT 0 NOT NULL,
    e_afterac integer DEFAULT 0 NOT NULL,
    f_time integer DEFAULT 100000000 NOT NULL,
    f_beforeac integer DEFAULT 0 NOT NULL,
    f_afterac integer DEFAULT 0 NOT NULL,
    g_time integer DEFAULT 100000000 NOT NULL,
    g_beforeac integer DEFAULT 0 NOT NULL,
    g_afterac integer DEFAULT 0 NOT NULL,
    h_time integer DEFAULT 100000000 NOT NULL,
    h_beforeac integer DEFAULT 0 NOT NULL,
    h_afterac integer DEFAULT 0 NOT NULL,
    i_time integer DEFAULT 100000000 NOT NULL,
    i_beforeac integer DEFAULT 0 NOT NULL,
    i_afterac integer DEFAULT 0 NOT NULL,
    j_time integer DEFAULT 100000000 NOT NULL,
    j_beforeac integer DEFAULT 0 NOT NULL,
    j_afterac integer DEFAULT 0 NOT NULL,
    k_time integer DEFAULT 100000000 NOT NULL,
    k_beforeac integer DEFAULT 0 NOT NULL,
    k_afterac integer DEFAULT 0 NOT NULL,
    l_time integer DEFAULT 100000000 NOT NULL,
    l_beforeac integer DEFAULT 0 NOT NULL,
    l_afterac integer DEFAULT 0 NOT NULL,
    a_pending integer DEFAULT 0 NOT NULL,
    b_pending integer DEFAULT 0 NOT NULL,
    c_pending integer DEFAULT 0 NOT NULL,
    d_pending integer DEFAULT 0 NOT NULL,
    e_pending integer DEFAULT 0 NOT NULL,
    f_pending integer DEFAULT 0 NOT NULL,
    g_pending integer DEFAULT 0 NOT NULL,
    h_pending integer DEFAULT 0 NOT NULL,
    i_pending integer DEFAULT 0 NOT NULL,
    j_pending integer DEFAULT 0 NOT NULL,
    k_pending integer DEFAULT 0 NOT NULL,
    l_pending integer DEFAULT 0 NOT NULL,
    current_level integer DEFAULT 1 NOT NULL,
    groupd character varying DEFAULT 'participant'::character varying NOT NULL,
    grade character varying DEFAULT '...'::character varying
);


--
-- Name: user_problem; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_problem (
    user_id integer NOT NULL,
    problem_id integer NOT NULL,
    lock boolean DEFAULT false NOT NULL,
    accepted boolean DEFAULT false NOT NULL
);


--
-- Name: user_problem_favorite; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_problem_favorite (
    user_id integer NOT NULL,
    problem_id integer NOT NULL
);


--
-- Name: user_problem_tmp; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_problem_tmp (
    user_id integer NOT NULL,
    problem_id integer NOT NULL,
    lock boolean DEFAULT false NOT NULL,
    accepted boolean DEFAULT false NOT NULL
);


--
-- Name: user_profile; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_profile (
    uid integer,
    fullname character varying,
    lastname character varying,
    email character varying,
    points double precision DEFAULT 0 NOT NULL,
    upid integer NOT NULL,
    mail_quote integer DEFAULT 400000 NOT NULL,
    consume_quote integer DEFAULT 0 NOT NULL,
    dob timestamp without time zone DEFAULT now() NOT NULL,
    show_dob boolean DEFAULT true NOT NULL,
    view_problem_info boolean DEFAULT false NOT NULL,
    passcode character(30)
);


--
-- Name: user_profile_upid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_profile_upid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_profile_upid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE user_profile_upid_seq OWNED BY user_profile.upid;


--
-- Name: user_stats; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_stats (
    uid integer NOT NULL,
    ac integer DEFAULT 0 NOT NULL,
    wa integer DEFAULT 0 NOT NULL,
    ce integer DEFAULT 0 NOT NULL,
    rte integer DEFAULT 0 NOT NULL,
    tle integer DEFAULT 0 NOT NULL,
    mle integer DEFAULT 0 NOT NULL,
    fle integer DEFAULT 0 NOT NULL,
    ole integer DEFAULT 0 NOT NULL,
    pe integer DEFAULT 0 NOT NULL,
    sv integer DEFAULT 0 NOT NULL,
    last_submission timestamp without time zone,
    last_accepted timestamp without time zone DEFAULT now(),
    stats_id integer NOT NULL,
    uq integer DEFAULT 0 NOT NULL,
    ivf integer DEFAULT 0 NOT NULL,
    uaccu integer DEFAULT 0 NOT NULL
);


--
-- Name: user_stats_contest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_stats_contest (
    uid integer NOT NULL,
    ac integer DEFAULT 0 NOT NULL,
    wa integer DEFAULT 0 NOT NULL,
    ce integer DEFAULT 0 NOT NULL,
    rte integer DEFAULT 0 NOT NULL,
    tle integer DEFAULT 0 NOT NULL,
    mle integer DEFAULT 0 NOT NULL,
    fle integer DEFAULT 0 NOT NULL,
    ole integer DEFAULT 0 NOT NULL,
    pe integer DEFAULT 0 NOT NULL,
    sv integer,
    cid integer NOT NULL,
    uq integer DEFAULT 0 NOT NULL,
    ivf integer DEFAULT 0 NOT NULL
);


--
-- Name: user_stats_stats_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_stats_stats_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_stats_stats_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE user_stats_stats_id_seq OWNED BY user_stats.stats_id;


--
-- Name: virtual_contest_guest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE virtual_contest_guest (
    vid integer NOT NULL,
    username character varying NOT NULL
);


--
-- Name: virtual_contest_problem; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE virtual_contest_problem (
    vid integer NOT NULL,
    pid integer NOT NULL
);


--
-- Name: votes; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE votes (
    uid integer,
    pid integer,
    vote integer
);


--
-- Name: votes_stats; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE votes_stats (
    pid integer,
    vote1_cant double precision DEFAULT 0,
    vote2_cant double precision DEFAULT 0,
    vote3_cant double precision DEFAULT 0,
    vote4_cant double precision DEFAULT 0,
    vote5_cant double precision DEFAULT 0,
    vote1_sum double precision DEFAULT 0,
    vote2_sum double precision DEFAULT 0,
    vote3_sum double precision DEFAULT 0,
    vote4_sum double precision DEFAULT 0,
    vote5_sum double precision DEFAULT 0
);


--
-- Name: wboard_contest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE wboard_contest (
    id integer NOT NULL,
    url character varying NOT NULL,
    name character varying NOT NULL,
    sid integer NOT NULL,
    startdate timestamp without time zone NOT NULL,
    enddate timestamp without time zone NOT NULL,
    notifcreated boolean DEFAULT true,
    notifchanged boolean DEFAULT false,
    notifnearcontest boolean DEFAULT true NOT NULL
);


--
-- Name: wboard_contest_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE wboard_contest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: wboard_contest_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE wboard_contest_id_seq OWNED BY wboard_contest.id;


--
-- Name: wboard_site; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE wboard_site (
    sid integer NOT NULL,
    site character varying NOT NULL,
    url character varying NOT NULL,
    code character varying NOT NULL,
    completed boolean NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    timeanddateid integer DEFAULT 99 NOT NULL,
    timezone character varying(50) DEFAULT 'America/Havana'::character varying NOT NULL
);


--
-- Name: wboard_site_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE wboard_site_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: wboard_site_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE wboard_site_id_seq OWNED BY wboard_site.sid;


--
-- Name: wboard_user_site; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE wboard_user_site (
    uid integer NOT NULL,
    sid integer NOT NULL
);


--
-- Name: act_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY account_activation ALTER COLUMN act_id SET DEFAULT nextval('account_activation_act_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY admin_log ALTER COLUMN id SET DEFAULT nextval('admin_log_id_seq'::regclass);


--
-- Name: aid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY announcements ALTER COLUMN aid SET DEFAULT nextval('announcements_aid_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY authorities ALTER COLUMN id SET DEFAULT nextval('authorities_id_seq'::regclass);


--
-- Name: id_clarification; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY clarification ALTER COLUMN id_clarification SET DEFAULT nextval('clarification_id_clarification_seq'::regclass);


--
-- Name: id_classification; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY classifications ALTER COLUMN id_classification SET DEFAULT nextval('classifications_id_classification_seq'::regclass);


--
-- Name: cm_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY cojmail_log ALTER COLUMN cm_id SET DEFAULT nextval('cojmail_log_cm_id_seq'::regclass);


--
-- Name: aid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_awards ALTER COLUMN aid SET DEFAULT nextval('contest_awards_users_aid_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_brief ALTER COLUMN id SET DEFAULT nextval('contest_brief_id_seq'::regclass);


--
-- Name: cfid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_flags ALTER COLUMN cfid SET DEFAULT nextval('contest_flags_cfid_seq'::regclass);


--
-- Name: rid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_registration ALTER COLUMN rid SET DEFAULT nextval('contest_registration_rid_seq'::regclass);


--
-- Name: sid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_style ALTER COLUMN sid SET DEFAULT nextval('contest_style_sid_seq'::regclass);


--
-- Name: submit_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_submition ALTER COLUMN submit_id SET DEFAULT nextval('contest_submition_submit_id_seq'::regclass);


--
-- Name: country_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY country ALTER COLUMN country_id SET DEFAULT nextval('country_country_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY datasets ALTER COLUMN id SET DEFAULT nextval('datasets_id_seq'::regclass);


--
-- Name: draft_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY draft ALTER COLUMN draft_id SET DEFAULT nextval('draft_draft_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY events ALTER COLUMN id SET DEFAULT nextval('events_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY faq ALTER COLUMN id SET DEFAULT nextval('faq_id_seq'::regclass);


--
-- Name: rid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY global_access_rules ALTER COLUMN rid SET DEFAULT nextval('global_access_rules_rid_seq'::regclass);


--
-- Name: global_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY global_flags ALTER COLUMN global_id SET DEFAULT nextval('global_flags_global_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "group" ALTER COLUMN id SET DEFAULT nextval('group_id_seq'::regclass);


--
-- Name: vid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY individual_virtual_contest ALTER COLUMN vid SET DEFAULT nextval('individual_virtual_contest_vid_seq'::regclass);


--
-- Name: inst_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY institution ALTER COLUMN inst_id SET DEFAULT nextval('institution_inst_id_seq'::regclass);


--
-- Name: idmail; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY internal_mail ALTER COLUMN idmail SET DEFAULT nextval('internal_mail_idmail_seq'::regclass);


--
-- Name: lid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY language ALTER COLUMN lid SET DEFAULT nextval('languages_lid_seq'::regclass);


--
-- Name: lid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY locale ALTER COLUMN lid SET DEFAULT nextval('locale_lid_seq'::regclass);


--
-- Name: nid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY news ALTER COLUMN nid SET DEFAULT nextval('news_nid_seq'::regclass);


--
-- Name: id_plagicoj_result_judge_revision; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY plagicoj_result_judge_revision ALTER COLUMN id_plagicoj_result_judge_revision SET DEFAULT nextval('plagicoj_result_judge_revisio_id_plagicoj_result_judge_revi_seq'::regclass);


--
-- Name: pid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY poll ALTER COLUMN pid SET DEFAULT nextval('poll_pid_seq'::regclass);


--
-- Name: aid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY poll_answer ALTER COLUMN aid SET DEFAULT nextval('poll_answer_aid_seq'::regclass);


--
-- Name: pid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem ALTER COLUMN pid SET DEFAULT nextval('exercise_exercice_id_seq'::regclass);


--
-- Name: stats_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_stats ALTER COLUMN stats_id SET DEFAULT nextval('problem_stats_stats_id_seq'::regclass);


--
-- Name: rid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY roles ALTER COLUMN rid SET DEFAULT nextval('roles_rid_seq'::regclass);


--
-- Name: idmail; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY send_mail ALTER COLUMN idmail SET DEFAULT nextval('send_mail_idmail_seq'::regclass);


--
-- Name: aid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY special_awards ALTER COLUMN aid SET DEFAULT nextval('contest_awards_aid_seq'::regclass);


--
-- Name: submit_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY submition ALTER COLUMN submit_id SET DEFAULT nextval('submition_submit_id_seq'::regclass);


--
-- Name: ucid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_clarification ALTER COLUMN ucid SET DEFAULT nextval('user_clarification_ucid_seq'::regclass);


--
-- Name: upid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile ALTER COLUMN upid SET DEFAULT nextval('user_profile_upid_seq'::regclass);


--
-- Name: stats_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_stats ALTER COLUMN stats_id SET DEFAULT nextval('user_stats_stats_id_seq'::regclass);


--
-- Name: uid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY users ALTER COLUMN uid SET DEFAULT nextval('tbl_user_user_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY wboard_contest ALTER COLUMN id SET DEFAULT nextval('wboard_contest_id_seq'::regclass);


--
-- Name: sid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY wboard_site ALTER COLUMN sid SET DEFAULT nextval('wboard_site_id_seq'::regclass);


--
-- Data for Name: account_activation; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: account_activation_act_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('account_activation_act_id_seq', 21580, true);


--
-- Data for Name: achievements; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO achievements VALUES (28, 'Prolog Master', 'Logic is the beginning of wisdom', 'AC 20 problems in Prolog', 'Prolog', true);
INSERT INTO achievements VALUES (1, 'C# Master', 'Congratulations! Now how about some Java?', 'AC 20 problems in C# language', 'C#', true);
INSERT INTO achievements VALUES (2, 'Java Master', 'Write once, AC everywhere', 'AC 20 problems in Java language', 'Java', true);
INSERT INTO achievements VALUES (4, 'C Master', 'Citius, Altius, Fortius', 'AC 20 problems in C language', 'C', true);
INSERT INTO achievements VALUES (3, 'C++ Master', 'Shooting your foot and the feet of others', 'AC 20 problems in C++ language', 'C++', true);
INSERT INTO achievements VALUES (6, 'Ruby Master', 'Oh, shiny!', 'AC 20 problems in Ruby', 'Ruby', true);
INSERT INTO achievements VALUES (7, 'Bash Master', 'Bashing keyboards is no way to go around in life, son...', 'AC 20 problems in Bash', 'Bash', true);
INSERT INTO achievements VALUES (9, 'Python Master', 'Spam and eggs for the BDFL!', 'AC 20 problems in Python', 'Py', true);
INSERT INTO achievements VALUES (10, 'Perl Master', 'Before swine...', 'AC 20 problems in Perl', 'Perl', true);
INSERT INTO achievements VALUES (11, 'Pascal Master', 'Pascal is obsolete, or It is not. You must wager (it''s not optional)', 'AC 20 problems in Pascal', 'Pas', true);
INSERT INTO achievements VALUES (12, 'Polyglot', 'Must be nice taking the Babel Fish off...', 'AC a problem in 5 different languages', 'Poly', true);
INSERT INTO achievements VALUES (8, 'PHP Master', 'Something is so wrong about this...', 'AC 20 problems in PHP', 'PHP', true);
INSERT INTO achievements VALUES (13, 'C++11 Master', 'The sins of the father, revisited upon the son.', 'AC 20 problems in C++11', 'C++11', true);
INSERT INTO achievements VALUES (14, 'Haskell Master', 'Tainted Love...', 'AC 20 problems in Haskell', 'Haskell', true);
INSERT INTO achievements VALUES (5, 'Sniper', 'Like ACing fish in a barrel', 'AC 10 problems in a row', 'Sniper', true);
INSERT INTO achievements VALUES (29, 'Inflate the balloon', 'Save your breath. You are gonna needed.', 'AC at least one problem other than pid 1000', 'Balloon', true);
INSERT INTO achievements VALUES (30, 'Rhino Master', 'In case you are in no rush to get Nashorn...', 'AC 20 problems in Rhino JS', 'Rhino', true);
INSERT INTO achievements VALUES (31, 'NodeJS Master', 'Asynchronous, chromey goodness', 'AC 20 problems in NodeJS', 'NodeJS', true);
INSERT INTO achievements VALUES (17, 'Combinations Master', 'So many ways to fail', 'AC 20 problems tagged as Combinations', 'Comb', true);
INSERT INTO achievements VALUES (16, 'Math Wiz', 'Crunchy, crunchy numbers', 'AC 20 problems tagged as Arithmetic-Algebra', 'Arith', true);
INSERT INTO achievements VALUES (15, 'Brute', 'Solves more problems than it creates', 'AC 20 problems tagged as Brute Force', 'Brute', true);
INSERT INTO achievements VALUES (19, 'Dynamic', 'So much energy here', 'AC 20 problems tagged as Dynamic Programming', 'Dynamic', true);
INSERT INTO achievements VALUES (20, 'Game Theorist', 'Life is a zero sum game.  ', 'AC 20 problems tagged as Game Theory', 'Game', true);
INSERT INTO achievements VALUES (21, 'Drawing Master', 'Are you sharp or obtuse?', 'AC 20 problems tagged as Geometry', 'Geo', true);
INSERT INTO achievements VALUES (23, 'Number Theorist', 'People lie all the time, numbers only when they''re asked.', 'AC 20 problems tagged as Number Theory', 'Numbers', true);
INSERT INTO achievements VALUES (18, 'Data Structurer', 'Big Data is not going to map/reduce itself!', 'AC 20 problems tagged as Data Structure', 'Data', true);
INSERT INTO achievements VALUES (24, 'Searcher', 'While we wait for the quantum algorithm, this will have to do.', 'AC 20 problems tagged as Sorting-Searching', 'Sort', true);
INSERT INTO achievements VALUES (25, 'Graph Theorist', 'Maybe the salesman can rest at last?', 'AC 20 problems tagged as Graph Theory', 'Graph', true);
INSERT INTO achievements VALUES (26, 'String Theorist', 'No strings attached, promise', 'AC 20 problems tagged as Strings', 'Strings', true);
INSERT INTO achievements VALUES (22, 'Greedy Gecko', 'Greed, for the lack of a better word, is good.', 'AC 20 problems tagged as Greedy', 'Greedy', true);
INSERT INTO achievements VALUES (27, 'Oddball', 'One of these things is not like the others...', 'AC 20 problems tagged as Ad-hoc', 'Ad-hoc', true);
INSERT INTO achievements VALUES (32, 'VBasic Master', 'Being a Microsoft friend', 'AC 20 problems in VBasic', 'VBasic', true);
INSERT INTO achievements VALUES (33, 'General', 'General', 'General', 'General', false);


--
-- Data for Name: admin_clarification; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: admin_log; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO admin_log VALUES (6262916, 'user logged in', '2016-11-07 12:50:19.630638', 'admin');
INSERT INTO admin_log VALUES (6262917, 'user logged in', '2016-11-07 23:25:04.816975', 'admin');
INSERT INTO admin_log VALUES (6262918, 'user logged in', '2016-11-08 00:33:26.442857', 'admin');
INSERT INTO admin_log VALUES (6262919, 'user logged in', '2016-11-08 01:00:04.210965', 'admin');
INSERT INTO admin_log VALUES (6262920, 'user logged in', '2016-11-08 18:22:24.40391', 'psetter1');
INSERT INTO admin_log VALUES (6262921, 'user logged in', '2016-11-08 18:29:47.60853', 'user1');


--
-- Name: admin_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('admin_log_id_seq', 6262921, true);


--
-- Data for Name: announcements; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO announcements VALUES (44, 'Dear user, <a href="http://coj.uci.cu/contest/running.xhtml">clicking on this link</a> you can see the running contests!', false, 'dovier', '2013-02-05 10:56:19.801411', 0);
INSERT INTO announcements VALUES (18, '<i>Dear user, <a href="http://coj.uci.cu/contest/coming.xhtml">clicking on this link</a> you can see the coming contests!</i>', false, 'jelara', '2013-09-20 19:40:05.112199', 0);
INSERT INTO announcements VALUES (56, 'Por favor, actualice el nickname y los integrantes de su equipo (ir a Edit account). En el ranking, para verificar los datos, pase el puntero por encima del nombre del equipo.<br>', false, 'dovier', '2014-04-13 18:27:07.270298', 0);
INSERT INTO announcements VALUES (55, 'If you are facing problems with your submissions in C/C++, if system returned the error "collect2: ld returned 1 exit status", please check the following <a href="http://coj-forum.uci.cu/viewtopic.php?f=8&amp;t=1950">topic in forum http://coj-forum.uci.cu/viewtopic.php?f=8&amp;t=1950</a>. Regreted.<br>', false, 'george', '2014-02-10 12:54:08.783886', 0);
INSERT INTO announcements VALUES (25, 'Go to the "Overview" page to see more info about the contest!', false, 'dovier', '2013-09-20 23:35:25.875446', 1288);
INSERT INTO announcements VALUES (51, 'There are one more problem in each level. And also one more level, with four problems. Good luck to everyone!!!', false, 'dovier', '2013-09-20 23:35:52.849855', 0);
INSERT INTO announcements VALUES (52, '<i><a href="http://en.wikipedia.org/wiki/Programmers%27_Day">Happy Programmers'' Day!!</a></i><br>', false, 'george', '2013-09-14 07:32:22.789237', 0);
INSERT INTO announcements VALUES (62, 'Achievements have been added and recalculated, please check the gallery on your user account.<br>', false, 'jasr', '2014-10-21 20:38:26.338689', 0);
INSERT INTO announcements VALUES (63, 'We are currently tuning and debugging our new qualification engine. Fluctuations and weird results in judgements, as well as irregularities with user points are due to this new software. Don''t panic: All submits from Oct 21, 2014 onwards will be rejudged once the software is mature enough.<br>', false, 'jasr', '2014-12-19 00:31:28.59171', 0);
INSERT INTO announcements VALUES (72, '<a href="https://coj-forum.uci.cu/viewtopic.php?f=9&amp;t=2642">Happy COJ''s 5th Anniversary!</a><br>', false, 'dovier', '2015-06-06 20:10:56.318471', 0);
INSERT INTO announcements VALUES (74, 'Estimados usuarios: desafortunadamente el problema con el motor de calificación del COJ no se podrá arreglar hasta que se reanude el curso docente en la UCI, a finales de Agosto. El COJ está (y seguirá) disponible, pero no se juzgarán los envíos realizados hasta normalizar el motor de calificación. Rogamos disculpas por las molestias y les pedimos que tengan confianza en que todo será corregido lo más pronto posible.<br>', false, 'ymondelo20', '2015-08-24 16:49:48.480968', 0);
INSERT INTO announcements VALUES (73, 'Le invitamos a responder estas <a href="http://coj.uci.cu/poll/list.xhtml">encuestas</a>. Puede enviarnos comentarios adicionales mediante el <i>micro-blogging</i> / You are welcome to answer these <a href="http://coj.uci.cu/poll/list.xhtml">surveys</a>. You can send additional comments by the micro-blogging.<br>', false, 'dovier', '2015-10-18 13:52:09.063728', 0);
INSERT INTO announcements VALUES (79, '<div align="justify">El conjunto oficial de problemas utilizado durante el Torneo Argentino de Programación, en castellano, <a href="http://coj.uci.cu/downloads/files/TAP2015-es.pdf">puede ser descargado aqui</a>.<br></div>', false, 'dovier', '2015-11-12 23:03:26.029266', 0);
INSERT INTO announcements VALUES (77, 'Welcome to the COJ Round Contest #2: send us any picture of you and/or your friends/teammates during the contest or near to it. Best pictures will be uploaded to <a href="http://coj.uci.cu/contest/gallery.xhtml?cid=1460">public gallery</a> of the competition. Thank you and have fun! <img src="http://coj.uci.cu/js/WYSIWYG/images/emoticons/biggrin.gif"><br>', false, 'ymondelo20', '2015-09-17 12:38:49.948687', 0);
INSERT INTO announcements VALUES (20, 'Are you interested to participate in the ACM-ICPC, and your Caribbean institution is not officially enlisted? Please, <a href="https://coj-forum.uci.cu/viewtopic.php?f=9&amp;t=918">click here</a> to see the current list of involved institutions.<br>', false, 'dovier', '2015-10-18 13:49:33.186405', 0);
INSERT INTO announcements VALUES (78, 'Equipos clasificados a la Final Caribeña 2015 del ACM-ICPC (Concurso 
Regional) / Qualified teams for the 2015 ACM-ICPC Caribbean Finals 
(Regional Contest). Ver <a href="https://coj-forum.uci.cu/viewtopic.php?f=9&amp;t=2767">listado en el foro</a> y <a href="https://icpc.baylor.edu/regionals/finder/cf-2015/teams">listado en el sitio del ACM-ICPC</a>.', false, 'dovier', '2015-11-01 00:53:03.65237', 0);
INSERT INTO announcements VALUES (83, '<a href="https://coj-forum.uci.cu/viewtopic.php?f=12&amp;t=2839">IMPORTANTE LLAMADO A LOS USUARIOS DEL COJ / <i>IMPORTANT CALL TO ALL USERS OF THE COJ</i></a><br>', false, 'dovier', '2015-12-11 15:30:27.435955', 0);
INSERT INTO announcements VALUES (82, '<a href="http://live.uci.cu/">Transmisión en vivo y ranking</a> de la Sede Cubana 2015.<br>', false, 'ybroche', '2015-11-15 21:39:27.450947', 0);
INSERT INTO announcements VALUES (84, '<a href="http://coj.uci.cu/contest/contestview.xhtml?cid=1479">¡Participa en el Abierto UCI de Programación!</a><br>', false, 'dovier', '2015-12-18 22:40:25.882678', 0);
INSERT INTO announcements VALUES (81, '<a href="http://coj.uci.cu/event/cubansite/#slide-events">Cronograma general de actividades</a> de la Sede Cubana 2015.<br>', false, 'dovier', '2015-11-16 15:32:46.52915', 0);
INSERT INTO announcements VALUES (90, '<a href="https://coj-forum.uci.cu/viewtopic.php?f=12&amp;t=2897">Be careful with Scammers.</a>', false, 'dovier', '2016-02-26 08:26:55.835642', 0);
INSERT INTO announcements VALUES (71, '<a href="https://coj-forum.uci.cu/viewtopic.php?f=9&amp;t=2631">Desempeño de equipos caribeños</a> en las Finales Mundiales del ACM-ICPC.<br>', false, 'dovier', '2015-12-01 12:10:53.464145', 0);
INSERT INTO announcements VALUES (85, '<a href="https://coj-forum.uci.cu/viewtopic.php?f=18&amp;t=2887">II Copa UNIVERSIDAD de Programación: Convocatoria para participación / Call for participation.</a>', false, 'dovier', '2016-02-02 06:51:49.149034', 0);
INSERT INTO announcements VALUES (87, '<a href="https://coj-forum.uci.cu/viewtopic.php?f=35&amp;t=2631">Equipos caribeños en las Finales Mundiales del ACM-ICPC.</a><br>', false, 'dovier', '2016-02-29 20:20:18.999163', 0);
INSERT INTO announcements VALUES (92, '@TODOS: Esta noche, comenzando a las 10:00 p.m. (y probablemente durante 4 horas), está programado un mantenimiento del sistema... con el fin de mejorar los recursos de hardware del COJ.<br>', false, 'dovier', '2016-03-04 08:43:49.273888', 0);
INSERT INTO announcements VALUES (91, '@Concursantes elegibles de la UCI: Este jueves 3/marzo, 9:00 p.m. en el Lab. 101 Docente 4, acto público de conformación y vinculación de equipos al MPC-TLJ, sean o no conformados por éste. Más info en el <a href="https://coj-forum.uci.cu/viewtopic.php?f=34&amp;t=2840">MSE2</a>.<br>', false, 'dovier', '2016-03-03 18:54:37.246325', 0);
INSERT INTO announcements VALUES (88, '<a href="https://coj-forum.uci.cu/viewtopic.php?f=35&amp;t=2846">Breve historia de Cuba en el ACM-ICPC hasta el año 2008.</a><br>', false, 'dovier', '2016-02-29 20:20:23.802333', 0);
INSERT INTO announcements VALUES (86, '<a href="https://coj-forum.uci.cu/viewtopic.php?f=35&amp;t=2790">Podios del Caribe en los Concursos Regionales del ACM-ICPC.</a><br>', false, 'dovier', '2016-02-29 20:20:27.826929', 0);
INSERT INTO announcements VALUES (94, '<a href="https://coj-forum.uci.cu/viewtopic.php?f=34&amp;t=2928">Entrenamientos en la UCI del IPVCE Lenin 2013+</a><br>', false, 'dovier', '2016-03-31 13:28:35.62535', 0);
INSERT INTO announcements VALUES (95, '¡Ya funciona nuevamente el registro de nuevos usuarios en el Foro del COJ! <a href="https://coj-forum.uci.cu/ucp.php?mode=register">Clic aquí para ir directo al formulario de registro.</a><br>', false, 'dovier', '2016-04-01 17:16:04.88775', 0);
INSERT INTO announcements VALUES (96, '<a href="https://coj-forum.uci.cu/viewtopic.php?f=12&amp;t=2948">Interioridades del algoritmo del motor de evaluación</a><br>', false, 'dovier', '2016-04-07 07:34:10.539939', 0);


--
-- Name: announcements_aid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('announcements_aid_seq', 97, true);


--
-- Data for Name: authorities; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO authorities VALUES ('admin', 'ROLE_ADMIN', 72660);
INSERT INTO authorities VALUES ('admin', 'ROLE_USER', 72661);
INSERT INTO authorities VALUES ('psetter2', 'ROLE_PSETTER', 72666);
INSERT INTO authorities VALUES ('psetter2', 'ROLE_USER', 72667);
INSERT INTO authorities VALUES ('user1', 'ROLE_USER', 72668);
INSERT INTO authorities VALUES ('psetter1', 'ROLE_PSETTER', 72670);
INSERT INTO authorities VALUES ('psetter1', 'ROLE_JUDGE', 72671);
INSERT INTO authorities VALUES ('psetter1', 'ROLE_USER', 72672);


--
-- Name: authorities_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('authorities_id_seq', 72673, true);


--
-- Data for Name: balloontrackers; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: clarification; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: clarification_id_clarification_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('clarification_id_clarification_seq', 3225, true);


--
-- Data for Name: classifications; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO classifications VALUES (2, 'Brute Force', 0, 15);
INSERT INTO classifications VALUES (1, 'Arithmetic-Algebra', 0, 16);
INSERT INTO classifications VALUES (3, 'Combination', 0, 17);
INSERT INTO classifications VALUES (4, 'Data Structures', 0, 18);
INSERT INTO classifications VALUES (5, 'Dynamic Programming', 0, 19);
INSERT INTO classifications VALUES (6, 'Game Theory', 0, 20);
INSERT INTO classifications VALUES (7, 'Geometry', 0, 21);
INSERT INTO classifications VALUES (8, 'Greedy', 0, 22);
INSERT INTO classifications VALUES (9, 'Number Theory', 0, 23);
INSERT INTO classifications VALUES (10, 'Sorting-Searching', 0, 24);
INSERT INTO classifications VALUES (23, 'Graph Theory', 0, 25);
INSERT INTO classifications VALUES (24, 'Strings', 0, 26);
INSERT INTO classifications VALUES (25, 'Ad-Hoc', 0, 27);


--
-- Name: classifications_id_classification_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('classifications_id_classification_seq', 28, true);


--
-- Data for Name: cojmail_log; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: cojmail_log_cm_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('cojmail_log_cm_id_seq', 152267, true);


--
-- Data for Name: contest; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: contest_awards; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: contest_awards_aid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('contest_awards_aid_seq', 1, false);


--
-- Name: contest_awards_users_aid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('contest_awards_users_aid_seq', 1, false);


--
-- Data for Name: contest_brief; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: contest_brief_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('contest_brief_id_seq', 653, true);


--
-- Name: contest_cid_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('contest_cid_sequence', 1430, false);


--
-- Data for Name: contest_dataset_verdict; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: contest_flags; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: contest_flags_cfid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('contest_flags_cfid_seq', 654, true);


--
-- Data for Name: contest_judges; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: contest_registration; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: contest_registration_rid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('contest_registration_rid_seq', 0, true);


--
-- Data for Name: contest_source; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: contest_style; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: contest_style_sid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('contest_style_sid_seq', 5, true);


--
-- Data for Name: contest_submition; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: contest_submition_submit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('contest_submition_submit_id_seq', 329767, true);


--
-- Data for Name: contributions; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO country VALUES ('BGR', 'Bulgaria', 33, true, 'BG', 'http://en.wikipedia.org/wiki/Bulgaria');
INSERT INTO country VALUES ('BDI', 'Burundi', 35, true, 'BI', 'http://en.wikipedia.org/wiki/Burundi');
INSERT INTO country VALUES ('KHM', 'Cambodia', 36, true, 'KH', 'http://en.wikipedia.org/wiki/Cambodia');
INSERT INTO country VALUES ('CAN', 'Canada', 38, true, 'CA', 'http://en.wikipedia.org/wiki/Canada');
INSERT INTO country VALUES ('CPV', 'Cape Verde', 39, true, 'CV', 'http://en.wikipedia.org/wiki/Cape_Verde');
INSERT INTO country VALUES ('TCD', 'Chad', 42, true, 'TD', 'http://en.wikipedia.org/wiki/Chad');
INSERT INTO country VALUES ('CHL', 'Chile', 43, true, 'CL', 'http://en.wikipedia.org/wiki/Chile');
INSERT INTO country VALUES ('CHN', 'China', 44, true, 'CN', 'http://en.wikipedia.org/wiki/China');
INSERT INTO country VALUES ('COL', 'Colombia', 46, true, 'CO', 'http://en.wikipedia.org/wiki/Colombia');
INSERT INTO country VALUES ('COM', 'Comoros', 47, true, 'KM', 'http://en.wikipedia.org/wiki/Comoros');
INSERT INTO country VALUES ('COK', 'Cook Islands', 49, true, 'CK', 'http://en.wikipedia.org/wiki/Cook_Islands');
INSERT INTO country VALUES ('CRI', 'Costa Rica', 50, true, 'CR', 'http://en.wikipedia.org/wiki/Costa_Rica');
INSERT INTO country VALUES ('CUB', 'Cuba', 52, true, 'CU', 'http://en.wikipedia.org/wiki/Cuba');
INSERT INTO country VALUES ('BFA', 'Burkina Faso', 34, true, 'BF', 'http://en.wikipedia.org/wiki/Burkina_Faso');
INSERT INTO country VALUES ('CMR', 'Cameroon', 37, true, 'CM', 'http://en.wikipedia.org/wiki/Cameroon');
INSERT INTO country VALUES ('CYM', 'Cayman Islands', 40, true, 'KY', 'http://en.wikipedia.org/wiki/Cayman_Islands');
INSERT INTO country VALUES ('CAF', 'Central African Republic', 41, true, 'CF', 'http://en.wikipedia.org/wiki/Central_African_Republic');
INSERT INTO country VALUES ('CXR', 'Christmas Island', 45, true, 'CX', 'http://en.wikipedia.org/wiki/Christmas_Island');
INSERT INTO country VALUES ('COG', 'Republic of the Congo', 48, true, 'CG', 'http://en.wikipedia.org/wiki/Republic_of_the_Congo');
INSERT INTO country VALUES ('HRV', 'Croatia', 51, true, 'HR', 'http://en.wikipedia.org/wiki/Croatia');


--
-- Name: country_country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('country_country_id_seq', 253, true);


--
-- Data for Name: dataset_verdict_json; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO dataset_verdict_json VALUES (933814, '{"d":[{"c":0,"t":72,"m":1662976,"s":"Accepted"}]}');
INSERT INTO dataset_verdict_json VALUES (933815, '{"d":[]}');
INSERT INTO dataset_verdict_json VALUES (933816, '{"d":[{"c":0,"t":3376,"m":1336836096,"s":"Accepted"},{"c":1,"t":2465,"m":1336836096,"s":"Accepted"},{"c":2,"t":1794,"m":1336836096,"s":"Accepted"},{"c":3,"t":1413,"m":1336836096,"s":"Accepted"}]}');
INSERT INTO dataset_verdict_json VALUES (933817, '{"d":[{"c":0,"t":1803,"m":7733248,"s":"Accepted"},{"c":1,"t":1730,"m":7733248,"s":"Accepted"},{"c":2,"t":2454,"m":7733248,"s":"Accepted"},{"c":3,"t":1943,"m":7733248,"s":"Accepted"}]}');
INSERT INTO dataset_verdict_json VALUES (933818, '{"d":[{"c":0,"t":null,"m":null,"s":"Runtime Error"},{"c":1,"t":null,"m":null,"s":"Runtime Error"},{"c":2,"t":null,"m":null,"s":"Runtime Error"},{"c":3,"t":null,"m":null,"s":"Runtime Error"}]}');
INSERT INTO dataset_verdict_json VALUES (933819, '{"d":[{"c":0,"t":82,"m":64561152,"s":"Accepted"},{"c":1,"t":90,"m":64561152,"s":"Accepted"},{"c":2,"t":124,"m":64561152,"s":"Accepted"},{"c":3,"t":99,"m":64561152,"s":"Accepted"}]}');
INSERT INTO dataset_verdict_json VALUES (933820, '{"d":[{"c":0,"t":100,"m":64561152,"s":"Wrong Answer"},{"c":1,"t":101,"m":64561152,"s":"Wrong Answer"},{"c":2,"t":99,"m":64561152,"s":"Wrong Answer"},{"c":3,"t":108,"m":64561152,"s":"Wrong Answer"}]}');
INSERT INTO dataset_verdict_json VALUES (933821, '{"d":[{"c":0,"t":72,"m":64561152,"s":"Accepted"},{"c":1,"t":68,"m":64561152,"s":"Accepted"},{"c":2,"t":63,"m":64561152,"s":"Accepted"},{"c":3,"t":70,"m":64561152,"s":"Accepted"}]}');
INSERT INTO dataset_verdict_json VALUES (933822, '{"d":[{"c":0,"t":3142,"m":1337700352,"s":"Accepted"}]}');
INSERT INTO dataset_verdict_json VALUES (933823, '{"d":[{"c":0,"t":1804,"m":41422848,"s":"Accepted"},{"c":1,"t":1566,"m":41422848,"s":"Accepted"},{"c":2,"t":2294,"m":41422848,"s":"Wrong Answer"},{"c":3,"t":1275,"m":41422848,"s":"Wrong Answer"}]}');
INSERT INTO dataset_verdict_json VALUES (933824, '{"d":[{"c":0,"t":36,"m":1552384,"s":"Accepted"},{"c":1,"t":57,"m":1552384,"s":"Accepted"},{"c":2,"t":74,"m":1552384,"s":"Accepted"},{"c":3,"t":62,"m":1552384,"s":"Accepted"}]}');
INSERT INTO dataset_verdict_json VALUES (933825, '{"d":[{"c":0,"t":50,"m":1552384,"s":"Wrong Answer"},{"c":1,"t":14,"m":1552384,"s":"Wrong Answer"},{"c":2,"t":52,"m":1552384,"s":"Wrong Answer"},{"c":3,"t":13,"m":1552384,"s":"Wrong Answer"}]}');
INSERT INTO dataset_verdict_json VALUES (933826, '{"d":[{"c":0,"t":null,"m":null,"s":"Case Time Limit Exceeded"},{"c":1,"t":null,"m":null,"s":"Case Time Limit Exceeded"},{"c":2,"t":null,"m":null,"s":"Case Time Limit Exceeded"},{"c":3,"t":null,"m":null,"s":"Case Time Limit Exceeded"}]}');
INSERT INTO dataset_verdict_json VALUES (933827, '{"d":[{"c":0,"t":null,"m":null,"s":"Case Time Limit Exceeded"},{"c":1,"t":null,"m":null,"s":"Case Time Limit Exceeded"},{"c":2,"t":null,"m":null,"s":"Case Time Limit Exceeded"},{"c":3,"t":null,"m":null,"s":"Case Time Limit Exceeded"}]}');
INSERT INTO dataset_verdict_json VALUES (933828, '{"d":[{"c":0,"t":null,"m":null,"s":"Memory Limit Exceeded"},{"c":1,"t":null,"m":null,"s":"Memory Limit Exceeded"},{"c":2,"t":null,"m":null,"s":"Memory Limit Exceeded"},{"c":3,"t":null,"m":null,"s":"Memory Limit Exceeded"}]}');
INSERT INTO dataset_verdict_json VALUES (933829, '{"d":[{"c":0,"t":null,"m":null,"s":"Runtime Error"},{"c":1,"t":null,"m":null,"s":"Runtime Error"},{"c":2,"t":null,"m":null,"s":"Runtime Error"},{"c":3,"t":null,"m":null,"s":"Runtime Error"}]}');
INSERT INTO dataset_verdict_json VALUES (933830, '{"d":[{"c":0,"t":1669,"m":1336836096,"s":"Accepted"},{"c":1,"t":2548,"m":1336836096,"s":"Accepted"},{"c":2,"t":2080,"m":1336836096,"s":"Accepted"},{"c":3,"t":2184,"m":1336836096,"s":"Accepted"}]}');
INSERT INTO dataset_verdict_json VALUES (933831, '{"d":[]}');
INSERT INTO dataset_verdict_json VALUES (933832, '{"d":[{"c":0,"t":null,"m":null,"s":"Runtime Error"},{"c":1,"t":null,"m":null,"s":"Runtime Error"},{"c":2,"t":null,"m":null,"s":"Runtime Error"},{"c":3,"t":null,"m":null,"s":"Runtime Error"}]}');
INSERT INTO dataset_verdict_json VALUES (933833, '{"d":[{"c":0,"t":2001,"m":7729152,"s":"Wrong Answer"},{"c":1,"t":2731,"m":7729152,"s":"Wrong Answer"},{"c":2,"t":2129,"m":7729152,"s":"Wrong Answer"},{"c":3,"t":2004,"m":7729152,"s":"Wrong Answer"}]}');
INSERT INTO dataset_verdict_json VALUES (933834, '{"d":[{"c":0,"t":1384,"m":7733248,"s":"Accepted"},{"c":1,"t":3629,"m":7733248,"s":"Accepted"},{"c":2,"t":1266,"m":7733248,"s":"Accepted"},{"c":3,"t":2175,"m":7733248,"s":"Accepted"}]}');


--
-- Data for Name: datasets; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: datasets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('datasets_id_seq', 46380, true);


--
-- Data for Name: draft; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: draft_draft_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('draft_draft_id_seq', 768, true);


--
-- Data for Name: entries; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.597', 5569, 0, true, 'The contest Week of Code - 20 is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.618', 5570, 0, true, 'The contest XIV Copa Pascal de Programación (UCI) is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.642', 5571, 0, true, 'The contest World Codesprint April  is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.663', 5572, 0, true, 'The contest HourRank 8 is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.692', 5573, 0, true, 'The contest Game Theory is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.71', 5574, 0, true, 'The contest 101 Hack April 2016 is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.736', 5575, 0, true, 'The contest Regular Expresso 2 is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.765', 5576, 0, true, 'The contest Simply SQL - The Sequel is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.796', 5577, 0, true, 'The contest Women''s CodeSprint is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.82', 5578, 0, true, 'The contest Ad Infinitum 15 - Math Programming Contest is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.838', 5579, 0, true, 'The contest Liga Cubana de Programación 2016 (Etapa IV) is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.849', 5580, 0, true, 'The contest April Cook-Off 2016 is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.858', 5581, 0, true, 'The contest Liga Cubana de Programación 2016 (Etapa VI) is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.868', 5582, 0, true, 'The contest Liga Cubana de Programación 2016 (Etapa V) is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.877', 5583, 0, true, 'The contest The 2016 ACM-ICPC Caribbean Local Contests (Real contest) is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.89', 5584, 0, true, 'The contest Liga Cubana de Programación 2016 (Etapa VII) is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.9', 5585, 0, true, 'The contest The 2016 ACM-ICPC Caribbean National Contests (Real contest) is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);
INSERT INTO entries VALUES (NULL, '2016-11-07 11:13:36.909', 5586, 0, true, 'The contest The COJ Progressive Contest #10 is near. More info: <a target="new" href="http://coj.uci.cu/wboard/contests.xhtml">http://coj.uci.cu/wboard/contests.xhtml</a>', false, false, false, false);


--
-- Name: entries_eid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('entries_eid_seq', 5586, true);


--
-- Data for Name: entry_rate; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: events; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: events_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('events_id_seq', 2528, true);


--
-- Name: exercise_exercice_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('exercise_exercice_id_seq', 3631, true);


--
-- Data for Name: faq; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO faq VALUES ('You can use all the standard libraries included with the programming language that you are using. For example, this includes the STL (Standard Template Library) of C++ and the java.lang.BigInteger class of Java.', 'WHAT PROGRAMMING LIBRARIES CAN I USE?', 1);
INSERT INTO faq VALUES ('When the COJ has judged your submission, you will get a judgment that tells you what is the status of your solution. You can view the verdict on the <a href="/24h/status.xhtml">judgments</a> page. The following judgments are possible:
<ul>
<li>Judging (JDG) : The judge is busy and it can''t judge your solution at the moment. Usually you just need to wait for a few seconds.</li>
<li>Runtime Error (RTE) : The RTE judgment means that the program sent by the user has crashed during the execution with the judge''s secret tests (e.g.: the process was signaled or exited with a status other than 0). In C and C++, the value 0 must be returned from main(). In Java doing something to ensure that the exit status is 0 isn''t necessary. Some of the common reasons and their meanings are:
<ul>
<li>ACCESS_VIOLATION - The program tried to read from or write to a address for which it doesn''t have the appropriate access.</li>
<li>ARRAY_BOUNDS_EXCEEDED - The program tried to access an array element that is out of bounds and the underlying hardware supports bounds checking.</li>
<li>FLOAT_DENORMAL_OPERAND - One of the operands in a floating-point operation is denormal. A denormal value is one that is too small to represent as a standard floating-point value.</li>
<li>FLOAT_DIVIDE_BY_ZERO - The thread tried to divide a floating-point value by zero.</li>
<li>FLOAT_OVERFLOW - The exponent of a floating-point operation is greater than the magnitude allowed by the corresponding type.</li>
<li>FLOAT_UNDERFLOW - The exponent of a floating-point operation is less than the magnitude allowed by the corresponding type.</li>
<li>INTEGER_DIVIDE_BY_ZERO - The program tried to divide an integer value by zero.</li>
<li>INTEGER_OVERFLOW - The result of an integer operation caused a carry out of the most significant bit of the result.</li>
<li>STACK_OVERFLOW - The program used up its stack.</li>
</ul>
</li>
<li>Invalid Function (IVF) : The IVF judgment means that the program sent by the user tried to do something which isn''t allowed on the COJ. This includes the manipulation of file descriptors, opening files, running functions as fork() or exec(), creating threads, sending signals, or basically anything needless to solve the problem. All IVF judgments are examined to detect any attempt to exploit the system, in such case the involved user will be analyzed and banned from the COJ. Please, <a href="/general/contact.xhtml">contact us</a> if you think that an IVF judgment is not correct or "fair".</li>
<li>Time Limit Exceeded (TLE) : The TLE judgment means that the program sent by the user ran for too much time. When the time limit is exceeded, the program is terminated. The output produced isn''t inspected in this case, so getting TLE doesn''t mean that the program produced the correct output, only that it didn''t exit in time.</li>
<li>Output Limit Exceeded (OLE) : The program sent by the user tried to write too much information. This usually occurs if it goes into an infinite loop. Currently the output limit is 64 MB.</li>
<li>Size Limit Exceeded (SLE) : The source code sent by the user exceeds the size limit. Currently the size limit is 100 KB.</li>
<li>Memory Limit Exceeded (MLE) : The program sent by the user tried to use more memory than allowed.</li>
<li>Wrong Answer (WA) : The WA judgment means that the program sent by the user finished within the time limit, but the output produced was incorrect. It is important to check the output specification of the problem to avoid this judgment due to, for example, simple white-space errors.</li>
<li>Presentation Error (PE) : The output format of the program sent by the user isn''t exactly the same as the judge''s output, although the answer to the problem is correct. Check the program''s output against the output specification of the problem to detect blank lines, spaces, and others.</li>
<li>Unqualified (UQD) : The UQD verdict means that the program sent by the user couldn''t be run in that moment (engine stopped, datasets no available, among other reasons).</li>
<li>Compilation Error (CE) : The CE judgment means that the source code sent by the user couldn''t be compiled. Extra information (which can help you to debug the error) can be found on the judgments page. Any compilation time over one minute will cause a CE.
</li>
<li>Accepted (AC) : The AC judgment means that the program sent by the user has successfully passed all tests and produced the correct output. Congratulations!</li>
</ul>
', 'WHAT JUDGMENTS OF THE ONLINE JUDGE CAN I GET?', 2);
INSERT INTO faq VALUES ('Yes. This works as follows:
<p>First, you must have an accepted solution to the problem. You cannot see solutions sent to problems that you haven''t solved.
After you have at least one accepted solution to the problem, you will find a new button in the "Best Solutions" page, labeled "Lock my solutions".
When you click that button (and confirm your action), you will be able to see the solutions sent by all the users to that problem.
From that moment on, your position on the "best solutions" table will be locked: all subsequent submissions to that problem will be judged by the system, but will not appear on the "best solutions" table.
Of course, this action is irreversible: once you have locked your position on the "best solutions" table for a problem, you can never unlock it again.
The goal of this action is to further contribute to the learning and training of our users, while maintaining the competitivity of our site at the same time.</p>', 'CAN I SEE OTHER USERS'' SOLUTIONS?', 3);
INSERT INTO faq VALUES ('<p>Some details about the available programming languages are displayed in the table at the end of this page .</p>
', 'WHAT PROGRAMMING LANGUAGES ARE AVAILABLE?', 8);
INSERT INTO faq VALUES ('<p>The source code sent by the user:</p>
    <ul>
        <li>should always read the input from the STDIN (Standard Input) and write the output to the STDOUT (Standard Output);</li>
        <li>should never work with files, threads or other operating system functions;</li>
        <li>should strictly respect the input and output specifications as indicated in the problem description;</li>
        <li>should never exceed the limits defined for the problem (execution time, memory usage, size of the source code, and others).</li>
<p>Below you can download examples of source code for the problem <a href="/24h/problem.xhtml?abb=1000">A+B</a>:</p>
<ul>
        <li><a href="/downloads/samples/c.txt">C</a></li>
        <li><a href="/downloads/samples/cpp.txt">C++</a></li>
        <li><a href="/downloads/samples/java.txt">Java</a></li>
        <li><a href="/downloads/samples/csharp.txt">C#</a></li>
        <li><a href="/downloads/samples/pascal.txt">Pascal</a></li>
        <li><a href="/downloads/samples/perl.txt">Perl</a></li><li><a href="/downloads/samples/prolog.txt">Prolog</a></li>
        <li><a href="/downloads/samples/python.txt">Python</a></li>
        <li><a href="/downloads/samples/php.txt">PHP</a></li>
        <li><a href="/downloads/samples/ruby.txt">Ruby</a></li>
        <li><a href="/downloads/samples/cc.txt">C++11</a></li>
        <li><a href="/downloads/samples/nodejs.txt">Node JS</a></li> 
        <li><a href="/downloads/samples/vbasic.txt">Visual Basic</a></li>
    </ul></ul>', 'ARE THERE SOME CODE SAMPLES?', 6);
INSERT INTO faq VALUES ('<div align="justify">Internal Error is an error in judgments, or engine error, that is supposed which should not happen. Some errors are not internal, because they originate from the source code and they are expected, such as compilation errors or run-time errors; those are commonly reported to users as normal verdicts in the system. But there are errors related with inconsistent or missing datasets, or because the server does not have enough space, or even due errors in system programming; then an unexpected error is originated. That is an Internal Error, and must be closely reviewed by COJ Development Team (CDEVT).</div>', 'WHICH IS THE MEANING OF INTERNAL ERROR?', 15);
INSERT INTO faq VALUES ('<p align="justify">Yes. Almost all problems are associated with one or more tags. A tag is an element which includes both complexity and knowledge area of some problem. If you can´t see the tags in the problems, you should probably go and edit the personal profile and mark the option "Show tags".<br><br>The complexity is an estimated integer number which go from 1 to 5: Very Easy (1), Easy (2), Medium (3), Hard (4), Very Hard (5). This is a relative value which is supposed to be established by their authors; but sometimes they are not available at all. Obviously, with the time the users could propose changes to adjust real complexity of some problem.<br><br>Although in general is really difficult to make a standard grouping/classification of the knowledge areas related with computing programming, the following proposal used by Caribbean Online Judge to group their problems in categories is pretty close to desired goal:</p><ul><li>Ad-hoc (AH)</li><li>Arithmetic-Algebra (AA)</li><li>Brute Force (BF)</li><li>Combination (CO)</li><li>Data Structures (DS)</li><li>Dynamic Programming (DP)</li><li>Game Theory (GA)</li><li>General Geometry (GE)</li><li>Graph Theory (GT)</li><li>Greedy (GR)</li><li>Set-Number Theory (NT)</li><li>Sorting-Searching (SS)</li><li>Strings (ST)</li></ul><p align="justify">Then, problem categorization or tags, could be something like: "GE4" or "NT3, AA3" or "DP3, GT2" (remember that problems can have multiple categorizations but, we certainly avoid to use multiple complexities or unnecessary classifications).<br></p>', 'CAN I SEE THE COMPLEXITY OR KNOWLEDGE AREA OF THE PROBLEMS?', 18);


--
-- Name: faq_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('faq_id_seq', 18, true);


--
-- Data for Name: followers; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO followers VALUES (34227, 34227);
INSERT INTO followers VALUES (34228, 34228);
INSERT INTO followers VALUES (34229, 34229);
INSERT INTO followers VALUES (34230, 34230);
INSERT INTO followers VALUES (34231, 34231);
INSERT INTO followers VALUES (34232, 34232);


--
-- Data for Name: global_access_rules; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO global_access_rules VALUES (5, '*');


--
-- Name: global_access_rules_rid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('global_access_rules_rid_seq', 1, false);


--
-- Data for Name: global_flags; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO global_flags VALUES (true, '10.8.*.*,10.33.3.*', 1, true, false, false, true);


--
-- Name: global_flags_global_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('global_flags_global_id_seq', 1, false);


--
-- Data for Name: group; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO "group" VALUES (1, 'UCI');
INSERT INTO "group" VALUES (2, 'UMCC');
INSERT INTO "group" VALUES (3, 'UPR');
INSERT INTO "group" VALUES (4, 'UH');
INSERT INTO "group" VALUES (5, 'CUJAE');
INSERT INTO "group" VALUES (6, 'INSTEC');
INSERT INTO "group" VALUES (7, 'UCLV');
INSERT INTO "group" VALUES (8, 'UC');
INSERT INTO "group" VALUES (9, 'UCF');


--
-- Name: group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('group_id_seq', 9, true);


--
-- Data for Name: individual_virtual_contest; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: individual_virtual_contest_vid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('individual_virtual_contest_vid_seq', 17875, true);


--
-- Data for Name: institution; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO institution VALUES ('X4860', 'Xidian University', 44, 4860, true, 'http://');
INSERT INTO institution VALUES ('X4861', 'Xihua University', 44, 4861, true, 'http://');
INSERT INTO institution VALUES ('DSFTPRI', 'Desoft - Pinar del Río', 52, 8155, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('ISMI', 'Instituto Superior del MININT', 52, 162, true, 'http://');
INSERT INTO institution VALUES ('ITM', 'Instituto Técnico Militar', 52, 163, true, 'http://');
INSERT INTO institution VALUES ('UC', 'Universidad de Camagüey', 52, 147, true, 'http://www.reduc.edu.cu/');
INSERT INTO institution VALUES ('UCP-CB', 'Universidad de Ciencias Pedagógicas "Conrado Benítez"', 52, 171, true, 'https://www.cf.rimed.cu/');
INSERT INTO institution VALUES ('UCP-JM', 'Universidad de Ciencias Pedagógicas "José Martí"', 52, 174, true, 'http://www.cm.rimed.cu/');
INSERT INTO institution VALUES ('UCP-MAD', 'Universidad de Ciencias Pedagógicas "Manuel Ascunce Domenech"', 52, 173, true, 'http://www.ucp.ca.rimed.cu/');
INSERT INTO institution VALUES ('UCP-RGG', 'Universidad de Ciencias Pedagógicas "Raúl Gómez García"', 52, 179, true, 'http://www.ucp.gu.rimed.cu/');
INSERT INTO institution VALUES ('UCF', 'Universidad de Cienfuegos', 52, 148, true, 'http://www.ucf.edu.cu/');
INSERT INTO institution VALUES ('UHO', 'Universidad de Holguín', 52, 149, true, 'http://www.uho.edu.cu/');
INSERT INTO institution VALUES ('UIJ', 'Universidad de la Isla de la Juventud', 52, 145, true, 'http://www.cuij.edu.cu/');
INSERT INTO institution VALUES ('UH', 'Universidad de La Habana', 52, 150, true, 'http://www.uh.cu/');
INSERT INTO institution VALUES ('ULT', 'Universidad de Las Tunas', 52, 160, true, 'http://www.ult.edu.cu/');
INSERT INTO institution VALUES ('UPR', 'Universidad de Pinar del Río', 52, 152, true, 'http://www.upr.edu.cu/');
INSERT INTO institution VALUES ('UNISS', 'Universidad de Sancti Spíritus', 52, 144, true, 'http://www.suss.co.cu/');
INSERT INTO institution VALUES ('UCCI', 'University College Cayman Islands', 40, 143, true, 'http://www.ucci.edu.ky/');
INSERT INTO institution VALUES ('X4773', 'Beijing Normal University', 44, 4773, true, 'http://');
INSERT INTO institution VALUES ('X4774', 'Beijing Polytechnic University', 44, 4774, true, 'http://');
INSERT INTO institution VALUES ('X4775', 'Beijing University of Aeronautics and Astronautics', 44, 4775, true, 'http://');
INSERT INTO institution VALUES ('X4776', 'Beijing University of Posts and Telecommunications', 44, 4776, true, 'http://');
INSERT INTO institution VALUES ('X4777', 'Beijing University of Technology', 44, 4777, true, 'http://');
INSERT INTO institution VALUES ('X4778', 'Central China Normal University', 44, 4778, true, 'http://');
INSERT INTO institution VALUES ('X4779', 'Central South University', 44, 4779, true, 'http://');
INSERT INTO institution VALUES ('X4780', 'Chang''an University', 44, 4780, true, 'http://');
INSERT INTO institution VALUES ('X4781', 'Changsha Yali Middle School', 44, 4781, true, 'http://');
INSERT INTO institution VALUES ('X4782', 'China Textile University', 44, 4782, true, 'http://');
INSERT INTO institution VALUES ('X4783', 'China University of Geosciences (Wuhan)', 44, 4783, true, 'http://');
INSERT INTO institution VALUES ('X4784', 'Chongqing University', 44, 4784, true, 'http://');
INSERT INTO institution VALUES ('X4785', 'Dalian University', 44, 4785, true, 'http://');
INSERT INTO institution VALUES ('X4786', 'Dalian University of Technology', 44, 4786, true, 'http://');
INSERT INTO institution VALUES ('X4787', 'Donghua University', 44, 4787, true, 'http://');
INSERT INTO institution VALUES ('X4788', 'East China University of Science and Technology', 44, 4788, true, 'http://');
INSERT INTO institution VALUES ('X4790', 'Fudan University', 44, 4790, true, 'http://');
INSERT INTO institution VALUES ('X4791', 'Fujian Normal University', 44, 4791, true, 'http://');
INSERT INTO institution VALUES ('X4792', 'Fuzhou University', 44, 4792, true, 'http://');
INSERT INTO institution VALUES ('X4793', 'Guangdong University of Technology', 44, 4793, true, 'http://');
INSERT INTO institution VALUES ('X4794', 'Harbin Institute of Technology', 44, 4794, true, 'http://');
INSERT INTO institution VALUES ('X4795', 'Hefei University of Technology', 44, 4795, true, 'http://');
INSERT INTO institution VALUES ('X4796', 'Henan Agricultural University', 44, 4796, true, 'http://');
INSERT INTO institution VALUES ('X4797', 'High School Affiliated to Fudan University', 44, 4797, true, 'http://');
INSERT INTO institution VALUES ('X4798', 'High School Affiliated To Nanjing Normal University', 44, 4798, true, 'http://');
INSERT INTO institution VALUES ('X4799', 'Hong Kong Baptist University', 44, 4799, true, 'http://');
INSERT INTO institution VALUES ('X4800', 'Huazhong Polytehcnic', 44, 4800, true, 'http://');
INSERT INTO institution VALUES ('X4801', 'Huazhong University of Science & Technology', 44, 4801, true, 'http://');
INSERT INTO institution VALUES ('X4802', 'Hunan University', 44, 4802, true, 'http://');
INSERT INTO institution VALUES ('X4803', 'Jilin University Of Technology', 44, 4803, true, 'http://');
INSERT INTO institution VALUES ('X4805', 'Jinan University', 44, 4805, true, 'http://');
INSERT INTO institution VALUES ('X4806', 'Jinling High School', 44, 4806, true, 'http://');
INSERT INTO institution VALUES ('X4807', 'LanZhou University', 44, 4807, true, 'http://');
INSERT INTO institution VALUES ('X4808', 'Liaoning University', 44, 4808, true, 'http://');
INSERT INTO institution VALUES ('X4809', 'Nanjing University', 44, 4809, true, 'http://');
INSERT INTO institution VALUES ('X4810', 'Nanjing University of Science and Technology', 44, 4810, true, 'http://');
INSERT INTO institution VALUES ('X4811', 'Nankai University', 44, 4811, true, 'http://');
INSERT INTO institution VALUES ('X4812', 'National University of Defense Technology', 44, 4812, true, 'http://');
INSERT INTO institution VALUES ('X4813', 'North China Electric Power University', 44, 4813, true, 'http://');
INSERT INTO institution VALUES ('X4814', 'Northeast Normal University', 44, 4814, true, 'http://');
INSERT INTO institution VALUES ('X4815', 'Northeasten University', 44, 4815, true, 'http://');
INSERT INTO institution VALUES ('X4816', 'Northwestern Polytechnical University', 44, 4816, true, 'http://');
INSERT INTO institution VALUES ('X4817', 'Ocean University of China', 44, 4817, true, 'http://');
INSERT INTO institution VALUES ('X4818', 'Ocean University of QingDao', 44, 4818, true, 'http://');
INSERT INTO institution VALUES ('X4819', 'Peking University', 44, 4819, true, 'http://');
INSERT INTO institution VALUES ('X4820', 'Qufu Normal University', 44, 4820, true, 'http://');
INSERT INTO institution VALUES ('X4821', 'Renmin Unversity of China', 44, 4821, true, 'http://');
INSERT INTO institution VALUES ('X4822', 'Sandong University of Technology', 44, 4822, true, 'http://');
INSERT INTO institution VALUES ('X4823', 'ShanDong Normal University', 44, 4823, true, 'http://');
INSERT INTO institution VALUES ('X4824', 'Shandong University', 44, 4824, true, 'http://');
INSERT INTO institution VALUES ('X4825', 'Shanghai Financal Univeisity', 44, 4825, true, 'http://');
INSERT INTO institution VALUES ('X4826', 'Shanghai Finance and Economic University', 44, 4826, true, 'http://');
INSERT INTO institution VALUES ('X4827', 'Shanghai Fisheries Universty', 44, 4827, true, 'http://');
INSERT INTO institution VALUES ('X4828', 'Shanghai Jiaotong University', 44, 4828, true, 'http://');
INSERT INTO institution VALUES ('X4829', 'Shanghai Juvenile Science & Technology Guidance Center', 44, 4829, true, 'http://');
INSERT INTO institution VALUES ('X4830', 'Shanghai Maritime University', 44, 4830, true, 'http://');
INSERT INTO institution VALUES ('X4831', 'Shanghai Normal University', 44, 4831, true, 'http://');
INSERT INTO institution VALUES ('UCP-CSB', 'Universidad de Ciencias Pedagógicas "Capitán Silverio Blanco"', 52, 172, true, 'http://www.ucp.ss.rimed.cu/');
INSERT INTO institution VALUES ('UCP-EJV', 'Universidad de Ciencias Pedagógicas "Enrique José Varona"', 52, 167, true, 'http://www.varona.rimed.cu/');
INSERT INTO institution VALUES ('UCP-HAPZ', 'Universidad de Ciencias Pedagógicas "Héctor Alfredo Pineda Zaldívar"', 52, 168, true, 'http://www.ucpetp.rimed.cu/');
INSERT INTO institution VALUES ('UCP-JMV', 'Universidad de Ciencias Pedagógicas "Juan Marinello Vidaurreta"', 52, 169, true, 'http://www.ucp.ma.rimed.cu/');
INSERT INTO institution VALUES ('UCP-PT', 'Universidad de Ciencias Pedagógicas "Pepito Tey"', 52, 175, true, 'http://www.ucp.lt.rimed.cu/');
INSERT INTO institution VALUES ('UCP-RMM', 'Universidad de Ciencias Pedagógicas "Rafael María de Mendive"', 52, 165, true, 'http://www.ucp.pr.rimed.cu/');
INSERT INTO institution VALUES ('UDG-B', 'Universidad de Granma - Bayamo', 52, 159, true, 'http://www.udg.co.cu/');
INSERT INTO institution VALUES ('JILIN', 'Jilin University', 44, 4804, true, 'http://');
INSERT INTO institution VALUES ('X4832', 'ShangHai Normal University of FengXian Region', 44, 4832, true, 'http://');
INSERT INTO institution VALUES ('X4833', 'Shanghai Teachers University', 44, 4833, true, 'http://');
INSERT INTO institution VALUES ('X4834', 'Shanghai TieDao University', 44, 4834, true, 'http://');
INSERT INTO institution VALUES ('X4835', 'Shanghai Tongji University', 44, 4835, true, 'http://');
INSERT INTO institution VALUES ('X4836', 'Shanghai University', 44, 4836, true, 'http://');
INSERT INTO institution VALUES ('X4837', 'Shanghai University of Finance & Economics', 44, 4837, true, 'http://');
INSERT INTO institution VALUES ('X4838', 'ShanTou University', 44, 4838, true, 'http://');
INSERT INTO institution VALUES ('X4839', 'Shenzhen University', 44, 4839, true, 'http://');
INSERT INTO institution VALUES ('X4840', 'Sichuan University', 44, 4840, true, 'http://');
INSERT INTO institution VALUES ('X4841', 'South China Agricultural University', 44, 4841, true, 'http://');
INSERT INTO institution VALUES ('X4842', 'South China Normal University', 44, 4842, true, 'http://');
INSERT INTO institution VALUES ('X4843', 'South China University of Technology', 44, 4843, true, 'http://');
INSERT INTO institution VALUES ('X4844', 'Southern Yangtze University', 44, 4844, true, 'http://');
INSERT INTO institution VALUES ('X4845', 'Southwest Jiaotong University', 44, 4845, true, 'http://');
INSERT INTO institution VALUES ('X4846', 'The Central University for Nationalities', 44, 4846, true, 'http://');
INSERT INTO institution VALUES ('X4847', 'University of Electronic Science and Technology of China', 44, 4847, true, 'http://');
INSERT INTO institution VALUES ('X4848', 'Tianjin University', 44, 4848, true, 'http://');
INSERT INTO institution VALUES ('X4849', 'Tongji University', 44, 4849, true, 'http://');
INSERT INTO institution VALUES ('X4850', 'Tsinghua University', 44, 4850, true, 'http://');
INSERT INTO institution VALUES ('X4851', 'University of Science and Technology of China', 44, 4851, true, 'http://');
INSERT INTO institution VALUES ('X4852', 'University of Shanghai for Science and Technology', 44, 4852, true, 'http://');
INSERT INTO institution VALUES ('X4853', 'Wei Yu School', 44, 4853, true, 'http://');
INSERT INTO institution VALUES ('X4854', 'Wuhan University', 44, 4854, true, 'http://');
INSERT INTO institution VALUES ('X4855', 'WuHu No. 1 Middle School', 44, 4855, true, 'http://');
INSERT INTO institution VALUES ('X4856', 'Xi''an Institue Of Technology', 44, 4856, true, 'http://');
INSERT INTO institution VALUES ('X4857', 'Xi''an Jiaotong University', 44, 4857, true, 'http://');
INSERT INTO institution VALUES ('X4858', 'Xi''an University of Science and Technology', 44, 4858, true, 'http://');
INSERT INTO institution VALUES ('X4859', 'Xiamen University', 44, 4859, true, 'http://');
INSERT INTO institution VALUES ('X4862', 'Yanshan University', 44, 4862, true, 'http://');
INSERT INTO institution VALUES ('X4864', 'Zhejiang University of Technology', 44, 4864, true, 'http://');
INSERT INTO institution VALUES ('X4865', 'ZhongNan University of Economics & Law', 44, 4865, true, 'http://');
INSERT INTO institution VALUES ('X4866', 'Zhongshan (Sun Yat-sen) University', 44, 4866, true, 'http://');
INSERT INTO institution VALUES ('X4867', 'Dongguan University of Technology', 44, 4867, true, 'http://');
INSERT INTO institution VALUES ('X4868', 'Wuyi University', 44, 4868, true, 'http://');
INSERT INTO institution VALUES ('X4869', 'Dongguan Nanbo Polytechnic', 44, 4869, true, 'http://');
INSERT INTO institution VALUES ('X4870', 'Guang Dong Ocean University', 44, 4870, true, 'http://');
INSERT INTO institution VALUES ('X4871', 'ZhaoQing University', 44, 4871, true, 'http://');
INSERT INTO institution VALUES ('X4872', 'ZHBIT university', 44, 4872, true, 'http://');
INSERT INTO institution VALUES ('X4873', 'University of Electronic Science and Technology of China Zhongshan Institute', 44, 4873, true, 'http://');
INSERT INTO institution VALUES ('X4874', 'Guangzhou University', 44, 4874, true, 'http://');
INSERT INTO institution VALUES ('X4875', 'GuangDong Polytechnic Normal University', 44, 4875, true, 'http://');
INSERT INTO institution VALUES ('X4876', 'Guangdong University of Foreign Studies', 44, 4876, true, 'http://');
INSERT INTO institution VALUES ('X4877', 'Zhuhai College of Jilin University', 44, 4877, true, 'http://');
INSERT INTO institution VALUES ('X4878', 'Beijing Normal University Zhuhai', 44, 4878, true, 'http://');
INSERT INTO institution VALUES ('X4879', 'GuangDong University Of Business Studies', 44, 4879, true, 'http://');
INSERT INTO institution VALUES ('X4880', 'ShaoGuan University', 44, 4880, true, 'http://');
INSERT INTO institution VALUES ('X4881', 'Foshan University', 44, 4881, true, 'http://');
INSERT INTO institution VALUES ('X4882', 'Guangdong Institute of Science and Technology', 44, 4882, true, 'http://');
INSERT INTO institution VALUES ('X4883', 'Zhuhai College of Jinan University', 44, 4883, true, 'http://');
INSERT INTO institution VALUES ('X4884', 'Neusoft Institute of Information, Nanhai', 44, 4884, true, 'http://');
INSERT INTO institution VALUES ('X4885', 'Zhuhai City Polytechnic', 44, 4885, true, 'http://');
INSERT INTO institution VALUES ('X4886', 'Heilongjiang University', 44, 4886, true, 'http://');
INSERT INTO institution VALUES ('X4887', 'Harbin Engineering University', 44, 4887, true, 'http://');
INSERT INTO institution VALUES ('X4888', 'Northeast Agricultural University', 44, 4888, true, 'http://');
INSERT INTO institution VALUES ('X4889', 'Daqing Normal University', 44, 4889, true, 'http://');
INSERT INTO institution VALUES ('X4890', 'Huade School of Applied Technology of Harbin institute of technology', 44, 4890, true, 'http://');
INSERT INTO institution VALUES ('X4891', 'Harbin University of Science and Technology', 44, 4891, true, 'http://');
INSERT INTO institution VALUES ('X4892', 'Changchun University', 44, 4892, true, 'http://');
INSERT INTO institution VALUES ('X4893', 'Dalian Jiaotong University', 44, 4893, true, 'http://');
INSERT INTO institution VALUES ('X4894', 'Shenyang Institute of Chemical Technology', 44, 4894, true, 'http://');
INSERT INTO institution VALUES ('X4895', 'Shenyang Normal University', 44, 4895, true, 'http://');
INSERT INTO institution VALUES ('X4896', 'Inner Mongolia University', 44, 4896, true, 'http://');
INSERT INTO institution VALUES ('X4897', 'Northeast Forestry University', 44, 4897, true, 'http://');
INSERT INTO institution VALUES ('X4898', 'Heilongjiang Institute of Science and Technology', 44, 4898, true, 'http://');
INSERT INTO institution VALUES ('X4899', 'Dalian Nationalities University', 44, 4899, true, 'http://');
INSERT INTO institution VALUES ('X4900', 'Jilin Agricultural University', 44, 4900, true, 'http://');
INSERT INTO institution VALUES ('X4901', 'JiLin Normal University', 44, 4901, true, 'http://');
INSERT INTO institution VALUES ('X4902', 'Changchun Normal University', 44, 4902, true, 'http://');
INSERT INTO institution VALUES ('X4903', 'Inner Mongolia Finance and Economics College', 44, 4903, true, 'http://');
INSERT INTO institution VALUES ('X4904', 'Changchun University of Technology', 44, 4904, true, 'http://');
INSERT INTO institution VALUES ('X4905', 'Tong Hua Normal University', 44, 4905, true, 'http://');
INSERT INTO institution VALUES ('X4906', 'JiLin Agricultural Science and Technology College', 44, 4906, true, 'http://');
INSERT INTO institution VALUES ('X4907', 'Shenyang Agricultural University', 44, 4907, true, 'http://');
INSERT INTO institution VALUES ('X4908', 'ChangChun Taxation College', 44, 4908, true, 'http://');
INSERT INTO institution VALUES ('X4909', 'Jiamusi University', 44, 4909, true, 'http://');
INSERT INTO institution VALUES ('X4910', 'Dalian Maritime University', 44, 4910, true, 'http://');
INSERT INTO institution VALUES ('X4911', 'HeiLongJiang Institute of Technology', 44, 4911, true, 'http://');
INSERT INTO institution VALUES ('X4912', 'ChangChun University of Science and Technology', 44, 4912, true, 'http://');
INSERT INTO institution VALUES ('X4913', 'Shenyang Institute of Aeronautical Engineering', 44, 4913, true, 'http://');
INSERT INTO institution VALUES ('X4914', 'Heilongjiang East College', 44, 4914, true, 'http://');
INSERT INTO institution VALUES ('X4915', 'Beihua University', 44, 4915, true, 'http://');
INSERT INTO institution VALUES ('X4916', 'Harbin University of Commerce', 44, 4916, true, 'http://');
INSERT INTO institution VALUES ('X4917', 'Harbin Normal University', 44, 4917, true, 'http://');
INSERT INTO institution VALUES ('X4918', 'Northeastern University', 44, 4918, true, 'http://');
INSERT INTO institution VALUES ('X4919', 'Inner Mongolia Normal University', 44, 4919, true, 'http://');
INSERT INTO institution VALUES ('X4920', 'Dalian Polytechnic University', 44, 4920, true, 'http://');
INSERT INTO institution VALUES ('X4921', 'ShenYang University of Technology', 44, 4921, true, 'http://');
INSERT INTO institution VALUES ('X4922', 'Software College of Northeastern University', 44, 4922, true, 'http://');
INSERT INTO institution VALUES ('X4923', 'Shenyang Jianzhu University', 44, 4923, true, 'http://');
INSERT INTO institution VALUES ('X4924', 'University of Science and Technology Liaoning', 44, 4924, true, 'http://');
INSERT INTO institution VALUES ('X4925', 'Liaoning Normal University', 44, 4925, true, 'http://');
INSERT INTO institution VALUES ('X4926', 'ShenYang University', 44, 4926, true, 'http://');
INSERT INTO institution VALUES ('X4927', 'Jincheng College of Sichuan University', 44, 4927, true, 'http://');
INSERT INTO institution VALUES ('X4928', 'Zhejiang SCI-TECH University', 44, 4928, true, 'http://');
INSERT INTO institution VALUES ('X4929', 'Northwest A&F University', 44, 4929, true, 'http://');
INSERT INTO institution VALUES ('ZJU', 'Zhejiang University', 44, 4863, true, 'http://www.zju.edu.cn/');
INSERT INTO institution VALUES ('X4930', 'Zhejiang University City College', 44, 4930, true, 'http://');
INSERT INTO institution VALUES ('X4931', 'Heilongjiang Bayi Agricultural University', 44, 4931, true, 'http://');
INSERT INTO institution VALUES ('X4932', 'Harbin Huaxia Institute of Computer Technology', 44, 4932, true, 'http://');
INSERT INTO institution VALUES ('X4933', 'Harbin Finance College', 44, 4933, true, 'http://');
INSERT INTO institution VALUES ('X4934', 'Harbin University', 44, 4934, true, 'http://');
INSERT INTO institution VALUES ('X4935', 'Qiqihar University', 44, 4935, true, 'http://');
INSERT INTO institution VALUES ('X4936', 'Mudanjiang Medical University', 44, 4936, true, 'http://');
INSERT INTO institution VALUES ('X4937', 'Heilongjiang Info. Tech. Profession College', 44, 4937, true, 'http://');
INSERT INTO institution VALUES ('X4938', 'Deqiang Business College Harbin University of Commerce', 44, 4938, true, 'http://');
INSERT INTO institution VALUES ('X4939', 'Baicheng Normal College', 44, 4939, true, 'http://');
INSERT INTO institution VALUES ('X4940', 'Inner Mongolia Agricultural University', 44, 4940, true, 'http://');
INSERT INTO institution VALUES ('X4941', 'Communication University of China', 44, 4941, true, 'http://');
INSERT INTO institution VALUES ('X4942', 'Inner Mongolia University of Technology', 44, 4942, true, 'http://');
INSERT INTO institution VALUES ('X4943', 'Inner Mongolia University of Science&Technology', 44, 4943, true, 'http://');
INSERT INTO institution VALUES ('X4944', 'Dalian Neusoft Institute of Information', 44, 4944, true, 'http://');
INSERT INTO institution VALUES ('X4945', 'Northeast Dianli University', 44, 4945, true, 'http://');
INSERT INTO institution VALUES ('X4946', 'Jilin Teachers Institute of Engineering & Technology', 44, 4946, true, 'http://');
INSERT INTO institution VALUES ('X4947', 'Changchun Vocational Institute of Technology', 44, 4947, true, 'http://');
INSERT INTO institution VALUES ('X4948', 'Mudanjiang Teachers College', 44, 4948, true, 'http://');
INSERT INTO institution VALUES ('X4949', 'Harbin Institute of Technology at Weihai', 44, 4949, true, 'http://');
INSERT INTO institution VALUES ('X4950', 'DaQing Petrol Institute', 44, 4950, true, 'http://');
INSERT INTO institution VALUES ('X4951', 'Suihua College', 44, 4951, true, 'http://');
INSERT INTO institution VALUES ('X4952', 'Shandong Economic University', 44, 4952, true, 'http://');
INSERT INTO institution VALUES ('X4953', 'ZheJiang Forest College', 44, 4953, true, 'http://');
INSERT INTO institution VALUES ('X4954', 'Hangzhou Dianzi University', 44, 4954, true, 'http://');
INSERT INTO institution VALUES ('X4955', 'Information Engineering College of Hangzhou Dianzi University', 44, 4955, true, 'http://');
INSERT INTO institution VALUES ('X4956', 'Ningbo Institute of Technology, Zhejiang University', 44, 4956, true, 'http://');
INSERT INTO institution VALUES ('X4957', 'Chengdu Neusoft Institute of Information', 44, 4957, true, 'http://');
INSERT INTO institution VALUES ('X4958', 'Huaqiao University', 44, 4958, true, 'http://');
INSERT INTO institution VALUES ('X4959', 'Beijing Jiaotong University', 44, 4959, true, 'http://');
INSERT INTO institution VALUES ('X4960', 'Southwest Petroleum University', 44, 4960, true, 'http://');
INSERT INTO institution VALUES ('X4961', 'Beijing Forestry University', 44, 4961, true, 'http://');
INSERT INTO institution VALUES ('X4962', 'Zhejiang University of Science & Technology', 44, 4962, true, 'http://');
INSERT INTO institution VALUES ('X4963', 'Southwest University for Nationalities', 44, 4963, true, 'http://');
INSERT INTO institution VALUES ('X4964', 'Changsha University', 44, 4964, true, 'http://');
INSERT INTO institution VALUES ('X4965', 'Xiangtan University', 44, 4965, true, 'http://');
INSERT INTO institution VALUES ('X4966', 'Civil Aviation University of China', 44, 4966, true, 'http://');
INSERT INTO institution VALUES ('X4967', 'Ningbo University', 44, 4967, true, 'http://');
INSERT INTO institution VALUES ('X4968', 'Hunan Agricultural University', 44, 4968, true, 'http://');
INSERT INTO institution VALUES ('X4969', 'Xuhai College, China University of Mining and Technology', 44, 4969, true, 'http://');
INSERT INTO institution VALUES ('X4970', 'China University of Petroleum', 44, 4970, true, 'http://');
INSERT INTO institution VALUES ('X4971', 'Henan University', 44, 4971, true, 'http://');
INSERT INTO institution VALUES ('X4972', 'Jilin Institute of Chemical Technology', 44, 4972, true, 'http://');
INSERT INTO institution VALUES ('X4973', 'East China Normal University', 44, 4973, true, 'http://');
INSERT INTO institution VALUES ('X4974', 'Central South University of Forestry and Technology', 44, 4974, true, 'http://');
INSERT INTO institution VALUES ('X4975', 'East China Jiaotong University', 44, 4975, true, 'http://');
INSERT INTO institution VALUES ('X4976', 'Fujian University of Technology', 44, 4976, true, 'http://');
INSERT INTO institution VALUES ('X4977', 'Shandong Jianzhu University', 44, 4977, true, 'http://');
INSERT INTO institution VALUES ('X4978', 'Hebei Normal University', 44, 4978, true, 'http://');
INSERT INTO institution VALUES ('X4979', 'Anhui University', 44, 4979, true, 'http://');
INSERT INTO institution VALUES ('X4980', 'Anhui University of Science and Technology', 44, 4980, true, 'http://');
INSERT INTO institution VALUES ('X4981', 'Beihang University', 44, 4981, true, 'http://');
INSERT INTO institution VALUES ('X4982', 'Beijing Institute of Graphic Communication', 44, 4982, true, 'http://');
INSERT INTO institution VALUES ('X4983', 'Beijing University of Chemical Technology', 44, 4983, true, 'http://');
INSERT INTO institution VALUES ('X4984', 'Capital University of Economics and Business', 44, 4984, true, 'http://');
INSERT INTO institution VALUES ('X4985', 'Changchun Institute of Technology', 44, 4985, true, 'http://');
INSERT INTO institution VALUES ('X4986', 'Changsha University of Science and Technology', 44, 4986, true, 'http://');
INSERT INTO institution VALUES ('X4987', 'Changzhou Institute of Technology', 44, 4987, true, 'http://');
INSERT INTO institution VALUES ('X4988', 'Chengdu University', 44, 4988, true, 'http://');
INSERT INTO institution VALUES ('X4989', 'China Jiliang University', 44, 4989, true, 'http://');
INSERT INTO institution VALUES ('X4990', 'China University of Geosciences, Beijing', 44, 4990, true, 'http://');
INSERT INTO institution VALUES ('X4991', 'China University of Mining and Technology', 44, 4991, true, 'http://');
INSERT INTO institution VALUES ('X4992', 'China University of Mining and Technology, Beijing', 44, 4992, true, 'http://');
INSERT INTO institution VALUES ('X4993', 'Chongqing Technology and Business University', 44, 4993, true, 'http://');
INSERT INTO institution VALUES ('X4994', 'City College of Wenzhou University', 44, 4994, true, 'http://');
INSERT INTO institution VALUES ('X4995', 'College of Jiaxing', 44, 4995, true, 'http://');
INSERT INTO institution VALUES ('X4996', 'Donghu College, Wuhan University', 44, 4996, true, 'http://');
INSERT INTO institution VALUES ('X4997', 'Guanghua College of Changchun University', 44, 4997, true, 'http://');
INSERT INTO institution VALUES ('X4998', 'Software College of Changchun Institute of Technology', 44, 4998, true, 'http://');
INSERT INTO institution VALUES ('X4999', 'Hebei University', 44, 4999, true, 'http://');
INSERT INTO institution VALUES ('X5000', 'Hebei University of Technology', 44, 5000, true, 'http://');
INSERT INTO institution VALUES ('X5001', 'Hefei University', 44, 5001, true, 'http://');
INSERT INTO institution VALUES ('X5002', 'Henan Polytechnic University', 44, 5002, true, 'http://');
INSERT INTO institution VALUES ('X5003', 'Hengyang Normal University', 44, 5003, true, 'http://');
INSERT INTO institution VALUES ('X5004', 'Hohai University', 44, 5004, true, 'http://');
INSERT INTO institution VALUES ('X5005', 'HuaiHua University', 44, 5005, true, 'http://');
INSERT INTO institution VALUES ('X5006', 'Huaiyin Institute of Technology', 44, 5006, true, 'http://');
INSERT INTO institution VALUES ('X5007', 'Huangshan University', 44, 5007, true, 'http://');
INSERT INTO institution VALUES ('X5008', 'Huazhong Normal University', 44, 5008, true, 'http://');
INSERT INTO institution VALUES ('X5009', 'Hubei University', 44, 5009, true, 'http://');
INSERT INTO institution VALUES ('X5010', 'Hubei University of Economics', 44, 5010, true, 'http://');
INSERT INTO institution VALUES ('X5011', 'Hubei University of Technology', 44, 5011, true, 'http://');
INSERT INTO institution VALUES ('X5012', 'Hunan City University', 44, 5012, true, 'http://');
INSERT INTO institution VALUES ('X5013', 'Hunan College of Finance and Economics', 44, 5013, true, 'http://');
INSERT INTO institution VALUES ('X5014', 'Hunan First Normal College', 44, 5014, true, 'http://');
INSERT INTO institution VALUES ('X5015', 'Hunan Institute of Science and Technology', 44, 5015, true, 'http://');
INSERT INTO institution VALUES ('X5016', 'Hunan International Economics University', 44, 5016, true, 'http://');
INSERT INTO institution VALUES ('X5017', 'Hunan Renwen Keji University', 44, 5017, true, 'http://');
INSERT INTO institution VALUES ('X5018', 'Hunan University of Science and Engineering', 44, 5018, true, 'http://');
INSERT INTO institution VALUES ('X5019', 'Hunan University of Technology', 44, 5019, true, 'http://');
INSERT INTO institution VALUES ('X5020', 'Huzhou Teachers College', 44, 5020, true, 'http://');
INSERT INTO institution VALUES ('X5021', 'Huzhou Vocational College', 44, 5021, true, 'http://');
INSERT INTO institution VALUES ('X5022', 'Jiangsu Teachers University of Technology', 44, 5022, true, 'http://');
INSERT INTO institution VALUES ('X5023', 'Jiangxi Normal University', 44, 5023, true, 'http://');
INSERT INTO institution VALUES ('X5024', 'Jiaxing Vocational Technical College', 44, 5024, true, 'http://');
INSERT INTO institution VALUES ('X5025', 'Jinhua College of Profession and Technology', 44, 5025, true, 'http://');
INSERT INTO institution VALUES ('X5026', 'Jishou University', 44, 5026, true, 'http://');
INSERT INTO institution VALUES ('X5027', 'Lanzhou Jiaotong University', 44, 5027, true, 'http://');
INSERT INTO institution VALUES ('X5028', 'Leshan Teachers College', 44, 5028, true, 'http://');
INSERT INTO institution VALUES ('X5029', 'Lishui University', 44, 5029, true, 'http://');
INSERT INTO institution VALUES ('X5030', 'Lishui Vocational College', 44, 5030, true, 'http://');
INSERT INTO institution VALUES ('X5031', 'Nanhang Jincheng College', 44, 5031, true, 'http://');
INSERT INTO institution VALUES ('X5032', 'Nanhua University (University of South China)', 44, 5032, true, 'http://');
INSERT INTO institution VALUES ('X5033', 'Nanjing University of Aeronautics and Astronautics', 44, 5033, true, 'http://');
INSERT INTO institution VALUES ('X5034', 'Nanjing University of Chinese Medicine', 44, 5034, true, 'http://');
INSERT INTO institution VALUES ('X5035', 'Nanjing University of Finance and Economics', 44, 5035, true, 'http://');
INSERT INTO institution VALUES ('X5036', 'Nanjing University of Posts and Telecommunications', 44, 5036, true, 'http://');
INSERT INTO institution VALUES ('X5037', 'Nanjing University of Technology', 44, 5037, true, 'http://');
INSERT INTO institution VALUES ('X5038', 'Nantong University', 44, 5038, true, 'http://');
INSERT INTO institution VALUES ('X5039', 'Neijiang Normal University', 44, 5039, true, 'http://');
INSERT INTO institution VALUES ('X5040', 'Ningbo City College of Vocational Technology', 44, 5040, true, 'http://');
INSERT INTO institution VALUES ('X5041', 'North China University of Technology', 44, 5041, true, 'http://');
INSERT INTO institution VALUES ('X5042', 'North University of China', 44, 5042, true, 'http://');
INSERT INTO institution VALUES ('X5043', 'PLA University of Science and Technology', 44, 5043, true, 'http://');
INSERT INTO institution VALUES ('X5044', 'Renai College of Tianjin University', 44, 5044, true, 'http://');
INSERT INTO institution VALUES ('X5045', 'Shandong University at Weihai', 44, 5045, true, 'http://');
INSERT INTO institution VALUES ('X5046', 'Shanghai Ocean University', 44, 5046, true, 'http://');
INSERT INTO institution VALUES ('X5047', 'Shaoxing College of Art and Science', 44, 5047, true, 'http://');
INSERT INTO institution VALUES ('X5048', 'Shaoyang University', 44, 5048, true, 'http://');
INSERT INTO institution VALUES ('X5049', 'Soochow University', 44, 5049, true, 'http://');
INSERT INTO institution VALUES ('X5145', 'Shaoxing University', 44, 5145, true, 'http://');
INSERT INTO institution VALUES ('X5050', 'Southwest University of Science and Technology', 44, 5050, true, 'http://');
INSERT INTO institution VALUES ('X5051', 'Suzhou University of Science and Technology', 44, 5051, true, 'http://');
INSERT INTO institution VALUES ('X5052', 'Taiyuan University of Technology', 44, 5052, true, 'http://');
INSERT INTO institution VALUES ('X5053', 'Taizhou University', 44, 5053, true, 'http://');
INSERT INTO institution VALUES ('X5054', 'Taizhou Vocational and Technical College', 44, 5054, true, 'http://');
INSERT INTO institution VALUES ('X5055', 'The Central South University', 44, 5055, true, 'http://');
INSERT INTO institution VALUES ('X5056', 'Tianjin University of Technology', 44, 5056, true, 'http://');
INSERT INTO institution VALUES ('X5057', 'Tianjin University of Technology and Education', 44, 5057, true, 'http://');
INSERT INTO institution VALUES ('X5058', 'University of Science and Technology Beijing', 44, 5058, true, 'http://');
INSERT INTO institution VALUES ('X5059', 'Wenzhou Medical College', 44, 5059, true, 'http://');
INSERT INTO institution VALUES ('X5060', 'Wenzhou University', 44, 5060, true, 'http://');
INSERT INTO institution VALUES ('X5061', 'Wenzhou Vocational and Technical College', 44, 5061, true, 'http://');
INSERT INTO institution VALUES ('X5062', 'West Anhui University', 44, 5062, true, 'http://');
INSERT INTO institution VALUES ('X5063', 'Xiangnan University', 44, 5063, true, 'http://');
INSERT INTO institution VALUES ('X5064', 'Yangzhou University', 44, 5064, true, 'http://');
INSERT INTO institution VALUES ('X5065', 'Yiwu Industrial and Commercial College', 44, 5065, true, 'http://');
INSERT INTO institution VALUES ('X5066', 'Yunnan University', 44, 5066, true, 'http://');
INSERT INTO institution VALUES ('X5067', 'Zaozhuang University', 44, 5067, true, 'http://');
INSERT INTO institution VALUES ('X5068', 'Zhejiang Chinese Medical University', 44, 5068, true, 'http://');
INSERT INTO institution VALUES ('X5069', 'Zhejiang Education Institute', 44, 5069, true, 'http://');
INSERT INTO institution VALUES ('X5070', 'Zhejiang Financial Professional College', 44, 5070, true, 'http://');
INSERT INTO institution VALUES ('X5071', 'Zhejiang Gongshang University', 44, 5071, true, 'http://');
INSERT INTO institution VALUES ('X5072', 'Zhejiang University of Media and Communications', 44, 5072, true, 'http://');
INSERT INTO institution VALUES ('X5073', 'Zhejiang Normal University', 44, 5073, true, 'http://');
INSERT INTO institution VALUES ('X5074', 'Zhejiang Ocean University', 44, 5074, true, 'http://');
INSERT INTO institution VALUES ('X5075', 'Zhejiang Shuren University', 44, 5075, true, 'http://');
INSERT INTO institution VALUES ('X5076', 'Zhejiang University of Finance and Economics', 44, 5076, true, 'http://');
INSERT INTO institution VALUES ('X5077', 'Zhejiang Vocational and Technical Institute of Transportation', 44, 5077, true, 'http://');
INSERT INTO institution VALUES ('X5078', 'Zhejiang College of Construction', 44, 5078, true, 'http://');
INSERT INTO institution VALUES ('X5079', 'Zhejiang Industry and Trade Polytechnic', 44, 5079, true, 'http://');
INSERT INTO institution VALUES ('X5080', 'Zhejiang Wanli University', 44, 5080, true, 'http://');
INSERT INTO institution VALUES ('X5081', 'Zhejiang Water Conservancy and Hydropower College', 44, 5081, true, 'http://');
INSERT INTO institution VALUES ('X5082', 'Zhejiang Yuying College of Vocational Technology', 44, 5082, true, 'http://');
INSERT INTO institution VALUES ('X5083', 'Zhengzhou University', 44, 5083, true, 'http://');
INSERT INTO institution VALUES ('X5084', 'Zhijiang College,Zhejiang University of Technology', 44, 5084, true, 'http://');
INSERT INTO institution VALUES ('X5085', 'Zhejiang Normal University Xingzhi College', 44, 5085, true, 'http://');
INSERT INTO institution VALUES ('X5086', 'Capital Normal University', 44, 5086, true, 'http://');
INSERT INTO institution VALUES ('X5087', 'ChangShu Institute of Technology', 44, 5087, true, 'http://');
INSERT INTO institution VALUES ('X5088', 'ChonqQing University of Posts and Telecommunications', 44, 5088, true, 'http://');
INSERT INTO institution VALUES ('X5089', 'China Agricultural University', 44, 5089, true, 'http://');
INSERT INTO institution VALUES ('X5090', 'Chengdu Vocational College of Agricultural Science And Technology', 44, 5090, true, 'http://');
INSERT INTO institution VALUES ('X5091', 'Sichuan Normal University', 44, 5091, true, 'http://');
INSERT INTO institution VALUES ('X5092', 'Xichang College', 44, 5092, true, 'http://');
INSERT INTO institution VALUES ('X5093', 'Luoyang Normal University', 44, 5093, true, 'http://');
INSERT INTO institution VALUES ('X5094', 'Chengdu University of Information Technology', 44, 5094, true, 'http://');
INSERT INTO institution VALUES ('X5095', 'Sichuan Agricultural University', 44, 5095, true, 'http://');
INSERT INTO institution VALUES ('X5096', 'Chengdu Vocational & Technical College', 44, 5096, true, 'http://');
INSERT INTO institution VALUES ('X5097', 'Anhui Normal University', 44, 5097, true, 'http://');
INSERT INTO institution VALUES ('X5098', 'Henan Normal University', 44, 5098, true, 'http://');
INSERT INTO institution VALUES ('X5099', 'Software Engineering Institute of ECNU', 44, 5099, true, 'http://');
INSERT INTO institution VALUES ('X5100', 'Ningbo University of Technology', 44, 5100, true, 'http://');
INSERT INTO institution VALUES ('X5101', 'Anqing Teachers College', 44, 5101, true, 'http://');
INSERT INTO institution VALUES ('X5102', 'Nanjing Forestry University', 44, 5102, true, 'http://');
INSERT INTO institution VALUES ('X5103', 'Wuhan University of Technology', 44, 5103, true, 'http://');
INSERT INTO institution VALUES ('X5104', 'Oujiang College of Wenzhou University', 44, 5104, true, 'http://');
INSERT INTO institution VALUES ('X5105', 'Beijing Institute of Technology, Zhuhai Campus', 44, 5105, true, 'http://');
INSERT INTO institution VALUES ('X5106', 'Hangzhou Normal University', 44, 5106, true, 'http://');
INSERT INTO institution VALUES ('X5107', 'Institute of Electronic Technology, the PLA Information Engineering University', 44, 5107, true, 'http://');
INSERT INTO institution VALUES ('X5108', 'Jiangsu Institute of Education', 44, 5108, true, 'http://');
INSERT INTO institution VALUES ('X5109', 'Hubei Automotive Industries Institute', 44, 5109, true, 'http://');
INSERT INTO institution VALUES ('X5110', 'Zhengzhou Institute of Aeronautical Industry Management', 44, 5110, true, 'http://');
INSERT INTO institution VALUES ('X5111', 'University of Science and Technology of Henan', 44, 5111, true, 'http://');
INSERT INTO institution VALUES ('X5112', 'Xinyang Normal University', 44, 5112, true, 'http://');
INSERT INTO institution VALUES ('X5113', 'Information Engineering University of the People''s Liberation Army', 44, 5113, true, 'http://');
INSERT INTO institution VALUES ('X5114', 'Henan University of Technology', 44, 5114, true, 'http://');
INSERT INTO institution VALUES ('X5115', 'Zhongyuan Institute of Technology', 44, 5115, true, 'http://');
INSERT INTO institution VALUES ('X5116', 'Light Engineering Institutes Of Zhengzhou', 44, 5116, true, 'http://');
INSERT INTO institution VALUES ('X5117', 'Anyang Normal College', 44, 5117, true, 'http://');
INSERT INTO institution VALUES ('X5118', 'Anyang Institute of Technology', 44, 5118, true, 'http://');
INSERT INTO institution VALUES ('X5119', 'Pingdingshan University', 44, 5119, true, 'http://');
INSERT INTO institution VALUES ('X5120', 'Nanchang University', 44, 5120, true, 'http://');
INSERT INTO institution VALUES ('X5121', 'Jiangsu University', 44, 5121, true, 'http://');
INSERT INTO institution VALUES ('X5122', 'Zhanjiangshifan University', 44, 5122, true, 'http://');
INSERT INTO institution VALUES ('X5123', 'Zhanjiang Normal University', 44, 5123, true, 'http://');
INSERT INTO institution VALUES ('X5124', 'City College, Dongguan University of Technology', 44, 5124, true, 'http://');
INSERT INTO institution VALUES ('X5125', 'Huhhot University for Nationalities', 44, 5125, true, 'http://');
INSERT INTO institution VALUES ('X5126', 'Southern Medical University', 44, 5126, true, 'http://');
INSERT INTO institution VALUES ('X5127', 'Harbin Deqiang College of Commerce', 44, 5127, true, 'http://');
INSERT INTO institution VALUES ('X5128', 'Hunan Institute of Engineering', 44, 5128, true, 'http://');
INSERT INTO institution VALUES ('X5129', 'Shenyang Ligong University', 44, 5129, true, 'http://');
INSERT INTO institution VALUES ('X5130', 'College of Optical and Electronic Information Changchun University of Science and Technology', 44, 5130, true, 'http://');
INSERT INTO institution VALUES ('X5131', 'Jilin Medical College', 44, 5131, true, 'http://');
INSERT INTO institution VALUES ('X5132', 'The City College of JiLin Architectural and Civil Engineering Institute', 44, 5132, true, 'http://');
INSERT INTO institution VALUES ('X5133', 'Shanghai Business School', 44, 5133, true, 'http://');
INSERT INTO institution VALUES ('X5134', 'Shanghai Jianqiao College', 44, 5134, true, 'http://');
INSERT INTO institution VALUES ('X5135', 'Chifeng University', 44, 5135, true, 'http://');
INSERT INTO institution VALUES ('X5136', 'Anhui XinHua University', 44, 5136, true, 'http://');
INSERT INTO institution VALUES ('X5137', 'Anshan Normal College', 44, 5137, true, 'http://');
INSERT INTO institution VALUES ('X5138', 'Jilin Province Economics and Management Cadres College', 44, 5138, true, 'http://');
INSERT INTO institution VALUES ('X5139', 'Xi''an Institute of Posts and Telecommunications', 44, 5139, true, 'http://');
INSERT INTO institution VALUES ('X5140', 'Shanghai Foreign Language School', 44, 5140, true, 'http://');
INSERT INTO institution VALUES ('X5141', 'Inner Mongolia Electronic Information Vocational Technical College', 44, 5141, true, 'http://');
INSERT INTO institution VALUES ('X5142', 'Inner Mongolia University for the Nationalities', 44, 5142, true, 'http://');
INSERT INTO institution VALUES ('X5143', 'Vocational and Technical College of Inner Mongolia Agricultural University', 44, 5143, true, 'http://');
INSERT INTO institution VALUES ('X5144', 'JiNing Teacher College', 44, 5144, true, 'http://');
INSERT INTO institution VALUES ('X5146', 'Beijing Technology and Business University', 44, 5146, true, 'http://');
INSERT INTO institution VALUES ('X5147', 'Shanghai Second Polytechnic University', 44, 5147, true, 'http://');
INSERT INTO institution VALUES ('X5148', 'Jiangxi University of Science and Technology', 44, 5148, true, 'http://');
INSERT INTO institution VALUES ('X5149', 'Zhangzhou Normal University', 44, 5149, true, 'http://');
INSERT INTO institution VALUES ('X5150', 'Jincheng College of Nanjing University of Aeronautics and Astronautics', 44, 5150, true, 'http://');
INSERT INTO institution VALUES ('X5151', 'Hunan Normal University', 44, 5151, true, 'http://');
INSERT INTO institution VALUES ('X5152', 'Huazhong University of Science and Technology, Qiming School', 44, 5152, true, 'http://');
INSERT INTO institution VALUES ('X5153', 'Fujian Agriculture and Forestry University', 44, 5153, true, 'http://');
INSERT INTO institution VALUES ('X5154', 'Engineering Commerce College of South-Central University for Nationalities', 44, 5154, true, 'http://');
INSERT INTO institution VALUES ('X5155', 'Shandong University of Technology', 44, 5155, true, 'http://');
INSERT INTO institution VALUES ('X5156', 'Zhengzhou University of Light Industry', 44, 5156, true, 'http://');
INSERT INTO institution VALUES ('X5157', 'Jimei University', 44, 5157, true, 'http://');
INSERT INTO institution VALUES ('X5158', 'Guangzhou Maritime College', 44, 5158, true, 'http://');
INSERT INTO institution VALUES ('X5159', 'Jiangxi Finance and Economics University', 44, 5159, true, 'http://');
INSERT INTO institution VALUES ('X5160', 'The North University for Ethnics', 44, 5160, true, 'http://');
INSERT INTO institution VALUES ('X5161', 'Soochow University Wen Zheng College', 44, 5161, true, 'http://');
INSERT INTO institution VALUES ('X5162', 'Fuzhou University, Zhicheng College', 44, 5162, true, 'http://');
INSERT INTO institution VALUES ('X5163', 'XuZhou Normal University', 44, 5163, true, 'http://');
INSERT INTO institution VALUES ('X5164', 'Anhui Institute of Architecture and Industry', 44, 5164, true, 'http://');
INSERT INTO institution VALUES ('X5165', 'Huangshi Institute of Technology', 44, 5165, true, 'http://');
INSERT INTO institution VALUES ('X5166', 'Southeast University', 44, 5166, true, 'http://');
INSERT INTO institution VALUES ('X5167', 'Zhongyuan University of Technology', 44, 5167, true, 'http://');
INSERT INTO institution VALUES ('X5168', 'JiangNan University', 44, 5168, true, 'http://');
INSERT INTO institution VALUES ('X5169', 'Hebei Polytechnic University', 44, 5169, true, 'http://');
INSERT INTO institution VALUES ('X5170', 'Zhuhai Campus, Jinan University', 44, 5170, true, 'http://');
INSERT INTO institution VALUES ('X5171', 'Xiamen University of Technology', 44, 5171, true, 'http://');
INSERT INTO institution VALUES ('X5172', 'Shanghai University of Engineering Science', 44, 5172, true, 'http://');
INSERT INTO institution VALUES ('X5173', 'Hunan University of Commerce', 44, 5173, true, 'http://');
INSERT INTO institution VALUES ('X5174', 'Central South University, School of Software', 44, 5174, true, 'http://');
INSERT INTO institution VALUES ('X5175', 'Hohai University, Changzhou', 44, 5175, true, 'http://');
INSERT INTO institution VALUES ('X5176', 'Beijing Electronic Science and Technology Institute', 44, 5176, true, 'http://');
INSERT INTO institution VALUES ('X5177', 'Minjiang University', 44, 5177, true, 'http://');
INSERT INTO institution VALUES ('X5178', 'Beijing Union University', 44, 5178, true, 'http://');
INSERT INTO institution VALUES ('X5179', 'Tongda College of Nanjing University of Posts and Telecommunications', 44, 5179, true, 'http://');
INSERT INTO institution VALUES ('X5180', 'South-Central University for Nationalities', 44, 5180, true, 'http://');
INSERT INTO institution VALUES ('X5181', 'Beifang Ethnic University', 44, 5181, true, 'http://');
INSERT INTO institution VALUES ('X5182', 'NYIT-NUPT', 44, 5182, true, 'http://');
INSERT INTO institution VALUES ('X5183', 'Shenzhen Institute of Technology', 44, 5183, true, 'http://');
INSERT INTO institution VALUES ('X5184', 'Guilin University of Electronic Technology', 44, 5184, true, 'http://');
INSERT INTO institution VALUES ('X5185', 'Qingdao University', 44, 5185, true, 'http://');
INSERT INTO institution VALUES ('X5186', 'Zhejiang Industry Polytechnic College', 44, 5186, true, 'http://');
INSERT INTO institution VALUES ('X5187', 'Zhejiang Textile and Fashion College', 44, 5187, true, 'http://');
INSERT INTO institution VALUES ('X5188', 'Binjiang College of Zhejiang Chinese Medical University', 44, 5188, true, 'http://');
INSERT INTO institution VALUES ('X5189', 'Top Vocational Institute of Information & Technology of Shaoxing', 44, 5189, true, 'http://');
INSERT INTO institution VALUES ('X5190', 'Zhejiang Guangsha College of Construction and Vocational Technology', 44, 5190, true, 'http://');
INSERT INTO institution VALUES ('X5191', 'Tourism College of Zhejiang', 44, 5191, true, 'http://');
INSERT INTO institution VALUES ('X5192', 'Zhejiang Police Vocational Academy', 44, 5192, true, 'http://');
INSERT INTO institution VALUES ('X5193', 'Hangzhou Academy of Scientific Professional Technology', 44, 5193, true, 'http://');
INSERT INTO institution VALUES ('X5194', 'Zhejiang Economic & Trade Polytechnic', 44, 5194, true, 'http://');
INSERT INTO institution VALUES ('X5195', 'Jiaxing University Nanhu College', 44, 5195, true, 'http://');
INSERT INTO institution VALUES ('X5289', 'Universidad de Manizales', 46, 5289, true, 'http://');
INSERT INTO institution VALUES ('X5196', 'Wenzhou Vocational College of Science and Technology', 44, 5196, true, 'http://');
INSERT INTO institution VALUES ('X5197', 'Zhejiang Business Technology Institute', 44, 5197, true, 'http://');
INSERT INTO institution VALUES ('X5198', 'Hangzhou Vocational and Technical College', 44, 5198, true, 'http://');
INSERT INTO institution VALUES ('X5199', 'Shaoxing University Yuanpei College', 44, 5199, true, 'http://');
INSERT INTO institution VALUES ('X5200', 'Zhejiang Forestry University TianMu College', 44, 5200, true, 'http://');
INSERT INTO institution VALUES ('X5201', 'Zhejiang Forestry College', 44, 5201, true, 'http://');
INSERT INTO institution VALUES ('X5202', 'Ningbo Polytechnic', 44, 5202, true, 'http://');
INSERT INTO institution VALUES ('X5203', 'West Branch of Zhejiang University of Technology', 44, 5203, true, 'http://');
INSERT INTO institution VALUES ('X5204', 'Ningbo Dahongying University', 44, 5204, true, 'http://');
INSERT INTO institution VALUES ('X5205', 'QiuZhen School of Huzhou Teachers College', 44, 5205, true, 'http://');
INSERT INTO institution VALUES ('X5206', 'Quzhou College of Technology', 44, 5206, true, 'http://');
INSERT INTO institution VALUES ('X5207', 'Zhejiang ChangZheng Vocational & Technical College', 44, 5207, true, 'http://');
INSERT INTO institution VALUES ('X5208', 'DongFang College, Zhejiang University of Finance and Economics', 44, 5208, true, 'http://');
INSERT INTO institution VALUES ('X5209', 'Zhejiang Police College', 44, 5209, true, 'http://');
INSERT INTO institution VALUES ('X5210', 'Zhejiang Institute of Mechanical & Electrical Engineering', 44, 5210, true, 'http://');
INSERT INTO institution VALUES ('X5211', 'College of Science & Technology Ningbo University', 44, 5211, true, 'http://');
INSERT INTO institution VALUES ('X5212', 'College of Science and Art, Zhejiang Sci-Tech University', 44, 5212, true, 'http://');
INSERT INTO institution VALUES ('X5213', 'Guizhou University', 44, 5213, true, 'http://');
INSERT INTO institution VALUES ('X5214', 'Shangyu College Shaoxin University', 44, 5214, true, 'http://');
INSERT INTO institution VALUES ('X5215', 'BoHai University', 44, 5215, true, 'http://');
INSERT INTO institution VALUES ('X5216', 'Eastern Liaoning University', 44, 5216, true, 'http://');
INSERT INTO institution VALUES ('X5217', 'DaQing Petroleum Institution', 44, 5217, true, 'http://');
INSERT INTO institution VALUES ('X5218', 'Liaoning Universtity of Traditional Chinese Medicine', 44, 5218, true, 'http://');
INSERT INTO institution VALUES ('X5219', 'Liaoning Technical University', 44, 5219, true, 'http://');
INSERT INTO institution VALUES ('X5220', 'China Criminal Police College', 44, 5220, true, 'http://');
INSERT INTO institution VALUES ('X5221', 'Zhongkai University of Agriculture and Engineering', 44, 5221, true, 'http://');
INSERT INTO institution VALUES ('X5222', 'Quanzhou Normal University', 44, 5222, true, 'http://');
INSERT INTO institution VALUES ('X5223', 'Dalian Ocean University', 44, 5223, true, 'http://');
INSERT INTO institution VALUES ('X5224', 'Shenyang Institute of Engineering', 44, 5224, true, 'http://');
INSERT INTO institution VALUES ('X5225', 'Hunan University of Science and Technology', 44, 5225, true, 'http://');
INSERT INTO institution VALUES ('X5226', 'Keerqin Art Vocational College', 44, 5226, true, 'http://');
INSERT INTO institution VALUES ('X5227', 'Hulunbeier College', 44, 5227, true, 'http://');
INSERT INTO institution VALUES ('X5228', 'Inner Mongolia Business and Trade Vocational College', 44, 5228, true, 'http://');
INSERT INTO institution VALUES ('X5229', 'Baotou Teachers College', 44, 5229, true, 'http://');
INSERT INTO institution VALUES ('X5230', 'Inner Mongolia Technological College of Machinery and Electronics', 44, 5230, true, 'http://');
INSERT INTO institution VALUES ('X5231', 'Sanming University', 44, 5231, true, 'http://');
INSERT INTO institution VALUES ('X5232', 'Fuzhou University, Yangguang College', 44, 5232, true, 'http://');
INSERT INTO institution VALUES ('X5233', 'Northeast Petroleum University', 44, 5233, true, 'http://');
INSERT INTO institution VALUES ('X5234', 'llege Of  Humanities & Information  Changchun University of Technology', 44, 5234, true, 'http://');
INSERT INTO institution VALUES ('X5235', 'llege of Humanities & Information Changchun University of Technology', 44, 5235, true, 'http://');
INSERT INTO institution VALUES ('X5236', 'College of Humanities & Information Changchun University of Technology', 44, 5236, true, 'http://');
INSERT INTO institution VALUES ('X5237', 'ShenYang University of Chemical Technology', 44, 5237, true, 'http://');
INSERT INTO institution VALUES ('X5239', 'Corporación Universitaria de la Costa', 46, 5239, true, 'http://');
INSERT INTO institution VALUES ('X5240', 'Universidad Autonoma de Manizales', 46, 5240, true, 'http://');
INSERT INTO institution VALUES ('X5243', 'Escuela Colombiana de Ingeniería', 46, 5243, true, 'http://');
INSERT INTO institution VALUES ('X5244', 'Instituto Tecnológico de Soledad - Atlántico', 46, 5244, true, 'http://');
INSERT INTO institution VALUES ('X5245', 'Politécnico Grancolombiano', 46, 5245, true, 'http://');
INSERT INTO institution VALUES ('X5249', 'Universidad de Antioquia', 46, 5249, true, 'http://');
INSERT INTO institution VALUES ('X5250', 'Universidad de Medellín', 46, 5250, true, 'http://');
INSERT INTO institution VALUES ('X5256', 'Universidad Santiago de Cali', 46, 5256, true, 'http://');
INSERT INTO institution VALUES ('X5258', 'Universidad Industrial de Santander', 46, 5258, true, 'http://');
INSERT INTO institution VALUES ('X5261', 'Escuela de Ingeniería de Antioquia', 46, 5261, true, 'http://');
INSERT INTO institution VALUES ('X5262', 'Fundación Universitaria Konrad Lorenz', 46, 5262, true, 'http://');
INSERT INTO institution VALUES ('X5263', 'Fundación Universitaria Tecnológico Comfenalco', 46, 5263, true, 'http://');
INSERT INTO institution VALUES ('X5264', 'Institución Universitaria de Envigado', 46, 5264, true, 'http://');
INSERT INTO institution VALUES ('X5265', 'Politécnico Colombiano Jaime Isaza Cadavid', 46, 5265, true, 'http://');
INSERT INTO institution VALUES ('X5266', 'Universidad Autónoma de Colombia', 46, 5266, true, 'http://');
INSERT INTO institution VALUES ('X5267', 'Universidad Antonio Narino', 46, 5267, true, 'http://');
INSERT INTO institution VALUES ('X5268', 'Universidad Autónoma de Occidente', 46, 5268, true, 'http://');
INSERT INTO institution VALUES ('X5269', 'Universidad El Bosque', 46, 5269, true, 'http://');
INSERT INTO institution VALUES ('X5270', 'Universidad Cooperativa de Colombia - Bucaramanga', 46, 5270, true, 'http://');
INSERT INTO institution VALUES ('X5271', 'Universidad de Ibague', 46, 5271, true, 'http://');
INSERT INTO institution VALUES ('X5272', 'Universidad Libre -Bogota', 46, 5272, true, 'http://');
INSERT INTO institution VALUES ('X5273', 'Universidad de La Salle', 46, 5273, true, 'http://');
INSERT INTO institution VALUES ('X5278', 'Universidad Piloto de Colombia - Bogota', 46, 5278, true, 'http://');
INSERT INTO institution VALUES ('UNIANDES', 'Universidad de los Andes', 46, 5238, true, 'http://www.uniandes.edu.co/');
INSERT INTO institution VALUES ('UPBB', 'Universidad Pontificia Bolivariana - Bucaramanga', 46, 5276, true, 'http://www.upb.edu.co/');
INSERT INTO institution VALUES ('ICESI', 'Universidad Icesi', 46, 5254, true, 'http://www.icesi.edu.co/');
INSERT INTO institution VALUES ('PUJCA', 'Pontificia Universidad Javeriana - Cali', 46, 5246, true, 'http://www.javeriana.edu.co/');
INSERT INTO institution VALUES ('PUJBO', 'Pontificia Universidad Javeriana - Bogota', 46, 5241, true, 'http://www.javeriana.edu.co/');
INSERT INTO institution VALUES ('EAFIT', 'EAFIT University', 46, 5259, true, 'http://');
INSERT INTO institution VALUES ('UNITEC', 'Corporación Universitaria Unitec', 46, 5242, true, 'http://www.unitec.edu.co/');
INSERT INTO institution VALUES ('UDIC', 'Universidad Distrital', 46, 5253, true, 'http://www.udistrital.edu.co/');
INSERT INTO institution VALUES ('X5279', 'Universidad Piloto de Colombia - Girardot', 46, 5279, true, 'http://');
INSERT INTO institution VALUES ('X5280', 'Universidad Autónoma de Bucaramanga', 46, 5280, true, 'http://');
INSERT INTO institution VALUES ('X5281', 'Universidad Tecnológica de Bolivar', 46, 5281, true, 'http://');
INSERT INTO institution VALUES ('X5282', 'Fundacion CIDCA', 46, 5282, true, 'http://');
INSERT INTO institution VALUES ('X5283', 'Universidad de Cartagena', 46, 5283, true, 'http://');
INSERT INTO institution VALUES ('X5285', 'Fundación Universitaria Los Libertadores', 46, 5285, true, 'http://');
INSERT INTO institution VALUES ('EAN', 'Universidad EAN', 46, 5260, true, 'http://www.ean.edu.co/');
INSERT INTO institution VALUES ('UQUINDIO', 'Universidad del Quindio', 46, 5251, true, 'http://www.uniquindio.edu.co/');
INSERT INTO institution VALUES ('X5286', 'Universidad Autónoma del Caribe', 46, 5286, true, 'http://');
INSERT INTO institution VALUES ('X5287', 'Universidad de San Gil', 46, 5287, true, 'http://');
INSERT INTO institution VALUES ('X5288', 'Universidad de Caldas', 46, 5288, true, 'http://');
INSERT INTO institution VALUES ('X5290', 'Universidad San Buenaventura - Bogotá', 46, 5290, true, 'http://');
INSERT INTO institution VALUES ('X5291', 'Universidad de Pamplona', 46, 5291, true, 'http://');
INSERT INTO institution VALUES ('X5292', 'Universidad Cooperativa de Colombia Sede B/Meja', 46, 5292, true, 'http://');
INSERT INTO institution VALUES ('X5293', 'Universidad Popular del Cesar', 46, 5293, true, 'http://');
INSERT INTO institution VALUES ('X5295', 'Faculty of Science and Mathematics - Navi Sadu', 51, 5295, true, 'http://');
INSERT INTO institution VALUES ('X5296', 'University of Nis - Faculty of Electronic Engineering', 51, 5296, true, 'http://');
INSERT INTO institution VALUES ('UNALM', 'Universidad Nacional de Colombia - Medellin', 46, 5275, true, 'http://www.unal.edu.co/');
INSERT INTO institution VALUES ('TEC', 'Instituto Tecnológico de Costa Rica', 50, 5294, true, 'http://www.tec.cr/');
INSERT INTO institution VALUES ('UNIZG', 'University of Zagreb', 51, 5297, true, 'http://www.unizg.hr/homepage/‎');
INSERT INTO institution VALUES ('UNINORTE', 'Universidad del Norte', 46, 5284, true, 'http://www.uninorte.edu.co/');
INSERT INTO institution VALUES ('IPVCE-CM', 'IPVCE "Máximo Gómez"', 52, 8140, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('IPVCE-SS', 'IPVCE "Eusebio Olivera"', 52, 8138, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('IPVCE-VC', 'IPVCE "Ernesto Guevara"', 52, 8136, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('IPVCE-GT', 'IPVCE "José Maceo"', 52, 8145, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('IPVCE-GR', 'IPVCE "Silberto Alvarez Aroche"', 52, 8143, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('IPVCE-HO', 'IPVCE "Jose Martí"', 52, 8142, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('IPVCE-SC', 'IPVCE "Antonio Maceo"', 52, 8144, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('IPVCE-AR', 'IPVCE "Mártires de Humbolt 7"', 52, 8133, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('X4679', 'American University in Bulgaria', 33, 4679, true, 'http://');
INSERT INTO institution VALUES ('X4680', 'MG Atanas Radev - Jambol', 33, 4680, true, 'http://');
INSERT INTO institution VALUES ('X4681', 'Plovdiv University', 33, 4681, true, 'http://');
INSERT INTO institution VALUES ('X4682', 'Sofia University', 33, 4682, true, 'http://');
INSERT INTO institution VALUES ('X4683', 'Technical University of Varna', 33, 4683, true, 'http://');
INSERT INTO institution VALUES ('X4684', 'University of Plovdiv', 33, 4684, true, 'http://');
INSERT INTO institution VALUES ('X4685', 'University of Plovdiv Paisii Hilendarski', 33, 4685, true, 'http://');
INSERT INTO institution VALUES ('X4686', 'University of Rousse', 33, 4686, true, 'http://');
INSERT INTO institution VALUES ('X4687', '"Konstantin Preslavsky" University of Shumen', 33, 4687, true, 'http://');
INSERT INTO institution VALUES ('X4688', 'Ecole Supérieure d Informatique et de Gestion', 34, 4688, true, 'http://');
INSERT INTO institution VALUES ('X4690', 'Acadia University', 38, 4690, true, 'http://');
INSERT INTO institution VALUES ('X4691', 'Algoma University College', 38, 4691, true, 'http://');
INSERT INTO institution VALUES ('X4692', 'British Columbia Institute of Technology', 38, 4692, true, 'http://');
INSERT INTO institution VALUES ('X4693', 'Brock University', 38, 4693, true, 'http://');
INSERT INTO institution VALUES ('X4694', 'Carleton University', 38, 4694, true, 'http://');
INSERT INTO institution VALUES ('X4696', 'Conestoga College Institute of Technology and Advanced Learning', 38, 4696, true, 'http://');
INSERT INTO institution VALUES ('X4697', 'Dalhousie University', 38, 4697, true, 'http://');
INSERT INTO institution VALUES ('X4698', 'DeVry Institute of Technology - Calgary', 38, 4698, true, 'http://');
INSERT INTO institution VALUES ('X4699', 'Ecole de Technologie Supérieure', 38, 4699, true, 'http://');
INSERT INTO institution VALUES ('X4700', 'Fanshawe College', 38, 4700, true, 'http://');
INSERT INTO institution VALUES ('X4701', 'Lakehead University', 38, 4701, true, 'http://');
INSERT INTO institution VALUES ('X4702', 'Lethbridge Community College', 38, 4702, true, 'http://');
INSERT INTO institution VALUES ('X4703', 'McGill University', 38, 4703, true, 'http://');
INSERT INTO institution VALUES ('X4704', 'Memorial University of Newfoundland', 38, 4704, true, 'http://');
INSERT INTO institution VALUES ('X4705', 'Mount Allison University', 38, 4705, true, 'http://');
INSERT INTO institution VALUES ('X4706', 'Mount Royal College', 38, 4706, true, 'http://');
INSERT INTO institution VALUES ('X4707', 'Queen''s University', 38, 4707, true, 'http://');
INSERT INTO institution VALUES ('X4708', 'Ryerson Polytechnic University', 38, 4708, true, 'http://');
INSERT INTO institution VALUES ('X4709', 'Saint Mary''s University', 38, 4709, true, 'http://');
INSERT INTO institution VALUES ('X4710', 'Sheridan College', 38, 4710, true, 'http://');
INSERT INTO institution VALUES ('X4711', 'Sheridan College - Trafalgar Campus', 38, 4711, true, 'http://');
INSERT INTO institution VALUES ('X4712', 'Sheridan College Davis Campus', 38, 4712, true, 'http://');
INSERT INTO institution VALUES ('X4713', 'Sheridan College Trafalgar Campus', 38, 4713, true, 'http://');
INSERT INTO institution VALUES ('X4714', 'Sheridan Institute of ATAL Davis Campus', 38, 4714, true, 'http://');
INSERT INTO institution VALUES ('X4715', 'Sheridan Institute of Technology and Advanced Learning', 38, 4715, true, 'http://');
INSERT INTO institution VALUES ('X4717', 'St. Francis Xavier University', 38, 4717, true, 'http://');
INSERT INTO institution VALUES ('X4718', 'The University of Western Ontario', 38, 4718, true, 'http://');
INSERT INTO institution VALUES ('X4719', 'Trent University', 38, 4719, true, 'http://');
INSERT INTO institution VALUES ('X4720', 'Trinity Western University', 38, 4720, true, 'http://');
INSERT INTO institution VALUES ('X4721', 'Univeristy of Prince Edward Island', 38, 4721, true, 'http://');
INSERT INTO institution VALUES ('X4722', 'Université de Moncton', 38, 4722, true, 'http://');
INSERT INTO institution VALUES ('X4723', 'Université du Québec en Outaouais', 38, 4723, true, 'http://');
INSERT INTO institution VALUES ('X4724', 'Université Laval', 38, 4724, true, 'http://');
INSERT INTO institution VALUES ('X4726', 'University of British Columbia', 38, 4726, true, 'http://');
INSERT INTO institution VALUES ('X4727', 'University of Calgary', 38, 4727, true, 'http://');
INSERT INTO institution VALUES ('X4728', 'University of Guelph', 38, 4728, true, 'http://');
INSERT INTO institution VALUES ('X4729', 'University of Lethbridge', 38, 4729, true, 'http://');
INSERT INTO institution VALUES ('X4730', 'University of New Brunswick at Fredericton', 38, 4730, true, 'http://');
INSERT INTO institution VALUES ('X4731', 'University of New Brunswick Saint John', 38, 4731, true, 'http://');
INSERT INTO institution VALUES ('X4732', 'University of Ottawa', 38, 4732, true, 'http://');
INSERT INTO institution VALUES ('X4733', 'University of Saskatchewan', 38, 4733, true, 'http://');
INSERT INTO institution VALUES ('X4734', 'University of Toronto', 38, 4734, true, 'http://');
INSERT INTO institution VALUES ('X4735', 'University of Waterloo', 38, 4735, true, 'http://');
INSERT INTO institution VALUES ('X4736', 'University of Windsor', 38, 4736, true, 'http://');
INSERT INTO institution VALUES ('X4737', 'York University', 38, 4737, true, 'http://');
INSERT INTO institution VALUES ('X4738', 'Thompson Rivers University', 38, 4738, true, 'http://');
INSERT INTO institution VALUES ('X4739', 'University of Sherbrooke', 38, 4739, true, 'http://');
INSERT INTO institution VALUES ('X4740', 'McMaster University', 38, 4740, true, 'http://');
INSERT INTO institution VALUES ('X4741', 'University of Ontario Institute of Technology', 38, 4741, true, 'http://');
INSERT INTO institution VALUES ('X4742', 'Wilfrid Laurier University', 38, 4742, true, 'http://');
INSERT INTO institution VALUES ('X4743', 'University of Manitoba', 38, 4743, true, 'http://');
INSERT INTO institution VALUES ('X4744', 'University of Northern British Columbia', 38, 4744, true, 'http://');
INSERT INTO institution VALUES ('X4745', 'University of Victoria', 38, 4745, true, 'http://');
INSERT INTO institution VALUES ('X4748', 'Universidad Católica de Valparaíso', 43, 4748, true, 'http://');
INSERT INTO institution VALUES ('X4749', 'Universidad Católica del Maule', 43, 4749, true, 'http://');
INSERT INTO institution VALUES ('X4750', 'Universidad Católica del Norte', 43, 4750, true, 'http://');
INSERT INTO institution VALUES ('X4751', 'Universidad de Atacama', 43, 4751, true, 'http://');
INSERT INTO institution VALUES ('X4755', 'Universidad de Talca', 43, 4755, true, 'http://');
INSERT INTO institution VALUES ('X4757', 'UNIVERSIDAD MAYOR', 43, 4757, true, 'http://');
INSERT INTO institution VALUES ('X4758', 'Universidad Técnica Federico Santa María', 43, 4758, true, 'http://');
INSERT INTO institution VALUES ('X4760', 'University of Magallanes', 43, 4760, true, 'http://');
INSERT INTO institution VALUES ('X4761', 'Universidad de Magallanes', 43, 4761, true, 'http://');
INSERT INTO institution VALUES ('X4762', 'Instituto Nacional de Capacitación', 43, 4762, true, 'http://');
INSERT INTO institution VALUES ('ESSA', 'Ecole Supérieure des Sciences Appliquées', 34, 4689, true, 'http://');
INSERT INTO institution VALUES ('X4764', 'Universidad de La Serena', 43, 4764, true, 'http://');
INSERT INTO institution VALUES ('X4765', 'Instituto Professional Santo Tomás', 43, 4765, true, 'http://');
INSERT INTO institution VALUES ('X4766', 'Universidad Diego Portales', 43, 4766, true, 'http://');
INSERT INTO institution VALUES ('X4767', 'Instituto Profesional Santo Tomás, Concepción', 43, 4767, true, 'http://');
INSERT INTO institution VALUES ('X4768', 'University of Atacama', 43, 4768, true, 'http://');
INSERT INTO institution VALUES ('X4769', 'Universidad Central de Chile', 43, 4769, true, 'http://');
INSERT INTO institution VALUES ('X4770', 'Universidad Tecnológica Metropolitana', 43, 4770, true, 'http://');
INSERT INTO institution VALUES ('X4771', 'INACAP', 43, 4771, true, 'http://');
INSERT INTO institution VALUES ('X4772', 'Beijing Institute of Technology', 44, 4772, true, 'http://');
INSERT INTO institution VALUES ('UACH', 'Universidad Austral de Chile', 43, 4763, true, 'http://www.uach.cl/‎');
INSERT INTO institution VALUES ('PUCCL', 'Pontificia Universidad Católica de Chile', 43, 4747, true, 'http://www.uc.cl/');
INSERT INTO institution VALUES ('UBIOBIO', 'Universidad del Bio Bio', 43, 4756, true, 'http://www.ubiobio.cl/‎');
INSERT INTO institution VALUES ('UTP', 'Universidad Tecnológica de Pereira', 46, 5257, true, 'http://');
INSERT INTO institution VALUES ('UCR', 'Universidad de Costa Rica', 50, 7, true, 'http://www.ucr.ac.cr/');
INSERT INTO institution VALUES ('ECHNU', 'Eastern China Normal University', 44, 4789, true, 'http://');
INSERT INTO institution VALUES ('SFU', 'Simon Fraser University', 38, 4716, true, 'http://');
INSERT INTO institution VALUES ('IPITLG', 'Instituto Politécnico de Informática "Tania La Guerrillera"', 52, 28, true, 'http://');
INSERT INTO institution VALUES ('CONU', 'Concordia University', 38, 4695, true, 'http://');
INSERT INTO institution VALUES ('DSFTVCL', 'Desoft - Villa Clara', 52, 8154, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTMTZ', 'Desoft - Matanzas', 52, 9, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTCFG', 'Desoft - Cienfuegos', 52, 10, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTSSP', 'Desoft - Sancti Spíritus', 52, 11, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTCAV', 'Desoft - Ciego de Ávila', 52, 12, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTCMG', 'Desoft - Camaguey', 52, 13, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTLTU', 'Desoft - Las Tunas', 52, 14, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTGRM', 'Desoft - Granma', 52, 16, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTISJ', 'Desoft - Isla de la Juventud', 52, 19, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTART', 'Desoft - Artemisa', 52, 20, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTMAY', 'Desoft - Mayabeque', 52, 21, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTHOL', 'Desoft - Holguín', 52, 15, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTLHA', 'Desoft - La Habana', 52, 8, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('DSFTGTM', 'Desoft - Guantánamo', 52, 18, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('CEDAI', 'Centro de Automatización Integral', 52, 29, true, 'http://www.cedai.com.cu/');
INSERT INTO institution VALUES ('IIA', 'Instituto de Investigaciones Agropecuarias "Jorge Dimitrov"', 52, 8153, true, 'http://');
INSERT INTO institution VALUES ('ALBERTA', 'University of Alberta', 38, 4725, true, 'http://');
INSERT INTO institution VALUES ('DSFTSCU', 'Desoft - Santiago de Cuba', 52, 17, true, 'http://www.desoft.cu/');
INSERT INTO institution VALUES ('UTFSM', 'Universidad Técnica Federico Santa María - Campus Santiago', 43, 4759, true, 'http://www.utfsm.cl/‎');
INSERT INTO institution VALUES ('UNICA', 'Universidad de Ciego de Ávila', 52, 158, true, 'http://www.unica.cu/');
INSERT INTO institution VALUES ('UCP-CMC', 'Universidad de Ciencias Pedagógicas "Carlos Manuel de Céspedes"', 52, 180, true, 'http://www.ucp.ij.rimed.cu/');
INSERT INTO institution VALUES ('UCP-FV', 'Universidad de Ciencias Pedagógicas "Félix Varela"', 52, 170, true, 'http://www.ucp.vc.rimed.cu/');
INSERT INTO institution VALUES ('UCP-RMV', 'Universidad de Ciencias Pedagógicas "Rubén Martínez Villena"', 52, 166, true, 'http://www.ucpvillena.rimed.cu');
INSERT INTO institution VALUES ('UDG-M', 'Universidad de Granma - Manzanillo', 52, 8148, true, 'http://www.udg.co.cu/');
INSERT INTO institution VALUES ('UG', 'Universidad de Guantánamo', 52, 154, true, 'http://www.cug.co.cu/');
INSERT INTO institution VALUES ('UCI-FRA', 'Universidad de las Ciencias Informáticas - Fac Regional de Artemisa', 52, 8150, true, 'http://www.uci.cu/');
INSERT INTO institution VALUES ('UO-SAM', 'Universidad de Oriente - Sede Antonio Maceo', 52, 153, true, 'http://www.uo.edu.cu/');
INSERT INTO institution VALUES ('UO-SJAM', 'Universidad de Oriente - Sede Julio Antonio Mella', 52, 8151, true, 'http://www.uo.edu.cu/');
INSERT INTO institution VALUES ('CUJAE', 'Instituto Superior Politécnico "José Antonio Echeverría"', 52, 146, true, 'http://www.cujae.edu.cu/');
INSERT INTO institution VALUES ('ACINOX', 'Empresa de Aceros Inoxidables - Tunas', 52, 40, true, 'http://');
INSERT INTO institution VALUES ('UDV', 'Universidad del Valle', 46, 5252, true, 'http://www.univalle.edu.co/');
INSERT INTO institution VALUES ('UNALB', 'Universidad Nacional de Colombia - Bogotá', 46, 5274, true, 'http://www.unal.edu.co/');
INSERT INTO institution VALUES ('IPVCE-MY', 'IPVCE "Félix Varela"', 52, 8134, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('IPVCE-LH', 'IPVCE "Vladimir Ilich Lenin" ', 52, 8132, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('IPVCE-CA', 'IPVCE "Cándido González Morales"', 52, 8139, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('IPVCE-PR', 'IPVCE "Federico Engels"', 52, 8131, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('PSNIC', 'Preselección Nacional Cubana de Informática', 52, 61, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('UNAH', 'Universidad Agraria de La Habana', 52, 156, true, 'http://www.isch.edu.cu/');
INSERT INTO institution VALUES ('UCP-BRC', 'Universidad de Ciencias Pedagógicas "Blas Roca Calderío"', 52, 177, true, 'http://www.ucp.gr.rimed.cu/');
INSERT INTO institution VALUES ('UCP-JLC', 'Universidad de Ciencias Pedagógicas "José de la Luz y Caballero"', 52, 176, true, 'http://ucp.ho.rimed.cu/');
INSERT INTO institution VALUES ('UCI-FRC', 'Universidad de las Ciencias Informáticas - Facultad Regional de Ciego', 52, 8149, true, 'http://www.uci.cu/');
INSERT INTO institution VALUES ('UCHILE', 'Universidad de Chile', 43, 4752, true, 'http://www.uchile.cl/');
INSERT INTO institution VALUES ('USACH', 'Universidad de Santiago de Chile', 43, 4754, true, 'http://www.udesantiago.cl/');
INSERT INTO institution VALUES ('UNACR', 'Universidad Nacional de Costa Rica', 50, 69, true, 'http://www.una.ac.cr/');
INSERT INTO institution VALUES ('INSTEC', 'Instituto Superior de Tecnologías y Ciencias Aplicadas', 52, 164, true, 'http://www.instec.cu/');
INSERT INTO institution VALUES ('UCP-FPG', 'Universidad de Ciencias Pedagógicas "Frank País García"', 52, 178, true, 'http://www.ucp.sc.rimed.cu/');
INSERT INTO institution VALUES ('IPVCE-CF', 'IPVCE "Carlos Roloff"', 52, 8137, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('UCATC', 'Universidad Católica de Colombia', 46, 5247, true, 'http://www.ucatolica.edu.co/‎');
INSERT INTO institution VALUES ('UCMH', 'Universidad de Ciencias Médicas de la Habana', 52, 89, true, 'http://instituciones.sld.cu/ucmh/');
INSERT INTO institution VALUES ('UCMPR', 'Universidad de Ciencias Médicas de Pinar del Río', 52, 91, true, 'http://');
INSERT INTO institution VALUES ('ISCMVC', 'Instituto Superior de Ciencias Médicas de Villa Clara', 52, 92, true, 'http://');
INSERT INTO institution VALUES ('ISCMSC', 'Instituto Superior de Ciencias Médicas de Santiago de Cuba', 52, 90, true, 'http://');
INSERT INTO institution VALUES ('ISCMC', 'Instituto Superior de Ciencias Médicas de Camaguey', 52, 93, true, 'http://');
INSERT INTO institution VALUES ('UCMSS', 'Universidad de Ciencias Médicas de Sancti Spíritus', 52, 94, true, 'http://');
INSERT INTO institution VALUES ('UCMM', 'Universidad de Ciencias Médicas de Matanzas', 52, 95, true, 'http://');
INSERT INTO institution VALUES ('UCMHO', 'Universidad de Ciencias Médicas de Holguín', 52, 97, true, 'http://');
INSERT INTO institution VALUES ('UCMGTM', 'Universidad de Ciencias Médicas de Guantánamo', 52, 98, true, 'http://');
INSERT INTO institution VALUES ('UCMGRM', 'Universidad de Ciencias Médicas de Granma', 52, 99, true, 'http://');
INSERT INTO institution VALUES ('UCMCF', 'Universidad de Ciencias Médicas de Cienfuegos', 52, 100, true, 'http://');
INSERT INTO institution VALUES ('UCMCA', 'Universidad de Ciencias Médicas de Ciego de Ávila', 52, 101, true, 'http://');
INSERT INTO institution VALUES ('ISDI', 'Instituto Superior de Diseño Industrial', 52, 102, true, 'http://www.isdi.co.cu/');
INSERT INTO institution VALUES ('UNALMA', 'Universidad Nacional de Colombia - Manizales', 46, 104, true, 'http://www.manizales.unal.edu.co/');
INSERT INTO institution VALUES ('UPBM', 'Universidad Pontificia Bolivariana - Medellin', 46, 5277, true, 'http://www.upb.edu.co/');
INSERT INTO institution VALUES ('UFPS', 'Francisco de Paula Santander', 46, 108, true, 'http://www.ufps.edu.co/');
INSERT INTO institution VALUES ('UCO', 'Universidad Católica de Oriente', 46, 110, true, 'http://www.uco.edu.co/web/home14.HTML');
INSERT INTO institution VALUES ('UCENTRAL', 'Universidad Central', 46, 5248, true, 'http://www.ucentral.edu.co/');
INSERT INTO institution VALUES ('UART', 'Universidad de Artemisa', 52, 111, true, 'http://www.uart.cu/');
INSERT INTO institution VALUES ('UPTC', 'Universidad Pedagógica y Tecnológica de Colombia', 46, 128, true, 'www.uptc.edu.co');
INSERT INTO institution VALUES ('AUSA', 'Almacenes Universales', 52, 129, true, 'http://www.ausa.co.cu/');
INSERT INTO institution VALUES ('UDEC', 'Universidad de Concepción', 43, 4753, true, 'http://www.udec.cl/');
INSERT INTO institution VALUES ('UNICAUCA', 'Universidad del Cauco', 46, 130, true, 'http://www.unicauca.edu.co/');
INSERT INTO institution VALUES ('UMD', 'Universidad Minuto de Dios', 46, 5255, true, 'http://www.uniminuto.edu/');
INSERT INTO institution VALUES ('UCI', 'Universidad de las Ciencias Informáticas', 52, 151, true, 'http://www.uci.cu/');
INSERT INTO institution VALUES ('UCLV', 'Universidad Central "Marta Abreu" de Las Villas', 52, 157, true, 'http://www.uclv.edu.cu/');
INSERT INTO institution VALUES ('IPVCE-MT', 'IPVCE "Carlos Marx"', 52, 8135, true, 'http://www.cubaeduca.cu/');
INSERT INTO institution VALUES ('CURN', 'Corporación Universitaria Rafael Núñez', 46, 132, true, 'www.curn.edu.co');
INSERT INTO institution VALUES ('DATYS', 'Desarrollo de Aplicaciones Tecnologías y Sistemas Holguín', 52, 133, true, 'http://www.datys.cu');
INSERT INTO institution VALUES ('UCMLT', 'Universidad de Ciencias Médicas de Las Tunas', 52, 96, true, 'http://intranet.ltu.sld.cu/');
INSERT INTO institution VALUES ('ISMMM', 'Instituto Superior Minero Metalúrgico de Moa', 52, 155, true, 'http://www.ismm.edu.cu/');
INSERT INTO institution VALUES ('UM', 'Universidad de Matanzas', 52, 161, true, 'http://www.umcc.cu/');
INSERT INTO institution VALUES ('IPVCE-LT', 'IPVCE "Luis Urquiza"', 52, 8141, true, 'http://www.cubaeduca.cu/');


--
-- Name: institution_inst_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('institution_inst_id_seq', 8177, true);


--
-- Data for Name: internal_mail; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: internal_mail_idmail_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('internal_mail_idmail_seq', 106062, true);


--
-- Data for Name: language; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO language VALUES ('Text', 'text file', 1, true, 'text file', 'txt', 'txt', NULL, 10, '/bin/cat <SRC>');
INSERT INTO language VALUES ('C++', 'gcc-g++', 2, true, 'g++ 4.8.2', 'cpp', 'cpp', 3, 16, '/usr/bin/g++ -DONLINE_JUDGE -static -g -O2 -static-libstdc++ <SRC> -o <EXE>');
INSERT INTO language VALUES ('Java', 'java', 3, true, 'openjdk 1.7.0', 'java', 'java', 2, 15, '/usr/bin/javac -O <SRC>');
INSERT INTO language VALUES ('C', 'gcc', 4, true, 'gcc 4.8.2', 'gcc', 'c', 4, 13, '/usr/bin/gcc -DONLINE_JUDGE -static -g -O2 -std=gnu99 -static-libstdc++ <SRC> -o <EXE> -lm');
INSERT INTO language VALUES ('C#', 'mcs', 5, true, 'dmcs 3.2.8.0', 'csharp', 'cs', 1, 14, '/usr/bin/dmcs $1 && /usr/bin/mkbundle --static $2.exe -o $2');
INSERT INTO language VALUES ('Perl', 'perl', 6, true, 'perl 5.18.2', 'perl', 'pl', 10, 5, '/usr/bin/perl <SRC>');
INSERT INTO language VALUES ('Python', 'python', 7, true, 'python 2.7.6', 'python', 'py', 9, 12, '/usr/bin/python <SRC>');
INSERT INTO language VALUES ('Pascal', 'fpc', 8, true, 'fpc 2.6.2', 'pascal', 'pas', 11, 11, '/usr/bin/fpc -dONLINE_JUDGE <SRC>');
INSERT INTO language VALUES ('Ruby', 'ruby', 9, true, 'ruby 1.9.3', 'ruby', 'rb', 6, 7, '/usr/bin/ruby <SRC>');
INSERT INTO language VALUES ('PHP', 'php', 10, true, 'php5', 'php', 'php', 8, 8, '/usr/bin/php <SRC>');
INSERT INTO language VALUES ('Bash', 'sh', 11, true, 'bash 4.3.11', 'bash', 'sh', 7, 6, '/bin/bash <SRC>');
INSERT INTO language VALUES ('C++11', 'g++', 13, true, 'g++ 4.8.2', 'cpp11', 'cc', 13, 9, '/usr/bin/g++ -static -g -O2 -std=c++0x -static-libstdc++ <SRC> -o <EXE>');
INSERT INTO language VALUES ('Haskell', 'ghc', 14, false, 'ghc 7.6.3', 'ghc', 'hs', 14, 2, '/usr/bin/ghc -O2 -static -threaded -optl-static -fllvm <SRC>');
INSERT INTO language VALUES ('Prolog', 'gprolog', 15, true, 'SWI Prolog 6.6.4', 'prolog', 'pl', 28, 4, '/usr/bin/swipl --goal=program --stand_alone=true -o <EXE> -c <SRC>');
INSERT INTO language VALUES ('JavaScript-Rhino', 'rhino', 16, false, 'Javascript Rhino 1.7v4', 'rhino', 'js', 30, 1, NULL);
INSERT INTO language VALUES ('JavaScript-NodeJS', 'nodejs', 18, true, 'NodeJS v0.10.25', 'nodejs', 'js', 31, 3, '/usr/bin/nodejs <SRC>');
INSERT INTO language VALUES ('VBasic', 'vbnc', 19, false, 'vbnc 0.0.0.5943', 'vbasic', 'vb', 32, 17, '/usr/bin/vbnc $1 && /usr/bin/mkbundle --static $2.exe -o $2');


--
-- Data for Name: language_contest; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: language_stats; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO language_stats VALUES (13, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);


--
-- Data for Name: language_stats_contest; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: language_virtual_stats; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: languages_lid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('languages_lid_seq', 25, true);


--
-- Data for Name: locale; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO locale VALUES (1, 'English', 'en');
INSERT INTO locale VALUES (2, 'Español', 'es');


--
-- Name: locale_lid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('locale_lid_seq', 2, true);


--
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: news_nid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('news_nid_seq', 1, false);


--
-- Data for Name: plagicoj_result; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: plagicoj_result_judge_revisio_id_plagicoj_result_judge_revi_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('plagicoj_result_judge_revisio_id_plagicoj_result_judge_revi_seq', 1, false);


--
-- Data for Name: plagicoj_result_judge_revision; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: poll; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO poll VALUES (3, '¿Crees que la inclusión de Python, en los concursos locales y nacionales caribeños del ACM-ICPC 2015, motivaría a una mayor participación de equipos desde esos niveles de competición? / Do you think that including Python, in the 2015 ACM-ICPC Caribbean Local and National Contests, would encourage greater participation of teams starting in these competition levels?', 'Si lo creo (firmemente) / Yes, I do (firmly).', 'Si lo creo (aunque en menor medida) / Yes, I do (although a lesser extent).', 'Tal vez / Maybe.', 'No lo creo (aunque me gustaría que fuera probado) / No, I don''t think so (although I would like to be tested).', 'No lo creo (firmemente). No, I don''t think so (firmly).', true);
INSERT INTO poll VALUES (1, '¿Cómo notas la velocidad y estabilidad de conexión al COJ? / How do you fell about the speed and stability of connection to the COJ?', 'Muy buena / Very Good.', 'Buena / Good.', 'Regular / Medium.', 'Mala / Bad.', 'Muy mala / Very Bad.', true);
INSERT INTO poll VALUES (4, '¿Qué te parece la posibilidad de, en los concursos locales y nacionales caribeños del ACM-ICPC 2015, proveer los enunciados de los problemas en inglés y español? / What do you think about the possibility, in the 2015 ACM-ICPC Caribbean Local and National Contests, of provide the problems'' statements in English and Spanish?', 'Muy bueno / Very Good.', 'Bueno / Good.', 'Regular / Medium.', 'Malo / Bad.', 'Muy malo / Very Bad.', true);
INSERT INTO poll VALUES (2, '¿Qué te parece la posibilidad de incluir Python en los concursos locales y nacionales caribeños del ACM-ICPC 2015? / What do you think about the possibility of including Python in the 2015 ACM-ICPC Caribbean Local and National Contests?', 'Muy bueno / Very Good.', 'Bueno / Good.', 'Regular / Medium.', 'Malo / Bad.', 'Muy malo / Very Bad.', true);
INSERT INTO poll VALUES (5, '¿Crees que proveer los enunciados de los problemas en inglés y español, en los concursos locales y nacionales caribeños del ACM-ICPC 2015, motivaría a una mayor participación de equipos desde esos niveles de competición? / Do you think that providing the problems'' statements in English and Spanish, in the 2015 ACM-ICPC Caribbean Local and National Contests, would encourage greater participation of teams starting in these competition levels?
', 'Si lo creo (firmemente) / Yes, I do (firmly).', 'Si lo creo (aunque en menor medida) / Yes, I do (although a lesser extent).', 'Tal vez / Maybe.', 'No lo creo (aunque me gustaría que fuera probado) / No, I don''t think so (although I would like to be tested).', 'No lo creo (firmemente) / No, I don''t think so (firmly).', true);


--
-- Data for Name: poll_answer; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: poll_answer_aid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('poll_answer_aid_seq', 840, true);


--
-- Name: poll_pid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('poll_pid_seq', 6, true);


--
-- Data for Name: preference_profile; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: problem; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO problem VALUES (1011, 'Army Strength', '<div style="text-align: justify;">The next MechaGodzilla invasion is on its way to Earth. And once again, Earth will be the battleground for an epic war.

MechaGodzilla''s army consists of many nasty alien monsters, such as Space Godzilla, King Gidorah, and MechaGodzilla herself.

To stop them and defend Earth, Godzilla and her friends are preparing for the battle.

Each army consists of many different monsters. Each monster has a strength that can be described by a positive integer. The larger the value, the stronger the monster.

The war will consist of a series of battles. In each battle, the weakest of all the monsters that are still alive is killed.

If there are several weakest monsters, but all of them in the same army, one of them is killed at random. If both armies have at least one of the weakest monsters, a random weakest monster of MechaGodzilla''s army is killed.

The war is over if in one of the armies all monsters are dead. The dead army lost, the other one won.

You are given the strengths of all the monsters. Find out who wins the war.</div>', '<div style="text-align: justify;">The first line of the input file contains an integer <span style="font-weight: bold;">T</span> specifying the number of test cases. Each test case is preceded by a blank line.

Each test case starts with line containing two positive integers <span style="font-weight: bold;">NG </span>and <span style="font-weight: bold;">NM</span> (the number of monsters in Godzilla''s and in MechaGodzilla''s army). Two lines follow. The first one contains <span style="font-weight: bold;">NG</span> positive integers (the strengths of the monsters in Godzilla''s army). Similarly, the second one contains <span style="font-weight: bold;">NM</span> positive integers (the strengths of the monsters in MechaGodzilla''s army).</div>', '<div style="text-align: justify;">For each test case, output a single line with a string that describes the outcome of the battle.

If it is sure that Godzilla''s army wins, output the string "Godzilla".

If it is sure that MechaGodzilla''s army wins, output the string "MechaGodzilla".

Otherwise, output the string "uncertain".</div>', '2<br><br>1 1<br>1<br>1<br><br>3 2<br>1 3 2<br>5 5<br>', 'Godzilla<br>MechaGodzilla', '2008 IPSC []', '<div style="text-align: justify;">In the first test case, there are only two monsters, and they are equally strong. In this situation, MechaGodzilla''s monster is killed and the war ends.

In the second test case, the war will consist of three battles, and in each of them one of Godzilla''s monsters dies.

For all the test cases, int in C/C++/Java or longint in Pascal is enough.</div>', 5, 0, 0, 0, 1, '2011-10-12 23:06:31.0', '1011', 34227, true, 0, false, false, false, 'En el primero caso de prueba sólo hay dos monstruos, y son igualmente fuerte. En esta situación, el monstruo de MechaGodzilla muere y termina la guerra. En el segundo caso de prueba, la guerra consistirá en tres batallas, y en cada uno de ellos uno de los monstruos de Godzilla muere. Para todos los casos de prueba, int en C / C ++ / Java o entero largo en Pascal es suficiente.<br>', NULL, NULL, 'La próxima invasión MechaGodzilla está en camino a la Tierra. Y una vez más, la Tierra será el campo de batalla de una guerra épica. El ejército de MechaGodzilla se compone de muchos monstruos alienígenas desagradables, tales como Space Godzilla, King Gidorah y MechaGodzilla sí misma. Para detenerlos y defender la Tierra, Godzilla y sus amigos se preparan para la batalla. Cada ejército se compone de muchos monstruos diferentes. Cada monstruo tiene una fuerza que puede ser descrito por un número entero positivo. Cuanto mayor sea el valor, más fuerte es el monstruo. La guerra consistirá en una serie de batallas. En cada batalla, el más débil de todos los monstruos que todavía están vivos es asesinado. Si hay varios monstruos más débiles, pero todos ellos en el mismo ejército, uno de ellos es asesinado al azar. Si ambos ejércitos tienen al menos uno de los monstruos más débiles, un monstruo débil aleatoria del ejército de MechaGodzilla es asesinado. La guerra ha terminado si en uno de los ejércitos a todos los monstruos están muertos. El ejército muerto pierde, el otro ganó. Se le da los puntos fuertes de todos los monstruos. Averigüe quién gana la guerra.<br>', NULL, '', 'La primera línea del archivo de entrada contiene un entero T especificando el número de casos de prueba. Cada caso de prueba es precedido por una línea en blanco. Cada caso de prueba comienza con la línea que contiene dos enteros positivos NG y NM (el número de monstruos en Godzilla y en el ejército de MechaGodzilla). Dos líneas siguen. El primero contiene números enteros positivos NG (los puntos fuertes de los monstruos en el ejército de Godzilla ). Del mismo modo, el segundo contiene números enteros positivos NM (los puntos fuertes de los monstruos en el ejército de MechaGodzilla).<br>', NULL, NULL, 'Para cada caso de prueba, la salida de una sola línea con una cadena que describe el resultado de la batalla. Si está seguro de que el ejército de Godzilla gana, salida de la cadena "Godzilla". Si está seguro de que el ejército de MechaGodzilla gana, salida de la cadena "MechaGodzilla". De lo contrario, la salida de la cadena "incierto".<br>', true, '', 234);
INSERT INTO problem VALUES (1005, 'Rent your Airplane and make Money', '<div style="text-align: justify;">"ABEAS Corp." is a very small company that owns a single airplane. The customers of ABEAS Corp are large airline companies which rent the airplane to accommodate occasional overcapacity.

Customers send renting orders that consist of a time interval and a price that the customer is ready to pay for renting the airplane during the given time period. Orders of all the customers are known in advance. Of course, not all orders can be accommodated and some orders have to be declined. Eugene LAWLER, the Chief Scientific Officer of ABEAS Corp would like to maximise the profit of the company.

You are requested to compute an optimal solution.<br><br>Small Example:

Consider the case where the company has 4 orders:

 <br></div><ul style="text-align: justify;"><li>Order 1 (start time 0, duration 5, price 10).</li><li>Order 2 (start time 3, duration 7, price 8).</li><li>Order 3 (start time 5, duration 9, price 7).</li><li>Order 4 (start time 6, duration 9, price 8).</li></ul><div style="text-align: justify;">The optimal solution consists in declining Order 2 and 3 and the gain is 10 + 8 = 18.

Note that the solution made of Order 1 and 3 is feasible (the airplane is rented with no interruption from time 0 to time 14) but non-optimal.</div>', '<div style="text-align: justify;">The first line of the input contains a number <span style="font-weight: bold;">T (1 &lt;= T &lt;= 30)</span> that indicates the number of test cases to follow. The first line of each test case contains the number of orders <span style="font-weight: bold;">n (0 &lt;= n &lt;= 10000)</span>. In the following <span style="font-weight: bold;">n</span> lines the orders are given. Each order is described by 3 integer values: The start time of the order <span style="font-weight: bold;">st (0 &lt;= st &lt; 10^6)</span>, the duration <span style="font-weight: bold;">d</span> of the order <span style="font-weight: bold;">(0 &lt; d &lt; 10^6)</span>, and the price <span style="font-weight: bold;">p (0 &lt; p &lt; 10^5</span>) the customer is ready to pay for this order.</div>', '<div style="text-align: justify;">You are required to compute an optimal solution. For each test case your program has to write the total price paid by the airlines.</div>', '1<br>4<br>0 5 10<br>3 7 14<br>5 9 7<br>6 9 8<br>', '18', '2003 ACM-ICPC Southwestern European Regional Contest', '', 5, 0, 0, 0, 1, '2011-10-13 07:27:50.0', '1005', 34227, true, 0, false, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, '', 76);
INSERT INTO problem VALUES (1003, 'General Election', '<div style="text-align: justify;">General Election is over, now it is time to count the votes! There are <span style="font-weight: bold;">n (2 &lt;= n &lt;= 5) </span>candidates and <span style="font-weight: bold;">m (1 &lt;= m &lt;= 100)</span> vote regions. Given the number of votes for each candidate from each region, determine which candidate is the winner (one with the most votes).</div>', '<div style="text-align: justify;">The first line of input contains an integer <span style="font-weight: bold;">T (1 &lt;= T &lt;= 100)</span>, the number of test cases follow.

Each test case starts with two integers <span style="font-weight: bold;">n</span> and <span style="font-weight: bold;">m</span> denoting the number of candidate and number of region. The next <span style="font-weight: bold;">m</span> lines each contains <span style="font-weight: bold;">n</span> integers, <span style="font-weight: bold;">V1, V2, ..., Vn (0 &lt;= Vi &lt;= 1000) </span>the number of votes for candidate <span style="font-weight: bold;">i</span>.</div>', '<div style="text-align: justify;">For each test case, output in a line the winning candidate. You may safely assume that the winner is unique.</div>', '2<br>3 3<br>159 213 450<br>512 890 993<br>215 420 397<br>2 5<br>40 64<br>35 12<br>102 58<br>43 15<br>79 41<br>', '3<br>1<br>', '2009 ACM-ICPC INC []', '', 5, 0, 0, 0, 1, '2011-10-13 02:36:43.0', '1003', 34227, true, 0, false, false, false, '  ', '', '', 'Las elecciones generales han terminado, ahora es tiempo de contar los votos. Hay <b>n (2 <= n <= 5) </b>candidatos y <b>m (1 <= m <= 100) </b>regiones
 de votos. Dado el número de votos para cada candidato de cada región, 
determine cual candidato es el ganador (aquel con la mayoría de votos)', '', 'Elección General', 'La primera línea de entrada contiene un entero <b>T</b> (1 &lt;= <b>T</b> &lt;= 100), el número de casos de prueba que siguen. Cada caso de prueba comienza con dos enteros <b>n</b> y <b>m</b> que denotan el número de candidatos y de regiones respectivamente. Las próximas m líneas cada una contiene <b>n</b> enteros <b>V</b>1, <b>V</b>2, ..., <b>V</b>n (0 &lt;= <b>V</b>i &lt;= 1000) el número de votos para el candidato <b>i</b>.<br>', '', '', 'Para cada caso de prueba, imprima en una línea el candidato vencedor. Puede asumir que el ganador es único.', true, '', 45);
INSERT INTO problem VALUES (1016, 'Freckles', '<div style="text-align: justify;">In an episode of the Dick Van Dyke show, little Richie connects the freckles on his Dad''s back to form a picture of the Liberty Bell. Alas, one of the freckles turns out to be a scar, so his Ripley''s engagement falls through.

Consider Dick''s back to be a plane with freckles at various (x,y) locations. Your job is to tell Richie how to connect the dots so as to minimise the amount of ink used. Richie connects the dots by drawing straight lines between pairs, possibly lifting the pen between lines. When Richie is done there must be a sequence of connected lines from any freckle to any other freckle.</div>', '<div style="text-align: justify;">The first line contains <span style="font-weight: bold;">0 &lt; n &lt;= 100</span>, the number of freckles on Dick''s back. For each freckle, a line follows; each following line contains two real numbers indicating the (x,y) coordinates of the freckle.</div>', '<div style="text-align: justify;">Your program prints a single real number to two decimal places: the minimum total length of ink lines that can connect all the freckles.</div>', '3<br>1.0 1.0<br>2.0 2.0<br>2.0 4.0<br>', '3.41', '2005 Waterloo Local Contest', '', 5, 2000, 65536000, 30720, 1, '2011-10-13 00:50:31.0', '1016', 34227, true, 2000, false, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, 'https://coj-forum.uci.cu/viewtopic.php?f=22&t=144', 626);
INSERT INTO problem VALUES (1018, 'Ferry Loading III', '<div style="text-align: justify;">Before bridges were common, ferries were used to transport cars across rivers. River ferries, unlike their larger cousins, run on a guide line and are powered by the river''s current. Cars drive onto the ferry from one end, the ferry crosses the river, and the cars exit from the other end of the ferry.

There is a ferry across the river that can take <span style="font-weight: bold;">n </span>cars across the river in <span style="font-weight: bold;">t</span> minutes and return in <span style="font-weight: bold;">t</span> minutes. A car may arrive at either river bank to be transported by the ferry to the opposite bank. The ferry travels continuously back and forth between the banks so long it is carrying a car or there is at least one car waiting at either bank. Whenever the ferry arrives at one of the banks, it unloads its cargo and loads up to <span style="font-weight: bold;">n</span> cars that are waiting to cross. If there are more than <span style="font-weight: bold;">n</span>, those that have been waiting the longest are loaded. If there are no cars waiting on either bank, the ferry waits until one arrives, loads it (if it arrives on the same bank of the ferry), and crosses the river. At what time does each car reach the other side of the river?</div>', '<div style="text-align: justify;">The first line of input contains <span style="font-weight: bold;">c (1 &lt;= c &lt;= 10)</span>, the number of test cases. Each test case begins with <span style="font-weight: bold;">n</span>, <span style="font-weight: bold;">t</span>, <span style="font-weight: bold;">m</span>. <span style="font-weight: bold;">m</span> lines follow, each giving the arrival time for a car (in minutes since the beginning of the day), and the bank at which the car arrives ("left" or "right").</div>', '<div style="text-align: justify;">For each test case, output one line per car, in the same order as the input, giving the time at which that car is unloaded at the opposite bank. Output an empty line between cases.

You may assume that <span style="font-weight: bold;">0 &lt; n, t, m &lt;= 10^4</span>. The arrival times for each test case are strictly increasing. The ferry is initially on the left bank. Loading and unloading time may be considered to be 0.</div>', '2<br>2 10 10<br>0 left<br>10 left<br>20 left<br>30 left<br>40 left<br>50 left<br>60 left<br>70 left<br>80 left<br>90 left<br>2 10 3<br>10 right<br>25 left<br>40 left<br>', '10<br>30<br>30<br>50<br>50<br>70<br>70<br>90<br>90<br>110<br><br>30<br>40<br>60<br>', '2005 Waterloo Local Contest [Gordon Cormack]', '', 5, 2000, 65536000, 30720, 1, '2011-10-12 02:36:34.0', '1018', 34227, true, 2000, false, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, 'https://coj-forum.uci.cu/viewtopic.php?f=22&t=146', 625);
INSERT INTO problem VALUES (1017, 'Three powers', '<div style="text-align: justify;">Consider the set of all non-negative integer powers of 3.

<span style="font-weight: bold;">S</span> = { 1, 3, 9, 27, 81, ... }

Consider the sequence of all subsets of <span style="font-weight: bold;">S</span> ordered by the value of the sum of their elements. The question is simple: find the set at the <span style="font-weight: bold;">n-th</span> position in the sequence and print it in increasing order of its elements.</div>', '<div style="text-align: justify;">Each line of input (no more than 100) contains a number <span style="font-weight: bold;">n</span>, which is a positive integer with no more than 19 digits. The last line of input contains 0 and it should not be processed.</div>', '<div style="text-align: justify;">For each line of input, output a single line displaying the <span style="font-weight: bold;">n-th</span> set as described above, in the format used in the sample output.</div>', '1<br>7<br>14<br>783<br>1125900981634049<br>0<br>', '{ }<br>{ 3, 9 }<br>{ 1, 9, 27 }<br>{ 3, 9, 27, 6561, 19683 }<br>{ 59049, 3486784401, 205891132094649, 717897987691852588770249 }<br>', '2004 Waterloo Local Contest [Piotr Rudnicki]', '', 5, 2000, 65536000, 30720, 1, '2011-10-12 02:11:22.0', '1017', 34227, true, 2000, false, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, 'https://coj-forum.uci.cu/viewtopic.php?f=22&t=145', 507);
INSERT INTO problem VALUES (1019, 'Flipping colors', '<div style="text-align: justify;">A rectangle with sides parallel to the x-y axes and its left-lower corner at (0,0) is being painted. The rectangle may be thought of as a flat-screen display with almost infinite resolution; initially the entire rectangle is black.<br><div align="center"><img src="../downloads/images/fcolor.jpg"><br></div>Two numbers are given <span style="font-weight: bold;">0 &lt; h,v &lt; 1</span> and then:<br>* A vertical line is drawn dividing the horizontal sides of the rectangle in proportion <span style="font-weight: bold;">h:1-h</span> from the left.<br>* A horizontal line is drawn dividing the vertical sides of the rectangles in proportion <span style="font-weight: bold;">v:1-v</span> from the bottom up.<br>* These two lines divide the rectangle into four smaller rectangles.<br>* The upper left and the lower right sub-rectangles remain intact.<br>* The color of the other two rectangles is flipped (from black to white or from white to black) and now each of them is subject to the operation just performed on the bigger rectangle.<br>* This process continues (in principle) forever.<br><br>Given a point in the original rectangle but not on the boundary of any rectangle that turns up in the process of painting, determine the color of the point.</div>', '<div style="text-align: justify;">Input contains multiple cases (no more than 10). The first line of each case contains 4 numbers, the length of the rectangle <span style="font-weight: bold;">H</span>, the height of the rectangle <span style="font-weight: bold;">V</span> and then the numbers <span style="font-weight: bold;">h</span> and <span style="font-weight: bold;">v</span>. The next line contains one integer number <span style="font-weight: bold;">n</span>, the number of points to consider. The following <span style="font-weight: bold;">n</span> lines contain two numbers each, the x and the y coordinate of a point.</div>', '<div style="text-align: justify;">Organize your output as shown in the sample. For each point from input print the color of the point.</div>', '81 32 0.333333333333 0.5<br>6<br>16 30<br>16 25<br>16 12.0001<br>16 11.9999<br>16 7.987654321<br>16 7.0123456789<br>10 10 0.123456789 0.987654321<br>2<br>0.432 0.9876<br>9.432 0.9876<br>0 0 0 0<br>', 'Case 1:<br>black<br>black<br>white<br>black<br>white<br>white<br>Case 2:<br>white<br>black<br>', 'Waterloo Local Contest [Piotr Rudnicki]', '', 5, 2000, 65536000, 30720, 1, '2011-10-13 06:28:52.0', '1019', 34227, true, 2000, false, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, 'https://coj-forum.uci.cu/viewtopic.php?f=22&t=147', 494);
INSERT INTO problem VALUES (1014, 'Traditional BINGO', '<div style="text-align: justify;">Traditional BINGO is played in person in a large hall. Players meet at the hall, pay a fee to get in, then the games begin. A night of BINGO consists of many BINGO games played continuously, one after another.

A single BINGO game proceeds like this: Each player has a number of BINGO cards (players can usually play any number of cards). Each BINGO card has 5 rows and 5 columns thus providing 25 spaces.

The columns are labelled from left to right with the letters: ''B'', ''I'', ''N'', ''G'', ''O''. With one exception (the centre space is "free") the spaces in the card are assigned values as follows:

Each space in the ''B'' column contains a number from 1 - 15.
Each space in the ''I'' column contains a number from 16 - 30.
Each space in the ''N'' column contains a number from 31 - 45.
Each space in the ''G'' column contains a number from 46 - 60.
Each space in the ''O'' column contains a number from 61 - 75.

Furthermore, a number can appear only once on a single card.

Here''s a sample BINGO card:

</div><p style="text-align: justify;"><table style="text-align: left; margin-left: 0px; margin-right: 0px;" border="1" cellpadding="5"><tbody><tr align="center"><td><font size="5">B</font></td><td><font size="5">I</font></td><td><font size="5">N</font></td><td><font size="5">G</font></td><td><font size="5">O</font></td></tr><tr align="center"><td>10</td> <td>17</td> <td>39</td> <td>49</td> <td>64</td></tr>
<tr align="center"><td>12</td> <td>21</td> <td>36</td> <td>55</td> <td>62</td></tr><tr align="center">
<td>14</td> <td>25</td> <td><font size="1">FREE<br>SPACE</font></td> <td>52</td> <td>70</td></tr><tr align="center">
<td>7</td> <td>19</td> <td>32</td> <td>56</td> <td>68</td></tr><tr align="center"><td>5</td> <td>24</td> <td>34</td> <td>54</td> <td>71</td></tr></tbody></table></p><div style="text-align: justify;">

The number of unique BINGO cards is very large and can be calculated with this equation:

// the B, I, G, and O columns * the N column
(15 * 14 * 13 * 12 * 11) ^ 4 * (15 * 14 * 13 * 12)

While perhaps interesting to a statistician, the number of possible BINGO cards has nothing to do with player''s chances of winning.

You will note that there are 75 possible BINGO numbers:

B1, B2, B3, ..., B15, I16, I17, I18, ..., I30, N31, N32, ..., O74, O75.

Each of these numbers is represented by a ball in a large rotating bin. Each ball is painted with its unique BINGO number. An announcer spins the bin, reaches in a selects a ball, and a announces it to the room. The players check all of their cards to see if that number appears on their card. If it is, they mark it. A player may mark the centre FREE SPACE at any time.

When a player has a BINGO (5 marks in a row, column, or diagonal), he or she calls out BINGO. The game pauses while the card is verified. If indeed a winner, the game stops and a new game begins. If the card wasn''t a winner, the game proceeds where it left off. Each BINGO game proceeds until someone wins (there''s always a winner).</div>', '<div style="text-align: justify;">The first line of input contains <span style="font-weight: bold;">n (1 &lt;= n &lt;= 30)</span>, the number of BINGO games that you will analyse. <span style="font-weight: bold;">n</span> game descriptions follow. Each game description specifies a card to be played followed by a sequence of BINGO numbers. You are to determine, when the holder of the card will win the game, assuming the player has just this one card and there are no other players.

Each card description consists of five lines, giving the numbers on the card row by row. All but the 3rd row contain 5 numbers; the 3rd contains 4 because of the free space. One or more lines follow that represent some ordering of all 75 bingo numbers. All bingo numbers are simply integers between 1 and 75 (the one-letter prefix is redundant).</div>', '<div style="text-align: justify;">For each game, output the line "BINGO after n numbers announced" as appropriate.

Chances of Winning

Every BINGO game has a winning card, so a player''s chances of winning depend on the number of cards in the game and how many cards she or he is playing. For example, if a player has 12 cards in a game with 1200 cards, the chances of winning for that player is 1 in 100.</div>', '1<br>10 17 39 49 64<br>12 21 36 55 62<br>14 25 52 70<br>7 19 32 56 68<br>5 24 34 54 71<br>1 2 3 4 5 6 7 8 9 10<br>11 12 13 14 15 16 17 18 19 20<br>21 22 23 24 25 26 27 28 29 30<br>31 32 33 34 35 36 37 38 39 40<br>41 42 43 44 45 46 47 48 49 50<br>51 52 53 54 55 56 57 58 59 60<br>61 62 63 64 65 66 67 68 69 70<br>71 72 73 74 75<br>', 'BINGO after 14 numbers announced', '2005 Waterloo Local Contest', '', 5, 2000, 65536000, 30720, 1, '2011-10-13 07:23:54.0', '1014', 34227, true, 2000, false, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, 'https://coj-forum.uci.cu/viewtopic.php?f=22&t=142', 626);
INSERT INTO problem VALUES (1010, 'ACM contest and Blackout', '<div style="text-align: justify;">In order to prepare the "The First National ACM School Contest" (in 2001) the major of the city decided to provide all the schools with a reliable source of power. The major is really afraid of blackouts. So, in order to do that, power station "Future" and one school (does not matter which one) must be connected; in addition, some schools may be connected as well.

You may assume that a school has a reliable source of power if it''s connected directly to "Future", or to any other school that has a reliable source of power. You are given the cost of connection between some schools. The major has decided to pick out the two cheapest connection plans (the cost of the connection is equal to the sum of the connections between the schools). Your task is to help the major find the cost of the two cheapest connection plans.</div>', '<div style="text-align: justify;">The input starts with the number of test cases, <span style="font-weight: bold;">T (1 &lt;= T &lt;= 15)</span> on a line. Then <span style="font-weight: bold;">T</span> test cases follow. The first line of every test case contains two numbers, which are separated by a space: <span style="font-weight: bold;">N (3 &lt;= N &lt;= 100)</span> the number of schools in the city, and <span style="font-weight: bold;">M</span> the number of possible connections among them. Next <span style="font-weight: bold;">M</span> lines contain three numbers <span style="font-weight: bold;">Ai</span>, <span style="font-weight: bold;">Bi</span>, <span style="font-weight: bold;">Ci</span>, where <span style="font-weight: bold;">Ci</span> is the cost of the connection <span style="font-weight: bold;">(1 &lt;= Ci &lt;= 300)</span> between schools <span style="font-weight: bold;">Ai</span> and <span style="font-weight: bold;">Bi</span>. The schools are numbered with integers in the range <span style="font-weight: bold;">1 to N</span>.</div>', '<div style="text-align: justify;">For every test case print only one line of output. This line should contain two numbers separated by a single space, the cost of the two cheapest connection plans. Let <span style="font-weight: bold;">S1</span> be the cheapest cost and <span style="font-weight: bold;">S2</span> the next cheapest cost. It is important, that <span style="font-weight: bold;">S1 = S2</span> if and only if there are two cheapest plans, otherwise <span style="font-weight: bold;">S1 &lt; S2</span>. You can assume that it is always possible to find the costs <span style="font-weight: bold;">S1</span> and <span style="font-weight: bold;">S2</span>.</div>', '2<br>5 8<br>1 3 75<br>3 4 51<br>2 4 19<br>3 2 95<br>2 5 42<br>5 4 31<br>1 2 9<br>3 5 66<br>9 14<br>1 2 4<br>1 8 8<br>2 8 11<br>3 2 8<br>8 9 7<br>8 7 1<br>7 9 6<br>9 3 2<br>3 4 7<br>3 6 4<br>7 6 2<br>4 6 14<br>4 5 9<br>5 6 10<br>', '110 121<br>37 37<br>', '2001 Ukrainian National Olympiad in Informatics []', '', 5, 0, 0, 0, 0, '2011-10-13 01:23:24.0', '1010', 34227, true, 0, true, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, '', 432);
INSERT INTO problem VALUES (1015, 'A Walk Through the Forest', '<div style="text-align: justify;">Jimmy experiences a lot of stress at work these days, especially since his accident made working difficult. To relax after a hard day, he likes to walk home. To make things even nicer, his office is on one side of a forest, and his house is on the other. A nice walk through the forest, seeing the birds and chipmunks is quite enjoyable.

The forest is beautiful, and Jimmy wants to take a different route everyday. He also wants to get home before dark, so he always takes a path to make progress towards his house. He considers taking a path from <span style="font-weight: bold;">A</span> to <span style="font-weight: bold;">B</span> to be progress if there exists a route from <span style="font-weight: bold;">B</span> to his home that is shorter than any possible route from <span style="font-weight: bold;">A</span>. Calculate how many different routes through the forest Jimmy might take.</div>', '<div style="text-align: justify;">Input contains several test cases followed by a line containing 0. Jimmy has numbered each intersection or joining of paths starting with 1. His office is numbered 1, and his house is numbered 2. The first line of each test case gives the number of intersections <span style="font-weight: bold;">N (1 &lt; N &lt;= 10^3)</span>, and the number of paths <span style="font-weight: bold;">M</span>. The following <span style="font-weight: bold;">M</span> lines each contain a pair of intersections <span style="font-weight: bold;">a b</span> and an integer distance <span style="font-weight: bold;">d (1 &lt;= d &lt;= 10^6)</span> indicating a path of length between intersection <span style="font-weight: bold;">a</span> and a different intersection <span style="font-weight: bold;">b</span>. Jimmy may walk a path any direction he chooses. There is at most one path between any pair of intersections.</div>', '<div style="text-align: justify;">For each test case, output a single integer indicating the number of different routes through the forest. You may assume that this number does not exceed 2147483647.</div>', '5 6<br>1 3 2<br>1 4 2<br>3 4 3<br>1 5 12<br>4 2 34<br>5 2 24<br>7 8<br>1 3 1<br>1 4 1<br>3 7 1<br>7 4 1<br>7 5 1<br>6 7 1<br>5 2 1<br>6 2 1<br>0<br>', '2<br>4<br>', '2005 Waterloo Local Contest [Richard Krueger]', '', 5, 4000, 65536000, 30720, 1, '2011-10-11 02:46:40.0', '1015', 34227, true, 2000, true, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, 'https://coj-forum.uci.cu/viewtopic.php?f=22&t=143', 516);
INSERT INTO problem VALUES (1020, 'Rock-Paper-Scissors Tournament', '<div style="text-align: justify;" align="center"><div align="center"><img src="../downloads/images/ejercicio1009.jpg"><br></div>Rock-Paper-Scissors is game for two players, A and B, who each choose, independently of the other, one of rock, paper, or scissors. A player chosing paper wins over a player chosing rock; a player chosing scissors wins over a player chosing paper; a player chosing rock wins over a player chosing scissors. A player chosing the same thing as the other player neither wins nor loses.

A tournament has been organized in which each of <span style="font-weight: bold;">n</span> players plays <span style="font-weight: bold;">k</span> rock-scissors-paper games with each of the other players - <span style="font-weight: bold;">k*n*(n-1)/2</span> games in total. Your job is to compute the win average for each player, defined as <span style="font-weight: bold;">w/(w+l)</span> where <span style="font-weight: bold;">w</span> is the number of games won, and l is the number of games lost, by the player.<br></div>', '<div style="text-align: justify;">Input consists of several test cases. The first line of input for each case contains <span style="font-weight: bold;">1 &lt;= n &lt;= 100</span> and <span style="font-weight: bold;">1 &lt;= k &lt;= 100</span> as defined above. For each game, a line follows containing <span style="font-weight: bold;">p1, m1, p2, m2</span>. <span style="font-weight: bold;">1 &lt;= p1 &lt;= n</span> and <span style="font-weight: bold;">1 &lt;= p2 &lt;= n</span> are distinct integers identifying two players; <span style="font-weight: bold;">m1</span> and <span style="font-weight: bold;">m2</span> are their respective moves ("rock", "scissors", or "paper"). A line containing 0 follows the last test case.</div>', '<div style="text-align: justify;">Output one line each for player 1, player 2, and so on, through player <span style="font-weight: bold;">n</span>, giving the player''s win average rounded to three decimal places. If the win average is undefined, output "-". Output an empty line between cases.</div>', '2 4<br>1 rock 2 paper<br>1 scissors 2 paper<br>1 rock 2 rock<br>2 rock 1 scissors<br>2 1<br>1 rock 2 paper<br>0<br>', '0.333<br>0.667<br><br>0.000<br>1.000<br>', '2005 Waterloo Local Contest [Gordon Cormack]', '', 5, 6000, 65536000, 30720, 1, '2011-10-13 04:33:37.0', '1020', 34227, true, 6000, false, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, '', 625);
INSERT INTO problem VALUES (1007, 'Square Town Tales: Power Down', '<div style="text-align: justify;">In Square Town, the nuclear plant has gone off-line for no reason. The whole city is in darkness, crime and confusion increase at every moment. The mayor, understandably concerned, has commissioned the construction of a new electrical power plant, funded from the meagre city treasury. The commission went to a specialised company, one that builds custom power plants according to existing costumer demand.

The power plant must provide electricity to a contiguous area of city blocks. As these are hard times, no exceptions are permitted. The mayor (a pragmatic man) has decided that the project must maximise the area covered by the service, but in a particular way that ensures that street lighting is also increased. This benefits the entire city because public lights are free, as long as their power is supplied by at least two electrical transformers. Secondly, the amount of citizens benefited by the project must also be maximised. This number is measured by a consume scale previously agreed that allows the construction company to design the plant. A condition of the company stipulates that due to their particular technology the amount of transformers connected to the grid must have an exact square root (odd, isn''t?).

Your task will be to generate the request for the company, based on data of the city population, the contract conditions of both parties and the multiple options of power plants presented by the company. Requests must be formatted according to a template supplied by the company and detailed later.</div>', '<div style="text-align: left;">As implied by its name, Square Town is ideally squared due to its modern design (and the Covered Area of each project should be an square too). Let <span style="font-weight: bold;">T</span> be the length of one side of town expressed as the amount of city blocks of said side <span style="font-weight: bold;">(10 &lt;= T &lt;= 200)</span>. Next follows the population density in each block, standing for the quantity of consumers <span style="font-weight: bold;">C</span> in each block <span style="font-weight: bold;">(1 &lt;= C &lt;= 400)</span>. Each electrified block possesses a transformer that connects it to the power plant. Each block transformer also provides half of the power necessary for each of the adjacent street lights.

Finally, an unspecified quantity of numbers <span style="font-weight: bold;">P</span>, with each representing the number of consumers serviced by the specific power plant project <span style="font-weight: bold;">(1000 &lt;= P &lt;= 6000000)</span>. The input ends with <span style="font-weight: bold;">P = 0</span>.
 </div>', 'The city experts must provide the company with the provided company.<br>The template model supplied by the company consists of several lines:<br><ul><li>Power Supplied: The amount of consumers benefited by the project, equal or less that the total capacity implemented consumer(s).</li><li>Covered Area: Area covered measured in blocks and expressed in the form X and Y. May or may not contain blocks not serviced by the grid, that is, blocks that do not count with a transformer block(s).</li><li>Power Deficit: The amount of consumer of the city that does not benefit from the project consumer(s).</li></ul>For example, a template of a prior project goes as follows:<br><span style="font-style: italic;">City: Chiguane</span><br style="font-style: italic;"><span style="font-style: italic;">Power Supplied: 304 consumer(s)</span><br style="font-style: italic;"><span style="font-style: italic;">Covered Area: 31 x 31 block(s)</span><br style="font-style: italic;"><span style="font-style: italic;">Power Deficit: 43056 consumer(s)</span><br><br>You must print one of these templates for each project <span style="font-weight: bold;">P</span> specified in the input, separated by a white line.', '10<br>160 212 320 86 347 425 45 178 356 234<br>188 401 109 122 190 242 351 93 277 349<br>271 420 415 44 339 282 201 409 246 111<br>81 425 423 164 140 409 425 391 122 119<br>118 96 294 322 293 396 194 69 381 57<br>130 225 42 338 296 377 272 204 399 419<br>413 96 171 220 308 395 209 220 263 83<br>259 315 52 363 367 66 98 90 88 166<br>190 72 372 97 145 346 248 386 309 238<br>289 391 327 185 211 105 270 320 231 167<br>2100<br>3600<br>0<br>', 'City: Square Town<br>Power Supplied: 2095 consumer(s)<br>Covered Area: 3 x 3 block(s)<br>Power Deficit: 22090 consumer(s)<br><br>City: Square Town<br>Power Supplied: 3558 consumer(s)<br>Covered Area: 4 x 4 block(s)<br>Power Deficit: 20627 consumer(s)<br>', '2010 Copa Pascal UCI', 'The mayor has been quite specific with his conditions, don''t you agree?', 5, 0, 0, 0, 0, '2011-09-21 08:21:16.0', '1007', 34227, true, 0, true, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, '', 154);
INSERT INTO problem VALUES (1008, 'Overlapping Air Traffic Control Zones', '<div style="text-align: justify;">Optimisation of air traffic flow is one of the essential ways for airlines to maintain economic viability. All too often, however, weather and other anomalous conditions disrupt air traffic flow resulting in significant costs.

Automation systems for optimising flows are not currently able to quickly reconfigure when path planning must account for dynamic conditions such as moving weather systems. Human intervention is usually used to decide route modifications.

Decisions on route modification for one aircraft must take into account neighbouring aircraft safe zones in order to minimise possible collision risks. We will consider a 3D model in which the safe zone for one aircraft is represented as a parallelepiped.

Evaluation of aircraft collision risks, in this model, can be done by calculating the volume of the intersecting safe zones of the aircrafts in a given air traffic control zone. In other words, we need to be able to determine the volume of intersecting parallelepipeds.

Consider a number of parallelepipeds in space, having all the edges parallel to the axes. Your task is to write a program that outputs the volume occupied simultaneously by two or more parallelepipeds. Each parallelepiped is characterised by 6 integer values, the coordinates of two of its vertices:

(x1,y1,z1), (x2,y2,z2) with x1 &lt; x2, y1 &lt; y2 and z1 &lt; z2</div>', '<div style="text-align: justify;">The input contains several test cases, each of them consists of an integer <span style="font-weight: bold;">0 &lt;= n &lt;= 15</span> in the first line followed by n lines of 6-couples of integers describing the parallelepipeds. The total area occupied does not exceed 5x10^8.</div>', '<div style="text-align: justify;">For each test case, output on a line by itself an integer corresponding to the total volume occupied simultaneously by two or more parallelepipeds.</div>', '5<br>1 1 1 3 3 3<br>1 1 1 3 3 3<br>1 1 1 3 3 3<br>400000000 400000000 400000000 400000001 400000001 400000002<br>400000000 400000000 400000000 400000002 400000004 400000001', '9', 'University of Porto Alegre Local Contest', '', 5, 0, 0, 0, 1, '2011-10-06 23:01:44.0', '1008', 34227, true, 0, false, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, '', 441);
INSERT INTO problem VALUES (1009, 'Arranging Amplifiers', '<div style="text-align: justify;">Scientists at the TIFR, Mumbai, are doing some cutting edge research on the Propagation of Signals. A young researcher comes up with a method of progressively amplifying signals, as they progress along a path. The method involves the placing of Amplifiers at regular distances along the line. Each amplifier is loaded with a number a(i), which is called its amplification factor. The method of amplification is simple: an amplifier which receives a signal of strength X, and has Y loaded in it, results in a signal of strength Y^X [Y to the power X]. In course of his research, the young scientist tries to find out, that given a set of n amplifiers loaded with a(0), a(1), a(2), ..., a(n-1), which particular permutation of these amplifiers, when placed at successive nodes, with the initial node given a signal of strength 1, produces the strongest output signal.

This is better illustrated by the following example: 5 6 4
4^(5^(6^1)) is the strength of the strongest signal, which is generated by putting amplifier loaded with 6 in first place, 5 in second place and 4 in third place.

Given a list of integers specifying the set of amplifiers at hand, you must find out the order in which they must be placed, to get the highest signal strength. In case their exist multiple permutations with same output, you should print the one which has bigger amplifiers first.</div>', '<div style="text-align: justify;">First line of input contains <span style="font-weight: bold;">T (1 &lt;= T &lt;= 20)</span>, the number of test cases. For each test case first line contains a number <span style="font-weight: bold;">N (1 &lt;= N &lt;= 10^5)</span>, which is equal to the number of amplifiers available. Next line contains <span style="font-weight: bold;">N</span> integers, separated by spaces which denote the values with which the amplifiers are loaded. Each amplifier will be loaded with a positive integer <span style="font-weight: bold;">p (0 &lt; p &lt;= 10^9)</span>.<br></div>', '<div style="text-align: justify;">Output contains <span style="font-weight: bold;">T</span> lines, one for each test case. Each line contains <span style="font-weight: bold;">N</span> integers, denoting the order in which the amplifiers should be kept such that the result is strongest.</div>', '2<br>3<br>5 6 4<br>2<br>2 3<br>', '6 5 4<br>2 3<br>', '2008 CodeCraft []', '', 5, 0, 0, 0, 0, '2011-10-11 22:07:12.0', '1009', 34227, true, 0, false, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, '', 151);
INSERT INTO problem VALUES (1012, 'Milk Measuring', '<div style="text-align: justify;">Farmer John must measure <span style="font-weight: bold;">Q (1 &lt;= Q &lt;= 20000)</span> quarts of his finest milk and deliver it in one big bottle to a customer. He fills that bottle with exactly the number of quarts that the customer orders.

Farmer John has always been frugal. He is at the cow hardware store where he must purchase a set of pails with which to measure out <span style="font-weight: bold;">Q</span> quarts of milk from his giant milk tank. Since the pails each cost the same amount, your task is to figure out a minimal set of pails Farmer John can purchase in order to fill a bottle with exactly <span style="font-weight: bold;">Q</span> quarts of milk. Additionally, since Farmer John has to carry the pails home, given two minimal sets of pails he should choose the "smaller" one as follows: Sort the sets in ascending order. Compare the first pail in each set and choose the set with the smallest pail. If the first pails match, compare the second pails and choose from among those, else continue until the two sets differ. Thus the set {3, 5, 7, 100} should be chosen over {3, 6, 7, 8}.

To measure out milk, Farmer John may completely fill a pail from the tank and pour it into the bottle. He can never remove milk from the bottle or pour milk anywhere except into the bottle. With a one-quart pail, Farmer John would need only one pail to create any number of quarts in a bottle. Other pail combinations are not so convenient.

Determine the optimally small number of pails to purchase, given the guarantee that one and only one solution is possible for all contest input data.</div>', '<div style="text-align: justify;">The first line the single integer <span style="font-weight: bold;">Q</span>. Line 2 contains a single integer <span style="font-weight: bold;">P (1 &lt;= P &lt;= 100) </span>which is the number of pails in the store. Lines <span style="font-weight: bold;">3, ..., P+2</span>: Each line contains a single integer <span style="font-weight: bold;">pail_value (1 &lt;= pail_value &lt;= 10^4)</span>, the number of quarts a pail holds.</div>', '<div style="text-align: justify;">The output is a single line of space separated integers that contains the minimum number of pails required to measure out the desired number of quarts, followed by a sorted list (from smallest to largest) of the capacity of each of the required pails.</div>', '16<br>3<br>3<br>5<br>7<br>', '2 3 5', 'USACO Training Problems', '', 5, 0, 0, 0, 0, '2011-10-06 17:02:59.0', '1012', 34227, true, 0, true, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, '', 593);
INSERT INTO problem VALUES (1002, 'New House', '<div style="text-align: justify;">Johan wants to build a new house and he wants his house as large as it can.<br>Given an <span style="font-weight: bold;">N x N</span> grid land, find the largest square size that fit in the free area.</div>', '<div style="text-align: justify;">The first line of input contains an integer <span style="font-weight: bold;">T (1 &lt;= T &lt;= 100</span>), the number of test cases follow.

Each test case starts with an integer <span style="font-weight: bold;">N &lt;= 10</span>  denoting the size of the grid land. The next <span style="font-weight: bold;">N</span> lines each contains <span style="font-weight: bold;">N</span> characters. Each character be either ''.'' (free area) or ''#'' (obstacle). Your house should be build on free area.</div>', 'For each test case, output in a line the size of the largest square on free area.', '2<br>10<br>..........<br>.#####....<br>..#..###..<br>...##.....<br>...#..#...<br>...#...###<br>....######<br>..........<br>##########<br>#########.<br>5<br>##..#<br>....#<br>.#..#<br>####.<br>.#..#<br>', '3<br>2<br>', '2009 ACM-ICPC INC', '', 5, 0, 0, 0, 0, '2011-10-13 07:11:23.0', '1002', 34227, true, 0, false, false, false, '  ', '', '', 'Johan quiere construir una nueva casa, y la quiere tan grande como se pueda.<br>Dado un terreno reticular de tamaño <span style="font-weight: bold;">N x N</span>, halle el mayor cuadrado que pueda ser ubicado en el área libre del terreno.', '', 'Casa nueva', 'La primera línea de la entrada contiene un entero <span style="font-weight: bold;">T (1 &lt;= T &lt;= 100</span>), que representa la cantidad de casos de prueba que le siguen. Cada caso de prueba comienza con un entero <span style="font-weight: bold;">N &lt;= 10</span> que denota el tamaño del terreno reticular. Las próximas <b>N</b> líneas contienen <b>N</b> caracteres. Cada caracter será un punto (''.''), que representa un área libre, o un numeral (''#''), que representará un área ocupada. La casa se debe construir en el área libre.', '', '', 'Para cada caso de prueba, imprima en una línea aparte el tamaño del mayor cuadrado que se pueda construir en el área libre.', true, '', 45);
INSERT INTO problem VALUES (1004, 'Traversing Grid', '<div style="text-align: justify;">Starting at the top left corner of an <span style="font-weight: bold;">N x M</span> grid and facing towards the right, you keep walking one square at a time in the direction you are facing. If you reach the boundary of the grid or if the next square you are about to visit has already been visited, you turn right. You stop when all the squares in the grid have been visited. What direction will you be facing when you stop?

For example: Consider the case with <span style="font-weight: bold;">N = 3, M = 3</span>. The path followed will be <span style="font-style: italic;">(0,0) -&gt; (0,1) -&gt; (0,2) -&gt; (1,2) -&gt; (2,2) -&gt; (2,1) -&gt; (2,0) -&gt; (1,0) -&gt; (1,1)</span>. At this point, all squares have been visited, and you are facing right.</div>', '<div style="text-align: justify;">The first line contains <span style="font-weight: bold;">T </span>the number of test cases. Each of the next <span style="font-weight: bold;">T</span> lines contain two integers <span style="font-weight: bold;">N</span> and <span style="font-weight: bold;">M</span>, denoting the number of rows and columns respectively.</div>', 'Output <span style="font-weight: bold;">T</span> lines, one for each test case, containing the required direction you will be facing at the end. Output <span style="font-weight: bold;">L</span> for left, <span style="font-weight: bold;">R</span> for right, <span style="font-weight: bold;">U</span> for up, and <span style="font-weight: bold;">D</span> for down. <span style="font-weight: bold;">1 &lt;= T &lt;= 5000, 1 &lt;= N,M &lt;= 10^9.</span>', '4<br>1 1<br>2 2<br>3 1<br>3 3<br>', 'R<br>L<br>D<br>R<br>', 'Codechef Online Contest', '', 5, 0, 0, 0, 0, '2011-10-13 06:07:00.0', '1004', 34227, true, 0, false, false, false, ' ', NULL, NULL, '<span id="result_box" class="" lang="es"><span title="Starting at the top left corner of an N x M grid and facing towards the right, you keep walking one square at a time in the direction you are facing.">Comenzando
 en la esquina superior izquierda de una rejilla M x N y mirando hacia 
la derecha, se sigue caminando un cuadrado a la vez en la dirección que 
está enfrentando. </span><span title="If you reach the boundary of the grid or if the next square you are about to visit has already been visited, you turn right.">Si alcanza el límite de la red o si la próxima plaza que está a punto de visitar ya ha sido visitada, gire a la derecha. </span><span title="You stop when all the squares in the grid have been visited.">Te detienes cuando se han visitado todas las plazas de la parrilla. </span><span title="What direction will you be facing when you stop?">¿En qué dirección va a estar enfrentando cuando te detienes? </span><span title="For example: Consider the case with N = 3, M = 3. The path followed will be (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2">Por ejemplo: Consideremos el caso con N = 3, M = 3. El camino seguido será (0,0) -&gt; (0,1) -&gt; (0,2) -&gt; (1,2) -&gt; (2 </span><span title=",2) -> (2,1) -> (2,0) -> (1,0) -> (1,1).">, 2) -&gt; (2,1) -&gt; (2,0) -&gt; (1,0) -&gt; (1,1). </span><span title="At this point, all squares have been visited, and you are facing right.
">En este punto, todas las plazas se han visitado, y se enfrentan a la derecha.</span></span>', NULL, 'Rejilla de desplazamiento', '<span id="result_box" class="" lang="es"><span title="Especificación de entrada
"><br></span><span title="The first line contains T the number of test cases.">La primera línea contiene T, que es el número de casos de prueba. </span><span title="Each of the next T lines contain two integers N and M, denoting the number of rows and columns respectively.
">Cada una de las siguientes líneas T contiene dos números enteros N y 
M, que denota el número de filas y columnas respectivamente.</span></span>', NULL, NULL, '<span id="result_box" class="" lang="es"><span title="Output T lines, one for each test case, containing the required direction you will be facing at the end.">Salida T líneas, una para cada caso de prueba, que contiene la dirección requerida a la que se enfrenta al final. </span><span title="Output L for left, R for right, U for up, and D for down.">Salida L para izquierda, R para la derecha, U para arriba, y D para abajo. </span><span title="1 <= T <= 5000, 1 <= N,M <= 10^9.">1 &lt;= t &lt;= 5000, 1 &lt;= N, M &lt;= 10 ^ 9.</span></span>', true, '', 150);
INSERT INTO problem VALUES (1013, 'Perfect Pth Powers', '<div style="text-align: justify;">We say that <span style="font-weight: bold;">x</span> is a perfect square if, for some integer <span style="font-weight: bold;">b</span>, <span style="font-weight: bold;">x = b^2</span>. Similarly, <span style="font-weight: bold;">x</span> is a perfect cube if, for some integer <span style="font-weight: bold;">b</span>, <span style="font-weight: bold;">x = b^3</span>. More generally, <span style="font-weight: bold;">x</span> is a perfect pth power if, for some integer <span style="font-weight: bold;">b</span>, <span style="font-weight: bold;">x = b^p</span>. Given an integer <span style="font-weight: bold;">x</span> you are to determine the largest <span style="font-weight: bold;">p</span> such that <span style="font-weight: bold;">x</span> is a perfect pth power.</div>', '<div style="text-align: justify;">Each test case (no more than 50) is given by a line of input containing <span style="font-weight: bold;">x</span>. The value of <span style="font-weight: bold;">x</span> will have magnitude at least 2 and be within the range of a (32-bit) int in C, C++, and Java. A line containing 0 follows the last test case.</div>', '<div style="text-align: justify;">For each test case, output a line giving the largest integer <span style="font-weight: bold;">p</span> such that <span style="font-weight: bold;">x</span> is a perfect pth power.</div>', '17<br>1073741824<br>25<br>0<br>', '1<br>30<br>2<br>', '2004 Waterloo Local Contest [Gordon Cormack]', '', 5, 2000, 65536000, 30720, 1, '2011-10-13 08:13:25.0', '1013', 34227, true, 2000, false, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, 'https://coj-forum.uci.cu/viewtopic.php?f=22&t=1809', 505);
INSERT INTO problem VALUES (1001, 'Pipes', '<div style="text-align: justify;">After writing a solver for the "movable maze" game, you have grown tired of it. After all, you already know the optimal solution. To entertain yourself, you find another puzzle game called "Pipes", and play that for a while. On one puzzle, you have not been able to find a solution by hand, and you think that there is no solution. You decide to write a program to tell you whether this is the case.

The game is played on a grid with R rows and C columns. Each square of the grid contains a black dot in the center and black lines in the direction of some, none, or all of its north, east, south, and west neighboring squares, with the following restriction: if two opposite directions both have lines, then at least one of the other two directions has a line as well. In other words, it is forbidden for a square to consist of a straight line.<br><br>The objective of the game is to rotate each square, as many times as you like, such that for each square, if it has a line going in a compass direction (that is, north, east, south, or west), then it has a neighbor in that compass direction and that neighbor has a line going in the opposite compass direction. In other words, each edge in the grid should either have a line on both sides or neither side.

Your task is to determine whether a given grid has a solution.</div>', '<div style="text-align: justify;">The input consists of multiple test cases (no more then 20).

The first line of each test case contains two integers <span style="font-weight: bold;">R</span> and <span style="font-weight: bold;">C</span>, separated by spaces, with <span style="font-weight: bold;">1 &lt;= R, C &lt;= 12</span>.

The following <span style="font-weight: bold;">R</span> lines of input each contain one row of the grid, from north to south. Each of these lines contains exactly C strings of letters, separated by spaces, that correspond to squares of the grid, from west to east. Their format is as follows:<br><br>&nbsp;* If the string is the single character x, then the square does not contain a line to any of its neighbors.<br>&nbsp;* Otherwise, the string contains some of the characters <span style="font-style: italic;">N, E, S, W,</span> which indicate that a black line extends from this square''s center in the direction of its north, east, south, or west neighbor, respectively.<br><br>No character will appear in the string more than once.<br>Input is terminated by a line containing 0 0.<br>These zeros should not be processed.</div>', '<div style="text-align: justify;">For each test case, output SOLVABLE if there is a solution to the puzzle, and UNSOLVABLE otherwise.</div>', '3 3<br>NW NW x<br>NES NESW W<br>E W x<br>2 2<br>ES x<br>x N<br>0 0', 'SOLVABLE<br>UNSOLVABLE', '2009 Waterloo Local Contest [Malcolm Sharpe]', '', 5, 0, 0, 0, 1, '2011-10-11 23:59:37.0', '1001', 34227, true, 0, true, false, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, '', 620);
INSERT INTO problem VALUES (1006, 'Ciphering Programs', '<div style="text-align: justify;">Enrique and Tomás are two good friends whose greatest entertainment is programming. As all good programmers, they challenge each other frequently, the challenges consisting mostly in solving online judge problems in as little time as possible.

Enrique has observed that one of the motives that competition between them has become more even is the sharing of solutions between the two. This allows each programmer to know the tricks of the other. Enrique has decided to hide his solutions from Tomás and everyone else, but he has an inconvenience to guarantee it: He shares a workstation with Tomás. Wanting to avoid accidental access to his code he decides to encrypt everything in his programs. In order to do so he implements the following algorithm:

Change every letter in the code for its successor in the English alphabet (respecting its case). If the letter in question is <span style="font-weight: bold;">z</span>, then its successor is assumed to be <span style="font-weight: bold;">a</span>.

Then the algorithm rewrites each line as described as follows: first, the symbol in the centre of the text is written (if characters goes from positions <span style="font-weight: bold;">1 to n</span>, then the first character in being written is the character in position <span style="font-weight: bold;">(1+n)/2)</span>. Following the same strategy, the algorithm encodes the left half of the string, then the right and last half.

Thus, the Pascal program:

<pre>program p1000(Input,Output);<br>var<br>&nbsp;&nbsp;&nbsp; a,b:Integer;<br>begin<br>&nbsp;&nbsp;&nbsp;&nbsp;Readln(a,b);<br>&nbsp;&nbsp;&nbsp;&nbsp;Writeln(a+b);<br>end.</pre>
will be:

<pre>(npqsshb1 q000PqJouv,vuvq)u;<br>bws<br>:&nbsp;&nbsp;&nbsp;&nbsp;,bcfoJufhs;<br>hcfjo<br>e&nbsp;&nbsp;&nbsp;&nbsp;fSbbom(c,);<br>f&nbsp;&nbsp;&nbsp;&nbsp;sXjubom(c+);<br>ofe.</pre>
Enrique took a while programming the encoder, and now wants to ask you to help him revert the encryptions.</div>', '<div style="text-align: justify;">The input consists of one program, encoded as per the strategy suggested by Enrique.<br>No line in this program contains more than 20000 characters.</div>', '<div style="text-align: justify;">The output consists of Enrique''s program decoded. This result will be exactly the same to the program originally described.</div>', '(npqsshb1 q000PqJouv,vuvq)u;<br>bws<br>:&nbsp;&nbsp;&nbsp; ,bcfoJufhs;<br>hcfjo<br>e&nbsp;&nbsp;&nbsp; fSbbom(c,);<br>f&nbsp;&nbsp;&nbsp; sXjubom(c+);<br>ofe.', 'program p1000(Input,Output);<br>var<br>&nbsp;&nbsp;&nbsp; a,b:Integer;<br>begin<br>&nbsp;&nbsp;&nbsp; Readln(a,b);<br>&nbsp;&nbsp;&nbsp; Writeln(a+b);<br>end.<br>', '2010 Copa Pascal UCI []', '', 5, 0, 0, 0, 1, '2011-10-13 17:44:55.0', '1006', 34227, true, 0, false, false, false, '', '', '', '<div align="justify">Enrique y Tomás son dos buenos amigos cuyo mayor entretenimiento es la programación. Como todos los buenos programadores, se desafían con frecuencia, los retos consisten principalmente en la resolución de problemas en los jueces línea en el menor tiempo posible. Enrique ha observado que uno de los motivos por lo que la competencia entre ellos se ha hecho más pareja es el intercambio de soluciones entre los dos. Esto permite a cada programador conocer los trucos del otro. Enrique ha decidido ocultar sus soluciones de Tomás y todos los demás, pero tiene un inconveniente para garantizarlo: Él comparte una estación de trabajo con Tomás. Queriendo evitar el acceso accidental a su código decide cifrar todo en sus programas. Con el fin de hacer esta tarea implementa el algoritmo siguiente: Cambia cada letra en el código por su sucesora en el alfabeto Inglés (respetando si son mayúsculas o minúsculas). Si la letra en cuestión es la <b>z</b>, entonces su sucesora es una <b>a</b>. Luego el algoritmo vuelve a escribir cada línea como se describe a continuación: primero, el símbolo en el centro del texto es escrito (si los caracteres van de las posiciones <b>1 a n</b>, entonces el primer carácter que se escribe es el carácter en la posición <b>(1 + n) / 2</b>). Siguiendo la misma estrategia, el algoritmo codifica la mitad izquierda de la cadena, a continuación la derecha y última mitad. De este modo, el programa en Pascal:<br></div><br><pre>program p1000(Input,Output);<br>var<br>&nbsp;&nbsp;&nbsp; a,b:Integer;<br>begin<br>&nbsp;&nbsp;&nbsp;&nbsp;Readln(a,b);<br>&nbsp;&nbsp;&nbsp;&nbsp;Writeln(a+b);<br>end.<br></pre>será:<br><pre>(npqsshb1 q000PqJouv,vuvq)u;<br>bws<br>:&nbsp;&nbsp;&nbsp;&nbsp;,bcfoJufhs;<br>hcfjo<br>e&nbsp;&nbsp;&nbsp;&nbsp;fSbbom(c,);<br>f&nbsp;&nbsp;&nbsp;&nbsp;sXjubom(c+);<br>ofe.<br></pre>A Enrique le tomó un rato programar el codificador, y ahora quiere pedirle a usted que le ayude a revertir las encriptaciones.<br>', '', 'Cifrando programas', '<div align="justify">La entrada consiste en un programa, codificado según la estrategia sugerida por Enrique.<br>Ninguna línea en este programa contiene más de 20000 caracteres.</div>', '', '', '<div align="justify">La salida consiste en el programa de Enrique decodificado. Este resultado será exactamente el mismo que el programa descrito originalmente.</div>', true, '', 154);
INSERT INTO problem VALUES (1000, 'A+B Problem', 'For this problem you must calculate <span style="font-weight: bold;">A + B</span>, numbers given in the input.', 'The only line of input contain two space separated integers <span style="font-weight: bold;">A, B (0 &lt;= A, B &lt;= 10)</span>.', 'The only line of output should contain one integer: the sum of <span style="font-weight: bold;">A</span> and <span style="font-weight: bold;">B</span>.', '1 2', '3', 'Typical problem (almost every online judge include it) []', 'Read our <span style="font-weight: bold;">FAQs</span> carefully to see solution samples. <br>', 5, 0, 0, 0, 0, '2011-10-13 19:59:47.0', '1000', 34227, true, 0, true, false, false, 'Lea nuestras <span style="font-weight: bold;">FAQs</span> cuidadosamente para ver ejemplos de código fuente.  ', '<div style="direction: ltr; text-align: left;" id="OutputText" tabindex="99999" class="Wrap"><div><span class="" id="ouHighlight__0_3TO0_3">Leia</span><span id="noHighlight_0.7855851826503771"> </span><span class="" id="ouHighlight__5_7TO5_9">nosso</span><span id="noHighlight_0.6747936223946657"> </span><b><span id="ouHighlight__9_12TO11_13">FAQ</span></b><span id="noHighlight_0.08526182874241783"> </span><span class="" id="ouHighlight__14_22TO15_28">cuidadosamente</span><span id="noHighlight_0.5662067288548475"> </span><span id="ouHighlight__24_25TO30_33">para</span><span id="noHighlight_0.6894589797165609"> </span><span id="ouHighlight__27_29TO35_37">ver</span><span id="noHighlight_0.2979357640680471"> </span><span class="" id="ouHighlight__40_46TO39_46">amostras</span><span id="noHighlight_0.13653399920366427"> de </span><span class="" id="ouHighlight__31_38TO51_57">solução</span><span id="noHighlight_0.7419469197995348">.</span></div></div>  ', '<div style="direction: ltr; text-align: left;" id="OutputText" tabindex="99999" class="Wrap"><div><span class="" id="ouHighlight__0_2TO0_3">Para</span><span id="noHighlight_0.15180335150713953"> </span><span class="" id="ouHighlight__4_7TO5_8">esse</span><span id="noHighlight_0.15815223035658177"> </span><span id="ouHighlight__9_15TO10_17">problema</span><span id="noHighlight_0.31426078612549857">, </span><span id="ouHighlight__17_19TO20_23">você</span><span id="noHighlight_0.41395517200008813"> </span><span id="ouHighlight__21_24TO25_28">deve</span><span id="noHighlight_0.26496093985638824"> </span><span id="ouHighlight__26_34TO30_37">calcular</span><span id="noHighlight_0.9717559011053581"> </span><b><span id="ouHighlight__36_36TO39_39">A</span><span id="noHighlight_0.7258694707975493"> </span><span id="ouHighlight__38_38TO41_41">+</span><span id="noHighlight_0.6087755970885048"> </span></b><span id="ouHighlight__40_41TO43_44"><b>B</b>,</span><span id="noHighlight_0.7918567635167841"> </span><span class="" id="ouHighlight__43_49TO46_52">números</span><span id="noHighlight_0.4030076161243532"> </span><span class="" id="ouHighlight__51_55TO54_58">dados</span><span id="noHighlight_0.31576993081735094"> </span><span class="" id="ouHighlight__57_58TO60_61">na</span><span id="noHighlight_0.6356775429366543"> </span><span class="" id="ouHighlight__64_68TO63_69">entrada</span><span id="noHighlight_0.9467709535211911">.</span></div></div>  ', 'Para este problema se debe calcular <span style="font-weight: bold;">A + B</span>, números dados en la entrada.', 'A + B problema', 'Problema A+B', 'La única línea de entrada contiene dos enteros separados por espacio: <span style="font-weight: bold;">A, B (0 <= A, B <= 10)</span>.', '<div style="direction: ltr; text-align: left;" id="OutputText" tabindex="99999" class="Wrap"><div><span class="" id="ouHighlight__0_2TO0_0">A</span><span id="noHighlight_0.4735847480411235"> </span><span class="" id="ouHighlight__4_7TO2_6">única</span><span id="noHighlight_0.9015287132317485"> </span><span class="" id="ouHighlight__9_12TO8_12">linha</span><span id="noHighlight_0.18840240470056424"> </span><span class="" id="ouHighlight__14_15TO14_15">de</span><span id="noHighlight_0.9668276021823131"> </span><span class="" id="ouHighlight__17_21TO17_23">entrada</span><span id="noHighlight_0.6862893988307441"> </span><span class="" id="ouHighlight__23_29TO25_30">contêm</span><span id="noHighlight_0.9958348718663692"> </span><span id="ouHighlight__31_33TO32_35">dois</span><span id="noHighlight_0.19251634960577524"></span><span id="noHighlight_0.7631459220385737"> </span><span class="" id="ouHighlight__51_58TO57_64">inteiros</span><span id="noHighlight_0.99720132576909"> </span><span class="" id="ouHighlight__60_61TO66_67"><b>A</b>,</span><span id="noHighlight_0.8354512176787399"> </span><b><span class="" id="ouHighlight__63_63TO69_69">B</span></b><span id="noHighlight_0.5138678538593187"> </span><span class="" id="ouHighlight__65_66TO71_72">(0</span><span id="noHighlight_0.352025252272387"> </span><span class="" id="ouHighlight__68_69TO74_76">&lt; =</span><span id="noHighlight_0.8205472251943318"> </span><b><span class="highlight" id="ouHighlight__71_72TO78_79">A,</span><span id="noHighlight_0.29511662164040997"> </span><span id="ouHighlight__74_74TO81_81">B</span></b><span id="noHighlight_0.2935071953702073"> </span><span class="" id="ouHighlight__76_77TO83_85">&lt; =</span><span id="noHighlight_0.33783352169154635"> </span><span class="" id="ouHighlight__79_82TO87_90">10), </span><span class="" id="ouHighlight__79_82TO87_90"><span id="noHighlight_0.19251634960577524"></span><span class="" id="ouHighlight__41_49TO37_44">separada</span><span id="noHighlight_0.9564295049159057"> por </span><span class="" id="ouHighlight__35_39TO50_55">espaço</span><span id="noHighlight_0.7631459220385737"></span>.</span></div></div>  ', '<div style="direction: ltr; text-align: left;" id="OutputText" tabindex="99999" class="Wrap"><div><span class="" id="ouHighlight__0_2TO0_0">A única linha de saída deve conter um número inteiro: <b>A</b> soma da e <b>B</b>.</span><span class="" id="ouHighlight__69_70TO66_67"></span></div></div>  ', 'La única línea de salida puede contener un solo número entero: la suma de <span style="font-weight: bold;">A</span> y <span style="font-weight: bold;">B</span>.', true, '', 418);


--
-- Data for Name: problem_classification; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO problem_classification VALUES (2, 1000, 1);
INSERT INTO problem_classification VALUES (3, 1001, 3);
INSERT INTO problem_classification VALUES (2, 1002, 1);
INSERT INTO problem_classification VALUES (10, 1005, 2);
INSERT INTO problem_classification VALUES (5, 1007, 2);
INSERT INTO problem_classification VALUES (1, 1008, 3);
INSERT INTO problem_classification VALUES (2, 1009, 1);
INSERT INTO problem_classification VALUES (23, 1010, 2);
INSERT INTO problem_classification VALUES (3, 1012, 3);
INSERT INTO problem_classification VALUES (1, 1013, 2);
INSERT INTO problem_classification VALUES (23, 1015, 2);
INSERT INTO problem_classification VALUES (23, 1016, 1);
INSERT INTO problem_classification VALUES (1, 1017, 1);
INSERT INTO problem_classification VALUES (2, 1019, 2);
INSERT INTO problem_classification VALUES (25, 1003, 1);
INSERT INTO problem_classification VALUES (25, 1004, 1);
INSERT INTO problem_classification VALUES (25, 1006, 2);
INSERT INTO problem_classification VALUES (25, 1011, 1);
INSERT INTO problem_classification VALUES (25, 1014, 1);
INSERT INTO problem_classification VALUES (25, 1018, 2);
INSERT INTO problem_classification VALUES (25, 1020, 1);
INSERT INTO problem_classification VALUES (23, 1003, 1);


--
-- Data for Name: problem_contest; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: problem_id_source_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('problem_id_source_seq', 791, true);


--
-- Data for Name: problem_language; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO problem_language VALUES (1020, 4);
INSERT INTO problem_language VALUES (1020, 5);
INSERT INTO problem_language VALUES (1020, 2);
INSERT INTO problem_language VALUES (1020, 13);
INSERT INTO problem_language VALUES (1020, 3);
INSERT INTO problem_language VALUES (1020, 8);
INSERT INTO problem_language VALUES (1020, 6);
INSERT INTO problem_language VALUES (1020, 10);
INSERT INTO problem_language VALUES (1020, 7);
INSERT INTO problem_language VALUES (1020, 9);
INSERT INTO problem_language VALUES (1020, 1);
INSERT INTO problem_language VALUES (1013, 15);
INSERT INTO problem_language VALUES (1020, 15);
INSERT INTO problem_language VALUES (1019, 15);
INSERT INTO problem_language VALUES (1018, 15);
INSERT INTO problem_language VALUES (1016, 15);
INSERT INTO problem_language VALUES (1015, 15);
INSERT INTO problem_language VALUES (1014, 15);
INSERT INTO problem_language VALUES (1017, 15);
INSERT INTO problem_language VALUES (1013, 13);
INSERT INTO problem_language VALUES (1014, 13);
INSERT INTO problem_language VALUES (1015, 13);
INSERT INTO problem_language VALUES (1016, 13);
INSERT INTO problem_language VALUES (1018, 13);
INSERT INTO problem_language VALUES (1017, 13);
INSERT INTO problem_language VALUES (1019, 13);
INSERT INTO problem_language VALUES (1013, 14);
INSERT INTO problem_language VALUES (1014, 14);
INSERT INTO problem_language VALUES (1015, 14);
INSERT INTO problem_language VALUES (1016, 14);
INSERT INTO problem_language VALUES (1018, 14);
INSERT INTO problem_language VALUES (1017, 14);
INSERT INTO problem_language VALUES (1019, 14);
INSERT INTO problem_language VALUES (1017, 16);
INSERT INTO problem_language VALUES (1013, 16);
INSERT INTO problem_language VALUES (1020, 16);
INSERT INTO problem_language VALUES (1019, 16);
INSERT INTO problem_language VALUES (1018, 16);
INSERT INTO problem_language VALUES (1016, 16);
INSERT INTO problem_language VALUES (1015, 16);
INSERT INTO problem_language VALUES (1014, 16);
INSERT INTO problem_language VALUES (1017, 18);
INSERT INTO problem_language VALUES (1013, 18);
INSERT INTO problem_language VALUES (1020, 18);
INSERT INTO problem_language VALUES (1019, 18);
INSERT INTO problem_language VALUES (1018, 18);
INSERT INTO problem_language VALUES (1016, 18);
INSERT INTO problem_language VALUES (1015, 18);
INSERT INTO problem_language VALUES (1014, 18);
INSERT INTO problem_language VALUES (1019, 2);
INSERT INTO problem_language VALUES (1018, 3);
INSERT INTO problem_language VALUES (1018, 4);
INSERT INTO problem_language VALUES (1019, 1);
INSERT INTO problem_language VALUES (1017, 2);
INSERT INTO problem_language VALUES (1019, 9);
INSERT INTO problem_language VALUES (1018, 6);
INSERT INTO problem_language VALUES (1016, 2);
INSERT INTO problem_language VALUES (1013, 10);
INSERT INTO problem_language VALUES (1014, 4);
INSERT INTO problem_language VALUES (1019, 3);
INSERT INTO problem_language VALUES (1014, 6);
INSERT INTO problem_language VALUES (1013, 6);
INSERT INTO problem_language VALUES (1018, 1);
INSERT INTO problem_language VALUES (1016, 3);
INSERT INTO problem_language VALUES (1019, 8);
INSERT INTO problem_language VALUES (1017, 1);
INSERT INTO problem_language VALUES (1015, 1);
INSERT INTO problem_language VALUES (1013, 7);
INSERT INTO problem_language VALUES (1017, 8);
INSERT INTO problem_language VALUES (1018, 5);
INSERT INTO problem_language VALUES (1018, 8);
INSERT INTO problem_language VALUES (1018, 2);
INSERT INTO problem_language VALUES (1018, 9);
INSERT INTO problem_language VALUES (1015, 7);
INSERT INTO problem_language VALUES (1019, 4);
INSERT INTO problem_language VALUES (1014, 5);
INSERT INTO problem_language VALUES (1015, 8);
INSERT INTO problem_language VALUES (1019, 7);
INSERT INTO problem_language VALUES (1013, 8);
INSERT INTO problem_language VALUES (1015, 10);
INSERT INTO problem_language VALUES (1013, 5);
INSERT INTO problem_language VALUES (1018, 10);
INSERT INTO problem_language VALUES (1015, 9);
INSERT INTO problem_language VALUES (1016, 1);
INSERT INTO problem_language VALUES (1014, 1);
INSERT INTO problem_language VALUES (1013, 4);
INSERT INTO problem_language VALUES (1013, 1);
INSERT INTO problem_language VALUES (1014, 3);
INSERT INTO problem_language VALUES (1013, 2);
INSERT INTO problem_language VALUES (1014, 10);
INSERT INTO problem_language VALUES (1015, 6);
INSERT INTO problem_language VALUES (1015, 3);
INSERT INTO problem_language VALUES (1015, 4);
INSERT INTO problem_language VALUES (1013, 3);
INSERT INTO problem_language VALUES (1014, 8);
INSERT INTO problem_language VALUES (1014, 2);
INSERT INTO problem_language VALUES (1015, 5);
INSERT INTO problem_language VALUES (1014, 9);
INSERT INTO problem_language VALUES (1016, 6);
INSERT INTO problem_language VALUES (1013, 9);
INSERT INTO problem_language VALUES (1017, 5);
INSERT INTO problem_language VALUES (1017, 4);
INSERT INTO problem_language VALUES (1016, 7);
INSERT INTO problem_language VALUES (1016, 10);
INSERT INTO problem_language VALUES (1019, 10);
INSERT INTO problem_language VALUES (1016, 8);
INSERT INTO problem_language VALUES (1017, 6);
INSERT INTO problem_language VALUES (1017, 7);
INSERT INTO problem_language VALUES (1016, 9);
INSERT INTO problem_language VALUES (1019, 6);
INSERT INTO problem_language VALUES (1015, 2);
INSERT INTO problem_language VALUES (1019, 5);
INSERT INTO problem_language VALUES (1016, 5);
INSERT INTO problem_language VALUES (1017, 3);
INSERT INTO problem_language VALUES (1018, 7);
INSERT INTO problem_language VALUES (1017, 10);
INSERT INTO problem_language VALUES (1016, 4);
INSERT INTO problem_language VALUES (1014, 7);
INSERT INTO problem_language VALUES (1017, 9);
INSERT INTO problem_language VALUES (1006, 2);
INSERT INTO problem_language VALUES (1006, 5);
INSERT INTO problem_language VALUES (1006, 7);
INSERT INTO problem_language VALUES (1006, 1);
INSERT INTO problem_language VALUES (1006, 10);
INSERT INTO problem_language VALUES (1006, 11);
INSERT INTO problem_language VALUES (1006, 15);
INSERT INTO problem_language VALUES (1006, 3);
INSERT INTO problem_language VALUES (1006, 4);
INSERT INTO problem_language VALUES (1006, 8);
INSERT INTO problem_language VALUES (1006, 13);
INSERT INTO problem_language VALUES (1006, 9);
INSERT INTO problem_language VALUES (1006, 6);
INSERT INTO problem_language VALUES (1006, 18);
INSERT INTO problem_language VALUES (1001, 2);
INSERT INTO problem_language VALUES (1001, 5);
INSERT INTO problem_language VALUES (1001, 7);
INSERT INTO problem_language VALUES (1001, 13);
INSERT INTO problem_language VALUES (1001, 9);
INSERT INTO problem_language VALUES (1001, 6);
INSERT INTO problem_language VALUES (1001, 18);
INSERT INTO problem_language VALUES (1002, 3);
INSERT INTO problem_language VALUES (1002, 4);
INSERT INTO problem_language VALUES (1002, 8);
INSERT INTO problem_language VALUES (1002, 10);
INSERT INTO problem_language VALUES (1002, 11);
INSERT INTO problem_language VALUES (1002, 15);
INSERT INTO problem_language VALUES (1003, 2);
INSERT INTO problem_language VALUES (1003, 5);
INSERT INTO problem_language VALUES (1003, 7);
INSERT INTO problem_language VALUES (1003, 13);
INSERT INTO problem_language VALUES (1003, 9);
INSERT INTO problem_language VALUES (1003, 6);
INSERT INTO problem_language VALUES (1003, 18);
INSERT INTO problem_language VALUES (1008, 2);
INSERT INTO problem_language VALUES (1008, 5);
INSERT INTO problem_language VALUES (1008, 7);
INSERT INTO problem_language VALUES (1008, 1);
INSERT INTO problem_language VALUES (1008, 10);
INSERT INTO problem_language VALUES (1008, 6);
INSERT INTO problem_language VALUES (1008, 18);
INSERT INTO problem_language VALUES (1009, 3);
INSERT INTO problem_language VALUES (1009, 4);
INSERT INTO problem_language VALUES (1009, 1);
INSERT INTO problem_language VALUES (1009, 10);
INSERT INTO problem_language VALUES (1009, 6);
INSERT INTO problem_language VALUES (1009, 18);
INSERT INTO problem_language VALUES (1011, 3);
INSERT INTO problem_language VALUES (1011, 4);
INSERT INTO problem_language VALUES (1011, 8);
INSERT INTO problem_language VALUES (1011, 13);
INSERT INTO problem_language VALUES (1011, 9);
INSERT INTO problem_language VALUES (1011, 15);
INSERT INTO problem_language VALUES (1001, 3);
INSERT INTO problem_language VALUES (1001, 4);
INSERT INTO problem_language VALUES (1001, 8);
INSERT INTO problem_language VALUES (1001, 10);
INSERT INTO problem_language VALUES (1001, 11);
INSERT INTO problem_language VALUES (1001, 15);
INSERT INTO problem_language VALUES (1002, 2);
INSERT INTO problem_language VALUES (1002, 5);
INSERT INTO problem_language VALUES (1002, 7);
INSERT INTO problem_language VALUES (1002, 13);
INSERT INTO problem_language VALUES (1002, 9);
INSERT INTO problem_language VALUES (1002, 6);
INSERT INTO problem_language VALUES (1002, 18);
INSERT INTO problem_language VALUES (1003, 3);
INSERT INTO problem_language VALUES (1003, 4);
INSERT INTO problem_language VALUES (1003, 8);
INSERT INTO problem_language VALUES (1003, 10);
INSERT INTO problem_language VALUES (1003, 11);
INSERT INTO problem_language VALUES (1003, 15);
INSERT INTO problem_language VALUES (1004, 2);
INSERT INTO problem_language VALUES (1004, 3);
INSERT INTO problem_language VALUES (1004, 5);
INSERT INTO problem_language VALUES (1004, 4);
INSERT INTO problem_language VALUES (1004, 7);
INSERT INTO problem_language VALUES (1004, 8);
INSERT INTO problem_language VALUES (1004, 13);
INSERT INTO problem_language VALUES (1004, 10);
INSERT INTO problem_language VALUES (1004, 9);
INSERT INTO problem_language VALUES (1004, 11);
INSERT INTO problem_language VALUES (1004, 6);
INSERT INTO problem_language VALUES (1004, 15);
INSERT INTO problem_language VALUES (1004, 18);
INSERT INTO problem_language VALUES (1005, 2);
INSERT INTO problem_language VALUES (1005, 3);
INSERT INTO problem_language VALUES (1005, 5);
INSERT INTO problem_language VALUES (1005, 4);
INSERT INTO problem_language VALUES (1005, 7);
INSERT INTO problem_language VALUES (1005, 8);
INSERT INTO problem_language VALUES (1005, 13);
INSERT INTO problem_language VALUES (1005, 10);
INSERT INTO problem_language VALUES (1005, 9);
INSERT INTO problem_language VALUES (1005, 11);
INSERT INTO problem_language VALUES (1005, 6);
INSERT INTO problem_language VALUES (1005, 15);
INSERT INTO problem_language VALUES (1005, 18);
INSERT INTO problem_language VALUES (1007, 2);
INSERT INTO problem_language VALUES (1007, 3);
INSERT INTO problem_language VALUES (1007, 5);
INSERT INTO problem_language VALUES (1007, 4);
INSERT INTO problem_language VALUES (1007, 7);
INSERT INTO problem_language VALUES (1007, 8);
INSERT INTO problem_language VALUES (1007, 1);
INSERT INTO problem_language VALUES (1007, 13);
INSERT INTO problem_language VALUES (1007, 10);
INSERT INTO problem_language VALUES (1007, 9);
INSERT INTO problem_language VALUES (1007, 6);
INSERT INTO problem_language VALUES (1007, 15);
INSERT INTO problem_language VALUES (1007, 18);
INSERT INTO problem_language VALUES (1008, 3);
INSERT INTO problem_language VALUES (1008, 4);
INSERT INTO problem_language VALUES (1008, 8);
INSERT INTO problem_language VALUES (1008, 13);
INSERT INTO problem_language VALUES (1008, 9);
INSERT INTO problem_language VALUES (1008, 15);
INSERT INTO problem_language VALUES (1009, 2);
INSERT INTO problem_language VALUES (1009, 5);
INSERT INTO problem_language VALUES (1009, 7);
INSERT INTO problem_language VALUES (1009, 8);
INSERT INTO problem_language VALUES (1009, 13);
INSERT INTO problem_language VALUES (1009, 9);
INSERT INTO problem_language VALUES (1009, 15);
INSERT INTO problem_language VALUES (1010, 2);
INSERT INTO problem_language VALUES (1010, 5);
INSERT INTO problem_language VALUES (1010, 7);
INSERT INTO problem_language VALUES (1010, 1);
INSERT INTO problem_language VALUES (1010, 10);
INSERT INTO problem_language VALUES (1010, 6);
INSERT INTO problem_language VALUES (1010, 18);
INSERT INTO problem_language VALUES (1011, 2);
INSERT INTO problem_language VALUES (1011, 5);
INSERT INTO problem_language VALUES (1011, 7);
INSERT INTO problem_language VALUES (1011, 1);
INSERT INTO problem_language VALUES (1011, 10);
INSERT INTO problem_language VALUES (1011, 6);
INSERT INTO problem_language VALUES (1011, 18);
INSERT INTO problem_language VALUES (1012, 3);
INSERT INTO problem_language VALUES (1012, 4);
INSERT INTO problem_language VALUES (1012, 8);
INSERT INTO problem_language VALUES (1012, 13);
INSERT INTO problem_language VALUES (1012, 9);
INSERT INTO problem_language VALUES (1012, 15);
INSERT INTO problem_language VALUES (1010, 3);
INSERT INTO problem_language VALUES (1010, 4);
INSERT INTO problem_language VALUES (1010, 8);
INSERT INTO problem_language VALUES (1010, 13);
INSERT INTO problem_language VALUES (1010, 9);
INSERT INTO problem_language VALUES (1010, 15);
INSERT INTO problem_language VALUES (1012, 2);
INSERT INTO problem_language VALUES (1012, 5);
INSERT INTO problem_language VALUES (1012, 7);
INSERT INTO problem_language VALUES (1012, 1);
INSERT INTO problem_language VALUES (1012, 10);
INSERT INTO problem_language VALUES (1012, 6);
INSERT INTO problem_language VALUES (1012, 18);
INSERT INTO problem_language VALUES (1000, 2);
INSERT INTO problem_language VALUES (1000, 3);
INSERT INTO problem_language VALUES (1000, 5);
INSERT INTO problem_language VALUES (1000, 4);
INSERT INTO problem_language VALUES (1000, 7);
INSERT INTO problem_language VALUES (1000, 8);
INSERT INTO problem_language VALUES (1000, 1);
INSERT INTO problem_language VALUES (1000, 13);
INSERT INTO problem_language VALUES (1000, 10);
INSERT INTO problem_language VALUES (1000, 9);
INSERT INTO problem_language VALUES (1000, 11);
INSERT INTO problem_language VALUES (1000, 6);
INSERT INTO problem_language VALUES (1000, 15);
INSERT INTO problem_language VALUES (1000, 18);


--
-- Data for Name: problem_limits; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO problem_limits VALUES (1016, 1, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1016, 2, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1016, 4, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1016, 13, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1016, 16, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1014, 1, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1014, 2, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1014, 4, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1014, 13, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1014, 16, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1013, 1, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1013, 2, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1013, 4, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1013, 13, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1013, 16, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1020, 1, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1020, 2, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1020, 4, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1020, 13, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1020, 16, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1018, 1, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1018, 2, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1018, 4, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1018, 13, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1018, 16, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1015, 1, 2000, 4000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1015, 2, 2000, 4000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1015, 4, 2000, 4000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1015, 13, 2000, 4000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1015, 16, 2000, 4000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1017, 1, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1017, 2, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1017, 4, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1017, 13, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1017, 16, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1019, 1, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1019, 2, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1019, 4, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1019, 13, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1019, 16, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1016, 3, 6000, 6000, 3932160000, 30720);
INSERT INTO problem_limits VALUES (1014, 3, 6000, 6000, 3932160000, 30720);
INSERT INTO problem_limits VALUES (1013, 3, 6000, 6000, 3932160000, 30720);
INSERT INTO problem_limits VALUES (1020, 3, 18000, 18000, 3932160000, 30720);
INSERT INTO problem_limits VALUES (1018, 3, 6000, 6000, 3932160000, 30720);
INSERT INTO problem_limits VALUES (1015, 3, 6000, 12000, 3932160000, 30720);
INSERT INTO problem_limits VALUES (1017, 3, 6000, 6000, 3932160000, 30720);
INSERT INTO problem_limits VALUES (1019, 3, 6000, 6000, 3932160000, 30720);
INSERT INTO problem_limits VALUES (1016, 5, 4000, 4000, 1310720000, 30720);
INSERT INTO problem_limits VALUES (1014, 5, 4000, 4000, 1310720000, 30720);
INSERT INTO problem_limits VALUES (1013, 5, 4000, 4000, 1310720000, 30720);
INSERT INTO problem_limits VALUES (1020, 5, 12000, 12000, 1310720000, 30720);
INSERT INTO problem_limits VALUES (1018, 5, 4000, 4000, 1310720000, 30720);
INSERT INTO problem_limits VALUES (1015, 5, 4000, 8000, 1310720000, 30720);
INSERT INTO problem_limits VALUES (1017, 5, 4000, 4000, 1310720000, 30720);
INSERT INTO problem_limits VALUES (1019, 5, 4000, 4000, 1310720000, 30720);
INSERT INTO problem_limits VALUES (1016, 6, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1014, 6, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1013, 6, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1020, 6, 18000, 18000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1018, 6, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1015, 6, 6000, 12000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1017, 6, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1019, 6, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1016, 7, 12000, 12000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1014, 7, 12000, 12000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1013, 7, 12000, 12000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1020, 7, 36000, 36000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1018, 7, 12000, 12000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1015, 7, 12000, 24000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1017, 7, 12000, 12000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1019, 7, 12000, 12000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1016, 8, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1014, 8, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1013, 8, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1020, 8, 18000, 18000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1018, 8, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1015, 8, 6000, 12000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1017, 8, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1019, 8, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1016, 9, 6000, 6000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1014, 9, 6000, 6000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1013, 9, 6000, 6000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1020, 9, 18000, 18000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1018, 9, 6000, 6000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1015, 9, 6000, 12000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1017, 9, 6000, 6000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1019, 9, 6000, 6000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1016, 10, 12000, 12000, 327680000, 30720);
INSERT INTO problem_limits VALUES (1014, 10, 12000, 12000, 327680000, 30720);
INSERT INTO problem_limits VALUES (1013, 10, 12000, 12000, 327680000, 30720);
INSERT INTO problem_limits VALUES (1020, 10, 36000, 36000, 327680000, 30720);
INSERT INTO problem_limits VALUES (1018, 10, 12000, 12000, 327680000, 30720);
INSERT INTO problem_limits VALUES (1015, 10, 12000, 24000, 327680000, 30720);
INSERT INTO problem_limits VALUES (1017, 10, 12000, 12000, 327680000, 30720);
INSERT INTO problem_limits VALUES (1019, 10, 12000, 12000, 327680000, 30720);
INSERT INTO problem_limits VALUES (1016, 11, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1014, 11, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1013, 11, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1020, 11, 18000, 18000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1018, 11, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1015, 11, 6000, 12000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1017, 11, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1019, 11, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1016, 14, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1014, 14, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1013, 14, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1020, 14, 18000, 18000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1018, 14, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1015, 14, 6000, 12000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1017, 14, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1019, 14, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1016, 15, 6000, 6000, 1638400000, 30720);
INSERT INTO problem_limits VALUES (1014, 15, 6000, 6000, 1638400000, 30720);
INSERT INTO problem_limits VALUES (1013, 15, 6000, 6000, 1638400000, 30720);
INSERT INTO problem_limits VALUES (1020, 15, 18000, 18000, 1638400000, 30720);
INSERT INTO problem_limits VALUES (1018, 15, 6000, 6000, 1638400000, 30720);
INSERT INTO problem_limits VALUES (1015, 15, 6000, 12000, 1638400000, 30720);
INSERT INTO problem_limits VALUES (1017, 15, 6000, 6000, 1638400000, 30720);
INSERT INTO problem_limits VALUES (1019, 15, 6000, 6000, 1638400000, 30720);
INSERT INTO problem_limits VALUES (1016, 18, 12000, 12000, 983040000, 30720);
INSERT INTO problem_limits VALUES (1014, 18, 12000, 12000, 983040000, 30720);
INSERT INTO problem_limits VALUES (1013, 18, 12000, 12000, 983040000, 30720);
INSERT INTO problem_limits VALUES (1020, 18, 36000, 36000, 983040000, 30720);
INSERT INTO problem_limits VALUES (1018, 18, 12000, 12000, 983040000, 30720);
INSERT INTO problem_limits VALUES (1015, 18, 12000, 24000, 983040000, 30720);
INSERT INTO problem_limits VALUES (1017, 18, 12000, 12000, 983040000, 30720);
INSERT INTO problem_limits VALUES (1019, 18, 12000, 12000, 983040000, 30720);
INSERT INTO problem_limits VALUES (1001, 2, 4000, 20000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1001, 3, 12000, 60000, 3932160000, 10000);
INSERT INTO problem_limits VALUES (1001, 4, 4000, 20000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1001, 8, 12000, 60000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1001, 1, 4000, 20000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1001, 13, 4000, 20000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1001, 9, 12000, 60000, 131072000, 10000);
INSERT INTO problem_limits VALUES (1001, 15, 12000, 60000, 1638400000, 10000);
INSERT INTO problem_limits VALUES (1002, 2, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1002, 5, 4000, 4000, 1310720000, 10000);
INSERT INTO problem_limits VALUES (1002, 7, 12000, 12000, 131072000, 10000);
INSERT INTO problem_limits VALUES (1002, 1, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1002, 10, 12000, 12000, 327680000, 10000);
INSERT INTO problem_limits VALUES (1002, 11, 6000, 6000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1002, 15, 6000, 6000, 1638400000, 10000);
INSERT INTO problem_limits VALUES (1003, 4, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1003, 8, 6000, 6000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1003, 1, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1003, 10, 12000, 12000, 327680000, 10000);
INSERT INTO problem_limits VALUES (1003, 6, 6000, 6000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1003, 15, 6000, 6000, 1638400000, 10000);
INSERT INTO problem_limits VALUES (1003, 18, 12000, 12000, 983040000, 10000);
INSERT INTO problem_limits VALUES (1004, 3, 6000, 6000, 3932160000, 10000);
INSERT INTO problem_limits VALUES (1004, 4, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1004, 8, 6000, 6000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1004, 13, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1004, 9, 6000, 6000, 131072000, 10000);
INSERT INTO problem_limits VALUES (1004, 6, 6000, 6000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1004, 18, 12000, 12000, 983040000, 10000);
INSERT INTO problem_limits VALUES (1005, 2, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1005, 5, 4000, 4000, 1310720000, 10000);
INSERT INTO problem_limits VALUES (1005, 4, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1005, 7, 12000, 12000, 131072000, 10000);
INSERT INTO problem_limits VALUES (1005, 8, 6000, 6000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1005, 1, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1005, 13, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1005, 10, 12000, 12000, 327680000, 10000);
INSERT INTO problem_limits VALUES (1005, 9, 6000, 6000, 131072000, 10000);
INSERT INTO problem_limits VALUES (1005, 11, 6000, 6000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1005, 6, 6000, 6000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1005, 18, 12000, 12000, 983040000, 10000);
INSERT INTO problem_limits VALUES (1007, 2, 5000, 50000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1007, 3, 15000, 150000, 3932160000, 30000);
INSERT INTO problem_limits VALUES (1007, 5, 10000, 100000, 1310720000, 30000);
INSERT INTO problem_limits VALUES (1007, 4, 5000, 50000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1001, 5, 8000, 40000, 1310720000, 10000);
INSERT INTO problem_limits VALUES (1001, 7, 24000, 120000, 131072000, 10000);
INSERT INTO problem_limits VALUES (1001, 10, 24000, 120000, 327680000, 10000);
INSERT INTO problem_limits VALUES (1001, 11, 12000, 60000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1001, 6, 12000, 60000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1001, 18, 24000, 120000, 983040000, 10000);
INSERT INTO problem_limits VALUES (1002, 3, 6000, 6000, 3932160000, 10000);
INSERT INTO problem_limits VALUES (1002, 4, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1002, 8, 6000, 6000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1002, 13, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1002, 9, 6000, 6000, 131072000, 10000);
INSERT INTO problem_limits VALUES (1002, 6, 6000, 6000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1002, 18, 12000, 12000, 983040000, 10000);
INSERT INTO problem_limits VALUES (1003, 2, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1003, 3, 6000, 6000, 3932160000, 10000);
INSERT INTO problem_limits VALUES (1003, 5, 4000, 4000, 1310720000, 10000);
INSERT INTO problem_limits VALUES (1003, 7, 12000, 12000, 131072000, 10000);
INSERT INTO problem_limits VALUES (1003, 13, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1003, 9, 6000, 6000, 131072000, 10000);
INSERT INTO problem_limits VALUES (1003, 11, 6000, 6000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1004, 2, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1004, 5, 4000, 4000, 1310720000, 10000);
INSERT INTO problem_limits VALUES (1004, 7, 12000, 12000, 131072000, 10000);
INSERT INTO problem_limits VALUES (1004, 1, 2000, 2000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1004, 10, 12000, 12000, 327680000, 10000);
INSERT INTO problem_limits VALUES (1004, 11, 6000, 6000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1004, 15, 6000, 6000, 1638400000, 10000);
INSERT INTO problem_limits VALUES (1005, 3, 6000, 6000, 3932160000, 10000);
INSERT INTO problem_limits VALUES (1005, 15, 6000, 6000, 1638400000, 10000);
INSERT INTO problem_limits VALUES (1008, 3, 6000, 6000, 3932160000, 30720);
INSERT INTO problem_limits VALUES (1008, 4, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1008, 8, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1008, 13, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1008, 9, 6000, 6000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1008, 6, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1008, 18, 12000, 12000, 983040000, 30720);
INSERT INTO problem_limits VALUES (1011, 2, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1011, 5, 4000, 4000, 1310720000, 30720);
INSERT INTO problem_limits VALUES (1011, 7, 12000, 12000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1011, 1, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1011, 10, 12000, 12000, 327680000, 30720);
INSERT INTO problem_limits VALUES (1011, 11, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1011, 15, 6000, 6000, 1638400000, 30720);
INSERT INTO problem_limits VALUES (1007, 7, 30000, 300000, 131072000, 30000);
INSERT INTO problem_limits VALUES (1007, 1, 5000, 50000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1007, 10, 30000, 300000, 327680000, 30000);
INSERT INTO problem_limits VALUES (1007, 11, 15000, 150000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1007, 18, 30000, 300000, 983040000, 30000);
INSERT INTO problem_limits VALUES (1008, 2, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1008, 5, 4000, 4000, 1310720000, 30720);
INSERT INTO problem_limits VALUES (1008, 7, 12000, 12000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1008, 1, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1008, 10, 12000, 12000, 327680000, 30720);
INSERT INTO problem_limits VALUES (1008, 11, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1008, 15, 6000, 6000, 1638400000, 30720);
INSERT INTO problem_limits VALUES (1009, 2, 5000, 5000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1009, 3, 15000, 15000, 3932160000, 30720);
INSERT INTO problem_limits VALUES (1009, 4, 5000, 5000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1007, 8, 15000, 150000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1007, 13, 5000, 50000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1007, 9, 15000, 150000, 131072000, 30000);
INSERT INTO problem_limits VALUES (1007, 6, 15000, 150000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1007, 15, 15000, 150000, 1638400000, 30000);
INSERT INTO problem_limits VALUES (1009, 5, 10000, 10000, 1310720000, 30720);
INSERT INTO problem_limits VALUES (1009, 7, 30000, 30000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1009, 8, 15000, 15000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1009, 10, 30000, 30000, 327680000, 30720);
INSERT INTO problem_limits VALUES (1009, 11, 15000, 15000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1009, 15, 15000, 15000, 1638400000, 30720);
INSERT INTO problem_limits VALUES (1009, 1, 5000, 5000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1009, 13, 5000, 5000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1009, 9, 15000, 15000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1009, 6, 15000, 15000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1009, 18, 30000, 30000, 983040000, 30720);
INSERT INTO problem_limits VALUES (1010, 3, 12000, 30000, 3932160000, 30000);
INSERT INTO problem_limits VALUES (1010, 4, 4000, 10000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1010, 8, 12000, 30000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1010, 13, 4000, 10000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1010, 9, 12000, 30000, 131072000, 30000);
INSERT INTO problem_limits VALUES (1010, 6, 12000, 30000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1010, 18, 24000, 60000, 983040000, 30000);
INSERT INTO problem_limits VALUES (1011, 3, 6000, 6000, 3932160000, 30720);
INSERT INTO problem_limits VALUES (1011, 4, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1011, 8, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1011, 13, 2000, 2000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1011, 9, 6000, 6000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1011, 6, 6000, 6000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1011, 18, 12000, 12000, 983040000, 30720);
INSERT INTO problem_limits VALUES (1012, 2, 2000, 20000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1012, 5, 4000, 40000, 1310720000, 30720);
INSERT INTO problem_limits VALUES (1012, 7, 12000, 120000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1012, 1, 2000, 20000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1012, 10, 12000, 120000, 327680000, 30720);
INSERT INTO problem_limits VALUES (1012, 11, 6000, 60000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1012, 15, 6000, 60000, 1638400000, 30720);
INSERT INTO problem_limits VALUES (1010, 2, 4000, 10000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1010, 5, 8000, 20000, 1310720000, 30000);
INSERT INTO problem_limits VALUES (1010, 7, 24000, 60000, 131072000, 30000);
INSERT INTO problem_limits VALUES (1010, 1, 4000, 10000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1010, 10, 24000, 60000, 327680000, 30000);
INSERT INTO problem_limits VALUES (1012, 3, 6000, 60000, 3932160000, 30720);
INSERT INTO problem_limits VALUES (1012, 4, 2000, 20000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1012, 8, 6000, 60000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1012, 13, 2000, 20000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1012, 9, 6000, 60000, 131072000, 30720);
INSERT INTO problem_limits VALUES (1012, 6, 6000, 60000, 65536000, 30720);
INSERT INTO problem_limits VALUES (1012, 18, 12000, 120000, 983040000, 30720);
INSERT INTO problem_limits VALUES (1010, 11, 12000, 30000, 65536000, 30000);
INSERT INTO problem_limits VALUES (1010, 15, 12000, 30000, 1638400000, 30000);
INSERT INTO problem_limits VALUES (1006, 2, 2000, 2000, 65536000, 5000);
INSERT INTO problem_limits VALUES (1006, 5, 4000, 4000, 1310720000, 5000);
INSERT INTO problem_limits VALUES (1006, 7, 12000, 12000, 131072000, 5000);
INSERT INTO problem_limits VALUES (1006, 1, 2000, 2000, 65536000, 5000);
INSERT INTO problem_limits VALUES (1006, 10, 12000, 12000, 327680000, 5000);
INSERT INTO problem_limits VALUES (1006, 11, 6000, 6000, 65536000, 5000);
INSERT INTO problem_limits VALUES (1006, 15, 6000, 6000, 1638400000, 5000);
INSERT INTO problem_limits VALUES (1006, 3, 6000, 6000, 3932160000, 5000);
INSERT INTO problem_limits VALUES (1006, 4, 2000, 2000, 65536000, 5000);
INSERT INTO problem_limits VALUES (1006, 8, 6000, 6000, 65536000, 5000);
INSERT INTO problem_limits VALUES (1006, 13, 2000, 2000, 65536000, 5000);
INSERT INTO problem_limits VALUES (1006, 9, 6000, 6000, 131072000, 5000);
INSERT INTO problem_limits VALUES (1006, 6, 6000, 6000, 65536000, 5000);
INSERT INTO problem_limits VALUES (1006, 18, 12000, 12000, 983040000, 5000);
INSERT INTO problem_limits VALUES (1000, 2, 2000, 10000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1000, 3, 10000, 30000, 4294967296, 10000);
INSERT INTO problem_limits VALUES (1000, 5, 4000, 20000, 1310720000, 10000);
INSERT INTO problem_limits VALUES (1000, 4, 2000, 10000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1000, 7, 12000, 60000, 131072000, 10000);
INSERT INTO problem_limits VALUES (1000, 8, 6000, 30000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1000, 1, 2000, 10000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1000, 13, 2000, 10000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1000, 10, 12000, 60000, 327680000, 10000);
INSERT INTO problem_limits VALUES (1000, 9, 6000, 30000, 131072000, 10000);
INSERT INTO problem_limits VALUES (1000, 11, 6000, 30000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1000, 6, 6000, 30000, 65536000, 10000);
INSERT INTO problem_limits VALUES (1000, 15, 6000, 30000, 1638400000, 10000);
INSERT INTO problem_limits VALUES (1000, 18, 12000, 60000, 983040000, 10000);


--
-- Data for Name: problem_source; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO problem_source VALUES ('2009 ACM-ICPC INC', 45, '');
INSERT INTO problem_source VALUES ('2003 ACM-ICPC Southwestern European Regional Contest', 76, '');
INSERT INTO problem_source VALUES ('Codechef Online Contest', 150, '');
INSERT INTO problem_source VALUES ('2008 CodeCraft', 151, '');
INSERT INTO problem_source VALUES ('2010 Copa Pascal UCI', 154, '');
INSERT INTO problem_source VALUES ('2008 IPSC', 234, '');
INSERT INTO problem_source VALUES ('2009 Waterloo Local Contest', 620, 'Malcolm Sharpe');
INSERT INTO problem_source VALUES ('USACO Training Problems', 593, '');
INSERT INTO problem_source VALUES ('Typical problem (almost every online judge include it)', 418, '');
INSERT INTO problem_source VALUES ('2001 Ukrainian National Olympiad in Informatics', 432, '');
INSERT INTO problem_source VALUES ('University of Porto Alegre Local Contest', 441, '');
INSERT INTO problem_source VALUES ('Waterloo Local Contest', 494, 'Piotr Rudnicki');
INSERT INTO problem_source VALUES ('2005 Waterloo Local Contest', 626, '');
INSERT INTO problem_source VALUES ('2004 Waterloo Local Contest', 507, 'Piotr Rudnicki');
INSERT INTO problem_source VALUES ('2005 Waterloo Local Contest', 516, 'Richard Krueger');
INSERT INTO problem_source VALUES ('2005 Waterloo Local Contest', 625, 'Gordon V. Cormack');
INSERT INTO problem_source VALUES ('2004 Waterloo Local Contest', 505, 'Gordon V. Cormack');


--
-- Data for Name: problem_stats; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO problem_stats VALUES (1002, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1002, 0);
INSERT INTO problem_stats VALUES (1003, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1003, 0);
INSERT INTO problem_stats VALUES (1004, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1004, 0);
INSERT INTO problem_stats VALUES (1005, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1005, 0);
INSERT INTO problem_stats VALUES (1006, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1006, 0);
INSERT INTO problem_stats VALUES (1007, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1007, 0);
INSERT INTO problem_stats VALUES (1008, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1008, 0);
INSERT INTO problem_stats VALUES (1009, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1009, 0);
INSERT INTO problem_stats VALUES (1010, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1010, 0);
INSERT INTO problem_stats VALUES (1011, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1011, 0);
INSERT INTO problem_stats VALUES (1012, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1012, 0);
INSERT INTO problem_stats VALUES (1013, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1013, 0);
INSERT INTO problem_stats VALUES (1014, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1014, 0);
INSERT INTO problem_stats VALUES (1015, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1015, 0);
INSERT INTO problem_stats VALUES (1016, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1016, 0);
INSERT INTO problem_stats VALUES (1017, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1017, 0);
INSERT INTO problem_stats VALUES (1018, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1018, 0);
INSERT INTO problem_stats VALUES (1019, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1019, 0);
INSERT INTO problem_stats VALUES (1020, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1020, 0);
INSERT INTO problem_stats VALUES (1001, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1001, 0);
INSERT INTO problem_stats VALUES (1000, 3, 2, 1, 2, 2, 1, 0, 0, 0, 0, 0, 2, 1000, 0);


--
-- Data for Name: problem_stats_contest; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: problem_stats_stats_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('problem_stats_stats_id_seq', 7273706, true);


--
-- Data for Name: psetter_problem; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO psetter_problem VALUES (3, 1017);
INSERT INTO psetter_problem VALUES (3, 1018);
INSERT INTO psetter_problem VALUES (3, 1014);
INSERT INTO psetter_problem VALUES (3, 1013);
INSERT INTO psetter_problem VALUES (3, 1015);
INSERT INTO psetter_problem VALUES (3, 1019);
INSERT INTO psetter_problem VALUES (3, 1016);
INSERT INTO psetter_problem VALUES (65, 1006);


--
-- Data for Name: recommendation_profile; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO roles VALUES (11, 'ROLE_TRANSLATOR');
INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_SUPER_PSETTER');
INSERT INTO roles VALUES (5, 'ROLE_USER');
INSERT INTO roles VALUES (6, 'ROLE_TEAM');
INSERT INTO roles VALUES (3, 'ROLE_PSETTER');
INSERT INTO roles VALUES (4, 'ROLE_JUDGE');
INSERT INTO roles VALUES (9, 'ROLE_FILE_MANAGER');
INSERT INTO roles VALUES (10, 'ROLE_ENTRIES_MANAGER');


--
-- Name: roles_rid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('roles_rid_seq', 9, true);


--
-- Data for Name: send_mail; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: send_mail_idmail_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('send_mail_idmail_seq', 105959, true);


--
-- Name: shared_file_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('shared_file_seq', 88, true);


--
-- Data for Name: shared_files; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO shared_files VALUES (80, 'files/mpc-tlj/CuriosityGuideToCoding.pdf', 'CuriosityGuideToCoding.pdf', 798158);
INSERT INTO shared_files VALUES (86, 'files/COJ-CompleteGuideforProblemSetters.pdf', 'COJ-CompleteGuideforProblemSetters.pdf', 455718);


--
-- Data for Name: source; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO source VALUES (933814, '#include<iostream>
#include<cstdio>
#include<cmath>
#include<climits>
#include<iomanip>

using namespace std;

const int MAXV = 101;
double oo = 0;

int N;
double X[MAXV], Y[MAXV];
double MA[MAXV][MAXV];

bool mk[MAXV];
double C[MAXV];
double CostPrim(){
	for (int i = 1 ; i <= N ; i++)
		C[i] = oo;
	C[1] = 0;
	//mk[1] = true;
	double COSTO = 0.0;
	for (int i = 1 ; i <= N ; i++){
		double min = oo;
		int v;
		for (int j = 1 ; j <= N ; j++)
			if (!mk[j] && C[j] < min){
				min = C[j];
				v = j;
            }
		COSTO += min;
		mk[v] = true;
		for (int j = 1 ; j <= N ; j++)
			if (!mk[j]){
				if (C[j] > MA[v][j])
					C[j] = MA[v][j];
			}
	}
	return COSTO;
}



int main(){
	//freopen("d.i", "r", stdin);
	cin >> N;
	for (int i = 1 ; i <= N ; i++)
		cin >> X[i] >> Y[i];
	for (int i = 1 ; i < N ; i++)
		for (int j = i + 1 ; j <= N ; j++){
			double difX = X[j] - X[i];
			double difY = Y[j] - Y[i];
			MA[i][j] = MA[j][i] = sqrt(difX * difX + difY * difY);
			if (MA[i][j] + 1 > oo)
                oo = MA[i][j] + 1;
		}

    //cout << setprecision(3);
	//cout << CostPrim() << ''\n'';
	printf("%.2lf\n", CostPrim());
	//fclose(stdin);
	return 0;
}

', NULL);
INSERT INTO source VALUES (933815, 'compilation error', '/home/frank/UEngine/tmp/933815/933815.cc:1:1: error: ‘compilation’ does not name a type compilation error ^');
INSERT INTO source VALUES (933816, 'import java.util.*;

public class asd{
	public static void main(String args[]){
      		Scanner obj=new Scanner(System.in);
		
		int a=obj.nextInt();
		int b=obj.nextInt();		
		int c=a+b;

		System.out.println(c);

      	}

}', NULL);
INSERT INTO source VALUES (933817, 'a, b = raw_input().split('' '')

print int(a) + int(b)', NULL);
INSERT INTO source VALUES (933818, '#include<bits/stdc++.h>

using namespace std;

const int MAXN = 63000001;

int a, b;
bool A[MAXN];

int main(){
        for (int i = 1 ; i < MAXN ; i += 128){
                A[i] = i * i;
                A[i - 1]++;
                A[i] += A[i - 1];
        }

        cin >> a >> b;
        cout << a + b;

        return 0;
}', NULL);
INSERT INTO source VALUES (933819, '#include<bits/stdc++.h>

using namespace std;

const int MAXN = 63000001;

int a, b;
bool A[MAXN];

int main(){
        for (int i = 1 ; i < MAXN ; i += 128){
                A[i] = i * i;
                A[i - 1]++;
                A[i] += A[i - 1];
        }

        cin >> a >> b;
        cout << a + b;

        return 0;
}', NULL);
INSERT INTO source VALUES (933820, '#include<bits/stdc++.h>

using namespace std;

const int MAXN = 63000001;

int a, b;
bool A[MAXN];

int main(){
        for (int i = 1 ; i < MAXN ; i += 128){
                A[i] = i * i;
                A[i - 1]++;
                A[i] += A[i - 1];
        }

        cin >> a >> b;
        cout << a + b - a;

        return 0;
}', NULL);
INSERT INTO source VALUES (933821, '#include<bits/stdc++.h>

using namespace std;

const int MAXN = 63000001;

int a, b;
bool A[MAXN];

int main(){
        for (int i = 1 ; i < MAXN ; i += 128){
                A[i] = i * i;
                A[i - 1]++;
                A[i] += A[i - 1];
        }

        cin >> a >> b;
        cout << a + b;

        return 0;
}', NULL);
INSERT INTO source VALUES (933822, 'import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Main1 {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt ();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		DisjointSet set = new DisjointSet(n);;
		double minCosto = 0;
		int puntos[][]=new int[n][2];
		
		for (int i=0; i<n; i++) {
			puntos[i][0] = (int) (in.nextDouble ()*10);
			puntos[i][1] = (int) (in.nextDouble ()*10);
			
			for (int j=i-1; j>=0; j--) {
				if (puntos[i][0]!=puntos[j][0] || puntos[i][1]!=puntos[j][1])
					edges.add (new Edge (i,j, Math.hypot (puntos[i][0]-puntos[j][0],puntos[i][1]-puntos[j][1])));
				else
					set.union (i, j);
			}
		}
		
		Collections.sort (edges);
		for (Edge e : edges) {
			if (!set.isSameSet (e.i, e.j)) {
				minCosto += e.w;
				set.union (e.i, e.j);
			}
		}
		DecimalFormat d = new DecimalFormat("0.00");
      System.out.println (d.format (minCosto/10d));
	}
	
	public static class DisjointSet {
	    public int[] parent, sizes;
	    
	    public DisjointSet(int n)
	    {
	        parent = new int [n];
	        sizes = new int [n];
	        Arrays.fill (sizes, 1);
	        for(int i = 0; i < n; i++)
	            parent[i] = i;
	    }

	    public int find(int i)
	    {
	        if (parent[i] != i)
	            parent[i] = find(parent[i]);
	        return parent[i];
	    }
	    
	    public int getSize (int i) {
	   	 return sizes[find(i)];
	    }
	    
	    public boolean isSameSet (int x, int y) {
	   	 return find(x)==find(y);
	    }

	    public void union(int x, int y)
	    {
	        int x_root = find(x), y_root = find(y);
	        if (x_root != y_root) {
	            parent[y_root] = x_root;
	            sizes[x_root] += sizes[y_root];
	            sizes[y_root] = 0;
	        }
	    }
	}
	
	public  static class Edge implements Comparable<Edge> {
		public int i,j;
		public double w;

		public Edge (int i, int j, double w) {
			this.i = i;
			this.w = w;
			this.j = j;
		}
		
		public int compareTo (Edge o) {
	      if(w>o.w)
	      	return 1;
	      else if (w<o.w)
	      	return -1;
	      return 0;
      }
		
		public String toString () {
			return "["+i+","+j+"]="+w;
		}
	}
}', NULL);
INSERT INTO source VALUES (933823, '<?php
$a=1;  $b=2; echo $a+$b;
?>', NULL);
INSERT INTO source VALUES (933824, '#include<bits/stdc++.h>
using namespace std;
int a,b;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    //freopen("in.txt","r",stdin);
    cin>>a>>b;
    cout<<a+b<<"\n";
}', NULL);
INSERT INTO source VALUES (933825, '#include<bits/stdc++.h>
using namespace std;
int a,b;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    //freopen("in.txt","r",stdin);
    cin>>a>>b;
    cout<<a + a+b<<"\n";
}', NULL);
INSERT INTO source VALUES (933826, '#include<bits/stdc++.h>
using namespace std;
int a,b;

int main(){
    for (;;);
    ios::sync_with_stdio(0);
    cin.tie(0);
    //freopen("in.txt","r",stdin);
    cin>>a>>b;
    cout<<a + a+b<<"\n";
}', NULL);
INSERT INTO source VALUES (933827, '#include<bits/stdc++.h>
using namespace std;
int a,b;

int main(){
    int x = 10 / 0;
    for (;;);
    ios::sync_with_stdio(0);
    cin.tie(0);
    //freopen("in.txt","r",stdin);
    cin>>a>>b;
    cout<<a + a+b<<"\n";
}', NULL);
INSERT INTO source VALUES (933828, '#include<bits/stdc++.h>
using namespace std;
int a,b;

int A[100000000];

int main(){
    for (int i = 0 ; i < 100000000; i += 100) A[i] = i;
    ios::sync_with_stdio(0);
    cin.tie(0);
    //freopen("in.txt","r",stdin);
    cin>>a>>b;
    cout<<a + a+b<<"\n";
}', NULL);
INSERT INTO source VALUES (933829, '#include<bits/stdc++.h>
using namespace std;
int a,b;

//int A[100000000];

int main(){
//    for (int i = 0 ; i < 100000000; i += 100) A[i] = i;
    ios::sync_with_stdio(0);
    cin.tie(0);
    //freopen("in.txt","r",stdin);
    cin>>a>>b;
    cout<<a + a/b/0<<"\n";
}', NULL);
INSERT INTO source VALUES (933830, 'import java.util.*;
/**
 *
 * @author El Largo
 */
public class COJ {

    
    public static void main(String[] args) {
        Scanner q=new Scanner(System.in);
        
        int x=q.nextInt();
        int a=q.nextInt();
        System.out.println(x+a);
    }
    
}
', NULL);
INSERT INTO source VALUES (933831, 'import java.util.*;
/**
 *
 * @author El Largo
 
public class COJ {

    
    public static void main(String[] args) {
        Scanner q=new Scanner(System.in);
        
        int x=q.nextInt();
        int a=q.nextInt();
        System.out.println(x+a);
    }
    
}
', '/home/frank/UEngine/tmp/933831/933831.java:2: error: unclosed comment/**^/home/frank/UEngine/tmp/933831/933831.java:18: error: reached end of file while parsing2 errors');
INSERT INTO source VALUES (933832, 'package coj.ab;
import java.util.Scanner;
public class CojAb {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a,b,c;
        
        a=sc.nextInt();
        b=sc.nextInt();
        c=a+b;
        System.out.print(c);
    }
    
}', NULL);
INSERT INTO source VALUES (933833, 'def suma(A,B):
    c=A+B
    return c', NULL);
INSERT INTO source VALUES (933834, 'Z=raw_input()
print(int(Z[0])+int(Z[2]))', NULL);


--
-- Data for Name: special_awards; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO special_awards VALUES (2, 'Accurate Team', 'Team that solves the most problems without failed attempts');
INSERT INTO special_awards VALUES (3, 'Exclusive  Team', 'Team that solves a problem no one else does');
INSERT INTO special_awards VALUES (1, 'Fast Team', 'Team that gets "first accepted" in most problems');


--
-- Data for Name: submition; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO submition VALUES (34228, 1000, 'Accepted', 'C++', 229, 1552384, 194, '2016-11-08 18:26:37.49736', 933824, true, 'psetter1', -1, 74, 36, 57, false, 0, 0, true, 0, 0, 0, 4, true, true);
INSERT INTO submition VALUES (34228, 1000, 'Wrong Answer', 'C++', 129, 1552384, 198, '2016-11-08 18:27:15.591916', 933825, true, 'psetter1', 0, 52, 13, 32, false, 0, 0, true, 0, 0, 0, 0, false, false);
INSERT INTO submition VALUES (34228, 1000, 'Time Limit Exceeded', 'C++', 0, 0, 213, '2016-11-08 18:27:39.994569', 933826, true, 'psetter1', 0, 0, 0, 0, false, 0, 0, true, 0, 0, 0, 0, false, false);
INSERT INTO submition VALUES (34228, 1000, 'Time Limit Exceeded', 'C++', 0, 0, 234, '2016-11-08 18:27:56.72002', 933827, true, 'psetter1', 0, 0, 0, 0, false, 0, 0, true, 0, 0, 0, 0, false, false);
INSERT INTO submition VALUES (34228, 1000, 'Memory Limit Exceeded', 'C++', 0, 0, 276, '2016-11-08 18:28:47.742979', 933828, true, 'psetter1', 0, 0, 0, 0, false, 0, 0, true, 0, 0, 0, 0, false, false);
INSERT INTO submition VALUES (34228, 1000, 'Runtime Error', 'C++', 0, 0, 282, '2016-11-08 18:29:20.030984', 933829, true, 'psetter1', 0, 0, 0, 0, false, 0, 0, true, 0, 0, 0, 0, false, false);
INSERT INTO submition VALUES (34230, 1000, 'Accepted', 'Java', 8481, 1336836096, 289, '2016-11-08 18:30:29.497792', 933830, true, 'user1', -1, 2548, 1669, 2120, false, 0, 0, true, 0, 0, 0, 4, true, true);
INSERT INTO submition VALUES (34230, 1000, 'Compilation Error', 'Java', 0, 0, 287, '2016-11-08 18:30:48.17072', 933831, true, 'user1', -1, 0, 0, 0, false, 0, 0, true, 0, 0, 0, 0, false, false);
INSERT INTO submition VALUES (34230, 1000, 'Runtime Error', 'Java', 0, 0, 296, '2016-11-08 18:31:17.211806', 933832, true, 'user1', 0, 0, 0, 0, false, 0, 0, true, 0, 0, 0, 0, false, false);
INSERT INTO submition VALUES (34230, 1000, 'Wrong Answer', 'Python', 8865, 7729152, 39, '2016-11-08 18:32:19.182438', 933833, true, 'user1', 0, 2731, 2001, 2216, false, 0, 0, true, 0, 0, 0, 0, false, false);
INSERT INTO submition VALUES (34230, 1000, 'Accepted', 'Python', 8454, 7733248, 41, '2016-11-08 18:32:40.796748', 933834, true, 'user1', -1, 3629, 1266, 2113, false, 0, 0, true, 0, 0, 0, 4, true, false);


--
-- Name: submition_submit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('submition_submit_id_seq', 933834, true);


--
-- Name: tbl_user_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('tbl_user_user_id_seq', 34232, true);


--
-- Data for Name: team_profile; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: tmp; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: translation_pending; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: translation_pending_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('translation_pending_id_seq', 214, true);


--
-- Data for Name: user_achievement; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: user_clarification; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: user_clarification_ucid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('user_clarification_ucid_seq', 81496, true);


--
-- Data for Name: user_comments; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: user_comments_cid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('user_comments_cid_seq', 1, false);


--
-- Data for Name: user_contest; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: user_problem; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO user_problem VALUES (46, 1000, false, true);
INSERT INTO user_problem VALUES (4, 1000, false, true);
INSERT INTO user_problem VALUES (4, 1016, false, true);
INSERT INTO user_problem VALUES (34228, 1000, false, true);
INSERT INTO user_problem VALUES (34230, 1000, false, true);


--
-- Data for Name: user_problem_favorite; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: user_problem_tmp; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO user_problem_tmp VALUES (46, 1000, false, false);


--
-- Data for Name: user_profile; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO user_profile VALUES (34227, 'Admin', 'Admin', 'admin@coj.uci.cu', 0, 25121, 400000, 0, '1930-01-01 00:00:00', false, false, NULL);
INSERT INTO user_profile VALUES (34228, 'psetterUno', 'psetterUno', 'psetter1@coj.uci.cu', 4.65116279069767469, 25122, 400000, 0, '1930-01-01 00:00:00', false, false, NULL);
INSERT INTO user_profile VALUES (34229, 'psetterDos', 'psetterDos', 'psetter2@coj.cu', 0, 25123, 400000, 0, '1930-01-01 00:00:00', false, false, NULL);
INSERT INTO user_profile VALUES (34231, 'UserDOs', 'UserDos', 'user2@a.com', 0, 25125, 400000, 0, '1930-01-01 00:00:00', false, false, NULL);
INSERT INTO user_profile VALUES (34230, 'UserUno', 'UserUno', 'user1@brazil.com', 4.65116279069767469, 25124, 400000, 0, '1930-01-01 00:00:00', true, false, NULL);
INSERT INTO user_profile VALUES (34232, NULL, NULL, 'cojboard@uci.cu', 0, 25126, 400000, 0, '2016-11-07 14:44:00', false, false, NULL);


--
-- Name: user_profile_upid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('user_profile_upid_seq', 25126, true);


--
-- Data for Name: user_stats; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO user_stats VALUES (34227, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, '2016-11-07 12:36:50.662886', 93127721, 0, 0, 0);
INSERT INTO user_stats VALUES (34229, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, '2016-11-07 12:46:28.796305', 93127723, 0, 0, 0);
INSERT INTO user_stats VALUES (34231, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, '2016-11-07 12:50:10.723549', 93127725, 0, 0, 0);
INSERT INTO user_stats VALUES (34232, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, '2016-11-07 14:44:54.714946', 93127726, 0, 0, 0);
INSERT INTO user_stats VALUES (34228, 1, 1, 0, 1, 2, 1, 0, 0, 0, 0, '2016-11-08 18:29:20.030984', '2016-11-08 18:26:37.49736', 93127722, 0, 0, 1);
INSERT INTO user_stats VALUES (34230, 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, '2016-11-08 18:32:40.796748', '2016-11-08 18:32:40.796748', 93127724, 0, 0, 1);


--
-- Data for Name: user_stats_contest; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: user_stats_stats_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('user_stats_stats_id_seq', 93127726, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO users VALUES (34227, 'admin', '830fd6576ff2e40aa842f0f412873ef1', 'C', true, 52, 151, 'admin', 1, '2016-11-07 12:36:50.662886', '2016-11-07 12:36:50.662886', false, 2, 1, false, false, '127.0.0.1', '*', '2016-11-08 01:00:04.210965', false, '2016-11-07 12:36:50.662886', true, true, NULL, false, false, false, false, 'ROLE_USER', 'active', false, false, NULL, NULL);
INSERT INTO users VALUES (34228, 'psetter1', '90fbdff0ad3cbc12327ff34952d422d7', 'C', true, 52, 129, 'psetter1', 1, '2016-11-07 12:44:34.3449', '2016-11-07 12:44:34.3449', false, 2, 1, false, false, '127.0.0.1', '*', '2016-11-08 18:22:24.40391', false, '2016-11-07 12:44:34.3449', true, true, NULL, false, false, false, false, 'ROLE_USER', 'inactive', false, false, NULL, NULL);
INSERT INTO users VALUES (34229, 'psetter2', '90fbdff0ad3cbc12327ff34952d422d7', 'C', true, 52, 129, 'psetter2', 1, '2016-11-07 12:46:28.796305', '2016-11-07 12:46:28.796305', false, 2, 1, false, false, 'none', '*', NULL, false, '2016-11-07 12:46:28.796305', true, true, NULL, false, false, false, false, 'ROLE_USER', 'inactive', false, false, NULL, NULL);
INSERT INTO users VALUES (34231, 'user2', '90fbdff0ad3cbc12327ff34952d422d7', 'C', true, 13, 4244, 'user2', 1, '2016-11-07 12:50:10.723549', '2016-11-07 12:50:10.723549', false, 2, 1, false, false, 'none', '*', NULL, false, '2016-11-07 12:50:10.723549', true, true, NULL, false, false, true, true, 'ROLE_USER', 'inactive', true, false, NULL, NULL);
INSERT INTO users VALUES (34230, 'user1', '90fbdff0ad3cbc12327ff34952d422d7', 'C', true, 30, 4626, 'user1', 1, '2016-11-07 12:48:59.567424', '2016-11-07 12:48:59.567424', false, 2, 1, true, true, '127.0.0.1', '*', '2016-11-08 18:29:47.60853', false, '2016-11-07 12:48:59.567424', true, true, NULL, true, false, false, false, 'ROLE_USER', 'inactive', false, false, NULL, NULL);
INSERT INTO users VALUES (34232, 'COJboard', NULL, 'C', false, 0, 0, 'COJboard', 1, '2016-11-07 14:44:54.714946', '2016-11-07 14:44:54.714946', false, 1, 0, false, false, 'none', '*', NULL, false, '2016-11-07 14:44:54.714946', true, true, NULL, false, false, false, false, 'ROLE_USER', 'not active', false, false, NULL, NULL);


--
-- Data for Name: virtual_contest_guest; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: virtual_contest_problem; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: votes; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO votes VALUES (72, 1000, 3);
INSERT INTO votes VALUES (4, 1000, 4);
INSERT INTO votes VALUES (72, 1018, 4);
INSERT INTO votes VALUES (72, 1002, 2);
INSERT INTO votes VALUES (905, 1000, 4);
INSERT INTO votes VALUES (550, 1000, 12);
INSERT INTO votes VALUES (102, 1000, 19);
INSERT INTO votes VALUES (413, 1000, 19);


--
-- Data for Name: votes_stats; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO votes_stats VALUES (1007, 1, 1, 0, 1, 1, 0.5, 0.5, 0, 1, 3);
INSERT INTO votes_stats VALUES (1018, 1, 0, 1, 0, 1, 3.5, 0, 4.70000000000000018, 0, 2.29999999999999982);
INSERT INTO votes_stats VALUES (1017, 1, 1, 1, 1, 1, 5, 2.70000000000000018, 2.20000000000000018, 1.30000000000000004, 2.5);
INSERT INTO votes_stats VALUES (1002, 1, 1, 1, 1, 1, 2.60000000000000009, 1.69999999999999996, 2.89999999999999991, 3.5, 1.30000000000000004);
INSERT INTO votes_stats VALUES (1008, 1, 1, 0, 1, 1, 3.79999999999999982, 4.79999999999999982, 0, 0.800000000000000044, 3.5);
INSERT INTO votes_stats VALUES (1004, 1, 1, 1, 1, 1, 4.79999999999999982, 4.70000000000000018, 3.70000000000000018, 1.80000000000000004, 0.5);
INSERT INTO votes_stats VALUES (1003, 1, 1, 1, 1, 1, 11.6999999999999993, 3.79999999999999982, 4, 1.69999999999999996, 1.89999999999999991);
INSERT INTO votes_stats VALUES (1000, 18, 19, 16, 16, 17, 179.099999999999994, 177.400000000000006, 160.099999999999994, 153.199999999999989, 104);


--
-- Data for Name: wboard_contest; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: wboard_contest_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('wboard_contest_id_seq', 1030, true);


--
-- Data for Name: wboard_site; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO wboard_site VALUES (20, 'Red de Programación Competitiva', 'http://www.redprogramacioncompetitiva.com/', 'RPC', false, true, 99, 'America/Havana');
INSERT INTO wboard_site VALUES (19, 'Kattis Online Judge', 'https://open.kattis.com/', 'KATTIS', false, true, 239, 'Europe/Stockholm');
INSERT INTO wboard_site VALUES (18, 'HackerRank', 'https://www.hackerrank.com', 'HackerRank', false, true, 0, 'UTC');
INSERT INTO wboard_site VALUES (16, 'Codeforces', 'http://codeforces.com', 'Codeforces', false, true, 166, 'Europe/Moscow');
INSERT INTO wboard_site VALUES (13, 'UVa Online Judge', 'http://uva.onlinejudge.org', 'UVa', false, true, 141, 'Europe/Madrid');
INSERT INTO wboard_site VALUES (15, 'Codechef', 'http://www.codechef.com', 'Codechef', false, true, 44, 'Asia/Calcutta');
INSERT INTO wboard_site VALUES (17, 'Caribbean Online Judge', 'http://coj.uci.cu', 'COJ', false, true, 99, 'America/Havana');
INSERT INTO wboard_site VALUES (14, 'Timus Online Judge', 'http://acm.timus.ru', 'Timus', false, true, 358, 'Asia/Yekaterinburg');


--
-- Name: wboard_site_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('wboard_site_id_seq', 21, true);


--
-- Data for Name: wboard_user_site; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO wboard_user_site VALUES (1335, 16);
INSERT INTO wboard_user_site VALUES (1000, 15);
INSERT INTO wboard_user_site VALUES (1530, 13);
INSERT INTO wboard_user_site VALUES (2291, 17);
INSERT INTO wboard_user_site VALUES (731, 17);
INSERT INTO wboard_user_site VALUES (905, 15);
INSERT INTO wboard_user_site VALUES (64, 14);
INSERT INTO wboard_user_site VALUES (760, 15);
INSERT INTO wboard_user_site VALUES (1278, 18);
INSERT INTO wboard_user_site VALUES (2928, 16);
INSERT INTO wboard_user_site VALUES (870, 16);
INSERT INTO wboard_user_site VALUES (100, 18);
INSERT INTO wboard_user_site VALUES (100, 17);
INSERT INTO wboard_user_site VALUES (1146, 18);
INSERT INTO wboard_user_site VALUES (1146, 16);
INSERT INTO wboard_user_site VALUES (1146, 17);
INSERT INTO wboard_user_site VALUES (1469, 17);
INSERT INTO wboard_user_site VALUES (736, 17);
INSERT INTO wboard_user_site VALUES (16, 14);
INSERT INTO wboard_user_site VALUES (411, 17);
INSERT INTO wboard_user_site VALUES (1370, 15);
INSERT INTO wboard_user_site VALUES (1370, 16);
INSERT INTO wboard_user_site VALUES (1370, 17);
INSERT INTO wboard_user_site VALUES (2133, 19);
INSERT INTO wboard_user_site VALUES (1531, 17);
INSERT INTO wboard_user_site VALUES (2541, 18);
INSERT INTO wboard_user_site VALUES (421, 19);
INSERT INTO wboard_user_site VALUES (421, 20);
INSERT INTO wboard_user_site VALUES (421, 18);
INSERT INTO wboard_user_site VALUES (421, 13);
INSERT INTO wboard_user_site VALUES (421, 14);
INSERT INTO wboard_user_site VALUES (421, 15);
INSERT INTO wboard_user_site VALUES (421, 16);
INSERT INTO wboard_user_site VALUES (421, 17);
INSERT INTO wboard_user_site VALUES (4, 19);
INSERT INTO wboard_user_site VALUES (4, 20);
INSERT INTO wboard_user_site VALUES (4, 18);
INSERT INTO wboard_user_site VALUES (4, 13);
INSERT INTO wboard_user_site VALUES (4, 14);
INSERT INTO wboard_user_site VALUES (4, 15);
INSERT INTO wboard_user_site VALUES (4, 16);
INSERT INTO wboard_user_site VALUES (4, 17);
INSERT INTO wboard_user_site VALUES (2903, 16);
INSERT INTO wboard_user_site VALUES (2903, 17);
INSERT INTO wboard_user_site VALUES (2903, 15);
INSERT INTO wboard_user_site VALUES (2029, 17);
INSERT INTO wboard_user_site VALUES (469, 16);
INSERT INTO wboard_user_site VALUES (300, 17);
INSERT INTO wboard_user_site VALUES (413, 18);
INSERT INTO wboard_user_site VALUES (46, 16);
INSERT INTO wboard_user_site VALUES (46, 15);
INSERT INTO wboard_user_site VALUES (46, 17);
INSERT INTO wboard_user_site VALUES (2416, 16);


--
-- Name: account_activation_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY account_activation
    ADD CONSTRAINT account_activation_pkey PRIMARY KEY (act_id);


--
-- Name: achievements_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY achievements
    ADD CONSTRAINT achievements_pkey PRIMARY KEY (aid);


--
-- Name: admin_clarification_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY admin_clarification
    ADD CONSTRAINT admin_clarification_pkey PRIMARY KEY (id_clarification);


--
-- Name: admin_log_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY admin_log
    ADD CONSTRAINT admin_log_pkey PRIMARY KEY (id);


--
-- Name: announcements_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY announcements
    ADD CONSTRAINT announcements_pkey PRIMARY KEY (aid);


--
-- Name: authorities_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY authorities
    ADD CONSTRAINT authorities_pkey PRIMARY KEY (id);


--
-- Name: clarification_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY clarification
    ADD CONSTRAINT clarification_pkey PRIMARY KEY (id_clarification);


--
-- Name: classification_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY classifications
    ADD CONSTRAINT classification_pkey PRIMARY KEY (id_classification);


--
-- Name: classification_unique; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY classifications
    ADD CONSTRAINT classification_unique UNIQUE (name);


--
-- Name: cojmail_log_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY cojmail_log
    ADD CONSTRAINT cojmail_log_pkey PRIMARY KEY (cm_id);


--
-- Name: contest_brief_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_brief
    ADD CONSTRAINT contest_brief_pkey PRIMARY KEY (id);


--
-- Name: contest_flags_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_flags
    ADD CONSTRAINT contest_flags_pkey PRIMARY KEY (cfid);


--
-- Name: contest_judges_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_judges
    ADD CONSTRAINT contest_judges_pkey PRIMARY KEY (cid, id_admin);


--
-- Name: contest_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest
    ADD CONSTRAINT contest_pkey PRIMARY KEY (cid);


--
-- Name: contest_registration_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_registration
    ADD CONSTRAINT contest_registration_pkey PRIMARY KEY (rid);


--
-- Name: contest_style_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_style
    ADD CONSTRAINT contest_style_pkey PRIMARY KEY (sid);


--
-- Name: contest_submition_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_submition
    ADD CONSTRAINT contest_submition_pkey PRIMARY KEY (submit_id);


--
-- Name: country_name_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_name_key UNIQUE (name);


--
-- Name: country_name_key1; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_name_key1 UNIQUE (name);


--
-- Name: country_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_pkey PRIMARY KEY (country_id);


--
-- Name: country_zip_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_zip_key UNIQUE (zip);


--
-- Name: country_zip_key1; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_zip_key1 UNIQUE (zip);


--
-- Name: dataset_verdict_json_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY dataset_verdict_json
    ADD CONSTRAINT dataset_verdict_json_pkey PRIMARY KEY (sid);


--
-- Name: datasets_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY datasets
    ADD CONSTRAINT datasets_pkey PRIMARY KEY (id);


--
-- Name: draft_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY draft
    ADD CONSTRAINT draft_pkey PRIMARY KEY (draft_id);


--
-- Name: entries_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY entries
    ADD CONSTRAINT entries_pkey PRIMARY KEY (eid);


--
-- Name: entry_rate_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY entry_rate
    ADD CONSTRAINT entry_rate_pkey PRIMARY KEY (uid, eid);


--
-- Name: events_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);


--
-- Name: exercise_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem
    ADD CONSTRAINT exercise_pkey PRIMARY KEY (pid);


--
-- Name: followers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY followers
    ADD CONSTRAINT followers_pkey PRIMARY KEY (uid, fid);


--
-- Name: global_access_rules_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY global_access_rules
    ADD CONSTRAINT global_access_rules_pkey PRIMARY KEY (rid);


--
-- Name: global_flags_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY global_flags
    ADD CONSTRAINT global_flags_pkey PRIMARY KEY (global_id);


--
-- Name: group_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "group"
    ADD CONSTRAINT group_pkey PRIMARY KEY (id);


--
-- Name: individual_virtual_contest_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY individual_virtual_contest
    ADD CONSTRAINT individual_virtual_contest_pkey PRIMARY KEY (vid);


--
-- Name: institution_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY institution
    ADD CONSTRAINT institution_pkey PRIMARY KEY (inst_id);


--
-- Name: institution_zip_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY institution
    ADD CONSTRAINT institution_zip_key UNIQUE (zip);


--
-- Name: internal_mail_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY internal_mail
    ADD CONSTRAINT internal_mail_pkey PRIMARY KEY (idmail);


--
-- Name: language_contest_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language_contest
    ADD CONSTRAINT language_contest_pkey PRIMARY KEY (lid, cid);


--
-- Name: language_stats_contest_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language_stats_contest
    ADD CONSTRAINT language_stats_contest_pkey PRIMARY KEY (lid, cid);


--
-- Name: language_stats_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language_stats
    ADD CONSTRAINT language_stats_pkey PRIMARY KEY (lid);


--
-- Name: language_virtual_stats_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language_virtual_stats
    ADD CONSTRAINT language_virtual_stats_pkey PRIMARY KEY (lid);


--
-- Name: languages_language_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language
    ADD CONSTRAINT languages_language_key UNIQUE (language);


--
-- Name: languages_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language
    ADD CONSTRAINT languages_pkey PRIMARY KEY (lid);


--
-- Name: locale_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY locale
    ADD CONSTRAINT locale_pkey PRIMARY KEY (lid);


--
-- Name: news_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY news
    ADD CONSTRAINT news_pkey PRIMARY KEY (nid);


--
-- Name: pk_balloontrackers; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY balloontrackers
    ADD CONSTRAINT pk_balloontrackers PRIMARY KEY (uid, cid);


--
-- Name: pk_contest_awards; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY special_awards
    ADD CONSTRAINT pk_contest_awards PRIMARY KEY (aid);


--
-- Name: pk_contest_awards_users; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_awards
    ADD CONSTRAINT pk_contest_awards_users PRIMARY KEY (aid, cid);


--
-- Name: pk_contest_source; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_source
    ADD CONSTRAINT pk_contest_source PRIMARY KEY (sid);


--
-- Name: pk_faq; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY faq
    ADD CONSTRAINT pk_faq PRIMARY KEY (id);


--
-- Name: pk_poll; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY poll
    ADD CONSTRAINT pk_poll PRIMARY KEY (pid);


--
-- Name: pk_pollans; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY poll_answer
    ADD CONSTRAINT pk_pollans PRIMARY KEY (aid);


--
-- Name: pk_problem_source; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_source
    ADD CONSTRAINT pk_problem_source PRIMARY KEY (id_source);


--
-- Name: pk_shared_files; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY shared_files
    ADD CONSTRAINT pk_shared_files PRIMARY KEY (fid);


--
-- Name: pk_source; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY source
    ADD CONSTRAINT pk_source PRIMARY KEY (sid);


--
-- Name: plagicoj_result_judge_revision_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY plagicoj_result_judge_revision
    ADD CONSTRAINT plagicoj_result_judge_revision_pkey PRIMARY KEY (id_plagicoj_result_judge_revision);


--
-- Name: plagicoj_result_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY plagicoj_result
    ADD CONSTRAINT plagicoj_result_pkey PRIMARY KEY (id_source_submission, id_destination_submission);


--
-- Name: problem_classification_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_classification
    ADD CONSTRAINT problem_classification_pkey PRIMARY KEY (id_classification, pid);


--
-- Name: problem_contest_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_contest
    ADD CONSTRAINT problem_contest_pkey PRIMARY KEY (pid, cid);


--
-- Name: problem_language_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_language
    ADD CONSTRAINT problem_language_pkey PRIMARY KEY (pid, lid);


--
-- Name: problem_limits_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_limits
    ADD CONSTRAINT problem_limits_pk PRIMARY KEY (problem_id, language_id);


--
-- Name: problem_stats_contest_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_stats_contest
    ADD CONSTRAINT problem_stats_contest_pkey PRIMARY KEY (pid, cid);


--
-- Name: problem_stats_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_stats
    ADD CONSTRAINT problem_stats_pkey PRIMARY KEY (stats_id);


--
-- Name: psetter_problem_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY psetter_problem
    ADD CONSTRAINT psetter_problem_pkey PRIMARY KEY (user_id, problem_id);


--
-- Name: roles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (rid);


--
-- Name: send_mail_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY send_mail
    ADD CONSTRAINT send_mail_pkey PRIMARY KEY (idmail);


--
-- Name: submit_id_testnum; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_dataset_verdict
    ADD CONSTRAINT submit_id_testnum PRIMARY KEY (submit_id, testnum);


--
-- Name: submition_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY submition
    ADD CONSTRAINT submition_pkey PRIMARY KEY (submit_id);


--
-- Name: team_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY team_profile
    ADD CONSTRAINT team_profile_pkey PRIMARY KEY (uid);


--
-- Name: transtation_pending_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY translation_pending
    ADD CONSTRAINT transtation_pending_pkey PRIMARY KEY (id);


--
-- Name: un_pid_uid; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY poll_answer
    ADD CONSTRAINT un_pid_uid UNIQUE (pid, uid);


--
-- Name: user_achievement_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_achievement
    ADD CONSTRAINT user_achievement_pkey PRIMARY KEY (aid, uid);


--
-- Name: user_clarification_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_clarification
    ADD CONSTRAINT user_clarification_pkey PRIMARY KEY (ucid);


--
-- Name: user_comments_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_comments
    ADD CONSTRAINT user_comments_pkey PRIMARY KEY (cid);


--
-- Name: user_comments_uid_eid_date_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_comments
    ADD CONSTRAINT user_comments_uid_eid_date_key UNIQUE (uid, eid, date);


--
-- Name: user_contest_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_contest
    ADD CONSTRAINT user_contest_pkey PRIMARY KEY (uid, cid, virtual);


--
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY users
    ADD CONSTRAINT user_pkey PRIMARY KEY (uid);


--
-- Name: user_problem_favorite_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_problem_favorite
    ADD CONSTRAINT user_problem_favorite_pkey PRIMARY KEY (user_id, problem_id);


--
-- Name: user_problem_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_problem
    ADD CONSTRAINT user_problem_pkey PRIMARY KEY (user_id, problem_id);


--
-- Name: user_problem_pkey_tmp; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_problem_tmp
    ADD CONSTRAINT user_problem_pkey_tmp PRIMARY KEY (user_id, problem_id);


--
-- Name: user_profile_email_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_email_key UNIQUE (email);


--
-- Name: user_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_pkey PRIMARY KEY (upid);


--
-- Name: user_stats_contest_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_stats_contest
    ADD CONSTRAINT user_stats_contest_pkey PRIMARY KEY (uid, cid);


--
-- Name: user_stats_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_stats
    ADD CONSTRAINT user_stats_pkey PRIMARY KEY (stats_id);


--
-- Name: user_user_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY users
    ADD CONSTRAINT user_user_key UNIQUE (username);


--
-- Name: virtual_contest_problem_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY virtual_contest_problem
    ADD CONSTRAINT virtual_contest_problem_pkey PRIMARY KEY (vid, pid);


--
-- Name: dataset_verdict_json_sid_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE UNIQUE INDEX dataset_verdict_json_sid_idx ON dataset_verdict_json USING btree (sid DESC NULLS LAST);


--
-- Name: fki_; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_ ON language_contest USING btree (lid);


--
-- Name: fki_classifications_achievements; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_classifications_achievements ON classifications USING btree (aid);


--
-- Name: fki_contest_contest; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_contest_contest ON contest USING btree (template);


--
-- Name: fki_contest_users; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_contest_users ON contest USING btree (uid);


--
-- Name: fki_entries_uid; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_entries_uid ON entries USING btree (uid);


--
-- Name: fki_language_achievement; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_language_achievement ON language USING btree (aid);


--
-- Name: fki_pollans_poll; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_pollans_poll ON poll_answer USING btree (pid);


--
-- Name: fki_pollans_user; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_pollans_user ON poll_answer USING btree (uid);


--
-- Name: fki_problem_problem_source; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_problem_problem_source ON problem USING btree (id_source);


--
-- Name: ix_cid; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX ix_cid ON user_contest USING btree (cid);


--
-- Name: problemclassinstrig; Type: TRIGGER; Schema: public; Owner: -
--

CREATE TRIGGER problemclassinstrig AFTER INSERT ON problem_classification FOR EACH ROW EXECUTE PROCEDURE problemclassinsfn();


--
-- Name: problemclasstrig; Type: TRIGGER; Schema: public; Owner: -
--

CREATE TRIGGER problemclasstrig AFTER DELETE ON problem_classification FOR EACH ROW EXECUTE PROCEDURE problemclassfn();


--
-- Name: admin_clarification_id_clarification_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY admin_clarification
    ADD CONSTRAINT admin_clarification_id_clarification_fkey FOREIGN KEY (id_clarification) REFERENCES clarification(id_clarification);


--
-- Name: clarification_id_team_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY clarification
    ADD CONSTRAINT clarification_id_team_fkey FOREIGN KEY (id_team) REFERENCES users(uid);


--
-- Name: contest_brief_cid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_brief
    ADD CONSTRAINT contest_brief_cid_fkey FOREIGN KEY (cid) REFERENCES contest(cid);


--
-- Name: contest_flags_cid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_flags
    ADD CONSTRAINT contest_flags_cid_fkey FOREIGN KEY (cid) REFERENCES contest(cid);


--
-- Name: contest_user_clarification_cid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_judges
    ADD CONSTRAINT contest_user_clarification_cid_fkey FOREIGN KEY (cid) REFERENCES contest(cid);


--
-- Name: contest_user_clarification_id_admin_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_judges
    ADD CONSTRAINT contest_user_clarification_id_admin_fkey FOREIGN KEY (id_admin) REFERENCES users(uid);


--
-- Name: entry_rate_eid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY entry_rate
    ADD CONSTRAINT entry_rate_eid_fkey FOREIGN KEY (eid) REFERENCES entries(eid);


--
-- Name: entry_rate_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY entry_rate
    ADD CONSTRAINT entry_rate_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: fk_balloontrackers_contest; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY balloontrackers
    ADD CONSTRAINT fk_balloontrackers_contest FOREIGN KEY (cid) REFERENCES contest(cid);


--
-- Name: fk_balloontrackers_users; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY balloontrackers
    ADD CONSTRAINT fk_balloontrackers_users FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: fk_classifications_achievements; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY classifications
    ADD CONSTRAINT fk_classifications_achievements FOREIGN KEY (aid) REFERENCES achievements(aid);


--
-- Name: fk_contest_awards_special_awards; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_awards
    ADD CONSTRAINT fk_contest_awards_special_awards FOREIGN KEY (aid) REFERENCES special_awards(aid);


--
-- Name: fk_contest_awards_users; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest_awards
    ADD CONSTRAINT fk_contest_awards_users FOREIGN KEY (cid) REFERENCES contest(cid);


--
-- Name: fk_contest_contest; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest
    ADD CONSTRAINT fk_contest_contest FOREIGN KEY (template) REFERENCES contest(cid);


--
-- Name: fk_contest_users; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY contest
    ADD CONSTRAINT fk_contest_users FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: fk_entries_uid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY entries
    ADD CONSTRAINT fk_entries_uid FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: fk_language_achievement; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language
    ADD CONSTRAINT fk_language_achievement FOREIGN KEY (aid) REFERENCES achievements(aid);


--
-- Name: fk_pollans_poll; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY poll_answer
    ADD CONSTRAINT fk_pollans_poll FOREIGN KEY (pid) REFERENCES poll(pid);


--
-- Name: fk_pollans_user; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY poll_answer
    ADD CONSTRAINT fk_pollans_user FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: fk_problem_problem_source; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem
    ADD CONSTRAINT fk_problem_problem_source FOREIGN KEY (id_source) REFERENCES problem_source(id_source);


--
-- Name: followers_fid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY followers
    ADD CONSTRAINT followers_fid_fkey FOREIGN KEY (fid) REFERENCES users(uid);


--
-- Name: followers_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY followers
    ADD CONSTRAINT followers_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: individual_virtual_contest_cid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY individual_virtual_contest
    ADD CONSTRAINT individual_virtual_contest_cid_fkey FOREIGN KEY (cid) REFERENCES contest(cid);


--
-- Name: individual_virtual_contest_username_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY individual_virtual_contest
    ADD CONSTRAINT individual_virtual_contest_username_fkey FOREIGN KEY (username) REFERENCES users(username);


--
-- Name: internal_mail_idfrom_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY internal_mail
    ADD CONSTRAINT internal_mail_idfrom_fkey FOREIGN KEY (idfrom) REFERENCES users(username);


--
-- Name: language_contest_cid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language_contest
    ADD CONSTRAINT language_contest_cid_fkey FOREIGN KEY (cid) REFERENCES contest(cid);


--
-- Name: language_contest_lid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language_contest
    ADD CONSTRAINT language_contest_lid_fkey FOREIGN KEY (lid) REFERENCES language(lid);


--
-- Name: language_stats_contest_cid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language_stats_contest
    ADD CONSTRAINT language_stats_contest_cid_fkey FOREIGN KEY (cid) REFERENCES contest(cid);


--
-- Name: language_stats_contest_lid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language_stats_contest
    ADD CONSTRAINT language_stats_contest_lid_fkey FOREIGN KEY (lid) REFERENCES language(lid);


--
-- Name: language_stats_lid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language_stats
    ADD CONSTRAINT language_stats_lid_fkey FOREIGN KEY (lid) REFERENCES language(lid);


--
-- Name: language_virtual_stats_lid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language_virtual_stats
    ADD CONSTRAINT language_virtual_stats_lid_fkey FOREIGN KEY (lid) REFERENCES language(lid);


--
-- Name: news_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY news
    ADD CONSTRAINT news_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: plagicoj_result_judge_revision; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY plagicoj_result_judge_revision
    ADD CONSTRAINT plagicoj_result_judge_revision FOREIGN KEY (id_source_submission, id_destination_submission) REFERENCES plagicoj_result(id_source_submission, id_destination_submission) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: plagicoj_result_judge_revision_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY plagicoj_result_judge_revision
    ADD CONSTRAINT plagicoj_result_judge_revision_id_user_fkey FOREIGN KEY (id_user) REFERENCES users(username) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: preference_profile_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY preference_profile
    ADD CONSTRAINT preference_profile_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: problem_classification_id_classification_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_classification
    ADD CONSTRAINT problem_classification_id_classification_fkey FOREIGN KEY (id_classification) REFERENCES classifications(id_classification) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: problem_classification_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_classification
    ADD CONSTRAINT problem_classification_pid_fkey FOREIGN KEY (pid) REFERENCES problem(pid);


--
-- Name: problem_language_lid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_language
    ADD CONSTRAINT problem_language_lid_fkey FOREIGN KEY (lid) REFERENCES language(lid);


--
-- Name: problem_language_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_language
    ADD CONSTRAINT problem_language_pid_fkey FOREIGN KEY (pid) REFERENCES problem(pid);


--
-- Name: problem_stats_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY problem_stats
    ADD CONSTRAINT problem_stats_pid_fkey FOREIGN KEY (pid) REFERENCES problem(pid);


--
-- Name: recommendation_profile_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY recommendation_profile
    ADD CONSTRAINT recommendation_profile_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: team_profile_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY team_profile
    ADD CONSTRAINT team_profile_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: user_clarification_id_clarification_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_clarification
    ADD CONSTRAINT user_clarification_id_clarification_fkey FOREIGN KEY (id_clarification) REFERENCES clarification(id_clarification);


--
-- Name: user_clarification_teamfor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_clarification
    ADD CONSTRAINT user_clarification_teamfor_fkey FOREIGN KEY (teamfor) REFERENCES users(uid);


--
-- Name: user_comments_eid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_comments
    ADD CONSTRAINT user_comments_eid_fkey FOREIGN KEY (eid) REFERENCES entries(eid);


--
-- Name: user_comments_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_comments
    ADD CONSTRAINT user_comments_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: user_profile_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: user_stats_contest_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_stats_contest
    ADD CONSTRAINT user_stats_contest_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: user_stats_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_stats
    ADD CONSTRAINT user_stats_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);


--
-- Name: users_lid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_lid_fkey FOREIGN KEY (lid) REFERENCES language(lid);


--
-- Name: users_lid_fkey1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_lid_fkey1 FOREIGN KEY (lid) REFERENCES language(lid);


--
-- Name: users_locale_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_locale_fkey FOREIGN KEY (locale) REFERENCES locale(lid);


--
-- PostgreSQL database dump complete
--

