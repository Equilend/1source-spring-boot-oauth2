select id, value, label
from (
select 1 as sort, s.eql_security_id as id, s.ticker as value, s.ticker || ': ' || m.security_description as label
from occ_eligible_sec s join eql_sec_master m on m.eql_security_id = s.eql_security_id and m.issuer_country_cd = 'US' and m.exchange_country_cd = 'US' and m.active_ind = 'Y'
union
select 2 as sort, s.eql_security_id as id, s.cusip as value, s.cusip || ': ' || m.security_description as label from occ_eligible_sec s join eql_sec_master m on m.eql_security_id = s.eql_security_id and m.issuer_country_cd = 'US' and m.exchange_country_cd = 'US' and m.active_ind = 'Y') x
order by sort, value;