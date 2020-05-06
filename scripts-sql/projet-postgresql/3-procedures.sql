SET search_path TO projet;


-- Supprime toutes les fonctions du schéma

DO $ccode$
DECLARE 
	r RECORD;
BEGIN
	FOR r IN
		SELECT 'DROP FUNCTION ' || ns.nspname || '.' || proname 
	       || '(' || oidvectortypes(proargtypes) || ')' AS sql
		FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid)
		WHERE ns.nspname = 'projet'  
	LOOP
		EXECUTE r.sql;
	END LOOP;
END;
$ccode$;


-- Fonctions

CREATE FUNCTION compte_inserer(
	p_pseudo 		VARCHAR,
	p_motdepasse 	VARCHAR,
	p_mail			VARCHAR,
	p_id        	OUT	INT,
	p_roles			VARCHAR[]
) 
AS $ccode$

BEGIN
	INSERT INTO compte ( pseudo, motdepasse, mail )
	VALUES ( p_pseudo, p_motdepasse,p_mail )
	RETURNING id INTO p_id;
	
	
END;
$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION compte_modifier(
	p_pseudo 		VARCHAR,
	p_motdepasse 	VARCHAR,
	p_mail			VARCHAR,
	p_id     		INT,
	
) 
RETURNS VOID 
AS $ccode$

BEGIN
	UPDATE compte 
	SET pseudo = p_pseudo, 
		motdepasse = p_motdepasse, 
		mail = p_mail 
	WHERE id =  p_id;

	
END;
$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION compte_supprimer( p_id INT ) 
RETURNS VOID 
AS $ccode$
BEGIN
	
	DELETE FROM compte WHERE id = p_id;
END;
$ccode$ LANGUAGE plpgsql;


--CREATE FUNCTION compte_retrouver( p_idCompte INT ) 
--RETURNS TABLE (
--    idcompte    INT,
--    pseudo      VARCHAR,
--    motdepasse  VARCHAR,
--    email       VARCHAR,
--    roles       VARCHAR[]
--)
--AS $ccode$
--BEGIN
--	RETURN QUERY
--	SELECT c.*, ARRAY_AGG( r.role ) AS roles
--	FROM compte c 
--	LEFT JOIN role r ON c.idcompte = r.idcompte
--	WHERE c.idcompte = p_idcompte
--	GROUP BY c.idcompte;
--END;
--$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION compte_retrouver( p_id INT ) 
RETURNS SETOF v_compte
AS $ccode$
BEGIN
	RETURN QUERY
	SELECT * 
	FROM v_compte
	WHERE id = p_id;
END;
$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION compte_lister_tout() 
RETURNS SETOF v_compte_avec_roles 
AS $ccode$
BEGIN
	RETURN QUERY
	SELECT * 
	FROM v_compte_avec_roles 
	ORDER BY pseudo;
END;
$ccode$ LANGUAGE plpgsql;


--CREATE FUNCTION compte_valider_authentification( p_pseudo VARCHAR, p_motdepasse VARCHAR ) 
--RETURNS TABLE (
--    idcompte    INT,
--    pseudo      VARCHAR,
--    motdepasse  VARCHAR,
--    email       VARCHAR,
--    roles       VARCHAR[]
--)
--AS $ccode$
--BEGIN
--	RETURN QUERY
--	SELECT c.*,  ARRAY_AGG( r.role ) AS roles
--	FROM compte c 
--	LEFT JOIN role r ON c.idcompte = r.idcompte
--	WHERE c.pseudo = P_pseudo
--	  AND c.motdepasse = p_motdepasse
--	GROUP BY c.idcompte;
--END;
--$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION compte_valider_authentification( p_pseudo VARCHAR, p_motdepasse VARCHAR ) 
RETURNS SETOF v_compte
AS $ccode$
BEGIN
	RETURN QUERY
	SELECT * FROM v_compte
	WHERE pseudo = P_pseudo
	  AND motdepasse = p_motdepasse;
END;
$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION compte_verifier_unicite_pseudo(
	p_pseudo 		VARCHAR,
	p_id     		INT,
	OUT p_unicite	BOOLEAN
) 
AS $ccode$
BEGIN
	SELECT COUNT(*) = 0 INTO p_unicite
	FROM compte
	WHERE pseudo = p_pseudo
	  AND idcompte <> P_id;
END;
$ccode$ LANGUAGE plpgsql;


