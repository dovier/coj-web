SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';

SET search_path = public, pg_catalog;

CREATE DOMAIN varchar_ci AS character varying(255) NOT NULL DEFAULT ''::character varying;

CREATE FUNCTION _varchar_ci_equal(varchar_ci, varchar_ci) RETURNS boolean
    LANGUAGE sql STRICT
    AS $_$SELECT LOWER($1) = LOWER($2)$_$;

CREATE FUNCTION _varchar_ci_greater_equals(varchar_ci, varchar_ci) RETURNS boolean
    LANGUAGE sql STRICT
    AS $_$SELECT LOWER($1) >= LOWER($2)$_$;

CREATE FUNCTION _varchar_ci_greater_than(varchar_ci, varchar_ci) RETURNS boolean
    LANGUAGE sql STRICT
    AS $_$SELECT LOWER($1) > LOWER($2)$_$;

CREATE FUNCTION _varchar_ci_less_equal(varchar_ci, varchar_ci) RETURNS boolean
    LANGUAGE sql STRICT
    AS $_$SELECT LOWER($1) <= LOWER($2)$_$;

CREATE FUNCTION _varchar_ci_less_than(varchar_ci, varchar_ci) RETURNS boolean
    LANGUAGE sql STRICT
    AS $_$SELECT LOWER($1) < LOWER($2)$_$;

CREATE FUNCTION _varchar_ci_not_equal(varchar_ci, varchar_ci) RETURNS boolean
    LANGUAGE sql STRICT
    AS $_$SELECT LOWER($1) != LOWER($2)$_$;

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

CREATE FUNCTION problemclasstrigfn() RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
DELETE FROM plagicoj_result WHERE id_source_submission IN (SELECT submit_id FROM submition WHERE pid = OLD.pid);
DELETE FROM plagicoj_result WHERE id_destination_submission IN (SELECT submit_id FROM submition WHERE pid = OLD.pid);
END;
$$;

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

CREATE OPERATOR < (
    PROCEDURE = _varchar_ci_less_than,
    LEFTARG = varchar_ci,
    RIGHTARG = varchar_ci,
    COMMUTATOR = >,
    NEGATOR = >=,
    RESTRICT = scalarltsel,
    JOIN = scalarltjoinsel
);

CREATE OPERATOR <= (
    PROCEDURE = _varchar_ci_less_equal,
    LEFTARG = varchar_ci,
    RIGHTARG = varchar_ci,
    COMMUTATOR = >=,
    NEGATOR = >,
    RESTRICT = scalarltsel,
    JOIN = scalarltjoinsel
);

CREATE OPERATOR <> (
    PROCEDURE = _varchar_ci_not_equal,
    LEFTARG = varchar_ci,
    RIGHTARG = varchar_ci,
    COMMUTATOR = <>,
    NEGATOR = =,
    RESTRICT = neqsel,
    JOIN = neqjoinsel
);

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

CREATE OPERATOR > (
    PROCEDURE = _varchar_ci_greater_than,
    LEFTARG = varchar_ci,
    RIGHTARG = varchar_ci,
    COMMUTATOR = <,
    NEGATOR = <=,
    RESTRICT = scalargtsel,
    JOIN = scalargtjoinsel
);

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

CREATE TABLE account_activation (
    act_id integer NOT NULL,
    username character varying NOT NULL,
    user_key character varying NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    registration boolean DEFAULT true NOT NULL
);

CREATE SEQUENCE account_activation_act_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE account_activation_act_id_seq OWNED BY account_activation.act_id;

CREATE TABLE achievements (
    aid integer NOT NULL,
    name character varying(200),
    description character varying(300),
    condition character varying(300),
    legend character varying DEFAULT ''::character varying NOT NULL,
    enabled boolean DEFAULT true NOT NULL
);

CREATE TABLE admin_clarification (
    id_clarification integer NOT NULL,
    reply boolean DEFAULT false NOT NULL
);

CREATE TABLE admin_log (
    id integer NOT NULL,
    log text,
    log_date timestamp without time zone DEFAULT now() NOT NULL,
    username character varying NOT NULL
);

CREATE SEQUENCE admin_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE admin_log_id_seq OWNED BY admin_log.id;

CREATE TABLE announcements (
    aid integer NOT NULL,
    content character varying NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    username character varying NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    contest_id integer DEFAULT 0 NOT NULL
);

CREATE SEQUENCE announcements_aid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE announcements_aid_seq OWNED BY announcements.aid;

CREATE TABLE authorities (
    username character varying NOT NULL,
    authority character varying NOT NULL,
    id integer NOT NULL
);

CREATE SEQUENCE authorities_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE authorities_id_seq OWNED BY authorities.id;

CREATE TABLE balloontrackers (
    uid integer NOT NULL,
    cid integer NOT NULL
);

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

CREATE SEQUENCE clarification_id_clarification_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE clarification_id_clarification_seq OWNED BY clarification.id_clarification;

CREATE TABLE classifications (
    id_classification integer NOT NULL,
    name text NOT NULL,
    estimated_code_length integer DEFAULT 0,
    aid integer DEFAULT 0 NOT NULL
);

CREATE SEQUENCE classifications_id_classification_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE classifications_id_classification_seq OWNED BY classifications.id_classification;

CREATE TABLE cojmail_log (
    cm_id integer NOT NULL,
    usuario character varying NOT NULL,
    request character varying(10000) NOT NULL,
    date timestamp without time zone NOT NULL
);

