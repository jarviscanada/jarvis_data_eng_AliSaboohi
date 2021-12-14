CREATE TABLE IF NOT EXISTS PUBLIC.host_info (
	id		SERIAL NOT NULL,
	hostname	VARCHAR NOT NULL,
	cpu_number	SMALLINT NOT NULL,
	cpu_architecture VARCHAR NOT NULL,
	cpu_model	VARCHAR NOT NULL,
	cpu_mhz		DECIMAL NOT NULL,
	L2_cache	VARCHAR NOT NULL,
	total_mem	VARCHAR NOT NULL,
	"timestamp"	TIMESTAMP NOT NULL,
	UNIQUE(hostname),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS PUBLIC.host_usage (
	host_id		SERIAL NOT NULL,
	"timestamp"	TIMESTAMP NOT NULL,
	memory_free	INT NOT NULL,
	cpu_idle	SMALLINT NOT NULL,
	cpu_kernel	SMALLINT NOT NULL,
	disk_io		SMALLINT NOT NULL,
	disk_available	VARCHAR NOT NULL,
	FOREIGN KEY(host_id) REFERENCES host_info(id)
);