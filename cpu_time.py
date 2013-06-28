from time import sleep

process_id = '1871'      # Process id is figured out using `ps -aux | grep <process-name>`

fh = open('/proc/stat', "r")
lines = fh.readlines()
arr = lines[0].split(' ')
time_total_before=0
for i in range(2,len(arr)):
	time_total_before = time_total_before + int(arr[i])

fh = open('/proc/'+process_id+'/stat', "r")
lines = fh.readlines()
arr = lines[0].split(' ')
utime_before=int(arr[13])
stime_before=int(arr[14])

sleep(1)

fh = open('/proc/stat', "r")
lines = fh.readlines()
arr = lines[0].split(' ')
time_total_after=0
for i in range(2,len(arr)):
	time_total_after = time_total_after + int(arr[i])

fh = open('/proc/'+process_id+'/stat', "r")
lines = fh.readlines()
arr = lines[0].split(' ')
utime_after=int(arr[13])
stime_after=int(arr[14])

user_util = 100 * float(utime_after - utime_before) / (time_total_after - time_total_before);
sys_util = 100 * float(stime_after - stime_before) / (time_total_after - time_total_before);

print "Before: User Utilization Time = ", utime_before, ", System Utilization Time = ", stime_before, ", Total Utilization Time = ", time_total_before
print "After: User Utilization Time =  ", utime_after, ", System Utilization Time = ", stime_after, ", Total Utilization Time = ", time_total_after
print "User Utilization Time = ", user_util
print "System Utilization Time = ", sys_util