CREATE SEQUENCE cojmail_log_cm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE cojmail_log_cm_id_seq OWNED BY cojmail_log.cm_id;

CREATE SEQUENCE contest_cid_sequence
    START WITH 1430
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

CREATE TABLE contest_awards (
    aid integer NOT NULL,
    cid integer NOT NULL
);

CREATE TABLE special_awards (
    aid integer NOT NULL,
    name character varying(100),
    description character varying(300)
);

CREATE SEQUENCE contest_awards_aid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE contest_awards_aid_seq OWNED BY special_awards.aid;

CREATE SEQUENCE contest_awards_users_aid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE contest_awards_users_aid_seq OWNED BY contest_awards.aid;

CREATE TABLE contest_brief (
    cid integer NOT NULL,
    overview character varying,
    id integer NOT NULL
);

CREATE SEQUENCE contest_brief_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE contest_brief_id_seq OWNED BY contest_brief.id;

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

CREATE SEQUENCE contest_flags_cfid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE contest_flags_cfid_seq OWNED BY contest_flags.cfid;

CREATE TABLE contest_judges (
    cid integer NOT NULL,
    id_admin integer NOT NULL
);

CREATE TABLE contest_registration (
    rid integer NOT NULL,
    registration character varying NOT NULL
);

CREATE SEQUENCE contest_registration_rid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE contest_registration_rid_seq OWNED BY contest_registration.rid;

CREATE TABLE contest_source (
    sid integer NOT NULL,
    code text,
    error text
);

CREATE TABLE contest_style (
    sid integer NOT NULL,
    style_name character varying NOT NULL,
    enabled boolean DEFAULT false NOT NULL
);

CREATE SEQUENCE contest_style_sid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE contest_style_sid_seq OWNED BY contest_style.sid;

CREATE TABLE contest_submition (
    submit_id integer NOT NULL,
    uid integer NOT NULL,
    pid integer NOT NULL,
    cid integer NOT NULL,
    status character varying NOT NULL,
    language character varying NOT NULL,
    "time" double precision DEFAULT 0,
    memory integer DEFAULT 0,
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
    accepted boolean DEFAULT false NOT NULL
);

CREATE SEQUENCE contest_submition_submit_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE contest_submition_submit_id_seq OWNED BY contest_submition.submit_id;

CREATE TABLE contributions (
    uid integer,
    cid integer
);

CREATE TABLE country (
    zip character varying DEFAULT 'CU'::character varying NOT NULL,
    name character varying DEFAULT 'CUBA'::character varying NOT NULL,
    country_id integer NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    zip_two character varying DEFAULT ''::character varying NOT NULL,
    website character varying DEFAULT 'http://'::character varying NOT NULL
);

CREATE SEQUENCE country_country_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE country_country_id_seq OWNED BY country.country_id;

CREATE TABLE datasets (
    id integer NOT NULL,
    problem_id integer DEFAULT 0 NOT NULL,
    input text DEFAULT ''::text NOT NULL,
    output text DEFAULT ''::text NOT NULL
);

CREATE SEQUENCE datasets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE datasets_id_seq OWNED BY datasets.id;

CREATE TABLE draft (
    draft_id integer NOT NULL,
    username character varying NOT NULL,
    content text NOT NULL,
    title character varying,
    date timestamp without time zone DEFAULT now() NOT NULL,
    size integer DEFAULT 0 NOT NULL
);

CREATE SEQUENCE draft_draft_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE draft_draft_id_seq OWNED BY draft.draft_id;

CREATE SEQUENCE entries_eid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

CREATE TABLE entry_rate (
    uid integer NOT NULL,
    eid integer NOT NULL
);

CREATE TABLE events (
    type integer,
    date timestamp without time zone,
    args text,
    id integer NOT NULL,
    enabled boolean DEFAULT true NOT NULL
);

CREATE SEQUENCE events_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE events_id_seq OWNED BY events.id;

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

CREATE SEQUENCE exercise_exercice_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE exercise_exercice_id_seq OWNED BY problem.pid;

CREATE TABLE faq (
    answer text,
    question text,
    id integer NOT NULL
);

CREATE SEQUENCE faq_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE faq_id_seq OWNED BY faq.id;

CREATE TABLE followers (
    uid integer NOT NULL,
    fid integer NOT NULL
);

CREATE TABLE global_access_rules (
    rid bigint NOT NULL,
    rule character varying DEFAULT '*'::character varying NOT NULL
);

CREATE SEQUENCE global_access_rules_rid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE global_access_rules_rid_seq OWNED BY global_access_rules.rid;

CREATE TABLE global_flags (
    enabled_source_code_view boolean DEFAULT true NOT NULL,
    access_rules character varying DEFAULT '*'::character varying NOT NULL,
    global_id integer NOT NULL,
    enabled_submission boolean DEFAULT true NOT NULL,
    maintenance_mode boolean DEFAULT false NOT NULL,
    mail_notification_disabled boolean DEFAULT false NOT NULL,
    enabled_mail boolean DEFAULT false NOT NULL
);

CREATE SEQUENCE global_flags_global_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE global_flags_global_id_seq OWNED BY global_flags.global_id;

CREATE TABLE "group" (
    id integer NOT NULL,
    description character varying NOT NULL
);

CREATE SEQUENCE group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE group_id_seq OWNED BY "group".id;

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

CREATE SEQUENCE individual_virtual_contest_vid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE individual_virtual_contest_vid_seq OWNED BY individual_virtual_contest.vid;

