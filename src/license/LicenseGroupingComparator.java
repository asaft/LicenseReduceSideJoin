package license;



import licenseWritables.LicenseKey;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;




public class LicenseGroupingComparator extends WritableComparator {
	
	public LicenseGroupingComparator(){
		super(LicenseKey.class,true);
	}
	@Override
	public int compare(WritableComparable w1,WritableComparable w2){
		int retVal = 0;
		LicenseKey s1 = (LicenseKey)w1;
		LicenseKey s2 = (LicenseKey)w2;
		retVal = s1.get_id().compareTo(s2.get_id());
		return retVal;
	}
}
