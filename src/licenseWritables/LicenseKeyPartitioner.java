package licenseWritables;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class LicenseKeyPartitioner extends Partitioner<LicenseKey,Text> {

	@Override
	public int getPartition(LicenseKey key, Text value, int numberOfReducers) {
		// TODO Auto-generated method stub
		return (key.hashCode() & Integer.MAX_VALUE) % numberOfReducers;
	}
	

}
