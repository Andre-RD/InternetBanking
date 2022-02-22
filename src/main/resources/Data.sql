CREATE TABLE TB_CLIENTE(    ID_CLIENTE NUMERIC NOT NULL AUTO_INCREMENT,
                            NOME_CLIENTE VARCHAR(250),
                            PLANO_EXCLUSIVO BIT,
                            SALDO NUMERIC,
                            NUMERO_CONTA VARCHAR,
                            DATA_NASCIMENTO DATE,
                        PRIMARY KEY(ID_CLIENTE));

INSERT INTO TB_CLIENTE VALUES(1,'ANDRÉ MOURA', 'FALSE', 7500.60 , '1234-12312', TO_DATE('03/03/1999','DD/MM/YYYY'));

CREATE TABLE TB_TIPO_TRANSACAO (CD_TIPO_TRANSACAO NUMERIC NOT NULL,
                                DS_TIPO_TRANSACAO  VARCHAR,
                                 PRIMARY KEY(CD_TIPO_TRANSACAO));

CREATE TABLE TB_TRANSACOES( CODIGO_TRANSACAO NUMERIC NOT NULL AUTO_INCREMENT,
                            ID_CLIENTE NUMERIC NOT NULL,
                            VALOR_TRANSACAO NUMERIC,
                            VALOR_JUROS NUMERIC,
                            CD_TIPO_TRANSACAO NUMERIC,
                            DT_TRANSACAO DATE,
                            FOREIGN KEY(ID_CLIENTE) REFERENCES TB_CLIENTE(ID_CLIENTE),
                            FOREIGN KEY(CD_TIPO_TRANSACAO) REFERENCES TB_TIPO_TRANSACAO(CD_TIPO_TRANSACAO),
                            PRIMARY KEY(CODIGO_TRANSACAO,ID_CLIENTE));


INSERT INTO TB_TIPO_TRANSACAO VALUES(1, 'DEPOSITO');
INSERT INTO TB_TIPO_TRANSACAO VALUES(2, 'SAQUE');
INSERT INTO TB_TIPO_TRANSACAO VALUES(3, 'JUROS');
INSERT INTO TB_TRANSACOES VALUES( 1, 1, 7684.50 ,0, 1, TO_DATE('12/05/2021','DD/MM/YYYY'));
INSERT INTO TB_TRANSACOES VALUES( 2, 1, 122.50 ,0.5, 2, TO_DATE('22/09/2021','DD/MM/YYYY'));