CREATE TABLE institution (
    zip character varying DEFAULT 'UCI'::character varying NOT NULL,
    name character varying DEFAULT 'Universidad de las Ciencias Informáticas'::character varying NOT NULL,
    country_id integer NOT NULL,
    inst_id integer NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    website character varying DEFAULT 'http://'::character varying NOT NULL
);

CREATE SEQUENCE institution_inst_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE institution_inst_id_seq OWNED BY institution.inst_id;

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

CREATE SEQUENCE internal_mail_idmail_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE internal_mail_idmail_seq OWNED BY internal_mail.idmail;

CREATE TABLE language (
    language character varying,
    name_bin character varying,
    lid integer NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    descripcion character varying,
    key character varying DEFAULT 'C'::character varying NOT NULL,
    ext character varying DEFAULT 'none'::character varying NOT NULL,
    aid integer,
    priority integer
);

CREATE TABLE language_contest (
    lid integer NOT NULL,
    cid integer NOT NULL
);

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

CREATE SEQUENCE languages_lid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE languages_lid_seq OWNED BY language.lid;

CREATE TABLE locale (
    lid integer NOT NULL,
    description character varying NOT NULL,
    i18n character varying NOT NULL
);

CREATE SEQUENCE locale_lid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE locale_lid_seq OWNED BY locale.lid;

CREATE TABLE news (
    title character varying NOT NULL,
    description text NOT NULL,
    content text NOT NULL,
    uid integer NOT NULL,
    nid integer NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL
);

CREATE SEQUENCE news_nid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE news_nid_seq OWNED BY news.nid;

CREATE TABLE plagicoj_result (
    id_source_submission integer NOT NULL,
    id_destination_submission integer NOT NULL,
    dictum double precision
);

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

CREATE SEQUENCE plagicoj_result_judge_revisio_id_plagicoj_result_judge_revi_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE plagicoj_result_judge_revisio_id_plagicoj_result_judge_revi_seq OWNED BY plagicoj_result_judge_revision.id_plagicoj_result_judge_revision;

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

CREATE TABLE poll_answer (
    aid integer NOT NULL,
    pid integer,
    uid integer,
    option integer
);

CREATE SEQUENCE poll_answer_aid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE poll_answer_aid_seq OWNED BY poll_answer.aid;

CREATE SEQUENCE poll_pid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE poll_pid_seq OWNED BY poll.pid;

CREATE TABLE preference_profile (
    uid integer,
    date timestamp without time zone DEFAULT now(),
    valorations character varying
);

CREATE TABLE problem_classification (
    id_classification bigint NOT NULL,
    pid bigint NOT NULL,
    complexity integer DEFAULT 3 NOT NULL
);

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

CREATE SEQUENCE problem_id_source_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE problem_language (
    pid integer NOT NULL,
    lid integer NOT NULL
);

CREATE TABLE problem_limits (
    problem_id bigint NOT NULL,
    language_id bigint NOT NULL,
    max_case_execution_time bigint,
    max_total_execution_time bigint,
    max_memory bigint,
    max_source_code_lenght bigint
);

CREATE TABLE problem_source (
    name character varying,
    id_source integer DEFAULT nextval('problem_id_source_seq'::regclass) NOT NULL,
    author character varying
);

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

CREATE SEQUENCE problem_stats_stats_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE problem_stats_stats_id_seq OWNED BY problem_stats.stats_id;

CREATE TABLE psetter_problem (
    user_id integer NOT NULL,
    problem_id integer NOT NULL
);

CREATE TABLE recommendation_profile (
    uid integer,
    recommendation character varying,
    date timestamp without time zone DEFAULT now() NOT NULL
);

CREATE TABLE roles (
    rid integer NOT NULL,
    role character varying DEFAULT 'ROLE_USER'::character varying NOT NULL
);

CREATE SEQUENCE roles_rid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE roles_rid_seq OWNED BY roles.rid;

CREATE TABLE send_mail (
    title character varying,
    text character varying NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    idmail integer NOT NULL,
    username character varying NOT NULL,
    size integer DEFAULT 0 NOT NULL,
    send_to character varying NOT NULL
);

CREATE SEQUENCE send_mail_idmail_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE send_mail_idmail_seq OWNED BY send_mail.idmail;

CREATE SEQUENCE shared_file_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE shared_files (
    fid integer DEFAULT nextval('shared_file_seq'::regclass) NOT NULL,
    path text,
    name character varying(300)
);

CREATE TABLE source (
    sid integer NOT NULL,
    code text,
    error text
);

CREATE TABLE submition (
    uid integer NOT NULL,
    pid integer NOT NULL,
    status character varying NOT NULL,
    language character varying NOT NULL,
    "time" integer DEFAULT 0,
    memory integer DEFAULT 0,
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

CREATE SEQUENCE submition_submit_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE submition_submit_id_seq OWNED BY submition.submit_id;

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

CREATE SEQUENCE tbl_user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE tbl_user_user_id_seq OWNED BY users.uid;

CREATE TABLE team_profile (
    uid integer NOT NULL,
    user_1 character varying DEFAULT 'none'::character varying NOT NULL,
    user_2 character varying DEFAULT 'none'::character varying NOT NULL,
    user_3 character varying DEFAULT 'none'::character varying NOT NULL,
    coach character varying DEFAULT 'none'::character varying NOT NULL
);

CREATE TABLE tmp (
    sid integer,
    code text
);

CREATE SEQUENCE translation_pending_id_seq
    START WITH 40
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

CREATE TABLE user_achievement (
    aid integer NOT NULL,
    uid integer NOT NULL,
    awarded boolean DEFAULT false NOT NULL,
    count integer DEFAULT 0,
    level integer DEFAULT 0 NOT NULL
);

CREATE TABLE user_clarification (
    id_clarification integer NOT NULL,
    teamfor integer NOT NULL,
    ucid integer NOT NULL,
    read boolean DEFAULT false NOT NULL
);

CREATE SEQUENCE user_clarification_ucid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE user_clarification_ucid_seq OWNED BY user_clarification.ucid;

CREATE TABLE user_comments (
    uid integer,
    eid integer,
    date timestamp(6) without time zone,
    content text,
    cid integer NOT NULL,
    enabled boolean DEFAULT true
);

CREATE SEQUENCE user_comments_cid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE user_comments_cid_seq OWNED BY user_comments.cid;

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
    groupd character varying DEFAULT 'participant'::character varying NOT NULL
);

