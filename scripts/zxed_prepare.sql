-- [ZX-Ed] Prepare ZXDB for editing.
-- by Einar Saukas

USE zxdb;

ALTER TABLE aliases ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE authors ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE availabletypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE booktypeins ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE compilations ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE countries ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE downloads ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE entries ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE extras ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE features ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE filetypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE formattypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE frameworks ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE genretypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE groups ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE grouptypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE hosts ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE idioms ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE interviews ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE issues ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE labelfiles ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE labels ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE labeltypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE licenses ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE licensetypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE licensors ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE machinetypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE magazines ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE magfiles ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE magrefs ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE members ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE nvgs ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE origintypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE permissions ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE permissiontypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE platforms ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE ports ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE publicationtypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE publishers ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE referencetypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE relatedlinks ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE releases ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE remakes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE roles ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE roletypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE schemetypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE scores ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE toolfiles ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE tools ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE topics ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE topictypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE variationtypes ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;
ALTER TABLE websites ADD COLUMN IF NOT EXISTS zxed int(11) NOT NULL default 0;

-- END
