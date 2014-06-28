// Process's CPU Utiization Time 
// Run as `javac cpu_time.java` 
// Then java cpu_time

/*
Copyright (C) 2014 by Neeraj Tuteja

Permission is hereby granted, free of charge, to any person obtaining a copyof this software and associated documentation files (the "Software"), to dealin the Software without restriction, including without limitation the rightsto use, copy, modify, merge, publish, distribute, sublicense, and/or sellcopies of the Software, and to permit persons to whom the Software isfurnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included inall copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS ORIMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THEAUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHERLIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS INTHE SOFTWARE.
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;

public class cpu_time {
	public static void  main(String []args){
		String process_id = new String("1871");      // Process id is figured out using `ps -aux | grep <process-name>`
		FileInputStream	fis = null;
		BufferedReader reader = null;
		long time_total_before = 0,  time_total_after = 0, utime_before = 0, stime_before = 0, utime_after = 0, stime_after = 0;

//               /proc/stat read before
		try {
			fis = new FileInputStream("/proc/stat");
			reader = new BufferedReader(new InputStreamReader(fis));
			String line = reader.readLine();
			String [] arr = line.split(" ");
			for(int i=2;i<arr.length;++i){
				time_total_before += Integer.parseInt(arr[i]);
			}
		}
		catch (FileNotFoundException ex){
			Logger.getLogger(cpu_time.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (IOException ex){
			Logger.getLogger(cpu_time.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally {
			try {
				reader.close();
	                	fis.close();
        		} 
			catch (IOException ex) {
		                Logger.getLogger(cpu_time.class.getName()).log(Level.SEVERE, null, ex);
		        }
		}

//               /proc/<process-id>/stat read before
                try {
                        fis = new FileInputStream("/proc/"+process_id+"/stat");
                        reader = new BufferedReader(new InputStreamReader(fis));
                        String line = reader.readLine();
                        String [] arr = line.split(" ");
                        utime_before = Integer.parseInt(arr[13]);
                        stime_before = Integer.parseInt(arr[14]);
                }
                catch (FileNotFoundException ex){
                        Logger.getLogger(cpu_time.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IOException ex){
                        Logger.getLogger(cpu_time.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally {
                        try {
                                reader.close();
                                fis.close();
                        }
                        catch (IOException ex) {
                                Logger.getLogger(cpu_time.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }


            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }

//               /proc/stat read AFTER
                try {
                        fis = new FileInputStream("/proc/stat");
                        reader = new BufferedReader(new InputStreamReader(fis));
                        String line = reader.readLine();
                        String [] arr = line.split(" ");
                        for(int i=2;i<arr.length;++i){
                                time_total_after+= Integer.parseInt(arr[i]);
                        }
                }
                catch (FileNotFoundException ex){
                        Logger.getLogger(cpu_time.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IOException ex){
                        Logger.getLogger(cpu_time.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally {
                        try {
                                reader.close();
                                fis.close();
                        }
                        catch (IOException ex) {
                                Logger.getLogger(cpu_time.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }

//               /proc/<process-id>/stat read AFTER
                try {
                        fis = new FileInputStream("/proc/"+process_id+"/stat");
                        reader = new BufferedReader(new InputStreamReader(fis));
                        String line = reader.readLine();
                        String [] arr = line.split(" ");
                        utime_after = Integer.parseInt(arr[13]);
                        stime_after = Integer.parseInt(arr[14]);
                }
                catch (FileNotFoundException ex){
                        Logger.getLogger(cpu_time.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IOException ex){
                        Logger.getLogger(cpu_time.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally {
                        try {
                                reader.close();
                                fis.close();
                        }
                        catch (IOException ex) {
                                Logger.getLogger(cpu_time.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
double user_util = 100 * (double)(utime_after - utime_before) / (time_total_after - time_total_before);
double sys_util = 100 * (double)(stime_after - stime_before) / (time_total_after - time_total_before);

System.out.println("Before: User Utilization Time = "+ utime_before+ ", System Utilization Time = "+ stime_before+ ", Total Utilization Time = "+ time_total_before);
System.out.println("After: User Utilization Time =  "+ utime_after+ ", System Utilization Time = "+ stime_after+ ", Total Utilization Time = "+ time_total_after);
System.out.println("User Utilization Time = " + user_util);
System.out.println("System Utilization Time = "+ sys_util);


	}
}