CREATE TABLE user_problem (
    user_id integer NOT NULL,
    problem_id integer NOT NULL,
    lock boolean DEFAULT false NOT NULL,
    accepted boolean DEFAULT false NOT NULL
);

CREATE TABLE user_problem_favorite (
    user_id integer NOT NULL,
    problem_id integer NOT NULL
);

CREATE TABLE user_problem_tmp (
    user_id integer NOT NULL,
    problem_id integer NOT NULL,
    lock boolean DEFAULT false NOT NULL,
    accepted boolean DEFAULT false NOT NULL
);

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

CREATE SEQUENCE user_profile_upid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE user_profile_upid_seq OWNED BY user_profile.upid;

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

CREATE SEQUENCE user_stats_stats_id_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE user_stats_stats_id_seq OWNED BY user_stats.stats_id;

CREATE TABLE virtual_contest_guest (
    vid integer NOT NULL,
    username character varying NOT NULL
);

CREATE TABLE virtual_contest_problem (
    vid integer NOT NULL,
    pid integer NOT NULL
);

CREATE TABLE votes (
    uid integer,
    pid integer,
    vote integer
);

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

CREATE SEQUENCE wboard_contest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE wboard_contest_id_seq OWNED BY wboard_contest.id;

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

CREATE SEQUENCE wboard_site_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE wboard_site_id_seq OWNED BY wboard_site.sid;

CREATE TABLE wboard_user_site (
    uid integer NOT NULL,
    sid integer NOT NULL
);

ALTER TABLE ONLY account_activation ALTER COLUMN act_id SET DEFAULT nextval('account_activation_act_id_seq'::regclass);

ALTER TABLE ONLY admin_log ALTER COLUMN id SET DEFAULT nextval('admin_log_id_seq'::regclass);

ALTER TABLE ONLY announcements ALTER COLUMN aid SET DEFAULT nextval('announcements_aid_seq'::regclass);

ALTER TABLE ONLY authorities ALTER COLUMN id SET DEFAULT nextval('authorities_id_seq'::regclass);

ALTER TABLE ONLY clarification ALTER COLUMN id_clarification SET DEFAULT nextval('clarification_id_clarification_seq'::regclass);

ALTER TABLE ONLY classifications ALTER COLUMN id_classification SET DEFAULT nextval('classifications_id_classification_seq'::regclass);

ALTER TABLE ONLY cojmail_log ALTER COLUMN cm_id SET DEFAULT nextval('cojmail_log_cm_id_seq'::regclass);

ALTER TABLE ONLY contest_awards ALTER COLUMN aid SET DEFAULT nextval('contest_awards_users_aid_seq'::regclass);

ALTER TABLE ONLY contest_brief ALTER COLUMN id SET DEFAULT nextval('contest_brief_id_seq'::regclass);

ALTER TABLE ONLY contest_flags ALTER COLUMN cfid SET DEFAULT nextval('contest_flags_cfid_seq'::regclass);

ALTER TABLE ONLY contest_registration ALTER COLUMN rid SET DEFAULT nextval('contest_registration_rid_seq'::regclass);

ALTER TABLE ONLY contest_style ALTER COLUMN sid SET DEFAULT nextval('contest_style_sid_seq'::regclass);

ALTER TABLE ONLY contest_submition ALTER COLUMN submit_id SET DEFAULT nextval('contest_submition_submit_id_seq'::regclass);

ALTER TABLE ONLY country ALTER COLUMN country_id SET DEFAULT nextval('country_country_id_seq'::regclass);

ALTER TABLE ONLY datasets ALTER COLUMN id SET DEFAULT nextval('datasets_id_seq'::regclass);

ALTER TABLE ONLY draft ALTER COLUMN draft_id SET DEFAULT nextval('draft_draft_id_seq'::regclass);

ALTER TABLE ONLY events ALTER COLUMN id SET DEFAULT nextval('events_id_seq'::regclass);

ALTER TABLE ONLY faq ALTER COLUMN id SET DEFAULT nextval('faq_id_seq'::regclass);

ALTER TABLE ONLY global_access_rules ALTER COLUMN rid SET DEFAULT nextval('global_access_rules_rid_seq'::regclass);

ALTER TABLE ONLY global_flags ALTER COLUMN global_id SET DEFAULT nextval('global_flags_global_id_seq'::regclass);

ALTER TABLE ONLY "group" ALTER COLUMN id SET DEFAULT nextval('group_id_seq'::regclass);

ALTER TABLE ONLY individual_virtual_contest ALTER COLUMN vid SET DEFAULT nextval('individual_virtual_contest_vid_seq'::regclass);

ALTER TABLE ONLY institution ALTER COLUMN inst_id SET DEFAULT nextval('institution_inst_id_seq'::regclass);

