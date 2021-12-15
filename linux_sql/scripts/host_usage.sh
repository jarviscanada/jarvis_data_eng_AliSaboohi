psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne  5 ]; then
  echo "Bad Arguments"
  exit 1
fi

vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)

timestamp=$(vmstat -t | tail -1 | awk '{print $18,$19}' | xargs)
memory_free=$(vmstat --unit M | tail -1 | awk '{print $4}' | xargs)
cpu_idle=$(vmstat --unit M | tail -1 | awk '{print $15}' | xargs)
cpu_kernel=$(vmstat --unit M | tail -1 | awk '{print $13}' | xargs)
disk_io=$(vmstat -d | tail -1 | awk '{print $10}' | xargs)
disk_available=$(df -BM | tail -3 | head -1 | awk '{print $4}' | xargs)

host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

insert_stmt="INSERT INTO host_usage(host_id,timestamp,memory_free,cpu_idle,cpu_kernel,disk_io,disk_available)
VALUES((SELECT id FROM host_info WHERE hostname='$hostname'),'$timestamp','$memory_free','$cpu_idle','$cpu_kernel','$disk_io','$disk_available')"

export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?