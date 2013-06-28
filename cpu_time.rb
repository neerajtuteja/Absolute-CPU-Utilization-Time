#### Process's CPU Utiization Time ####
#### Run as `ruby cpu_time.py` ####

process_id = '1941'      # Process id is figured out using `ps -aux | grep <process-name>`

line = File.open('/proc/stat'){|f|  f.readline}
line = line.strip
arr = line.split(' ')
time_total_before=0
for i in (1...(arr.length))
	time_total_before = time_total_before + arr[i].to_i
end


line = File.open('/proc/'+process_id+'/stat'){|f|  f.readline}
line = line.strip
arr = line.split(' ')
utime_before = arr[13].to_i
stime_before = arr[14].to_i

sleep(1)

line = File.open('/proc/stat'){|f|  f.readline}
line = line.strip
arr = line.split(' ')
time_total_after=0
for i in (1...(arr.length))
        time_total_after = time_total_after + arr[i].to_i
end


line = File.open('/proc/'+process_id+'/stat'){|f|  f.readline}
line = line.strip
arr = line.split(' ')
utime_after = arr[13].to_i
stime_after = arr[14].to_i

user_util = 100 * (utime_after - utime_before).to_f / (time_total_after - time_total_before);
sys_util = 100 * (stime_after - stime_before).to_f / (time_total_after - time_total_before);

print "Before: User Utilization Time = "+ utime_before.to_s + ", System Utilization Time = " + stime_before.to_s + ", Total Utilization Time = " + time_total_before.to_s + "\n"
print "After: User Utilization Time =  "+ utime_after.to_s + ", System Utilization Time = " + stime_after.to_s + ", Total Utilization Time = " + time_total_after.to_s + "\n"
print "User Utilization Time = "+ user_util.to_s + "\n"
print "System Utilization Time = "+ sys_util.to_s + "\n"