ALTER TABLE ONLY internal_mail ALTER COLUMN idmail SET DEFAULT nextval('internal_mail_idmail_seq'::regclass);

ALTER TABLE ONLY language ALTER COLUMN lid SET DEFAULT nextval('languages_lid_seq'::regclass);

ALTER TABLE ONLY locale ALTER COLUMN lid SET DEFAULT nextval('locale_lid_seq'::regclass);

ALTER TABLE ONLY news ALTER COLUMN nid SET DEFAULT nextval('news_nid_seq'::regclass);

ALTER TABLE ONLY plagicoj_result_judge_revision ALTER COLUMN id_plagicoj_result_judge_revision SET DEFAULT nextval('plagicoj_result_judge_revisio_id_plagicoj_result_judge_revi_seq'::regclass);

ALTER TABLE ONLY poll ALTER COLUMN pid SET DEFAULT nextval('poll_pid_seq'::regclass);

ALTER TABLE ONLY poll_answer ALTER COLUMN aid SET DEFAULT nextval('poll_answer_aid_seq'::regclass);

ALTER TABLE ONLY problem ALTER COLUMN pid SET DEFAULT nextval('exercise_exercice_id_seq'::regclass);

ALTER TABLE ONLY problem_stats ALTER COLUMN stats_id SET DEFAULT nextval('problem_stats_stats_id_seq'::regclass);

ALTER TABLE ONLY roles ALTER COLUMN rid SET DEFAULT nextval('roles_rid_seq'::regclass);

ALTER TABLE ONLY send_mail ALTER COLUMN idmail SET DEFAULT nextval('send_mail_idmail_seq'::regclass);

ALTER TABLE ONLY special_awards ALTER COLUMN aid SET DEFAULT nextval('contest_awards_aid_seq'::regclass);

ALTER TABLE ONLY submition ALTER COLUMN submit_id SET DEFAULT nextval('submition_submit_id_seq'::regclass);

ALTER TABLE ONLY user_clarification ALTER COLUMN ucid SET DEFAULT nextval('user_clarification_ucid_seq'::regclass);

ALTER TABLE ONLY user_profile ALTER COLUMN upid SET DEFAULT nextval('user_profile_upid_seq'::regclass);

ALTER TABLE ONLY user_stats ALTER COLUMN stats_id SET DEFAULT nextval('user_stats_stats_id_seq'::regclass);

ALTER TABLE ONLY users ALTER COLUMN uid SET DEFAULT nextval('tbl_user_user_id_seq'::regclass);

ALTER TABLE ONLY wboard_contest ALTER COLUMN id SET DEFAULT nextval('wboard_contest_id_seq'::regclass);

ALTER TABLE ONLY wboard_site ALTER COLUMN sid SET DEFAULT nextval('wboard_site_id_seq'::regclass);

ALTER TABLE ONLY account_activation
    ADD CONSTRAINT account_activation_pkey PRIMARY KEY (act_id);

ALTER TABLE ONLY achievements
    ADD CONSTRAINT achievements_pkey PRIMARY KEY (aid);

ALTER TABLE ONLY admin_clarification
    ADD CONSTRAINT admin_clarification_pkey PRIMARY KEY (id_clarification);

ALTER TABLE ONLY admin_log
    ADD CONSTRAINT admin_log_pkey PRIMARY KEY (id);

ALTER TABLE ONLY announcements
    ADD CONSTRAINT announcements_pkey PRIMARY KEY (aid);

ALTER TABLE ONLY authorities
    ADD CONSTRAINT authorities_pkey PRIMARY KEY (id);

ALTER TABLE ONLY clarification
    ADD CONSTRAINT clarification_pkey PRIMARY KEY (id_clarification);

ALTER TABLE ONLY classifications
    ADD CONSTRAINT classification_pkey PRIMARY KEY (id_classification);

ALTER TABLE ONLY classifications
    ADD CONSTRAINT classification_unique UNIQUE (name);

ALTER TABLE ONLY cojmail_log
    ADD CONSTRAINT cojmail_log_pkey PRIMARY KEY (cm_id);

ALTER TABLE ONLY contest_brief
    ADD CONSTRAINT contest_brief_pkey PRIMARY KEY (id);

ALTER TABLE ONLY contest_flags
    ADD CONSTRAINT contest_flags_pkey PRIMARY KEY (cfid);

ALTER TABLE ONLY contest_judges
    ADD CONSTRAINT contest_judges_pkey PRIMARY KEY (cid, id_admin);

ALTER TABLE ONLY contest
    ADD CONSTRAINT contest_pkey PRIMARY KEY (cid);

ALTER TABLE ONLY contest_registration
    ADD CONSTRAINT contest_registration_pkey PRIMARY KEY (rid);

ALTER TABLE ONLY contest_style
    ADD CONSTRAINT contest_style_pkey PRIMARY KEY (sid);

ALTER TABLE ONLY contest_submition
    ADD CONSTRAINT contest_submition_pkey PRIMARY KEY (submit_id);

ALTER TABLE ONLY country
    ADD CONSTRAINT country_name_key UNIQUE (name);

ALTER TABLE ONLY country
    ADD CONSTRAINT country_name_key1 UNIQUE (name);

ALTER TABLE ONLY country
    ADD CONSTRAINT country_pkey PRIMARY KEY (country_id);

ALTER TABLE ONLY country
    ADD CONSTRAINT country_zip_key UNIQUE (zip);

