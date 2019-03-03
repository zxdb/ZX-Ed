-- [ZX-Ed] Prepare ZXDB for editing.
-- by Einar Saukas

USE zxdb;

alter table aliases add column if not exists zxed int(11) not null default 0;
alter table authors add column if not exists zxed int(11) not null default 0;
alter table availabletypes add column if not exists zxed int(11) not null default 0;
alter table booktypeins add column if not exists zxed int(11) not null default 0;
alter table compilations add column if not exists zxed int(11) not null default 0;
alter table countries add column if not exists zxed int(11) not null default 0;
alter table downloads add column if not exists zxed int(11) not null default 0;
alter table entries add column if not exists zxed int(11) not null default 0;
alter table extensions add column if not exists zxed int(11) not null default 0;
alter table extras add column if not exists zxed int(11) not null default 0;
alter table features add column if not exists zxed int(11) not null default 0;
alter table filetypes add column if not exists zxed int(11) not null default 0;
alter table genretypes add column if not exists zxed int(11) not null default 0;
alter table groups add column if not exists zxed int(11) not null default 0;
alter table grouptypes add column if not exists zxed int(11) not null default 0;
alter table hosts add column if not exists zxed int(11) not null default 0;
alter table idioms add column if not exists zxed int(11) not null default 0;
alter table interviews add column if not exists zxed int(11) not null default 0;
alter table issues add column if not exists zxed int(11) not null default 0;
alter table labelfiles add column if not exists zxed int(11) not null default 0;
alter table labels add column if not exists zxed int(11) not null default 0;
alter table labeltypes add column if not exists zxed int(11) not null default 0;
alter table licenses add column if not exists zxed int(11) not null default 0;
alter table licensetypes add column if not exists zxed int(11) not null default 0;
alter table licensors add column if not exists zxed int(11) not null default 0;
alter table machinetypes add column if not exists zxed int(11) not null default 0;
alter table magazines add column if not exists zxed int(11) not null default 0;
alter table magfiles add column if not exists zxed int(11) not null default 0;
alter table magrefs add column if not exists zxed int(11) not null default 0;
alter table members add column if not exists zxed int(11) not null default 0;
alter table nvgs add column if not exists zxed int(11) not null default 0;
alter table permissions add column if not exists zxed int(11) not null default 0;
alter table permissiontypes add column if not exists zxed int(11) not null default 0;
alter table platforms add column if not exists zxed int(11) not null default 0;
alter table ports add column if not exists zxed int(11) not null default 0;
alter table publicationtypes add column if not exists zxed int(11) not null default 0;
alter table publishers add column if not exists zxed int(11) not null default 0;
alter table referencetypes add column if not exists zxed int(11) not null default 0;
alter table relatedlicenses add column if not exists zxed int(11) not null default 0;
alter table relations add column if not exists zxed int(11) not null default 0;
alter table relationtypes add column if not exists zxed int(11) not null default 0;
alter table releases add column if not exists zxed int(11) not null default 0;
alter table remakes add column if not exists zxed int(11) not null default 0;
alter table roles add column if not exists zxed int(11) not null default 0;
alter table roletypes add column if not exists zxed int(11) not null default 0;
alter table schemetypes add column if not exists zxed int(11) not null default 0;
alter table scores add column if not exists zxed int(11) not null default 0;
alter table toolfiles add column if not exists zxed int(11) not null default 0;
alter table tools add column if not exists zxed int(11) not null default 0;
alter table topics add column if not exists zxed int(11) not null default 0;
alter table topictypes add column if not exists zxed int(11) not null default 0;
alter table variationtypes add column if not exists zxed int(11) not null default 0;
alter table webrefs add column if not exists zxed int(11) not null default 0;
alter table websites add column if not exists zxed int(11) not null default 0;

-- END
