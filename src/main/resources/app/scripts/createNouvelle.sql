DROP TABLE IF EXISTS signaler_article;
DROP TABLE IF EXISTS signaler_membre;
DROP TABLE IF EXISTS service_juridique;
DROP TABLE IF EXISTS service_commercial;
DROP TABLE IF EXISTS gestionnaire;
DROP TABLE IF EXISTS enchere;
-- DROP TABLE IF EXISTS vendre;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS membre;
DROP TABLE IF EXISTS compte;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS categorie;
DROP TABLE IF EXISTS avis;
DROP TABLE IF EXISTS frais;
DROP TABLE IF EXISTS commission;
DROP TABLE IF EXISTS visite;
DROP TABLE IF EXISTS type_membre;
DROP TABLE IF EXISTS option_enchere;

-- -----------------------------------------------------------------------------
--       TABLE : VISITE
-- -----------------------------------------------------------------------------

CREATE TABLE visite(
                       visite_id INT AUTO_INCREMENT,
                       visite_date_heure TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                       CONSTRAINT pk_visite PRIMARY KEY(visite_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : COMPTE
-- -----------------------------------------------------------------------------

CREATE TABLE compte(

                       compte_id INT AUTO_INCREMENT,
                       compte_email VARCHAR(255) NOT NULL,
                       compte_mdp VARCHAR(255) NOT NULL,
                       compte_role VARCHAR(50) NOT NULL,

                       CONSTRAINT pk_compte PRIMARY KEY(compte_id)

);

-- -----------------------------------------------------------------------------
--       TABLE : MEMBRE
-- -----------------------------------------------------------------------------

CREATE TABLE membre(

                       membre_id INT AUTO_INCREMENT,
                       membre_nom VARCHAR(50) NOT NULL,
                       membre_prenom VARCHAR(50) NOT NULL,
                       membre_date_naissance DATE,
                       membre_code_postal VARCHAR(50),
                       membre_adresse_postale VARCHAR(50),
                       membre_ville VARCHAR(50),
                       membre_pays VARCHAR(50),
                       membre_plus TINYINT(1) DEFAULT 0,
                       compte_id INT NOT NULL,

                       CONSTRAINT pk_membre PRIMARY KEY(membre_id),
                       CONSTRAINT fk_membre_compte_id FOREIGN KEY (compte_id) REFERENCES compte (compte_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : GESTIONNAIRE
-- -----------------------------------------------------------------------------

CREATE TABLE gestionnaire
(

    gestionnaire_id INT AUTO_INCREMENT,
    compte_id       INT NOT NULL,

    CONSTRAINT pk_gestionnaire PRIMARY KEY (gestionnaire_id),
    CONSTRAINT fk_gestionnaire_compte_id FOREIGN KEY (compte_id) REFERENCES compte (compte_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : SERVICE_COMMERCIAL
-- -----------------------------------------------------------------------------

CREATE TABLE service_commercial(

                                   service_commercial_id INT AUTO_INCREMENT,
                                   compte_id INT NOT NULL,

                                   CONSTRAINT pk_service_commercial PRIMARY KEY(service_commercial_id),
                                   CONSTRAINT fk_service_commercial_compte_id FOREIGN KEY(compte_id) REFERENCES compte(compte_id)

);

-- -----------------------------------------------------------------------------
--       TABLE : SERVICE_JURIDIQUE
-- -----------------------------------------------------------------------------

CREATE TABLE service_juridique(

                                  service_juridique_id INT AUTO_INCREMENT,
                                  compte_id INT NOT NULL,

                                  CONSTRAINT pk_service_juridique PRIMARY KEY(service_juridique_id),
                                  CONSTRAINT fk_service_juridique_compte_id FOREIGN KEY(compte_id) REFERENCES compte(compte_id)

);

-- -----------------------------------------------------------------------------
--       TABLE : CATEGORIE
-- -----------------------------------------------------------------------------

CREATE TABLE categorie(

                          categorie_id INT AUTO_INCREMENT,
                          categorie_libelle VARCHAR(50) NOT NULL,
                          super_categorie_id INT,

                          CONSTRAINT pk_categorie PRIMARY KEY(categorie_id),
                          CONSTRAINT fk_categorie_super_categorie_id FOREIGN KEY (super_categorie_id) REFERENCES categorie (categorie_id)

);

-- -----------------------------------------------------------------------------
--       TABLE : AVIS
-- -----------------------------------------------------------------------------

CREATE TABLE avis(

                     avis_id INT AUTO_INCREMENT,
                     avis_libelle VARCHAR(50) NOT NULL,
                     PRIMARY KEY(avis_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : FRAIS
-- -----------------------------------------------------------------------------

CREATE TABLE frais(
                      frais_id INT AUTO_INCREMENT,
                      PRIMARY KEY(frais_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : COMMISSION
-- -----------------------------------------------------------------------------

CREATE TABLE commission(
                           commission_id INT AUTO_INCREMENT,
                           PRIMARY KEY(commission_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : TYPE_MEMBRE
-- -----------------------------------------------------------------------------

CREATE TABLE type_membre(
                            type_membre_id INT AUTO_INCREMENT,
                            type_membre_libelle VARCHAR(50),
                            PRIMARY KEY(type_membre_id)
);

-- -----------------------------------------------------------------------------
--       TABLE : OPTION
-- -----------------------------------------------------------------------------

CREATE TABLE option_enchere(

                               option_enchere_id INT AUTO_INCREMENT,
                               option_enchere_libelle VARCHAR(255),
                               option_enchere_prix_catalogue FLOAT,
                               option_enchere_prix_gold FLOAT,

                               PRIMARY KEY(option_enchere_id)

);

-- -----------------------------------------------------------------------------
--       TABLE : ARTICLE
-- -----------------------------------------------------------------------------

CREATE TABLE article(

                        article_id INT AUTO_INCREMENT,
                        article_titre VARCHAR(50) NOT NULL,
                        article_description VARCHAR(50),
                        article_frais_port FLOAT,
                        article_region_livraison VARCHAR(50),
                        article_date_heure_creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        article_date_cloture DATE,
                        article_mode_cloture VARCHAR(50),
                        article_montant_vente FLOAT,
                        article_prix_depart FLOAT,
                        article_prix_reserve FLOAT,
                        article_prix_achat_immediat FLOAT,
                        article_pas FLOAT,
                        article_prix_en_cours FLOAT,
                        article_cloturer TINYINT(1) DEFAULT 0,
                        article_bloquer TINYINT(1) DEFAULT 0,
                        categorie_id INT,
                        option_enchere_id INT,
                        membre_vendeur_id INT NOT NULL,
                        membre_acheteur_id INT,

                        CONSTRAINT pk_article PRIMARY KEY(article_id),
                        CONSTRAINT fk_article_membre_vendeur_id FOREIGN KEY (membre_vendeur_id) REFERENCES membre (membre_id),
                        CONSTRAINT fk_article_membre_acheteur_id FOREIGN KEY (membre_acheteur_id) REFERENCES membre (membre_id),
                        CONSTRAINT fk_article_categorie_id FOREIGN KEY (categorie_id) REFERENCES categorie (categorie_id),
                        CONSTRAINT fk_article_option_enchere_id FOREIGN KEY (option_enchere_id) REFERENCES option_enchere (option_enchere_id)

);

-- -----------------------------------------------------------------------------
--       TABLE : VOTE
-- -----------------------------------------------------------------------------

CREATE TABLE vote(

                     vote_id INT AUTO_INCREMENT,
                     vote_commentaire VARCHAR(255),
                     avis_id INT NOT NULL,
                     article_id INT NOT NULL,
                     membre_id INT NOT NULL,
                     type_membre_id INT NOT NULL,

                     CONSTRAINT pk_vote PRIMARY KEY(vote_id),
                     CONSTRAINT fk_vote_avis_id FOREIGN KEY (avis_id) REFERENCES avis (avis_id),
                     CONSTRAINT fk_vote_article_id FOREIGN KEY (article_id) REFERENCES article (article_id),
                     CONSTRAINT fk_vote_membre_id FOREIGN KEY (membre_id) REFERENCES membre (membre_id),
                     CONSTRAINT fk_vote_type_membre_id FOREIGN KEY (type_membre_id) REFERENCES type_membre (type_membre_id)
);


-- -----------------------------------------------------------------------------
--       TABLE : ENCHERE
-- -----------------------------------------------------------------------------

CREATE TABLE enchere(

                        enchere_id INT AUTO_INCREMENT,
                        enchere_montant FLOAT NOT NULL,
                        enchere_date_heure DATETIME,
                        membre_id INT NOT NULL,
                        article_id INT NOT NULL,

                        CONSTRAINT pk_enchere PRIMARY KEY(enchere_id),
                        CONSTRAINT fk_enchere_membre_id FOREIGN KEY(membre_id) REFERENCES membre(membre_id),
                        CONSTRAINT fk_enchere_article_id FOREIGN KEY(article_id) REFERENCES article(article_id)

);

-- -----------------------------------------------------------------------------
--       TABLE : SIGNALER_ARTICLE
-- -----------------------------------------------------------------------------

CREATE TABLE signaler_article(

                                 signaler_article_id INT AUTO_INCREMENT,
                                 signaler_article_commentaire VARCHAR(255) NOT NULL,
                                 article_id INT NOT NULL,
                                 service_juridique_id INT NOT NULL,

                                 CONSTRAINT pk_signaler_article PRIMARY KEY(signaler_article_id),
                                 CONSTRAINT fk_signaler_article_article_id FOREIGN KEY(article_id) REFERENCES article(article_id),
                                 CONSTRAINT fk_signaler_article_service_juridique_id FOREIGN KEY(service_juridique_id) REFERENCES service_juridique(service_juridique_id)

);

-- -----------------------------------------------------------------------------
--       TABLE : SIGNALER_MEMBRE
-- -----------------------------------------------------------------------------

CREATE TABLE signaler_membre(

                                signaler_membre_id INT AUTO_INCREMENT,
                                signaler_membre_commentaire VARCHAR(255) NOT NULL,
                                membre_id INT NOT NULL,
                                service_juridique_id INT NOT NULL,

                                CONSTRAINT pk_signaler_membre PRIMARY KEY(signaler_membre_id),
                                CONSTRAINT fk_signaler_membre_membre_id FOREIGN KEY(membre_id) REFERENCES membre(membre_id),
                                CONSTRAINT fk_signaler_membre_service_juridique_id FOREIGN KEY(service_juridique_id) REFERENCES service_juridique(service_juridique_id)

);

-- -----------------------------------------------------------------------------
--       INSERT
-- -----------------------------------------------------------------------------

INSERT INTO COMPTE (compte_id, compte_email, compte_mdp, compte_role) VALUES
(1, 'caroline.dubois@gmail.fr', 'pwd', 'GESTIONNAIRE'),
(2, 'jean.gautier@gmail.fr', 'pwd', 'SERVICE_JUIDIQUE'),
(3, 'emmeline.charbonneau@gmail.fr', 'pwd', 'SERVICE_COMMERCIAL'),
(4, 'fantina.sorel@gmail.fr', 'pwd', 'MEMBRE');

INSERT INTO GESTIONNAIRE (gestionnaire_id, compte_id) VALUES
(1, 1);

INSERT INTO SERVICE_JURIDIQUE (service_juridique_id, compte_id) VALUES
    (2, 2);

INSERT INTO SERVICE_COMMERCIAL (service_commercial_id, compte_id) VALUES
    (3, 3);

INSERT INTO MEMBRE (membre_id, membre_nom, membre_prenom, compte_id) VALUES
    (4, "sorel", "fantina", 4);

INSERT INTO avis (avis_libelle) VALUES
("Très Bon"),
("Gros Problème");


INSERT INTO type_membre (type_membre_libelle) VALUES
("VENDEUR"),
("ACHETEUR");

-- INSERT INTO role (role_libelle) VALUES
-- ("membre"),
-- ("service_juridique"),
-- ("service_commercial"),
-- ("gestionnaire");


INSERT INTO option_enchere (option_enchere_libelle, option_enchere_prix_catalogue, option_enchere_prix_gold) VALUES
("Compteur de visites", 0, 0),
("Photo dans la liste des objets", 0.5, 0.3),
("Photo supplémentaire", 0.2, 0.15),
("Titre en gras", 0.1, 0.1);


INSERT INTO categorie (categorie_id, categorie_libelle, super_categorie_id) VALUES
(1, "Multimédia", null),
(2, "Télévision", 1),
(3, "Vidéo", 1),
(4, "Audio-HIFI", 1),
(5, "Photo", 1),
(6, "Informatique", 1),
(7, "Téléphone", 1),
(8, "Equipement de bureau", 1),
(9, "Téléviseurs 4/3", 2),
(10, "Téléviseurs 16/9", 2),
(11, "Téléviseurs LCD", 2),
(12, "Téléviseurs Plasma", 2),

(13, "Electroménager", null),
(14, "Lavage", 13),
(15, "Froid", 13),
(16, "Lave-linge", 14),
(17, "Sèche-linge", 14);


