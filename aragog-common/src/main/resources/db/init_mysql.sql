CREATE TABLE rule (
  id                 INT AUTO_INCREMENT,
  uuid               VARCHAR(128),
  rule_name          VARCHAR(32),
  create_time        DATETIME,
  update_time        DATETIME,
  script_type        VARCHAR(32),
  list_page_script   TEXT,
  detail_page_script TEXT
);
