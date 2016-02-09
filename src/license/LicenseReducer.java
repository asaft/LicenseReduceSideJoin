package license;

import java.io.IOException;
import java.util.Iterator;

import licenseWritables.JoinNameAndLicense;
import licenseWritables.LicenseKey;
import licenseWritables.LicenseNameWritable;
import licenseWritables.LicenseTypeWritable;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;



public class LicenseReducer extends Reducer<LicenseKey, JoinNameAndLicense, Text, Text>  {

	Text _keyOut = new Text(); 
	Text _valueOut = new Text();
	@Override
	protected void reduce(
			LicenseKey key,
			Iterable<JoinNameAndLicense> values,
			Reducer<LicenseKey, JoinNameAndLicense, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Iterator<JoinNameAndLicense> it = values.iterator();
		if (it.hasNext()){
			JoinNameAndLicense  license = it.next();
			LicenseNameWritable studentName = ( LicenseNameWritable)license.get_licenseWritable();
			_keyOut.set(studentName.get_name());
			int numofCourses;
			String licenseInfo = "";   
		
			
			for ( numofCourses=1;it.hasNext();numofCourses++){
				license  = it.next(); 
				LicenseTypeWritable licenseType = (LicenseTypeWritable )license.get_licenseWritable();
				if(numofCourses == 1){
				licenseInfo +=""+numofCourses+"." + licenseType.get_type();
				}else{
				licenseInfo +=" "+numofCourses+"." + licenseType.get_type();
				}
			}
		
				_valueOut.set(licenseInfo);
				context.write(_keyOut, _valueOut);
			
			
			
		}
		
		
	}
	
	

}
