-- [ZX-Ed] Restore original ZXDB structure.
-- by Einar Saukas

USE zxdb;

alter table aliases drop column if exists zxed;
alter table authors drop column if exists zxed;
alter table availabletypes drop column if exists zxed;
alter table booktypeins drop column if exists zxed;
alter table compilations drop column if exists zxed;
alter table countries drop column if exists zxed;
alter table downloads drop column if exists zxed;
alter table entries drop column if exists zxed;
alter table extensions drop column if exists zxed;
alter table extras drop column if exists zxed;
alter table features drop column if exists zxed;
alter table filetypes drop column if exists zxed;
alter table genretypes drop column if exists zxed;
alter table groups drop column if exists zxed;
alter table grouptypes drop column if exists zxed;
alter table hosts drop column if exists zxed;
alter table idioms drop column if exists zxed;
alter table interviews drop column if exists zxed;
alter table issues drop column if exists zxed;
alter table labelfiles drop column if exists zxed;
alter table labels drop column if exists zxed;
alter table labeltypes drop column if exists zxed;
alter table licenses drop column if exists zxed;
alter table licensetypes drop column if exists zxed;
alter table licensors drop column if exists zxed;
alter table machinetypes drop column if exists zxed;
alter table magazines drop column if exists zxed;
alter table magfiles drop column if exists zxed;
alter table magrefs drop column if exists zxed;
alter table members drop column if exists zxed;
alter table nvgs drop column if exists zxed;
alter table permissions drop column if exists zxed;
alter table permissiontypes drop column if exists zxed;
alter table platforms drop column if exists zxed;
alter table ports drop column if exists zxed;
alter table publicationtypes drop column if exists zxed;
alter table publishers drop column if exists zxed;
alter table referencetypes drop column if exists zxed;
alter table relatedlicenses drop column if exists zxed;
alter table relations drop column if exists zxed;
alter table relationtypes drop column if exists zxed;
alter table releases drop column if exists zxed;
alter table remakes drop column if exists zxed;
alter table roles drop column if exists zxed;
alter table roletypes drop column if exists zxed;
alter table schemetypes drop column if exists zxed;
alter table scores drop column if exists zxed;
alter table toolfiles drop column if exists zxed;
alter table tools drop column if exists zxed;
alter table topics drop column if exists zxed;
alter table topictypes drop column if exists zxed;
alter table variationtypes drop column if exists zxed;
alter table webrefs drop column if exists zxed;
alter table websites drop column if exists zxed;

-- END