ALTER TABLE ONLY country
    ADD CONSTRAINT country_zip_key1 UNIQUE (zip);

ALTER TABLE ONLY datasets
    ADD CONSTRAINT datasets_pkey PRIMARY KEY (id);

ALTER TABLE ONLY draft
    ADD CONSTRAINT draft_pkey PRIMARY KEY (draft_id);

ALTER TABLE ONLY entries
    ADD CONSTRAINT entries_pkey PRIMARY KEY (eid);

ALTER TABLE ONLY entry_rate
    ADD CONSTRAINT entry_rate_pkey PRIMARY KEY (uid, eid);

ALTER TABLE ONLY events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);

ALTER TABLE ONLY problem
    ADD CONSTRAINT exercise_pkey PRIMARY KEY (pid);

ALTER TABLE ONLY followers
    ADD CONSTRAINT followers_pkey PRIMARY KEY (uid, fid);

ALTER TABLE ONLY global_access_rules
    ADD CONSTRAINT global_access_rules_pkey PRIMARY KEY (rid);

ALTER TABLE ONLY global_flags
    ADD CONSTRAINT global_flags_pkey PRIMARY KEY (global_id);

ALTER TABLE ONLY "group"
    ADD CONSTRAINT group_pkey PRIMARY KEY (id);

ALTER TABLE ONLY individual_virtual_contest
    ADD CONSTRAINT individual_virtual_contest_pkey PRIMARY KEY (vid);

ALTER TABLE ONLY institution
    ADD CONSTRAINT institution_pkey PRIMARY KEY (inst_id);

ALTER TABLE ONLY institution
    ADD CONSTRAINT institution_zip_key UNIQUE (zip);

ALTER TABLE ONLY internal_mail
    ADD CONSTRAINT internal_mail_pkey PRIMARY KEY (idmail);

ALTER TABLE ONLY language_contest
    ADD CONSTRAINT language_contest_pkey PRIMARY KEY (lid, cid);

ALTER TABLE ONLY language_stats_contest
    ADD CONSTRAINT language_stats_contest_pkey PRIMARY KEY (lid, cid);

ALTER TABLE ONLY language_stats
    ADD CONSTRAINT language_stats_pkey PRIMARY KEY (lid);

ALTER TABLE ONLY language_virtual_stats
    ADD CONSTRAINT language_virtual_stats_pkey PRIMARY KEY (lid);

ALTER TABLE ONLY language
    ADD CONSTRAINT languages_language_key UNIQUE (language);

ALTER TABLE ONLY language
    ADD CONSTRAINT languages_pkey PRIMARY KEY (lid);

ALTER TABLE ONLY locale
    ADD CONSTRAINT locale_pkey PRIMARY KEY (lid);

ALTER TABLE ONLY news
    ADD CONSTRAINT news_pkey PRIMARY KEY (nid);

ALTER TABLE ONLY balloontrackers
    ADD CONSTRAINT pk_balloontrackers PRIMARY KEY (uid, cid);

ALTER TABLE ONLY special_awards
    ADD CONSTRAINT pk_contest_awards PRIMARY KEY (aid);

ALTER TABLE ONLY contest_awards
    ADD CONSTRAINT pk_contest_awards_users PRIMARY KEY (aid, cid);

ALTER TABLE ONLY contest_source
    ADD CONSTRAINT pk_contest_source PRIMARY KEY (sid);

ALTER TABLE ONLY faq
    ADD CONSTRAINT pk_faq PRIMARY KEY (id);

ALTER TABLE ONLY poll
    ADD CONSTRAINT pk_poll PRIMARY KEY (pid);

ALTER TABLE ONLY poll_answer
    ADD CONSTRAINT pk_pollans PRIMARY KEY (aid);

ALTER TABLE ONLY problem_source
    ADD CONSTRAINT pk_problem_source PRIMARY KEY (id_source);

ALTER TABLE ONLY shared_files
    ADD CONSTRAINT pk_shared_files PRIMARY KEY (fid);

ALTER TABLE ONLY source
    ADD CONSTRAINT pk_source PRIMARY KEY (sid);

ALTER TABLE ONLY plagicoj_result_judge_revision
    ADD CONSTRAINT plagicoj_result_judge_revision_pkey PRIMARY KEY (id_plagicoj_result_judge_revision);

ALTER TABLE ONLY plagicoj_result
    ADD CONSTRAINT plagicoj_result_pkey PRIMARY KEY (id_source_submission, id_destination_submission);

ALTER TABLE ONLY problem_classification
    ADD CONSTRAINT problem_classification_pkey PRIMARY KEY (id_classification, pid);

ALTER TABLE ONLY problem_contest
    ADD CONSTRAINT problem_contest_pkey PRIMARY KEY (pid, cid);

ALTER TABLE ONLY problem_language
    ADD CONSTRAINT problem_language_pkey PRIMARY KEY (pid, lid);

ALTER TABLE ONLY problem_limits
    ADD CONSTRAINT problem_limits_pk PRIMARY KEY (problem_id, language_id);

ALTER TABLE ONLY problem_stats_contest
    ADD CONSTRAINT problem_stats_contest_pkey PRIMARY KEY (pid, cid);

ALTER TABLE ONLY problem_stats
    ADD CONSTRAINT problem_stats_pkey PRIMARY KEY (stats_id);

ALTER TABLE ONLY psetter_problem
    ADD CONSTRAINT psetter_problem_pkey PRIMARY KEY (user_id, problem_id);

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (rid);

ALTER TABLE ONLY send_mail
    ADD CONSTRAINT send_mail_pkey PRIMARY KEY (idmail);

