-- 1. Group hosts by hardware info
select cpu_number,
       id,
       total_mem
from host_info
group by cpu_number,id,total_mem
order by total_mem desc;

-- 2. Average memory usage
select id as "host_id",hostname,aa.ts,aa.avg
from
(select  round5(host_usage."timestamp") as ts,CAST((((CAST(RTRIM (total_mem, 'K') as decimal) - memory_free)/CAST(RTRIM (total_mem, 'K') as decimal))*100) as INT) as "avg",host_id as x
from host_usage
join host_info
on host_info.id = host_usage.host_id
group by ts,avg,host_id
order by ts
) aa
inner join  host_info on id = aa.x

-- 3. Detect host failure
select host_id ,round5("timestamp"), count(round5("timestamp"))
from host_usage
group by round5("timestamp"), host_id
having count(round5("timestamp")) < 3
order by round5(round5("timestamp"));
