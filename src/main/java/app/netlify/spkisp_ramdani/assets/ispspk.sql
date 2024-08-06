-- akun definition

CREATE TABLE IF NOT EXISTS akun (
	id_akun INTEGER PRIMARY KEY,
	nama_pengguna TEXT,
	sandi TEXT
	, alamat TEXT, telepon TEXT, email TEXT);

-- bobot definition

CREATE TABLE IF NOT EXISTS bobot (
	id_bobot INTEGER PRIMARY KEY,
	id_prasetel INTEGER,
	id_kriteria INTEGER,
	nilai_bobot REAL
);

-- bobot_prasetel definition

CREATE TABLE IF NOT EXISTS bobot_prasetel (
	id_prasetel INTEGER PRIMARY KEY,
	nama_prasetel TEXT,
	digunakan INTEGER
);

-- kriteria definition

CREATE TABLE IF NOT EXISTS kriteria (
	id_kriteria INTEGER PRIMARY KEY,
	nama_kriteria TEXT,
	jenis_kriteria TEXT
, satuan_kriteria TEXT
);

-- nilai definition

CREATE TABLE IF NOT EXISTS nilai (
	id_paket INTEGER,
	id_kriteria INTEGER,
	nilai INTEGER
);

-- paket definition

CREATE TABLE IF NOT EXISTS paket (
	id_paket INTEGER PRIMARY KEY,
	id_provider INTEGER,
	nama_paket TEXT,
	keterangan_paket TEXT
);

-- provider definition

CREATE TABLE IF NOT EXISTS provider (
	id_provider INTEGER PRIMARY KEY,
	nama_provider TEXT
);

-- v_bobot source

CREATE VIEW IF NOT EXISTS v_bobot AS
SELECT id_bobot, nama_kriteria AS id_kriteria, nilai_bobot, id_prasetel FROM bobot p LEFT JOIN kriteria p2 ON p.id_kriteria = p2.id_kriteria;

-- v_paket source

CREATE VIEW IF NOT EXISTS v_paket AS
SELECT id_paket, nama_provider as id_provider, nama_paket, keterangan_paket FROM paket p LEFT JOIN provider p2 ON p.id_provider = p2.id_provider;

INSERT INTO akun (id_akun,nama_pengguna,sandi,alamat,telepon,email) VALUES
	 (1,'admin','admin','alamat admin','081111111111','admin@mail.com');