ALTER TABLE ONLY submition
    ADD CONSTRAINT submition_pkey PRIMARY KEY (submit_id);

ALTER TABLE ONLY team_profile
    ADD CONSTRAINT team_profile_pkey PRIMARY KEY (uid);

ALTER TABLE ONLY translation_pending
    ADD CONSTRAINT transtation_pending_pkey PRIMARY KEY (id);

ALTER TABLE ONLY poll_answer
    ADD CONSTRAINT un_pid_uid UNIQUE (pid, uid);

ALTER TABLE ONLY user_achievement
    ADD CONSTRAINT user_achievement_pkey PRIMARY KEY (aid, uid);

ALTER TABLE ONLY user_clarification
    ADD CONSTRAINT user_clarification_pkey PRIMARY KEY (ucid);

ALTER TABLE ONLY user_comments
    ADD CONSTRAINT user_comments_pkey PRIMARY KEY (cid);

ALTER TABLE ONLY user_comments
    ADD CONSTRAINT user_comments_uid_eid_date_key UNIQUE (uid, eid, date);

ALTER TABLE ONLY user_contest
    ADD CONSTRAINT user_contest_pkey PRIMARY KEY (uid, cid, virtual);

ALTER TABLE ONLY users
    ADD CONSTRAINT user_pkey PRIMARY KEY (uid);

ALTER TABLE ONLY user_problem_favorite
    ADD CONSTRAINT user_problem_favorite_pkey PRIMARY KEY (user_id, problem_id);

ALTER TABLE ONLY user_problem
    ADD CONSTRAINT user_problem_pkey PRIMARY KEY (user_id, problem_id);

ALTER TABLE ONLY user_problem_tmp
    ADD CONSTRAINT user_problem_pkey_tmp PRIMARY KEY (user_id, problem_id);

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_email_key UNIQUE (email);

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_pkey PRIMARY KEY (upid);

ALTER TABLE ONLY user_stats_contest
    ADD CONSTRAINT user_stats_contest_pkey PRIMARY KEY (uid, cid);

ALTER TABLE ONLY user_stats
    ADD CONSTRAINT user_stats_pkey PRIMARY KEY (stats_id);

ALTER TABLE ONLY users
    ADD CONSTRAINT user_user_key UNIQUE (username);

ALTER TABLE ONLY virtual_contest_problem
    ADD CONSTRAINT virtual_contest_problem_pkey PRIMARY KEY (vid, pid);

CREATE INDEX fki_ ON language_contest USING btree (lid);

CREATE INDEX fki_classifications_achievements ON classifications USING btree (aid);

CREATE INDEX fki_contest_contest ON contest USING btree (template);

CREATE INDEX fki_contest_users ON contest USING btree (uid);

CREATE INDEX fki_entries_uid ON entries USING btree (uid);

CREATE INDEX fki_language_achievement ON language USING btree (aid);

CREATE INDEX fki_pollans_poll ON poll_answer USING btree (pid);

CREATE INDEX fki_pollans_user ON poll_answer USING btree (uid);

CREATE INDEX fki_problem_problem_source ON problem USING btree (id_source);

CREATE INDEX ix_cid ON user_contest USING btree (cid);

CREATE TRIGGER problemclassinstrig AFTER INSERT ON problem_classification FOR EACH ROW EXECUTE PROCEDURE problemclassinsfn();

CREATE TRIGGER problemclasstrig AFTER DELETE ON problem_classification FOR EACH ROW EXECUTE PROCEDURE problemclassfn();

ALTER TABLE ONLY admin_clarification
    ADD CONSTRAINT admin_clarification_id_clarification_fkey FOREIGN KEY (id_clarification) REFERENCES clarification(id_clarification);

ALTER TABLE ONLY clarification
    ADD CONSTRAINT clarification_id_team_fkey FOREIGN KEY (id_team) REFERENCES users(uid);

ALTER TABLE ONLY contest_brief
    ADD CONSTRAINT contest_brief_cid_fkey FOREIGN KEY (cid) REFERENCES contest(cid);

ALTER TABLE ONLY contest_flags
    ADD CONSTRAINT contest_flags_cid_fkey FOREIGN KEY (cid) REFERENCES contest(cid);

ALTER TABLE ONLY contest_judges
    ADD CONSTRAINT contest_user_clarification_cid_fkey FOREIGN KEY (cid) REFERENCES contest(cid);

ALTER TABLE ONLY contest_judges
    ADD CONSTRAINT contest_user_clarification_id_admin_fkey FOREIGN KEY (id_admin) REFERENCES users(uid);

ALTER TABLE ONLY entry_rate
    ADD CONSTRAINT entry_rate_eid_fkey FOREIGN KEY (eid) REFERENCES entries(eid);

ALTER TABLE ONLY entry_rate
    ADD CONSTRAINT entry_rate_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY balloontrackers
    ADD CONSTRAINT fk_balloontrackers_contest FOREIGN KEY (cid) REFERENCES contest(cid);

ALTER TABLE ONLY balloontrackers
    ADD CONSTRAINT fk_balloontrackers_users FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY classifications
    ADD CONSTRAINT fk_classifications_achievements FOREIGN KEY (aid) REFERENCES achievements(aid);

ALTER TABLE ONLY contest_awards
    ADD CONSTRAINT fk_contest_awards_special_awards FOREIGN KEY (aid) REFERENCES special_awards(aid);

