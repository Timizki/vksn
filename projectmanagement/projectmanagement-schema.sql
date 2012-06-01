--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: person; Type: TABLE; Schema: public; Owner: projectmanagement; Tablespace: 
--

CREATE TABLE person (
    id integer NOT NULL,
    deleted timestamp without time zone,
    created timestamp without time zone NOT NULL,
    username character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    password character varying(255)
);


ALTER TABLE public.person OWNER TO projectmanagement;

--
-- Name: person_role; Type: TABLE; Schema: public; Owner: projectmanagement; Tablespace: 
--

CREATE TABLE person_role (
    person_id integer NOT NULL,
    roles_id integer NOT NULL
);


ALTER TABLE public.person_role OWNER TO projectmanagement;

--
-- Name: project; Type: TABLE; Schema: public; Owner: projectmanagement; Tablespace: 
--

CREATE TABLE project (
    id integer NOT NULL,
    deleted timestamp without time zone,
    created timestamp without time zone NOT NULL,
    deadline timestamp without time zone,
    name character varying(255),
    description character varying(255)
);


ALTER TABLE public.project OWNER TO projectmanagement;

--
-- Name: project_person; Type: TABLE; Schema: public; Owner: projectmanagement; Tablespace: 
--

CREATE TABLE project_person (
    project_id integer NOT NULL,
    actors_id integer NOT NULL
);


ALTER TABLE public.project_person OWNER TO projectmanagement;

--
-- Name: project_tasks; Type: TABLE; Schema: public; Owner: projectmanagement; Tablespace: 
--

CREATE TABLE project_tasks (
    project_id integer NOT NULL,
    task_id integer NOT NULL
);


ALTER TABLE public.project_tasks OWNER TO projectmanagement;

--
-- Name: role; Type: TABLE; Schema: public; Owner: projectmanagement; Tablespace: 
--

CREATE TABLE role (
    id integer NOT NULL,
    deleted timestamp without time zone,
    created timestamp without time zone NOT NULL,
    name character varying(255)
);


ALTER TABLE public.role OWNER TO projectmanagement;

--
-- Name: task; Type: TABLE; Schema: public; Owner: projectmanagement; Tablespace: 
--

CREATE TABLE task (
    id integer NOT NULL,
    deleted timestamp without time zone,
    created timestamp without time zone NOT NULL,
    minduration integer,
    hourduration integer,
    name character varying(255),
    state integer,
    description character varying(255),
    actor_id integer
);


ALTER TABLE public.task OWNER TO projectmanagement;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: projectmanagement
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO projectmanagement;

--
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: projectmanagement; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: person_role_roles_id_key; Type: CONSTRAINT; Schema: public; Owner: projectmanagement; Tablespace: 
--

ALTER TABLE ONLY person_role
    ADD CONSTRAINT person_role_roles_id_key UNIQUE (roles_id);


--
-- Name: project_pkey; Type: CONSTRAINT; Schema: public; Owner: projectmanagement; Tablespace: 
--

ALTER TABLE ONLY project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id);


--
-- Name: project_tasks_task_id_key; Type: CONSTRAINT; Schema: public; Owner: projectmanagement; Tablespace: 
--

ALTER TABLE ONLY project_tasks
    ADD CONSTRAINT project_tasks_task_id_key UNIQUE (task_id);


--
-- Name: role_pkey; Type: CONSTRAINT; Schema: public; Owner: projectmanagement; Tablespace: 
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: task_pkey; Type: CONSTRAINT; Schema: public; Owner: projectmanagement; Tablespace: 
--

ALTER TABLE ONLY task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);


--
-- Name: fk27a9a555574a11; Type: FK CONSTRAINT; Schema: public; Owner: projectmanagement
--

ALTER TABLE ONLY task
    ADD CONSTRAINT fk27a9a555574a11 FOREIGN KEY (actor_id) REFERENCES person(id);


--
-- Name: fk33e64f3bcdf42248; Type: FK CONSTRAINT; Schema: public; Owner: projectmanagement
--

ALTER TABLE ONLY project_person
    ADD CONSTRAINT fk33e64f3bcdf42248 FOREIGN KEY (actors_id) REFERENCES person(id);


--
-- Name: fk33e64f3bdff4aae3; Type: FK CONSTRAINT; Schema: public; Owner: projectmanagement
--

ALTER TABLE ONLY project_person
    ADD CONSTRAINT fk33e64f3bdff4aae3 FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: fk49fc87206fb18f2a; Type: FK CONSTRAINT; Schema: public; Owner: projectmanagement
--

ALTER TABLE ONLY person_role
    ADD CONSTRAINT fk49fc87206fb18f2a FOREIGN KEY (roles_id) REFERENCES role(id);


--
-- Name: fk49fc8720ea935cb1; Type: FK CONSTRAINT; Schema: public; Owner: projectmanagement
--

ALTER TABLE ONLY person_role
    ADD CONSTRAINT fk49fc8720ea935cb1 FOREIGN KEY (person_id) REFERENCES person(id);


--
-- Name: fkc814b2e823e7c871; Type: FK CONSTRAINT; Schema: public; Owner: projectmanagement
--

ALTER TABLE ONLY project_tasks
    ADD CONSTRAINT fkc814b2e823e7c871 FOREIGN KEY (task_id) REFERENCES task(id);


--
-- Name: fkc814b2e8dff4aae3; Type: FK CONSTRAINT; Schema: public; Owner: projectmanagement
--

ALTER TABLE ONLY project_tasks
    ADD CONSTRAINT fkc814b2e8dff4aae3 FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

