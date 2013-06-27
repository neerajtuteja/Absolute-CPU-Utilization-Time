<?php
$process_id = '1972';

$fh = fopen('/proc/stat', "r");
$line  = fgets($fh,4096);
$arr = explode(" ", $line);
$time_total_before=0;
for($i=1;$i<sizeof($arr);++$i){
  $time_total_before+= (int)$arr[$i];
}
$fh = fopen('/proc/'.$process_id.'/stat', "r");
$line  = fgets($fh,4096);
$arr = explode(" ", $line);
$utime_before=(int)$arr[13];
$stime_before=(int)$arr[14];

sleep(1);

$fh = fopen('/proc/stat', "r");
$line  = fgets($fh,4096);
$arr = explode(" ", $line);
$time_total_after=0;
for($i=1;$i<sizeof($arr);++$i){
  $time_total_after+= (int)$arr[$i];
}
$fh = fopen('/proc/'.$process_id.'/stat', "r");
$line  = fgets($fh,4096);
$arr = explode(" ", $line);
$utime_after=(int)$arr[13];
$stime_after=(int)$arr[14];


$user_util = 100 * ($utime_after - $utime_before) / ($time_total_after - $time_total_before);
$sys_util = 100 * ($stime_after - $stime_before) / ($time_total_after - $time_total_before);



echo "\nBefore: User Utilization Time =  $utime_before, System Utilization Time = $stime_before, Total Utilization Time = $time_total_before\n";
echo "\nAfter: User Utilization Time =  $utime_after, System Utilization Time = $stime_after, Total Utilization Time = $time_total_after\n";
echo "\nUser Utilization Time = ".$user_util."\n";
echo "\nSystem Utilization Time = ".$sys_util."\n";


?>