ALTER TABLE ONLY contest_awards
    ADD CONSTRAINT fk_contest_awards_users FOREIGN KEY (cid) REFERENCES contest(cid);

ALTER TABLE ONLY contest
    ADD CONSTRAINT fk_contest_contest FOREIGN KEY (template) REFERENCES contest(cid);

ALTER TABLE ONLY contest
    ADD CONSTRAINT fk_contest_users FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY entries
    ADD CONSTRAINT fk_entries_uid FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY language
    ADD CONSTRAINT fk_language_achievement FOREIGN KEY (aid) REFERENCES achievements(aid);

ALTER TABLE ONLY poll_answer
    ADD CONSTRAINT fk_pollans_poll FOREIGN KEY (pid) REFERENCES poll(pid);

ALTER TABLE ONLY poll_answer
    ADD CONSTRAINT fk_pollans_user FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY problem
    ADD CONSTRAINT fk_problem_problem_source FOREIGN KEY (id_source) REFERENCES problem_source(id_source);

ALTER TABLE ONLY followers
    ADD CONSTRAINT followers_fid_fkey FOREIGN KEY (fid) REFERENCES users(uid);

ALTER TABLE ONLY followers
    ADD CONSTRAINT followers_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY individual_virtual_contest
    ADD CONSTRAINT individual_virtual_contest_cid_fkey FOREIGN KEY (cid) REFERENCES contest(cid);

ALTER TABLE ONLY individual_virtual_contest
    ADD CONSTRAINT individual_virtual_contest_username_fkey FOREIGN KEY (username) REFERENCES users(username);

ALTER TABLE ONLY internal_mail
    ADD CONSTRAINT internal_mail_idfrom_fkey FOREIGN KEY (idfrom) REFERENCES users(username);

ALTER TABLE ONLY language_contest
    ADD CONSTRAINT language_contest_cid_fkey FOREIGN KEY (cid) REFERENCES contest(cid);

ALTER TABLE ONLY language_contest
    ADD CONSTRAINT language_contest_lid_fkey FOREIGN KEY (lid) REFERENCES language(lid);

ALTER TABLE ONLY language_stats_contest
    ADD CONSTRAINT language_stats_contest_cid_fkey FOREIGN KEY (cid) REFERENCES contest(cid);

ALTER TABLE ONLY language_stats_contest
    ADD CONSTRAINT language_stats_contest_lid_fkey FOREIGN KEY (lid) REFERENCES language(lid);

ALTER TABLE ONLY language_stats
    ADD CONSTRAINT language_stats_lid_fkey FOREIGN KEY (lid) REFERENCES language(lid);

ALTER TABLE ONLY language_virtual_stats
    ADD CONSTRAINT language_virtual_stats_lid_fkey FOREIGN KEY (lid) REFERENCES language(lid);

ALTER TABLE ONLY news
    ADD CONSTRAINT news_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY plagicoj_result_judge_revision
    ADD CONSTRAINT plagicoj_result_judge_revision FOREIGN KEY (id_source_submission, id_destination_submission) REFERENCES plagicoj_result(id_source_submission, id_destination_submission) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY plagicoj_result_judge_revision
    ADD CONSTRAINT plagicoj_result_judge_revision_id_user_fkey FOREIGN KEY (id_user) REFERENCES users(username) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY preference_profile
    ADD CONSTRAINT preference_profile_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY problem_classification
    ADD CONSTRAINT problem_classification_id_classification_fkey FOREIGN KEY (id_classification) REFERENCES classifications(id_classification) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY problem_classification
    ADD CONSTRAINT problem_classification_pid_fkey FOREIGN KEY (pid) REFERENCES problem(pid);

ALTER TABLE ONLY problem_language
    ADD CONSTRAINT problem_language_lid_fkey FOREIGN KEY (lid) REFERENCES language(lid);

ALTER TABLE ONLY problem_language
    ADD CONSTRAINT problem_language_pid_fkey FOREIGN KEY (pid) REFERENCES problem(pid);

ALTER TABLE ONLY problem_stats
    ADD CONSTRAINT problem_stats_pid_fkey FOREIGN KEY (pid) REFERENCES problem(pid);

ALTER TABLE ONLY recommendation_profile
    ADD CONSTRAINT recommendation_profile_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY team_profile
    ADD CONSTRAINT team_profile_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY user_clarification
    ADD CONSTRAINT user_clarification_id_clarification_fkey FOREIGN KEY (id_clarification) REFERENCES clarification(id_clarification);

ALTER TABLE ONLY user_clarification
    ADD CONSTRAINT user_clarification_teamfor_fkey FOREIGN KEY (teamfor) REFERENCES users(uid);

ALTER TABLE ONLY user_comments
    ADD CONSTRAINT user_comments_eid_fkey FOREIGN KEY (eid) REFERENCES entries(eid);

ALTER TABLE ONLY user_comments
    ADD CONSTRAINT user_comments_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY user_stats_contest
    ADD CONSTRAINT user_stats_contest_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY user_stats
    ADD CONSTRAINT user_stats_uid_fkey FOREIGN KEY (uid) REFERENCES users(uid);

ALTER TABLE ONLY users
    ADD CONSTRAINT users_lid_fkey FOREIGN KEY (lid) REFERENCES language(lid);

ALTER TABLE ONLY users
    ADD CONSTRAINT users_lid_fkey1 FOREIGN KEY (lid) REFERENCES language(lid);

ALTER TABLE ONLY users
    ADD CONSTRAINT users_locale_fkey FOREIGN KEY (locale) REFERENCES locale(lid);

